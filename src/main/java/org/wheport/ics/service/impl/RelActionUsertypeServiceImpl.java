package org.wheport.ics.service.impl;

import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheport.ics.dao.RelActionUsertypeMapper;
import org.wheport.ics.domain.EnumConstants;
import org.wheport.ics.domain.bo.ReturnModel;
import org.wheport.ics.domain.pojo.RelActionUsertype;
import org.wheport.ics.service.RelActionUsertypeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class RelActionUsertypeServiceImpl implements RelActionUsertypeService {

    @Autowired
    private RelActionUsertypeMapper relActionUsertypeMapper;

    @Override
    public ReturnModel checkUserType(Integer id) {
        ReturnModel result = new ReturnModel();

        RelActionUsertype relActionUsertype = new RelActionUsertype();
        relActionUsertype.setActionId(id);
        List<RelActionUsertype> relList = relActionUsertypeMapper.select(relActionUsertype);
        if (null != relList && relList.size() > 0) {
            Assertion assertion = AssertionHolder.getAssertion();
            if (null != assertion) {
                Map<String, Object> attrs = assertion.getPrincipal().getAttributes();
                String userType = (String) attrs.get("app_type");//这里的app_type值是被逗号分隔开的
                List<String> userTypeList = new ArrayList<>();
                if (null != userType && !"".equals(userType)) {
                    userTypeList = Arrays.asList(userType.split(","));
                }

                boolean hasAuth = false;
                out:
                for (RelActionUsertype rel : relList) {
                    if (null != userTypeList && userTypeList.size() > 0) {
                        for (String thisUserType : userTypeList) {
                            if (rel.getUsertypeId().equals(thisUserType)) {
                                hasAuth = true;
                                break out;
                            }
                        }
                    }
                }
                if (hasAuth) {
                    result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
                } else {
                    result.setCode(EnumConstants.RETURN_FAIL.getCode());
                }
            } else {
                result.setCode(EnumConstants.RETURN_FAIL.getCode());
            }
        } else {
            result.setCode(EnumConstants.RETURN_SUCCESS.getCode());

        }
        return result;
    }
}
