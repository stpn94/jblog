package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;

@RestController("categoryControllerApi")
@RequestMapping("/{id:(?!assets).*}/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/admin/category")
	public JsonResult read(@PathVariable("id") String id) {
		List<CategoryVo> list = categoryService.getAllCategory(id);
		return JsonResult.success(list);
	}

	@PostMapping("/admin/category")
	public JsonResult post(
			@PathVariable("id") String id, 
			@RequestBody CategoryVo vo) {
		vo.setId(id);
		categoryService.insertCategory(vo);
		return JsonResult.success(vo);
	}

	@DeleteMapping("/admin/delete/{no}")
	public JsonResult delete(
			@PathVariable("no") Long no) {
		System.out.println("-------" + no);
		categoryService.delete(no);
		return JsonResult.success(no);
	}

}
