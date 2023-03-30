INSERT INTO users(username, password, enabled, authority_id)
VALUES ('root', '$2a$10$9OSvsL5g5KalRN8O.bvCt.td0KEXc4PZWHgInf3aVPArNe8wWP8Ji', true,
        (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));
