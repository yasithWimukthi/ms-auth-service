CREATE TABLE IF NOT EXISTS users_auth
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    email
    VARCHAR
(
    320
) NOT NULL UNIQUE,
    password VARCHAR
(
    255
) NOT NULL,
    role VARCHAR
(
    50
) NOT NULL
    );
