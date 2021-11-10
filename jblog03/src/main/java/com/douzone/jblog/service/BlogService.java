package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

//	public void insertBlog(BlogVo vo) {
//		blogRepository.insertBlog(vo);
//	}
//
	public void update(BlogVo vo) {
		blogRepository.update(vo);
	}

	public BlogVo getBlog(String id) {
		return blogRepository.findById(id);
	}

}
