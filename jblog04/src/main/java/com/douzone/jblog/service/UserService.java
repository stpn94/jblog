package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public void join(UserVo vo) {
		//Create user
		userRepository.insert(vo);
		//Create default-blog
		blogRepository.insert(vo.getId());
		//Create default-Category
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(vo.getId());
		categoryVo.setName("구분없음");
		categoryVo.setDesc("기본 카테고리입니다.");
		categoryRepository.insert(categoryVo);
	}
	
	//로그인
	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}
	
	//Ajax iD duplication Check
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}


}