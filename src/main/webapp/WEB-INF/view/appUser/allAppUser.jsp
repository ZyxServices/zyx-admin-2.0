<%--
  Created by IntelliJ IDEA.
  User: 文楷
  Date: 2016/7/15
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户操作</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-用户操作" name="description"/>
    <meta content="" name="author"/>
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/datetimepicker.css" />
    <link rel="stylesheet" href="../../css/tiyujia/style.css" />
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        用户管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">用户管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#" id="listType">用户列表</a></li>
                    </ul>
                </div>
            </div>
            <div class="live_index">
                <button class="create_live btn btn-default btn-lg margin-bottom-10">创建用户</button>
                <div>
                    <select class="form-control" id="officialSelect" onchange="changeOfficialTable()">
                        <option value="1">官方用户</option>
                        <option value="2">普通用户</option>
                    </select>
                </div>
                <div class="live_manage">
                    <table id="allApp_user_table" class="table table-hover"
                           data-pagination="true"
                           data-show-refresh="true"
                           data-show-toggle="true"
                           data-showColumns="true"
                           data-detail-formatter="detailFormatter">
                    </table>
                </div>
            </div>
            <%--创建用户--%>
            <div class="create_liveType row-fluid">
                <form class="form-horizontal" role="form" id="createAppUserForm" enctype="multipart/form-data">
                    <div class="control-group form-group">

                        <input type="hidden" name="id" id="userId">
                        <label class="control-label">昵称</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="nickname" id="nickname" placeholder="输入昵称"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">性别</label>

                        <div class="controls">
                            <label class="radio"><input type="radio" name="sex" checked value="1">男</label>
                            <label class="radio"><input type="radio" name="sex" value="0">女</label>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">电话</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="phone" id="phone" placeholder="输入手机号"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">密码</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="password" id="password" placeholder="输入密码"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">头像</label>

                        <div class="controls col-xs-5">
                            <input type="file" class="hideInput" name="avatar" id="avatar">
                            <a class="btn btn-default" href="javascript:void (0)" id="photoCover" onclick="$('input[id=avatar]').click();">选择文件</a>
                            <span class="help-inline required">*</span>
                            <div style="margin-top: 10px" id="imagesWrap" class="showImg">
                                <img id="avatarImg" src="">
                            </div>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">生日</label>

                        <div class="controls col-xs-5">
                            <input type="hidden" name="birthday" id="birthday">
                            <input type="text" class="span6" name="birthdayStr" id="birthdayStr" placeholder="输入生日"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">所在地</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="address" id="address" placeholder="输入地址"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">签名</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="signature" id="signature" placeholder="输入签名"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>
                    <div class="margin-bottom-25">
                        <button type="button" id="createButton" class="btn" onclick="beginCreate()">确认
                        </button>
                        <button type="button" class="btn" onclick="window.location.reload();">返回</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 用户推荐开始 -->
<!-- 模态框（Modal） -->
<!-- 用户推荐结束 -->
<jsp:include page="../public/common-footer.jsp"/>
</body>
<script src="../../js/app.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/appUser/allAppUser.js"></script>