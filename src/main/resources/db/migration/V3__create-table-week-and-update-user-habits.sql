CREATE TABLE week (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    done BOOLEAN NOT NULL,
    start DATE NOT NULL,
    end DATE NOT NULL,
    percentage_for_day FLOAT(5, 2),
    client_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_week_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);
