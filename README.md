Movie Recommendation Engine
=====================================
A simple movie recommendation engine based on Apache Mahout.  This is a Spring application with persistence provided by Spring Data/Hibernate/JPA.  Although it's a simple application, it is a real world recommendation engine with data stored in 2 formats:
MySQL database and CSV file.

The idea is simple given:
    1. A list of users
    2. A list of movies
    3. Similarities between movie 1 and movie 2
    4. User's preferene for each movie

We recommend a set of movies that the user would enjoy.
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
