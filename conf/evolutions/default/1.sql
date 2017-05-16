# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                        bigint not null,
  name                      varchar(255),
  phone                     varchar(255),
  email                     varchar(255),
  constraint pk_contact primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  constraint uq_user_username unique (username),
  constraint pk_user primary key (id))
;

create sequence contact_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contact;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contact_seq;

drop sequence if exists user_seq;

