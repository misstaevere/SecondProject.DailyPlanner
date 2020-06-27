CREATE DATABASE IF NOT EXISTS plannerdb;
use plannerdb;

CREATE TABLE user (
    user_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE task (
    task_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fk_user_ID INT NOT NULL,
    task_name VARCHAR(100) NOT NULL,
    task_date DATE NOT NULL,
    task_time TIME NOT NULL,
    task_location VARCHAR(100),
    FOREIGN KEY (fk_user_ID) REFERENCES user (user_ID)
    );
    
    show tables;