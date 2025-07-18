create schema md4_session15_jdbc;
use md4_session15_jdbc;

create table students
(
    student_id    int primary key auto_increment,
    full_name     varchar(100) not null,
    date_of_birth date         not null,
    email         varchar(100) not null unique
);

DELIMITER $$
create procedure get_all_students()
begin
    select * from students;
end;
DELIMITER $$

DELIMITER $$
create procedure add_student(
    in in_full_name varchar(100),
    in in_date_of_birth date,
    in in_email varchar(100)
)
begin
    insert into students (full_name, date_of_birth, email)
    values (in_full_name, in_date_of_birth, in_email);
end;
DELIMITER $$

DELIMITER $$
create procedure update_student(
    in in_id int,
    in in_full_name varchar(100),
    in in_date_of_birth date,
    in in_email varchar(100)
)
begin
    update students
    set full_name     = in_full_name,
        date_of_birth = in_date_of_birth,
        email         = in_email
    where student_id = in_id;
end;
DELIMITER $$

DELIMITER $$
create procedure find_student_by_id(in in_id int)
begin
    select *
    from students
    where student_id = in_id;
end;
DELIMITER $$

DELIMITER $$
create procedure delete_student(in in_id int)
begin
    delete
    from students
    where student_id = in_id;
end;
DELIMITER $$

DELIMITER $$
create procedure is_exist_id(
    in_id int,
    out count_student int)
begin
    select count(*)
    into count_student
    from students
    where student_id = in_id;
end;
DELIMITER $$

DELIMITER $$
create procedure find_student_by_id(
    in_id int
)
begin
    select *
    from students
    where student_id = in_id;
end;
DELIMITER $$
