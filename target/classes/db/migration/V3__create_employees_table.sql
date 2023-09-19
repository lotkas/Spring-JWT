CREATE TABLE IF NOT EXISTS employees
(
    id         bigint      NOT NULL auto_increment primary key,
    email      varchar(55) not null,
    first_name  varchar(55) not null,
    last_name   varchar(55) not null,
    position_id bigint      not null,
    age        int         not null,
    salary     decimal     not null,
    in_job      tinyint(1)  not null,
    constraint employees_positions_id_fk
        foreign key (position_id) references positions (id)
);