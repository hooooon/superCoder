select * from husers;

select * from board;

create table husers(
	seq			number(5)		primary key,
	name		varchar2(20)	not null,
	password	varchar2(20)	not null,
	email		varchar2(50)	not null,
	gender		number(1)		not null,
	age			number(3)		default 0
)

delete husers where seq=13;



