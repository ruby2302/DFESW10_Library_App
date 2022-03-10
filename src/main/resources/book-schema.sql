drop table if exists book CASCADE;
create table book (
id integer AUTO_INCREMENT,
title varchar(255), 
author varchar(255), 
genre varchar(255), 
checked_Out boolean,
return_Date varchar(255),
rating varchar(255),
primary key (id)
);