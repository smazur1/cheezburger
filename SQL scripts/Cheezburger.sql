************************************************
rem script for building cheezburger tables.

****************************************************
create table ch_user (
userid int primary key,
username varchar (30),
password varchar (30)
);

create table ch_user_role (
userroleid int primary key,
userid int,
rolecode varchar (10)
);

create table ch_activity (
actid int primary key,
actcode varchar(10),
actdescription varchar(50)
);


create table ch_role_activity (
roleactid int primary key,
rolecode varchar (10),
actcode varchar(10),
raaccess char 
);

create table ch_activity_dependency (
 actdepid int primary key,
 jobcode varchar(10),
 actcode varchar(10),
 depactcode varchar (10)
 );
 
 
 create table ch_application_activity (
 appactid int primary key,
 appid int,
 actcode varchar(10),
 actstatus char,
 actmoddate date
  );
  
 create table ch_application (
 appid int primary key,
 name varchar (50),
 address varchar(100),
 birthday varchar (10),
 education varchar(255),
 jobhistory varchar(255),
 appref varchar(255),
 jobcode varchar(10),
 druguse varchar(50),
 veteran varchar(50),
 citizen varchar(20),
 appstatus char,
 createdate date,
 moddate date
 ); 
 
 
 create table CH_HRROLE (
hr_id int primary key,
role_code varchar(10),
role_description varchar(50)
);
  
create table CH_JOBTYPE (
job_id int primary key,
job_code varchar(10),
job_description varchar(50)
);  

create table CH_JOBACTIVITY (
jobact_id int primary key,
job_code varchar(10),
act_code varchar(10)
);

ALTER TABLE ch_user_role
ADD FOREIGN KEY (userid)
REFERENCES ch_user(userid)
;

alter table ch_hrrole
add unique (role_code);

ALTER TABLE ch_user_role
ADD FOREIGN KEY (rolecode)
REFERENCES ch_hrrole(role_code)
;

alter table ch_activity
add unique (actcode);

alter table ch_jobtype
add unique (job_code);

alter table ch_jobactivity
add foreign key (job_code)
references ch_jobtype(job_code);

alter table ch_jobactivity
add foreign key (act_code)
references ch_activity (actcode);

alter table ch_application
add foreign key (jobcode)
references ch_jobtype (job_code);

alter table ch_role_activity
add foreign key (rolecode)
references ch_hrrole (role_code);

alter table ch_role_activity
add foreign key (actcode)
references ch_activity (actcode);

alter table ch_application_activity
add foreign key (appid)
references ch_application (appid);

alter table ch_application_activity
add foreign key (actcode)
references ch_activity (actcode);

alter table ch_activity_dependency
add foreign key (jobcode)
references ch_jobtype (job_code);

alter table ch_activity_dependency
add foreign key (actcode)
references ch_activity (actcode);

alter table ch_activity_dependency
add foreign key (depactcode)
references ch_activity (actcode);

rem end ddl for CheezburgerHR
*********************************************************************************


