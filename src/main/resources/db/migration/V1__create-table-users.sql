CREATE TABLE "users"
(
    id                   BIGINT NOT NULL,
    name                 VARCHAR(100) NOT NULL,
    experience           INTEGER,
    email                VARCHAR(60) NOT NULL,
    phone_number         VARCHAR(30) NOT NULL,
    job_responsibilities VARCHAR(255),
    country              VARCHAR(255) NOT NULL,
    profession           SMALLINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);