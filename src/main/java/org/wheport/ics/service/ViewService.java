package org.wheport.ics.service;

import org.springframework.web.multipart.MultipartFile;
import org.wheport.ics.domain.bo.ComplaintInfoModel;
import org.wheport.ics.domain.bo.ReturnModel;

import javax.servlet.http.HttpServletRequest;

public interface ViewService {

    /**
     * 查询首页
     * @return
     */
    ReturnModel queryFirstPage();

    /**
     * 查询公共资源
     * @return
     */
    ReturnModel queryResourcesCommon();

    /**
     * 查询本页面资源内容
     * @param moduleId 模块id
     * @return
     */
    ReturnModel queryIndexPageContent(Integer moduleId);

    /**
     * 查询资讯列表
     * @param catIdOne 资讯一级分类id
     * @return
     */
    ReturnModel queryPressCatList(Integer catIdOne);

    /**
     * 分页查询资讯
     * @param page 当前页数
     * @param parentId 一级父分类id
     * @param catId 二级父分类id
     * @return
     */
    ReturnModel pageQueryPressList(Integer page, Integer parentId, Integer catId);

    /**
     * 查询资讯详情
     * @param catIdOne 资讯一级分类id
     * @param pressId 资讯id
     * @return
     */
    ReturnModel queryPressDetail(Integer catIdOne, Integer pressId);

    /**
     * 查询问答
     * @return
     */
    ReturnModel queryQuestionAndAnswer();

    /**
     * 查询视频
     * @return
     */
    ReturnModel queryVideo();

    /**
     * 增加视频查看次数
     * @param videoId
     * @return
     */
    ReturnModel addViewCount(Integer videoId);

    /**
     * 提交投诉建议
     * @param request 请求request
     * @param model 投诉内容
     * @param complaintFile 投诉附件
     * @return
     */
    ReturnModel submitComplaint(ComplaintInfoModel model, HttpServletRequest request, MultipartFile complaintFile);

    /**
     * 查询资讯关联的文件下载
     * @param catIdOne 资讯一级分类id
     * @param pressId 资讯id
     * @return
     */
    ReturnModel queryPressRelDownLoad(Integer catIdOne, Integer pressId);

    /**
     * 查询当前用户的业务类型是否可访问资源
     * @param type 资源类型
     * @param id 资源id
     * @return
     */
    ReturnModel checkMenuAuth(String type, String id);

    /**
     * 查询访问资源是否需要登录
     * @param type
     * @param id
     * @return
     */
    ReturnModel checkIsVisit(String type, String id);
}
