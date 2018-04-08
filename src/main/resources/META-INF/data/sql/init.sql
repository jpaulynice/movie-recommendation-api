/** populate users table */
insert into users (name,email) values ('jay','jay.paulynice@gmail.com');
insert into users (name,email) values ('nate','nate.paulynice@gmail.com');
insert into users (name,email) values ('aiden','aiden.paulynice@gmail.com');
insert into users (name,email) values ('mella','mella.paulynice@gmail.com');
insert into users (name,email) values ('me','me.paulynice@gmail.com');
insert into users (name,email) values ('none','no.recommendations@gmail.com');


/** populate movies table */
insert into items (name,type,imdb_id, img) 
values('captain america','ACTION','tt0103923', 'http://ia.media-imdb.com/images/M/MV5BZDkxMjg3MzAtMmFjYS00YzUyLWFhYTQtZmFlMzFhZDhjNDI5XkEyXkFqcGdeQXVyMTExNDQ2MTI@._V1_SX300.jpg');

insert into items (name,type,imdb_id, img) 
values('iron man','ACTION','tt0371746', 'http://ia.media-imdb.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg');

insert into items (name,type,imdb_id, img) 
values('rambo','ACTION','tt0462499', 'http://ia.media-imdb.com/images/M/MV5BMTI5Mjg1MzM4NF5BMl5BanBnXkFtZTcwNTAyNzUzMw@@._V1_SX300.jpg');

insert into items (name,type,imdb_id, img) 
values('titanic','DRAMA','tt0120338', 'http://ia.media-imdb.com/images/M/MV5BMjExNzM0NDM0N15BMl5BanBnXkFtZTcwMzkxOTUwNw@@._V1_SX300.jpg');

insert into items (name,type,imdb_id, img) 
values('cars','FAMILY','tt0317219', 'http://ia.media-imdb.com/images/M/MV5BMTg5NzY0MzA2MV5BMl5BanBnXkFtZTYwNDc3NTc2._V1_SX300.jpg');

insert into items (name,type,imdb_id, img) 
values('planes','FAMILY','tt1691917', 'http://ia.media-imdb.com/images/M/MV5BMjAwODc5NzYzOF5BMl5BanBnXkFtZTcwNTk4MjEzOQ@@._V1_SX300.jpg');

/** populate taste_preferences table */
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

/** populate taste_item_similarity table */
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
