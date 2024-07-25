CREATE TABLE topic_X_tag (
    topic_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (topic_id, tag_id),
    FOREIGN KEY (topic_id) REFERENCES topics(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);