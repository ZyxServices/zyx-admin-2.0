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
            'imageR': {
                validators: {
                    notEmpty: {
                        message: '请上传图片'
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
        },
        columns: [
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'title', title: '标题'},
            {field: 'courseLabelName', title: '标签'},
            {field: 'courseType', title: '类型' ,formatter:transType},
            {field: 'imgUrl', title: '图片', formatter:getImage},
            {field: 'createTime', title: '发布时间', formatter: getLocalTime},
            {field: 'commentCount', title: '评论数'},
            {field: 'zanCount', title: '点赞数'},
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: courseFormatter}
        ]
    })
    //时间转换
    function getLocalTime(value) {
        return (new Date(value).format("yyyy-mm-dd HH:MM:ss"));
    }
    /*table列表图片显示*/
    function getImage(value) {
        if(value){
            var imgUrl = value.split(".");
            value = '<a href="http://image.tiyujia.com/'+value+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>';
        }
        return value;
    }
    function transType(value) {
        return value == 0 ? "图文":"视频";

    }
});
//分类操作
function courseFormatter(value, row, index) {
    var btnText;
    var Recommend;
    if (row.mask == 0) {
        btnText = "屏蔽"
    } else if (row.mask == 1) {
        btnText = "取消屏蔽"
    }
    if (row.recommendType == 0) {
        Recommend = "未推荐"
    }
    else if (row.recommendType == 1) {
        Recommend = "推荐"
    }
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">' + Recommend + '</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">' + btnText + '</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}
//操作分类事件
var operateEvent = {
    //预览
    'click .preview': function (e, value, row, index) {
        $("#createModify").show();
        $("#activityList").hide();
        $("input[name=title]").val(row.title).attr("disabled", "disabled");
        $('#activity-summernote').summernote('destroy');
        $("#images").attr("src",'http://image.tiyujia.com/'+row.imgUrl+'');
        $('#summernotContent').html(row.content);
        $("#labelId option[value='" + row.labelId + "']").attr("selected", true);
        $("#examine option[value='" + row.courseType + "']").attr("selected", true);
        $("#labelId").trigger("liszt:updated");
        $("#labelId").chosen();
        $("#labelId").attr("disabled", "disabled");
        $("#examine").attr("disabled", "disabled");
    },
    //推荐
    'click .recommend': function (e, value, row, index) {
        $("#courseSelect").empty();
        $.post("/v2/deva/sequence", {area: "1"}, function (result) {
            if (result.state == 200) {
                if (result.data.length > 0) {
                    for (var i = 0; i < result.data.length; i++) {
                        $("#courseSelect").append("<option value='" + result.data[i] + "'>" + result.data[i] + "</option>");
                    }
                    $("#CourseModal").modal("show");
                    $("#gridSystemModalLabel").html("教程攻略推荐");
                    $("#gridSystemModalLabel").css("color",'#000');
                    $("#recommendPhotoCover").html("选择文件");
                    $("#recommendImg").attr("src", "");
                    $("input[name=modelId]").val(row.id);
                    $("#devaImage").attr("src",'http://image.tiyujia.com/'+row.imgUrl+'');
                } else {
                    $("#courseSelect").html('<option value="">圈子序列号已满，请先删除</option>');
                    $("#RdSures").attr("disabled", true);
                    $.Popup({
                        confirm: false,
                        template: "圈子序列号已满，请先删除"
                    });
                }
            }
        })
    },
    //编辑
    'click .edit': function (e, value, row, index) {
        console.log(row);
        $("#createModify").show();
        $("#activityList").hide();
        $("#courseCreateFrom").attr("value", 2);
        $("input[name=title]").val(row.title);
        $("#image").val(row.imgUrl);
        $("#courseId").val(row.id);
        /*在编辑的时候判断是否修改了图片*/
        ISCHANGEIMG = row.imgUrl;
        $("#images").attr("src",'http://image.tiyujia.com/'+row.imgUrl+'');
        $('#course-summernote').summernote('code', row.content);
        $("#labelId option[value='" + row.labelId + "']").attr("selected", true);
        $("#examine option[value='" + row.courseType + "']").attr("selected", true);
        $("#labelId").trigger("liszt:updated");
        $("#labelId").chosen();
        $('#courseCreateFrom')
            .bootstrapValidator('removeField', 'imageR')
            .data('bootstrapValidator').validate();
    },

    //屏蔽
    'click .Shield': function (e, value, row, index) {
        //屏蔽状态
        var state;
        if (row.mask == 0) {
            state = 1;
        }
        else if (row.mask == 1) {
            state = 0;
        }
        //屏蔽确认
        $.Popup({
            template: '屏蔽之后，教程将不展示在首页模块中。 ?',
            saveEvent: function () {
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "/v1/course/maskCourse?id=" + row.id + "&maskType=" + state,
                    success: function (data) {
                        console.log(row.mask)
                        if (row.mask == 1) {
                            $.Popup({
                                confirm: false,
                                template: "取消屏蔽成功"
                            });
                        }
                        else if (row.mask = 0) {
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
                    type: "POST",
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
                        }
                        else {
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
//推荐
$("#RdSures").click(function () {
    if ($("select[name=sequence]").val() != '') {
        $("#RdSures").attr("disabled", true);
        $("#gridSystemModalLabel").html("推荐中，请稍后...");
        if($("#recommendFile")[0].files[0] == undefined){/*未修改原先的图片*/
            courseRecommend();
        }else{/*修改了图片，先上传图片*/
            var formData = new FormData();
            formData.append('file', $("#recommendFile")[0].files[0]);
            $.ajax({
                url: 'http://api.tiyujia.com/v1/upload/file',
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                success:function (result) {
                    if(result.state == 200){
                        $("#imageUrl").val(result.data.url);
                        courseRecommend();
                    }else{
                        $("#RdSures").attr("disabled", false);
                        $("#gridSystemModalLabel").html(result.errmsg);
                        $("#gridSystemModalLabel").css("color",'#b94a48');
                    }
                }
            })
        }
    }
})
function courseRecommend() {
    $("#courseRecommend").ajaxSubmit({
        type: "post",
        dateType: "json",
        url: "/v2/deva/add",
        async: false,
        complete:function () {
            $("#gridSystemModalLabel").html("教程攻略推荐");
            $("#gridSystemModalLabel").css("color",'#000');
            $("#RdSures").attr("disabled", false);
        },
        success: function (result) {
            $.Popup({
                confirm: false,
                title: "推荐成功"
            });
            $("#CourseModal").modal("hide");
            $('#courseCreateFrom').bootstrapTable('refresh');
        }

    })
}
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
    $("#courseCreateFrom")[0].reset();
    $('#courseCreateFrom').data('bootstrapValidator').resetForm(true);
    $("#courseCreateFrom").attr("value", 1);
    $("#labelId").chosen();
    courseLable;
    ISCHANGEIMG = '';
}
var ISCHANGEIMG = '';
$("#courseSure").click(function () {
    var formData = new FormData();
    formData.append('file', $("#lefile")[0].files[0]);
    var courseStatue = $("#courseCreateFrom").val();
    $('#post-summernote').summernote('code', "");
    var isChange = $("#image").val();
    if (courseStatue == 1 || ISCHANGEIMG !=isChange) {
        $.ajax({
            url: 'http://api.tiyujia.com/v1/upload/file',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                /*传图片之前做验证*/
                var _isValid = $("#courseCreateFrom").data('bootstrapValidator').isValid();
                if(_isValid){
                    $("#upload").modal('show');
                    if(courseStatue == 1){
                        $("#uploadContent").html('创建中，请稍后...');
                    }else{
                        $("#uploadContent").html('修改中，请稍后...');
                    }
                }else{
                    return _isValid;
                }
            },
            success:function (result) {
                if(result.state == 200){
                    $("#image").val(result.data.url);
                    if(courseStatue == 1){
                        Grade("/v1/course/add", "创建成功");
                    }else{
                        Grade("/v1/course/updateCourse", "编辑成功");
                    }
                }
            },
            error:function (result) {
                $.Popup({
                    confirm: false,
                    template: result
                });
            }
        })
    } else if (courseStatue == 2 && ISCHANGEIMG ==isChange) {
        Grade("/v1/course/updateCourse", "编辑成功");
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
            formData.append('file', files[0]);
            console.log(formData);
            $.ajax({
                url: 'http://api.tiyujia.com/v1/upload/file',//后台文件上传接口
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    console.log(result);
                    console.log(result);
                    if (result.state == 200) {
                        $('#course-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data.url, 'img');
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
                $("#createModify").hide();
                $("#activityList").show();
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


/*创建中type=file的样式处理*/
$('input[id=lefile]').change(function () {
    $('#courseCreateFrom').bootstrapValidator('addField', 'imageR', {
        validators: {
            notEmpty: {
                message: '请上传图片'
            }
        }
    });
    $("#image").val($(this).val());
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#images").attr("src", objUrl);
        }
    }else {
        $("#photoCover").html("选择文件");
        $("#images").attr("src", "");
    }
});
$('input[id=recommendFile]').change(function () {
    if ($(this).val()) {
        $('#recommendPhotoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#recommendImg").attr("src", objUrl);
        }
    }else{
        $("#recommendPhotoCover").html("选择文件");
        $("#recommendImg").attr("src", "");
    }
});
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
