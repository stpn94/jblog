package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public double findAllCount() {
		return sqlSession.selectOne("category.findAllCount");
	}

	public boolean delete(Long no) {
		return sqlSession.selectOne("category.delete", no);
	}

	public List<CategoryVo> findByList(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return sqlSession.selectList("category.findByList", map);

	}

	public CategoryVo findByNo(String id, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("id", id);
		return sqlSession.selectOne("category.findByNo", map);

	}

	public double findCountByPostNo(String id, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("id", id);
		return sqlSession.selectOne("category.findCountByPostNo", map);
	}

	public CategoryVo findByOne(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return sqlSession.selectOne("category.findByOne", map);
	}

}
