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
    <title>场馆管理管理</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-攀岩馆场馆管理" name="description"/>

    <meta content="攀岩馆场馆管理" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/summernote.css"/>
    <link rel="stylesheet" href="../../css/tiyujia/style.css"/>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=bc83b9475a5b54ab35e22bbaf1b0ab06&plugin=AMap.Autocomplete"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<style type="text/css">
    body{
        margin:0;
        height:100%;
        width:100%;
        position:absolute;
        font-size:12px;
    }
    h6{
        margin: 0;
    }
    #mapContainer{
        position: absolute;
        top:0;
        left: 0;
        right:0;
        bottom:0;
    }

    #tip{
        background-color:#fff;
        border:1px solid #ccc;
        padding-left:10px;
        padding-right:2px;
        position:absolute;
        /*min-height:65px;*/
        top:10px;
        font-size:12px;
        right:10px;
        border-radius:3px;
        overflow:hidden;
        line-height:20px;
        /*min-width:400px;*/
    }
    #tip input[type="button"]{
        background-color: #0D9BF2;
        height:25px;
        text-align:center;
        line-height:25px;
        color:#fff;
        font-size:12px;
        border-radius:3px;
        outline: none;
        border:0;
        cursor:pointer;

    }

    #tip input[type="text"]{
        height:25px;
        border:1px solid #ccc;
        padding-left:5px;
        border-radius:3px;
        outline:none;
        margin-top: 10px;
    }
    #pos{
        height: 70px;
        background-color: #fff;
        padding-left: 10px;
        padding-right: 10px;
        position:absolute;
        font-size: 12px;
        right: 10px;
        bottom: 30px;
        border-radius: 3px;
        line-height: 30px;
        border:1px solid #ccc;
    }
    #pos input{
        border:1px solid #ddd;
        height:23px;
        border-radius:3px;
        outline:none;
    }
    /*.modal-body{*/
        /*max-width: 100%;*/
    /*}*/
    #result1{
        max-height:300px;
    }
</style>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid" id="venuesList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        攀岩馆场馆管理
                        <small>statistics and more</small>
                    </h3>
                </div>
                <div>
                    <div class="margin-bottom-10"><a class="btn btn-default" href="javascript:void(0)"
                                                     onclick="typeInfo()">录入场馆信息</a></div>
                    <span>城市</span>
                    <select id="city" class="form-control" onchange="changeBannerTable(this)">
                        <option value="">全部</option>
                    </select>
                </div>
            </div>
            <div id="banner-list">
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="dynamic_table"></table>
                    </div>
                </div>
            </div>

        </div>
        <div class="container-fluid hide" id="lineList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        添加场馆线路
                        <small>statistics and more</small>
                    </h3>
                </div>
                <div class="margin-bottom-10"><a class="btn btn-default" href="javascript:void(0)"
                                                 onclick="operateEventssssss.addLineModal()">添加线路</a></div>
            </div>
            <div class="row-fluid">
                <div class="span12 responsive">
                    <table id="lineTabel"></table>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <a href="javascript:void(0)" class="btn btn-default"
                       onclick="window.location.reload();">返回</a>
                </div>
            </div>
        </div>
        <%--编辑修改场馆信息--%>
        <div class="container-fluid hide" id="createModify">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title" id="pageTitle">
                        上传场馆信息
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">场馆</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#" id="listType">创建</a></li>
                    </ul>
                </div>
            </div>
            <div id="create-modify">
                <div class="row-fluid">
                    <form id="createVenue" enctype="multipart/form-data" class="form-horizontal" role="form">
                        <div class="control-group">
                            <label class="control-label">场馆名称</label>

                            <div class="controls">
                                <input type="text" id="v_name" name="name" class="span6" placeholder="请输入场馆名称"/>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">难度系数</label>

                            <div class="controls col-xs-5">
                                <input type="text" id="v_title" name="level" class="span6" placeholder="请输入场馆难度系数"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label id="v_imgtitle" class="control-label">上传场馆封面图</label>

                            <div id="v_img" class="controls col-xs-5">
                                <div id="imgWrap">
                                    <input id="lefile" type="file">
                                    <input id="imgUrls" type="text" class="hide" name="imgUrls">
                                    <span class="help-inline required">*</span>
                                </div>
                                <div style="margin-top: 10px;" id="v_imagesWrap" class="showImg">
                                    <img id="images" src="">
                                </div>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">岩厂概述</label>

                            <div id="ven_des" class="controls summernote">
                                <div class="span6 col-xs-5">
                                    <div id="activity-summernote"></div>
                                    <input id="v_desc" type="text" class="hideInput" name="description" value="">
                                </div>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">场馆地址</label>

                            <div class="controls">
                                <input type="text" id="v_address" name="address" class="span6" placeholder="请输入场馆地址"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">经度</label>

                            <div class="controls">
                                <input type="text" id="v_longitude" name="longitude" class="span6"
                                       placeholder=""/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">纬度</label>

                            <div class="controls">
                                <input type="text" id="v_latitude" name="latitude" class="span6" placeholder=""/>

                                <a id="openMap" onclick="$('#mapModel').modal('show')" class="controls"
                                   style="margin-left: 0;display: block;cursor:hand">打开地图</a>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">咨询电话</label>

                            <div class="controls">
                                <input type="text" id="v_phone" name="phone" class="span6" placeholder="请输入正确的手机号码"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">所属城市</label>

                            <div class="controls">
                                <select id="v_city" name="city">
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">所属城市</label>

                            <div class="controls">
                                <select id="v_type" name="type">
                                    <option value="1">室内</option>
                                    <option value="2">室外</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group hide" id="addChoice">
                            <label class="control-label"></label>

                            <div class="controls">
                                <input type="text" class="span3" id="requiredVal"><a href="javascript:void(0)"
                                                                                     class="btn btn-default"
                                                                                     onclick="createRequired()">确定</a>
                                <span class="help-inline required" id="userRequiredInput">*</span>
                            </div>
                        </div>


                    </form>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-default" id="czs" onclick="operateEventssssss.createVenue()">确定
                            </button>
                            <a href="javascript:void(0)" class="btn btn-default"
                               onclick="window.location.reload();">返回</a>
                        </div>
                    </div>
                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<%--添加线路modal--%>
<div class="modal fade hide" style=" width:60%;left:20%;right:20%" id="addLineModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>添加线路</h3></div>
    <div class="modal-body">
        <form id="lineForm" class="form-horizontal" role="form" enctype="multipart/form-data">

            <div class="control-group">
                <label class="control-label">线路名称</label>

                <div class="controls">
                    <input name="path" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">开线者</label>

                <div class="controls">
                    <input name="developer" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">开线时间</label>

                <div class="controls">
                    <input name="developTime" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">路线类型</label>

                <div class="controls">
                    <input name="pathType" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">难度对应得分</label>

                <div class="controls">
                    <input name="score" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">难度等级</label>

                <div class="controls">
                    <input name="level" type="text"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">线路长度</label>

                <div class="controls">
                    <input name="pathLength" type="text"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">线路图</label>

                <div class="controls">
                    <input id="lineUrl" type="hidden" name="url"/>
                    <input type="file" id="lineImage"/>

                    <div id="lingImgdiv"></div>

                </div>

            </div>
            <div class="control-group">
                <label class="control-label">线路位置</label>

                <div class="controls">
                    <input name="location" type="text"/>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:void(0)" class="btn btn-default" id="sureLine"
                   onclick="operateEventssssss.submitLineForm()">确定</a>
                <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>取消</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade hide" id="mapModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>经纬度获取</h3></div>
    <div class="modal-body" style="height: 700px">
        <div >

            <div id="container"></div>
            <div id="mapContainer" ></div>
            <div id="tip">
                <b>请输入关键字：</b>
                <input type="text" id="keyword" name="keyword" value="" onkeydown='keydown(event)' style="width: 55%;"/>  <button onclick="search()">搜索</button>
                <div id="result1" name="result1"></div>
            </div>
            <%--<div id="pos">--%>
                <%--<b>鼠标左键在地图上单击获取坐标</b>--%>
                <%--<br><div>X：<input type="text" id="lngX" name="lngX" value=""/>&nbsp;Y：<input type="text" id="latY" name="latY" value=""/></div>--%>
            <%--</div>--%>
        </div>
    </div>
    <div class="modal-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="javascript:void(0)" class="btn btn-default" data-dismiss='modal'>关闭</a>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/climbing/venues.js"></script>
//地图
<script>
    var windowsArr = [];
    var marker = [];
    var mapObj = new AMap.Map("mapContainer", {
        resizeEnable: true,
        view: new AMap.View2D({
            resizeEnable: true,
            zoom:13//地图显示的缩放级别
        }),
        keyboardEnable:false
    });
//    var clickEventListener=AMap.event.addListener(mapObj,'click',function(e,context){
//        console.log(e,context)
//        obtainXy(e.lnglat.getLng(),e.lnglat.getLat())
//        document.getElementById("v_longitude").value = x;
//        document.getElementById("v_latitude").value =y;
//    });
    AMap.plugin(['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Geocoder'], function () {
        var geocoder = new AMap.Geocoder({
            city: "010"//城市，默认：“全国”
        });
        mapObj.on('click', function (e) {
            geocoder.getAddress(e.lnglat, function (status, result) {
                console.log(result)
                obtainXy(e.lnglat.getLng(), e.lnglat.getLat(),result.regeocode.formattedAddress)
            })
            console.log(e)
        })
    })
//    mapObj.on('click', function (e) {
//        geocoder.getAddress(e.lnglat, function (status, result) {
//            obtainXy(e.lnglat.getLng(), e.lnglat.getLat(),result.regeocode.formattedAddress)
//        })
//        console.log(e)
//    })

    document.getElementById("keyword").onkeyup = keydown;
    //输入提示
    function autoSearch() {
        var keywords = document.getElementById("keyword").value;
        var auto;
        //加载输入提示插件
        AMap.service(["AMap.Autocomplete"], function() {
            var autoOptions = {
                city: "" //城市，默认全国
            };
            auto = new AMap.Autocomplete(autoOptions);
            //查询成功时返回查询结果
            if ( keywords.length > 0) {
                auto.search(keywords, function(status, result){
                    autocomplete_CallBack(result);
                });
            }
            else {
                document.getElementById("result1").style.display = "none";
            }
        });
    }

    //输出输入提示结果的回调函数
    function autocomplete_CallBack(data) {
        var resultStr = "";
        var tipArr = data.tips;
        if (tipArr&&tipArr.length>0) {
            for (var i = 0; i < tipArr.length; i++) {
                resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)
                + ",this)' onclick='selectResult(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)
                + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\"" + "data=" + tipArr[i].adcode + ">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";
            }
        }
        else  {
            resultStr = " 注意！！！<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
        }
        document.getElementById("result1").curSelect = -1;
        document.getElementById("result1").tipArr = tipArr;
        document.getElementById("result1").innerHTML = resultStr;
        document.getElementById("result1").style.display = "block";
    }

    //输入提示框鼠标滑过时的样式
    function openMarkerTipById(pointid, thiss) {  //根据id打开搜索结果点tip
        thiss.style.background = '#CAE1FF';
    }

    //输入提示框鼠标移出时的样式
    function onmouseout_MarkerStyle(pointid, thiss) {  //鼠标移开后点样式恢复
        thiss.style.background = "";
    }

    //从输入提示框中选择关键字并查询
    function selectResult(index) {
        var number;
        if(index==42){
            index=0;number=42
        }
        if(index<0){
            return;
        }
        if (navigator.userAgent.indexOf("MSIE") > 0) {
            document.getElementById("keyword").onpropertychange = null;
            document.getElementById("keyword").onfocus = focus_callback;
        }
        //截取输入提示的关键字部分
        var text = document.getElementById("divid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");
        var cityCode = document.getElementById("divid" + (index + 1)).getAttribute('data');
        number!=42?document.getElementById("keyword").value = text:'';
        document.getElementById("result1").style.display = "none";
        //根据选择的输入提示关键字查询
        mapObj.plugin(["AMap.PlaceSearch"], function(e) {
            var msearch = new AMap.PlaceSearch();  //构造地点查询类
            AMap.event.addListener(msearch, "complete", placeSearch_CallBack); //查询成功时的回调函数
            msearch.setCity(100000);
            msearch.search(text);  //关键字查询查询
        });
    }
    function search(){
        mapObj.plugin(["AMap.PlaceSearch",'AMap.Geocoder'], function(e) {
            var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
                pageSize: 5,
                pageIndex: 1,
                city: "010", //城市
                map: mapObj//,
                //panel: "panel"
            });
            document.getElementById("result1").style.display = "none";
            //关键字查询
//            AMap.event.addListener(placeSearch, "complete", placeSearch_CallBack); //查询成功时的回调函数
            placeSearch.search(document.getElementById("keyword").value,function(status, result) {
                placeSearch_CallBack(result)
            });
        });
    }
    //定位选择输入提示关键字
    function focus_callback() {
        if (navigator.userAgent.indexOf("MSIE") > 0) {
            document.getElementById("keyword").onpropertychange = autoSearch;
        }
    }

    //输出关键字查询结果的回调函数
    function placeSearch_CallBack(data) {
        console.log(data)
        //清空地图上的InfoWindow和Marker
        windowsArr = [];
        marker     = [];
        mapObj.clearMap();
        var resultStr1 = "";
        var poiArr = data.poiList.pois;
        var resultCount = poiArr.length;
        for (var i = 0; i < resultCount; i++) {
            resultStr1 += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/" + (i + 1) + ".png\"></td>" + "<td><h6><font color=\"#00a6ac\">名称: " + poiArr[i].name + "</font></h6>";
            resultStr1 += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</td></tr></table></div>";
            addmarker(i, poiArr[i]);
        }
        mapObj.setFitView();
    }

    //鼠标滑过查询结果改变背景样式，根据id打开信息窗体
    function openMarkerTipById1(pointid, thiss) {
        thiss.style.background = '#CAE1FF';
        windowsArr[pointid].open(mapObj, marker[pointid]);
    }

    //添加查询结果的marker&infowindow
    function addmarker(i, d) {
        var lngX = d.location.getLng();
        var latY = d.location.getLat();
        var latxy=''+lngX+','+latY+'';
        var address='"'+d.address+'"'
        var name='"'+d.name+'"'
        console.log(lngX, latY)
        var markerOption = {
            map:mapObj,
            icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
            position:new AMap.LngLat(lngX, latY)
        };
        var mar = new AMap.Marker(markerOption);
        marker.push(new AMap.LngLat(lngX, latY));
        var infoWindow = new AMap.InfoWindow({
            content:"<button onclick='obtainXy("+lngX+","+latY+","+address+","+name+")' style='font-size: 12px'>点击获取此地址及经纬度</button><h6><font color=\"#00a6ac\">  " + (i + 1) + ". " + d.name + "</font></h6>" + TipContents(latxy, d.address, d.tel),
//            size:new AMap.Size(300, 0),
            autoMove:true,
            offset:new AMap.Pixel(0,-30)
        });
        windowsArr.push(infoWindow);
        var aa = function (e) {
            var nowPosition = mar.getPosition(),
                    lng_str = nowPosition.lng,
                    lat_str = nowPosition.lat;
            infoWindow.open(mapObj, nowPosition);
            console.log(lng_str, lng_str)
//            document.getElementById("lngX").value = lng_str;
//            document.getElementById("latY").value = lat_str;
        };
        AMap.event.addListener(mar, "click", aa);
    }

    //infowindow显示内容
    function TipContents(type, address, tel) {  //窗体内容
        if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
            type = "暂无";
        }
        if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
            address = "暂无";
        }
        if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {
            tel = "暂无";
        }
        var str = "  地址：" + address + "<br />  电话：" + tel + " <br />  经纬度：" + type;
        return str;
    }
    function keydown(event){
        var key = (event||window.event).keyCode;
        var result = document.getElementById("result1")
        var cur = result.curSelect;
        if(key===40){//down
            if(cur + 1 < result.childNodes.length){
                if(result.childNodes[cur]){
                    result.childNodes[cur].style.background='';
                }
                result.curSelect=cur+1;
                result.childNodes[cur+1].style.background='#CAE1FF';
                document.getElementById("keyword").value = result.tipArr[cur+1].name;
            }
        }else if(key===38){//up
            if(cur-1>=0){
                if(result.childNodes[cur]){
                    result.childNodes[cur].style.background='';
                }
                result.curSelect=cur-1;
                result.childNodes[cur-1].style.background='#CAE1FF';
                document.getElementById("keyword").value = result.tipArr[cur-1].name;
            }
        }else if(key === 13){
            var res = document.getElementById("result1");
            if(res && res['curSelect'] !== -1){
                console.log(document.getElementById("result1").curSelect)
                selectResult(document.getElementById("result1").curSelect);

            }
        }else{
//            autoSearch();
        }
    }
    function obtainXy(x,y,a){
        $('#mapModel').modal('hide')
        $.Popup({
            template: '<div style="font-size: 18px">确认获取该地址及经纬度吗?<div style="color: red"><br/>地址:'+a+'<br/>经纬度：'+x+','+y+'</div></div>',
            _zindex:10050,
            saveEvent: function () {
                document.getElementById("v_longitude").value = x;
                document.getElementById("v_latitude").value =y;
                document.getElementById("v_address").value = a;
                $('#mapModel').modal('hide')
            },
            cancelEvent:function(){
                $('#mapModel').modal('show')
            },
            cancelText:'返回'
        })

    }
</script>
</body>
</html>
