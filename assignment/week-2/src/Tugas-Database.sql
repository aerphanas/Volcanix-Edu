-- ans 1

SELECT title, vote_average, release_date FROM movies.movie
WHERE release_date IS NOT NULL
ORDER BY  release_date ASC, vote_average ASC
LIMIT 5;


-- ans 2

SELECT DISTINCT title, release_date FROM movies.movie
INNER JOIN movies.movie_genres ON movie.movie_id = movie_genres.movie_id
INNER JOIN movies.genre ON movie_genres.genre_id = genre.genre_id
WHERE genre.genre_name IN ('Fantasy', 'Horror', 'Action') AND movie.budget > 200000000
ORDER BY movie.release_date ASC
LIMIT 5;

-- ans 3

(SELECT title, popularity, person_name FROM movies.movie
INNER JOIN movies.movie_cast on movie.movie_id = movie_cast.movie_id
INNER JOIN movies.person on movie_cast.person_id = person.person_id
WHERE person.person_name LIKE 'Robert Downey Jr.'
ORDER BY popularity ASC
LIMIT 1)

UNION ALL

(SELECT title, popularity, person_name FROM movies.movie
INNER JOIN movies.movie_cast on movie.movie_id = movie_cast.movie_id
INNER JOIN movies.person on movie_cast.person_id = person.person_id
WHERE person.person_name LIKE 'Robert Downey Jr.'
ORDER BY popularity DESC
LIMIT 1);

-- ans 4

SELECT COUNT(title) AS Jumlah FROM movies.movie
WHERE vote_average = 10.00;

-- ans 5

SELECT COUNT(person_name) AS Jumlah FROM movies.person
LEFT JOIN movies.movie_crew on movie_crew.person_id = person.person_id
WHERE movie_crew.person_id IS NULL;

