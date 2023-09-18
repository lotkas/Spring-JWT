CREATE TABLE IF NOT EXISTS users
(
    id         bigint NOT NULL auto_increment primary key,
    username   varchar(55) not null,
    password   varchar(55) not null,
    employeeId bigint      not null,
    roleId     bigint      not null,
    constraint users_employees_id_fk
    foreign key (employeeId) references employees (id)
);