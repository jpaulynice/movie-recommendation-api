Movie Recommendation Engine
=====================================
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
1. Update [DB Properties](https://github.com/julesbond007/movie-recommendation-engine/blob/master/src/main/resources/META-INF/properties/db.properties) to have correct user/password to mysql db
2. Run `gradle clean build`
3. `cp -r build/libs/movie-recommendation-engine.war TOMCAT_HOME/webapps/<some-name>.war`

On Startup, the application runs the scripts under `src/main/resources/META-INF/data/sql` to create the tables and initial data.

REST API Example:
```java
GET localhost:8080/<some-name>/api/v1/users/2/recommendations?limit=2
[
  {
    "genre": "ACTION",
    "id": 4,
    "imdb_id": "tt0110419",
    "name": "mcgyver",
    "similarMovies": [...]
  },
  {
    "genre": "FAMILY",
    "id": 5,
    "imdb_id": "tt0317219",
    "name": "cars",
    "similarMovies": [...]
  }
]
```




