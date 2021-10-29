package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {


	@Autowired
	private SqlSession sqlSession;


//	@Autowired
//	CategoryService categoryService;
//	public void insertUser(UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, PostVo postVo) {
//		sqlSession.insert("user.insert", userVo);
//		sqlSession.insert("blog.insert", blogVo);
//		sqlSession.insert("category.insert", categoryVo);
//
//		CategoryVo categoryVoOne = categoryService.findByOne(userVo.getId());
//		postVo.setCategory_no(categoryVoOne.getNo());
//		
//		sqlSession.insert("post.insert", postVo);
//	}
	
	public void insert(UserVo userVo) {
		sqlSession.insert("user.insert", userVo);
	}

	public UserVo getUser(String id) {
		return sqlSession.selectOne("user.findById", id);
	}

	public UserVo getUser(String id, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("i", id);
		map.put("p", password);
		return sqlSession.selectOne("user.findByIdAndPassword", map);

	}

}
