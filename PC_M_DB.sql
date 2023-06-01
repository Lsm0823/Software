DROP USER PC_M_DB CASCADE;

CREATE USER PC_M IDENTIFIED BY 1234 ;
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
GRANT connect, resource, dba TO PC_M;


drop table PC회원;
drop table 주문;
drop table 상품;


CREATE TABLE PC회원(
 이름  VARCHAR(20),
 회원아이디 VARCHAR(20),
 회원비밀번호 VARCHAR(20),
 전화번호 VARCHAR(20),
 생년월일 DATE,
 남은시간 INT,
 
 PRIMARY KEY(회원아이디)

);

CREATE TABLE 상품(
 카테고리번호 INT ,
 카테고리명  VARCHAR(20),
 상품번호 INT,
 상품명 VARCHAR(20),
 가격 INT,
 재고량 INT,
 
 PRIMARY KEY(상품번호)

);

CREATE TABLE 주문(
 주문번호 INT ,
 회원아이디 VARCHAR(20),
 결제일시 DATE,
 상품번호 INT,
 상품수량 INT,
 
 CONSTRAINT 주문 PRIMARY KEY(상품번호, 주문번호,회원아이디),
 FOREIGN KEY (회원아이디) REFERENCES PC회원(회원아이디),
 FOREIGN KEY (상품번호)  REFERENCES 상품(상품번호)

);

INSERT INTO PC회원 VALUES  ('김태희', 'kth', '0000', '01011111111','1998/11/20',120);
INSERT INTO PC회원 VALUES  ('남승우', 'nsw', '1111', '01011111112','1997/01/01',180);
INSERT INTO PC회원 VALUES  ('이상목', 'lsm', '2222', '01011111113','1997/02/02',60);
INSERT INTO PC회원 VALUES  ('임미송', 'ims', '3333', '01011111114','2000/03/03',10);
INSERT INTO PC회원 VALUES  ('홍길동', 'hkd', '4444', '01011111115','2000/04/04',100);
INSERT INTO PC회원 VALUES  ('김길동', 'kkd', '5555', '01011111116','2000/05/04',100);
INSERT INTO PC회원 VALUES  ('이길동', 'lkd', '6666', '01011111117','2000/06/04',110);
INSERT INTO PC회원 VALUES  ('박길동', 'pkd', '7777', '01011111118','2000/07/04',120);
INSERT INTO PC회원 VALUES  ('남길동', 'nkd', '8888', '01011111119','2000/08/04',130);
INSERT INTO PC회원 VALUES  ('임길동', 'ikd', '9999', '01011111110','2000/09/04',140);
INSERT INTO PC회원 VALUES  ('감길동', 'kamkd', '1010', '01011111010','2000/10/04',140);


INSERT INTO 상품 VALUES  (001,'음식',001,'라면',3000,30);
INSERT INTO 상품 VALUES  (001,'음식',002,'볶음밥',4500,40);
INSERT INTO 상품 VALUES  (002,'음료',003,'콜라',1500,50);
INSERT INTO 상품 VALUES  (002,'음료',004,'사이다',1500,50);
INSERT INTO 상품 VALUES  (003,'기타',005,'포카칩',1700,25);
INSERT INTO 상품 VALUES  (003,'기타',006,'홈런볼',1500,25);


DELETE FROM 주문;


select*from PC회원;
select*from 상품;
select*from 주문;

SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER TRIGGER_PRICE
after insert OR update ON 주문
FOR EACH ROW
BEGIN
    IF INSERTING THEN 
    UPDATE 상품 SET 상품.재고량 = 상품.재고량 - :new.상품수량 WHERE :NEW.상품번호 = 상품.상품번호;
    END IF;
END;


create or replace PROCEDURE ADD_other(
    V_주문번호 in 주문.주문번호%TYPE,
    V_아이디 in 주문.회원아이디%TYPE,
    V_결제일시 date,
    V_상품번호 in 주문.상품번호%TYPE,
    V_상품수량 in 주문.상품수량%TYPE
   
)

as

BEGIN
    DBMS_OUTPUT.ENABLE;

    insert into 주문(주문번호,회원아이디,결제일시,상품번호,상품수량)
    values(V_주문번호,V_아이디,sysdate, V_상품번호, V_상품수량);

    commit;

    DBMS_OUTPUT.PUT_LINE( '주문번호 : ' || V_주문번호);
    DBMS_OUTPUT.PUT_LINE( '회원아이디 : ' || V_아이디);
    DBMS_OUTPUT.PUT_LINE( '상품번호 : ' || V_상품번호);
    DBMS_OUTPUT.PUT_LINE( '상품수량 : ' || V_상품수량);

 end;
 




CREATE SEQUENCE 주문번호SEQ --시퀸스로 리뷰번호메기기
   START WITH 1
   INCREMENT BY 1;

