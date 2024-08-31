CREATE TABLE client (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    type_user VARCHAR(100) NOT NULL,
    is_client BOOLEAN NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL,
    street VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    number VARCHAR(20),
    state CHAR(2) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    complement VARCHAR(100),
    PRIMARY KEY (id)
);
