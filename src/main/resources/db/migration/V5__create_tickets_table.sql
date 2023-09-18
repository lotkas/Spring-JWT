CREATE TABLE IF NOT EXISTS tickets
(
    id         bigint                             NOT NULL auto_increment primary key,
    employeeId bigint                             not null,
    message    varchar(55)                        not null,
    createdAt  timestamp                          not null,
    status     enum ('DONE', 'WAIT', 'PROCESSED') not null,
    constraint tickets_employees_id_fk
        foreign key (employeeId) references employees (id)
);
