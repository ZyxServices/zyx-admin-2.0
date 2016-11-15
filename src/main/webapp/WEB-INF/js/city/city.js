/**
 * Created by ZYX on 2016/11/9.
 */
$(function(){
    $("#addCityForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'cityName': {
                validators: {
                    notEmpty: {
                        message: '请填写城市名称'
                    }
                }
            }
        }
    });
    initCity();
})

function initCity() {
    $("#city-list-table").bootstrapTable('destroy');
    $("#city-list-table").bootstrapTable({
        url: "/v1/city/queryAll",
        method:'get',
        locale: 'zh-US',
        striped: true,           //是否显示行间隔色
        pagination: true,
        cache: false,
        search: true,
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
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
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
            item.state = item.state == 0 ? "启用":"禁用";
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
    if(row.state == "启用"){
        dataArray.push('<a class="isStart p5" href="javascript:void(0)" title="remove">禁用</a>');
    }else{
        dataArray.push('<a class="isStart p5" href="javascript:void(0)" title="remove">启用</a>');
    }
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    return dataArray.join('');
}
/*table事件*/
var operateEvents = {
    'click .remove':function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '确定删除该城市么？',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/city/delCity?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#city-list-table').bootstrapTable('refresh');
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
    'click .isStart':function (e, value, row, index) {
        var warm = "";
        var state = row.state == "启用" ? 1 : 0;
        if(row.state == "启用"){
            warm = "确定禁用该城市？"
        }else{
            warm = "确定启用该城市？"
        }
        $.Popup({
            title: '系统提示',
            template: warm,
            saveEvent: function () {
                $.ajax({
                    url: "/v1/city/updateState",
                    async: false,
                    type: "post",
                    data:{id:row.id,state:state},
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '操作成功'
                            })
                            $('#city-list-table').bootstrapTable('refresh');
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
    }
}
/*添加等级*/
function addCitys() {
    $("#addCityModal").modal('show');
    $("#addCityForm")[0].reset();
    $('#addCityForm').data('bootstrapValidator').resetForm(true);
}
$("#confirmCmd").click(function () {
    $('#addCityForm').data('bootstrapValidator').validateField('cityName');
    $('#addCityForm').ajaxSubmit({
        url: '/v1/city/add',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#addCityForm").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            $("#addCityModal").modal('hide');
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: '添加成功'
                });
                $('#city-list-table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    })
})

