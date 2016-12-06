<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/11/9
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>城市管理</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-城市管理" name="description" />

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
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        城市管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">城市</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="banner-list">
                <div class="row-fluid">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="addCitys()">添加城市</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="city-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="cityName">城市名称</th>
                                <th data-field="state">是否启用</th>
                                <th data-field="createTime" data-formatter="timeFormat">城市名称创建时间</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--添加城市--%>
<div class="modal hide" id="addCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>添加城市</h3></div>
    <div class="modal-body">
        <form id="addCityForm" class="form-horizontal" role="form" enctype="multipart/form-data">

            <div class="control-group form-group">
                <label class="control-label">城市名称</label>
                <div class="controls col-xs-5">
                    <input type="text" name="cityName"/>
                    <span class="help-inline required">*</span>
                </div>
            </div>

            <%--<div class="control-group">
                <label class="control-label">启用状态</label>
                <div class="controls">
                    <label class="radio"><input type="radio" checked value="0" name="state">启用</label>
                    <label class="radio"><input type="radio" value="1" name="state">禁用</label>
                </div>
            </div>--%>

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
<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/city/city.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
