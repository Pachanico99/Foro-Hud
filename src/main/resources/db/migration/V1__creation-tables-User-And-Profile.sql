CREATE TABLE users_profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(300) NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    role VARCHAR(20),
    q_prestige INT,
    profile_id BIGINT,
    FOREIGN KEY (profile_id) REFERENCES users_profiles(id)
);