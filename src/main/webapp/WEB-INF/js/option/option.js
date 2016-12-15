/**
 * Created by ZYX on 2016/12/5.
 */
$(function(){
    initVersionTable();
});

function initVersionTable() {
    $("#option-list-table").bootstrapTable('destroy');
    $("#option-list-table").bootstrapTable({
        url: "/v2/opinion/queryUser",
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
                page: params.offset + 1,
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
            item.nickname = item.user.nickname;
            item._appType = item.appType;
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
            template: '确定删除该意见？',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/opinion/del",
                    async: false,
                    data:{id:row.id},
                    type: "get",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            });
                            $('#option-list-table').bootstrapTable('refresh');
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