create table endereco(
	id bigint auto_increment primary key,
    streetName Varchar(50) not Null,
    numbe varchar(15) not null,
    complement Varchar(50),
    neighourhood varchar(50) not null,
    city varchar(30) not null,
    state varchar(30),
    country varchar(50) not null,
    zipcode varchar(50) not null,
    latitude decimal(10, 8),
    longitude decimal(11, 8) 
    

) engine=InnoDB default CHARSET=utf8;

INSERT INTO endereco(streetName,numbe,complement,neighourhood,city,state,country,zipcode,latitude,longitude) 
	values ('Ferreira','12','Sem complemento','Sapupara','Maranguape','Ceara','brazil','6195000',40.7172740,-74.00898606);