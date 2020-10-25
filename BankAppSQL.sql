create database BankAppSQL;
use BankAppSQL;

create table BankUser(
FirstName varchar(50),
LastName varchar(50),
UserName varchar(250) Primary Key,
Password varchar(100)
);

select * from BankUser;
truncate BankUser;
drop table BankUser;

create table BankSecurity(
UserName varchar(250),
UserQuestion varchar(250),
UserAnswer varchar(250),
FOREIGN KEY(UserName) REFERENCES BankUser(UserName)
);

select * from BankSecurity;
truncate BankSecurity;
drop table BankSecurity;

create table BankAccounts(
UserName varchar(250),
Savings double,
Checkings double,
FOREIGN KEY(UserName) REFERENCES BankUser(UserName)
);


select * from BankAccounts;
truncate BankAccounts;
drop table BankAccounts;
