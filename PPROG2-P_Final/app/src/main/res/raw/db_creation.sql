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

CREATE TABLE favorites(
    _id INT PRIMARY KEY,
    name_restaurant VARCHAR(255),
    address_restaurant VARCHAR(1000),
    rate_restaurant VARCHAR(255),
    type_restaurant VARCHAR(255),
    open_restaurant VARCHAR(255),
    close_restaurant VARCHAR(255),
    username_user VARCHAR(255)
)

