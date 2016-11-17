<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/11/9
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>求约管理</title>
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
                        活动管理
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">求约</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div>
                <div class="row-fluid">
                    <div class="span12 margin-bottom-10">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createActivity()">创建活动</a>
                        <a class="btn btn-default" href="javascript:void(0)" onclick="batchDelete()">批量删除</a>
                    </div>
                    <form  id="searchCondition" enctype="multipart/form-data" class="form-inline" role="form">
                        <select name="type" id="searchType">
                            <option value="0">官方</option>
                            <option value="1">用户</option>
                        </select>

                        <select name="activityType" id="searchActivityType">
                            <option value="1">求约</option>
                            <option value="2">求带</option>
                        </select>

                        <select name="paymentType" id="searchPaymentType">
                            <option value="0">奖励</option>
                            <option value="1">免费</option>
                            <option value="2">AA</option>
                        </select>

                        <select name="status" id="searchStatus">
                            <option value="0">正在报名</option>
                            <option value="1">已结束</option>
                        </select>
                        <a class="btn btn-default" href="javascript:void(0)" onclick="startFilter()">开始筛选</a>
                    </form>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table class="table table-hover" id="appointment-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">id</th>
                                <th data-field="title">活动标题</th>
                                <th data-field="activityType">活动类型</th>
                                <th data-field="status">活动状态</th>
                                <th data-field="imgUrls">活动图片</th>
                                <th data-field="userName">发布人</th>
                                <th data-field="createTime" data-formatter="timeFormat">发布时间</th>
                                <th data-field="address">活动地点</th>
                                <th data-field="paymentType">付费类型</th>
                                <th data-field="zanNum">点赞数</th>
                                <th data-field="viewNum">评论数</th>
                                <th data-field="joinNum">已报名</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%--活动创建修改预览--%>
        <div class="container-fluid hide" id="createModify">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title" id="pageTitle">
                        活动管理
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">活动</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#" id="listType">创建</a></li>
                    </ul>
                </div>
            </div>
            <div id="create-modify">
                <div class="row-fluid">
                    <form id="updateCreateFrom" enctype="multipart/form-data" class="form-horizontal" role="form">
                        <input type="hidden" name="id" id="avtivityId" value="">
                        <%--代表官方发布类型--%>
                        <input type="hidden" name="type" value="0">

                        <div class="control-group form-group">
                            <label class="control-label">活动名称</label>
                            <div class="controls col-xs-5">
                                <input type="text" id="title" name="title" class="span6" placeholder="请输入活动名称"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">封面</label>
                            <div class="controls col-xs-5">
                                <div id="imgWrap">
                                    <%--图片的路径--%>
                                    <input id="image" type="text" class="hideInput" name="imageUrls">
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
                            <div class="controls col-xs-5 summernote">
                                <%--<div class="span6 col-xs-5">--%>
                                    <%--<div id="activity-summernote"></div>--%>
                                    <textarea name="descContent" class="span6" id="descContent" rows="10"></textarea>
                                    <%--<input id="desc" type="text" class="hideInput" name="descContent">--%>
                                <%--</div>--%>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">活动开始时间</label>
                            <div class="controls col-xs-5">
                                <input type="text" class="span6" id="activityStartTime" name="startTime"
                                       placeholder="活动开始时间"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">活动截止时间</label>
                            <div class="controls col-xs-5">
                                <input type="text" class="span6" disabled id="activityEndTime" name="endTime"
                                       placeholder="活动截止时间"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">报名截止时间</label>
                            <div class="controls col-xs-5">
                                <input type="text" class="span6" disabled id="signEndTime" name="lastTime" placeholder="报名截止时间"/>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">报名人数</label>
                            <div class="controls col-xs-5">
                                <input type="text" id="maxPeople" name="maxPeople" class="span6" placeholder="请输入最大人数"/>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">活动类型</label>
                            <div class="controls col-xs-5">
                                <select name="activityType" id="activityType" class="span6" style="height: 34px">
                                    <option value="1">求约</option>
                                    <option value="2">求带</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">集合地点</label>
                            <div class="controls">
                                <input type="text" id="address" name="address" class="span6" placeholder="请输入详细地址"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">付费类型</label>
                            <div class="controls">
                                <select name="paymentType" class="span6" id="paymentType" style="height: 34px" onchange="changePaymentType(this)">
                                    <option value="0">奖励</option>
                                    <option value="1">免费</option>
                                    <option value="2">AA</option>
                                </select>
                                <input type="text" name="price" placeholder="输入AA金额" id="price" class="hide">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button class="btn btn-default" id="czS">确定</button>
                                <a href="javascript:void(0)" class="btn btn-default"
                                   onclick="window.location.reload();">返回</a>
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
<div class="modal fade hide" id="activityRecommend" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>用户推荐</h3></div>
    <div class="modal-body">
        <form id="devaForm" class="form-horizontal" role="form" enctype="multipart/form-data">

            <input type="hidden" name="model" value="2"/>
            <input type="hidden" name="modelId" id="modelId"/>
            <input type="hidden" name="imageUrl" id="imageUrl"/>

            <div class="control-group form-group">
                <label class="control-label">将此活动推荐到</label>
                <div class="controls col-xs-5">
                    <select id="area" name="area" onchange="changeDevArea(this)">
                        <option value="1">首页</option>
                        <option value="2">求约</option>
                    </select>
                </div>
            </div>

            <div class="control-group form-group">
                <label class="control-label">banner位置</label>
                <div class="controls col-xs-5">
                    <select id="sequence" name="sequence">
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">推荐状态</label>
                <div class="controls">
                    <label class="radio"><input type="radio" checked value="1" name="state">激活</label>
                    <label class="radio"><input type="radio" value="0" name="state">未激活</label>
                </div>
            </div>

        </form>
    </div>
    <div class="modal-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:void(0)" class="btn btn-default" id="confirmCmd">确定</a>
                <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>取消</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade hide" id="upload" aria-hidden="true" data-backdrop="static">
    <div class="modal-body">
        <p id="uploadContent"></p>
    </div>
</div>

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>

<script src="../../js/app.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/climbing/appointment.js"></script>
<script>

    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
