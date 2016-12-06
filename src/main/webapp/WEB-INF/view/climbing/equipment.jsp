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
    <title>装备控</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-装备控" name="description"/>

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
                        装备控
                        <small>statistics and more</small>
                    </h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0 12px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                </div>
            </div>
            <div id="activity-list">
                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)"
                           onclick=" $('#createModify').show(); $('#activityList').hide();">发布帖子</a>
                        <a class="btn btn-default" href="javascript:void(0)" onclick="manageLabel()">装备管理标签</a>
                    </div>
                </div>
                <h3 class="page-title"> 装备帖子管理</h3>
                <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                <div class="controls">
                    <select id="e_user" name="labelId" onchange="initTable()">
                        <option value="1">官方</option>
                        <option value="0">用户</option>
                    </select>
                </div>

                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="dynamic_table"></table>
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

                        发布帖子

                    </h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="create-modify">

                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">

                    <form id="updateCreateFrom" enctype="multipart/form-data" class="form-horizontal" role="form">
                        <div class="control-group form-group officeUser">
                            <label class="control-label labelControl ">选择官方账户户</label>

                            <div class="">
                                <select id="choiceUser" name="officialId" class="span3">
                                </select>
                            </div>
                        </div>
                        <div class="control-group form-group officeUser">
                            <label class="control-label labelControl"
                                   style="width: 100px;margin-right: 10px;text-align: left">标题</label>

                            <div class=" col-xs-5">
                                <input type="text" id="title" name="title" class="span3" placeholder="请输入活动标题"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>


                        <div class="control-group form-group">
                            <label class="control-label labelControl">内容</label>

                            <div class=" summernote">
                                <div class="span6 col-xs-5">
                                    <div id="equipment-summernote"></div>
                                    <input id="desc" type="text" class="hideInput" name="content" value="">
                                </div>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label labelControl">标签</label>

                            <div class="">
                                <select id="v_label" name="labelId">
                                </select>
                            </div>
                        </div>
                        <hr>
                    </form>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-default" id="Czs" onclick="operateEventssssss.createEquip()">确定
                            </button>
                            <a href="javascript:void(0)" class="btn btn-default"
                               onclick="window.location.reload();">返回</a>
                        </div>
                    </div>

                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>

        <div class="container-fluid hide" id="manageLabel">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        装备标签管理

                    </h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                    <div class="row-fluid margin-bottom-10">
                        <div class="span6">
                            <a class="btn btn-default" href="javascript:void(0)"
                               onclick="operateEventssssss.addLabel()">添加标签</a>
                        </div>
                        <div>
                            <select id="label-appType" class="form-control" onchange="initTable()">
                                <option value="1">趣攀岩</option>
                            </select>
                        </div>
                    </div>
                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->
            <table id="labelTabel"></table>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default">确定</button>
                    <a href="javascript:void(0)" class="btn btn-default" onclick="window.location.reload();">返回</a>
                </div>
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
<script type="text/javascript" src="../../js/climbing/equipment.js"></script>
<script>

    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
