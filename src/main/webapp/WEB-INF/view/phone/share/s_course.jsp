<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tiyujia/index.css"/>
    <title>教程详情</title>
</head>
<body>
<div class="container-fluid content">
    <div class="row title">
        <div class="col-xs-12">
            <h3>攀岩装备大介绍-有图有真相</h3>
        </div>
        <div class="col-xs-12">方日报讯 （记者/金朱玺 通讯员/陈建族
            钟雯）4日，记者从2016国际攀联中国广州世界青年攀岩锦标赛组委会获悉：由国际攀岩联合会主办、广州市体育局承办的2016国际攀联中国广州世界青年攀岩锦标赛将于11月7日至13日在广州大学城体育中心攀岩场举行。
            本次比赛设速度攀岩、难度攀岩、攀石比赛，分男女少年A组、少年B组、青年组，共18个单项。据官方统计，本届比赛共吸引了来自41个国家和地区近500名运动员报名参赛，参赛队伍和参赛运动员数量均创国内举办的各类攀岩赛事之最。
            本次比赛也吸引了国内外多位优秀选手参加。在报名选手中，有美籍日裔攀岩天才阿诗玛，以及去年世青赛全能亚军、广州小将潘愚非，他将冲击男子少年A组难度攀岩及攀石赛桂冠。
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-xs-12"><h4>精彩评论</h4></div>
        <div class="col-xs-12 pr0 meg">
            <div class="col-xs-2 .col-md-1  p0 phone1">
                <img class="avatar" src="<%=request.getContextPath()%>/images/avatar.jpg">
            </div>
            <div class="col-xs-10 .col-md-11  p0 phone9">
                小美爱吃肉
                <div class="grade">初窥门径</div>
                <br>
                <span class="time">40分钟前</span>
            </div>
            <div class="col-xs-10 col-xs-offset-1 mes-content p0 phone-offset-1">
                如果你无法用简介的语言表达它，说明你真的还不够了解它，热爱它就多多关注吧！
            </div>
        </div>
        <div class="col-xs-12 pr0 meg">
            <div class="col-xs-2 p0 phone1">
                <img class="avatar" src="<%=request.getContextPath()%>/images/avatar.jpg">
            </div>
            <div class="col-xs-10 p0 phone9">
                小美爱吃肉
                <div class="grade">初窥门径</div>
                <br>
                <span class="time">40分钟前</span>
            </div>
            <div class="col-xs-10 col-xs-offset-1 mes-content p0 phone-offset-1">
                如果你无法用简介的语言表达它，说明你真的还不够了解它，热爱它就多多关注吧！
            </div>
        </div>
        <button type="button" class=" footer-btn">查看更多精彩内容，使劲搓这里</button>
    </div>

</div>

</body>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/share/share_index.js"></script>
<script>
    //接口调用遍历
    share(function(res){
        console.log(res,1)
    })
</script>
</html>