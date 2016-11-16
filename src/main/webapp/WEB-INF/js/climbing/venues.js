/**
 * Created by 文楷 on 2016/7/15.
 */
var $table = $('#dynamic_table'),
    $remove = $('#remove');
//场馆列表
function initTable(city) {
    $("#dynamic_table").bootstrapTable('destroy');
    $('#dynamic_table').bootstrapTable({
        url: ("/v2/venue/queryVenue"),
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
                page: params.pageNumber - 1,
                pageNumber: params.pageSize,
                searchText: params.searchText,
                sortName: params.sortName,
                city:city
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
            {field: 'name', title: '场馆名称'},
            {field: 'type', title: '类型', sortable: true},
            {field: 'city', title: '所属城市'},
            {field: 'longitude', title: '经度'},
            {field: 'latitude', title: '纬度'},
            {field: 'level', title: '难度系数', sortable: true, width: 200},
            {field: 'address', title: '地址'},
            {field: 'phone', title: '联系电话'},
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

}
//路线列表
function queryLine(url) {
    $('#lineTabel').bootstrapTable({
        url: (url),
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
                page: params.pageNumber - 1,
                pageNumber: params.pageSize,
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
            {field: 'venueId', title: 'venueId', align: 'center', valign: 'middle'},
            {field: 'url', title: '线路图', align: 'center', valign: 'middle'},
            {field: 'path', title: '线路名称', align: 'center', valign: 'middle'},
            {field: 'developer', title: '开线者', align: 'center', valign: 'middle'},
            {field: 'developTime', title: '开线时间', align: 'center', valign: 'middle'},
            {field: 'pathType', title: '线路类型', align: 'center', valign: 'middle'},
            {field: 'level', title: '线路难度', align: 'center', valign: 'middle'},
            {field: 'visitNum', title: '多少人去过', align: 'center', valign: 'middle'},
            {field: 'commentNum', title: '评论数', align: 'center', valign: 'middle'},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: seeUrlFormatter}]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
}
//场馆操作
function operateFormatter(value, row, index) {
    return ['<a class="preview p5"   href="javascript:void(0)" title="preview">查看</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="edit">编辑</a>',
        '<a class="addLine p5" href="javascript:void(0)" title="recommend">管理线路</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'].join('');
}
//路线列表
function seeUrlFormatter(value, row, index) {
    return [
        '<a class="lineEdit p5"   href="javascript:void(0)" title="Like">编辑</a>',
        '<a class="lineDelete p5"   href="javascript:void(0)" title="Like">删除</a>',
    ].join('');
}

//列表操作事件
var operateEventssssss = {
    'click .edit': function (e, value, row, index) {
        typeInfo()
        console.log(row)
        $("#createVenue")[0].reset();
        $('#v_name').val(row.name)
        $('#v_title').val(row.level)
        $('#activity-summernote').summernote('code', row.description);
        //$('#activity-summernote').val(row.description)
        $('#v_address').val(row.address)
        $('#v_longitude').val(row.longitude)
        $('#v_latitude').val(row.latitude)
        $('#v_phone').val(row.phone)
        $('#v_city').val(row.city)
        $('#v_type').val(row.type)
        $('#pageTitle').html('编辑场馆信息')
        $("#czs").attr("onclick", "operateEventssssss.createVenue(this, 1)");
        $("#imgUrls").val(row.imgUrls)
        $("#createVenue").append("<input name='id' class='hide' value='" + row.id + "'>")
    },
    'click .addLine': function (e, value, row, index) {
        queryLine('/v2/sportInfo/querySportInfo?venueId=' + row.id + '');
        $("#lineForm").append('<input type="text" class="hide" id="venueId" name="venueId" value="'+row.id+'">')
        $("#addLine").hide();
        $("#venuesList").hide();
        $("#lineList").show();

    },
    'click .remove': function (e, value, row, index) {
        var delUrl = '/v2/venue/del?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'dynamic_table', 'post')
    },
    'click .lineDelete': function (e, value, row, index) {
        var delUrl = '/v2/sportInfo/del?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'lineTabel', 'post')
    },
    'click .lineEdit':function(e, value, row, index) {
        $('#addLineModal').modal('show')
        $('#addLineModal h3').html('编辑线路')
        $("#lineForm input[name='path']").val(row.path)
        $("#lineForm input[name='developer']").val(row.developer)
        $("#lineForm input[name='developTime']").val(row.developTime)
        $("#lineForm input[name='pathType']").val(row.pathType)
        $("#lineForm input[name='score']").val(row.score)
        $("#lineForm input[name='level']").val(row.level)
        $("#lineForm input[name='pathLength']").val(row.pathLength)
        $("#lineForm input[name='id']").remove()
        $("#lineForm").append("<input name='id' type='hidden' value='" + row.id + "'>")
        $("#lineUrl").val(row.url)
        $('#sureLine').attr('onclick','operateEventssssss.submitLineForm(this, 1)')
    },
    createVenue: function (obj, eidt) {
        var url;
        eidt == true ? url = '/v2/venue/update' : url = '/v2/venue/insert'
        if ($("#imgUrls").val() == '') {
            uploadImg('lefile','imgUrls',function(){
                operateEventssssss.submitVenuesForm(url)
            })
        } else {
            operateEventssssss.submitVenuesForm(url)
        }
    },
    submitLineForm:function(obj, eidt){
        var url;
        eidt == true ? url = '/v2/sportInfo/update' : url = '/v2/sportInfo/insert'
        if ($("#lineUrl").val() == '') {
            uploadImg('lineImage','lineUrl',function(){
                operateEventssssss.lineForm(url)
            })
        } else {
            operateEventssssss.lineForm(url)
        }
    },
    submitVenuesForm: function (url) {
        $.ajax({
            url: url,//提交地址
            data: $("#createVenue").serialize(),//将表单数据序列化
            type: "POST",
            dataType: "json",
            success: function (result) {
                if (result.state == 200) {
                    console.log(result.url)
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
    lineForm:function(url){
        $.ajax({
            url: url,//提交地址
            data: $("#lineForm").serialize(),//将表单数据序列化
            type: "POST",
            dataType: "json",
            success: function (result) {
                if (result.state == 200) {
                    $.Popup({
                        confirm: false,
                        template: '操作成功'
                    })
                    $('#addLineModal').modal('hide')
                    $('#lineTabel').bootstrapTable("refresh");
                } else {
                    $.Popup({
                        confirm: false,
                        template: '数据格式错误'
                    })
                }
            },
            error: function () {
                $.Popup({
                    confirm: false,
                    template: '数据格式错误'
                })
            }
        });
    },
    addLineModal:function(){
        $('#sureLine').attr('onclick','operateEventssssss.submitLineForm()')
        $('#addLineModal').modal('show')
        var venueId=$('#venueId').val();
        $('#lineForm')[0].reset()
        $('#venueId').val(venueId);
    }

};

//查看Url事件
var seeUrl = {
    'click .seeUrl': function (e, value, row, index) {
        alert('You click like seeUrl action, row: ' + JSON.stringify(row));
    }
}
$(function () {
    $.ajax({
        url: '/v1/city/queryByState',
        type: 'get',
        dataType: 'json',
        success: function (result) {
            result.data.forEach(function(e){
                $('#v_city').append('<option value="'+ e.cityName+'">'+ e.cityName+'</option>')
                $('#city').append('<option value="'+ e.cityName+'">'+ e.cityName+'</option>')
            })
        }
    })
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
    $(window).resize(function () {
        $('#dynamic_table').bootstrapTable('resetView');
    });
    $('#activity-summernote').on('summernote.change', function (content, $editable) {
        $("#v_desc").val($editable);
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
                    url: '/v1/upload/file',//后台文件上传接口
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (result) {
                        console.log(result)
                        if (result.state == 200) {
                            $('#activity-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data.url, 'img');
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
    initTable();
})
function typeInfo(){
    $("#addLine").hide();
    $("#venuesList").hide();
    $("#createModify").show();
}
/*点击添加线路---弹出模态窗*/

function changeBannerTable(obj) {
    var _val = $(obj).val();
    initTable(_val);
}
