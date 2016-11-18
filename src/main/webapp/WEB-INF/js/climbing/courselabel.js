/**
 * Created by ZYX on 2016/11/9.
 */
$(function () {
    $("#labelcreate").bootstrapValidator({
        fields: {
            "labelName": {
                validators: {
                    notEmpty: {
                        message: '请输入标签名称'
                    }
                }
            }
        }
    });

//圈子表格数据

    $("#Course_table").bootstrapTable({
        type: 'get',
        url: ("/v1/CourseLabel/queryAll"),
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
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
        smartDisplay: false,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        strictSearch: false,        //是否启用模糊收索
        queryParamsType: "undefined",
        dataField: "data",
        queryParams: function queryParams(params) {   //设置查询参数
            console.log(params)
            var param = {
                page: 1,
                pageNumber: params.pageSize,
                searchText: params.searchText
                //sortName: params.sortName
                //sortOrder: params.sortOrder
            };
            return param;
        },
        onLoadSuccess: function (data) {  //加载成功时执行
            console.log(data)
        },
        columns: [
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'labelName', title: '标签名称'},
            {field: 'createTime', title: '发布时间', formatter: getLocalTime},
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: courseFormatter}
        ]
    })
    //时间转换
    function getLocalTime(value) {
        return (new Date(value).format("yyyy-mm-dd HH:MM:ss"));
    }

});
//分类操作
function courseFormatter(value, row, index) {
    return [
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}
//操作分类事件
var operateEvent = {
    //删除
    'click .remove': function (e, value, row, index) {
        $.Popup({
            template: '确认删除吗?',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/CourseLabel/delCourseLabel?id=" + row.id,
                    async: false,
                    type: "DELETE",
                    success: function (result) {
                        if (result.state && result.state == 200) {
                            $('#Course_table').bootstrapTable('remove', {
                                field: 'id',
                                values: [row.id]
                            });
                            $.Popup({
                                confirm: false,
                                template: "删除成功"
                            });
                            $("#Course_table").bootstrapTable('refresh');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: result.errmsg
                            })
                        }

                    }
                });
            }
        });

    }
};
/*查询标签*/

function createLabel() {
    $("#addGradesModal").modal('show');
}
$("#confirmCmd").click(function () {
    $('#labelcreate').ajaxSubmit({
        url: "/v1/CourseLabel/add",
        type: 'post',
        dataType: 'json',
        processData: false,
        contentType: false,
        beforeSend: function () {
            return $("#labelcreate").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: "创建成功"
                });
                $("#addGradesModal").modal('hide');
                $('#Course_table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    })

})

