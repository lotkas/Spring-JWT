CREATE TABLE IF NOT EXISTS tickets
(
    id         bigint                             NOT NULL auto_increment primary key,
    employee_id bigint                             not null,
    message    varchar(55)                        not null,
    created_at  timestamp                          not null,
    status     enum ('DONE', 'WAIT', 'PROCESSED') not null,
    constraint tickets_employees_id_fk
        foreign key (employee_id) references employees (id)
);
