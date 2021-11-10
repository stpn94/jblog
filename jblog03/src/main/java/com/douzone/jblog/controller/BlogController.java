package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") // assets 제외한 문자는 받아라 : ? 있어도 되고 없어도 되고 , ! 제외
public class BlogController {

	@Autowired
	BlogService blogService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PostService postService;

	@Autowired
	FileUploadService fileUploadService;

	@GetMapping({ "", "{pathNo1}", "{pathNo1}/{pathNo2}" })
	public String index(
			@PathVariable("id") String id, 
			@PathVariable("pathNo1") Optional<Long> pathNo1, 
			@PathVariable("pathNo2") Optional<Long> pathNo2, 
			Model model) {

		/* 비회원 일때는 id가 guest로 온다. */
		if (id.equals("guest")) {
			String title = "안녕하세요. 비회원 페이지 입니다. ";
			String contents = "안녕하세요. 비회원 내용 입니다. ";
			model.addAttribute("title", title);
			model.addAttribute("contents", contents);
			System.out.println("Enter the Non User");
			return "blog/main";
		}

		//--------------------------------------------------

		Long categoryNo = 0L;
		Long postNo = 0L;

		/* When Click post */
		/* pathNo2.isPresent() = Post is exist */
		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
			//Put data in postVo
			PostVo postVo = postService.getPost(id, postNo);

			/* When postVo isNull */
			if (postVo == null) {
				System.out.println("[post is null] so Go to main");
				return "blog/main";
			} else {
				//Put currentPost and postList at model 
				model.addAttribute("title", postVo.getTitle());
				model.addAttribute("contents", postVo.getContents());
				model.addAttribute("postList", postService.getPosts(id, categoryNo));
			}
		} else if (pathNo1.isPresent()) {
			/* When Click Category */
			categoryNo = pathNo1.get();
			System.out.println("categoryNo====>>"+ categoryNo);

			/* If postList is empty */
			if (postService.getTopPost(id, categoryNo) == null) {
				System.out.println("categoryNo====>>"+ categoryNo+"에 아무 게시글이 없음");
			} else {
				// Get topCategory, topPost
//				CategoryVo categoryVo = categoryService.getTopCategory(id);
				PostVo postVo = postService.getTopPost(id, categoryNo);

				//Put currentPost and postList at model 
				model.addAttribute("title", postVo.getTitle());
				model.addAttribute("contents", postVo.getContents());
				model.addAttribute("postList", postService.getPosts(id, categoryNo));
			}

		}
		/* 처음 입장 */
		else {
			//id로 제일 최신 카테고리 가져오기
			CategoryVo categoryVo = categoryService.getTopCategory(id);
			System.out.println(id);
			System.out.println(categoryVo.getNo());
			//id로 제일 상단 카테고리의 포스트 불러오기
			PostVo postVo = postService.getTopPost(id, categoryVo.getNo());
			System.out.println(postVo);
			if (postVo == null) {
				return "blog/main";
			} else {
				model.addAttribute("title", postVo.getTitle());
				model.addAttribute("contents", postVo.getContents());
				model.addAttribute("postList", postService.getPosts(id, categoryVo.getNo()));
			}

		}

		List<CategoryVo> categoryList = categoryService.getLists(id);
		model.addAttribute("blogVo", blogService.getBlog(id));
		model.addAttribute("categoryList", categoryList);

		return "blog/main";
	}

	@Auth
	@GetMapping("admin/basic")
	public String adminBasic(
			@AuthUser UserVo authUser, 
			Model model) {

		//Get blogVo and add.
		model.addAttribute("blogVo", blogService.getBlog(authUser.getId()));
		return "blog/admin/basic";

	}

	@Auth
	@PostMapping("admin/basic")
	public String adminBasic(
			@RequestParam("file") MultipartFile file,
			@AuthUser UserVo authUser, 
			@ModelAttribute BlogVo blogVo) {

		String url = fileUploadService.restore(file, authUser.getId());

		blogVo.setId(authUser.getId());
		if (url == null || url.equals("null")) {
			blogVo.setLogo("default");
		} else {
			blogVo.setLogo(url);
		}
		blogService.update(blogVo);
		return "redirect:/" + authUser.getId();
	}

	@Auth
	@GetMapping("admin/category")
	public String adminCategory(
			@AuthUser UserVo authUser, 
			Model model) {
		
		model.addAttribute("blogVo", blogService.getBlog(authUser.getId()));

		List<CategoryVo> list = categoryService.getLists(authUser.getId());

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCount((int) categoryService.getCategoryCountByNo(authUser.getId(), list.get(i).getNo()));
		}

		model.addAttribute("categoryList", list);
		return "blog/admin/category";
	}

	@Auth
	@PostMapping("admin/delete")
	public JsonResult adminDelete(
			@AuthUser UserVo authUser, 
			@PathVariable("no") Long no) {

		Long data = 0L;

		data = no;
		return JsonResult.success(data);
	}

	@Auth
	@GetMapping("admin/write")
	public String adminWrite(
			@AuthUser UserVo authUser, 
			Model model, 
			@ModelAttribute PostVo postVo,
			@ModelAttribute BlogVo blogVo) {

		model.addAttribute("blogVo", blogService.getBlog(authUser.getId()));
		model.addAttribute("categoryList", categoryService.getLists(authUser.getId()));
		return "blog/admin/write";
	}

	@Auth
	@PostMapping("admin/write")
	public String adminWrite(
			@AuthUser UserVo authUser, 
			@ModelAttribute @Valid PostVo postVo, 
			@ModelAttribute(value = "category") Long no,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/write";
		}
		System.out.println(postVo.getTitle());
		System.out.println(postVo.getContents());
		System.out.println(postVo.getNo());
		System.out.println(postVo.getCategoryNo());
		System.out.println("categoryNo" + no);
		postVo.setCategoryNo(no);
		
		
		
		postService.insert(postVo);
		
		
		return "redirect:/" + authUser.getId();
	}

	
}
