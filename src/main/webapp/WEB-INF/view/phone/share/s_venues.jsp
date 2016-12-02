<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tiyujia/index.css"/>
    <title>攀岩馆详情</title>
</head>
<style>
    .venues .banner {
        background-image: url(<%=request.getContextPath()%>/images/venues-banner.png);
        background-repeat: no-repeat;
        padding-top: 5px;
        padding-left: 25px;
        /*margin: 0px 0px 0 10px;*/
        max-width: 100%;
        width: 100%;
        height: 35%;
        max-height: 35%;
        background-size: 95% 100%;
        background-position: 8px;
    }
</style>
<body>
<div class="container-fluid content venues">
    <div class="row">
        <div id="v-imgUrls" class="col-xs-12 banner" style="">
            <button type="button" id="v-label" class="v-label-default">室内</button>
        </div>
        <div class="col-xs-12 ptb10 pb">
            <span id="v-name" style="font-size: 16px;color: #000000;font-weight: 500">成都理工大学攀岩基地</span>
            <button id="v-label2" type="button" class="v-label">室内</button>
        </div>
        <div class="col-xs-12 pb10" style="position: relative">
            <div style="bottom: 8px;position: absolute;color:#999999">难度系数</div>
            <div id="v-level" style="margin-left: 65px;">
                <img src="<%=request.getContextPath()%>/images/solid.png" alt="" width="15px" height="15px"/>
                <img src="<%=request.getContextPath()%>/images/solid.png" alt="" width="15px" height="15px"/>
                <img src="<%=request.getContextPath()%>/images/hollow.png" alt="" width="15px" height="15px"/>
                <img src="<%=request.getContextPath()%>/images/hollow.png" alt="" width="15px" height="15px"/>
                <img src="<%=request.getContextPath()%>/images/hollow.png" alt="" width="15px" height="15px"/>
            </div>
        </div>
    </div>
    <div class="row " style="margin-top: 10px;padding-bottom: 10px">
        <div class="col-xs-12">
            <p><img class="v-img fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_adress@2x.png"></p>
            <span id="v-address">成都市体育馆二号馆三楼篮球中心场地【我去过】</span>
        </div>
        <div class=" col-xs-12">
            <p><img class="v-img fl" src="<%=request.getContextPath()%>/images/bg/phone/btn_phone@2x.png"></p>
            <span id="v-phone"> 13681929137</span>
        </div>
    </div>
    <div class="row pb20" style="margin-top: 10px">
        <div class="col-xs-12"><h4>岩场概况</h4></div>
        <div id="v-description" class="col-xs-12 overflow">
            方日报讯 （记者/金朱玺 通讯员/陈建族
            钟雯）4日，记者从2016国际攀联中国广州世界青年攀岩锦标赛组委会获悉：由国际攀岩联合会主办、广州市体育局承办的2016国际攀联中国广州世界青年攀岩锦标赛将于11月7日至13日在广州大学城体育中心攀岩场举行。
            本次比赛设速度攀岩、难度攀岩、攀石比赛，分男女少年A组、少年B组、青年组，共18个单项。据官方统计，本届比赛共吸引了来自41个国家和地区近500名运动员报名参赛，参赛队伍和参赛运动员数量均创国内举办的各类攀岩赛事之最。
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div id="v-paths" class="col-xs-12">

        </div>

    </div>
    <div class="row pb20" style="margin-top: 20px">
        <div class="col-xs-12"><h4>开发背景</h4></div>
        <div class="col-xs-12">
            本次比赛设速度攀岩、难度攀岩、攀石比赛，分男女少年A组、少年B组、青年组，共18个单项。据官方统计，本届比赛共吸引了来自41个国家和地区近500名运动员报名参赛，
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div id="v-comments" class="col-xs-12">
        </div>
        <button type="button" class=" footer-btn">查看更多精彩内容，使劲搓这里</button>
    </div>
</div>
</body>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/share/share_index.js"></script>
<script src="../../js/dataformat.js" type="text/javascript"></script>
<script>
    //接口调用遍历
    shareCommon.Ajax(4, function (res) {
        var data = {
            dataPush: function (obj, res) {
                for (var i in obj) {
                    obj[i].formatter != undefined ? $('#' + obj[i].id + '').html(obj[i].formatter(eval(obj[i].field))) : $('#' + obj[i].id + '').html(eval(obj[i].field))
                }
            },
            typeFormatter: function (data) {
                return data == 2 ? '室外' : '室内';
            },
            timeFormatter: function (data) {
                return new Date(data).format("yyyy-mm-dd HH:MM:ss")
            },
            levelFormatter: function (data) {
                var solidHtml = '<img style="margin: 0 2px;" src="<%=request.getContextPath()%>/images/solid.png" alt="" width="15px" height="15px"/>'
                var hollowHtml = '<img style="margin: 0 2px;" src="<%=request.getContextPath()%>/images/hollow.png" alt="" width="15px" height="15px"/>'
                return new String(solidHtml).repeat(data) + new String(hollowHtml).repeat(5 - data)
            },
            imgFormatter: function (data) {
                $('#v-imgUrls').css({'background-image': 'url( http://image.tiyujia.com/' + data + ')'})
            },
            pathsFormatter: function (data) {
                var pathsHtml = '<h4>岩场路线</h4>';
                for (var i in data) {
                    var lineImg;
                    data.url != undefined ? lineImg = "http://image.tiyujia.com/" + data.url : lineImg = " http://image.tiyujia.com/" + res.venue.imgUrls;
                    var pathHtml = ' <div class="col-xs-12  meg v-line ">' +
                            ' <div class="col-xs-12 p0 " style=" border-bottom: #e5e5e5 1px solid;padding-bottom: 10px">' +
                            ' <div class="col-xs-5 col-md-3  col-sm-1 p0 line-details1 ">' +
                            '<img  src=' + lineImg + ' alt="" width="120px" height="80px"/></div>' +
                            ' <div class="col-xs-7 col-md-9  col-sm-11 p0 line-details2">' +
                            ' <div class="v-title">' + data[i].path + '</div>' +
                            ' <div class="lineDeveloper time" style="height: 20px;overflow: hidden;">开线者：<span  >' + data[i].developer + '</span></div>' +
                            ' <br>' +
                            ' <div class="col-xs-12 p0" style="padding-top: 2px;">难度系数' +
                            ' <span >' + data[i].level + '</span></div> </div> </div> </div>';
                    pathsHtml += pathHtml;
                }
                return pathsHtml;
            }
        }
        data.dataPush([{field: 'res.venue.type', id: 'v-label', formatter: data.typeFormatter},
            {field: 'res.venue.type', id: 'v-label2', formatter: data.typeFormatter},
            {field: 'res.venue.name', id: 'v-name'},
            {field: 'res.venue.description', id: 'v-description'},
            {field: 'res.venue.phone', id: 'v-phone'},
            {field: 'res.venue.address', id: 'v-address'},
            {field: 'res.venue.level', id: 'v-level', formatter: data.levelFormatter},
            {field: 'res.venue.imgUrls', id: 'v-imgUrls', formatter: data.imgFormatter},
            {field: 'res.paths', id: 'v-paths', formatter: data.pathsFormatter},
            {field: 'res.comments', id: 'v-comments', formatter: shareCommon.commentsFormatter},
        ], res)
        //字符串重复
        String.prototype.repeat = function (n) {
            var _this = this;
            var result = '';
            for (var i = 0; i < n; i++) {
                result += _this;
            }
            return result;
        }
    })
</script>
</html>