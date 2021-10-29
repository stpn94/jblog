select user(), database();

-- USER
-- insert user
-- INSERT
--   INTO user 
-- VALUES (#{id} ,#{name} ,#{password})
INSERT
  INTO user (id,name,password)
VALUES ('id', 'name', '123'),
	   ('id1', 'name1', '123'),
       ('id2', 'name2', '123'),
       ('id3', 'name3', '123'),
       ('id4', 'name4', '123'),
       ('id5', 'name5', '123');

-- findById
-- SELECT id, name, password, join_date 
--   FROM user 
--  WHERE 1 = 1
SELECT id, name, password, join_date 
  FROM user
 WHERE 1 = 1
   AND ID = 'id';

-- findByIdAndPassword
-- SELECT id, name , password 
--   FROM user  
--  WHERE 1 = 1 
--    AND id = #{i} 
--    AND password = #{p}
SELECT id, name , password 
  FROM user  
 WHERE 1 = 1 
   AND id = 'id'
   AND password = '123';

-- /USER

-- BLOG
-- INSERT
--   INTO blog (user_id, title, logo)
-- VALUES (#{id}, #{title}, #{logo})
INSERT
  INTO blog (user_id, title, logo)
VALUES ('id', 'title', '/logo'),
	   ('id1', 'title1', '/logo'),
       ('id2', 'title2', '/logo'),
       ('id3', 'title3', '/logo'),
       ('id4', 'title4', '/logo'),
       ('id5', 'title5', '/logo');

-- SELECT id , title , logo
--   FROM blog
--  WHERE id = #{id}
SELECT user_id, title, logo
  FROM blog
 WHERE user_id='id';

-- UPDATE blog 
--    SET title= #{title}, logo = #{logo}
--  WHERE user_id = #{id}
UPDATE blog
   SET title='title', logo='/logo' 
 WHERE user_id= 'id';
-- /BLOG