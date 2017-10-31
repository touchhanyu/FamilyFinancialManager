--drop table FFM_SYS_SYSSEQ cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_SYSSEQ                                        */
/*==============================================================*/
create table FFM_SYS_SYSSEQ 
(
   SEQCODE              VARCHAR2(30)         not null,
   MAXNO                INTEGER,
   constraint PK_FFM_SYS_SYSSEQ primary key (SEQCODE)
);

comment on column FFM_SYS_SYSSEQ.SEQCODE is
'主键代码';

comment on column FFM_SYS_SYSSEQ.MAXNO is
'主键值';

--drop table FFM_SYS_SYSVAR cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_SYSVAR                                        */
/*==============================================================*/
create table FFM_SYS_SYSVAR 
(
   VARTYPE              VARCHAR2(20)         not null,
   VALUE                VARCHAR2(200),
   constraint PK_FFM_SYS_SYSVAR primary key (VARTYPE)
);

comment on column FFM_SYS_SYSVAR.VARTYPE is
'系统参数类型';

comment on column FFM_SYS_SYSVAR.VALUE is
'系统参数值';

--drop table FFM_SYS_USER cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_USER                                          */
/*==============================================================*/
create table FFM_SYS_USER 
(
   ID                   INTEGER              not null,
   NAME                 VARCHAR2(30),
   PASSWORD             VARCHAR2(50),
   REALNAME             VARCHAR2(40),
   IDCARD               VARCHAR2(18),
   CARDTYPE             CHAR(2),
   SEX                  CHAR(1),
   NATIONALID           INTEGER,
   CULTRUE              VARCHAR2(20),
   TELPHONE             VARCHAR2(15),
   PHONE                VARCHAR2(15),
   EMAIL                VARCHAR2(30),
   QQ                   VARCHAR2(15),
   CITYID               INTEGER,
   HOMEADDRESS          VARCHAR2(50),
   OFFICEADDRESS        VARCHAR2(50),
   AUTHORITY            CHAR(1),
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   MODIFYDATE           DATE,
   MODIFYTIME           VARCHAR2(8),
   OPERID               INTEGER,
   constraint PK_FFM_SYS_USER primary key (ID)
);

comment on table FFM_SYS_USER is
'用户信息表';

comment on column FFM_SYS_USER.ID is
'用户编号';

comment on column FFM_SYS_USER.NAME is
'用户名称';

comment on column FFM_SYS_USER.PASSWORD is
'用户密码';

comment on column FFM_SYS_USER.REALNAME is
'真实姓名';

comment on column FFM_SYS_USER.IDCARD is
'证件号码';

comment on column FFM_SYS_USER.CARDTYPE is
'证件类型';

comment on column FFM_SYS_USER.SEX is
'性别';

comment on column FFM_SYS_USER.NATIONALID is
'国籍';

comment on column FFM_SYS_USER.CULTRUE is
'民族';

comment on column FFM_SYS_USER.TELPHONE is
'手机号';

comment on column FFM_SYS_USER.PHONE is
'电话号码';

comment on column FFM_SYS_USER.EMAIL is
'电子邮箱';

comment on column FFM_SYS_USER.QQ is
'QQ号';

comment on column FFM_SYS_USER.CITYID is
'城市编号';

comment on column FFM_SYS_USER.HOMEADDRESS is
'家庭住址';

comment on column FFM_SYS_USER.OFFICEADDRESS is
'工作地址';

comment on column FFM_SYS_USER.AUTHORITY is
'用户权限';

comment on column FFM_SYS_USER.MAKEDATE is
'注册日期';

comment on column FFM_SYS_USER.MAKETIME is
'注册时间';

comment on column FFM_SYS_USER.MODIFYDATE is
'修改日期';

comment on column FFM_SYS_USER.MODIFYTIME is
'修改时间';

comment on column FFM_SYS_USER.OPERID is
'操作员';

--drop table FI_COM_NATIONAL cascade constraints;
/*==============================================================*/
/* Table: FI_COM_NATIONAL                                       */
/*==============================================================*/
create table FI_COM_NATIONAL 
(
   ID                   INTEGER,
   NAME                 VARCHAR2(40),
   NAME_ENG             VARCHAR2(40)
);

comment on table FI_COM_NATIONAL is
'国家信息表';

comment on column FI_COM_NATIONAL.ID is
'国家编号';

comment on column FI_COM_NATIONAL.NAME is
'国家中文名称';

comment on column FI_COM_NATIONAL.NAME_ENG is
'国家英文名称';

--drop table FFM_SYS_SYSTASK cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_SYSTASK                                       */
/*==============================================================*/
create table FFM_SYS_SYSTASK 
(
   TASKID               INTEGER              not null,
   TASKNAME             VARCHAR2(30),
   TASKSTARTTIME        VARCHAR2(8),
   TASKTIMES            INTEGER,
   TASKPER              INTEGER,
   TASKPATH             VARCHAR2(150),
   TASKSTATUS           CHAR(1),
   constraint PK_FFM_SYS_SYSTASK primary key (TASKID)
);

comment on table FFM_SYS_SYSTASK is
'系统定时任务表';

comment on column FFM_SYS_SYSTASK.TASKID is
'任务编号';

comment on column FFM_SYS_SYSTASK.TASKNAME is
'任务名称';

comment on column FFM_SYS_SYSTASK.TASKSTARTTIME is
'任务开始时间';

comment on column FFM_SYS_SYSTASK.TASKTIMES is
'任务执行次数';

comment on column FFM_SYS_SYSTASK.TASKPER is
'任务间隔时间';

comment on column FFM_SYS_SYSTASK.TASKPATH is
'任务类全路径';

comment on column FFM_SYS_SYSTASK.TASKSTATUS is
'任务状态';

--drop table FFM_SYS_MENU cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_MENU                                          */
/*==============================================================*/
create table FFM_SYS_MENU 
(
   ID                   INTEGER              not null,
   TEXT                 VARCHAR2(30),
   ICON                 VARCHAR2(20),
   PATH                 VARCHAR2(200),
   PID                  INTEGER,
   MORDER               INTEGER,
   STATE                CHAR(1),
   constraint PK_FFM_SYS_MENU primary key (ID)
);

comment on table FFM_SYS_MENU is
'系统菜单配置表';

comment on column FFM_SYS_MENU.ID is
'菜单编号';

comment on column FFM_SYS_MENU.TEXT is
'菜单名称';

comment on column FFM_SYS_MENU.ICON is
'菜单图标';

comment on column FFM_SYS_MENU.PATH is
'菜单路径';

comment on column FFM_SYS_MENU.PID is
'父菜单编号';

comment on column FFM_SYS_MENU.MORDER is
'菜单顺序';

comment on column FFM_SYS_MENU.STATE is
'菜单状态';

--drop table FFM_SYS_DICTIONARY cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_DICTIONARY                                    */
/*==============================================================*/
create table FFM_SYS_DICTIONARY 
(
   ID                   INTEGER              not null,
   PID                  INTEGER,
   DICTCODE             VARCHAR2(20),
   DICTNAME             VARCHAR2(40),
   STATE                CHAR(1),
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   constraint PK_FFM_SYS_DICTIONARY primary key (ID)
);

comment on table FFM_SYS_DICTIONARY is
'数据字典表';

comment on column FFM_SYS_DICTIONARY.ID is
'字典编号';

comment on column FFM_SYS_DICTIONARY.PID is
'父编号';

comment on column FFM_SYS_DICTIONARY.DICTCODE is
'字典代码';

comment on column FFM_SYS_DICTIONARY.STATE is
'使用状态';

comment on column FFM_SYS_DICTIONARY.MAKEDATE is
'创建日期';

comment on column FFM_SYS_DICTIONARY.MAKETIME is
'创建时间';

--drop table FFM_SYS_DICTIONARYDETAIL cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_DICTIONARYDETAIL                              */
/*==============================================================*/
create table FFM_SYS_DICTIONARYDETAIL 
(
   ID                   INTEGER              not null,
   DID                  INTEGER,
   PID                  INTEGER,
   DICTCODE             VARCHAR2(20),
   DICTVALUE            VARCHAR2(20),
   DORDER               INTEGER,
   STATE                CHAR(1),
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   constraint PK_FFM_SYS_DICTIONARYDETAIL primary key (ID)
);

comment on table FFM_SYS_DICTIONARYDETAIL is
'数据字典明细表';

comment on column FFM_SYS_DICTIONARYDETAIL.ID is
'字典明细编号';

comment on column FFM_SYS_DICTIONARYDETAIL.DID is
'字典编号';

comment on column FFM_SYS_DICTIONARYDETAIL.PID is
'父编号';

comment on column FFM_SYS_DICTIONARYDETAIL.DICTCODE is
'字典代码';

comment on column FFM_SYS_DICTIONARYDETAIL.DICTVALUE is
'字典明细值';

comment on column FFM_SYS_DICTIONARYDETAIL.DORDER is
'显示顺序';

comment on column FFM_SYS_DICTIONARYDETAIL.STATE is
'使用状态';

comment on column FFM_SYS_DICTIONARYDETAIL.MAKEDATE is
'创建日期';

comment on column FFM_SYS_DICTIONARYDETAIL.MAKETIME is
'创建时间';

--drop table FFM_SYS_AUTHORITY cascade constraints;
/*==============================================================*/
/* Table: FFM_SYS_AUTHORITY                                     */
/*==============================================================*/
create table FFM_SYS_AUTHORITY 
(
   USERID               INTEGER              not null,
   MENUID               INTEGER              not null,
   MAKEDATE             DATE,
   MAKETIME             VARCHAR2(8),
   OPERID               INTEGER,
   constraint PK_FFM_SYS_AUTHORITY primary key (USERID, MENUID)
);

comment on table FFM_SYS_AUTHORITY is
'用户菜单权限表';

comment on column FFM_SYS_AUTHORITY.USERID is
'用户ID';

comment on column FFM_SYS_AUTHORITY.MENUID is
'菜单ID';

comment on column FFM_SYS_AUTHORITY.MAKEDATE is
'操作日期';

comment on column FFM_SYS_AUTHORITY.MAKETIME is
'操作时间';

comment on column FFM_SYS_AUTHORITY.OPERID is
'操作员';