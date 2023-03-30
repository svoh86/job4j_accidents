CREATE TABLE IF NOT EXISTS users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled   BOOLEAN default true,
    PRIMARY KEY (username)
);

comment on table users is 'Зарегистрированные пользователи';
comment on column users.username is 'Имя пользователя и первичный ключ';
comment on column users.password is 'Пароль пользователя';
comment on column users.enabled is 'Разрешение на доступ';