/**
 * Created by 文楷 on 2016/12/5.
 */
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
            console.log(result.regeocode.addressComponent,1)
            var provinceCity=''+result.regeocode.addressComponent.province+''+result.regeocode.addressComponent.city+''
            obtainXy(e.lnglat.getLng(), e.lnglat.getLat(),result.regeocode.formattedAddress,provinceCity)
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
    var latxy='"'+lngX+'.'+latY+'"';
    var address='"'+d.address+'"'
    var name='"'+d.name+'"'
    var c= '"'+d.pname+d.name+'"'
    console.log(d)
    var markerOption = {
        map:mapObj,
        icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
        position:new AMap.LngLat(lngX, latY)
    };
    var mar = new AMap.Marker(markerOption);
    marker.push(new AMap.LngLat(lngX, latY));
    var infoWindow = new AMap.InfoWindow({
        content:"<button onclick='obtainXy("+lngX+","+latY+","+address+","+c+")' style='font-size: 12px'>点击获取此地址及经纬度</button><h6><font color=\"#00a6ac\">  " + (i + 1) + ". " + d.name + "</font></h6>城市："+ d.cityname+"<br>区域："+ d.adname+"<br>" + TipContents(latxy, d.address, d.tel),
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
function obtainXy(x,y,a,c){
    console.log(x,a,c)
    $('#mapModel').modal('hide')
    $.Popup({
        template: '<div style="font-size: 18px">确认获取该地址及经纬度吗?<div style="color: red"><br/>省市:'+c+'<br/>地址:'+a+'<br/>经纬度：'+x+','+y+'<br/></div></div>',
        _zindex:10050,
        saveEvent: function () {
            if(document.getElementById("address")){
                document.getElementById("address").value = a;
            }else{
                document.getElementById("v_address").value = a;
            }

            document.getElementById("v_longitude").value = x;
            document.getElementById("v_latitude").value =y;
            $('#mapModel').modal('hide')
        },
        cancelEvent:function(){
            $('#mapModel').modal('show')
        },
        cancelText:'返回'
    })

}