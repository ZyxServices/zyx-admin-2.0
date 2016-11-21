/**
 * Created by ZYX on 2016/7/20.
 */
$(function () {
/*创建的验证*/
    $("#sysUserCreateForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'username': {
                validators: {
                    notEmpty: {
                        message: '用户名称不能为空'
                    }
                }
            }, 'pass': {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }, 'name': {
                validators: {
                    notEmpty: {
                        message: '管理员名称不能为空'
                    }
                }
            }, 'roleId': {
                validators: {
                    notEmpty: {
                        message: '请选择权限等级'
                    }
                }
            }, 'remark': {
                validators: {
                    notEmpty: {
                        message: '请输入备注'
                    }
                }
            }
        }
    });

    $("#administrators-list-table").bootstrapTable({
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        pageList: [10, 15, 20, 25],  //记录数可选列表
        checkbox: true,
        height: 500,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        strictSearch: true,
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        method: "get",
        url: "/v1/sysUser/list",
        dataField: "data",
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                searchText: params.searchText,
                sortName: params.sortName,
                sortOrder: params.sortOrder
            };
            return param;
        }
    });
/*创建管理员*/
    $("#sysUserCreateForm").ajaxForm({
        url: '/v1/sysUser/insert',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#sysUserCreateForm").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state == 200) {
                backToSysUsers();
            } else {
                if (result.state == 9004) {
                    $.Popup({
                        confirm: false,
                        template: '用户已存在'
                    });
                } else {
                    $.Popup({
                        confirm: false,
                        template: '用户新建失败'
                    });
                }
                $("#createButton").attr("disabled", false);
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });
/*修改权限等级*/
    $("#sysUserEditForm").ajaxForm({
        url: '/v1/sysUser/editRole',
        type: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.state == 200) {
                $("#roleModal").modal('hide');
                $('#administrators-list-table').bootstrapTable('refresh');
                $.Popup({
                    confirm: false,
                    template: '修改权限等级成功'
                });
            } else {
                $.Popup({
                    confirm: false,
                    template: '修改权限等级失败'
                });
                $("#editButton").attr("disabled", false);
            }
        },
        error: function () {
            $("#editButton").attr("disabled", false);
        }
    });
})

// 日志列操作
function operateLog(value, row, index) {
    var _html = [];
    _html.push('<a class="p5 look" href="javascript:void(0)" title="查看">查看</a>');
    return _html.join('');
}

// 操作列
function operateFormatter(value, row, index) {
    var _html = [];
    _html.push('<a class="p5 setJurisdiction" href="javascript:void(0)" title="权限设置">权限设置</a>');
    _html.push('<a class="p5 move" href="javascript:void(0)" title="删除">删除</a>');
    return _html.join('');
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
function stateFormat(data) {
    return data == 0?"操作成功":"操作失败"
}
// 操作事件edit
var operateEvent = {
    'click .look': function (e, value, row) {
        $("#operateLogModal").modal("show");
        $("#administrators-log-table").bootstrapTable('destroy');
        $("#administrators-log-table").bootstrapTable({
            toolbar: '#toolbar',        //工具按钮用哪个容器
            striped: true,           //是否显示行间隔色
            cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,          //是否显示分页（*）
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            pageNumber: 1,            //初始化加载第一页，默认第一页
            pageSize: 10,            //每页的记录行数（*）
            pageList: [10, 15, 20, 25],  //记录数可选列表
            checkbox: true,
            height: 300,
            checkboxHeader: "true",
            sortable: true,           //是否启用排序
            sortOrder: "asc",          //排序方式
            strictSearch: true,
            sidePagination: "server",
            method: "get",
            url: "/v1/sysUser/queryOperation",
            dataField: "data",
            queryParamsType: "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    pageNumber: params.pageSize,
                    page: params.pageNumber,
                    searchText: params.searchText,
                    userId: row.id
                };
                return param;
            }
        });
    },
    'click .setJurisdiction': function (e, value, row) {
        $("#roleModal").modal("show");
        $("#editUserId").val(row["id"]);
        $.ajax({
            url: "/v1/role/all",
            type: "GET",
            dataType: 'json',
            success: function (data) {
                var json = data["data"];
                var option = '';
                for (var i = 0; i < json.length; i++) {
                    if (json[i].id == row["roleId"]) {
                        option += '<option value="' + json[i].id + '" selected>' + json[i].role_name + '</option>'
                    } else {
                        option += '<option value="' + json[i].id + '">' + json[i].role_name + '</option>'
                    }
                }
                $("#edit_role_select").html(option);
            },
            error: function (er) {
            }
        });
    },
    'click .move': function (e, value, row) {
        $.Popup({
            title: '删除',
            template: '确定删除该管理员',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/sysUser/delete?id="+row.id,
                    async: false,
                    type: "delete",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#administrators-list-table').bootstrapTable('refresh');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '删除失败'
                            })
                        }
                    },
                    error:function (res) {
                        $.Popup({
                            confirm: false,
                            template: res
                        })
                    }
                });
            }
        })
    }
};

function backToSysUsers() {
    $("#administratorsList").show();
    $("#administratorsCreate").hide();
    $("#administratorsRoleEdit").hide();
    $('#administrators-list-table').bootstrapTable("refresh");
}
/*点击创建管理员*/
function createAdministrators() {
    $("#administratorsList").hide();
    $("#administratorsCreate").show();
    $("#administratorsRoleEdit").hide();
    $.ajax({
        url: "/v1/role/all",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            var json = data["data"];
            var option = '<option value="">请选择</option>';
            for (var i = 0; i < json.length; i++) {
                option += '<option value="' + json[i].id + '">' + json[i].role_name + '</option>';
            }
            $("#role_select").html(option);
        },
        error: function (er) {
        }
    });
}

function beginCreateSysUser() {
    $("#sysUserCreateForm").submit();
}

function beginEditSysUser() {
    $("#sysUserEditForm").submit();
}
