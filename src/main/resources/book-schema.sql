drop table if exists book CASCADE;
create table book (
id integer AUTO_INCREMENT,
title varchar(255), 
author varchar(255), 
genre varchar(255), 
checked_Out boolean,
return_Date date,
out_Of_10 integer,
primary key (id)
);