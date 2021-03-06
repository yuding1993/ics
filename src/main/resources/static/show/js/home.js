avalon.ready(function () {
    /*公共资源查询*/
    queryResourcesCommon();//查询头部图片以及头部、底部文字、模块列表
    vm.nowModuleId = paramsMap.moduleId;//定义当前模块
    /*公共资源查询结束*/
    queryIndexPageContent(paramsMap.moduleId);//查询当前模块资源

    // 所有子系统应用模块开始
    queryAllApplicationContent();
    // 所有子系统应用模块结束
});

var dailiUrl = "";

var vm = avalon.define({
    $id: "home",
    /*公共属性和方法*/
    resourcesCommon: {},//头和尾的公共资源
    moduleList: [],//模块列表
    friendLinkCommon: {},//公共友情链接
    nowDateString: "",//头部时间字符串
    nowModuleId: 0,//现在的模块id
    isLogin: false,//登陆状态,
    isNotLogin: true,
    userName: "",//登陆用户名
    userNameComplete: "",//用户名全称
    skipPage: function (moduleId, pageUrl) {
        // 某些模块未登录禁止访问（后台判断）
        $.ajax({
            url: "../view/checkIsVisit",
            data: {
                type: "module",
                id: moduleId
            },
            dataType: "json",
            async: false,
            success: function (resp) {
                if (resp.code != 0) {
                    layer.msg('您还未登录，请登录后再试！', {icon: 7,time:3000});
                    return;
                }
                $.ajax({
                    url: "../view/checkMenuAuth",
                    data: {
                        type: "module",
                        id: moduleId
                    },
                    dataType: "json",
                    async: false,
                    success: function (resp) {
                        if (resp.code != 0) {
                            layer.msg('没有权限访问！', {icon: 7,time:3000});
                            return;
                        }
                        window.location.href = pageUrl + "?moduleId=" + moduleId;//跳转模块页面
                    },
                    error: function () {
                        layer.msg('服务器异常！', {icon: 7,time:3000});
                    }
                });
            },
            error: function () {
                layer.msg('服务器异常！', {icon: 7,time:3000});
            }
        });
    },
    login: function () {//登陆
        var path = window.location.pathname + window.location.search;
        path = path.substring(1, path.length);//当index.jsp地址改变时，截取的值也需改变
        window.location.href = "../sec/" + path;
    },
    openUrlSelf: function (linkUrl) {//打开新页面
        if (linkUrl == "" || linkUrl == null || linkUrl == undefined) {
            return false;
        }
        window.location.href = linkUrl;
    },
    openUrl: function (url) {//打开新页面
        if (url == "" || url == null || url == undefined) {
            return false;
        }
        window.open(url);
    },
    /**
     * 打开前检查是否登录
     * @returns {boolean}
     */
    openBeforeCheck: function (url) {
        if (vm.isLogin) {
            window.open(url);
        } else {
            layer.msg('您还未登录，请登录后再试！', {icon: 7,time:3000});
            // $(".icLogin").show();
            return false;
        }
    },
    openFriendLink: function (url, linkId) {//打开新页面
        if (url == "" || url == null || url == undefined) {
            return false;
        }
        $.ajax({
            url: "../view/checkMenuAuth",
            data: {
                type: "friendLink",
                id: linkId
            },
            dataType: "json",
            async: false,
            success: function (resp) {
                if (resp.code != 0) {
                    layer.msg('没有权限访问！', {icon: 7,time:3000});
                    return;
                }
                window.open(url);
            },
            error: function () {
                layer.msg('服务器异常！', {icon: 7,time:3000});
            }
        });
    },
    gotoPressCatList: function (catIdOne, belongModuleId, pressId) {//跳转资讯分类页面
        if (pressId != null && pressId != "" && pressId != undefined) {
            window.location.href = "pressCatList.html?catIdOne=" + catIdOne + "&moduleId=" + belongModuleId + "&viewPressId=" + pressId;
        } else {
            window.location.href = "pressCatList.html?catIdOne=" + catIdOne + "&moduleId=" + belongModuleId;
        }
    },
    viewDetail: function (pressId, parentCatId, belongModuleId) {//跳转到资讯详情页面
        if (pressId == "" || pressId == undefined || pressId == null) {
            return false;
        }
        window.location.href = "pressCatList.html?catIdOne=" + parentCatId + "&viewPressId=" + pressId + "&moduleId=" + belongModuleId;
    },
    /*公共属性和方法结束*/
    carousel: {},
    pressMap: {},
    advertList: [],
    friendLinkList: [],
    friendLinkShowList: [],
    imgNowIndexOne: 0,
    imgNowIndexTwo: 0,
    friendLinkMin: 0,
    friendLinkMax: 4,
    nowFriendPianyi: 0,
    backToTop: function () {
        var speed = 200;//滑动的速度
        $('body,html').animate({scrollTop: 0}, speed);
        return false;
    },
    // 所有子系统应用模块开始
    moduleName: "",
    imgOne: {},
    actionMap: {}
    // 所有子系统应用模块结束
});

window.onload = function(){
    show1();
}

function queryResourcesCommon() {
    $.ajax({
        url: "../view/queryResourcesCommon",
        type: 'post',
        error: function () {
            layer.msg('系统异常！', {icon: 7,time:3000});
        },
        success: function (data) {

            vm.resourcesCommon = data.obj.resourcesCommon;
            vm.moduleList = data.obj.moduleList;
            vm.friendLinkCommon = data.obj.friendLink;
        }
    });
}

function getDateString() {
    vm.nowDateString = getNowDateString();
}

function queryIndexPageContent(moduleId) {
    $.ajax({
        url: "../view/queryIndexPageContent",
        data: {moduleId: moduleId},
        dataType: 'json',
        type: 'post',
        error: function () {
            layer.msg('系统异常！', {icon: 7,time:3000});
        },
        success: function (data) {
            vm.carousel = data.obj.carouselList;
            vm.pressMap = data.obj.pressMap;
            vm.advertList = data.obj.advertList;
            vm.friendLinkList = data.obj.friendLinkList;
            vm.friendLinkShowList = data.obj.friendLinkList[0].list.concat();
        }
    });
}

function queryAllApplicationContent() {
    $.ajax({
        url: "../view/queryIndexPageContent",
        // moduleId为3查询所有子系统应用
        data: {moduleId: 3},
        dataType: 'json',
        type: 'post',
        error: function () {
            layer.msg('系统异常！', {icon: 7,time:3000});
        },
        success: function (data) {
            vm.imgOne = data.obj.imgList[0];
            vm.moduleName = data.obj.moduleName;
            vm.actionMap = data.obj.actionMap;
        }
    });
}

function firendLink(url) {
    if (url == "" || url == null || url == undefined) {
        return false;
    }
    window.open(url);
}

function viewDetail(pressId, parentCatId, belongModuleId)//跳转到资讯详情页面
{
    if (pressId == "" || pressId == undefined || pressId == null) {
        return false;
    }
    window.location.href = "pressDetail.html?catIdOne=" + parentCatId + "&pressId=" + pressId + "&moduleId=" + belongModuleId;
}

/**
 * 打开qp
 * @returns {boolean}
 */
function openQp() {
    if (vm.isLogin) {
        $.get("../view/checkIsIcLogin", {}, function (data) {
            if (data.code == "0") {
                $.get("../view/turnToQp", {}, function (data) {
                    if (data.code == "0") {
                        window.open(data.obj);
                    } else {
                        layer.msg('必须通过IC卡登陆本系统方可访问！请先退出登录然后使用IC卡登陆！', {icon: 7,time:3000});
                        // $(".icLogin").show();
                        return false;
                    }
                });
            } else {
                layer.msg('必须通过IC卡登陆本系统方可访问！', {icon: 7,time:3000});
                // $(".icLogin").show();
                return false;
            }
        });
    } else {
        layer.msg('必须通过IC卡登陆本系统方可访问！', {icon: 7,time:3000});
        // $(".icLogin").show();
        return false;
    }
}

/**
 * 打开保金保函
 * @returns {boolean}
 */
function openInsuranceBond() {
    if (vm.isLogin) {
        $.get("../view/checkIsIcLogin", {}, function (data) {
            if (data.code == "0") {
                window.open("https://search.wh-eport.cn/zhcx/a/guarantee/index");
            } else {
                layer.msg('必须通过IC卡登陆本系统方可访问！', {icon: 7,time:3000});
                // $(".icLogin").show();
                return false;
            }
        });
    } else {
        layer.msg('必须通过IC卡登陆本系统方可访问！', {icon: 7,time:3000});
        // $(".icLogin").show();
        return false;
    }
}

function show1(){
    document.getElementById("div_iframe1").hidden="";
    document.getElementById("div_iframe2").hidden="hidden";
    try{
        document.getElementById("div_iframe3").hidden="hidden";
    }catch(e){}
}

function show2(){
    document.getElementById("div_iframe1").hidden="hidden";
    document.getElementById("div_iframe2").hidden="";
    try{
        document.getElementById("div_iframe3").hidden="hidden";
    }catch(e){}
    checkSetupControl();//检查是否要安装客户端控件
}

function show3(){
    document.getElementById("div_iframe1").hidden="hidden";
    document.getElementById("div_iframe2").hidden="hidden";
    try{
        document.getElementById("div_iframe3").hidden="";
    }catch(e){}
}