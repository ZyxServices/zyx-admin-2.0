<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>历史记录</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-历史记录" name="description"/>
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

        .clearfix {
            clear: both;
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

        .historyWrap {
            padding: 0 10px;
        }

        .historyWrap ul li {
            padding: 10px 0;
            border-bottom: 1px solid #e6e6e6;
            position: relative;
        }

        .historyWrap ul li img {
            max-width: 140px;
            height: auto;
        }

        .historyWrap ul li .rockDetail {
            margin-left: 10px;
        }

        .historyWrap ul li .rockDetail .time {
            position: absolute;
            bottom: 20px;
        }

        .historyWrap ul li .rockDetail h3 {
            margin-bottom: 10px;
            font-size: 15px;
        }

        .historyWrap ul li .rockDetail p {
            color: #999;
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
                <img id="userAvatar" src="<%=request.getContextPath()%>/images/bg/phone/header.jpg">
            </li>
            <li>
                <h4 id="userNickname">土拨鼠</h4>
            </li>
            <li class="honor">
                我已经攀岩了<span id="recordCount">20</span>次，我厉害吧
            </li>
        </ul>
    </div>
    <%--历史记录--%>
    <div class="historyWrap">
        <ul id="historyUl">
            <%--<li>
                <div class="fl">
                    <img src="<%=request.getContextPath()%>/images/bg/phone/history-left.png">
                </div>
                <div class="rockDetail fl">
                    <h3>绵阳鹰嘴攀岩岩场</h3>
                    <p>难度：<span>5.0</span> 01:20:10</p>
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
                    <p>难度：
                        <sapn>5.0</sapn>
                        01:20:10
                    </p>
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
                    <p>难度：
                        <sapn>5.0</sapn>
                        01:20:10
                    </p>
                    <p class="time">2016-9-20</p>
                </div>
                <div class="clearfix"></div>
            </li>--%>
        </ul>
    </div>
    <a href="javascript:void (0)" class="challenge">我也要记录攀岩次数</a>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/dataformat.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/share/share_index.js"></script>
<script>
    //接口调用遍历
    share(1, function (res) {
        if (res.user.avatar) {
            $("#userAvatar").attr("src", "http://image.tiyujia.com/" + res.user.avatar)
        }
        $("#userNickname").html(res.user.nickname);
        $("#recordCount").html(res.recordCount);
        var _records = res.records;
        var objLi = '';
        var _historyImg = ''
        for (var i = 0; i < _records.length; i++) {
            if(_records[i].imgUrls){
//                _historyImg = "http://image.tiyujia.com/"+_records[i].imgUrls;
                _historyImg = "<%=request.getContextPath()%>/images/bg/phone/header.jpg";
            }else{
                _historyImg = "<%=request.getContextPath()%>/images/bg/phone/header.jpg";
            }
            objLi += '<li> ' +
                    '<div class="fl">' +
                    '<img src='+_historyImg+'>' +
                    '</div>' +
                    '<div class="rockDetail fl">' +
                    '<h3>'+_records[i].venueName+'</h3>' +
                    '<p>难度：<span>'+_records[i].level+'</span>'+secondTrans(_records[i].spendTime)+'</p>' +
                    '<p class="time">'+timeFormat(_records[i].createTime)+'</p>' +
                    '</div>' +
                    '<div class="clearfix"></div>' +
                    '</li>'
        }
        $("#historyUl").append(objLi);
    })
    function timeFormat(data) {
        return new Date(data).format("mm-dd HH:MM:ss");
    }
    /*将秒转化为时分秒*/
    function secondTrans(time) {
        var minute = parseInt(time/60);/*分*/
        var second = time%60 == 0?"00":time%60;/*秒*/
        var hour = '';
        if(minute >= 60){/*超过一个小时*/
            hour = parseInt(minute/60);
            minute = parseInt(minute%60) == 0 ? "00":parseInt(minute%60);
            return hour+":"+minute+":"+second;
        }else {
            return '00:'+minute+':'+second;
        }
    }
</script>
</html>