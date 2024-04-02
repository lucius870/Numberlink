drop table if exists score  CASCADE;
drop table if exists  comment  CASCADE;
drop table if exists rating CASCADE;

create table score
(
    game varchar(64) not null,
    played_on timestamp not null,
    player varchar(64) not null,
    points int not null

);

create table comment
(
    game varchar(64) not null,
    player varchar(64) not null,
    comment varchar(255) not null,
    commented_on timestamp not null
);

create table rating
(

    game varchar(64) not null,
    player varchar(64) not null,
    rating int not null,
    rated_on timestamp not null
);
