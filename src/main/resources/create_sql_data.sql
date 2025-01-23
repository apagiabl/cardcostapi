CREATE DATABASE IF NOT EXISTS cardcostapi;
CREATE TABLE IF NOT EXISTS cardcostapi.clearing_cost_matrix (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_code VARCHAR(2) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL);
