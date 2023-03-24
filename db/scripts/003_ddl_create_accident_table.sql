CREATE TABLE IF NOT EXISTS accident
(
    id      SERIAL PRIMARY KEY,
    name    TEXT NOT NULL,
    text    TEXT NOT NULL,
    address TEXT NOT NULL,
    type_id INT  NOT NULL REFERENCES accident_type (id)
);

comment on table accident is 'Инциденты';
comment on column accident.id is 'Идентификатор инцидента';
comment on column accident.name is 'Имя автора';
comment on column accident.text is 'Описание инцидента';
comment on column accident.address is 'Адрес инцидента';
comment on column accident.type_id is 'Внешний ключ на тип инцидента';
