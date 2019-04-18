package org.wheport.ics.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wheport.ics.service.LoginService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/sec")
public class SecController {

    @Autowired
    private LoginService loginServiceImpl;

    /**
     * 重定向登录成功后的地址
     */
    @RequestMapping(value = "/**/*.html", method = RequestMethod.GET)
    public ModelAndView redirect(HttpServletRequest request) {
        return loginServiceImpl.redirect(request);
    }

    /**
     * 重定向企业中心页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyUserInfoUrl", method = RequestMethod.GET)
    public ModelAndView modifyUserInfoUrl(HttpServletRequest request) {
        return loginServiceImpl.modifyUserInfoUrl(request);
    }

    /**
     * 重定向密码修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyPasswordUrl", method = RequestMethod.GET)
    public ModelAndView modifyPasswordUrl(HttpServletRequest request) {
        return loginServiceImpl.modifyPasswordUrl(request);
    }

    /**
     * 重定向cas登出页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        return loginServiceImpl.logout(request);
    }

    /**
     * 登录跳转
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        return loginServiceImpl.login(request);
    }
}
