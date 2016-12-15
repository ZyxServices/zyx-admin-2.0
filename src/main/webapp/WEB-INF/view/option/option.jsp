<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/12/15
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>意见管理</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="趣攀岩-意见管理" name="description" />

    <meta content="" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/tiyujia/style.css" />
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid" id="optionList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        意见管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">意见</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="option-list">
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="option-list-table">
                            <thead>
                            <tr>
                                <th data-field="id">ID</th>
                                <th data-field="nickname">用户名</th>
                                <th data-field="createTime" data-formatter="timeFormat">创建时间</th>
                                <th data-field="content">内容</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
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
<script type="text/javascript" src="../../js/option/option.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
