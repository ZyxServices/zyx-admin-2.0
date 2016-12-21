/**
 * Created by 文楷 on 2016/7/15.
 */

function initAppUserTable() {
    //先销毁表格
    $('#allApp_user_table').bootstrapTable('destroy');
    $('#allApp_user_table').bootstrapTable({
        url: ("/v2/appUser/queryUser"),
        method: 'get',
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        // formatSearch:"输入用户昵称",
        sortable: true,           //是否启用排序
        sortOrder: "desc",          //排序方式
        pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        strictSearch: true,        //是否启用模糊收索
        queryParamsType: "undefined",
        dataField: "data",
        silentSort: false,
        formatSearch:function () {
          return "昵称搜索"
        },
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                page: params.pageNumber,
                pageNumber: params.pageSize,
                searchText: params.searchText,
                official: $("#officialSelect").val(),
                sortName: params.sortName,
                sortOrder: params.sortOrder
            };
            return param;
        },
        onLoadSuccess: function (result) {  //加载成功时执行

        },
        onLoadError: function () {  //加载失败时执行
            // alert("加载数据失败");·
            // layer.msg("加载数据失败", {time : 1500, icon : 2});
        },
        columns: [
            {field: '', checkbox: true, align: 'center'},
            {field: 'id', title: 'id', align: 'center'},
            {field: 'nickname', title: '用户昵称'},
            {field: 'sex', title: '性别', formatter:sexFormat},
            {field: 'phone', title: '联系电话'},
            {field: 'createTime', title: '注册时间', sortable: true,formatter:timeFormat},
            {field: 'birthday', title: '生日', sortable: true, formatter:timeFormat},
            {field: 'address', title: '所在地', sortable: true},
            {field: 'concernCount', title: '动态数量', sortable: true},
            {field: 'level', title: '个人等级', sortable: true},
            {field: 'money', title: '攀岩币', sortable: true},
            {field: 'operation', title: '操作', align: 'center', formatter: operateFormatter,events:operateEvent}]
    });
}
function sexFormat(data) {
    return data == 0 ? "女":"男";
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
// 操作
function operateFormatter(value, row, index) {
    var _html = [];
    if(row.official == 1){
        _html.push('<a class="edit p5" href="javascript:void(0)" disabled>编辑</a>');
        _html.push('<a class="remove p5" href="javascript:void(0)">删除</a>')
    }
    if (row.mask) {
        _html.push('<a class="isMask p5" href="javascript:void(0)" title="屏蔽">屏蔽</a>');
    } else {
        _html.push('<a class="isMask p5" href="javascript:void(0)" title="取消屏蔽">取消屏蔽</a>');
    }
    return _html.join('');
}
var ISCHANGEIMG = '';/*判断是否修改了图片*/
var operateEvent = {
    'click .edit': function (e, value, row, index) {
        $(".live_index").hide();
        $(".create_liveType").show();
        $("#listType").html("编辑用户");
        $("#avatar").val(row.avatar);
        ISCHANGEIMG = row.avatar;
        $("#nickname").val(row.nickname);
        $("#userId").val(row.id);
        $("#phone").val(row.phone);
        $("#password").val('');
        $('input[name=sex]').eq(!row.sex).attr({"checked": "checked"});
        if(row.avatar){
            $("#avatarImg").attr("src","http://image.tiyujia.com/" + row.avatar);
        }
        $("#address").val(row.address);
        $("#birthdayStr").val(new Date(row.birthday).format("yyyy-mm-dd HH:MM:ss"));
        $("#birthday").val(row.birthday);
        $("#signature").val(row.signature);
        $('#createAppUserForm').bootstrapValidator('removeField', 'avatarR');
        $('#createAppUserForm').data('bootstrapValidator').validate();
    },
    'click .isMask':function (e, value, row, index) {
        var mask = '';
        var content = '';
        if(row.mask == 0){/*0是正常状态，1代表被屏蔽了*/
            mask = 1;
            content = "屏蔽之后该用户将不能再启用";
        }else{
            mask = 0;
            content = "这是取消屏蔽是否的对话框"
        }
        $.Popup({
            title: '屏蔽',
            template: content,
            saveEvent: function () {
                $.ajax({
                    url: "/v2/appUser/mask",
                    async: false,
                    type: "get",
                    data: {id: row.id,mask: mask},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '操作成功'
                            })
                            $('#allApp_user_table').bootstrapTable("refresh");
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '操作失败'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .remove': function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '该活动的所有数据将被完全删除，不能再被浏览',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/appUser/del",
                    async: false,
                    type: "get",
                    data: {id: row.id},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#allApp_user_table').bootstrapTable("refresh");
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '删除失败'
                            })
                        }
                    }
                });
            }
        })
    }
};

$(".create_live").click(function () {
    $("#createAppUserForm")[0].reset();
    $("#listType").html("创建用户");
    $(".create_liveType").show();
    $(".live_index").hide();
    $("#createButton").attr("disabled", false);
    $('#createAppUserForm').data('bootstrapValidator').resetForm(true);
});


function createEditAppUser(url) {
    $("#createAppUserForm").ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        complete:function () {
            $("#upload").modal('hide');
        },
        success: function (result) {
            if (result.state == 200) {
                $('#allApp_user_table').bootstrapTable("refresh");
                backToUsers();
            } else {
                if (result.state == 5001) {
                    $.Popup({
                        confirm: false,
                        template: '账号已存在'
                    });
                }else{
                    $.Popup({
                        confirm: false,
                        template: '创建异常，请刷新后再创建'
                    });
                }
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });
}

function beginCreate() {
    var formData = new FormData();
    formData.append('avatar', $("#avatarR")[0].files[0]);
    if($("#listType").html() == "创建用户" || ISCHANGEIMG != $("#avatar").val()){/*创建用户或者修改了图片的时候都需要先上传图片*/
        $.ajax({
            url: 'http://119.61.66.55:18100/v2/upload ',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                /*传图片之前做验证*/
                var _isValid = $('#createAppUserForm').data('bootstrapValidator').isValid();
                if(_isValid){
                    $("#upload").modal('show');
                    if($("#listType").html() == "创建用户"){
                        $("#uploadContent").html('创建中，请稍后...');
                    }else{
                        $("#uploadContent").html('修改中，请稍后...');
                    }
                }else{
                    return _isValid;
                }
            },
            success:function (result) {
                if(result.state == 200){
                    $("#avatar").val(result.data.url);
                    if($("#listType").html() == "创建用户"){
                        createEditAppUser('/v2/appUser/insert')
                    }else{
                        createEditAppUser('/v2/appUser/update')
                    }
                }else{
                    $.Popup({
                        confirm: false,
                        template: JSON.stringify(result)
                    });
                    $("#upload").modal('hide');
                }
            },
            error:function (result) {
                $.Popup({
                    confirm: false,
                    template: result
                });
                $("#upload").modal('hide');
            }
        })
        
    }else{/*未改变图片的上传*/
        if($('#createAppUserForm').data('bootstrapValidator').isValid()){
            createEditAppUser('/v2/appUser/update')
        }
    }
}

function backToUsers() {
    $(".create_liveType").hide();
    $(".live_index").show();
}
/*图片上传*/
$('input[id=avatarR]').change(function () {
    $('#createAppUserForm').bootstrapValidator('addField', 'avatarR', {
        validators: {
            notEmpty: {
                message: '头像必须上传'
            }
        }
    });
    $("#avatar").val($(this).val());
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        $("#avatarR").html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#avatarImg").attr("src", objUrl);
        }
    }else {
        $("#photoCover").html("选择文件");
        $("#avatarImg").attr("src", "");
    }
});
//图片预览
//建立一個可存取到該file的url
function getImgURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}
$(function () {
    initAppUserTable();
    /*表单验证*/
    $("#createAppUserForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'nickname': {
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    }
                }
            }, 'phone': {
                validators: {
                    notEmpty: {
                        message: '电话号码必填'
                    },
                    regexp: {
                        regexp: /^(1[3|4|5|7|8]\d{9})$/, /*只支持手机电话*/
                        message: '请输入正确手机号码'
                    }
                }
            }, 'password': {
                validators: {
                    notEmpty: {
                        message: '密码必填'
                    }
                }
            }, 'avatarR': {
                validators: {
                    notEmpty: {
                        message: '头像必须上传'
                    }
                }
            }, 'address': {
                validators: {
                    notEmpty: {
                        message: '所在地必填'
                    }
                }
            }/*, 'signature': {
                validators: {
                    notEmpty: {
                        message: '签名必填'
                    }
                }
            }*/
        }
    });
    $('#birthdayStr').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: true,
        todayBtn: true,
        autoclose: true,
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    }).on('hide', function (e) {
        $("#birthday").val(e.timeStamp);
    });
});
$("#birthdayStr").focus(function () {
    $(this).blur();
});
function changeOfficialTable() {
    $('#allApp_user_table').bootstrapTable("refresh");
}
function get_unix_time(dateStr)
{
    var newstr = dateStr.replace(/-/g,'/');
    var date =  new Date(newstr);
    var time_str = date.getTime().toString();
    return time_str.substr(0, 10);
}