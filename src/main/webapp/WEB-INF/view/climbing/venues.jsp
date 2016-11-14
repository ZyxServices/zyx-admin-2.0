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
</head>
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
                    <select class="form-control" onchange="changeBannerTable(this)">
                        <option value="1">北京</option>
                        <option value="2">上海</option>
                        <option value="3">成都</option>
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
        <div class="container-fluid hide" id="lineList" >
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        添加场馆线路
                        <small>statistics and more</small>
                    </h3>
                </div>
                <div class="margin-bottom-10"><a class="btn btn-default" href="javascript:void(0)" onclick="operateEventssssss.addLineModal()">添加线路</a></div>
            </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="lineTabel" ></table>
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
                                <input type="text" id="v_title" name="level" class="span6" placeholder="请输入场馆难度系数"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">上传场馆封面图</label>

                            <div class="controls col-xs-5">
                                <div id="imgWrap">
                                    <input id="lefile" type="file">
                                    <input id="imgUrls" type="text" class="hide" name="imgUrls">
                                    <span class="help-inline required">*</span>
                                </div>
                                <div style="margin-top: 10px" id="v_imagesWrap" class="showImg">
                                    <img id="images" src="">
                                </div>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">场馆介绍</label>

                            <div class="controls summernote">
                                <div class="span6 col-xs-5">
                                    <div id="activity-summernote"></div>
                                    <input id="v_desc" type="text" class="hideInput" name="description" value="">
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
                                <input type="text" id="v_longitude" name="longitude" class="span6" placeholder=""/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">纬度</label>

                            <div class="controls">
                                <input type="text" id="v_latitude" name="latitude" class="span6" placeholder=""/>
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
                                    <option value="成都">成都</option>
                                    <option value="北京">北京</option>
                                    <option value="上海">上海</option>
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
<div class="modal fade hide" id="addLineModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
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
                <label   class="control-label">开线者</label>

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
                <label  class="control-label">难度对应得分</label>

                <div class="controls">
                    <input name="score" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label  class="control-label">难度等级</label>

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
                    <input id="lineUrl" type="hidden"  name="url" />
                    <input type="file" id="lineImage"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:void(0)" class="btn btn-default" id="sureLine" onclick="operateEventssssss.submitLineForm()">确定</a>
                <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>取消</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/climbing/venues.js"></script>
<script>

    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
