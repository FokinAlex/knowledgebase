![Microsoft SQL Server logo][logo]

**Microsoft SQL Server** is a relational database management system developed by Microsoft.

[![Go Back][backmark]][backlink]
[![Go Home][homemark]][homelink]

## Contents

1. [DDL](#DDL)
    1. [Create database](#Create-database)
    2. [Create table](#Create-table)
2. 
3. 

## Getting started ...

...

## DDL

A **Data Definition Language** (DDL) is a syntax similar to a computer programming language for defining 
data structures, especially database schemas. DDL statements create, modify and remove database objects 
such as tables, indexes and users. Common DDL statements are: `create`, `alter` and `drop`.

## Create database

Creating database `steinerdb` with two file groups `major` and `minor`:

```sql
create database [steinerdb]
on primary
    (name = 'f1', filename = '/usr/src/steinerdb/major/sdb_1.mdf', size = 10Mb, maxsize = unlimited, filegrowth = 5Mb),
    (name = 'f2', filename = '/usr/src/steinerdb/major/sdb_2.ndf', size = 10Mb, maxsize = 100mb, filegrowth = 5Mb),
filegroup minor
    (name = 'f3', filename = '/usr/src/steinerdb/minor/sdb_2.ndf', size = 10Mb, maxsize = 100mb, filegrowth = 5Mb),
    (name = 'f4', filename = '/usr/src/steinerdb/minor/sdb_3.ndf', size = 10Mb, maxsize = 100mb, filegrowth = 5Mb),
    (name = 'f5', filename = '/usr/src/steinerdb/minor/sdb_4.ndf', size = 10Mb, maxsize = 100mb, filegrowth = 5Mb)
log on
    (name = 'lf1', filename = '/usr/src/steinerdb/major/log_1.ldf', size = 10Mb, maxsize = 100mb, filegrowth = 5Mb),
    (name = 'lf2', filename = '/usr/src/steinerdb/minor/log_2.ldf', size = 10Mb, maxsize = 100mb, filegrowth = 5Mb);
```

`ls -Rlh` in `/usr/src/steinerdb` returns:

```text
.:
total 8.0K
drwxr-xr-x 2 root root 4.0K Apr 27 09:59 major
drwxr-xr-x 2 root root 4.0K Apr 27 09:59 minor

./major:
total 31M
-rw-r----- 1 root root 10M Apr 27 09:59 log_1.ldf
-rw-r----- 1 root root 10M Apr 27 09:59 sdb_1.mdf
-rw-r----- 1 root root 10M Apr 27 09:59 sdb_2.ndf

./minor:
total 40M
-rw-r----- 1 root root 10M Apr 27 09:59 log_2.ldf
-rw-r----- 1 root root 10M Apr 27 09:59 sdb_2.ndf
-rw-r----- 1 root root 10M Apr 27 09:59 sdb_3.ndf
-rw-r----- 1 root root 10M Apr 27 09:59 sdb_4.ndf
```

## Create table

Database structure:

![Database diagram][diagram]

Creating tables:

```sql
create table algorithms (
    id                      integer identity primary key,
    system_name             varchar (10),
    description             varchar (255)
);

create table cases (
    id                      integer identity primary key,
    minimum_spanning_tree   numeric (20, 5),
    minimum_steiner_tree    numeric (20, 5),
    count_of_points         numeric (3)
);

create table results (
     id                      integer identity primary key,
     case_id                 integer,
     algorithm_id            integer,
     steiner_tree            numeric (20, 5),
     ms_time                 integer,
     constraint results_case_fk      foreign key (case_id)      references cases (id),
     constraint results_algorithm_fk foreign key (algorithm_id) references algorithms (id)
);

create table points (
    id                      integer identity primary key,
    x                       numeric (20, 5),
    y                       numeric (20, 5)
);

create table case_points (
    point_id                integer unique identity,
    case_id                 integer,
    constraint case_points_point_fk foreign key (point_id) references points (id),
    constraint case_points_case_fk  foreign key (case_id)  references cases (id)
);

create table result_points (
     point_id               integer unique identity,
     result_id              integer,
     constraint result_points_point_fk  foreign key (point_id)  references points (id),
     constraint result_points_result_fk foreign key (result_id) references results (id)
);
```

**TODO: Изменение и удаление записей**

**TODO: union, union all, except, intersect**

[logo]:     https://github.com/FokinAlex/knowledgebase/blob/master/resources/logos/mssql.png?raw=true
[homelink]: https://github.com/FokinAlex/knowledgebase
[homemark]: https://github.com/FokinAlex/knowledgebase/blob/master/resources/marks/home.png?raw=true
[backlink]: https://github.com/FokinAlex/knowledgebase/blob/master/readme.md
[backmark]: https://github.com/FokinAlex/knowledgebase/blob/master/resources/marks/backward.png?raw=true
[diagram]:  https://github.com/FokinAlex/knowledgebase/blob/master/resources/diagrams/steinerdb_diagram.png?raw=true
