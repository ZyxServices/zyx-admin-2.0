/**
 * Created by ZYX on 2016/11/9.
 */

$(function () {
    //增加等级验证
    $("#lineForm").bootstrapValidator({
        fields: {
            "name": {
                validators: {
                    notEmpty: {
                        message: '请输入等级名称'
                    }
                }
            },
            "step": {
                validators: {
                    notEmpty: {
                        message: '请输入阶级序号'
                    },
                    numeric: {message: '只能输入数字'}
                }
            },
            "minScore": {
                validators: {
                    notEmpty: {
                        message: '请输入该等级最小积分'
                    },
                    numeric: {message: '只能输入数字'}
                }
            },
            "maxScore": {
                validators: {
                    notEmpty: {
                        message: '请输入该等级最大积分'
                    },
                    numeric: {message: '只能输入数字'}
                }
            }

        }
    });
    $("#homepage-list-table").bootstrapTable('destroy');
    $("#homepage-list-table").bootstrapTable({
        type: 'get',
        url: ("/v2/level/list"),
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 13,            //每页的记录行数（*）
        pageList: [13],
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        height: 500,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        sidePagination: "server",
        strictSearch: false,        //是否启用模糊收索
        queryParamsType: "undefined",
        dataField: "data",
        queryParams: function queryParams(params) {   //设置查询参数
            return [];
        },
        columns: [
            {field: 'step', title: '阶级'},
            {field: 'name', title: '等级名称'},
            {field: 'minScore', title: '等级最小积分'},
            {field: 'maxScore', title: '等级最大积分'},
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: circleFormatter}
        ]
    })

})
//分类操作
function circleFormatter(value, row, index) {
    return [
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}
//操作分类事件
var operateEvent = {
    //编辑等级
    'click .edit': function (e, value, row, index) {
        $("#addGradesModal").modal('show');
        $("#lineForm").attr("value", 2);
        $("input[name=id]").val(row.id);
        $("input[name=name]").val(row.name);
        $("input[name=step]").val(row.step).attr("disabled", "disabled");
        $("input[name=minScore]").val(row.minScore).attr("disabled", "disabled");
        $("input[name=maxScore]").val(row.maxScore).attr("disabled", "disabled");

    },
    //等级删除
    'click .remove': function (e, value, row, index) {
        $.Popup({
            template: '确认删除吗?',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/level/del?id=" + row.id,
                    async: false,
                    type: "post",
                    success: function (data) {
                        if (result.state && result.state == 200) {
                            $('#homepage-list-table').bootstrapTable('remove', {
                                field: 'id',
                                values: [row.id]
                            });
                            $.Popup({
                                confirm: false,
                                template: "删除成功"
                            });
                            $("#homepage-list-table").bootstrapTable('refresh');
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

/*创建等级*/
function addGrades() {
    $("#addGradesModal").modal('show');
    $("#lineForm").attr("value", 1);
    $("input[name=name]").attr("value", "").removeAttr("disabled");
    $("input[name=step]").attr("value", "").removeAttr("disabled");
    $("input[name=minScore]").attr("value", "").removeAttr("disabled");
    $("input[name=maxScore]").attr("value", "").removeAttr("disabled");

}
$("#confirmCmd").click(function () {
    var stutate = $("#lineForm").val();
    if (stutate == 1) {
        Grade("/v2/level/insert", "创建成功");
    }
    else if (stutate == 2) {
        Grade("/v2/level/update", "修改成功")
    }
    console.log(stutate);
})
//创建等级、编辑等级公用方法
function Grade(url, template, errmsg) {
    $('#lineForm').ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        processData: false,
        contentType: false,
        beforeSend: function () {
            return $("#lineForm").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: template
                });
                $("#addGradesModal").modal('hide');
                $('#homepage-list-table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    })

}

