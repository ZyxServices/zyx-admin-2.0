<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排行榜分享</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-排行榜分享" name="description"/>
    <meta content="趣攀岩" name="author"/>
    <style type="text/css">
        * {
            padding: 0;
            margin: 0;
            font-family: "微软雅黑";
        }

        ul {
            list-style: none;
        }

        .fl {
            float: left;
        }

        .fr {
            float: right;
        }

        .header {
            padding: 50px 0 20px 0;
            background: url("<%=request.getContextPath()%>/images/bg/phone/header_bg.png") no-repeat 100% 100%;
            background-size: 100% 100%;
        }

        .header img {
            width: 80px;
            height: 80px;
            border-radius: 100%;
        }

        .header ul {
            text-align: center;
            color: #fff;
        }

        .header ul li {
            margin-top: 10px;
        }

        .header ul li.honor {
            font-size: 14px;
            color: #cdcdcd;
        }

        .header ul li.honor span {
            font-size: 16px;
            color: #fff;
        }

        .rankWrap {
            padding: 0 10px;
        }

        .rankWrap ul {
            position: relative;
        }

        .rankWrap ul li .avatar {
            width: 50px;
            height: 50px;
            border-radius: 100%;
        }

        .rankWrap ul li {
            line-height: 50px;
            padding: 20px 0;
            border-bottom: 1px solid #e6e6e6;
        }

        .rankWrap ul li .ranking {
            width: 30px;
            text-align: center;
            font-size: 12px;
        }

        .rankWrap ul li:nth-child(2) .ranking {
            background: url("<%=request.getContextPath()%>/images/bg/phone/medal_1.png") no-repeat;
            background-size: 20px 25px;
            background-position: 5px 16px;
            color: #fff;
        }

        .rankWrap ul li:nth-child(3) .ranking {
            background: url("<%=request.getContextPath()%>/images/bg/phone/medal_2.png") no-repeat;
            background-size: 20px 25px;
            background-position: 5px 16px;
            color: #fff;
        }

        .rankWrap ul li:nth-child(4) .ranking {
            background: url("<%=request.getContextPath()%>/images/bg/phone/medal_3.png") no-repeat;
            background-size: 20px 25px;
            background-position: 5px 16px;
            color: #fff;
        }

        .rankWrap ul li .personInfo {
            padding-left: 90px;
            font-size: 12px;
        }

        .rankWrap ul li .personInfo .title {
            font-size: 14px;
        }

        .rankWrap ul li .rankName {
            padding: 0 5px;
            background: #ff702a;
            color: #fff;
            border-radius: 10px;
            margin-left: 5px;
        }

        .cret {
            position: absolute;
            top: 0;
            left: -10px;
        }

        .honorVal, .rankWrap ul li:nth-child(2).ranking {
            color: #ff702a;
        }

        .challenge {
            display: inline-block;
            width: 100%;
            height: 50px;
            background: #ff702a;
            line-height: 50px;
            text-align: center;
            text-decoration: none;
            color: #fff;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="container">
    <!--image-->
    <div class="header">
        <ul>
            <li>
                <img id="avatar" src="<%=request.getContextPath()%>/images/bg/phone/header.jpg">
            </li>
            <li>
                <h4 id="nickname">土拨鼠</h4>
            </li>
            <li class="honor">
                战胜了<span id="beat">1221</span>人，获得<span id="number">22</span>名
            </li>
        </ul>
    </div>
    <%--榜单--%>
    <div class="rankWrap">
        <ul id="rankingListUl">
            <%--<li>
                <span><img class="cret" src="<%=request.getContextPath()%>/images/bg/phone/cret.png"></span>
                <span class="ranking fl">22</span>
                <img class="avatar fl" src="<%=request.getContextPath()%>/images/bg/phone/header.jpg">
                <p class="personInfo">
                    <span class="title">萌妹子</span>
                    <span class="rankName">登堂入室</span>
                    <span class="fr">荣誉值：<span class="honorVal">334</span></span>
                </p>
            </li>
            <li>
                <span class="ranking fl">1</span>
                <img class="avatar fl" src="<%=request.getContextPath()%>/images/bg/phone/header.jpg">
                <p class="personInfo">
                    <span class="title">萌妹子</span>
                    <span class="rankName">登堂入室</span>
                    <span class="fr">荣誉值：<span>334</span></span>
                </p>
            </li>
            <li>
                <span class="ranking fl">2</span>
                <img class="avatar fl" src="<%=request.getContextPath()%>/images/bg/phone/header.jpg">
                <p class="personInfo">
                    <span class="title">萌妹子</span>
                    <span class="rankName">登堂入室</span>
                    <span class="fr">荣誉值：<span>334</span></span>
                </p>
            </li>
            <li>
                <span class="ranking fl">3</span>
                <img class="avatar fl" src="<%=request.getContextPath()%>/images/bg/phone/header.jpg">
                <p class="personInfo">
                    <span class="title">萌妹子</span>
                    <span class="rankName">登堂入室</span>
                    <span class="fr">荣誉值：<span>334</span></span>
                </p>
            </li>--%>
        </ul>
    </div>
    <a href="javascript:void (0)" class="challenge">我要去挑战他</a>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="../../js/share/share_index.js"></script>
<script>
    //接口调用遍历
    shareCommon.Ajax(function (res) {
        if (res.own.avatar) {
            $("#avatar").attr("src", "http://image.tiyujia.com/" + res.own.avatar);
        }
        $("#nickname").html(res.own.nickname);
        $("#beat").html(res.beat);
        $("#number").html(res.own.number);
        var ownAvatar = '';
        if(res.own.avatar){
            ownAvatar = "http://image.tiyujia.com/"+res.own.avatar;
        }else{
            ownAvatar = "<%=request.getContextPath()%>/images/bg/phone/header.jpg";
        }
        var liObj = '<li>' +
                '<span><img class="cret" src="<%=request.getContextPath()%>/images/bg/phone/cret.png"></span>' +
                '<span class="ranking fl">'+res.own.number+'</span>' +
                '<img class="avatar fl" src='+ownAvatar+'>' +
                '<p class="personInfo">' +
                '<span class="title">'+res.own.nickname+'</span>' +
                '<span class="rankName">'+res.own.levelName+'</span>' +
                '<span class="fr">荣誉值：<span class="honorVal">'+res.own.totalScore+'</span></span>' +
                '</p>' +
                '</li>';
        var _tops = res.tops;
        var avatar = '';
        for(var i = 0;i < _tops.length; i++){
            if(_tops[i].avatar){
                avatar = "http://image.tiyujia.com/"+_tops[i].avatar;
            }else{
                avatar = "<%=request.getContextPath()%>/images/bg/phone/header.jpg";
            }
            liObj += '<li>' +
                    '<span class="ranking fl">'+_tops[i].number+'</span>' +
                    '<img class="avatar fl" src='+avatar+'>' +
                    '<p class="personInfo">' +
                    '<span class="title">'+_tops[i].nickname+'</span>' +
                    '<span class="rankName">'+_tops[i].levelName+'</span>' +
                    '<span class="fr">荣誉值：<span>'+_tops[i].totalScore+'</span></span>' +
                    '</p>' +
                    '</li>'
        }
        $("#rankingListUl").append(liObj);

    })
</script>
</html>