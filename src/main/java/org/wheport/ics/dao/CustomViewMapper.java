package org.wheport.ics.dao;

import org.wheport.ics.domain.bo.*;
import org.wheport.ics.domain.pojo.*;
import org.wheport.ics.domain.vo.FirstPage;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface CustomViewMapper extends MySqlMapper<ModuleInfoModel>, Mapper<ModuleInfoModel> {

    List<ModuleInfoModel> queryModuleList();

    FirstPage queryFirstPage();

    PageConfig queryIndexPageContent(Integer moduleId);

    List<Integer> queryModuleRelCarouselCatList(Integer moduleId);

    List<CarouselImageInfoModel> queryCarouselImageInfo(Integer carouselCatId);

    List<Integer> queryModuleRelPressCatList(Integer moduleId);

    List<Integer> queryPressCatLevelTwo(Integer pressCatId);

    List<PressInfoModel> queryPressInfo(List<Integer> pressCatLevelTwo);

    List<Map<String, Object>> queryModuleRelActionCatList(Integer moduleId);

    List<ActionInfo> queryActionInfo(Integer actionCatId);

    List<AdvertInfo> queryAdvertInfo(Integer moduleId);

    List<ResourcesImage> queryResourcesImage(Integer moduleId);

    RelModulePress queryRelPressImg(RelModulePress relModulePress);

    List<String> queryPressCatBackImg(Integer pressCatId);

    List<PressCat> queryPressCatList(Integer pressCatId);

    Integer queryPressListCount(Integer pressCatId);

    List<PressInfoModel> queryPressInfoList(PageModel pageModel);

    Integer queryCurrentPageCount(Integer pressCatId);

    String queryPressCatNameByPressId(Integer pressId);

    List<FriendLinkInfo> queryFriendLinkList(FriendLinkInfo friendLinkInfo);

    List<Integer> queryFriendLinkCatList(Integer moduleId);

    List<FriendLinkInfo> qeuryFriendLink(Integer friendCatId);

    List<QuestionAndAnswersModel> queryQuestionAndAnswer();

    List<VideoInfo> queryVideo();

    List<Map<String, Object>> queryRelModuleGraphicsList(Integer moduleId);

    List<GraphicsContext> queryGraphicsList(Integer catId);

    PressInfoModel queryPressDetail(PressInfoModel pressInfo);

    List<DownloadFile> queryPressRelFileList(Integer pressId);
}
