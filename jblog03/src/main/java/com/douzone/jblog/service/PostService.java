package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired 
	PostRepository postRepository;
	
	public void insert(PostVo vo) {	
		postRepository.insert(vo);				
	}

	public List<PostVo> getPosts(String id , Long no) {
		return postRepository.findByList(id , no);
	}
	
	public PostVo getPost(String id , Long no) {
		return postRepository.findByNo(id , no);
	}

	/* findTopPost by Userid,categroyNo*/
	public PostVo getTopPost(String id , Long no) {
		return postRepository.findByOne( id ,  no);
	}
}
