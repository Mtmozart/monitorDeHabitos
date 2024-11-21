
CREATE TABLE habit (
    id VARCHAR(36) PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    done ENUM('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
    start DATE NOT NULL,
    end DATE NOT NULL,
    client_id VARCHAR(36) NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE,
    INDEX idx_client_id (client_id)
);

CREATE TABLE week (
    id VARCHAR(36) PRIMARY KEY,
    habit_id VARCHAR(36) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_percentage DECIMAL(5, 2) CHECK (total_percentage <= 100),
    percentage_per_day DECIMAL(5, 2) CHECK (percentage_per_day <= 100),
    CONSTRAINT fk_week_habit FOREIGN KEY (habit_id) REFERENCES habit(id) ON DELETE CASCADE,
    INDEX idx_habit_id (habit_id)
);

CREATE TABLE progress (
    id VARCHAR(36) PRIMARY KEY,
    habit_day DATE NOT NULL,
    week_id VARCHAR(36) NOT NULL,
    completed ENUM('NOT_STARTED', 'COMPLETED') NOT NULL,
    CONSTRAINT fk_week FOREIGN KEY (week_id) REFERENCES week(id) ON DELETE CASCADE,
    INDEX idx_week_id (week_id)
);
