use md4_ss16_db;

create table movies (
    movie_id int auto_increment primary key ,
    title varchar(50) not null,
    director varchar(50) not null ,
    year int not null
);

# drop procedure delete_movie;

DELIMITER $$
create procedure add_movie (
    title_in varchar(50),
    director_in varchar(50),
    year_in int
)
begin
    insert into movies (title, director, year)
        values (title_in, director_in, year_in);
end $$

create procedure list_movies ()
begin
    select * from movies;
end $$

create procedure update_movie (
    id_in int,
    title_in varchar(50),
    director_in varchar(50),
    year_in int
)
begin
    update movies
        set title = title_in,
            director = director_in,
            year = year_in
    where movie_id = id_in;
end $$

create procedure delete_movie (
    id_in int
)
begin
    delete from movies
    where movie_id = id_in;
end $$

DELIMITER ;

