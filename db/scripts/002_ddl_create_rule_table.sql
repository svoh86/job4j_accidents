CREATE TABLE IF NOT EXISTS rule
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

comment on table rule is 'Статья';
comment on column rule.id is 'Идентификатор статьи';
comment on column rule.name is 'Название статьи';