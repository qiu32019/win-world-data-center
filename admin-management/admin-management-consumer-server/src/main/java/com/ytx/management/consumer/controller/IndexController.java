package com.ytx.management.consumer.controller;

import com.ytx.management.consumer.controller.anotation.PermissionLimit;
import com.ytx.management.consumer.utils.CookieUtil;
import com.ytx.management.consumer.utils.JacksonUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {


	@RequestMapping("/")
	public String index(Model model) {

//		Map<String, Object> dashboardMap = xxlJobService.dashboardInfo();
//		model.addAllAttributes(dashboardMap);

//		return "index";
		return "redirect:/jobgroup";
	}

	@RequestMapping("/toLogin")
	@PermissionLimit(limit=false)
	public String toLogin(HttpServletRequest request, HttpServletResponse response) {
		/*if (loginService.ifLogin(request, response) != null) {
			return "redirect:/";
		}*/
		return "login";
	}

	@RequestMapping(value="login", method= RequestMethod.POST)
	@ResponseBody
	@PermissionLimit(limit=false)
	public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember){
		boolean ifRem = (ifRemember!=null && ifRemember.trim().length()>0 && "on".equals(ifRemember))?true:false;
		if("admin".equals(userName) && "123456".equals(password)){
			String tokenJson = JacksonUtil.writeValueAsString(userName+password);
			String tokenHex = new BigInteger(tokenJson.getBytes()).toString(16);
			CookieUtil.set(response, "LOGIN_IDENTITY_KEY", tokenHex, ifRem);
			return ReturnT.SUCCESS;
		}
		return ReturnT.FAIL;
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
