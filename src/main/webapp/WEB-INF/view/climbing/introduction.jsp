<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/11/9
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教程管理</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-求约管理" name="description"/>

    <meta content="属于活动" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/summernote.css"/>
    <link rel="stylesheet" href="../../css/datetimepicker.css"/>
    <link rel="stylesheet" href="../../css/tiyujia/style.css"/>
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid" id="activityList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        教程管理
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">教程</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="activity-list">
                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createcourse()">发布教程攻略</a>
                        <a class="btn btn-default" onclick="courseLabel()">管理标签</a>
                        <a class="btn btn-default" href="javascript:void(0)" onclick="batchDelete()">批量删除</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table class="table table-hover" id="Course_table">
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%--活动创建修改预览--%>
        <div class="container-fluid hide" id="createModify">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title" id="pageTitle">

                        教程管理
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">教程</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#" id="listType">创建</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="create-modify">
                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">
                    <form class="form-horizontal" role="form" id="courseCreateFrom"
                          enctype="multipart/form-data" method="post">
                        <%--     <input name="createId" type="hidden" value="20"/>--%>
                        <input name="circleId" type="hidden"/>
                        <%--        <input name="state" type="hidden" value="-2">--%>

                        <div class="control-group form-group">
                            <label class="control-label">标题</label>
                            <div class="controls col-xs-6">
                                <input type="text" name="title" class="span6"/>
                                <span class="help-inline">*</span>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">内容</label>
                            <div class="controls summernote">
                                <div class="span6 col-xs-5">
                                    <div id="course-summernote"></div>
                                    <input id="desc" type="text" class="hideInput" name="content">
                                </div>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">标签</label>
                            <div class="controls  col-xs-6">
                                <select name="labelId" data-placeholder="请选择标签哦" id="labelId" data-rel="chosen"
                                        style="width:350px;" class="chzn-select" tabindex="7">

                                </select>
                                <span class="help-inline">*</span>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">类型</label>
                            <div class="controls">
                                <select class="span6" id="examine" name="courseType"<%-- onchange="isReviewed()"--%>>
                                    <option value="0">图文</option>
                                    <option value="1">视频</option>
                                </select>
                            </div>
                        </div>


                        <div class="control-group form-group">
                            <label class="control-label"></label>
                            <div class="col-sm-offset-2 col-sm-10">
                                <button class="btn btn-default" id="courseSure">确定</button>
                                <a href="javascript:void(0)" onclick="window.location.reload();"
                                   class="btn btn-default">返回</a>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>


    </div>
    <!-- END PAGE -->
</div>

<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/climbing/introduction.js"></script>
<script>
    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });
</script>
</body>
</html>
