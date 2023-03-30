CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled   BOOLEAN default true,
    authority_id INT NOT NULL REFERENCES authorities(id)
);

comment on table users is 'Зарегистрированные пользователи';
comment on column users.id is 'Идентификатор пользователя';
comment on column users.username is 'Имя пользователя';
comment on column users.password is 'Пароль пользователя';
comment on column users.enabled is 'Разрешение на доступ';
comment on column users.authority_id is 'Внешний клюя на роль пользователя';