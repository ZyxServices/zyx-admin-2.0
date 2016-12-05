/**
 * Created by ZYX on 2016/12/5.
 */
$(function(){
    initVersionTable();
    initValidForm();
    $('#publishTime').datetimepicker({
        language: 'zh-CN',
        weekStart: true,
        todayBtn: true,
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        startDate:$("#createTime").val(),
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    }).on('hide', function (e) {
        $('#versionForm').data('bootstrapValidator')
            .updateStatus('publishTime', 'NOT_VALIDATED', null)
            .validateField('publishTime');
        $("#createTime").datetimepicker("setEndDate",$("#publishTime").val());
    });
    $('#createTime').datetimepicker({
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
        $('#versionForm').data('bootstrapValidator')
            .updateStatus('createTime', 'NOT_VALIDATED', null)
            .validateField('createTime');
        $("#publishTime").datetimepicker("setStartDate",$("#createTime").val());
        $("#publishTime").attr({'disabled': false});
    });
});
function initValidForm() {
    $("#versionForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'version': {
                validators: {
                    notEmpty: {
                        message: '版本号不能为空'
                    }
                }
            }, 'createTime': {
                validators: {
                    notEmpty: {
                        message: '请选择创建时间'
                    }
                }
            }, 'publishTime': {
                validators: {
                    notEmpty: {
                        message: '请选择发布时间'
                    }
                }
            }
        }
    });
}
function platformType() {
    $("#version-list-table").bootstrapTable('refresh');
}

function initVersionTable() {
    $("#version-list-table").bootstrapTable('destroy');
    $("#version-list-table").bootstrapTable({
        url: "/v2/version/queryVersion",
        method:'get',
        locale: 'zh-US',
        striped: true,           //是否显示行间隔色
        pagination: true,
        cache: false,
        search: false,
        strictSearch: true,
        uniqueId: "id",
        height:500,
        pageSize: 20,
        contentType: "application/x-www-form-urlencoded",
        pageList: new Array(20, 50, 100),
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: function (params) {
            return {
                platform:$("#platform").val(),
                page: params.offset,
                appType:$("#appType").val(),
                pageNumber: params.limit
            }
        },
        responseHandler:groupFromData
    })
}

function groupFromData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            item._platform = item.platform;
            item._appType = item.appType;
            if(item.appType == 1){
                item.appType = "去攀岩"
            }
            if(item.platform == 1){
                item.platform = "Android";
            }else if(item.platform == 2){
                item.platform = "IOS";
            }
            item.createTime = new Date(item.createTime).format("yyyy-mm-dd HH:MM:ss");
            item.publishTime = new Date(item.publishTime).format("yyyy-mm-dd HH:MM:ss");
            dataArray.push(item)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}

/*
 * 列表操作
 * */
function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="remove">编辑</a>');
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    return dataArray.join('');
}
/*table事件*/
var operateEvents = {
    'click .remove': function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '确定删除该版本？',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/version/del",
                    async: false,
                    data:{id:row.id},
                    type: "post",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            });
                            $('#version-list-table').bootstrapTable('refresh');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '删除失败，请刷新之后再次操作'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .edit':function (e, value, row, index) {
        $("#addVersionModal").modal('show');
        $("#updateId").val(row.id);
        $("#version").val(row.version);
        $("#CreateAppType").val(row._appType);
        $("#publishTime").val(row.publishTime);
        $("#createTime").val(row.createTime);
        $("#downloadUrl").val(row.downloadUrl);
        $("#notes").val(row.notes);
        $("#publishTime").attr({'disabled': false});
        $("#createPlatform").val(row._platform);
        $("#versionForm").data('bootstrapValidator').validate();
    }
}

function createVersion() {
    $("#addVersionModal").modal('show');
    $("#updateId").val('');
    $("#versionForm")[0].reset();
    $('#versionForm').data('bootstrapValidator').resetForm(true);
    $("#publishTime").attr({'disabled': true});
}
$("#publishTime,#createTime").focus(function () {
    $(this).blur();
});

/*点击创建消息按钮*/
function confirmVersion() {
    if($("#updateId").val() == ''){
        versionFormOperate('/v2/version/insert');
    }else{
        versionFormOperate('/v2/version/update');
    }
}
function versionFormOperate(url) {
    $('#versionForm').ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        beforeSubmit:function () {
            $("#versionForm").data('bootstrapValidator').validate();
            return $("#versionForm").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: "操作成功"
                });
                $("#addVersionModal").modal('hide');
                $('#version-list-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
}