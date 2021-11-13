package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}

	public List<PostVo> findByList(String id, Long no) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);

		return sqlSession.selectList("post.findByList", map);
	}

	public PostVo findByNo(String id, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);
		return sqlSession.selectOne("post.findByNo", map);
	}
	
	public PostVo findByOne(String id, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);
		return sqlSession.selectOne("post.findByOne" , map);
	}
	
	public void deletePost(Long categoryNo) {
		sqlSession.delete("post.deletePost", categoryNo);
	}

}
