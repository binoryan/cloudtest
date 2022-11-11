
/*FM Users: */
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('loadtest10001','loadtest10002',
'loadtest10003','loadtest10004','loadtest10005','loadtest10006','loadtest10007','loadtest10008','loadtest10009',
'loadtest10010','loadtest10011','loadtest10012','loadtest10013','loadtest10014','loadtest10015','loadtest10016',
'loadtest10017','loadtest10018','loadtest10019','loadtest10020','loadtest10021','loadtest10022','loadtest10023',
'loadtest10024','loadtest10025','loadtest10026','loadtest10027','loadtest10028','loadtest10029','loadtest10030',
'loadtest10031','loadtest10032','loadtest10033','loadtest10034','loadtest10035','loadtest10036','loadtest10037',
'loadtest10038','loadtest10039','loadtest10040','loadtest10041','loadtest10042','loadtest10043','loadtest10044',
'loadtest10045','loadtest10046','loadtest10047','loadtest10048','loadtest10049','loadtest10050','loadtest10051',
'loadtest10052','loadtest10053','loadtest10054','loadtest10055','loadtest10056','loadtest10057','loadtest10058',
'loadtest10059','loadtest10060','loadtest10061','loadtest10062','loadtest10063','loadtest10064','loadtest10065',
'loadtest10066','loadtest10067','loadtest10068','loadtest10069','loadtest10070','loadtest10071','loadtest10072',
'loadtest10073','loadtest10074','loadtest10075','loadtest10076','loadtest10077','loadtest10078','loadtest10079',
'loadtest10080','loadtest10081','loadtest10082','loadtest10083','loadtest10084','loadtest10085','loadtest10086',
'loadtest10087','loadtest10088','loadtest10089','loadtest10090');

/*OBO: */
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('philip.theimer@hdsupply.com','shaun.dowell@hdsupply.com','carole.presson@hdsupply.com','joshua.stroud2@hdsupply.com','john.hart3@hdsupply.com',
'james.bula@hdsupply.com','leon.palo@hdsupply.com','ronald.parks@hdsupply.com','kelly.gavin-heimerl@hdsupply.com','kresha.morgan@hdsupply.com',
'julie.goldman@hdsupply.com','juan.alonso@hdsupply.com','johnny.ortiz2@hdsupply.com','john.schuldes@hdsupply.com','james.deruiter@hdsupply.com',
'joseph.wittgens@hdsupply.com','matthew.rubio@hdsupply.com','todd.gunnels@hdsupply.com','thomas.berard@hdsupply.com','thomas.blackmore@hdsupply.com',
'jeffrey.thomas@hdsupply.com','james.baranska@hdsupply.com','joseph.marshall@hdsupply.com','james.elmore@hdsupply.com','ginger.mangum@hdsupply.com',
'walter.tappe@hdsupply.com','jeffrey.wilson@hdsupply.com','jeffrey.renna@hdsupply.com','tracy.maddix@hdsupply.com','tonya.hansen@hdsupply.com',
'mishonna.perry@hdsupply.com','anthony.brooks@hdsupply.com','mary.fredericks@hdsupply.com','paul.buchman@hdsupply.com','philip.theimer@hdsupply.com',
'shaun.dowell@hdsupply.com','carole.presson@hdsupply.com','joshua.stroud2@hdsupply.com','john.hart3@hdsupply.com','james.bula@hdsupply.com','leon.palo@hdsupply.com',
'ronald.parks@hdsupply.com');

/*Super OBO: */
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('alma.gaytan@hdsupply.com','brandy.klabunde@hdsupply.com','elisa.gonzalez@hdsupply.com','evelyn.kohn@hdsupply.com','christine.mcdonald@hdsupply.com',
'dann.dominguez@hdsupply.com','gerard.sampson@hdsupply.com','cynthia.lemmon@hdsupply.com','nicole.rutter@hdsupply.com','mark.mccoy@hdsupply.com',
'nancy.phillips@hdsupply.com','miguel.gonzales@hdsupply.com','martin.espinoza2@hdsupply.com');

/*Avendra Users: */
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('brandyburke','shortie','sanjhotels');

/*Suma Users:*/
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('avpatel1953','dpboos','jacksonjr','mvalentine','unclepook','steve.dadson','mspavalon','drnold24','redroof662','kbrophy');

/*EWallet OBO Users:*/
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('tomaria.goodwin@hdsupply.com','eric.micheletti@hdsupply.com','raymond.montanez@hdsupply.com','scott.black@hdsupply.com','carrie.rigdon@hdsupply.com',
'monique.huddleston@hdsupply.com','alan.ademis@hdsupply.com','tara.melvin@hdsupply.com','christopher.martin@hdsupply.com','latanya.daniels@hdsupply.com',
'cynthia.valentino@hdsupply.com','richard.jones@hdsupply.com','sonya.godette@hdsupply.com','david.leite@hdsupply.com','john.hart3@hdsupply.com','kevin.mcbride@hdsupply.com');

/*EWallet SOBO Users: */
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('chandrasekhar.geddam@hdsupply.com');

/*Non EWallet users: */
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in ('carole.presson@hdsupply.com','joshua.stroud2@hdsupply.com','leon.palo@hdsupply.com','kelly.gavin-heimerl@hdsupply.com','matthew.rubio@hdsupply.com',
'julie.goldman@hdsupply.com','juan.alonso@hdsupply.com','johnny.ortiz2@hdsupply.com','john.schuldes@hdsupply.com','james.deruiter@hdsupply.com', 'joseph.wittgens@hdsupply.com');

/*BMS Users: */
update userreg set (logonpassword, salt) = (select logonpassword, salt from userreg where logonid = 'prddevops1') where
logonid in (update userreg set status = 1 where logonid in ('ccampbell'.'thetatcat','tylerj');


