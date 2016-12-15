<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/12/5
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>版本管理</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="去攀岩-版本管理" name="description" />

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
                        版本管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">版本</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="banner-list">
                <div class="row-fluid margin-bottom-10">
                    <div class=" margin-bottom-10">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createVersion()">创建版本</a>
                    </div>
                    <div>
                        <select id="platform" onchange="platformType()">
                            <option value="1">Android</option>
                            <option value="2">IOS</option>
                        </select>
                        <select id="appType" onchange="changeAppType()">
                            <option value="1">趣攀岩</option>
                        </select>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="version-list-table">
                            <thead>
                            <tr>
                                <th data-field="id">ID</th>
                                <th data-field="appType">app类型</th>
                                <th data-field="version">版本号</th>
                                <th data-field="platform">系统类型</th>
                                <th data-field="createTime">创建时间</th>
                                <th data-field="publishTime">发布时间</th>
                                <th data-field="downloadUrl">下载地址</th>
                                <th data-field="notes">注释</th>
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
<%--创建版本modal--%>
<div class="modal hide" id="addVersionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>添加版本</h3></div>
    <div class="modal-body">
        <form id="versionForm" class="form-horizontal" role="form" enctype="multipart/form-data">

            <input type="hidden" name="id" id="updateId">
            <div class="control-group">
                <label class="control-label">系统</label>
                <div class="controls">
                    <select name="platform" id="createPlatform">
                        <option value="1">Android</option>
                        <option value="2">IOS</option>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">app类型</label>
                <div class="controls">
                    <select name="platform" id="CreateAppType">
                        <option value="1">去攀岩</option>
                    </select>
                </div>
            </div>

            <div class="control-group form-group">
                <label class="control-label">版本号</label>
                <div class="controls col-xs-5">
                    <input name="version" type="text" id="version" style="width: 220px;"/>
                    <span class="help-inline required">*</span>
                </div>
            </div>

            <div class="control-group form-group">
                <label class="control-label">创建时间</label>

                <div class="controls col-xs-5">
                    <input name="createTime" type="text" id="createTime" style="width: 220px;"/>
                    <span class="help-inline required">*</span>
                </div>
            </div>

            <div class="control-group form-group">
                <label class="control-label">发布时间</label>

                <div class="controls col-xs-5">
                    <input name="publishTime" type="text" id="publishTime" disabled style="width: 220px;"/>
                    <span class="help-inline required">*</span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">下载地址</label>

                <div class="controls">
                    <input name="downloadUrl" type="text" id="downloadUrl" style="width: 220px;"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">注释</label>

                <div class="controls">
                    <input name="notes" type="text" id="notes" style="width: 220px;"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:void(0)" class="btn btn-default" id="sureLine"
                   onclick="confirmVersion()">确定</a>
                <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>取消</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/version/version.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
