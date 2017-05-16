# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  phone                     varchar(255),
  email                     varchar(255),
  constraint pk_contact primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  constraint uq_user_username unique (username),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table contact;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

