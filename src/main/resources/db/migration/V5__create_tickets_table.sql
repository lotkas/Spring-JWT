CREATE TABLE IF NOT EXISTS tickets
(
    id          bigint                             NOT NULL auto_increment primary key,
    employee_id bigint                             NOT NULL,
    message     varchar(55)                        NOT NULL,
    created_at  timestamp                          NOT NULL,
    status      enum ('DONE', 'WAIT', 'PROCESSED') NOT NULL,
    constraint tickets_employees_id_fk
        foreign key (employee_id) references employees (id)
);
