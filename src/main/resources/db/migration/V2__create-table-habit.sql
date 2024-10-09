CREATE TABLE habit (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    done BOOLEAN NOT NULL,
    start DATE NOT NULL,
    end DATE NOT NULL,
    percentage_for_day FLOAT(5, 2),
    habit_day DATE NOT NULL,
    client_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);


CREATE TABLE progress (
    id BIGINT NOT NULL AUTO_INCREMENT,
    habit_day DATE NOT NULL,
    habit_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_habit FOREIGN KEY (habit_id) REFERENCES habit(id) ON DELETE CASCADE
);
