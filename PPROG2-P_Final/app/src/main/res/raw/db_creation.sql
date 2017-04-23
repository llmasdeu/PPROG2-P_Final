CREATE TABLE users(
    _id INT UNIQUE NOT NULL PRIMARY KEY,
    name VARCHAR(255) DEFAULT '',
    surname VARCHAR(255) DEFAULT '',
    email VARCHAR(255) DEFAULT '',
    password VARCHAR(255) DEFAULT '',
    gender VARCHAR(6) DEFAULT 'other',
    description varchar(255) DEFAULT ''
);