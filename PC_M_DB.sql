DROP USER PC_M_DB CASCADE;

CREATE USER PC_M IDENTIFIED BY 1234 ;
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
GRANT connect, resource, dba TO PC_M;


drop table PCȸ��;
drop table �ֹ�;
drop table ��ǰ;


CREATE TABLE PCȸ��(
 �̸�  VARCHAR(20),
 ȸ�����̵� VARCHAR(20),
 ȸ����й�ȣ VARCHAR(20),
 ��ȭ��ȣ VARCHAR(20),
 ������� DATE,
 �����ð� INT,
 
 PRIMARY KEY(ȸ�����̵�)

);

CREATE TABLE ��ǰ(
 ī�װ���ȣ INT ,
 ī�װ���  VARCHAR(20),
 ��ǰ��ȣ INT,
 ��ǰ�� VARCHAR(20),
 ���� INT,
 ��� INT,
 
 PRIMARY KEY(��ǰ��ȣ)

);

CREATE TABLE �ֹ�(
 �ֹ���ȣ INT ,
 ȸ�����̵� VARCHAR(20),
 �����Ͻ� DATE,
 ��ǰ��ȣ INT,
 ��ǰ���� INT,
 
 CONSTRAINT �ֹ� PRIMARY KEY(��ǰ��ȣ, �ֹ���ȣ,ȸ�����̵�),
 FOREIGN KEY (ȸ�����̵�) REFERENCES PCȸ��(ȸ�����̵�),
 FOREIGN KEY (��ǰ��ȣ)  REFERENCES ��ǰ(��ǰ��ȣ)

);

INSERT INTO PCȸ�� VALUES  ('������', 'kth', '0000', '01011111111','1998/11/20',120);
INSERT INTO PCȸ�� VALUES  ('���¿�', 'nsw', '1111', '01011111112','1997/01/01',180);
INSERT INTO PCȸ�� VALUES  ('�̻��', 'lsm', '2222', '01011111113','1997/02/02',60);
INSERT INTO PCȸ�� VALUES  ('�ӹ̼�', 'ims', '3333', '01011111114','2000/03/03',10);
INSERT INTO PCȸ�� VALUES  ('ȫ�浿', 'hkd', '4444', '01011111115','2000/04/04',100);
INSERT INTO PCȸ�� VALUES  ('��浿', 'kkd', '5555', '01011111116','2000/05/04',100);
INSERT INTO PCȸ�� VALUES  ('�̱浿', 'lkd', '6666', '01011111117','2000/06/04',110);
INSERT INTO PCȸ�� VALUES  ('�ڱ浿', 'pkd', '7777', '01011111118','2000/07/04',120);
INSERT INTO PCȸ�� VALUES  ('���浿', 'nkd', '8888', '01011111119','2000/08/04',130);
INSERT INTO PCȸ�� VALUES  ('�ӱ浿', 'ikd', '9999', '01011111110','2000/09/04',140);
INSERT INTO PCȸ�� VALUES  ('���浿', 'kamkd', '1010', '01011111010','2000/10/04',140);


INSERT INTO ��ǰ VALUES  (001,'����',001,'���',3000,30);
INSERT INTO ��ǰ VALUES  (001,'����',002,'������',4500,40);
INSERT INTO ��ǰ VALUES  (002,'����',003,'�ݶ�',1500,50);
INSERT INTO ��ǰ VALUES  (002,'����',004,'���̴�',1500,50);
INSERT INTO ��ǰ VALUES  (003,'��Ÿ',005,'��īĨ',1700,25);
INSERT INTO ��ǰ VALUES  (003,'��Ÿ',006,'Ȩ����',1500,25);


DELETE FROM �ֹ�;


select*from PCȸ��;
select*from ��ǰ;
select*from �ֹ�;

SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER TRIGGER_PRICE
after insert OR update ON �ֹ�
FOR EACH ROW
BEGIN
    IF INSERTING THEN 
    UPDATE ��ǰ SET ��ǰ.��� = ��ǰ.��� - :new.��ǰ���� WHERE :NEW.��ǰ��ȣ = ��ǰ.��ǰ��ȣ;
    END IF;
END;


create or replace PROCEDURE ADD_other(
    V_�ֹ���ȣ in �ֹ�.�ֹ���ȣ%TYPE,
    V_���̵� in �ֹ�.ȸ�����̵�%TYPE,
    V_�����Ͻ� date,
    V_��ǰ��ȣ in �ֹ�.��ǰ��ȣ%TYPE,
    V_��ǰ���� in �ֹ�.��ǰ����%TYPE
   
)

as

BEGIN
    DBMS_OUTPUT.ENABLE;

    insert into �ֹ�(�ֹ���ȣ,ȸ�����̵�,�����Ͻ�,��ǰ��ȣ,��ǰ����)
    values(V_�ֹ���ȣ,V_���̵�,sysdate, V_��ǰ��ȣ, V_��ǰ����);

    commit;

    DBMS_OUTPUT.PUT_LINE( '�ֹ���ȣ : ' || V_�ֹ���ȣ);
    DBMS_OUTPUT.PUT_LINE( 'ȸ�����̵� : ' || V_���̵�);
    DBMS_OUTPUT.PUT_LINE( '��ǰ��ȣ : ' || V_��ǰ��ȣ);
    DBMS_OUTPUT.PUT_LINE( '��ǰ���� : ' || V_��ǰ����);

 end;
 




CREATE SEQUENCE �ֹ���ȣSEQ --�������� �����ȣ�ޱ��
   START WITH 1
   INCREMENT BY 1;

