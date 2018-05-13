Movie Recommendation Engine
=====================================
[![Build Status](https://travis-ci.org/julesbond007/movie-recommendation-api.svg?branch=master)](https://travis-ci.org/julesbond007/movie-recommendation-api)
[![Coverage Status](https://coveralls.io/repos/github/julesbond007/movie-recommendation-api/badge.svg?branch=master)](https://coveralls.io/github/julesbond007/movie-recommendation-api?branch=master)

A simple movie recommendation engine based on [Apache Mahout](https://mahout.apache.org/) machine learning library.  This is a Jersey REST API with persistence using Spring Data/Hibernate/JPA.  Although it's a simple application, it is a real recommendation engine with data stored in a MySQL database.  

The idea is simple. Given:

<ol>
    <li> A list of users</li>
    <li> A list of movies</li>
    <li> Similarity between movie 1 and movie 2</li>
    <li> Partial users' preferences for the movies</li>
</ol>

Recommend a set of movies that the user would enjoy.

Setup
-------
1. Install docker and docker-compose if not already installed `brew install docker && brew install docker-compose`
2. run `./gradlew clean build -x test` to skip tests
3. optionally run `./gradlew clean build`, update [test.properties](https://github.com/julesbond007/movie-recommendation-api/blob/master/src/main/resources/META-INF/properties/test.properties) with correct username/password to local database
3. run docker`docker-compose up`

On Startup, the application runs these 2 scripts under: `src/main/resources/META-INF/data/sql` :

1. [recreate tables](https://github.com/julesbond007/movie-recommendation-engine/blob/master/src/main/resources/META-INF/data/sql/ddl.sql)
2. [populate data](https://github.com/julesbond007/movie-recommendation-engine/blob/master/src/main/resources/META-INF/data/sql/init.sql)

Database schema:

[<img width="300px" src="https://raw.githubusercontent.com/julesbond007/movie-recommendation-api/master/design/recommendation_tables.png">](https://raw.githubusercontent.com/julesbond007/movie-recommendation-api/master/design/recommendation_tables.png)

REST API Example:
```bash
GET http://localhost:8080/movies/api/v1/users/2/recommendations?limit=2
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
GET http://localhost:8080/movies/api/v1/users/9000/recommendations?limit=2
```

Response:
```json
{
    "status": 404,
    "info": "No user found with id: 9000",
    "requestId": "880a6889-3969-43dc-9de1-4692d69ff807"
}
```




