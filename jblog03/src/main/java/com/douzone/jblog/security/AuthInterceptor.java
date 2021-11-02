package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws Exception {
		System.out.println("AuthInterceptor[들렸음]");
		// 1. handler 종류 확인 
		if (handler instanceof HandlerMethod == false) {
			System.out.println("AuthInterceptor[아무거도안함]");
			// DefaultServletHandler 처리하는 경우 ( 정적 자원 접근)
			return true;
		}
		
		//2. casting(업케스팅 해야지 HandlerMethod에 있는 getMethodAnnotation 쓸수 있지.)
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method의 @Auth 받아오기
		// getMethodAnnotation이 Auth클레스가 있는지 확인
		/* {여기에 @Auth 있는지 확인}
		* public String [메서드](HttpSession session, Model model) {return "board/write";} */
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Handler Method에 @Auth가 없으면 Type에 있는지 확인
		/* {여기에 @Auth 있는지 확인}
		* public class [클래스]{} */
		if (auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		//5. Type이나 Method 둘다 @Auth가 적용이 안되어 있는 경우
		/* 3번과 4번이 모두 없을때. */
		if (auth == null) {
			return true;
		}

		//6 . @Auth 가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		/* 여기서 확인해버리기 */
		HttpSession session = request.getSession();

		if (session == null) {
			System.out.println("session이 null이라서 ==>> [/user/login]으로");
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			System.out.println("authUser가 null이라서 ==>> [/user/login]으로");
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

//		// 7. admin일 경우
//		// 권한(Authorization) 체크를 위해서 @Auth의 role 가져오기 ("USER","ADMIN")
//		String role = auth.role().toString();
//		String[] divId = request.getRequestURI().toString().split("/");	
//		String id = divId[2];
//		/* (6번 결과와 7번결과를 비교하기) */
//		//		과제
//		System.out.println("AuthInterceptor : " + role + "작업중");
//			// admin임을 알 수 있는 조건을 작성한다.
//			// ex) 서비스의 id가 root이면 admin이다.
//			if( id.equals(authUser.getId()) == false){   // admin이 아니므로 return false
//				response.sendRedirect(request.getContextPath());
//				return false;
//		}
		System.out.println("AuthInterceptor[아무거도안함]");
		return true;
	}

}
