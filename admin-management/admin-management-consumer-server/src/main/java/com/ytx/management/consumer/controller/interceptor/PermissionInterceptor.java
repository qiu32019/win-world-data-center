package com.ytx.management.consumer.controller.interceptor;

import com.ytx.management.consumer.controller.anotation.PermissionLimit;
import com.ytx.management.consumer.controller.model.User;
import com.ytx.management.consumer.utils.CookieUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截
 *
 * @author xuxueli 2015-12-12 18:09:04
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {

//	@Resource
//	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}

		// if need login
		boolean needLogin = true;
		boolean needAdminuser = false;
		HandlerMethod method = (HandlerMethod)handler;
		PermissionLimit permission = method.getMethodAnnotation(PermissionLimit.class);
		if (permission!=null) {
			needLogin = permission.limit();
			needAdminuser = permission.adminuser();
		}

		if (needLogin) {
			String cookieToken = CookieUtil.getValue(request, "LOGIN_IDENTITY_KEY");
			if(StringUtils.isEmpty(cookieToken)){
				response.sendRedirect(request.getContextPath() + "/toLogin");
			}
			User user = new User();
			user.setRole(1);
			user.setUsername("admin");
			user.setPermission("");
			request.setAttribute("LOGIN_IDENTITY_KEY", user);

			/*XxlJobUser loginUser = loginService.ifLogin(request, response);
			if (loginUser == null) {
				response.sendRedirect(request.getContextPath() + "/toLogin");
				//request.getRequestDispatcher("/toLogin").forward(request, response);
				return false;
			}
			if (needAdminuser && loginUser.getRole()!=1) {
				throw new RuntimeException(I18nUtil.getString("system_permission_limit"));
			}
			request.setAttribute(LoginService.LOGIN_IDENTITY_KEY, loginUser);*/
		}

		return super.preHandle(request, response, handler);
	}
	
}
