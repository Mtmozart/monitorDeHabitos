CREATE TABLE client (
     id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    type_user ENUM('ADMIN', 'CLIENT') NOT NULL,
    is_client BOOLEAN NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE,
    street VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    number VARCHAR(20),
    state CHAR(2) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    complement VARCHAR(100)
);
