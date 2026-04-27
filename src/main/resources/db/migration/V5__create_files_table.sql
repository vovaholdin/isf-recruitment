    CREATE TABLE files(
        id BIGSERIAL PRIMARY KEY,
        filename VARCHAR(255),
        url TEXT NOT NULL,
        user_id BIGINT,

        CONSTRAINT fk_user
                      FOREIGN KEY (user_id)
                      REFERENCES users(id)
                      ON DELETE CASCADE
    );