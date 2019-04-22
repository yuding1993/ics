package org.wheport.ics.service;

import org.wheport.ics.domain.bo.ReturnModel;

public interface ActionInfoService {

    ReturnModel turnToQp();

    ReturnModel getSuffixUrl(Integer id);

    ReturnModel getActionUrlById(Integer id);
}
