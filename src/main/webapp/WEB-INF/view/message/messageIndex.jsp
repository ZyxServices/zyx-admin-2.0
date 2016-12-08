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
  <title>消息管理</title>
  <meta charset="utf-8" />
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />

  <meta content="体育家-消息管理" name="description" />

  <meta content="" name="author" />

  <!-- BEGIN GLOBAL MANDATORY STYLES -->
  <jsp:include page="../public/common-styles.jsp"/>
  <link rel="stylesheet" href="../../css/datetimepicker.css" />
  <link rel="stylesheet" href="../../css/tiyujia/style.css" />
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
  <jsp:include page="../public/nav.jsp"/>
  <div class="page-content">
    <div class="container-fluid" id="messageList">
      <div class="row-fluid">
        <div class="span12">
          <h3 class="page-title">
            消息管理<small>statistics and more</small>
          </h3>
          <ul class="breadcrumb">
            <li>
              <i class="icon-home"></i>
              <a href="javascript:void(0)">消息</a>
              <i class="icon-angle-right"></i>
            </li>
            <li><a href="#">列表</a></li>
          </ul>
        </div>
      </div>
      <div id="banner-list">
        <div class="row-fluid margin-bottom-10">
          <div class="span12 margin-bottom-10">
            <a class="btn btn-default" href="javascript:void(0)" onclick="createMessage()">创建消息</a>
            <%--<a class="btn btn-default" href="javascript:void(0)" onclick="batchDelete()">批量删除</a>--%>
          </div>
        </div>
        <div class="row-fluid">
          <div class="span12 responsive">
            <table id="message-list-table">
              <thead>
              <tr>
                <th data-checkbox="true"></th>
                <th data-field="id">ID</th>
                <th data-field="content">消息内容</th>
                <th data-field="type">消息类别</th>
                <th data-field="pushTime" data-formatter="timeFormat">推送时间</th>
                <th data-field="done">推送是否完成</th>
                <th data-formatter="operate" data-events="operateEvents">操作</th>
              </tr>
              </thead>
            </table>
          </div>
        </div>
      </div>

    </div>
    <%--创建消息--%>
    <div class="container-fluid hide" id="messageCreate">
      <div class="row-fluid">
        <div class="span12">
          <h3 class="page-title">
            消息管理<small>statistics and more</small>
          </h3>
          <ul class="breadcrumb">
            <li>
              <i class="icon-home"></i>
              <a href="javascript:void(0)">消息</a>
              <i class="icon-angle-right"></i>
            </li>
            <li><a href="#">创建</a></li>
          </ul>
        </div>
      </div>
      <div id="banner-edit">
        <div class="row-fluid">

          <form class="form-horizontal" role="form" id="messageForm">
            <div class="control-group form-group">
              <label class="control-label">推送内容</label>
              <div class="controls col-xs-5">
                <textarea class="span6" rows="5" name="content"></textarea>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">消息类别</label>
              <div class="controls">
                <select name="type" class="span6">
                  <option value="0">系统消息</option>
                  <option value="1">日常推送</option>
                </select>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">推送时间</label>
              <div class="controls">
                <label class="radio"><input type="radio" checked value="0" name="pushType" onchange="isSetPushTime(this)">立刻</label>
                <label class="radio"><input type="radio" value="1" name="pushType" onchange="isSetPushTime(this)">设定</label>
                <input type="text" id="pushTime" name="pushTime" disabled placeholder="选择设定时间">
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:(0)" onclick="confirmMessage()" class="btn btn-default">确定</a>
                <a href="javascript:(0)" class="btn btn-default" onclick="window.location.reload();">返回</a>
              </div>
            </div>
          </form>

        </div>

        <!-- END DASHBOARD STATS -->

      </div>

    </div>
    <!-- END PAGE CONTAINER-->

  </div>

  <!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/message/message.js"></script>
<script>

  jQuery(document).ready(function() {

    App.init(); // initlayout and core plugins

  });

</script>
</body>
</html>