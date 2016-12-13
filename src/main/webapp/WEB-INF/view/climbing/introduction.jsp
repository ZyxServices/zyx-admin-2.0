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
                        <input type="hidden" name="id" id="courseId">

                        <div class="control-group">
                            <label class="control-label">选择官方用户</label>
                            <div class="controls">
                                <select id="choiceOfficial" name="officialId" class="span6">
                                </select>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">标题</label>
                            <div class="controls col-xs-6">
                                <input type="text" name="title" class="span6"/>
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">封面</label>
                            <div class="controls col-xs-5">
                                <div id="imgWrap">
                                    <%--图片的路径--%>
                                    <input id="image" type="text" class="hideInput" name="imgUrl">
                                    <%--文件类型--%>
                                    <input id="lefile" type="file" class="hideInput" name="imageR">
                                    <a class="btn btn-default" href="javascript:void (0)" id="photoCover"
                                       onclick="$('input[id=lefile]').click();">选择文件</a>
                                    <span class="help-inline required">*</span>
                                </div>
                                <div style="margin-top: 10px" id="imagesWrap" class="showImg">
                                    <img id="images" src="">
                                </div>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">内容</label>
                            <div class="controls summernote">
                                <div class="span6 col-xs-5" id="summernotContent">
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
                        <div class="control-group">
                            <label class="control-label">类型</label>
                            <div class="controls">
                                <select class="span6" id="examine" name="courseType">
                                    <option value="图文">图文</option>
                                    <option value="视频">视频</option>
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
<!-- 推荐弹窗-->
<div class="modal fade hide" role="dialog" aria-labelledby="gridSystemModalLabel" id="CourseModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                <h4 class="modal-title" id="gridSystemModalLabel">教程攻略推荐</h4>
            </div>
            <div class="modal-body" style="padding:10px 20px ;">
                <form class="form-horizontal form_bottom" role="form" id="courseRecommend"
                      enctype="multipart/form-data" method="post" style="margin-bottom:0px;">

                    <div class="control-group">
                        <label class="control-label">攀岩馆banner排序：</label>
                        <div class="controls">
                            <select id="courseSelect" name="sequence"></select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">推荐图片</label>
                        <div class="controls showImg">
                            <img id="devaImage" src="">
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <input type="hidden" name="imageUrl" id="imageUrl">
                            <input id="recommendFile" type="file" class="hideInput">
                            <a class="btn btn-default" href="javascript:void (0)" id="recommendPhotoCover"
                               onclick="$('input[id=recommendFile]').click();">选择图片</a>
                            <div style="margin-top: 10px" id="recommendImgWrap" class="showImg">
                                <img id="recommendImg" src="">
                            </div>
                        </div>
                    </div>


                    <input type="hidden" name="model" value="1">
                    <input type="hidden" name="modelId" id="modelId">
                    <input type="hidden" name="area" value="1">
                    <input type="hidden" name="state" value="1">

                </form>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal">取消</a>
                <a class="btn btn-primary" id="RdSures">确认</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="modal fade hide" id="upload" aria-hidden="true" data-backdrop="static">
    <div class="modal-body">
        <p id="uploadContent"></p>
    </div>
</div>
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
