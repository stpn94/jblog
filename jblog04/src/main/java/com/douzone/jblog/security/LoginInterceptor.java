package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.out.println("[LoginIntercepter]들렸음");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// 새로운 객체 생성하지 말고 주입받은거 가져오기

		UserVo authUser = userService.getUser(id, password);

		// 비밀번호가 틀리면(가져올 객체가 앖으면) login.jsp로
		if (authUser == null) {

			request.setAttribute("id", id);
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);

			System.out.println("[LoginIntercepter] : " + authUser + " === Id,비밀번호 확인실패 로그인 폼으로");

			return false;
		}

		// session 처리

		// session 에 authUser 넣는다.
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);

		System.out.println("[LoginIntercepter] : " + authUser + " === Id,비밀번호 확인하고 session처리 완료");
		response.sendRedirect(request.getContextPath());
		return false;
	}

}
