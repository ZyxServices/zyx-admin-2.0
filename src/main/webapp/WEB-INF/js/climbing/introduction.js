/**
 * Created by ZYX on 2016/11/9.
 */
$(function () {
    $("#courseCreateFrom").bootstrapValidator({
        fields: {
            "title": {
                validators: {
                    notEmpty: {
                        message: '请输入名称'
                    }
                }
            },
            "content": {
                validators: {
                    notEmpty: {
                        message: '请上传圈子头像'
                    }
                }
            },
            "labelId": {
                validators: {
                    notEmpty: {
                        message: '请选择分类'
                    }
                }
            },
            "courseType": {
                validators: {
                    notEmpty: {
                        message: '请输入简介'
                    }
                }
            }
        }
    });

//圈子表格数据

    $("#Course_table").bootstrapTable({
        type: 'get',
        url: ("/v1/course/queryCourse"),
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
            {field: 'title', title: '标题'},
            {field: 'courseLabelName', title: '标签'},
            {field: 'courseLabelName', title: '类型'},
            {field: 'imgUrl', title: '图片'},
            {field: 'createTime', title: '发布时间', formatter: getLocalTime},
            {field: 'commentCount', title: '评论数'},
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
    var btnText;
    if (row.mask == 0) {
        btnText = "屏蔽"
    } else if (row.mask == 1) {
        btnText = "取消屏蔽"
    }
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">' + btnText + '</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}
//操作分类事件
var operateEvent = {
    //预览
    'click .preview': function (e, value, row, index) {
        console.log(row);
        $("#createModify").show();
        $("#activityList").hide();
        $("input[name=title]").val(row.title).attr("disabled", "disabled")
        $('#course-summernote').summernote('code', row.content);
        $("#labelId").chosen();
        $("#labelId option[value='" + row.labelId + "']").attr("selected", true);
        $("#examine option[value='" + row.courseType + "']").attr("selected", true);
        $("#labelId").trigger("liszt:updated");
        $("#labelId").chosen();
        $("#labelId").attr("disabled", "disabled");
        $("#examine").attr("disabled", "disabled");
    },
    //推荐
    'click .recommend': function (e, value, row, index) {


    },
    //编辑
    'click .edit': function (e, value, row, index) {

    },

    //屏蔽
    'click .Shield': function (e, value, row, index) {
        //屏蔽状态
        var state;
        if (row.state == 0) {
            state = 1;
        }
        else if (row.state == 1) {
            state = 0;
        }
        //屏蔽确认
        $.Popup({
            template: '屏蔽之后，教程将不展示在首页模块中。 ?',
            saveEvent: function () {
                $.ajax({
                    async: false,
                    type: "delete",
                    url: "/v1/course/maskCourse?id=" + row.id + "&state=" + state,
                    success: function (data) {
                        if (row.state == 1) {
                            $.Popup({
                                confirm: false,
                                template: "取消屏蔽成功"
                            });
                        }
                        else if (row.state = 0) {
                            $.Popup({
                                confirm: false,
                                template: "屏蔽成功"
                            });

                        }
                        $("#Course_table").bootstrapTable('refresh');
                    }
                })
            }
        })
    },
    //删除
    'click .remove': function (e, value, row, index) {
        $.Popup({
            template: '确认删除吗?',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/course/delCourse?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (data) {
                        $('#Course_table').bootstrapTable('remove', {
                            field: 'id',
                            values: [row.id]
                        });
                        $.Popup({
                            confirm: false,
                            template: "删除成功"
                        });
                        $("#Course_table").bootstrapTable('refresh');
                    }
                });
            }
        });

    }
};

/*查询标签*/
var firstopction = "<option value=''></option>";
var courseLable = $.ajax({
    url: "/v1/CourseLabel/queryByState",
    type: 'get',
    dataType: 'json',
    success: function (data) {
        var html = "";
        html = html
        for (var i = 0; i < data.data.length; i++) {
            html = html + "<option value='" + data.data[i].id + "'>" + data.data[i].labelName + "</option>"
        }
        $("#labelId").append(firstopction + html)
    }
});
function courseLabel() {
    window.location.href = "/menu/climbing/couserlabel";
}
function createcourse() {
    $("#createModify").show();
    $("#activityList").hide();
    $("#courseCreateFrom").attr("value", 1);
    $("#labelId").chosen();
    courseLable;
}
$("#courseSure").click(function () {
    var courseStatue = $("#courseCreateFrom").val();
    $('#post-summernote').summernote('code', "");
    if (courseStatue == 1) {
        Grade("/v1/course/add", "创建成功");
    }
});
//summernote图片路径处理
$('#course-summernote').on('summernote.change', function (content, $editable) {
    $("#desc").val($editable);
    var makrup = $('#course-summernote').summernote('code');
    $("input[name=content]").val(makrup);
    $("#courseCreateFrom").bootstrapValidator('updateStatus', 'content', 'NOT_VALIDATED');
}).summernote({
    callbacks: {
        onImageUpload: function (files) {
            console.log(files);
            //上传图片到服务器，使用了formData对象，至于兼容性...据说对低版本IE不太友好
            var formData = new FormData();
            formData.append('imgFile', files[0]);
            $.ajax({
                url: '/v1/upload/file',//后台文件上传接口
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    console.log(result);
                    if (result.state == 200) {
                        $('#post-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data.url, 'img');
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
//创建教程、编辑教程公用方法
function Grade(url, template, errmsg) {
    $('#courseCreateFrom').ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        processData: false,
        contentType: false,
        beforeSend: function () {
            return $("#courseCreateFrom").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: template
                });
                $('#Course_table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    })

}
/*图片预览*/
//建立一個可存取到該file的url
function getImgURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}
