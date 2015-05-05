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
