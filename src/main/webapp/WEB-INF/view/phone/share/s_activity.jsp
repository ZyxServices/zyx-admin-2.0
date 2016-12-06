<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>活动详情</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-活动详情" name="description"/>
    <meta content="趣攀岩" name="author"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tiyujia/index.css"/>
    <style type="text/css">
        * {
            padding: 0;
            margin: 0;
            font-family: "微软雅黑";
        }

        .fl {
            float: left;
        }

        .fr {
            float: right;
        }

        .clearfix {
            clear: both;
        }

        .padding-top-bottom-10 {
            padding: 10px 0;
        }

        .activityWrap {
            padding: 0 10px;
        }

        .imageWrap {
            padding: 10px 0;
            border-bottom: 1px solid #e6e6e6;
        }

        .imageWrap img {
            width: 100%;
        }

        .imageWrap .freeSword {
            color: #00AA88;
        }

        .imageWrap h3 {
            padding: 10px 0;
        }

        .contentWrap {
            padding-top: 10px;
            border-bottom: 1px solid #e6e6e6;
        }

        .locationWrap span {
            display: inline-block;
            height: 30px;
            line-height: 30px;
        }

        .locationWrap img {
            width: 24px;
            height: 24px;
            margin-top: 3px;
        }
    </style>
</head>
<body>
<div class="activityWrap">
    <!--image-->
    <div class="imageWrap">
        <img src="<%=request.getContextPath()%>/images/bg/phone/phone-detail.png" id="imgShow">
        <div><h4 id="title">[登山]登顶5000米，来挑战吗？<span class="fr freeSword" id="paymentType">免费</span></h4></div>
        <div class="clearfix"></div>
    </div>
    <!--content-->
    <div class="contentWrap">
        <p id="descContent">
            本周六想在成都龙岩山爬一次5.9的路线，本人攀岩萌新一枚，找一个大神带带我
        </p>
    </div>
    <!--location-->
    <div class="locationWrap padding-top-bottom-10">
        <p class=""><img class="fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_time@2x.png"><span id="activityTime">本周六14点12分-15点12分</span>
        </p>
        <div class="clearfix"></div>
        <p class=""><img class="fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_phone@2x.png"><span id="phone">15252525252</span>
        </p>
        <div class="clearfix"></div>
        <p class=""><img class="fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_adress@2x.png"><span id="address">成都市体育馆二号馆三楼篮球中心场地</span>
        </p>
    </div>
</div>
<%--评论--%>
<div class="container-fluid content venues">
    <div class="row" style="margin-top: 20px">
        <div id="v-comments" class="col-xs-12">
        </div>
        <button type="button" class=" footer-btn">查看更多精彩内容，使劲搓这里</button>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/dataformat.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/share/share_index.js"></script>
<script>
    //接口调用遍历
    shareCommon.Ajax( function (res) {
        var _activity = res.activity;
        $("#imgShow").attr("src","http://image.tiyujia.com/"+_activity.imgUrls);
        if(_activity.paymentType == 0){
            $("#paymentType").html("奖励")
        }else if(_activity.paymentType == 1){
            $("#paymentType").html("免费")
        }else{
            $("#paymentType").html("AA")
        }
        $("#title").html(_activity.title);
        $("#activityTime").html(timeFormat(_activity.startTime)+"—"+timeFormat(_activity.endTime))
        $("#descContent").html(_activity.descContent);
        $("#address").html(_activity.address);
        $("#v-comments").html(shareCommon.commentsFormatter(res.comments))
    })
    function timeFormat(data) {
        return new Date(data).format("mm-dd HH:MM:ss");
    }
</script>
</html>