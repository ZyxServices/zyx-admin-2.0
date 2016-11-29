<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>活动详情</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-活动详情" name="description"/>
    <meta content="趣攀岩" name="author"/>
    <style type="text/css">
        *{
            padding: 0;
            margin: 0;
            font-family: "微软雅黑";
        }
        .fl{
            float: left;
        }
        .fr{
            float: right;
        }
        .clearfix{
            clear: both;
        }
        .padding-top-bottom-10{
            padding: 10px 0;
        }
        .container{
            padding: 0 10px;
        }
        .imageWrap{
            padding: 10px 0;
            border-bottom: 1px solid #ccc;
        }
        .imageWrap img{
            width: 100%;
        }
        .imageWrap .freeSword{
            color: #00AA88;
        }
        .imageWrap h3{
            padding: 10px 0;
        }
        .imageWrap .startTimeWord{
            color: #bbb;
        }
        .contentWrap{
            border-bottom: 1px solid #ccc;
        }
        .locationWrap span{
            display: inline-block;
            height: 30px;
            line-height: 30px;
        }
        .locationWrap img{
            width: 24px;
            height: 24px;
            margin-top: 3px;
        }
    </style>
</head>
<body>
<div class="container">
    <!--image-->
    <div class="imageWrap">
        <img src="<%=request.getContextPath()%>/images/bg/phone/phone-detail.png">
        <div><h3>[登山]登顶5000米，来挑战吗？<span class="fr freeSword">免费</span></h3></div>
        <div class="clearfix"></div>
        <span class="startTimeWord">2016.9.13——2016.9.16</span>
    </div>
    <!--content-->
    <div class="contentWrap padding-top-bottom-10">
        <p class="padding-top-bottom-10">
            本周六想在成都龙岩山爬一次5.9的路线，本人攀岩萌新一枚，找一个大神带带我
        </p>
        <p>攀岩地点：<span>成都龙岩山攀岩馆</span></p>
    </div>
    <!--location-->
    <div class="locationWrap padding-top-bottom-10">
        <p class=""><img class="fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_time@2x.png"><span>本周六14点12分-15点12分</span></p>
        <div class="clearfix"></div>
        <p class=""><img class="fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_phone@2x.png"><span>15252525252</span></p>
        <div class="clearfix"></div>
        <p class=""><img class="fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_adress@2x.png"><span>成都市体育馆二号馆三楼篮球中心场地</span></p>
    </div>
</div>
</body>
<script type="text/javascript" src="../../js/share/s_venues.js"></script>
<script>
    //接口调用遍历
    share(function(res){
        console.log(res,1)
    })
</script>
</html>