CREATE TABLE week (
    id BIGINT NOT NULL AUTO_INCREMENT,
    habit_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    totale_percentage FLOAT(5, 2),
    number_week BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_week_habit FOREIGN KEY (habit_id) REFERENCES habit(id) ON DELETE CASCADE,
    CONSTRAINT fk_week_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);
