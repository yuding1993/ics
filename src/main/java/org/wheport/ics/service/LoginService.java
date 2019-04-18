package org.wheport.ics.service;

import org.springframework.web.servlet.ModelAndView;
import org.wheport.ics.domain.bo.ReturnModel;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    ReturnModel isLogin();

    ReturnModel checkIsIcLogin();

    String openServiceReturnParam(String serviceUrl, String moduleId, String nameSpace, String method);

    ModelAndView redirect(HttpServletRequest request);

    ModelAndView modifyUserInfoUrl(HttpServletRequest request);

    ModelAndView modifyPasswordUrl(HttpServletRequest request);

    ModelAndView logout(HttpServletRequest request);

    ModelAndView login(HttpServletRequest request);

    ModelAndView register(HttpServletRequest request);
}
