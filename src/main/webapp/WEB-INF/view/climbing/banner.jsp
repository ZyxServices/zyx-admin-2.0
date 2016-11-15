<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/11/9
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>banner管理</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-攀岩馆banner管理" name="description" />

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
                        banner管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">攀岩馆banner</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
                <div>
                    <select class="form-control" onchange="changeBannerTable(this)">
                        <option value="1">首页banner</option>
                        <option value="2">求约banner</option>
                    </select>
                </div>
            </div>
            <div id="banner-list">
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="banner-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="model">banner模块</th>
                                <th data-field="area">展示区域</th>
                                <th data-field="sequence">banner位置</th>
                                <th data-field="imageUrl">图片</th>
                                <th data-field="state">状态</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

        </div>
        <%--编辑banner--%>
        <div class="container-fluid hide" id="bannerEdit">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        攀岩馆banner管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">攀岩馆banner</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">编辑banner</a></li>
                    </ul>
                </div>
            </div>
            <div id="banner-edit">
                <div class="row-fluid">

                    <form class="form-horizontal" role="form" id="bannerForm">
                        <input type="hidden" name="id" id="devaId">
                        <div class="control-group form-group">
                            <label class="control-label">banner模块</label>
                            <div class="controls col-xs-5">
                                <select name="model" id="model" disabled class="span6">
                                    <option value="1">教程攻略</option>
                                    <option value="2">求约</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">展示区域</label>
                            <div class="controls">
                                <select name="area" id="area" onchange="changeDevArea(this)" class="span6">
                                    <option value="1">首页</option>
                                    <option value="2">求约</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">banner位置</label>
                            <div class="controls col-xs-5">
                                <select class="span6" id="sequence" name="sequence">

                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">活动原有封面图</label>
                            <div class="controls" id="preImage">

                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">封面</label>
                            <div class="controls">
                                <input type="hidden" name="imageUrl" id="imageUrl">
                                <input id="lefile" type="file" class="hideInput">
                                <a class="btn btn-default" href="javascript:void (0)" id="photoCover" onclick="$('input[id=lefile]').click();">选择图片</a>
                                <div style="margin-top: 10px" id="imagesWrap" class="showImg">
                                    <img id="images" src="">
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">推荐状态</label>
                            <div class="controls">
                                <label class="radio"><input type="radio" checked value="1" name="state">激活</label>
                                <label class="radio"><input type="radio" value="0" name="state">未激活</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <a href="javascript:(0)" id="confirmDeva" class="btn btn-default">确定</a>
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
<%--<script type="text/javascript" src="../../js/banner/bannerCommon.js"></script>--%>
<script type="text/javascript" src="../../js/climbing/banner.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>

