/**
 * Created by 文楷 on 2016/11/29.
 */
//获取url参数
function share(listID,success) {
    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }
//获取页面数据
    $.ajax({
        url: '/share?id=' + GetRequest().id + '&type=' + listID + '',
        type: 'post',
        success: function (res) {
            success(res)
        }
    })
}
function dataPush(obj,res){
    for(var i in obj){
        obj[i].formatter!=undefined?$('#'+obj[i].id+'').html(obj[i].formatter(eval(obj[i].field))) : $('#'+obj[i].id+'').html(eval(obj[i].field))
    }
}
