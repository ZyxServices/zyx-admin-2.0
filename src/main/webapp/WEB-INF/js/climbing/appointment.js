/**
 * Created by ZYX on 2016/11/9.
 */

var ISCHANGEIMG = '';/*判断修改活动的时候是否修改了图片*/
$(function () {
    /*表单验证*/
    $("#updateCreateFrom").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'title': {
                validators: {
                    notEmpty: {
                        message: '组合名称不能为空'
                    }
                }
            }, 'imageR': {
                validators: {
                    notEmpty: {
                        message: '请上传图片'
                    }
                }
            }, 'descContent': {
                validators: {
                    notEmpty: {
                        message: '请输入内容'
                    }
                }
            }, 'startTime': {
                validators: {
                    notEmpty: {
                        message: '请选择开始时间'
                    },
                    callback: {
                        message: '开始日期不能大于结束日期',
                        callback: function (value, validator, $field) {
                            var endValue = $('#activityEndTime').val();
                            var endTime = new Date(endValue.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(value.replace("-", "/").replace("-", "/"));
                            if (endValue == '') {
                                return true;
                            }else if (endValue != '') {
                                validator.updateStatus('endTime', 'VALID');
                                return startTime <= endTime;
                            }
                        }
                    }
                }
            }, 'endTime': {
                validators: {
                    notEmpty: {
                        message: '请选择结束时间'
                    },
                    callback: {
                        message: '结束日期不能小于开始日期',
                        callback: function (value, validator, $field) {
                            var startValue = $('#activityStartTime').val();
                            var endTime = new Date(value.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(startValue.replace("-", "/").replace("-", "/"));
                            validator.updateStatus('startTime', 'VALID');
                            return endTime >= startTime;
                        }
                    }
                }
            }, 'lastTime': {
                validators: {
                    notEmpty: {
                        message: '请选择活动截止时间'
                    },
                    callback: {
                        message: '活动截止时间不能大于开始日期',
                        callback: function (value, validator, $field) {
                            var startValue = $('#activityStartTime').val();
                            var lastTime = new Date(value.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(startValue.replace("-", "/").replace("-", "/"));
                            validator.updateStatus('startTime', 'VALID');
                            return lastTime <= startTime;
                        }
                    }
                }
            }, 'maxPeople': {
                validators: {
                    integer: {
                        message: '请输入整数'
                    }
                }
            }
        }
    });

    /*初始化表格*/
    initAppointmentTable();

    $('#activityStartTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: true,
        todayBtn: true,
        autoclose: true,
        todayHighlight: 1,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    }).on('hide', function (e) {
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('startTime', 'NOT_VALIDATED', null)
            .validateField('startTime');
        $("#activityEndTime").attr({'disabled': false});
        $("#signEndTime").attr({'disabled': false})
    });
    $('#activityEndTime').datetimepicker({
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
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('endTime', 'NOT_VALIDATED', null)
            .validateField('endTime');
    });
    $('#signEndTime').datetimepicker({
        language: 'zh-CN',
        weekStart: true,
        todayBtn: true,
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    }).on('hide', function (e) {
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('lastTime', 'NOT_VALIDATED', null)
            .validateField('lastTime');
    });
});

function initAppointmentTable() {
    $("#appointment-list-table").bootstrapTable('destroy');
    $("#appointment-list-table").bootstrapTable({
        url: "/v2/activity/queryActivity",
        method: 'get',
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "desc",          //排序方式
        contentType: "application/x-www-form-urlencoded",
        height: 500,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        queryParamsType: "undefined",
        queryParams: queryParams,
        responseHandler: fromData
    })
}

$("#activityStartTime,#activityEndTime,#signEndTime").focus(function () {
    $(this).blur();
});

function queryParams(params) {
    return {
        page: params.pageNumber-1,
        pageNumber: params.pageSize,
        title: params.searchText,
        type: $("#searchType").val(),
        activityType: $("#searchActivityType").val(),
        paymentType: $("#searchPaymentType").val(),
        status: $("#searchStatus").val(),
        appType:$("#appType").val()
    };
}

function fromData(res) {
    if (res.state == 480) {
        $("#content-wrapper").html("<section class='content'>无权限</section>");
        return false;
    }
    if(res.state == 804){
        return {
            rows: {},
            total: 0
        }
    }
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            /*通过开始时间获取活动状态*/
            item.activityType = item.activityType == 1 ? "求约":"求带";
            /*修改的时候使用的图片地址*/
            item._image = item.imgUrls;
            if(item.imgUrls){
                var imgUrl = item.imgUrls.split(".");
                item.imgUrls = '<a href="http://image.tiyujia.com/'+item.imgUrls+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>';
            }
            item.status = isActivityStart(item.startTime);
            dataArray.push(item)
        });
        return {
            rows: dataArray,
            total: res.total
        }
    }
}
/*根据活动开始时间查看当前活动是否已开始*/
function isActivityStart(startTimeStr) {
    var currentTime = new Date();
    /*将时间戳转变为时间*/
    var startTime = (new Date(startTimeStr).format("yyyy-mm-dd HH:MM:ss")).replace("-", "/").replace("-", "/");
    var status = startTime > currentTime ? "正在报名":"活动已开始";
    return status;
}


$("#confirmCmd").click(function () {
    $("#activityRecommend").modal('hide');
    $("#upload").modal('show');
    $("#uploadContent").html('推荐上传中...');
    activityRecommend();
})
/*推荐*/
function activityRecommend() {
    $('#devaForm').ajaxSubmit({
        url: '/v2/deva/add',
        type: 'post',
        dataType: 'json',
        complete:function () {
            $("#upload").modal('hide');
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: '推荐成功'
                });
                $('#appointment-list-table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    })
}
/*点击修改和添加的确认按钮时先上传图片*/
$("#czS").click(function () {
    var formData = new FormData();
    formData.append('avatar', $("#lefile")[0].files[0]);
    if ($("#listType").html() == "创建") {
        /*创建*/
        $.ajax({
            url: 'http://119.61.66.55:18100/v2/upload',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                var _isValid = $("#updateCreateFrom").data('bootstrapValidator').isValid();
                if(_isValid){
                    $("#upload").modal('show');
                     $("#uploadContent").html('创建中，请稍后...');
                }else{
                    return _isValid;
                }
            },
            complete:function () {
                $("#upload").modal('hide');
            },
            success: function (result) {
                if (result.state == 200) {
                    $("#image").val(result.data.url);
                    updateCreateFrom('/v2/activity/release');
                }
            },
            error:function (result) {
                $.Popup({
                    confirm: false,
                    template: result
                });
            }
        })
    } else {
        /*修改*/
        var isChange = $("#image").val();
        if(ISCHANGEIMG != isChange){/*不相等代表换了图片*/
            $.ajax({
                url: 'http://119.61.66.55:18100/v2/upload',
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function () {
                    /*传图片之前做验证*/
                    var _isValid = $("#updateCreateFrom").data('bootstrapValidator').isValid();
                    if(_isValid){
                        $("#upload").modal('show');
                        $("#uploadContent").html('活动修改中，请稍后...');
                    }else{
                        return _isValid;
                    }
                },
                success:function (result) {
                    if(result.state == 200){
                        $("#image").val(result.data.url);
                        updateCreateFrom('/v2/activity/update');
                    }
                }
            })
        }else{
            if($("#updateCreateFrom").data('bootstrapValidator').isValid()){
                $("#upload").modal('show');
                $("#uploadContent").html('活动修改中，请稍后...');
                updateCreateFrom('/v2/activity/update');
            }
        }
    }
})
/*活动的创建和修改*/
function updateCreateFrom(url) {
    $('#updateCreateFrom').ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        complete:function () {
            $("#upload").modal('hide');
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: result.successmsg
                });
                $("#activityList").show();
                $("#createModify").hide();
                $('#appointment-list-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
}
/*点击编辑和预览---表格的内容*/
function editPreview(row) {
    $("#title").val(row.title);
    $("#images").attr("src", "http://image.tiyujia.com/" + row._image);
    // $('#activity-summernote').summernote('code', row.descContent);
    $("#descContent").val(row.descContent);
    $("#activityStartTime").val(new Date(row.startTime).format("yyyy-mm-dd HH:MM:ss"));
    $("#activityEndTime").val(new Date(row.endTime).format("yyyy-mm-dd HH:MM:ss"));
    $("#signEndTime").val(new Date(row.lastTime).format("yyyy-mm-dd HH:MM:ss"));
    $("#maxPeople").val(row.maxPeople);
    $("#activityType").val(row.activityType == "求约" ? "1":"2");
    $("#address").val(row.address);
    $("#v_latitude").val(row.latitude);
    $("#v_longitude").val(row.longitude);
    $("#paymentType").val(row.paymentType);
}
function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    if (row.mask == 0) {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield">屏蔽</a>')
    } else {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield">解除屏蔽</a>')
    }
    /*官方活动有删除，用户活动无删除*/
    if (row.type == 0) {
        dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>')
    }
    if(row.isDeva){
        dataArray.push('<a class="p5" href="javascript:void(0)" disabled>已推荐</a>')
    }else{
        dataArray.push('<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>');
    }
    return dataArray.join('');
}
var HOMEAREA = 1;
var APPOINTMENTAREA = 2;
var operateEvents = {
    'click .edit': function (e, value, row, index) {
        $("#activityEndTime").attr({'disabled': false});
        $("#signEndTime").attr({'disabled': false});
        queryOfficial("choiceOfficial");
        $("#listType").html("编辑");
        $("#image").val(row._image);
        ISCHANGEIMG = row._image;/*保留编辑之前的图片地址*/
        $("#avtivityId").val(row.id);
        editPreview(row);
        $("#createModify").show();
        $("#activityList").hide();
        /*编辑的时候去掉对图片的验证*/
        $('#updateCreateFrom')
            .bootstrapValidator('removeField', 'imageR')
            .data('bootstrapValidator').validate();
    },
    'click .preview': function (e, value, row, index) {
        $("#listType").html("预览");
        $("#title").attr("disabled", "disabled");
        $("#imgWrap").hide();
        $('#activity-summernote').summernote('destroy');
        $('#activity-summernote').summernote({toolbar: false, airMode: true});
        $("#activityStartTime").attr("disabled", "disabled");
        $("#activityEndTime").attr("disabled", "disabled");
        $("#address").attr("disabled", "disabled");
        $("#price").attr("disabled", "disabled");
        $("#signEndTime").attr("disabled", "disabled");
        $("#maxPeople").attr("disabled", "disabled");
        $("#paymentType").attr("disabled", "disabled");
        $("#activityType").attr("disabled", "disabled");
        $("#czS").remove();
        editPreview(row);
        $("#createModify").show();
        $("#activityList").hide();
    },
    'click .recommend': function (e, value, row, index) {
        getBannerSequence(HOMEAREA);
        $("#activityRecommend").modal('show');
        $("#modelId").val(row.id);
        $("#imageUrl").val(row._image);
    },
    'click .Shield': function (e, value, row, index) {
        var type = row.mask == 0 ? 1 : 0;
        $.Popup({
            title: '屏蔽',
            template: '屏蔽之后，该活动将不在首页活动和活动列表页展示，“我的关注”和“我的”中活动保留，仍可以被浏览',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/activity/maskActivity",
                    async: false,
                    type: "post",
                    data: {id: row.id, maskType: type},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            if (type == 1) {
                                $.Popup({
                                    confirm: false,
                                    template: '屏蔽成功'
                                })
                            } else {
                                $.Popup({
                                    confirm: false,
                                    template: '解除屏蔽成功'
                                })
                            }
                            $('#appointment-list-table').bootstrapTable('refresh');
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
    },
    'click .remove': function (e, value, row, index) {
        var type = row.del == 0 ? 1 : 0;
        $.Popup({
            title: '删除',
            template: '该活动的所有数据将被完全删除，不能再被浏览',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/activity/delActivity",
                    async: false,
                    type: "post",
                    data: {id: row.id, delType: type},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#appointment-list-table').bootstrapTable('refresh');
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
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
/*修改支付类型*/
function changePaymentType(obj) {
    if($(obj).val() == 2){
        $("#price").show();
    }else{
        $("#price").hide();
    }
}
/*改变推荐位置*/
function changeDevArea(obj) {
    var area = $(obj).val();
    getBannerSequence(area);
}
/*获取banner的sequence*/
function getBannerSequence(area) {
    $.ajax({
        url: "/v2/deva/sequence",
        type: 'POST',
        dataType: 'json',
        async:false,
        data: {area: area},
        success: function (result) {
            var option = '';
            if (result.state == 200) {
                if(result.data.length > 0){
                    for(var i = 0;i < result.data.length; i++){
                        option += '<option>'+result.data[i]+'</option>';
                    }
                    $("#sequence").html(option);
                }else{
                    var warm = "";
                    if(area == 1){
                        $("#sequence").html('<option value="">首页banner序列号已满，请先删除其他序列号再推荐</option>');
                    }else{
                        $("#sequence").html('<option value="">求约banner序列号已满，请先删除其他序列号再推荐</option>');
                    }
                }
            } else {
                $.Popup({
                    confirm: false,
                    template: '未获取到banner序号，请刷新页面'
                })
            }
        }
    });
}

function createActivity() {
    $("#listType").html("创建");
    $("#photoCover").html("选择文件");
    $("#createModify").show();
    $("#activityList").hide();
    queryOfficial("choiceOfficial");
    $("#images").attr({'src': ''});
    $("#avtivityId").val('');
    $('#activity-summernote').summernote('reset');
    $("#userRequired").hide();
    $('#updateCreateFrom')[0].reset();
    $('#updateCreateFrom').data('bootstrapValidator').resetForm(true);
    $("#maxPeople").val("999");
    $('#updateCreateFrom').data('bootstrapValidator').validateField('maxPeople');
}

/*创建中type=file的样式处理*/
$('input[id=lefile]').change(function () {
    $('#updateCreateFrom').bootstrapValidator('addField', 'imageR', {
        validators: {
            notEmpty: {
                message: '请上传图片'
            }
        }
    });
    $("#image").val($(this).val());
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#images").attr("src", objUrl);
        }
    }else {
        $("#photoCover").html("选择文件");
        $("#images").attr("src", "");
    }
});

/*推荐中type=file的样式处理*/
$('input[id=recommendFile]').change(function () {
    if ($(this).val()) {
        $('#recommendPhotoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#recommendImg").attr("src", objUrl);
        }
    }else{
        $("#recommendPhotoCover").html("选择文件");
        $("#recommendImg").attr("src", "");
    }
});
/*图片预览*/
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
/*活动批量删除*/
function batchDelete() {
    var bathId = [];
    $.map($("#appointment-list-table").bootstrapTable('getSelections'), function (row) {
        bathId.push(row.id);
    });
    $.Popup({
        title: '删除',
        template: '这些活动的所有数据将被完全删除，不能再被浏览',
        saveEvent: function () {
            $.ajax({
                url: "/v2/activity/delActivity",
                async: false,
                type: "post",
                data: {id: bathId.join(), delType: 1},
                dateType: "json",
                success: function (result) {
                    if (result.state == 200) {
                        $.Popup({
                            confirm: false,
                            template: '删除成功'
                        })
                        $('#appointment-list-table').bootstrapTable('refresh');
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
/*筛选表格----表格过滤条件*/
function startFilter() {
    $('#appointment-list-table').bootstrapTable('refresh');
}
