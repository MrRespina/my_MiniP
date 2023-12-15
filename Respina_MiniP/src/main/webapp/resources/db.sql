CREATE TABLE MEMBER(

	m_id varchar2(10 char) primary key,
	m_pw varchar2(10 char) not null,
	m_name varchar2(10 char) not null,
	m_addr varchar2(200 char) not null,
	m_photo varchar2(200 char) not null,
	m_class number(1) not null

);

INSERT INTO MEMBER VALUES('respina','sdj7524','병천','07614,서울시 강서구 개화동로 23길 45,646-19','asdf.gif',1);

SELECT * FROM member;

DELETE FROM MEMBER WHERE m_id='pin';

UPDATE MEMBER SET m_name = '천' WHERE m_id='pin';

UPDATE member SET m_pw=1234,m_name='병천',m_addr='99999,서울시,646-22' WHERE m_id='pin';

DELETE FROM member WHERE m_id='pin';

= = = = = = = = = = = = = =

create table board(
	b_no number(4) primary key, 			
	b_owner varchar2(10 char) not null, 	
	b_text varchar2(300 char) not null,
	b_when date not null
);

create sequence board_seq;

SELECT COUNT(*) FROM board;

insert into board values(board_seq.nextval,'지병천','안녕하살법!',sysdate);
insert into board values(board_seq.nextval,'Respina','연습용....',sysdate);
insert into board values(board_seq.nextval,'Respina','연습!',sysdate);
insert into board values(board_seq.nextval,'pin','안녕!',sysdate);

select * from board ORDER BY b_when DESC;

UPDATE board SET b_when=sysdate,b_text='갈!!!' WHERE b_no = 9;

SELECT * FROM 
	(SELECT rownum as rn,b_no,b_owner,b_when,b_text FROM 
		(SELECT * FROM  board ORDER BY b_when DESC)) 
	 WHERE rn >= 4 and rn <= 6
	 
 SELECT * FROM 
	(SELECT rownum as rn, b_no,b_owner,b_when,b_text FROM  (SELECT * FROM board WHERE
		b_owner='Respina'  ORDER BY b_when DESC)) 
	 WHERE rn >= 1 and rn <= 3
	 
	SELECT * FROM (SELECT DISTINCT(b_no),b_text,b_owner,m_id FROM board,member 
	WHERE m_id like '%123%' or b_text like '%123%')
	
	SELECT COUNT(*) FROM (SELECT DISTINCT(b_no),b_text,b_owner,b_when FROM 
		(SELECT DISTINCT(b_no),b_text,b_owner,m_id,b_when FROM board,member WHERE m_id like '%123%' or b_text like '%123%'))
		
		SELECT COUNT(*) FROM (SELECT DISTINCT(b_no),b_text,b_owner,b_when FROM 
		(SELECT DISTINCT(b_no),b_text,b_owner,m_id,b_when FROM board,member WHERE 
		m_id like '%123%' or b_text like '%123%'))

SELECT rn,b_no,b_owner,b_when,b_text FROM(
SELECT rownum as rn, b_no,b_owner,b_when,b_text FROM(
SELECT DISTINCT(b_no),b_owner,b_when,b_text FROM board,member WHERE
		m_id like '%연%' or b_text like '%연%' ORDER BY b_when DESC) ) WHERE rn >=1 and rn <=3
		
SELECT b_no,b_owner,b_when,b_text FROM 
	(SELECT rownum as rn, b_no,b_owner,b_when,b_text FROM  (SELECT DISTINCT(b_no),b_owner,b_when,b_text FROM board,member WHERE
		m_id like '%pin%' or b_text like '%123%' )
	 WHERE rn <![CDATA[>=]]> #{param1} and rn <![CDATA[<=]]> #{param2} ORDER BY b_when DESC
	 
delete from board where b_no = 1;

=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=

create table board_comment(
	c_no number(5) primary key, 
	c_b_no number(4) not null,
	c_owner varchar2(10 char) not null,
	c_text varchar2(200 char) not null,
	c_when date not null,
	constraint b_comment
		foreign key(c_b_no) references board(b_no)
		on delete cascade
);
create sequence comment_seq;

INSERT INTO board_comment VALUES(comment_seq.nextval,4,'pin','앙녕!',sysdate);

SELECT c_no,c_b_no,c_owner,c_text,c_when FROM board_comment,board WHERE c_b_no = b_no;

SELECT b_no,b_text,b_owner,b_when FROM (
	SELECT DISTINCT(c_no), b_no,b_text,b_owner,b_when FROM board_comment,board WHERE c_owner = 'Respina') 
		WHERE c_b_no =  b_no ORDER BY b_when DESC
		
SELECT * FROM (
SELECT DISTINCT(c_no),b_text,b_owner,b_no,c_owner FROM board,board_comment WHERE c_b_no=b_no) WHERE c_owner='pin'

SELECT DISTINCT(b_no),b_text,b_owner,c_owner FROM (
SELECT DISTINCT(c_no),b_text,b_owner,b_no,c_owner FROM board,board_comment 
	WHERE c_b_no=b_no) WHERE c_owner='Respina'
		
UPDATE board_comment SET c_owner = 'b' WHERE c_no=1;

DELETE FROM board_comment WHERE c_no=1;

SELECT * FROM board_comment;
