-- Удаляем старое значение по умолчанию
ALTER TABLE users
    ALTER COLUMN id DROP DEFAULT;

-- Изменяем тип колонки на BIGINT (не BIGSERIAL!)
ALTER TABLE users
    ALTER COLUMN id TYPE BIGINT;

-- Создаём sequence для автоинкремента
CREATE SEQUENCE IF NOT EXISTS users_id_seq;

-- Устанавливаем новое значение по умолчанию из sequence
ALTER TABLE users
    ALTER COLUMN id SET DEFAULT nextval('users_id_seq');

-- Связываем sequence с колонкой (для автоматического удаления при DROP COLUMN)
ALTER SEQUENCE users_id_seq OWNED BY users.id;

-- Устанавливаем текущее значение sequence на основе максимального id
SELECT setval('users_id_seq', COALESCE((SELECT MAX(id) FROM users), 1));