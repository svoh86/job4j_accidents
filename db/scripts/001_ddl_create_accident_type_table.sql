CREATE TABLE IF NOT EXISTS accident_type
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

comment on table accident_type is 'Тип инцидента';
comment on column accident_type.id is 'Идентификатор типа инцидента';
comment on column accident_type.name is 'Название типа инцидента';