<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/12
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- BEGIN CORE PLUGINS -->

<script src="../../js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="../../js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.vo.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../../js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="../../js/jquery.form.js" type="text/javascript"></script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="../../js/daterangepicker.js" type="text/javascript"></script>
<script src="../../js/jquery.gritter.js" type="text/javascript"></script><%--消息提示--%>
<script src="../../js/bootstrap-table.js" type="text/javascript"></script>
<script src="../../js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="../../js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="../../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="../../js/bootstrapValidator.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="../../js/summernote.min.js" type="text/javascript"></script>
<script src="../../js/summernote-zh-CN.js" type="text/javascript"></script>
<script src="../../js/jquery.tree-multiselect.js" type="text/javascript"></script>
<script src="../../js/public/chosen.jquery.min.js" type="text/javascript"></script>

<script src="../../js/dataformat.js" type="text/javascript"></script>
<script src="../../js/public/dialog.js" type="text/javascript"></script>

<!-- 工具 -->
<script src="../../js/utils/resolveUtils.js" type="text/javascript"></script>

<script type="text/javascript">

    $(function () {
        var url = window.location.href;
        var urlLength = url.split("/").length;
        var getMenuObj = url.split("/")[urlLength - 2];
        var getSecondMenuObj = url.split("/")[urlLength - 1].split("?");
        if(getSecondMenuObj.length == 2){
            getSecondMenuObj[0] = 'circlelist';
        }
        if (getSecondMenuObj[0] == "home") {
            $("." + getSecondMenuObj[0]).addClass("active");
            $("." + getSecondMenuObj[0]).find(".selected").show();
            return;
        }
        if (getMenuObj == "appUser" || getMenuObj == "sys" || getMenuObj == "climbing" || getMenuObj == "community") {
            $("." + getMenuObj).addClass("open");
            $("." + getMenuObj).addClass("active");
            $("." + getMenuObj).find(".arrow").addClass("open");
            $("." + getMenuObj).find(".selected").show();
            $("." + getMenuObj).find(".sub-menu").show();
            $("." + getSecondMenuObj[0]).addClass("active");
        } else {
            $("." + getMenuObj).addClass("active");
            $("." + getMenuObj).find(".selected").show();
        }
    })

    /*请求的officialId*/
    function queryOfficial(id) {
        $.ajax({
            url:'/v2/sysUser/choice',
            type:'post',
            async: false,
            success:function (res) {
                var data = res.data;
                var option = '';
                for(var i = 0;i < data.length; i++){
                    option += '<option value='+data[i].id+'>'+data[i].nickname+'</option>'
                }
                $("#"+id).html(option);
            }
        })
    }

</script>
