create table question
(
	id int auto_increment,
	description text,
	gmt_create BIGINT,
	gmt_modified bigint,
	title varchar(50),
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256),
	constraint question_pk
		primary key (id)
);

