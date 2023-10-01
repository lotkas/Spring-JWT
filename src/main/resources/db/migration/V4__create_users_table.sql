CREATE TABLE IF NOT EXISTS users
(
    id          bigint      NOT NULL auto_increment primary key,
    username    varchar(55) NOT NULL,
    password    varchar(80) NOT NULL,
    employee_id bigint,
    role_id     bigint      NOT NULL,
    constraint users_employees_id_fk
        foreign key (employee_id) references employees (id),
    constraint users_roles_id_fk
        foreign key (role_id) references roles (id)
);