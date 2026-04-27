CREATE TABLE files_client(
                      id BIGSERIAL PRIMARY KEY,
                      filename VARCHAR(255),
                      url TEXT NOT NULL,
                      client_id BIGINT,

                      CONSTRAINT fk_user
                          FOREIGN KEY (client_id)
                              REFERENCES client(id)
                              ON DELETE CASCADE
);
