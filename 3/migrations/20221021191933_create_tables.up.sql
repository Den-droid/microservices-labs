create table users
(
	id int not null auto_increment,
	name varchar(50) not null,
	address varchar(50) not null,
	primary key(id)
);

create table food
(
	id int not null auto_increment,
	name varchar(50) not null,
	price float not null,
	primary key(id)
);