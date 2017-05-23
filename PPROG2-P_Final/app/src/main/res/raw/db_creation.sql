CREATE TABLE users(
    _id INT PRIMARY KEY,
    name VARCHAR(255) DEFAULT '',
    surname VARCHAR(255) DEFAULT '',
    username VARCHAR(255) DEFAULT '',
    email VARCHAR(255) DEFAULT '',
    password VARCHAR(255) DEFAULT '',
    gender VARCHAR(6) DEFAULT 'other',
    description VARCHAR(255) DEFAULT '',
    profile_picture VARCHAR(255) DEFAULT ''
);

CREATE TABLE searches(
    _id INT PRIMARY KEY,
    search VARCHAR(255) DEFAULT ''
);

