<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/11/9
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>场馆管理管理</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-攀岩馆场馆管理" name="description"/>

    <meta content="攀岩馆场馆管理" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/summernote.css"/>
    <link rel="stylesheet" href="../../css/tiyujia/style.css"/>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=bc83b9475a5b54ab35e22bbaf1b0ab06&plugin=AMap.Autocomplete"></script>
</head>
<style type="text/css">
    body {
        margin: 0;
        height: 100%;
        width: 100%;
        position: absolute;
        font-size: 12px;
    }

    h6 {
        margin: 0;
    }

    #mapContainer {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
    }

    #tip {
        background-color: #fff;
        border: 1px solid #ccc;
        padding-left: 10px;
        padding-right: 2px;
        position: absolute;
        /*min-height:65px;*/
        top: 10px;
        font-size: 12px;
        right: 10px;
        border-radius: 3px;
        overflow: hidden;
        line-height: 20px;
        /*min-width:400px;*/
    }

    #tip input[type="button"] {
        background-color: #0D9BF2;
        height: 25px;
        text-align: center;
        line-height: 25px;
        color: #fff;
        font-size: 12px;
        border-radius: 3px;
        outline: none;
        border: 0;
        cursor: pointer;

    }

    #tip input[type="text"] {
        height: 25px;
        border: 1px solid #ccc;
        padding-left: 5px;
        border-radius: 3px;
        outline: none;
        margin-top: 10px;
    }

    #pos {
        height: 70px;
        background-color: #fff;
        padding-left: 10px;
        padding-right: 10px;
        position: absolute;
        font-size: 12px;
        right: 10px;
        bottom: 30px;
        border-radius: 3px;
        line-height: 30px;
        border: 1px solid #ccc;
    }

    #pos input {
        border: 1px solid #ddd;
        height: 23px;
        border-radius: 3px;
        outline: none;
    }

    /*.modal-body{*/
    /*max-width: 100%;*/
    /*}*/
    #result1 {
        max-height: 300px;
    }
</style>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid" id="venuesList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        攀岩馆场馆管理
                        <small>statistics and more</small>
                    </h3>
                </div>
                <div>
                    <div class="margin-bottom-10"><a class="btn btn-default" href="javascript:void(0)"
                                                     onclick="typeInfo()">录入场馆信息</a></div>
                    <span>城市</span>
                    <select id="city" class="form-control" onchange="initTable()">
                        <option value="">全部</option>
                    </select>
                    <span>app版本</span>
                    <select id="appType" class="form-control" onchange="initTable()">
                        <option value="1">趣攀岩</option>
                    </select>
                </div>
            </div>
            <div id="banner-list">
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="dynamic_table"></table>
                    </div>
                </div>
            </div>

        </div>
        <div class="container-fluid hide" id="lineList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        添加场馆线路
                        <small>statistics and more</small>
                    </h3>
                </div>
                <div class="margin-bottom-10"><a class="btn btn-default" href="javascript:void(0)"
                                                 onclick="operateEventssssss.addLineModal()">添加线路</a></div>
            </div>
            <div class="row-fluid">
                <div class="span12 responsive">
                    <table id="lineTabel"></table>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <a href="javascript:void(0)" class="btn btn-default"
                       onclick="window.location.reload();">返回</a>
                </div>
            </div>
        </div>
        <%--编辑修改场馆信息--%>
        <div class="container-fluid hide" id="createModify">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title" id="pageTitle">
                        上传场馆信息
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">场馆</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#" id="listType">创建</a></li>
                    </ul>
                </div>
            </div>
            <div id="create-modify">
                <div class="row-fluid">
                    <form id="createVenue" enctype="multipart/form-data" class="form-horizontal" role="form">
                        <div class="control-group">
                            <label class="control-label">场馆名称</label>

                            <div class="controls">
                                <input type="text" id="v_name" name="name" class="span6" placeholder="请输入场馆名称"/>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">难度系数</label>

                            <div class="controls col-xs-5">
                                <select  type="text" id="v_title" name="level">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label id="v_imgtitle" class="control-label">上传场馆封面图</label>

                            <div id="v_img" class="controls col-xs-5">
                                <div id="imgWrap">
                                    <input id="lefile" type="file">
                                    <input id="imgUrls" type="text" class="hide" name="imgUrls">
                                    <span class="help-inline required">*</span>
                                </div>
                                <div style="margin-top: 10px;" id="v_imagesWrap" class="showImg">
                                    <img id="images" src="">
                                </div>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">岩厂概述</label>

                            <div id="ven_des" class="controls summernote">
                                <div class="span6 col-xs-5">
                                    <div id="activity-summernote"></div>
                                    <input id="v_desc" type="text" class="hideInput" name="description" value="">
                                </div>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">开发背景</label>

                            <div id="ven_background" class="controls summernote">
                                <div class="span6 col-xs-5">
                                    <div id="background-summernote"></div>
                                    <input id="v_background" type="text" class="hideInput" name="background" value="">
                                </div>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">场馆地址</label>

                            <div class="controls">
                                <input type="text" id="v_address" name="address" class="span6" placeholder="请输入场馆地址"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">经度</label>

                            <div class="controls">
                                <input type="text" id="v_longitude" name="longitude" class="span6"
                                       placeholder="" readonly />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">纬度</label>

                            <div class="controls">
                                <input type="text" id="v_latitude" name="latitude" class="span6" placeholder=""
                                       readonly/>
                                <a id="openMap" onclick="$('#mapModel').modal('show')" class="controls"
                                   style="margin-left: 0;display: block;cursor:hand">打开地图</a>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">咨询电话</label>

                            <div class="controls">
                                <input type="text" id="v_phone" name="phone" class="span6" placeholder="请输入正确的手机号码"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">所属城市</label>

                            <div class="controls">
                                <select id="v_city" name="city">
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">所属城市</label>

                            <div class="controls">
                                <select id="v_type" name="type">
                                    <option value="1">室内</option>
                                    <option value="2">室外</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">app版本</label>

                            <div class="controls">
                                <select name="appType" class="span6" id="app" style="height: 34px">
                                    <option value="1">趣攀岩</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group hide" id="addChoice">
                            <label class="control-label"></label>

                            <div class="controls">
                                <input type="text" class="span3" id="requiredVal"><a href="javascript:void(0)"
                                                                                     class="btn btn-default"
                                                                                     onclick="createRequired()">确定</a>
                                <span class="help-inline required" id="userRequiredInput">*</span>
                            </div>
                        </div>


                    </form>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-default" id="czs" onclick="operateEventssssss.createVenue()">确定
                            </button>
                            <a href="javascript:void(0)" class="btn btn-default"
                               onclick="window.location.reload();">返回</a>
                        </div>
                    </div>
                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<%--添加线路modal--%>
<div class="modal fade hide" style=" width:60%;left:20%;right:20%" id="addLineModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>添加线路</h3></div>
    <div class="modal-body">
        <form id="lineForm" class="form-horizontal" role="form" enctype="multipart/form-data">

            <div class="control-group">
                <label class="control-label">线路名称</label>

                <div class="controls">
                    <input name="path" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">开线者</label>

                <div class="controls">
                    <input name="developer" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">开线时间</label>

                <div class="controls">
                    <input name="developTime" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">路线类型</label>

                <div class="controls">
                    <input name="pathType" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">难度对应得分</label>

                <div class="controls">
                    <input name="score" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">难度等级</label>

                <div class="controls">
                    <input name="level" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">线路长度</label>

                <div class="controls">
                    <input name="pathLength" type="text"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">线路图</label>

                <div class="controls">
                    <input id="lineUrl" type="hidden" name="url"/>
                    <input type="file" id="lineImage"/>

                    <div id="lingImgdiv"></div>

                </div>

            </div>
            <div class="control-group">
                <label class="control-label">线路位置</label>

                <div class="controls">
                    <input name="location" type="text"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:void(0)" class="btn btn-default" id="sureLine"
                   onclick="operateEventssssss.submitLineForm()">确定</a>
                <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>取消</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade hide" id="mapModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>经纬度获取</h3></div>
    <div class="modal-body" style="height: 700px">
        <div>

            <div id="container"></div>
            <div id="mapContainer"></div>
            <div id="tip">
                <b>请输入关键字：</b>
                <input type="text" id="keyword" name="keyword" value="" onkeydown='keydown(event)' style="width: 55%;"/>
                <button onclick="search()">搜索</button>
                <div id="result1" name="result1"></div>
            </div>
            <%--<div id="pos">--%>
            <%--<b>鼠标左键在地图上单击获取坐标</b>--%>
            <%--<br><div>X：<input type="text" id="lngX" name="lngX" value=""/>&nbsp;Y：<input type="text" id="latY" name="latY" value=""/></div>--%>
            <%--</div>--%>
        </div>
    </div>
    <div class="modal-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>关闭</a>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/climbing/venues.js"></script>
//地图
<script src="../../js/public/map.js" type="text/javascript"></script>
</body>
</html>
