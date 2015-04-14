--populate users
insert into users (name,email) values ('jay','jay.paulynice@gmail.com');
insert into users (name,email) values ('nate','nate.paulynice@gmail.com');
insert into users (name,email) values ('aiden','aiden.paulynice@gmail.com');
insert into users (name,email) values ('mella','mella.paulynice@gmail.com');
insert into users (name,email) values ('me','me.paulynice@gmail.com');


--populate items
insert into items (name,type,imdb_id) values('captain america','ACTION','tt0103923');
insert into items (name,type,imdb_id) values('iron man','ACTION','tt0371746');
insert into items (name,type,imdb_id) values('rambo','ACTION','tt0462499');
insert into items (name,type,imdb_id) values('mcgyver','ACTION','tt0110419');
insert into items (name,type,imdb_id) values('cars','FAMILY','tt0317219');
insert into items (name,type,imdb_id) values('planes','FAMILY','tt1691917');


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
insert into taste_preferences (user_id,item_id,preference) values (5,3,1);
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