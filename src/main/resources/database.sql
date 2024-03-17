drop table if exists score on CASCADE;
drop table if exists  comment on CASCADE;
drop table if exists rating on CASCADE;

create table score
(
    player varchar(64) not null,
    game varchar(64) not null,
    points int not null,
    playedOn timestamp not null
);

create table comment
(
    game varcharch(64) not null,
    player varchar(64) not null,
    comment varchar(255) not null,
    commentedOn timestamp not null
);

create table rating
(
    player varchar(64) not null,
    game varchar(64) not null,
    rating int not null,
    ratedOn timestamp not null
);