drop table FI_CURRENCY_TYPE cascade constraints;

/*==============================================================*/
/* Table: FI_CURRENCY_TYPE                                      */
/*==============================================================*/
create table FI_CURRENCY_TYPE 
(
   ID                   INTEGER              not null,
   NAME                 VARCHAR2(40),
   NAME_ENG             VARCHAR2(4),
   SYMBOL               VARCHAR2(4),
   NATIONAL             INTEGER,
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   constraint PK_FI_CURRENCY_TYPE primary key (ID)
);

comment on column FI_CURRENCY_TYPE.ID is
'货币编号';

comment on column FI_CURRENCY_TYPE.NAME is
'货币名称';

comment on column FI_CURRENCY_TYPE.NAME_ENG is
'货币英文简称';

comment on column FI_CURRENCY_TYPE.SYMBOL is
'货币符号';

comment on column FI_CURRENCY_TYPE.NATIONAL is
'国家编号';

comment on column FI_CURRENCY_TYPE.MAKEDATE is
'创建日期';

comment on column FI_CURRENCY_TYPE.MAKETIME is
'创建时间';

drop table FI_CURRENCY_PRICE cascade constraints;

/*==============================================================*/
/* Table: FI_CURRENCY_PRICE                                     */
/*==============================================================*/
create table FI_CURRENCY_PRICE 
(
   ID                   INTEGER              not null,
   CURRENCYID           INTEGER,
   NAME                 VARCHAR2(40),
   FBUYFRI              NUMBER(20,4),
   MBUYPRI              NUMBER(20,4),
   FSELLPRI             NUMBER(20,4),
   MSELLPRI             NUMBER(20,4),
   BANKCONVERSIONPRI    NUMBER(20,4),
   PUBLICDATE           DATE,
   PUBLICTIME           VARCHAR2(8),
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   constraint PK_FI_CURRENCY_PRICE primary key (ID)
);

comment on column FI_CURRENCY_PRICE.ID is
'汇率明细编号';

comment on column FI_CURRENCY_PRICE.CURRENCYID is
'货币编号';

comment on column FI_CURRENCY_PRICE.NAME is
'货币名称';

comment on column FI_CURRENCY_PRICE.FBUYFRI is
'现汇买入价';

comment on column FI_CURRENCY_PRICE.MBUYPRI is
'现钞买入价';

comment on column FI_CURRENCY_PRICE.FSELLPRI is
'现汇卖出价';

comment on column FI_CURRENCY_PRICE.MSELLPRI is
'现钞卖出价';

comment on column FI_CURRENCY_PRICE.BANKCONVERSIONPRI is
'银行折算价/中间价';

comment on column FI_CURRENCY_PRICE.PUBLICDATE is
'发布日期';

comment on column FI_CURRENCY_PRICE.PUBLICTIME is
'发布时间';

comment on column FI_CURRENCY_PRICE.MAKEDATE is
'创建日期';

comment on column FI_CURRENCY_PRICE.MAKETIME is
'创建时间';