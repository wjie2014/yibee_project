--每日瑜伽 创建表
create table sport_information(
    id int primary key not null auto_increment,
    name varchar(255) not null,
    updateTime date,
    thumb varchar(255),
    typeid int not null,
    typeName varchar(255),
    times int,
    detail LONGTEXT
) charset utf8 collate utf8_general_ci;
--为id建立索引
CREATE INDEX sport_information_id ON sport_information (id);
--分页查询表
SELECT * FROM sport_information INNER JOIN (SELECT id FROM sport_information ORDER BY id desc LIMIT 1, 10) sport USING (id);

--创建视频表存储
 create table sport_movie(
    id int primary key not null auto_increment,
    name varchar(255) not null,
    updateTime date,
    thumb varchar(255),
    typeid int not null,
    typeName varchar(255),
    times int,
    movie_url varchar(255)  
    ) charset utf8 collate utf8_general_ci;
--为id建立索引
CREATE INDEX sport_movie_id ON sport_movie (id);


----用户权限设计----
--action表（权限表）：记录着系统中所有的动作，以及动作相关描述。
create table action(
	actionid int primary key not null,--id
	actionname varchar(20) not null,--动作名称
	actioncolumnid int not null,--动作分栏编号，映射到actioncolumn表
	action varchar(20) not null,--动作字符串
    viewmode int not null--是否可见，0可见，1不可见
);
--actioncolumn表（权限分栏表）：记录着动作的分栏，系统运行时，左侧菜单栏提供了几块不同的功能，每一块就是一个分栏，每添加一个分栏，该表中的记录就会增加一条,相对应的，左侧菜单栏中也会新增机一个栏。
create table actioncolumn(
	actioncolumnid int primary key  not null,--id
    actioncolumnname varchar(60) not null --动作分栏名称
);
--actiongroup表（权限映射表）：记录着动作所在的组。
create table actiongroup(
	id int primary key not null,--id
	action int not null,--动作名称,映射到action表
	groupid int not null,--动作所在组，映射到mastergroup表
	masterid int not null,--创建者id
	mastername varchar(60) not null,--创建者名字
	createdate date not null--创建时间
);
--groupmanager表（管理组表）：记录着管理组的相关信息，每添加一个管理组，这里的记录就会增加一条。
create table groupmanager(
	groupid int primary key not null,--id
    groupname varchar(60) not null,--管理组名称
	groupinfo varchar(255) ,--管理组信息
	masterid int not null,--创建者id
	mastername varchar(60) not null,--创建者名字
	createdate date not null--创建时间
);
--mastergroup表（人员映射表）：记录着管理员所在的管理组，由于一名管理员可能同同时属于多个组，所以该表中关于某一名管理员的记录可能有多条。
create table mastergroup(
	id int primary key not null,--id
 	masterid int not null,--管理员id，映射到master表
	name varchar(20) not null,--管理员名称
	groupid int not null ,--管理组id，映射group表
    masterid2 int not null,--修改者id
	mastername varchar(20) not null,--修改者名称
	createdate date not null--修改时间
);
--master表（人员表）：记录着所有管理员的信息，每添加一个管理员，该表就会增加一条记录。
create table master(
	id int primary key not null,--id
	name varchar(20) not null,--管理员名称
	password varchar(30) not null,--管理员密码
	createdate date not null,--创建时间
	truename varchar(20),--真实姓名
	sex varchar(2) not null,--性别
	birthday date ,--出生日期
    dept varchar(60) not null,--所属部门
	position varchar(60) not null,--职务
	positiondesc varchar(255) ,--职务描述
	office_phone varchar(16),--办公室电话
    mobile varchar(16),--手机号码
	home_phone varchar(16),--家里电话
	email varchar(60),--邮箱
	masterid int not null,--创建者id
	mastername varchar(20) not null,--创建者名称
	createdate date not null--创建时间
);
----用户权限设计----