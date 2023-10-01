CREATE TABLE IF NOT EXISTS roles
(
    id   bigint                 NOT NULL auto_increment primary key,
    role enum ('ADMIN', 'USER') NOT NULL
);