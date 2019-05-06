package org.wheport.ics.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.wheport.ics.config.ConfigurationConfig;
import org.wheport.ics.domain.EnumConstants;
import org.wheport.ics.domain.bo.ReturnModel;
import org.wheport.ics.service.LoginService;
import org.wheport.ics.util.AccessServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {

    Log log = LogFactory.getLog(this.getClass());

    @Value("${cas.client-logout-url}")
    private String casLogoutUrl;

    @Value("${cas.client-host-url}")
    private String casClientHostUrl;

    @Override
    public ReturnModel isLogin() {
        ReturnModel result = new ReturnModel();

        Map<String,Object> resultMap = new HashMap<String,Object>(16);

        boolean isLogin = false;
        String account = "";
        String userId = "";
        Assertion assertion = AssertionHolder.getAssertion();
        if(null != assertion){
            //获取用户名
            account = assertion.getPrincipal().getName();
            if(null != account){
                isLogin = true;
            }
            Map<String, Object> attrs = assertion.getPrincipal().getAttributes();
            userId = (String) attrs.get("uid");
        }
        resultMap.put("isLogin", isLogin);
        resultMap.put("userName", account);
        resultMap.put("userId", userId);

        result.setObj(resultMap);
        System.out.print("lbs0822:======> "+result);
        return result;
    }

    @Override
    public ReturnModel checkIsIcLogin() {
        ReturnModel result = new ReturnModel();

        Assertion assertion = AssertionHolder.getAssertion();
        if(null != assertion){
            // 获取用户信息
            Map<String, Object> attrs = assertion.getPrincipal().getAttributes();
            String loginType = (String) attrs.get("login_type");
            if(null != loginType && "1".equals(loginType)){//1为IC卡登陆 0为用户名密码登陆
                result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
            }else{
                result.setCode(EnumConstants.RETURN_FAIL.getCode());
            }
        }else{
            result.setCode(EnumConstants.RETURN_FAIL.getCode());
        }

        return result;
    }

    @Override
    public String openServiceReturnParam(@RequestParam("serviceUrl") String serviceUrl, @RequestParam("moduleId") String moduleId, @RequestParam("nameSpace") String nameSpace, @RequestParam("method") String method) {
        String resultUrl = "";

        Assertion assertion = AssertionHolder.getAssertion();
        if(null != assertion){
            // 获取用户信息
            Map<String, Object> attrs = assertion.getPrincipal().getAttributes();

            String entId = (String) attrs.get("org_code");//企业机构代码
            String authType = "";
            String accountId = "";//若账号密码登陆，则取国检邮箱，若卡登陆，则取卡号

            String loginName = (String) attrs.get("login_name");//若是ic卡登陆，则此为ic卡号
            String login_type = (String) attrs.get("login_type");//登陆类型  | 认证类别

            if(null != login_type && "0".equals(login_type)){//账号密码登陆
                authType = "2";
                accountId = (String) attrs.get("aqsiq_box");
            }else if(null != login_type && "1".equals(login_type)){//卡登陆
                authType = "1";
                accountId = loginName;
            }

            String param = entId + "," + authType + "," + accountId + "," + moduleId;

            resultUrl = AccessServer.startAccess(serviceUrl, param, nameSpace, method);
        }

        return resultUrl;
    }

    @Override
    public ModelAndView redirect(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            String uri = request.getRequestURI();
            log.info("uri:" + uri);
            String path = request.getContextPath();
            //uri = uri.substring(0, path.length()) + uri.substring(path.length()*2+4,uri.length());//4是/sec的长度
            uri = path + uri.substring(path.length() * 2 + 4, uri.length());
            log.info("uri:" + uri + "---path:" + path);

            Map<String, String[]> map = request.getParameterMap();
            Set<Map.Entry<String, String[]>> set = map.entrySet();
            Iterator<Map.Entry<String, String[]>> it = set.iterator();
            boolean isFirst = true;
            while (it.hasNext()) {
                Map.Entry<String, String[]> entry = it.next();
                if (isFirst) {
                    uri = uri + "?" + entry.getKey() + "=";
                } else {
                    uri = uri + "&" + entry.getKey() + "=";
                }
                System.out.println("KEY:" + entry.getKey());
                for (String i : entry.getValue()) {
                    uri += i;
                    System.out.println(i);
                }
                isFirst = false;
            }

            mav.setView(new RedirectView(uri, false));
        } catch (Exception e) {
            log.error("redirect error cause{}", e);
        }
        return mav;
    }

    @Override
    public ModelAndView modifyUserInfoUrl(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(ConfigurationConfig.getProperty("modifyUserInfoUrl"), false));
        return mav;
    }

    @Override
    public ModelAndView modifyPasswordUrl(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(ConfigurationConfig.getProperty("modifyPasswordUrl"), false));
        return mav;
    }

    @Override
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        // 用户登出时将session无效化
        session.invalidate();
        mav.setView(new RedirectView(casLogoutUrl, false));
        return mav;
    }

    @Override
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        // 登录成功返回至主页
        mav.setView(new RedirectView(casClientHostUrl, false));
        return mav;
    }

    @Override
    public ModelAndView register(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(ConfigurationConfig.getProperty("registerUrl"), false));
        return mav;
    }
}
