INSERT INTO posts (name, text_, image_path, tags, like_count)
VALUES
    ('Title 1', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', null, '{"tag1", "tag2"}', 1),
    ('Title 2', 'Text 2', null, '{"tag2", "tag3"}', 2),
    ('Title 3', 'Text 3', null, '{"tag5", "tag4"}', 3),
    ('Title 4', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', null, '{"tag6", "tag5"}', 0),
    ('Title 5', 'Text 5', null, '{"tag7", "tag6"}', 1),
    ('Title 6', 'Text 6', null, '{"tag8", "tag7"}', 2),
    ('Title 7', 'Text 7', null, '{"tag9", "tag8"}', 3),
    ('Title 8', 'Text 8', null, '{"tag10", "tag1"}', 4),
    ('Title 9', 'Text 9', null, '{"tag1", "tag2"}', 1),
    ('Title 10', 'Text 10', null, '{"tag2", "tag3"}', 2),
    ('Title 11', 'Text 11', null, '{"tag3", "tag4"}', 3),
    ('Title 12', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', null, '{"tag4", "tag5"}', 4),
    ('Title 13', 'Text 13', null, '{"tag5", "tag1"}', 5),
    ('Title 14', 'Text 14', null, '{"tag6", "tag2"}', 6),
    ('Title 15', 'Text 15', null, '{"tag7", "tag3"}', 7),
    ('Title 16', 'Text 16', null, '{"tag8", "tag4"}', 0),
    ('Title 17', 'Text 17', null, '{"tag9", "tag5"}', 1),
    ('Title 18', 'Text 18', null, '{"tag10", "tag1"}', 2),
    ('Title 19', 'Text 19', null, '{"tag1", "tag2"}', 3),
    ('Title 20', 'Text 20', null, '{"tag2", "tag4"}', 4),
    ('Title 21', 'Text 21', null, '{"tag3", "tag5"}', 5),
    ('Title 22', 'Text 22', null, '{"tag4", "tag6"}', 5);

INSERT INTO post_comments (post_id, created_at, text_)
VALUES
    (1, '2025-01-01 10:10:10', 'Small comment'),
    (1, '2025-01-02 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'),
    (2, '2025-01-03 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'),
    (3, '2025-01-04 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'),
    (4, '2025-01-05 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'),
    (5, '2025-01-06 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'),
    (6, '2025-01-07 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'),
    (7, '2025-01-08 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'),
    (2, '2025-01-09 10:10:10', 'The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.');