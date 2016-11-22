/**
 * Created by 文楷 on 2016/7/15.
 */
var $table = $('#dynamic_table'),
    $remove = $('#remove');
function initTable(num) {
    $("#dynamic_table").bootstrapTable('destroy');
    $('#dynamic_table').bootstrapTable({
        url: ("/v1/equip/queryEquip"),
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
        sortable: true,           //是否启用排序
        sortOrder: "desc",          //排序方式
        pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
        smartDisplay: false,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        strictSearch: true,        //是否启用模糊收索
        queryParamsType: "undefined",
        dataField: "data",
        silentSort: false,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                page: params.pageNumber,
                pageNumber: params.pageSize,
                searchText: params.searchText,
                sortName: params.sortName,
                equipType:num
                //sortOrder: params.sortOrder
            };
            return param;
        },
        columns: [
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'labelId', title: 'id', align: 'center', valign: 'middle'},
            {field: 'title', title: '帖子标题'},
            {field: 'equipLabelName', title: '标签', sortable: true},
            {field: 'account.nickName', title: '发布人'},
            {field: 'createTime', title: '发布时间', sortable: true, width: 200, formatter: timeFormat},
            {field: 'commentCount', title: '评论数'},
            {field: 'zanCount', title: '点赞数', sortable: true},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: operateFormatter}]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
    $remove.click(function () {
        var ids = getIdSelections();
        $.Popup({
            template: '确认批量删除吗?',
            saveEvent: function () {
                ids.forEach(function (e) {
                    var delUrl = '/concern/deleteOne?id=' + e;
                    $.ajax({
                        url: delUrl,
                        async: false,
                        type: "delete",
                        success: function (meg) {
                            $table.bootstrapTable("refresh");

                        }

                    })
                });
            }
        })
    });
    $('#labelTabel').bootstrapTable({
        url: ("/v1/equipLabel/queryAll"),
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
        sortable: true,           //是否启用排序
        sortOrder: "desc",          //排序方式
        pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
        smartDisplay: false,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列,
        sidePagination: "server",
        strictSearch: true,        //是否启用模糊收索
        queryParamsType: "undefined",
        dataField: "data",
        silentSort: false,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                start: params.pageNumber - 1,
                pageSize: params.pageSize,
                searchText: params.searchText,
                sortName: params.sortName
                //sortOrder: params.sortOrder
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
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'labelName', title: '标签名称'},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: seeUrlFormatter}]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
    /*查询创建活动时需要选择的用户*/
    $.ajax({
        url: '/v1/equipLabel/queryByState',
        type: 'get',
        dataType: 'json',
        success: function (result) {
            result.data.forEach(function(e){
                $('#v_label').append('<option value="'+ e.id+'">'+ e.labelName+'</option>')
            })
        }
    })

}
//操作
function operateFormatter(value, row, index) {
    return row.equipType == 1 ? ['<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="edit">编辑</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">'+btnState(row)+'</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'].join('')
        : ['<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">'+btnState(row)+'</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'].join('');
}
//查看Url
function seeUrlFormatter(value, row, index) {
    return [
        '<a class="labelEnable p5"   href="javascript:void(0)" title="Like">'+EnableState(row)+'</a>',
        '<a class="labelRemove p5"   href="javascript:void(0)" title="Like">删除</a>',
    ].join('');
}
// 认证状态格式化
function authFormatter(value) {
    return value == 2 ? "已认证" : value == 1 ? "待审核" : value == 3 ? "认证失败" : "未认证";
}
//动态类型
function typeFormatter(data) {
    return data == 0 ? "测试" : data == 1 ? "个人动态" : data == 2 ? "活动动态" : data == 3 ? "明星动态" : "圈子动态";
}
//动态状态按钮初始化
function btnState(row) {
    return row.mask == 0 ? "屏蔽" : "取消屏蔽";
}
//动态状态按钮初始化
function EnableState(row) {
    return row.state == 0 ? "禁用" : "启用";
}
//判断标题是否为空
function judgeTiltle(title) {
    return title == null ? '标题为空' : title;
}
function manageLabel(){
    $("#manageLabel").show();
    $("#activityList").hide();
}
//动态推荐内容上传
function devDynamic() {
    $.ajax({
        url: '/v1/deva/add',
        type: 'post',
        data: $('#recommend').serialize(),
        dataType: 'json',
        success: function (result) {
            removeEvent('upload')
            if (result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: '推荐成功'
                })
            } else if (result.state == 73002) {
                $.Popup({
                    confirm: false,
                    template: '推荐内容重复'
                })
            }
        },
        error: function (res) {
            removeEvent('upload')
            $.Popup({
                confirm: false,
                template: '推荐上传失败（请检查内容是否填写完整！！！）'
            })
        }
    })
}

//操作事件eidt
var operateEventssssss = {
    'click .preview': function (e, value, row, index) {
        var strArry
        $(".dynamicPreview").addClass('on')
        $(".live_index").addClass('hide')
        $('.topicContent').html(row.topicContent)
        $('.username').html(row.userVo.nickName)
        row.imgUrl != null ? strArry = row.imgUrl.split(',') : '';
        for (var i in strArry) {
            $('#container').append(' <div class="box"><div class="boximg"><img src=' + 'http://image.tiyujia.com/' + strArry[i] + '></div></div>')

        }
    },
    'click .edit': function (e, value, row, index) {
        $('#updateCreateFrom').append('<input type="hidden" name="id" value="'+row.id+'">')
        $("#pageTitle").html('编辑帖子')
        $("#title").val(row.labelId)
        $("#v_label").val(row.labelId)
        $('#equipment-summernote').summernote('code', row.content);
        $("#createModify").show();
        $("#activityList").hide();
        $('#Czs').attr('onclick','operateEventssssss.createEquip(this,1)')
    },
    'click .Shield': function (e, value, row, index) {
        var state, btnval, btn, html;
        var btnclick = this
        switch (this.innerHTML) {
            case '屏蔽':
                state = 1, btnval = '取消屏蔽', html = '确认屏蔽吗?';
                break;
            case '取消屏蔽':
                state = 0, btnval = '屏蔽', html = '确定取消屏蔽吗?';
                break;
        }
        $.Popup({
            template: html,
            saveEvent: function () {
                $.ajax({
                    url: "/v1/equip/maskEquip?id=" + row.id + "&maskType=" + state + "",
                    async: false,
                    type: "post",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            btnclick
                            btn = btnval
                        } else {
                            alert(result.successmsg)
                        }
                    }
                })
                btnclick.innerHTML = btn;
                //$(btnclick).parent().prevAll()[6].innerHTML = statetext;
            }
        })

    },
    'click .remove': function (e, value, row, index) {
        var delUrl = '/v1/equip/delEquip?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'dynamic_table', 'post')
    },
    'click .labelRemove': function (e, value, row, index) {
        var delUrl = '/v1/equipLabel/delEquipLabel?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'labelTabel', 'delete')
    },
    'click .labelEnable': function (e, value, row, index) {
        var state, btnval, btn, html;
        var btnclick = this
        switch (this.innerHTML) {
            case '禁用':
                state = 1, btnval = '启用', html = '确认禁用吗?';
                break;
            case '启用':
                state = 0, btnval = '禁用', html = '确定启用吗?';
                break;
        }
        $.Popup({
            template: html,
            saveEvent: function () {
                $.ajax({
                    url: "/v1/equipLabel/updateState?id=" + row.id + "&state=" + state + "",
                    async: false,
                    type: "post",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            btnclick
                            btn = btnval
                        } else {
                            alert(result.successmsg)
                        }
                    }
                })
                btnclick.innerHTML = btn;
                //$(btnclick).parent().prevAll()[6].innerHTML = statetext;
            }
        })
    },
    createEquip: function (obj, eidt) {
        var url;
        eidt == true ? url = '/v1/equip/updateEquip' : url = '/v1/equip/add'
        $.ajax({
            url: url,//提交地址
            data: $("#updateCreateFrom").serialize(),//将表单数据序列化
            type: "POST",
            dataType: "json",
            success: function (result) {
                if (result.state == 200) {
                    window.location.reload();
                } else {
                    $.Popup({
                        confirm: false,
                        template: result.errmsg
                    })
                }
            },
            error: function () {
                $.Popup({
                    confirm: false,
                    template: '上传失败,请检查内容是否填写完整'
                })
            }
        });
    },
    addLabel:function(){
        $.Popup({
            title:'添加标签',
            template: ' <label style="float: left;padding: 0 20px">标签:</label> <div ><input id="labelName"></div>',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/equipLabel/add?userId=1&labelName="+$('#labelName').val()+"",
                    async: false,
                    type: "post",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $('#labelTabel').bootstrapTable("refresh");
                        } else {
                            alert(result.successmsg)
                        }
                    }
                })
            }
        })
    }

};

//查看Url事件
var seeUrl = {
    'click .seeUrl': function (e, value, row, index) {
        alert('You click like seeUrl action, row: ' + JSON.stringify(row));
    }
}
$(function () {
    $(".create_live").click(function () {
        $.ajax({
            url: "/v1/appUser/list/official/all",
            type: 'get',
            dataType: 'json',
            success: function (result) {
                console.log(result)
                if (result.rows.length == 0) {
                    $.Popup({
                        confirm: false,
                        template: '官方账户为空，请添加官方账户再继续!!!'
                    })
                } else {
                    $(".create_liveType").addClass('on')
                    $(".live_index").addClass('hide')
                    var user = '';
                    result.rows.forEach(function (item, i) {
                        user += '<option value=' + item.id + ' >' + item.nickname + '</option>'
                    })
                    $("#choiceUser").append(user)
                }

            }
        });
    })
    $('#equipment-summernote').on('summernote.change', function (content, $editable) {
        $("#desc").val($editable);
        //$('#createVenue').data('bootstrapValidator')
        //    .updateStatus('descContent', 'NOT_VALIDATED', null)
        //    .validateField('descContent');
    }).summernote({
        callbacks: {
            onImageUpload: function (files) {
                //上传图片到服务器，使用了formData对象，至于兼容性...据说对低版本IE不太友好
                var formData = new FormData();
                formData.append('file', files[0]);
                $.ajax({
                    url: 'http://api.tiyujia.com/v1/upload/file',//后台文件上传接口
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (result) {
                        console.log(result)
                        if (result.state == 200) {
                            $('#equipment-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data.url, 'img');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: result.successmsg
                            })
                        }
                    }
                });
            }
        },
        lang: 'zh-CN',
        height: 200
    });
    $(window).resize(function () {
        $('#dynamic_table').bootstrapTable('resetView');
    });
    $('#e_user').change(function(){
        initTable($('#e_user').val());
    })
    initTable($('#e_user').val());
})