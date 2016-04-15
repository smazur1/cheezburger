select aa.actid, ad.actid, ad.depactid from ch_application_activity aa inner join ch_activity_dependency ad
on ad.actid = 10
and ad.depactid = aa.actid
and aa.actstatus = 'I'
inner join ch_application ap
on aa.appid = ap.appid
and ad.job_id = ap.job_id
;

select count(*) from ch_application_activity aa inner join ch_activity_dependency ad
on ad.actid = 6
and ad.depactid = aa.actid
and aa.actstatus = 'I'
inner join ch_application ap
on aa.appid = ap.appid
and ad.job_id = ap.job_id
;

=======================================
select ra.hr_id, ra.actid, ra.raaccess from ch_user u inner join ch_user_role ur
on u.userid = ur.userid
inner join ch_role_activity ra
on ur.hr_id = ra.hr_id
and u.userid = 1
inner join ch_application_activity aa
on ra.actid = aa.actid
;


select ra.hr_id, ra.actid, ra.raaccess from ch_user u inner join ch_user_role ur
on u.userid = ur.userid
inner join ch_role_activity ra
on ur.hr_id = ra.hr_id
and u.userid = 1
and ra.raaccess = '1'
inner join ch_application_activity aa
on ra.actid = aa.actid
;


