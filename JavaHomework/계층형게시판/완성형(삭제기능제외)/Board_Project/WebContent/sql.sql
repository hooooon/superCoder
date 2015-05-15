create table ausers(
	id 			varchar2(28) 	primary key,
	password 	varchar2(8)		not null,
	name 		varchar2(30)	not null,
	role 		varchar2(5)		default 'User'
)

create table aboard(
	seq			number(5) 		primary key,
	family		number(5)		default 0,
	group_seq	number(5)		default 0,
	depth		number(5)		default 0,
	title		varchar2(200)	not null,
	nickname	varchar2(30)	not null,
	content		varchar2(2000)	not null,
	regdate		date 			default sysdate,
	cnt			number(5)		default 0,
	userid		varchar2(8),
	constraint aboard_userid_fk foreign key(userid) references ausers(id)
)

alter table aboard add(family varchar2(5) default 0); 
alter table aboard rename column parent to group_seq; 

insert into ausers values('admin', 'admin', '관리자', 'Admin');
insert into ausers values('abc', 'abc', '김씨', 'Admin');
insert into ausers values('test', 'test', '이씨', 'User');
insert into ausers values('123', '123', '박씨', 'User');


update aboard set group_seq=group_seq+1 where family = (select family from aboard where seq=4) and group_seq > (select group_seq from aboard where seq=4); 

insert into aboard(seq, family, group_seq, depth, title, nickname, content, regdate, userid) 
values( (select nvl(max(seq), 0)+1 from aboard), (select family from aboard where seq=4), 
(select group_seq+1 from aboard where seq=4), (select depth+1 from aboard where seq=4),'asdf', 'asdf', 'asdf.', sysdate, 'admin');


update aboard set title='첫 번째 글에 대한 답글2에 대한 답글' where seq=5;
update aboard set group_seq=3 where seq=2;

select * from aboard order by TO_NUMBER(family) desc, group_seq;

select * from aboard;

update aboard set group_seq=group_seq-1 where family = (select family from aboard where seq=3) and group_seq > 3; 

delete aboard where seq=9;