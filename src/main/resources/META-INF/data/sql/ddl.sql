--recreate database from scratch

drop database recommendationdb;
create database recommendationdb;
use recommendationdb;

drop table if exists taste_item_similarity;
drop table if exists taste_preferences;
drop table if exists users;
drop table if exists items;

--items table
create table items (
    item_id      bigint not null auto_increment, 
    name         varchar (100) not null, 
    type         varchar (100) default null,
    imdb_id      varchar (100) default null,
    img          varchar (2000) default null,
    
    primary key (item_id) 
); 

--users table
create table users ( 
    user_id      bigint not null auto_increment,
    name         varchar (50) NOT null,
    email        varchar (100) default null,
    
    primary key (user_id) 
); 

--taste preference table
create table taste_preferences (
    user_id      bigint not null,
    item_id      bigint not null,
    preference   integer not null,
    timestamp    timestamp not null default current_timestamp,
    
    foreign key (user_id) references users (user_id) on delete cascade,
    foreign key (item_id) references items (item_id) on delete cascade 
); 

--create taste similarity table
create table taste_item_similarity (
    item_id_a    bigint not null,
    item_id_b    bigint not null,
    similarity   double not null,
    
    foreign key (item_id_a) references items (item_id) on delete cascade,
    foreign key (item_id_b) references items (item_id) on delete cascade 
); 

--create indexes
create index item_preferences_index1 on taste_preferences (user_id, item_id); 
create index item_preferences_index2 on taste_preferences (user_id); 
create index item_preferences_index3 on taste_preferences (item_id);
create index taste_item_similarity_index1 on taste_item_similarity (item_id_a);
create index taste_item_similarity_index2 on taste_item_similarity (item_id_b);
