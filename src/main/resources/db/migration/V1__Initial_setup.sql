CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    telegram_id BIGINT UNIQUE NOT NULL,
    username VARCHAR(50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--CREATE TABLE Runs (
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    user_id INT,
--    distance DECIMAL(5, 2) NOT NULL, -- Дистанция в километрах
--    duration TIME NOT NULL,           -- Время в формате 'HH:MM:SS'
--    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
--);
