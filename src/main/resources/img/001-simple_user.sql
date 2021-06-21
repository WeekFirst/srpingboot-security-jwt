create table simple_user
(
	id int auto_increment
		primary key,
	user_name varchar(50) not null,
	user_password varchar(255) not null,
	user_authorities varchar(255) default 'normal' not null,
	user_status tinyint default 0 not null,
	create_time datetime not null,
	modified_time datetime not null,
	user_nick varchar(255) default '游客' not null
)
comment 'user table';

