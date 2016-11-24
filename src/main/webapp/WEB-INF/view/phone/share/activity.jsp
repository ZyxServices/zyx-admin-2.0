<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>活动详情</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-活动详情" name="description"/>
    <meta content="去攀岩" name="author"/>
    <style type="text/css">
        .fl{
            float: left;
        }
        .fr{
            float: right;
        }
        .clearfix{
            clear: both;
        }
        .container{
            padding: 0 10px;
        }
        .imageWrap img{
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <!--image-->
    <div class="imageWrap">
        <img src="<%=request.getContextPath()%>/images/bg/phone/phone-detail.png">
    </div>
    <!--content-->
    <div class="contentWrap">
        <div><h2 class="fl">[登山]登顶5000米，来挑战吗？</h2><span class="fr"></span></div>
        <div class="clearfix"></div>
        <span>2016.9.13——2016.9.16</span>
        <p>
            本周六想在成都龙岩山爬一次5.9的路线，本人攀岩萌新一枚，找一个大神带带我
        </p>
        <p>攀岩地点：<span>成都龙岩山攀岩馆</span></p>
    </div>
    <!--location-->
    <div class="locationWrap">
        <div>
            <p class="fl"><img src="<%=request.getContextPath()%>/images/bg/phone/btn_time@2x.png"><span>本周六14点12分-15点12分</span></p>
            <p class="fl"><img src="<%=request.getContextPath()%>/images/bg/phone/btn_phone@2x.png"><span>15252525252</span></p>
        </div>
        <div class="clearfix"></div>
        <div>
            <img src="<%=request.getContextPath()%>/images/bg/phone/btn_adress@2x.png"><span>成都市体育馆二号馆三楼篮球中心场地</span>
        </div>
    </div>
</div>
</body>
</html>