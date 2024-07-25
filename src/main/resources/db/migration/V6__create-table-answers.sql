CREATE TABLE answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    author_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    assessment VARCHAR(15),
    FOREIGN KEY (author_id) REFERENCES users_profiles(id),
    FOREIGN KEY (topic_id) REFERENCES topics(id)
);