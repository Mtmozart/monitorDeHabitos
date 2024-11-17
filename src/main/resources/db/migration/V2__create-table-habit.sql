CREATE TABLE habit (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    done ENUM('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
    start DATE NOT NULL,
    end DATE NOT NULL,
    client_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);

CREATE TABLE week (
    id BIGINT NOT NULL AUTO_INCREMENT,
    habit_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_percentage FLOAT(4, 2),
    percentage_per_day FLOAT(4, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_week_habit FOREIGN KEY (habit_id) REFERENCES habit(id)
);

CREATE TABLE progress (
    id BIGINT NOT NULL AUTO_INCREMENT,
    habit_day DATE NOT NULL,
    week_id BIGINT NOT NULL,
    completed BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_week FOREIGN KEY (week_id) REFERENCES week(id) ON DELETE CASCADE
);
