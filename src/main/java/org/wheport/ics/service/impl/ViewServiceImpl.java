package org.wheport.ics.service.impl;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.wheport.ics.dao.*;
import org.wheport.ics.domain.EnumConstants;
import org.wheport.ics.domain.bo.*;
import org.wheport.ics.domain.pojo.*;
import org.wheport.ics.domain.vo.FirstPage;
import org.wheport.ics.service.ViewService;
import org.wheport.ics.util.ClearXss;
import org.wheport.ics.util.UpDownloadUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ViewServiceImpl implements ViewService {

    @Autowired
    private CustomViewMapper customViewMapper;
    @Autowired
    private ResourcesCommonMapper resourcesCommonMapper;
    @Autowired
    private PressCatMapper pressCatMapper;
    @Autowired
    private ActionCatMapper actionCatMapper;
    @Autowired
    private PressInfoMapper pressInfoMapper;
    @Autowired
    private ModuleInfoMapper moduleInfoMapper;
    @Autowired
    private FriendLinkCatMapper friendLinkCatMapper;
    @Autowired
    private VideoInfoMapper videoInfoMapper;
    @Autowired
    private ComplaintInfoMapper complaintInfoMapper;
    @Autowired
    private GraphicsContextCatMapper graphicsContextCatMapper;
    @Autowired
    private RelFilePressMapper relFilePressMapper;
    @Autowired
    private DownloadFileMapper downloadFileMapper;
    @Autowired
    private UamsRelModuleRoleMapper uamsRelModuleRoleMapper;
    @Autowired
    private UamsRelActionCatRoleMapper uamsRelActionCatRoleMapper;
    @Autowired
    private UamsRelFriendRoleMapper uamsRelFriendRoleMapper;
    @Autowired
    private UamsRelActionRoleMapper uamsRelActionRoleMapper;

    private Log log = LogFactory.getLog(this.getClass());

    @Override
    public ReturnModel queryFirstPage() {
        log.info("---查询首个模块----开始");
        ReturnModel result = new ReturnModel();

        FirstPage firstPage = customViewMapper.queryFirstPage();
        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(firstPage);

        log.info("---查询首个模块----结束");
        return result;
    }

    @Override
    public ReturnModel queryResourcesCommon() {
        log.info("---查询公共资源----开始");
        ReturnModel result = new ReturnModel();

        Map<String, Object> resultMap = new HashMap<String, Object>(16);

        //查询公共资源
        ResourcesCommon resourcesCommon = new ResourcesCommon();
        resourcesCommon.setId(1);
        resourcesCommon = resourcesCommonMapper.selectByPrimaryKey(resourcesCommon);
        if (resourcesCommon != null) {
            resourcesCommon.setRegUrl(UpDownloadUtil.createAccessPath(resourcesCommon.getRegUrl()));
            resourcesCommon.setUserguideUrl(UpDownloadUtil.createAccessPath(resourcesCommon.getUserguideUrl()));
            resourcesCommon.setAndroidImgUrl(UpDownloadUtil.createAccessPath(resourcesCommon.getAndroidImgUrl()));
            resourcesCommon.setIosImgUrl(UpDownloadUtil.createAccessPath(resourcesCommon.getIosImgUrl()));
        }
        resultMap.put("resourcesCommon", resourcesCommon);

        //所有模块
        List<ModuleInfoModel> moduleList = this.customViewMapper.queryModuleList();
        // 暂时过滤“公司简介”
        Iterator<ModuleInfoModel> iterator = moduleList.iterator();
        while (iterator.hasNext()){
            ModuleInfoModel moduleInfoModel = iterator.next();
            if ("公司简介".equals(moduleInfoModel.getModuleName())){
                iterator.remove();
            }
        }
        resultMap.put("moduleList", moduleList);

        //公共友情链接
        List<Map<String, Object>> friendLinkList = new ArrayList<Map<String, Object>>();

        Map<String, Object> friendLinkMap = new HashMap<String, Object>(16);
        FriendLinkCat friendLinkCat = new FriendLinkCat();
        friendLinkCat.setId(1);
        friendLinkCat = friendLinkCatMapper.selectByPrimaryKey(friendLinkCat);
        FriendLinkInfo friendLinkInfo = new FriendLinkInfo();
        friendLinkInfo.setCatId(1);
        List<FriendLinkInfo> friendLinkListOne = this.customViewMapper.queryFriendLinkList(friendLinkInfo);
        friendLinkMap.put("catName", friendLinkCat.getCatName());
        friendLinkMap.put("list", friendLinkListOne);
        friendLinkList.add(friendLinkMap);

        friendLinkMap = new HashMap<String, Object>();
        friendLinkCat = new FriendLinkCat();
        friendLinkCat.setId(2);
        friendLinkCat = friendLinkCatMapper.selectByPrimaryKey(friendLinkCat);
        friendLinkInfo = new FriendLinkInfo();
        friendLinkInfo.setCatId(2);
        List<FriendLinkInfo> friendLinkListTwo = this.customViewMapper.queryFriendLinkList(friendLinkInfo);
        friendLinkMap.put("catName", friendLinkCat.getCatName());
        friendLinkMap.put("list", friendLinkListTwo);
        friendLinkList.add(friendLinkMap);

        friendLinkMap = new HashMap<String, Object>();
        friendLinkCat = new FriendLinkCat();
        friendLinkCat.setId(3);
        friendLinkCat = friendLinkCatMapper.selectByPrimaryKey(friendLinkCat);
        friendLinkInfo = new FriendLinkInfo();
        friendLinkInfo.setCatId(3);
        List<FriendLinkInfo> friendLinkListThree = this.customViewMapper.queryFriendLinkList(friendLinkInfo);
        friendLinkMap.put("catName", friendLinkCat.getCatName());
        friendLinkMap.put("list", friendLinkListThree);
        friendLinkList.add(friendLinkMap);

        resultMap.put("friendLink", friendLinkList);

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(resultMap);
        log.info("---查询公共资源----结束");
        return result;
    }

    @Override
    public ReturnModel queryIndexPageContent(Integer moduleId) {
        log.info("---查询页面资源----开始");
        ReturnModel result = new ReturnModel();
        Map<String,Object> resultMap = new HashMap<String,Object>();

        ModuleInfo moduleInfo = new ModuleInfo();
        moduleInfo.setId(moduleId);
        moduleInfo = moduleInfoMapper.selectByPrimaryKey(moduleInfo);

        resultMap.put("moduleName", moduleInfo.getModuleName());

        PageConfig pageConfig = customViewMapper.queryIndexPageContent(moduleId);
        if(null != pageConfig)
        {
            if(null != pageConfig.getIsRelCarousel() && "y".equals(pageConfig.getIsRelCarousel()))//是否关联轮播图
            {
                List<List<CarouselImageInfoModel>> carouselList = new ArrayList<List<CarouselImageInfoModel>>();//里面嵌套不同catId的轮播图list，list里每一个也是list
                List<Integer> carouselCatList = this.customViewMapper.queryModuleRelCarouselCatList(moduleId);
                if(null != carouselCatList && carouselCatList.size() > 0){
                    for(Integer carouselCatId : carouselCatList){
                        List<CarouselImageInfoModel> carouselImageInfoList = this.customViewMapper.queryCarouselImageInfo(carouselCatId);//这是上面list<list>里面的obj
                        for (CarouselImageInfoModel item : carouselImageInfoList) {
                            item.setImgUrl(UpDownloadUtil.createAccessPath(item.getImgUrl()));
                        }
                        if(null != carouselImageInfoList && carouselImageInfoList.size() > 0){
                            carouselList.add(carouselImageInfoList);
                        }
                    }
                }
                resultMap.put("carouselList", carouselList);
            }
            if(null != pageConfig.getIsRelPressCat() && "y".equals(pageConfig.getIsRelPressCat()))//是否关联资讯
            {
                List<Map<String,Object>> pressMapList = new ArrayList<Map<String,Object>>();//返回前台的list，每个map里面有一个list和一个name
                List<Integer> pressCatList = this.customViewMapper.queryModuleRelPressCatList(moduleId);//这是资讯一级分类
                if(null != pressCatList && pressCatList.size() > 0){
                    for(Integer pressCatId : pressCatList){
                        Map<String,Object> pressMap = new HashMap<String,Object>();
                        List<Integer> pressCatLevelTwo = this.customViewMapper.queryPressCatLevelTwo(pressCatId);
                        if(null != pressCatLevelTwo && pressCatLevelTwo.size() > 0){
                            List<PressInfoModel> pressInfoList = this.customViewMapper.queryPressInfo(pressCatLevelTwo);//这是上面map里面的list
                            for (PressInfoModel item : pressInfoList) {
                                item.setPressImgUrl(UpDownloadUtil.createAccessPath(item.getPressImgUrl()));
                            }
                            if(null != pressInfoList && pressInfoList.size() > 0){
                                pressMap.put("pressList", pressInfoList);
                            }
                        }
                        //查询分类名称
                        PressCat pressCat = new PressCat();
                        pressCat.setId(pressCatId);
                        pressCat = pressCatMapper.selectByPrimaryKey(pressCat);
                        pressMap.put("pressCatId", pressCatId);
                        pressMap.put("pressCatName", pressCat.getCatName());
                        pressMap.put("belongModuleId", pressCat.getBelongModuleId());
                        //查询关联图片
                        RelModulePress relModulePress = new RelModulePress();
                        relModulePress.setModuleId(moduleId);
                        relModulePress.setPressCatId(pressCatId);
                        relModulePress = this.customViewMapper.queryRelPressImg(relModulePress);
                        if(null != relModulePress && null != relModulePress.getRelImgUrl() && !"".equals(relModulePress.getRelImgUrl())){
                            relModulePress.setRelImgUrl(UpDownloadUtil.createAccessPath(relModulePress.getRelImgUrl()));
                            pressMap.put("imgUrl", relModulePress.getRelImgUrl());
                        }

                        pressMapList.add(pressMap);
                    }
                }
                resultMap.put("pressMap", pressMapList);
            }

            if(null != pageConfig.getIsRelActionCat() && "y".equals(pageConfig.getIsRelActionCat())){//是否关联功能分类
                Assertion assertion = AssertionHolder.getAssertion();
                String uid = null;
                List<UamsRelActionCatRole> authCatList = null;
                List<UamsRelActionRole> authActionList = null;
                if(assertion != null){
                    Map<String, Object> attrs = assertion.getPrincipal().getAttributes();
                    uid = (String) attrs.get("uid");
                    authCatList = uamsRelActionCatRoleMapper.selectAllowViewCat(uid);
                    authActionList = uamsRelActionRoleMapper.selectAllowAction(uid);
                }

                List<Map<String,Object>> actionMapList = new ArrayList<Map<String,Object>>();//返回前台的list，每个map里面有一个list和一个name

                List<Map<String,Object>> actionParentList = this.customViewMapper.queryModuleRelActionCatList(moduleId);//查询一级分类
                if(null != actionParentList && actionParentList.size() > 0){
                    for(Map<String,Object> thisMap : actionParentList){
                        boolean flag = false; //通过二级分类标志是否把一级分类加入列表
                        Integer actionParentCatId = (Integer) thisMap.get("actionCatId");

                        ActionCat parentCat = new ActionCat();
                        parentCat.setId(actionParentCatId);
                        parentCat = actionCatMapper.selectByPrimaryKey(parentCat);

                        if(thisMap.get("imgUrl") != null){
                            thisMap.put("imgUrl", UpDownloadUtil.createAccessPath(String.valueOf(thisMap.get("imgUrl"))));
                        }
                        if(thisMap.get("imgUrl2") != null){
                            thisMap.put("imgUrl2", UpDownloadUtil.createAccessPath(String.valueOf(thisMap.get("imgUrl2"))));
                        }

                        thisMap.put("actionParentName", parentCat.getCatName());
                        thisMap.put("isShow", false);//默认不显示,口岸应用页面用到

                        ActionCat actionCat = new ActionCat();
                        actionCat.setParentId(actionParentCatId);
                        List<ActionCat> actionCatList = this.actionCatMapper.select(actionCat);//查询二级分类

                        if(null != actionCatList && actionCatList.size() > 0){
                            List<Map<String,Object>> actionList = new ArrayList<Map<String,Object>>();
                            for(ActionCat catModel : actionCatList){
                                Map<String,Object> catMap = new HashMap<String,Object>();

                                catMap.put("actionCatName", catModel.getCatName());

                                List<ActionInfo> actionInfoList = this.customViewMapper.queryActionInfo(catModel.getId());
                                // 在这里处理二级分类的过滤
                                if(uid != null){
                                    // actionInfoList
                                    List<ActionInfo> tempList=  new ArrayList<>();
                                    if(authActionList != null && authActionList.size() > 0){
                                        for (UamsRelActionRole rel : authActionList) {
                                            for (ActionInfo actionInfo : actionInfoList) {
                                                if(rel.getActionId().equals(actionInfo.getId())){
                                                    tempList.add(actionInfo);
                                                }
                                            }
                                        }
                                    }
                                    actionInfoList = tempList;
                                }
                                for (ActionInfo item : actionInfoList) {
                                    if (item.getImgUrl() != null) {
                                        item.setImgUrl(UpDownloadUtil.createAccessPath(item.getImgUrl()));
                                    }
                                }
                                if(null != actionInfoList && actionInfoList.size() > 0){
                                    catMap.put("actionList", actionInfoList);
                                }
                                if(uid != null){
                                    if(authCatList != null && authCatList.size()>0 ){
                                        for (UamsRelActionCatRole item : authCatList) {
                                            if(item.getActionCatId().equals(catModel.getId())){
                                                actionList.add(catMap);
                                                flag = true;
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    actionList.add(catMap);
                                    flag = true;
                                }
                                //actionList.add(catMap);
                            }
                            thisMap.put("actionList", actionList);
                        }
                        // 在这里处理一级分类的过滤
                        /*if(uid != null){
                            if(authCatList != null && authCatList.size()>0 ){
                                for (UamsRelActionCatRole item : authCatList) {
                                    if(item.getActionCatId() == thisMap.get("actionCatId")){
                                        actionMapList.add(thisMap);
                                        break;
                                    }
                                }
                            }
                        } else {
                            actionMapList.add(thisMap);
                        }*/
                        if(flag)
                            actionMapList.add(thisMap);
                    }
                }
                resultMap.put("actionMap", actionMapList);
            }
            if(null != pageConfig.getIsRelAdvert() && "y".equals(pageConfig.getIsRelAdvert())){//是否关联广告
                List<AdvertInfo> advertList = this.customViewMapper.queryAdvertInfo(moduleId);
                for (AdvertInfo item : advertList) {
                    item.setImgUrl(UpDownloadUtil.createAccessPath(item.getImgUrl()));
                }
                resultMap.put("advertList", advertList);
            }
            if(null != pageConfig.getIsRelImg() && "y".equals(pageConfig.getIsRelImg())){//是否关联图片
                List<ResourcesImage> imgList = this.customViewMapper.queryResourcesImage(moduleId);
                for (ResourcesImage item : imgList) {
                    item.setResourceUrl(UpDownloadUtil.createAccessPath(item.getResourceUrl()));
                }
                resultMap.put("imgList", imgList);
            }
            if(null != pageConfig.getIsRelFriend() && "y".equals(pageConfig.getIsRelFriend())){//是否关联友情链接
                List<Map<String,Object>> friendLinkList = new ArrayList<Map<String,Object>>();

                List<Integer> catIdList = this.customViewMapper.queryFriendLinkCatList(moduleId);
                if(null != catIdList && catIdList.size() > 0){
                    for(Integer friendCatId : catIdList){
                        Map<String,Object> map = new HashMap<String,Object>();
                        List<FriendLinkInfo> friendLinkLsit = this.customViewMapper.qeuryFriendLink(friendCatId);
                        for (FriendLinkInfo item : friendLinkLsit) {
                            item.setFriendLinkImgUrl(UpDownloadUtil.createAccessPath(item.getFriendLinkImgUrl()));
                        }
                        map.put("list", friendLinkLsit);
                        friendLinkList.add(map);
                    }
                }
                resultMap.put("friendLinkList", friendLinkList);
            }
            if(null != pageConfig.getIsRelGraphics() && "y".equals(pageConfig.getIsRelGraphics())){//是否关联图文
                List<Map<String,Object>> graphicsList = new ArrayList<Map<String,Object>>();

                List<Map<String,Object>> relCatList = customViewMapper.queryRelModuleGraphicsList(moduleId);
                if(null != relCatList && relCatList.size() > 0){
                    for(Map<String,Object> map : relCatList){
                        Map<String,Object> thisMap = new HashMap<String,Object>();
                        thisMap.put("catName", map.get("catName"));

                        Integer catId = (Integer) map.get("catId");
                        List<GraphicsContext> thisList = customViewMapper.queryGraphicsList(catId);
                        thisMap.put("list", thisList);

                        graphicsList.add(thisMap);
                    }
                }
                resultMap.put("graphicsList", graphicsList);
            }

            result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
            result.setObj(resultMap);
        }else{
            result.setCode(EnumConstants.RETURN_FAIL.getCode());
        }

        log.info("---查询页面资源----结束");
        return result;
    }

    public ReturnModel queryPressCatList(Integer catIdOne) {
        log.info("---查询资讯分类列表----开始");
        ReturnModel result = new ReturnModel();

        Map<String,Object> resultMap = new HashMap<String,Object>();
        //查询背景图
        List<String> imgUrls = this.customViewMapper.queryPressCatBackImg(catIdOne);
        if(null != imgUrls && imgUrls.size() > 0){
            String imgUrl = imgUrls.get(0);
            imgUrl = UpDownloadUtil.createAccessPath(imgUrl);
            resultMap.put("imgUrl", imgUrl);
        }
        //查询分类名称和分类logo
        PressCat pressCat = new PressCat();
        pressCat.setId(catIdOne);
        pressCat = pressCatMapper.selectByPrimaryKey(pressCat);
        if(pressCat != null){
            pressCat.setLogoUrl(UpDownloadUtil.createAccessPath(pressCat.getLogoUrl()));
        }
        resultMap.put("pressCat", pressCat);
        //查询二级分类
        List<PressCat> pressCatList = this.customViewMapper.queryPressCatList(catIdOne);
        resultMap.put("pressCatList", pressCatList);

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(resultMap);

        log.info("---查询资讯分类列表----结束");
        return result;
    }

    public ReturnModel pageQueryPressList(Integer page, Integer parentId, Integer catId) {
        log.info("---分页查询资讯分类信息----开始");
        ReturnModel result = new ReturnModel();

        PressPageModel resultModel = new PressPageModel();
        //获取当前页面分页数
        Integer pageCount = this.customViewMapper.queryCurrentPageCount(parentId);
        PageModel pageModel = new PageModel(page,pageCount);
        pageModel.setRequestObj(catId);
        //分页查询
        Integer recordCount = this.customViewMapper.queryPressListCount(catId);
        List<Integer> pageList = new ArrayList<Integer>();
        Integer i = 1;
        if(recordCount > 0){
            pageList.add(i);
        }
        while(recordCount > pageCount){
            i++;
            recordCount = recordCount - pageCount;
            pageList.add(i);
        }
        List<PressInfoModel> pressList = this.customViewMapper.queryPressInfoList(pageModel);
        //把值传回
        resultModel.setNowPage(page);
        resultModel.setPageList(pageList);
        resultModel.setPressInfoList(pressList);

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(resultModel);

        log.info("---分页查询资讯分类信息----结束");
        return result;
    }

    public ReturnModel queryPressDetail(Integer catIdOne, Integer pressId) {
        log.info("---查询资讯详情----开始");
        ReturnModel result = new ReturnModel();

        Map<String,Object> resultMap = new HashMap<String,Object>();
        //查询背景图
        List<String> imgUrls = this.customViewMapper.queryPressCatBackImg(catIdOne);
        if(null != imgUrls && imgUrls.size() > 0){
            resultMap.put("imgUrl", imgUrls.get(0));
        }
        //查询分类名称
        PressCat pressCat = new PressCat();
        pressCat.setId(catIdOne);
        pressCat = pressCatMapper.selectByPrimaryKey(pressCat);
        resultMap.put("parentCatName", pressCat.getCatName());
        String catName = this.customViewMapper.queryPressCatNameByPressId(pressId);
        resultMap.put("catName", catName);
        //查询资讯详情
        PressInfoModel pressInfo = new PressInfoModel();
        pressInfo.setId(pressId);
        pressInfo = customViewMapper.queryPressDetail(pressInfo);
        List<DownloadFile> fileList = customViewMapper.queryPressRelFileList(pressId);
        pressInfo.setFileList(fileList);
        resultMap.put("pressInfo", pressInfo);

        //查询上下篇
        Integer catId = pressInfo.getPressCatId();
        PressInfo preLastInfo = new PressInfo();
        preLastInfo.setPressCatId(catId);
        List<PressInfo> preLastList = pressInfoMapper.select(preLastInfo);
        for(int i = 0;i < preLastList.size();i++){
            if(preLastList.get(i).getId().equals(pressId)){
                if(i - 1 >= 0){
                    resultMap.put("prevPressInfo", preLastList.get(i-1));
                }
                if(i + 1 < preLastList.size()){
                    resultMap.put("nextPressInfo", preLastList.get(i+1));
                }
            }
        }

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(resultMap);

        log.info("---查询资讯详情----结束");
        return result;
    }

    public ReturnModel queryQuestionAndAnswer() {
        log.info("---查询问答----开始");
        ReturnModel result = new ReturnModel();

        List<QuestionAndAnswersModel> list = this.customViewMapper.queryQuestionAndAnswer();

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(list);

        log.info("---查询问答----结束");
        return result;
    }

    public ReturnModel queryVideo() {
        log.info("---查询视频----开始");
        ReturnModel result = new ReturnModel();

        List<VideoInfo> list = this.customViewMapper.queryVideo();
        for (VideoInfo item : list) {
            item.setImgUrl(UpDownloadUtil.createAccessPath(item.getImgUrl()));
        }

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(list);

        log.info("---查询视频----结束");
        return result;
    }

    public ReturnModel addViewCount(Integer videoId) {
        log.info("---增加视频查看次数----开始");
        ReturnModel result = new ReturnModel();

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setId(videoId);
        videoInfo = videoInfoMapper.selectByPrimaryKey(videoInfo);

        if(null != videoInfo){
            Integer count = videoInfo.getViewCount();
            count += 1;
            videoInfo.setViewCount(count);
            videoInfoMapper.updateByPrimaryKeySelective(videoInfo);

            result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
            result.setObj(count);
        }

        log.info("---增加视频查看次数----结束");
        return result;
    }

    @Transactional
    public ReturnModel submitComplaint(ComplaintInfoModel model, HttpServletRequest request, MultipartFile complaintFile) {
        log.info("---提交投诉----开始");
        ReturnModel result = new ReturnModel();

        //验证验证码
        String captcha = (String) request.getSession().getAttribute("complaintCaptcha");
        String lowercashcaptcha = captcha.toLowerCase();
        if(null != model.getCaptcha() && captcha.equals(model.getCaptcha()) || lowercashcaptcha.equals(model.getCaptcha())){
            //屏蔽xss关键字
            model.setName(ClearXss.cleanXSS(model.getName()));
            model.setMobile(ClearXss.cleanXSS(model.getMobile()));
            model.setContent(ClearXss.cleanXSS(model.getContent()));

            Date date = new Date();

            if(null != complaintFile && !complaintFile.isEmpty()){
                long maxfileSize = 2*1024*1024;//设置文件的最大值
                //文件时间戳
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String fileNo = format.format(date);

                //源文件路径
                String uploadPath = UpDownloadUtil.createOriginaUrl("uploadPath");
                String  downloadPath = UpDownloadUtil.createOriginaUrl("downloadPath");

                long fileSize = complaintFile.getSize();//文件的大小
                if(fileSize > maxfileSize)
                {
                    result.setCode("-1");
                    result.setMsg("文件过大，请导入小于2M的文件");
                    log.info("---提交投诉----文件过大-----");
                    return result;
                }
                //源文件后缀名
                String ext = UpDownloadUtil.getExt(complaintFile);

                if(null != ext && !"doc".equals(ext) && !"docx".equals(ext)){
                    result.setCode("-1");
                    result.setMsg("文件格式错误，只能上传doc、docx类型的文件");
                    log.info("---提交投诉----文件类型错误，现上传类型为."+ext+"-----");
                    return result;
                }

                String fileName = "complaintFile_"+fileNo;
                fileName = UpDownloadUtil.createFileName(fileName,ext);
                try {
                    UpDownloadUtil.saveFile(new File(uploadPath), fileName,complaintFile.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String fileUrl = downloadPath + fileName;

                model.setFileUrl(fileUrl);
            }

            model.setAddTime(date);
            Integer in = complaintInfoMapper.insertSelective(model);
            if(in > 0){
                result.setCode("0");
            }else{
                result.setCode("-1");
                result.setMsg("提交失败");
                log.info("---提交投诉----提交失败-----");
            }
        }else{
            result.setCode("-1");
            result.setMsg("验证码错误");
            log.info("---提交投诉----验证码错误-----");
        }

        log.info("---提交投诉----结束");
        return result;
    }

    public ReturnModel queryPressRelDownLoad(Integer catIdOne, Integer pressId) {
        log.info("---查询资讯下载详情----开始");
        ReturnModel result = new ReturnModel();

        Map<String,Object> resultMap = new HashMap<String,Object>();
        //查询背景图
        List<String> imgUrls = this.customViewMapper.queryPressCatBackImg(catIdOne);
        if(null != imgUrls && imgUrls.size() > 0){
            resultMap.put("imgUrl", imgUrls.get(0));
        }
        //查询分类名称
        PressCat pressCat = new PressCat();
        pressCat.setId(catIdOne);
        pressCat = pressCatMapper.selectByPrimaryKey(pressCat);
        resultMap.put("parentCatName", pressCat.getCatName());
        String catName = this.customViewMapper.queryPressCatNameByPressId(pressId);
        resultMap.put("catName", catName);
        //查询资讯下载详情
        RelFilePress relFilePress = new RelFilePress();
        relFilePress.setPressId(pressId);
        List<RelFilePress> relFilePressList = relFilePressMapper.select(relFilePress);
        if(null != relFilePressList && relFilePressList.size() > 0){
            Integer fileId = relFilePressList.get(0).getFileId();
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setId(fileId);
            downloadFile = downloadFileMapper.selectByPrimaryKey(downloadFile);
            resultMap.put("fileInfo", downloadFile);
        }

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        result.setObj(resultMap);

        log.info("---查询资讯下载详情----结束");
        return result;
    }

    @Override
    public ReturnModel checkMenuAuth(String type, String id) {
        Assertion assertion = AssertionHolder.getAssertion();
        ReturnModel result = new ReturnModel();
        Map<String, Object> paramMap = new HashMap<>();
        // 获取用户信息
        if(assertion != null){
            Map<String, Object> attrs = assertion.getPrincipal().getAttributes();
            String uid = (String) attrs.get("uid");
            if(StringUtils.equalsIgnoreCase(type, "module")){
                paramMap.put("userId", uid);
                paramMap.put("moduleId", id);
                Long count = uamsRelModuleRoleMapper.findRelModuleRoleCount(paramMap);
                if (count > 0){
                    result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
                } else {
                    result.setCode(EnumConstants.RETURN_FAIL.getCode());
                }
                return result;
            } else if(StringUtils.equalsIgnoreCase(type, "friendLink")){
                paramMap.put("userId", uid);
                paramMap.put("linkId", id);
                Long count = uamsRelFriendRoleMapper.findRelFriendLinkCount(paramMap);
                if (count > 0){
                    result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
                } else {
                    result.setCode(EnumConstants.RETURN_FAIL.getCode());
                }
                return result;
            } else {
                result.setCode(EnumConstants.RETURN_FAIL.getCode());
                result.setMsg("parameter error");
            }

        }

        result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        return result;
    }

    @Override
    public ReturnModel checkIsVisit(String type, String id) {
        Assertion assertion = AssertionHolder.getAssertion();
        ReturnModel result = new ReturnModel();
        // 获取用户信息
        if(assertion == null){
            // 由于不能轻易更改数据库表结构，此处暂写死,以下为用户未登录时不能访问的菜单
            Map<String, String> moduleNameUnAccess = new HashMap<>();
            moduleNameUnAccess.put("3", "口岸通关");
            moduleNameUnAccess.put("4", "数据中心");
            moduleNameUnAccess.put("6", "湖北水运平台");
            moduleNameUnAccess.put("7", "办事指南");

            Set<String> moduleIds = moduleNameUnAccess.keySet();
            if (moduleIds.contains(id)){
                result.setCode(EnumConstants.RETURN_FAIL.getCode());
            } else {
                result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
            }
        } else {
            result.setCode(EnumConstants.RETURN_SUCCESS.getCode());
        }
        return result;
    }
}
