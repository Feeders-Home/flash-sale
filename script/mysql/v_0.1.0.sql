use flash_sale;

CREATE TABLE goods
(
id varchar(32),
name varchar (64),
quantity int ,
desc varchar (255)
);

INSERT INTO `goods` (`id`, `name`, `quantity`, `desc`) VALUES('1','iPhone8','100',"very cheap");