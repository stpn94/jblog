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
VALUES ('id', 'title', '/assets/images/spring-logo.jpg'),
	   ('id1', 'title1', '/assets/images/spring-logo.jpg'),
       ('id2', 'title2', '/assets/images/spring-logo.jpg'),
       ('id3', 'title3', '/assets/images/spring-logo.jpg'),
       ('id4', 'title4', '/assets/images/spring-logo.jpg'),
       ('id5', 'title5', '/assets/images/spring-logo.jpg');
delete from blog;
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

select * from category;

-- CATEGORY
-- <-- insert category -->
-- 	INSERT 
-- 	  INTO category (blog_id, name, desc)
-- 	VALUES (null , #{name} ,#{desc} ,now() ,#{blog_id})
INSERT
  INTO category (blog_id, name, `desc`)
VALUES ('id', 'category' ,'id의 카테고리 1번.'),
	   ('id', 'category1' ,'id의 카테고리 2번.'),
       ('id', 'category2' ,'id의 카테고리 3번.'),
       ('id', 'category3' ,'id의 카테고리 4번.'),
       ('id', 'category4' ,'id의 카테고리 5번.');

-- <!-- delete category -->
-- 	DELETE
-- 	  FROM category
-- 	 WHERE = #{no}  
select * from category;

DELETE
  FROM category
 WHERE blog_id = 'id'
   AND no = 1 ;

-- <!-- findAllCount category -->
-- 	SELECT count(*) 
-- 	  FROM category
SELECT count(*) 
  FROM category;
  

-- <!-- findByOne Recently category -->
-- 	SELECT name , no
-- 	  FROM category
-- 	 WHERE 1=1
-- 	   AND blog_id = #{id}
-- 	 LIMIT 0 , 1

SELECT name , no
  FROM category
 WHERE 1=1
   AND blog_id = 'id'
 LIMIT 0 , 1;
 
SELECT c.no as no , p.title as title , p.contents as contents
  FROM user u , category c , post p
 WHERE 1 =1
   AND u.id = 'id'
   AND c.no = #{no}
 LIMIT 0 , 1;

-- <!-- findCountByPostNo category -->
-- 	SELECT count(*) 
-- 	  FROM category a , post b
-- 	 WHERE 1=1
-- 	   AND a.blog_id = #{id}
-- 	   AND b.category_no = a.no
-- 	   AND b.category_no = #{no}

SELECT count(*) 
  FROM category c , post p
 WHERE 1=1
   AND c.blog_id = 'id'
   AND p.category_no = c.no
   AND p.category_no = 1;

-- <!-- findByList category -->
-- 	SELECT a.no , a.name , a.desc, a.reg_date , a.blog_id
-- 	  FROM category a
-- 	 WHERE 1=1
-- 	   AND a.blog_id = #{id}
SELECT no , name , `desc`, blog_id
  FROM category
 WHERE 1=1
   AND blog_id = 'id';
   
-- <!-- findByNo category -->
-- 	SELECT a.no , a.name , a.desc , a.reg_date , a.blog_id
-- 	  FROM category a
-- 	 WHERE 1=1
-- 	   AND a.blog_id = #{id}
-- 	   AND a.no  = #{no}
SELECT no , name , `desc` , blog_id
  FROM category
 WHERE 1=1
   AND blog_id = 'id'
   AND no  = 82;
   
SELECT p.no as no, p.title as title, p.contents as contents, p.reg_date as regDate, p.category_no as categoryNo
  FROM post p , category c
 WHERE 1 = 1
   AND p.category_no = c.no
   AND c.blog_id = 'id'
   AND c.no = 82
   LIMIT 0 , 1;
-- /CATEGORY

-- POST

-- <insert id="insert" parameterType="postvo">	
-- 		INSERT
-- 		  INTO post (category_no, title, contents)
-- 		VALUES (null , #{title} ,#{contents} ,now() ,#{category_no})

INSERT
  INTO post (category_no, title, contents)
VALUES (82, 'title' , 'content'),
	   (82, 'title1' , 'content1'),
       (82, 'title2' , 'content2'),
       (82, 'title3' , 'content3'),
       (82, 'title4' , 'content4');

select * from post;


-- 	<select id="findByOne" resultType="postvo" parameterType="map">

-- 		SELECT b.no , c.title , c.contents
-- 		  FROM user a , category b , post c
-- 		 WHERE 1 =1
-- 		   AND a.id = #{id}
-- 		   AND b.no = #{no}
-- 		 LIMIT 0 , 1

SELECT u.id, c.no , p.title , p.contents
  FROM user u , category c , post p
 WHERE 1 =1
   AND u.id = 'id'
   AND c.no = '62'
 LIMIT 0 , 1;


-- 	<select id="findByList" parameterType="map" resultType="postvo">
-- 		SELECT a.no , a.title , a.contents, a.reg_date , a.category_no , b.blog_id
-- 		  FROM post a , category b
-- 		 WHERE 1=1
-- 		   AND b.blog_id=#{id}
-- 		   AND a.category_no=b.no
-- 		   AND a.category_no=#{no}

SELECT p.no , p.title , p.contents, p.reg_date , p.category_no , c.blog_id
  FROM post p , category c
 WHERE 1=1
   AND c.blog_id = 'id'
   AND p.category_no = c.no
   AND p.category_no = '11';

select * from post;
select * from category;

-- 	<select id="findByNo" parameterType="map" resultType="postvo">
-- 		SELECT a.no , a.title , a.contents, a.reg_date , a.category_no
-- 		  FROM post a , category b
-- 		 WHERE 1 = 1
-- 		   AND b.blog_id = #{id}
-- 		   AND a.category_no = b.no
-- 		   AND a.no = #{no}
SELECT p.no , p.category_no, p.title , p.contents, p.reg_date
  FROM post p , category c
 WHERE 1 = 1
   AND p.category_no = c.no
   AND c.blog_id = 'id'
   AND p.no = 1;
   
	SELECT c.noas no, c.name as name , c.desc, count(p.category_no) as count
	  FROM category c left JOIN post p
  	    ON c.no = p.category_no 
	 WHERE c.blog_id = 'id'
	 GROUP BY c.no;
   
DELETE
  FROM category
 WHERE no = 24 ;
delete from user where id LIKE CONCAT('%','id','%');
delete from blog where user_id LIKE CONCAT('%','id','%');
delete from category where blog_id = 'id';
delete from category where no = 10;
delete from post where category_no = 11;
select * from user;
select * from category;
select * from blog;
select * from post;