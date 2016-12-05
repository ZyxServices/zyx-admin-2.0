/**
 * Created by ZYX on 2016/11/9.
 */
$(function(){
    initMessageTable();
    /*banner表单验证*/
    $("#bannerForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'sequence': {
                validators: {
                    notEmpty: {
                        message: 'banner序号不能为空'
                    }
                }
            }
        }
    });
})

function initMessageTable() {
    $("#message-list-table").bootstrapTable('destroy');
    $("#message-list-table").bootstrapTable({
        url: "/v2/sysMessage/querySysMessage",
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
                page: params.offset,
                pageNumber: params.limit,
                appType:$("#appType").val()
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
            item.type = item.type == 0 ? "系统消息":"日常推送";
            item.done = item.done == 0 ? "完成":"未完成";
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
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    return dataArray.join('');
}
/*table事件*/
var operateEvents = {
    'click .remove': function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '确定删除该banner？',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/sysMessage/del",
                    async: false,
                    data:{id:row.id},
                    type: "post",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#message-list-table').bootstrapTable('refresh');
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
    }
}
function createMessage() {
    $("#messageList").hide();
    $("#messageCreate").show();
    $("#messageForm")[0].reset();
    $('#pushTime').datetimepicker({
        language: 'zh-CN',
        weekStart: true,
        todayBtn: true,
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        startDate:new Date(),
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    })
}
$("#pushTime").focus(function () {
    $(this).blur();
});
function isSetPushTime(obj) {
    var _val = $(obj).val();
    if(_val == 1){
        $("#pushTime").attr("disabled",false);
    }else{
        $("#pushTime").attr("disabled",true);
        $("#pushTime").val("");
    }
}
/*点击创建消息按钮*/
function confirmMessage() {
    $('#messageForm').ajaxSubmit({
        url: '/v2/sysMessage/insert',
        type: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: "消息添加成功"
                });
                $("#messageList").show();
                $("#messageEdit").hide();
                $('#message-list-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
}