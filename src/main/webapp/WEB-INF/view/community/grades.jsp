<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/11/9
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>等级管理</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-等级管理" name="description" />

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
        <div class="container-fluid" id="bannerList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        等级管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">等级</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="banner-list">
                <div class="row-fluid">
                    <div class="span12 margin-bottom-10">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="addGrades()">添加等级</a>
                    </div>
                </div>
                <div>
                    <select id="appType" class="form-control" onchange="initGradersTable()">
                        <option value="1">趣攀岩</option>
                    </select>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="homepage-list-table">

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--添加修改等级modal--%>
<div class="modal fade hide" id="addGradesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>添加等级</h3></div>
    <div class="modal-body">
        <form id="lineForm" class="form-horizontal"  method="post"  role="form" enctype="multipart/form-data">
            <input type="hidden" name="id">
            <div class="control-group form-group">
                <label class="control-label">等级名称</label>
                <div class="controls form-group">
                    <input  type="text" name="name" />
                </div>
            </div>

            <div class="control-group form-group">
                <label class="control-label">阶级</label>
                <div class="controls ">
                    <input type="text" name="step"/>
                </div>
            </div>

            <div class="control-group form-group">
                <label class="control-label">等级最小积分 </label>
                <div class="controls">
                    <input type="text" name="minScore"/>
                </div>

            </div>
            <div class="control-group form-group">
                <label class="control-label">等级最大积分 </label>
                <div class="controls">
                    <input type="text" name="maxScore"/>
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
<script type="text/javascript" src="../../js/community/grades.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
