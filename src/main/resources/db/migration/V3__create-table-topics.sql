CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    author_id BIGINT NOT NULL,
    status VARCHAR(15),
    views INT,
    FOREIGN KEY (author_id) REFERENCES users_profiles(id)
);