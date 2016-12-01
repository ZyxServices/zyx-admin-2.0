/**
 * Created by 文楷 on 2016/11/29.
 */
//获取url参数
var shareCommon={
    Ajax:function(listID,success){
        var ways={
            GetRequest:function(){
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
        }
        $.ajax({
            url: '/share?id=' + ways.GetRequest().id + '&type=' + listID + '',
            type: 'post',
            success: function (res) {
                success(res)
            }
        })
    },
    commentsFormatter: function (data) {
        var commentsHtml = '<h4>他们都在说</h4></div>';
        for (var i in data) {
            var commentHtml = ' <div class="col-xs-12 p0 pr0 meg">' +
                ' <div class="col-xs-2 .col-md-1  p0 phone1">' +
                ' <img  class="avatar" src="http://image.tiyujia.com/' + data[i].avatar + '">' +
                ' </div>' +
                ' <div class="col-xs-10 .col-md-11  p0 phone9">' +
                ' ' + data[i].nickname + '' +
                ' <div class="grade">' + data[i].level + '</div>' +
                ' <br>' +
                ' <span class="time">'+dateStr(data[i].createTime)+'</span>' +
                ' </div>' +
                ' <div class="col-xs-10 col-xs-offset-1 mes-content p0 phone-offset-1">' +
                data[i].commentContent +
                ' </div>' +
                ' </div>'
            commentsHtml += commentHtml
        }
        return commentsHtml;
    }
}

