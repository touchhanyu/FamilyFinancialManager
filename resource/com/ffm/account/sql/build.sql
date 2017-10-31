--drop table FFM_ACCOUNT_DAYBOOK cascade constraints;
/*==============================================================*/
/* Table: FFM_ACCOUNT_DAYBOOK                                   */
/*==============================================================*/
create table FFM_ACCOUNT_DAYBOOK 
(
   ID                   VARCHAR2(16)         not null,
   DIRECTION            VARCHAR2(4),
   TYPE                 CHAR(2),
   AMOUNT               NUMBER(20,2),
   PURPOSE              VARCHAR2(20),
   REMARK               VARCHAR2(200),
   TRANDATE             DATE,
   TRANTIME             VARCHAR2(8),
   TRANOPER             INTEGER,
   constraint PK_FFM_ACCOUNT_DAYBOOK primary key (ID)
);

comment on table FFM_ACCOUNT_DAYBOOK is
'流水账';

comment on column FFM_ACCOUNT_DAYBOOK.ID is
'流水号';

comment on column FFM_ACCOUNT_DAYBOOK.DIRECTION is
'0:支入 1:支出';

comment on column FFM_ACCOUNT_DAYBOOK.TYPE is
'01:现金 02:银行卡 03:信用卡';

comment on column FFM_ACCOUNT_DAYBOOK.AMOUNT is
'金额';

comment on column FFM_ACCOUNT_DAYBOOK.PURPOSE is
'用途';

comment on column FFM_ACCOUNT_DAYBOOK.REMARK is
'备注';

comment on column FFM_ACCOUNT_DAYBOOK.TRANDATE is
'交易日期';

comment on column FFM_ACCOUNT_DAYBOOK.TRANTIME is
'交易时间';

comment on column FFM_ACCOUNT_DAYBOOK.TRANOPER is
'交易用户';

--drop table FFM_ACCOUNT_USERBALANCE cascade constraints;
/*==============================================================*/
/* Table: FFM_ACCOUNT_USERBALANCE                               */
/*==============================================================*/
create table FFM_ACCOUNT_USERBALANCE 
(
   ID                   INTEGER              not null,
   TYPE                 CHAR(2),
   WORTH                NUMBER(20,2),
   USERID               INTEGER,
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   OPERID               INTEGER,
   constraint PK_FFM_ACCOUNT_USERBALANCE primary key (ID)
);

comment on table FFM_ACCOUNT_USERBALANCE is
'用户资产表';

comment on column FFM_ACCOUNT_USERBALANCE.ID is
'资产编号';

comment on column FFM_ACCOUNT_USERBALANCE.TYPE is
'01:股票 02:外汇 03:基金';

comment on column FFM_ACCOUNT_USERBALANCE.WORTH is
'资产价值';

comment on column FFM_ACCOUNT_USERBALANCE.USERID is
'资产用户';

comment on column FFM_ACCOUNT_USERBALANCE.MAKEDATE is
'创建日期';

comment on column FFM_ACCOUNT_USERBALANCE.MAKETIME is
'创建时间';

comment on column FFM_ACCOUNT_USERBALANCE.OPERID is
'操作员';

--drop table FFM_ACCOUNT_BALANCEDETAIL cascade constraints;
/*==============================================================*/
/* Table: FFM_ACCOUNT_BALANCEDETAIL                             */
/*==============================================================*/
create table FFM_ACCOUNT_BALANCEDETAIL 
(
   ID                   INTEGER              not null,
   BALANCEID            INTEGER,
   DETAILID             VARCHAR2(8),
   TYPE                 CHAR(1),
   PRICE                NUMBER(10,2),
   NUM                  INTEGER,
   FEE                  NUMBER(10,2),
   AMOUNT               NUMBER(20,2),
   OUTSTANDING          NUMBER(20,2),
   REAMAINNUM           INTEGER,
   STATUS               CHAR(1),
   TRANDATE             DATE,
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   OPERID               INTEGER,
   constraint PK_FFM_ACCOUNT_BALANCEDETAIL primary key (ID)
);

comment on table FFM_ACCOUNT_BALANCEDETAIL is
'用户资产明细表';

comment on column FFM_ACCOUNT_BALANCEDETAIL.ID is
'资产明细编号';

comment on column FFM_ACCOUNT_BALANCEDETAIL.BALANCEID is
'资产编号';

comment on column FFM_ACCOUNT_BALANCEDETAIL.DETAILID is
'资产类型业务表编号';

comment on column FFM_ACCOUNT_BALANCEDETAIL.TYPE is
'1:买入 2:卖出';

comment on column FFM_ACCOUNT_BALANCEDETAIL.PRICE is
'资产单价';

comment on column FFM_ACCOUNT_BALANCEDETAIL.NUM is
'资产数量';

comment on column FFM_ACCOUNT_BALANCEDETAIL.FEE is
'手续费/佣金';

comment on column FFM_ACCOUNT_BALANCEDETAIL.AMOUNT is
'发生金额';

comment on column FFM_ACCOUNT_BALANCEDETAIL.OUTSTANDING is
'剩余金额';

comment on column FFM_ACCOUNT_BALANCEDETAIL.REAMAINNUM is
'剩余数量';

comment on column FFM_ACCOUNT_BALANCEDETAIL.STATUS is
'0:失效 1:生效';

comment on column FFM_ACCOUNT_BALANCEDETAIL.TRANDATE is
'交易日期';

comment on column FFM_ACCOUNT_BALANCEDETAIL.MAKEDATE is
'创建日期';

comment on column FFM_ACCOUNT_BALANCEDETAIL.MAKETIME is
'创建时间';

comment on column FFM_ACCOUNT_BALANCEDETAIL.OPERID is
'操作员';

--drop table FFM_Account_AssetDynamic cascade constraints;
/*==============================================================*/
/* Table: FFM_Account_AssetDynamic                              */
/*==============================================================*/
create table FFM_Account_AssetDynamic 
(
   BALANCEID            INTEGER              not null,
   DETAILID             VARCHAR2(8)          not null,
   TOTAL                NUMBER(20,2),
   NUM                  INTEGER,
   AVERAGEPRICE         NUMBER(10,2),
   constraint PK_FFM_ACCOUNT_ASSETDYNAMIC primary key (BALANCEID, DETAILID)
);

comment on table FFM_Account_AssetDynamic is
'资产价值动态表';

comment on column FFM_Account_AssetDynamic.BALANCEID is
'资产编号';

comment on column FFM_Account_AssetDynamic.DETAILID is
'资产类型业务表编号';

comment on column FFM_Account_AssetDynamic.TOTAL is
'资产价值合计';

comment on column FFM_Account_AssetDynamic.NUM is
'资产数量';

--drop table FFM_ACCOUNT_USERBALANCE cascade constraints;
/*==============================================================*/
/* Table: FFM_ACCOUNT_USERBALANCE                               */
/*==============================================================*/
create table FFM_ACCOUNT_USERBALANCE 
(
   ID                   INTEGER              not null,
   TYPE                 CHAR(2),
   WORTH                NUMBER(20,2),
   USERID               INTEGER,
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   OPERID               INTEGER,
   constraint PK_FFM_ACCOUNT_USERBALANCE primary key (ID)
);

comment on table FFM_ACCOUNT_USERBALANCE is
'用户资产表';

comment on column FFM_ACCOUNT_USERBALANCE.ID is
'资产编号';

comment on column FFM_ACCOUNT_USERBALANCE.TYPE is
'01:现金 02:银行 03:金融机构 10:信贷 33:股票 34:外汇 35:基金 ';

comment on column FFM_ACCOUNT_USERBALANCE.WORTH is
'资产价值';

comment on column FFM_ACCOUNT_USERBALANCE.USERID is
'资产用户';

comment on column FFM_ACCOUNT_USERBALANCE.MAKEDATE is
'创建日期';

comment on column FFM_ACCOUNT_USERBALANCE.MAKETIME is
'创建时间';

comment on column FFM_ACCOUNT_USERBALANCE.OPERID is
'操作员';

--drop table FFM_ACCOUNT_BALANCEBENIFIT cascade constraints;
/*==============================================================*/
/* Table: FFM_ACCOUNT_BALANCEBENIFIT                            */
/*==============================================================*/
create table FFM_ACCOUNT_BALANCEBENIFIT 
(
   ID                   INTEGER              not null,
   BALANCEDETAILID      INTEGER,
   DETAILID             VARCHAR2(8),
   BENIFIT              NUMBER(20,2),
   TRANDATE             DATE,
   constraint PK_FFM_ACCOUNT_BALANCEBENIFIT primary key (ID)
);

comment on table FFM_ACCOUNT_BALANCEBENIFIT is
'资产收益明细表';

comment on column FFM_ACCOUNT_BALANCEBENIFIT.ID is
'资产收益明细编号';

comment on column FFM_ACCOUNT_BALANCEBENIFIT.BALANCEDETAILID is
'资产明细编号';

comment on column FFM_ACCOUNT_BALANCEBENIFIT.DETAILID is
'资产类型业务表编号';

comment on column FFM_ACCOUNT_BALANCEBENIFIT.BENIFIT is
'资产收益';

comment on column FFM_ACCOUNT_BALANCEBENIFIT.TRANDATE is
'交易日期';