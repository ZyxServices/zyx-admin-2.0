<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排行榜分享</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-排行榜分享" name="description"/>
    <meta content="趣攀岩" name="author"/>
    <style type="text/css">
        *{
            padding: 0;
            margin: 0;
            font-family: "微软雅黑";
        }
        ul{
            list-style: none;
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
        .header{
            padding: 50px 0 20px 0;
            background: url("<%=request.getContextPath()%>/images/bg/phone/header_bg.png") no-repeat 100% 100%;
        }
        .header img{
            width: 80px;
            height: 80px;
            border-radius: 100%;
        }
        .header ul{
            text-align: center;
            color: #fff;
        }
        .header ul li{
            margin-top: 10px;
        }
        .header ul li.honor{
            font-size: 14px;
            color: #cdcdcd;
        }
        .header ul li.honor span{
            font-size: 16px;
            color: #fff;
        }
        .historyWrap{
            padding: 0 10px;
        }
        .historyWrap ul li{
            padding: 10px 0;
            border-bottom:1px solid #e6e6e6;
            position: relative;
        }
        .historyWrap ul li img{
            max-width: 140px;
            height: auto;
        }
        .historyWrap ul li .rockDetail{
            margin-left: 10px;
        }
        .historyWrap ul li .rockDetail .time{
            position: absolute;
            bottom: 20px;
        }
        .historyWrap ul li .rockDetail h3{
            margin-bottom: 10px;
        }
        .historyWrap ul li .rockDetail p{
            color: #999;
        }
        .challenge{
            display: inline-block;
            width: 100%;
            height: 50px;
            background: #ff702a;
            line-height: 50px;
            text-align: center;
            text-decoration: none;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="container">
    <!--image-->
    <div class="header">
        <ul>
            <li>
                <img src="<%=request.getContextPath()%>/images/bg/phone/header.jpg">
            </li>
            <li>
                <h4>土拨鼠</h4>
            </li>
            <li class="honor">
                我已经攀岩了<span>20</span>次，我厉害吧
            </li>
        </ul>
    </div>
    <%--榜单--%>
    <div class="historyWrap">
        <ul>
            <li>
                <div class="fl">
                    <img src="<%=request.getContextPath()%>/images/bg/phone/history-left.png">
                </div>
                <div class="rockDetail fl">
                    <h3>绵阳鹰嘴攀岩岩场</h3>
                    <p>难度：<sapn>5.0</sapn> 01:20:10</p>
                    <p class="time">2016-9-20</p>
                </div>
                <div class="clearfix"></div>
            </li>
            <li>
                <div class="fl">
                    <img src="<%=request.getContextPath()%>/images/bg/phone/history-left.png">
                </div>
                <div class="fl rockDetail">
                    <h3>绵阳鹰嘴攀岩岩场</h3>
                    <p>难度：<sapn>5.0</sapn> 01:20:10</p>
                    <p class="time">2016-9-20</p>
                </div>
                <div class="clearfix"></div>
            </li>
            <li>
                <div class="fl">
                    <img src="<%=request.getContextPath()%>/images/bg/phone/history-left.png">
                </div>
                <div class="fl rockDetail">
                    <h3>绵阳鹰嘴攀岩岩场</h3>
                    <p>难度：<sapn>5.0</sapn> 01:20:10</p>
                    <p class="time">2016-9-20</p>
                </div>
                <div class="clearfix"></div>
            </li>
        </ul>
        <a href="javascript:void (0)" class="challenge">我也要记录攀岩次数</a>
    </div>
</div>
</body>
</html>