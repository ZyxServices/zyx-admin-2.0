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
                            <a href="<%=request.getContextPath()%>/menu/climbing/introduction">教程列表</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="activity-list">
                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createLabel()">创建标签</a>
                        <a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
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
        </div>

    </div>
    <!-- END PAGE -->
</div>
<%--创建标签--%>
<div class="modal fade hide" id="addGradesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>创建标签</h3></div>
    <div class="modal-body">
        <form id="labelcreate" class="form-horizontal" method="post" role="form" enctype="multipart/form-data">
            <div class="control-group form-group">
                <label class="control-label">标签名称</label>
                <div class="controls form-group">
                    <input type="text" name="labelName"/>
                </div>
            </div>
            <div class="control-group form-group">
                <label class="control-label"></label>
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" id="confirmCmd">确定</button>
                    <a href="javascript:void(0)" onclick="window.location.reload();"
                       class="btn btn-default">返回</a>
                </div>
            </div>
        </form>
    </div>
    <%-- <div class="modal-footer">
         <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
                 <a href="javascript:void(0)" class="btn btn-default" id="confirmCmd">确定</a>
                 <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>取消</a>
             </div>
         </div>
     </div>--%>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/climbing/courselabel.js"></script>
<script>
    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });
</script>
</body>
</html>
