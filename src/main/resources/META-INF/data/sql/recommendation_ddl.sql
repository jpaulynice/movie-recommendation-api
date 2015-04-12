CREATE DATABASE recommendationdb; 
  USE recommendationdb; 
  
  CREATE TABLE items (
     item_id INTEGER NOT NULL AUTO_INCREMENT, 
     name varchar (100) NOT NULL, 
     type varchar (100) default NULL,
     imdb_id varchar (100) default NULL,
     PRIMARY KEY (item_id) 
  ); 

  CREATE TABLE users ( 
	 user_id INTEGER NOT NULL AUTO_INCREMENT,
     name varchar (50) NOT NULL, 
     email varchar (100) default NULL, 
     PRIMARY KEY (user_id) 
  ); 

  CREATE TABLE taste_preferences (
	 user_id INTEGER NOT NULL, 
     item_id INTEGER NOT NULL, 
     preference INTEGER NOT NULL, 
     timestamp timestamp not null default current_timestamp, 
     FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE, 
     FOREIGN KEY (item_id) REFERENCES items (item_id) ON DELETE CASCADE 
  ); 

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