select * from FFM_STOCK_COMPANY order by id desc;

select * from FFM_STOCK_STOCK order by GID;

select count(*) from FFM_STOCK_STOCKHISTROY where SERIALNO like '160314%';

insert into FI_STOCK_STOCKHISTROY
select '160202'||GID,GID,NAME,TODAYSTARTPRI,YESTODENDPRI,NOWPRI,TODAYMAX,TODAYMIN,COMPETITIVEPRI,RESERVEPRI,TRANUMBER,TRAAMOUNT,BUY1,BUY1PRI,BUY2,BUY2PRI,BUY3,BUY3PRI,BUY4,BUY4PRI,BUY5,BUY5PRI,SELL1,SELL1PRI,SELL2,SELL2PRI,SELL3,SELL3PRI,SELL4,SELL4PRI,SELL5,SELL5PRI,PUBLICDATE,PUBLICTIME,MAKEDATE,MAKETIME from FI_STOCK_STOCK;
select * from FFM_SYS_USER;
select * from FI_COM_SYSTASK;

select * from FFM_ACCOUNT_USERBALANCE;
select * from FFM_ACCOUNT_BALANCEDETAIL;
select * from FFM_Account_AssetDynamic;

select GID,NAME,NOWPRI*TOTALNUM from
(select DETAILID,sum(NUM) TOTALNUM from FFM_ACCOUNT_BALANCEDETAIL where BALANCEID in
(select ID from FFM_ACCOUNT_USERBALANCE where TYPE='33' and USERID=2) group by DETAILID)detail left join 
FFM_STOCK_STOCK stock on detail.DETAILID=stock.GTYPE||stock.GID

select TYPE,sum(NUM) from FFM_ACCOUNT_BALANCEDETAIL where BALANCEID= and DETAILID='' group by TYPE
select * from ffm_sys_user
delete FFM_ACCOUNT_balancedetail
select * from FFM_ACCOUNT_USERBALANCE

select sum(avgpri)/count(*) from 
(select gid,name,TODAYMAX,TODAYmin,nowpri,tranumber,traamount,
case when tranumber<>0 then traamount/tranumber else nowpri end avgpri,publicdate 
from ffm_stock_stockhistory where gid='000049' --and publicdate>to_date('2016-04-04','yyyy-MM-dd')
order by publicdate desc);