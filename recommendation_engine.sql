DROP DATABASE if exists recommendationdb;
CREATE DATABASE recommendationdb; 
  USE recommendationdb; 
  
  --create items tables
  CREATE TABLE items (
     item_id INTEGER NOT NULL AUTO_INCREMENT, 
     name varchar (100) NOT NULL, 
     type varchar (100) default NULL, 
     PRIMARY KEY (item_id) 
  ); 

  --create users table
  CREATE TABLE users ( 
	 user_id INTEGER NOT NULL AUTO_INCREMENT,
     name varchar (50) NOT NULL, 
     email varchar (100) default NULL, 
     PRIMARY KEY (user_id) 
  ); 

  --create taste_preferences table table
  CREATE TABLE taste_preferences (
	 user_id INTEGER NOT NULL, 
     item_id INTEGER NOT NULL, 
     preference INTEGER NOT NULL, 
     timestamp timestamp not null default current_timestamp, 
     FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE, 
     FOREIGN KEY (item_id) REFERENCES Items (item_id) ON DELETE CASCADE 
  ); 

  --create taste_item_similarity table
  CREATE TABLE taste_item_similarity (
	 item_id_a INTEGER NOT NULL, 
     item_id_b INTEGER NOT NULL, 
     similarity DOUBLE NOT NULL, 
     FOREIGN KEY (item_id_a) REFERENCES items (item_id) ON DELETE CASCADE, 
     FOREIGN KEY (item_id_b) REFERENCES items (item_id) ON DELETE CASCADE 
  ); 

  CREATE INDEX item_preferences_index1 ON taste_preferences (user_id, item_id); 
  CREATE INDEX item_preferences_index2 ON taste_preferences (user_id); 
  CREATE INDEX item_preferences_index3 ON taste_preferences (item_id);

--populate users
insert into users (name,email) values ('jay','jay.paulynice@gmail.com');
insert into users (name,email) values ('nate','nate.paulynice@gmail.com');
insert into users (name,email) values ('aiden','aiden.paulynice@gmail.com');
insert into users (name,email) values ('mella','mella.paulynice@gmail.com');
insert into users (name,email) values ('me','me.paulynice@gmail.com');


--populate items
insert into items (name,type) values('captain america','movie');
insert into items (name,type) values('iron man','movie');
insert into items (name,type) values('rambo','movie');
insert into items (name,type) values('mcgyver','movie');
insert into items (name,type) values('cars','movie');
insert into items (name,type) values('planes','movie');


--populate user preferences
insert into taste_preferences (user_id,item_id,preference) values (1,1,1);
insert into taste_preferences (user_id,item_id,preference) values (2,2,0);
insert into taste_preferences (user_id,item_id,preference) values (3,3,0);
insert into taste_preferences (user_id,item_id,preference) values (4,4,1);
insert into taste_preferences (user_id,item_id,preference) values (5,5,1);
insert into taste_preferences (user_id,item_id,preference) values (1,2,0);
insert into taste_preferences (user_id,item_id,preference) values (2,4,1);
insert into taste_preferences (user_id,item_id,preference) values (3,2,1);
insert into taste_preferences (user_id,item_id,preference) values (4,2,0);
insert into taste_preferences (user_id,item_id,preference) values (5,5,1);
insert into taste_preferences (user_id,item_id,preference) values (1,3,1);
insert into taste_preferences (user_id,item_id,preference) values (1,6,1);


--populate item similarity
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (1,2,1);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (1,3,1);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (1,4,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (1,5,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (2,3,1);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (2,4,1);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (2,5,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (2,6,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (3,4,1);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (3,5,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (3,6,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (4,5,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (4,6,0);
insert into taste_item_similarity (item_id_a,item_id_b,similarity) values (5,6,1);