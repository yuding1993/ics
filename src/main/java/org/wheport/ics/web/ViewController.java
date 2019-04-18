package org.wheport.ics.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wheport.ics.domain.bo.ComplaintInfoModel;
import org.wheport.ics.domain.bo.ReturnModel;
import org.wheport.ics.service.ActionInfoService;
import org.wheport.ics.service.LoginService;
import org.wheport.ics.service.RelActionUsertypeService;
import org.wheport.ics.service.ViewService;

import javax.servlet.http.HttpServletRequest;

@Api(description = "页面显示接口")
@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private ViewService viewServiceImpl;

    @Autowired
    private LoginService loginServiceImpl;

    @Autowired
    private ActionInfoService actionInfoServiceImpl;

    @Autowired
    private RelActionUsertypeService relActionUsertypeServiceImpl;

    /**
     * 查询首页
     * @return
     */
    @ApiOperation(value = "查询首页" ,  notes="查询首页")
    @RequestMapping(value="/queryFirstPage",method= RequestMethod.POST)
    public ReturnModel queryFirstPage(){
        return this.viewServiceImpl.queryFirstPage();
    }

    /**
     * 查询公共资源
     * @return
     */
    @ApiOperation(value = "查询公共资源" ,  notes="查询公共资源")
    @RequestMapping(value="/queryResourcesCommon",method=RequestMethod.POST)
    public ReturnModel queryResourcesCommon(){
        return this.viewServiceImpl.queryResourcesCommon();
    }

    /**
     * 查询本页面资源内容
     * @param moduleId 模块id
     * @return
     */
    @ApiOperation(value = "查询本页面资源内容" ,  notes="查询本页面资源内容")
    @RequestMapping(value="/queryIndexPageContent",method=RequestMethod.POST)
    public ReturnModel queryIndexPageContent(@RequestParam(value="moduleId")Integer moduleId){
        return this.viewServiceImpl.queryIndexPageContent(moduleId);
    }

    /**
     * 查询资讯列表
     * @param catIdOne 资讯一级分类id
     * @return
     */
    @ApiOperation(value = "查询资讯列表" ,  notes="查询资讯列表")
    @RequestMapping(value="/queryPressCatList",method=RequestMethod.POST)
    public ReturnModel queryPressCatList(@RequestParam(value="catIdOne",required=true)Integer catIdOne){
        return this.viewServiceImpl.queryPressCatList(catIdOne);
    }

    /**
     * 分页查询资讯
     * @param page 当前页数
     * @param parentId 一级父分类id
     * @param catId 二级父分类id
     * @return
     */
    @ApiOperation(value = "分页查询资讯" ,  notes="分页查询资讯")
    @RequestMapping(value="/pageQueryPressList",method=RequestMethod.POST)
    public ReturnModel pageQueryPressList(@RequestParam(value="page")Integer page,@RequestParam(value="parentId") Integer parentId,
                                          @RequestParam(value="catId")Integer catId){
        return this.viewServiceImpl.pageQueryPressList(page,parentId,catId);
    }

    /**
     * 查询资讯详情
     * @param catIdOne 资讯一级分类id
     * @param pressId 资讯id
     * @return
     */
    @ApiOperation(value = "查询资讯详情" ,  notes="查询资讯详情")
    @RequestMapping(value="/queryPressDetail",method=RequestMethod.POST)
    public ReturnModel queryPressDetail(@RequestParam(value="catIdOne")Integer catIdOne,@RequestParam(value="pressId")Integer pressId){
        return this.viewServiceImpl.queryPressDetail(catIdOne,pressId);
    }

    /**
     * 查询当前登录状态
     * @return
     */
    @ApiOperation(value = "查询当前登录状态" ,  notes="查询当前登录状态")
    @RequestMapping(value="/isLogin",method=RequestMethod.POST)
    public ReturnModel isLogin(){
        return loginServiceImpl.isLogin();
    }

    /**
     * 获取qp登录的参数值
     * @param actionId 功能id
     * @return
     */
    @ApiOperation(value = "获取qp登录的参数值" ,  notes="获取qp登录的参数值")
    @RequestMapping(value="/turnToQp",method=RequestMethod.GET)
    public ReturnModel turnToQp(@RequestParam(value="id")Integer actionId){
        return actionInfoServiceImpl.turnToQp(actionId);
    }

    /**
     * 检查当前用户是否为ic卡登录
     * @return
     */
    @ApiOperation(value = "检查否为ic卡登录" ,  notes="检查当前用户是否为ic卡登录")
    @RequestMapping(value="/checkIsIcLogin",method=RequestMethod.GET)
    public ReturnModel checkIsIcLogin(){
        return loginServiceImpl.checkIsIcLogin();
    }

    /**
     * 有参数跳转的功能补全参数
     * @param id 功能id
     * @return
     */
    @ApiOperation(value = "有参数跳转的功能补全参数" ,  notes="有参数跳转的功能补全参数")
    @RequestMapping(value="/getSuffixUrl",method=RequestMethod.GET)
    public ReturnModel getSuffixUrl(@RequestParam(value="id")Integer id){
        return actionInfoServiceImpl.getSuffixUrl(id);
    }

    /**
     * 查询问答
     * @return
     */
    @ApiOperation(value = "查询问答" ,  notes="查询问答")
    @RequestMapping(value="/queryQuestionAndAnswer",method=RequestMethod.POST)
    public ReturnModel queryQuestionAndAnswer(){
        return this.viewServiceImpl.queryQuestionAndAnswer();
    }

    /**
     * 查询视频
     * @return
     */
    @ApiOperation(value = "查询视频" ,  notes="查询视频")
    @RequestMapping(value="/queryVideo",method=RequestMethod.POST)
    public ReturnModel queryVideo(){
        return this.viewServiceImpl.queryVideo();
    }

    /**
     * 增加视频查看次数
     * @param videoId
     * @return
     */
    @ApiOperation(value = "增加视频查看次数" ,  notes="增加视频查看次数")
    @RequestMapping(value="/addViewCount",method=RequestMethod.POST)
    public ReturnModel addViewCount(@RequestParam(value="videoId") Integer videoId){
        return this.viewServiceImpl.addViewCount(videoId);
    }

    /**
     * 提交投诉建议
     * @param request 请求request
     * @param model 投诉内容
     * @param complaintFile 投诉附件
     * @return
     */
    @ApiOperation(value = "提交投诉建议" ,  notes="提交投诉建议")
    @RequestMapping(value="/submitComplaint",method=RequestMethod.POST)
    public ReturnModel submitComplaint(HttpServletRequest request, @ModelAttribute ComplaintInfoModel model,
                                       MultipartFile complaintFile){
        return this.viewServiceImpl.submitComplaint(model,request,complaintFile);
    }

    /**
     * 查询资讯关联的文件下载
     * @param catIdOne 资讯一级分类id
     * @param pressId 资讯id
     * @return
     */
    @ApiOperation(value = "查询资讯关联的文件下载" ,  notes="查询资讯关联的文件下载")
    @RequestMapping(value="/queryPressRelDownLoad",method=RequestMethod.POST)
    public ReturnModel queryPressRelDownLoad(@RequestParam(value="catIdOne")Integer catIdOne,@RequestParam(value="pressId")Integer pressId){
        return this.viewServiceImpl.queryPressRelDownLoad(catIdOne,pressId);
    }

    /**
     * 访问webService的形式获取参数，返回到前台拼接功能url
     * @param serviceUrl webService地址
     * @param moduleId 模块id
     * @param nameSpace webService的nameSpace
     * @param method webService的method
     * @return
     */
    @ApiOperation(value = "查询本页面资源内容" ,  notes="访问webService的形式获取参数，返回到前台拼接功能url")
    @RequestMapping(value="/openServiceReturnParam",method=RequestMethod.POST)
    public String openServiceReturnParam(@RequestParam(value="serviceUrl")String serviceUrl,@RequestParam(value="moduleId")String moduleId,
                                         @RequestParam(value="nameSpace")String nameSpace,@RequestParam(value="method")String method){

        return loginServiceImpl.openServiceReturnParam(serviceUrl, moduleId, nameSpace, method);
    }

    /**
     * 通过id获取功能地址
     * @param id 功能id
     * @return
     */
    @ApiOperation(value = "通过id获取功能地址" ,  notes="通过id获取功能地址")
    @RequestMapping(value="/getActionUrlById",method=RequestMethod.GET)
    public ReturnModel getActionUrlById(@RequestParam(value="id")Integer id){
        return actionInfoServiceImpl.getActionUrlById(id);
    }

    /**
     * 查询当前用户的业务类型是否可访问功能
     * @param id 功能id
     * @return
     */
    @ApiOperation(value = "查询访问功能" ,  notes="查询当前用户的业务类型是否可访问功能")
    @RequestMapping(value="/checkUserType",method=RequestMethod.POST)
    public ReturnModel checkUserType(@RequestParam(value="id")Integer id){
        return relActionUsertypeServiceImpl.checkUserType(id);
    }

    /**
     * 查询当前用户的业务类型是否可访问资源
     * @param type 资源类型
     * @param id 资源id
     * @return
     */
    @ApiOperation(value = "查询访问资源" ,  notes="查询当前用户的业务类型是否可访问资源")
    @GetMapping("/checkMenuAuth")
    public ReturnModel checkMenuAuth(@RequestParam(value="type")String type, @RequestParam(value="id")String id){
        ReturnModel rm = viewServiceImpl.checkMenuAuth(type, id);
        return rm;
    }

    /**
     * 注册跳转
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request) {
        return loginServiceImpl.register(request);
    }
}
