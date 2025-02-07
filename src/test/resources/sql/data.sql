DELETE FROM post_comments;
DELETE FROM posts;

INSERT INTO posts (id, name, text_, image_name, tags, like_count)
VALUES
    (1, 'Title 1', 'Text 1', null, '{"tag1", "tag2"}', 0),
    (2, 'Title 2', 'Text 2', null, '{"tag1", "tag3"}', 1),
    (3, 'Title 3', 'Text 3', null, '{"tag1", "tag4"}', 2),
    (4, 'Title 4', 'Text 4', null, '{"tag1", "tag5"}', 3),
    (5, 'Title 5', 'Text 5', null, '{"tag1", "tag6"}', 4);
SELECT SETVAL('posts_id_seq', (SELECT MAX(id) FROM posts));

INSERT INTO post_comments (id, post_id, created_at, text_)
VALUES
    (1, 1, '2025-01-01 00:00:00', 'Small comment 1'),
    (2, 2, '2025-01-01 00:00:00', 'Small comment 2'),
    (3, 2, '2025-01-01 00:00:00', 'Small comment 3'),
    (4, 3, '2025-01-01 00:00:00', 'Small comment 4'),
    (5, 4, '2025-01-01 00:00:00', 'Small comment 5');
SELECT SETVAL('post_comments_id_seq', (SELECT MAX(id) FROM post_comments));
