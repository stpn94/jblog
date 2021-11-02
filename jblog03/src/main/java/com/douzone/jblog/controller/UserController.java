package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	/* 회원가입 폼 */
	@GetMapping("user/join")
	public String join(@ModelAttribute UserVo userVo) {
		return "/user/join";
	}
	
	/* 회원가입 */
	@PostMapping("user/join")
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		String blogId = vo.getId();
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		//id가 guest면 안됨
		if (blogId.equals("guest")) {
			return "user/join";
		}
		
		userService.join(vo);
		
		return "redirect:/user/joinsuccess";
	}

	/* 회원가입 완료 */
	@GetMapping("user/joinsuccess")
	public String joinsuccess() {
		return "/user/joinsuccess";
	}

	/* 로그인 */
	@GetMapping("user/login")
	public String login() {
		return "/user/login";
	}

}
