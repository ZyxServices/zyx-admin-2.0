/**
 * Created by ZYX on 2016/11/9.
 */
$(function(){
    initVenuesTable(1);
})

function initVenuesTable(area) {
    $("#homepage-list-table").bootstrapTable('destroy');
    $("#homepage-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
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
                area: area,
                model: 1,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:groupFromData
    })
}
/*线路列表*/
function initLineTable() {
    $("#line-list-table").bootstrapTable('destroy');
    $("#line-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
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
                area: 1,
                model: 1,
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
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.modelTitle = item.devaModelVo.modelTitle;
            dataObj.model = item.model;
            dataObj.area = item.area;
            dataObj.createTime = item.createTime;
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>';
            }
            dataObj.sequence = item.sequence;
            dataObj.state = item.state == 1? "是":"否";
            dataArray.push(dataObj)
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
    dataArray.push('<a class="preview p5" href="javascript:void(0)" title="preview">预览</a>');
    dataArray.push('<a class="addLine p5" href="javascript:void(0)" title="addLine">添加线路</a>');
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
function operateLine(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    return dataArray.join('');
}
/*table事件*/
var operateEvents = {
    'click .withdraw':function (e, value, row, index) {
        $.Popup({
            title: '撤回',
            template: '确定撤回该banner？',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/deva/delete?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '撤回成功'
                            })
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '撤回失败，请重新刷新'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .remove':function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '确定删除该banner？',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/deva/delete?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
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
    'click .preview':function (e, value, row, index) {/*编辑有不是公用的部分*/
        $("#addLine").hide();
        $("#venuesList").hide();
        $("#createModify").show();
    },
    'click .edit':function (e, value, row, index) {/*编辑有不是公用的部分*/
        $("#addLine").hide();
        $("#venuesList").hide();
        $("#createModify").show();
    },
    'click .addLine':function (e, value, row, index) {
        initLineTable();
        $("#venuesList").hide();
        $("#createModify").hide();
        $("#addLine").show();
    }
}
var operateLineEvents = {
    'click .remove':function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '确定删除该线路？',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/deva/delete?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
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
    'click .edit':function (e, value, row, index) {/*编辑有不是公用的部分*/
        $("#addLineModal").modal('show');
    }
}
/*录入场馆信息*/
function typeInfo() {
    $("#addLine").hide();
    $("#venuesList").hide();
    $("#createModify").show();
    $("#updateCreateFrom")[0].reset();
}

/*点击添加线路---弹出模态窗*/
function addLineModal() {
    $("#addLineModal").modal('show');
}
function changeBannerTable(obj) {
    var _val = $(obj).val();
    initVenuesTable(_val);
}

