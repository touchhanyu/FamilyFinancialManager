create tablespace ffm datafile 'F:\Database\oradata\orcl\FFM.dbf' size 50m autoextend on;
create user ffm identified by ffm default tablespace ffm;
grant connect,resource to ffm;
grant unlimited tablespace to ffm;
grant dba to ffm;