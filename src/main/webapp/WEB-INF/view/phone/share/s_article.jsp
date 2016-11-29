<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>场馆管理管理</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-攀岩馆场馆管理" name="description"/>

    <meta content="攀岩馆场馆管理" name="author"/>
</head>
<body>11</body>
<script language="javascript">
    console.log(GetRequest().id)
    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }
</script>
</html>
