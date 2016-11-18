<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/13
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="page-sidebar nav-collapse collapse">
    <ul class="page-sidebar-menu">
        <li>
            <div class="sidebar-toggler hidden-phone"></div>
        </li>
        <li>
            <form class="sidebar-search">
                <div class="input-box">
                    <a href="javascript:;" class="remove"></a>
                    <input type="text" placeholder="Search..."/>
                    <input type="button" class="submit" value=" "/>
                </div>
            </form>
        </li>
        <li class="start home">
            <a href="<%=request.getContextPath()%>/home">
                <i class="icon-home"></i>
                <span class="title">首页</span>
                <span class="selected"></span>
            </a>
        </li>
        <%--攀岩管理--%>
        <shiro:hasPermission
                name="menu:climbing:banner or menu:climbing:equipment or menu:climbing:talentshow or menu:climbing:appointment or menu:climbing:venues or menu:climbing:introduction">
            <li class="climbing">
                <a href="javascript:;">
                    <i class="icon-tags"></i>
                    <span class="title">攀岩管理</span>
                    <span class="arrow"></span>
                    <span class="selected hide"></span>
                </a>
                <ul class="sub-menu">
                    <shiro:hasPermission name="menu:climbing:banner">
                        <li class="banner">
                            <a href="<%=request.getContextPath()%>/menu/climbing/banner">banner管理<span
                                    class="selected"></span></a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:climbing:introduction">
                        <li class="introduction couserlabel">
                            <a href="<%=request.getContextPath()%>/menu/climbing/introduction">教程攻略</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:climbing:equipment">
                        <li class="equipment">
                            <a href="<%=request.getContextPath()%>/menu/climbing/equipment">装备控</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:climbing:talentshow">
                        <li class="talentshow">
                            <a href="<%=request.getContextPath()%>/menu/climbing/talentshow">达人秀</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:climbing:appointment">
                        <li class="appointment">
                            <a href="<%=request.getContextPath()%>/menu/climbing/appointment">求约</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:climbing:venues">
                        <li class="venues">
                            <a href="<%=request.getContextPath()%>/menu/climbing/venues">场馆</a>
                        </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasPermission>
        <%--社区--%>
        <shiro:hasPermission name="menu:community:dynamic or menu:community:grades">
            <li class="community">
                <a href="javascript:;">
                    <i class="icon-globe"></i>
                    <span class="title">社区管理</span>
                    <span class="arrow"></span>
                    <span class="selected hide"></span>
                </a>
                <ul class="sub-menu">
                    <shiro:hasPermission name="menu:community:dynamic">
                        <li class="dynamic">
                            <a href="<%=request.getContextPath()%>/menu/community/dynamic">动态管理<span
                                    class=""></span></a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:community:grades">
                        <li class="grades">
                            <a href="<%=request.getContextPath()%>/menu/community/grades">等级管理<span
                                    class="selected"></span></a>
                        </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasPermission>
        <%--城市标签--%>
        <shiro:hasPermission name="menu:city:city">
            <li class="city">
                <a href="<%=request.getContextPath()%>/menu/city/city">
                    <i class="icon-play-circle"></i>
                    <span class="title">城市</span>
                    <span class="selected hide"></span>
                </a>
            </li>
        </shiro:hasPermission>
        <%--直播--%>
        <%--<shiro:hasPermission name="menu:live:living">
            <li class="live">
                <a href="<%=request.getContextPath()%>/menu/live/living">
                    <i class="icon-play-circle"></i>
                    <span class="title">直播</span>
                    <span class="selected hide"></span>
                </a>
            </li>
        </shiro:hasPermission>--%>
        <%--动态--%>
        <%--<shiro:hasPermission name="menu:dynamic:dynamicIndex">
            <li class="dynamic">
                <a href="<%=request.getContextPath()%>/menu/dynamic/dynamicIndex">
                    <i class="icon-heart"></i>
                    <span class="title">动态</span>
                    <span class="selected hide"></span>
                </a>
            </li>
        </shiro:hasPermission>--%>
        <%--圈子--%>
        <%--<shiro:hasPermission name="menu:circle:circlelist or menu:circle:circlepost or menu:circle:circleclassify">
            <li class="circle">
                <a href="javascript:;">
                    <i class="icon-globe"></i>
                    <span class="title">圈子</span>
                    <span class="arrow"></span>
                    <span class="selected hide"></span>
                </a>
                <ul class="sub-menu">
                    <shiro:hasPermission name="menu:circle:circleclassify">
                        <li class="circleclassify ">
                            <a href="<%=request.getContextPath()%>/menu/circle/circleclassify">圈子类别<span
                                    class=""></span></a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:circle:circlelist">
                        <li class="circlelist circlecreat">
                            <a href="<%=request.getContextPath()%>/menu/circle/circlelist">圈子列表<span
                                    class="selected"></span></a>
                        </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasPermission>--%>
        <%--商城--%>
        <%--<shiro:hasPermission name="menu:shop:goods or menu:shop:order">
            <li class="shop">
                <a href="javascript:;">
                    <i class="icon-gift"></i>
                    <span class="title">商城</span>
                    <span class="arrow "></span>
                    <span class="selected hide"></span>
                </a>
                <ul class="sub-menu">
                    <shiro:hasPermission name="menu:shop:goods">
                        <li class="goods">
                            <a href="<%=request.getContextPath()%>/menu/shop/goods">商品</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:shop:order">
                        <li class="order">
                            <a href="<%=request.getContextPath()%>/menu/shop/order">订单</a>
                        </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasPermission>--%>
        <%--用户--%>
        <shiro:hasPermission
                name="menu:appUser:allAppUser or menu:appUser:yrzAppUser or menu:appUser:dshAppUser or menu:appUser:officialAppUser">
            <li class="appUser">
                <a href="javascript:void(0)">
                    <i class="icon-user"></i>
                    <span class="title">用户管理</span>
                    <span class="arrow"></span>
                    <span class="selected hide"></span>
                </a>
                <ul class="sub-menu">
                    <shiro:hasPermission name="menu:appUser:allAppUser">
                        <li class="allAppUser">
                            <a href="<%=request.getContextPath()%>/menu/appUser/allAppUser">用户列表</a>
                        </li>
                    </shiro:hasPermission>
                    <%--<shiro:hasPermission name="menu:appUser:yrzAppUser">
                        <li class="yrzAppUser">
                            <a href="<%=request.getContextPath()%>/menu/appUser/yrzAppUser">已认证用户列表</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:appUser:dshAppUser">
                        <li class="dshAppUser">
                            <a href="<%=request.getContextPath()%>/menu/appUser/dshAppUser">待审核用户列表</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:appUser:officialAppUser">
                        <li class="officialAppUser">
                            <a href="<%=request.getContextPath()%>/menu/appUser/officialAppUser">官方用户列表</a>
                        </li>
                    </shiro:hasPermission>--%>
                </ul>
            </li>
        </shiro:hasPermission>
        <%--消息--%>
        <shiro:hasPermission name="menu:message:messageIndex">
            <li class="message">
                <a href="<%=request.getContextPath()%>/menu/message/messageIndex">
                    <i class="icon-comment"></i>
                    <span class="title">消息</span>
                    <span class="selected hide"></span>
                </a>
            </li>
        </shiro:hasPermission>
        <%--banner推荐--%>
        <%--<shiro:hasPermission
                name="menu:banner:activitybanner or menu:banner:livebanner or menu:banner:circlebanner or menu:banner:packagebanner or menu:banner:dynamicbanner">
            <li class="banner">
                <a href="javascript:;">
                    <i class="icon-star-empty"></i>
                    <span class="title">banner推荐</span>
                    <span class="arrow "></span>
                    <span class="selected hide"></span>
                </a>
                <ul class="sub-menu">
                    <shiro:hasPermission name="menu:banner:activitybanner">
                        <li class="activitybanner">
                            <a href="<%=request.getContextPath()%>/menu/banner/activitybanner">活动banner推荐</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:banner:livebanner">
                        <li class="livebanner">
                            <a href="<%=request.getContextPath()%>/menu/banner/livebanner">直播banner推荐</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:banner:circlebanner">
                        <li class="circlebanner">
                            <a href="<%=request.getContextPath()%>/menu/banner/circlebanner">圈子banner推荐</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:banner:packagebanner">
                        <li class="packagebanner">
                            <a href="<%=request.getContextPath()%>/menu/banner/packagebanner">背包banner推荐</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:banner:dynamicbanner">
                        <li class="dynamicbanner">
                            <a href="<%=request.getContextPath()%>/menu/banner/dynamicbanner">动态banner推荐</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:banner:userbanner">
                        <li class="userbanner">
                            <a href="<%=request.getContextPath()%>/menu/banner/userbanner">用户banner推荐</a>
                        </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasPermission>--%>
        <%--管理员管理--%>
        <shiro:hasPermission name="menu:sys:admin or menu:sys:roleList">
            <li class="sys">
                <a href="javascript:;">
                    <i class="icon-tasks"></i>
                    <span class="title">管理员管理</span>
                    <span class="arrow "></span>
                    <span class="selected hide"></span>
                </a>
                <ul class="sub-menu">
                    <shiro:hasPermission name="menu:sys:admin">
                        <li class="admin">
                            <a href="<%=request.getContextPath()%>/menu/sys/admin">管理员列表</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="menu:sys:roleList">
                        <li class="roleList">
                            <a href="<%=request.getContextPath()%>/menu/sys/roleList">权限等级管理</a>
                        </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasPermission>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>
