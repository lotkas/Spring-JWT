CREATE TABLE IF NOT EXISTS employees
(
    id          bigint      NOT NULL auto_increment primary key,
    email       varchar(55) NOT NULL,
    first_name  varchar(55) NOT NULL,
    last_name   varchar(55) NOT NULL,
    position_id bigint      NOT NULL,
    age         int         NOT NULL,
    salary      decimal     NOT NULL,
    in_job      tinyint(1)  NOT NULL,
    constraint employees_positions_id_fk
        foreign key (position_id) references positions (id)
);