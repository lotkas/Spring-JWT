CREATE TABLE IF NOT EXISTS employees
(
    id         bigint      NOT NULL auto_increment primary key,
    email      varchar(55) not null,
    firstName  varchar(55) not null,
    lastName   varchar(55) not null,
    positionId bigint      not null,
    age        int         not null,
    salary     decimal     not null,
    inJob      tinyint(1)  not null,
    constraint employees_positions_id_fk
        foreign key (positionId) references positions (id)
);