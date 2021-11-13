package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;

	public void insertCategory(CategoryVo vo) {
		categoryRepository.insert(vo);
	}

	public double getCategoryCount() {
		return categoryRepository.findAllCount();
	}

	public void delete(Long no) {
		//포스트 지우고
		System.out.println(no);
		postRepository.deletePost(no);
		categoryRepository.delete(no); 
		
	}

	public List<CategoryVo> getLists(String id) {
		return categoryRepository.findByList(id);
	}

	public CategoryVo getCategory(String id, Long no) {
		return categoryRepository.findByNo(id, no);
	}

	public double getCategoryCountByNo(String id, Long no) {
		return categoryRepository.findCountByPostNo(id, no);
	}

	public List<CategoryVo> getAllCategory(String id) {
		List<CategoryVo> list = categoryRepository.findAllCategory(id);
		//		int countOfCategory = blogRepository.countOfCategory();
		return list;
	}

	/* getTopCategory by id */
	public CategoryVo getTopCategory(String id) {
		return categoryRepository.findByOne(id);
	}

}
