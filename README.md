Movie Recommendation Engine
=====================================
A simple movie recommendation engine based on Apache Mahout.  This is a Spring application with persistence provided by Spring Data/Hibernate/JPA.  Although it's a simple application, it is a real recommendation engine with data stored in 2 formats:
MySQL database and CSV file.  The idea is simple.

Given:

<ol>
    <li> A list of users</li>
    <li> A list of movies</li>
    <li> Similarities between movie 1 and movie 2</li>
    <li> Users preferences for each movie</li>
</ol>

Recommend a set of movies that the user would enjoy.
<br/>
<br/>
<b>Interface:</b>


```java
package com.jodisoft.recommendation.engine;

import java.util.Set;
import org.apache.mahout.cf.taste.common.TasteException;
import com.jodisoft.recommendation.model.Movie;

public interface RecommendationEngine {
    public Set<Movie> recommend(final Integer userId, final int howMany)
            throws TasteException;
}
```
<b>Data Model:</b>

![Settings Window](https://raw.githubusercontent.com/julesbond007/movie-recommendation-engine/master/docs/recommendation_tables.png)

| Tables                   |Explanation                                                    |
|:--------------------------|:---------------------------------------------------------------|
| users                    |  Store user info like name, email etc.                        |
| items                    |  Store movie info like name, genre, unique identifier etc.    |
| taste_preferences        |  Mapping table between users and movies preference            |
| taste_item_similarity    |  Mapping table between for self referencing movie similarities|


<b>Hibernate Entities:</b>
<br/>
Movie.java

```java
@Entity(name = "items")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "imdb_id")
    private String imdb_id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MovieGenre genre;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "taste_item_similarity", joinColumns = { @JoinColumn(
            name = "item_id_a") }, inverseJoinColumns = { @JoinColumn(
            name = "item_id_b") })
    private Set<Movie> similarMovies;
    
    //getters and setters ommited
}
```
<br/>
User.java
<br/>
```java
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "taste_preferences", joinColumns = { @JoinColumn(
            name = "user_id") }, inverseJoinColumns = { @JoinColumn(
            name = "item_id") })
    private Set<Movie> moviePreferences;
    
    //getters and setters ommited
}
```
