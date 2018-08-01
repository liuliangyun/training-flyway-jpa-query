create table mall_demo.commodities (

  id bigint(20) primary key,

  name varchar(100) not null,

  price decimal(10,2) not null,

  type varchar(100) not null,

  band varchar(100) not null,

  description varchar(100) not null,

  date varchar(100) not null,

  location varchar(100) not null

) charset utf8;
