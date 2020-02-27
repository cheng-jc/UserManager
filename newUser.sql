use usermanager;
create table if not exists user(
id int unsigned primary key,
username varchar(32) not null,
email varchar(64) not null,
grade tinyint default 1,
password varchar(32) not null
) engine=InnoDB charset=utf8;