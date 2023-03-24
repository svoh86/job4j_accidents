CREATE TABLE IF NOT EXISTS accident_rule
(
    id          SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accident (id),
    rule_id INT NOT NULL REFERENCES rule (id)
);

comment on table accident_rule is 'Таблица для связи Many-to-many инциденты и статьи';
comment on column accident_rule.id is 'Идентификатор';
comment on column accident_rule.accident_id is 'Внешний ключ на инцидент';
comment on column accident_rule.rule_id is 'Внешний ключ на статью';
