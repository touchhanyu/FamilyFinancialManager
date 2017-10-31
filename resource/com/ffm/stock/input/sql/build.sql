--drop table FFM_STOCK_COMPANY cascade constraints;
/*==============================================================*/
/* Table: FFM_STOCK_COMPANY                                     */
/*==============================================================*/
create table FFM_STOCK_COMPANY 
(
   ID                   INTEGER              not null,
   NAME                 VARCHAR2(50),
   PINYIN               VARCHAR2(60),
   LEGALNAME            VARCHAR2(40),
   ORGID                VARCHAR2(20),
   GID                  VARCHAR2(8),
   GNAME                VARCHAR2(16),
   GTYPE                CHAR(2),
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   constraint PK_FFM_STOCK_COMPANY primary key (ID)
);

comment on table FFM_STOCK_COMPANY is
'公司信息表';

comment on column FFM_STOCK_COMPANY.ID is
'公司编号';

comment on column FFM_STOCK_COMPANY.NAME is
'公司名称';

comment on column FFM_STOCK_COMPANY.PINYIN is
'公司拼音';

comment on column FFM_STOCK_COMPANY.LEGALNAME is
'法人';

comment on column FFM_STOCK_COMPANY.ORGID is
'组织机构代码';

comment on column FFM_STOCK_COMPANY.GID is
'股票代码';

comment on column FFM_STOCK_COMPANY.MAKEDATE is
'创建日期';

comment on column FFM_STOCK_COMPANY.MAKETIME is
'创建时间';

--drop table FFM_STOCK_STOCK cascade constraints;
/*==============================================================*/
/* Table: FFM_STOCK_STOCK                                       */
/*==============================================================*/
create table FFM_STOCK_STOCK 
(
   GTYPE                CHAR(2)              not null,
   GID                  VARCHAR2(8)          not null,
   NAME                 VARCHAR2(16),
   TODAYSTARTPRI        NUMBER(10,2),
   YESTODENDPRI         NUMBER(10,2),
   NOWPRI               NUMBER(10,2),
   TODAYMAX             NUMBER(10,2),
   TODAYMIN             NUMBER(10,2),
   COMPETITIVEPRI       NUMBER(10,2),
   RESERVEPRI           NUMBER(10,2),
   TRANUMBER            INTEGER,
   TRAAMOUNT            NUMBER(20,2),
   BUY1                 INTEGER,
   BUY1PRI              NUMBER(10,2),
   BUY2                 INTEGER,
   BUY2PRI              NUMBER(10,2),
   BUY3                 INTEGER,
   BUY3PRI              NUMBER(10,2),
   BUY4                 INTEGER,
   BUY4PRI              NUMBER(10,2),
   BUY5                 INTEGER,
   BUY5PRI              NUMBER(10,2),
   SELL1                INTEGER,
   SELL1PRI             NUMBER(10,2),
   SELL2                INTEGER,
   SELL2PRI             NUMBER(10,2),
   SELL3                INTEGER,
   SELL3PRI             NUMBER(10,2),
   SELL4                INTEGER,
   SELL4PRI             NUMBER(10,2),
   SELL5                INTEGER,
   SELL5PRI             NUMBER(10,2),
   PUBLICDATE           DATE,
   PUBLICTIME           VARCHAR2(8),
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   constraint PK_FFM_STOCK_STOCK primary key (GID, GTYPE)
);

comment on table FFM_STOCK_STOCK is
'股票最新信息表';

comment on column FFM_STOCK_STOCK.GID is
'股票编号';

comment on column FFM_STOCK_STOCK.NAME is
'股票名称';

comment on column FFM_STOCK_STOCK.TODAYSTARTPRI is
'今日开盘价';

comment on column FFM_STOCK_STOCK.YESTODENDPRI is
'昨日收盘价';

comment on column FFM_STOCK_STOCK.NOWPRI is
'当前价格';

comment on column FFM_STOCK_STOCK.TODAYMAX is
'今日最高价';

comment on column FFM_STOCK_STOCK.TODAYMIN is
'今日最低价';

comment on column FFM_STOCK_STOCK.COMPETITIVEPRI is
'竞买价';

comment on column FFM_STOCK_STOCK.RESERVEPRI is
'竞卖价';

comment on column FFM_STOCK_STOCK.TRANUMBER is
'成交量';

comment on column FFM_STOCK_STOCK.TRAAMOUNT is
'成交金额';

comment on column FFM_STOCK_STOCK.BUY1 is
'买一';

comment on column FFM_STOCK_STOCK.BUY1PRI is
'买一报价';

comment on column FFM_STOCK_STOCK.BUY2 is
'买二';

comment on column FFM_STOCK_STOCK.BUY2PRI is
'买二报价';

comment on column FFM_STOCK_STOCK.BUY3 is
'买三';

comment on column FFM_STOCK_STOCK.BUY3PRI is
'买三报价';

comment on column FFM_STOCK_STOCK.BUY4 is
'买四';

comment on column FFM_STOCK_STOCK.BUY4PRI is
'买四报价';

comment on column FFM_STOCK_STOCK.BUY5 is
'买五';

comment on column FFM_STOCK_STOCK.BUY5PRI is
'买五报价';

comment on column FFM_STOCK_STOCK.SELL1 is
'卖一';

comment on column FFM_STOCK_STOCK.SELL1PRI is
'卖一报价';

comment on column FFM_STOCK_STOCK.SELL2 is
'卖二';

comment on column FFM_STOCK_STOCK.SELL2PRI is
'卖二报价';

comment on column FFM_STOCK_STOCK.SELL3 is
'卖三';

comment on column FFM_STOCK_STOCK.SELL3PRI is
'卖三报价';

comment on column FFM_STOCK_STOCK.SELL4 is
'卖四';

comment on column FFM_STOCK_STOCK.SELL4PRI is
'卖四报价';

comment on column FFM_STOCK_STOCK.SELL5 is
'卖五';

--drop table FFM_STOCK_STOCKHISTORY cascade constraints;
/*==============================================================*/
/* Table: FFM_STOCK_STOCKHISTORY                                */
/*==============================================================*/
create table FFM_STOCK_STOCKHISTORY 
(
   SERIALNO             VARCHAR2(16)         not null,
   GTYPE                CHAR(2)              not null,
   GID                  VARCHAR2(8),
   NAME                 VARCHAR2(16),
   TODAYSTARTPRI        NUMBER(10,2),
   YESTODENDPRI         NUMBER(10,2),
   NOWPRI               NUMBER(10,2),
   TODAYMAX             NUMBER(10,2),
   TODAYMIN             NUMBER(10,2),
   COMPETITIVEPRI       NUMBER(10,2),
   RESERVEPRI           NUMBER(10,2),
   TRANUMBER            INTEGER,
   TRAAMOUNT            NUMBER(20,2),
   BUY1                 INTEGER,
   BUY1PRI              NUMBER(10,2),
   BUY2                 INTEGER,
   BUY2PRI              NUMBER(10,2),
   BUY3                 INTEGER,
   BUY3PRI              NUMBER(10,2),
   BUY4                 INTEGER,
   BUY4PRI              NUMBER(10,2),
   BUY5                 INTEGER,
   BUY5PRI              NUMBER(10,2),
   SELL1                INTEGER,
   SELL1PRI             NUMBER(10,2),
   SELL2                INTEGER,
   SELL2PRI             NUMBER(10,2),
   SELL3                INTEGER,
   SELL3PRI             NUMBER(10,2),
   SELL4                INTEGER,
   SELL4PRI             NUMBER(10,2),
   SELL5                INTEGER,
   SELL5PRI             NUMBER(10,2),
   PUBLICDATE           DATE,
   PUBLICTIME           VARCHAR2(8),
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   constraint PK_FFM_STOCK_STOCKHISTORY primary key (SERIALNO, GTYPE)
);

comment on table FFM_STOCK_STOCKHISTORY is
'股票历史信息表';

comment on column FFM_STOCK_STOCKHISTORY.SERIALNO is
'流水号';

comment on column FFM_STOCK_STOCKHISTORY.GID is
'股票编号';

comment on column FFM_STOCK_STOCKHISTORY.NAME is
'股票名称';

comment on column FFM_STOCK_STOCKHISTORY.TODAYSTARTPRI is
'今日开盘价';

comment on column FFM_STOCK_STOCKHISTORY.YESTODENDPRI is
'昨日收盘价';

comment on column FFM_STOCK_STOCKHISTORY.NOWPRI is
'当前价格';

comment on column FFM_STOCK_STOCKHISTORY.TODAYMAX is
'今日最高价';

comment on column FFM_STOCK_STOCKHISTORY.TODAYMIN is
'今日最低价';

comment on column FFM_STOCK_STOCKHISTORY.COMPETITIVEPRI is
'竞买价';

comment on column FFM_STOCK_STOCKHISTORY.RESERVEPRI is
'竞卖价';

comment on column FFM_STOCK_STOCKHISTORY.TRANUMBER is
'成交量';

comment on column FFM_STOCK_STOCKHISTORY.TRAAMOUNT is
'成交金额';

comment on column FFM_STOCK_STOCKHISTORY.BUY1 is
'买一';

comment on column FFM_STOCK_STOCKHISTORY.BUY1PRI is
'买一报价';

comment on column FFM_STOCK_STOCKHISTORY.BUY2 is
'买二';

comment on column FFM_STOCK_STOCKHISTORY.BUY2PRI is
'买二报价';

comment on column FFM_STOCK_STOCKHISTORY.BUY3 is
'买三';

comment on column FFM_STOCK_STOCKHISTORY.BUY3PRI is
'买三报价';

comment on column FFM_STOCK_STOCKHISTORY.BUY4 is
'买四';

comment on column FFM_STOCK_STOCKHISTORY.BUY4PRI is
'买四报价';

comment on column FFM_STOCK_STOCKHISTORY.BUY5 is
'买五';

comment on column FFM_STOCK_STOCKHISTORY.BUY5PRI is
'买五报价';

comment on column FFM_STOCK_STOCKHISTORY.SELL1 is
'卖一';

comment on column FFM_STOCK_STOCKHISTORY.SELL1PRI is
'卖一报价';

comment on column FFM_STOCK_STOCKHISTORY.SELL2 is
'卖二';

comment on column FFM_STOCK_STOCKHISTORY.SELL2PRI is
'卖二报价';

comment on column FFM_STOCK_STOCKHISTORY.SELL3 is
'卖三';

comment on column FFM_STOCK_STOCKHISTORY.SELL3PRI is
'卖三报价';

comment on column FFM_STOCK_STOCKHISTORY.SELL4 is
'卖四';

comment on column FFM_STOCK_STOCKHISTORY.SELL4PRI is
'卖四报价';

comment on column FFM_STOCK_STOCKHISTORY.SELL5 is
'卖五';