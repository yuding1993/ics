package org.wheport.ics.service.impl;

import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.wheport.ics.dao.ActionInfoMapper;
import org.wheport.ics.domain.EnumConstants;
import org.wheport.ics.domain.bo.ReturnModel;
import org.wheport.ics.domain.pojo.ActionInfo;
import org.wheport.ics.service.ActionInfoService;

import java.net.URLEncoder;
import java.util.Map;

@Service
public class ActionInfoServiceImpl implements ActionInfoService {

    @Autowired
    private ActionInfoMapper actionInfoMapper;

    @Override
    public ReturnModel turnToQp(Integer actionId) {
        ReturnModel result = new ReturnModel();

        Assertion assertion = AssertionHolder.getAssertion();
        if(null != assertion){
            // 获取用户信息
            Map<String, Object> attrs = assertion.getPrincipal().getAttributes();
            String loginType = (String) attrs.get("login_type");
            if(null != loginType && "1".equals(loginType)){//1为IC卡登陆 0为用户名密码登陆
                String loginName = (String) attrs.get("login_name");
                String icPwd = (String) attrs.get("ic_pwd");

                //计算id值
                String str = loginName + icPwd;
                int num = 0;
                for (int i = 0; i < str.length(); i++){
                    num = (0x1f * num) + str.charAt(i);
                }

                String id= Integer.toHexString(num).toLowerCase();
                String service = "http://quickpass.chinaport.gov.cn/NSPCUpdate/CnEport.SuperPass.UIModule.Eport.application?id="+id;

                service = URLEncoder.encode(service);

                ActionInfo actionInfo = new ActionInfo();
                actionInfo.setId(actionId);
                actionInfo = actionInfoMapper.selectByPrimaryKey(actionInfo);

                String resultUrl = actionInfo.getActionLinkUrl() + service;

                result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
                result.setObj(resultUrl);
            }else{
                result.setCode(EnumConstants.RETURN_FAIL.getCode());
            }
        }else{
            result.setCode(EnumConstants.RETURN_FAIL.getCode());
        }
        return result;
    }

    @Override
    public ReturnModel getSuffixUrl(@RequestParam("id") Integer id) {
        ReturnModel result = new ReturnModel();

        Assertion assertion = AssertionHolder.getAssertion();
        if(null != assertion){
            ActionInfo actionInfo = new ActionInfo();
            actionInfo.setId(id);
            actionInfo = actionInfoMapper.selectByPrimaryKey(actionInfo);

            // 获取用户信息
            Map<String, Object> attrs = assertion.getPrincipal().getAttributes();
            //login_name
            String loginName = (String) attrs.get(actionInfo.getSuffixParamName());

            String resultUrl = actionInfo.getActionLinkUrl() + loginName;

            result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
            result.setObj(resultUrl);
        }else{
            result.setCode(EnumConstants.RETURN_FAIL.getCode());
        }

        return result;
    }

    @Override
    public ReturnModel getActionUrlById(@RequestParam("id") Integer id) {
        ReturnModel result = new ReturnModel();

        ActionInfo actionInfo = new ActionInfo();
        actionInfo.setId(id);
        actionInfo = actionInfoMapper.selectByPrimaryKey(actionInfo);

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(actionInfo.getActionLinkUrl());

        return result;
    }
}
