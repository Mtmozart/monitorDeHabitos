CREATE TABLE week (
    id BIGINT NOT NULL AUTO_INCREMENT,
    habit_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    totale_percentage FLOAT(4, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_week_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);
