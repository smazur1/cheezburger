create table ch_comment (
 comid int primary key,
 appactid int,
 comments varchar(255),
 moddate date
  );

ALTER TABLE ch_comment
ADD FOREIGN KEY (appactid)
REFERENCES ch_application_activity(appactid)
;  


create table ch_drug_screen (
 dsid int primary key,
 appactid int,
 testtype char,
 results char,
 moddate date
  );

ALTER TABLE ch_drug_screen
ADD FOREIGN KEY (appactid)
REFERENCES ch_application_activity(appactid)
;  