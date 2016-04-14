************************************************
rem script for building cheezburger tables.

****************************************************
drop table ch_user_role;
drop table ch_role_activity;
drop table ch_activity_dependency;
drop table ch_application_activity;
drop table ch_application;
drop table ch_jobactivity;


create table ch_user (
userid int primary key,
username varchar (30),
password varchar (30)
);

create table ch_user_role (
userroleid int primary key,
userid int,
hr_id int
);

create table ch_activity (
actid int primary key,
actcode varchar(10),
actdescription varchar(50)
);


create table ch_role_activity (
roleactid int primary key,
hr_id int,
actid int,
raaccess char 
);



create table ch_activity_dependency (
 actdepid int primary key,
 job_id int,
 actid int,
 depactid int
 );
 
 
 
 create table ch_application_activity (
 appactid int primary key,
 appid int,
 actid int,
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
 job_id int,
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
job_id int,
actid int
);

ALTER TABLE ch_user_role
ADD FOREIGN KEY (userid)
REFERENCES ch_user(userid)
;

alter table ch_hrrole
add unique (role_code);

ALTER TABLE ch_user_role
ADD FOREIGN KEY (hr_id)
REFERENCES ch_hrrole(hr_id)
;

alter table ch_activity
add unique (actcode);

alter table ch_jobtype
add unique (job_code);

alter table ch_jobactivity
add foreign key (job_id)
references ch_jobtype(job_id);

alter table ch_jobactivity
add foreign key (actid)
references ch_activity (actid);

alter table ch_application
add foreign key (job_id)
references ch_jobtype (job_id);

alter table ch_role_activity
add foreign key (hr_id)
references ch_hrrole (hr_id);

alter table ch_role_activity
add foreign key (actid)
references ch_activity (actid);

alter table ch_application_activity
add foreign key (appid)
references ch_application (appid);

alter table ch_application_activity
add foreign key (actid)
references ch_activity (actid);

alter table ch_activity_dependency
add foreign key (job_id)
references ch_jobtype (job_id);

alter table ch_activity_dependency
add foreign key (actid)
references ch_activity (actid);

alter table ch_activity_dependency
add foreign key (depactid)
references ch_activity (actid);

rem end ddl for CheezburgerHR
*********************************************************************************


