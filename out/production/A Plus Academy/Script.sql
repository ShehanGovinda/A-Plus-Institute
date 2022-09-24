DROP DATABASE IF EXISTS `APlus`;
CREATE DATABASE IF NOT EXISTS `APlus`;
SHOW DATABASES ;
USE `APlus`;
#-------------------

DROP TABLE IF EXISTS `Register`;
CREATE TABLE IF NOT EXISTS `Register`(
	FirstName VARCHAR(15),
	LastName VARCHAR(15),
	Nic VARCHAR(15),
	telephone VARCHAR(15),
	userType VARCHAR (10),
	username VARCHAR(10),
	password VARCHAR(10)
	);
SHOW TABLES ;
DESCRIBE Register;
#---------------------
DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
	sid VARCHAR(12) NOT NULL,
	snamefname VARCHAR(12),
	lastname VARCHAR(12),
	dateofbirth VARCHAR(12),
	gender VARCHAR(6),
	grade VARCHAR(20),
	parentname VARCHAR(50),
	contact VARCHAR(12),
	address VARCHAR (15),
    email VARCHAR(50),
    registationfee VARCHAR(10),
	CONSTRAINT PRIMARY KEY (sID)
	);
SHOW TABLES ;
DESCRIBE Student;
#---------------------

DROP TABLE IF EXISTS `Employee`;
CREATE TABLE IF NOT EXISTS `Employee`(
	EmpId VARCHAR (15) PRIMARY KEY NOT NULL,
	FirstName VARCHAR(10),
	LastName VARCHAR(10),
	Nic VARCHAR(15) ,
    contact VARCHAR(15),
	email VARCHAR(50),
    address VARCHAR(50),
    salary VARCHAR (50)
	);
SHOW TABLES ;
DESCRIBE `Employee`;

select * from Employee;
#---------------------

DROP TABLE IF EXISTS Subject;
CREATE TABLE IF NOT EXISTS Subject(
    subjectName VARCHAR (55) NOT NULL
);
SHOW TABLES;
DESCRIBE Subject;

#------------------------------

DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(
	TeacherId VARCHAR (15) NOT NULL,
	Teacherfirstname VARCHAR (15),
	Teacherlastname VARCHAR (15),
	TeacherNic VARCHAR (20),
	Teachercontact VARCHAR (15),
	Teacheraddress VARCHAR (50),
	TeacherEmail VARCHAR (50),
	subjectName VARCHAR (20),
	fee VARCHAR (10),
	CONSTRAINT PRIMARY KEY (TeacherId)

);
SHOW TABLES ;
DESCRIBE Teacher;
#---------------------
DROP TABLE IF EXISTS `Subjects`;
CREATE TABLE IF NOT EXISTS `Subjects`(
	sid VARCHAR (15),
	lastname VARCHAR(12),
	gender VARCHAR(6),
	grade VARCHAR(20),
	subjectName VARCHAR (55) ,
	TeacherName VARCHAR (25)
	);
SHOW TABLES ;
DESCRIBE `Subjects`;
#---------------------------------



DROP TABLE IF EXISTS payment;
CREATE TABLE IF NOT EXISTS payment(
    sid VARCHAR (15) NOT NULL ,
    cardNumber VARCHAR (55),
    subject VARCHAR (55) NOT NULL ,
    teacherlastname VARCHAR (55) NOT NULL ,
    paymentMonth VARCHAR (55),
    amount DOUBLE ,
    pDate DATE ,
    pTime TIME
);
SHOW TABLES;
DESC payment;
#---------------------



DROP TABLE IF EXISTS Schedule;
CREATE TABLE IF NOT EXISTS Schedule(
	TeacherId VARCHAR (15),
	Teacherfirstname VARCHAR (15),
	Teacherlastname VARCHAR (15),
    SubjectName VARCHAR (55),
    Sdate DATE,
    Stime TIME
);

SHOW TABLES ;
DESCRIBE Schedule;;
#---------------------

DROP TABLE IF EXISTS Mark;
CREATE TABLE IF NOT EXISTS Mark(
	sid VARCHAR (15) NOT NULL,
	lastname VARCHAR(12),
	SubjectName VARCHAR (55),
	IssuedDate DATE,
	Result VARCHAR (10),
    examName VARCHAR (55),
    examMonth VARCHAR (55),
    marks VARCHAR (55)
);
SHOW TABLES ;
DESCRIBE Mark;
#---------------------


DROP TABLE IF EXISTS Attendence;
CREATE TABLE IF NOT EXISTS Attendence(
	sid VARCHAR (10) NOT NULL,
	lastname VARCHAR(12),
   	Attendance VARCHAR (10),
    SubjectName VARCHAR (55),
    teacherName VARCHAR (55),
    Grade VARCHAR (20),
	Atime TIME,
	Adate DATE
);
SHOW TABLES ;
DESCRIBE Attendence;
#---------------------

DROP TABLE IF EXISTS `Employ Salary`;
CREATE TABLE IF NOT EXISTS `Employ Salary`(
	EmpId VARCHAR (15),
	FirstName VARCHAR(10),
	LastName VARCHAR(10),
	Emonthamount  VARCHAR (15),
	EDate DATE,
	ETime TIME,
	Amount DECIMAL (20,2)
);

SHOW TABLES ;
DESCRIBE `Employ Salary`;
#---------------------

DROP TABLE IF EXISTS `Teacher Payment`;
CREATE TABLE IF NOT EXISTS `Teacher Payment`(
	Teacherfirstname VARCHAR (15),
	Teacherlastname VARCHAR (15),
	Tamount;
	TDate DATE,
	TTime TIME,
	TeacherId VARCHAR (15) NOT NULL
	);

SHOW TABLES ;
DESCRIBE `Teacher Payment`;
#---------------------


