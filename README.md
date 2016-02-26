Movie Recommendation Engine
=====================================
[![Build Status](https://travis-ci.org/julesbond007/movie-recommendation-engine.svg?branch=master)](https://travis-ci.org/julesbond007/movie-recommendation-engine)

A simple movie recommendation engine based on Apache Mahout.  This is a Jersey REST API with persistence provided by Spring Data/Hibernate/JPA.  Although it's a simple application, it is a real recommendation engine with data stored in 2 formats:
MySQL database and CSV file.  The idea is simple.

Given:

<ol>
    <li> A list of users</li>
    <li> A list of movies</li>
    <li> Similarities between movie 1 and movie 2</li>
    <li> Users preferences for each movie</li>
</ol>

Recommend a set of movies that the user would enjoy.

Setup
-------
1. create recommendation db: `create database recommendationdb`
2. update [db properties](https://github.com/julesbond007/movie-recommendation-engine/blob/master/src/main/resources/META-INF/properties/db.properties): to have correct url, user/password to mysql database
3. run `gradle clean build`
4. deploy api: `cp -r build/libs/movie-recommendation-engine.war $TOMCAT_HOME/webapps/<some-name>.war`

On Startup, the application runs these 2 scripts under: `src/main/resources/META-INF/data/sql` :

1. [recreate tables](https://github.com/julesbond007/movie-recommendation-engine/blob/master/src/main/resources/META-INF/data/sql/ddl.sql)
2. [populate data](https://github.com/julesbond007/movie-recommendation-engine/blob/master/src/main/resources/META-INF/data/sql/init.sql)

REST API Example:
```java
GET localhost:8080/<some-name>/api/v1/users/2/recommendations?limit=2
```

API Response:
```json
[
  {
    "genre": "DRAMA",
    "id": 4,
    "imdb_id": "tt0120338",
    "name": "titanic",
    "img": "http://ia.media-imdb.com/images/M/MV5BMjExNzM0NDM0N15BMl5BanBnXkFtZTcwMzkxOTUwNw@@._V1_SX300.jpg"
  },
  {
    "genre": "FAMILY",
    "id": 5,
    "imdb_id": "tt0317219",
    "name": "cars",
    "img": "http://ia.media-imdb.com/images/M/MV5BMTg5NzY0MzA2MV5BMl5BanBnXkFtZTYwNDc3NTc2._V1_SX300.jpg"
  }
]
```

Try an invalid user with id 9000:
```java
GET localhost:8080/<some-name>/api/v1/users/9000/recommendations?limit=2
```

Response:
```json
{
    "status": 404,
    "info": "No user found with id: 9000",
    "requestId": "880a6889-3969-43dc-9de1-4692d69ff807"
}
```




