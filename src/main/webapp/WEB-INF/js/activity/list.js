/**
 * Created by ZYX on 2016/7/12.
 */

var ISCHANGEIMG = '';/*判断修改活动的时候是否修改了图片*/
$(function () {

    /*表单验证*/
    $("#updateCreateFrom").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'title': {
                validators: {
                    notEmpty: {
                        message: '组合名称不能为空'
                    }
                }
            }, 'imageR': {
                validators: {
                    notEmpty: {
                        message: '请上传图片'
                    }
                }
            }, 'desc': {
                validators: {
                    notEmpty: {
                        message: '请输入内容'
                    }
                }
            }, 'startTime': {
                validators: {
                    notEmpty: {
                        message: '请选择开始时间'
                    },
                    callback: {
                        message: '开始日期不能大于结束日期',
                        callback: function (value, validator, $field) {
                            var endValue = $('#activityEndTime').val();
                            var endTime = new Date(endValue.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(value.replace("-", "/").replace("-", "/"));
                            if (endValue == '') {
                                return true;
                            }else if (endValue != '') {
                                validator.updateStatus('endTime', 'VALID');
                                return startTime <= endTime;
                            }
                        }
                    }
                }
            }, 'endTime': {
                validators: {
                    notEmpty: {
                        message: '请选择结束时间'
                    },
                    callback: {
                        message: '结束日期不能小于开始日期',
                        callback: function (value, validator, $field) {
                            var startValue = $('#activityStartTime').val();
                            var endTime = new Date(value.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(startValue.replace("-", "/").replace("-", "/"));
                            validator.updateStatus('startTime', 'VALID');
                            return endTime >= startTime;
                        }
                    }
                }
            }/*, 'lastTime': {
                validators: {
                    notEmpty: {
                        message: '请选择活动截止时间'
                    },
                    callback: {
                        message: '活动截止时间不能大于开始日期',
                        callback: function (value, validator, $field) {
                            var startValue = $('#activityStartTime').val();
                            var lastTime = new Date(value.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(startValue.replace("-", "/").replace("-", "/"));
                            validator.updateStatus('startTime', 'VALID');
                            return lastTime <= startTime;
                        }
                    }
                }
            }*/, 'memberString': {
                validators: {
                    notEmpty: {
                        message: '必须填一个'
                    }
                }
            }, 'maxPeople': {
                validators: {
                    integer: {
                        message: '请输入整数'
                    }
                }
            }
        }
    });

    $('#activity-summernote').on('summernote.change', function (content, $editable) {
        $("#desc").val($editable);
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('desc', 'NOT_VALIDATED', null)
            .validateField('desc');
    }).summernote({
        callbacks: {
            onImageUpload: function (files) {
                console.log(files)
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

});

$("#czS").click(function () {
    var formData = new FormData();
    formData.append('imgFile', $("#lefile")[0].files[0]);
    if ($("#listType").html() == "创建") {
        /*创建*/
        $.ajax({
            url: '/v1/upload/file',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                $("#upload").modal('show');
                $("#uploadContent").html('创建中，请稍后...');
                console.log($("#updateCreateFrom").data('bootstrapValidator').isValid());
                /*传图片之前做验证*/
                if ($("#examine").val() == 1) {
                    var desc = "";
                    $("#template").find("input:checked").each(function (item) {
                        if (item == 0) {
                            desc += $(this).val()
                        } else {
                            desc += ("," + $(this).val());
                        }
                    });
                    $("#memberTemplate").val(desc);
                }
                return $("#updateCreateFrom").data('bootstrapValidator').isValid();
            },
            complete:function () {
                $("#upload").modal('hide');
            },
            success: function (result) {
                if (result.state == 200) {
                    $("#image").val(result.data.url);
                    updateCreateFrom('/v1/activity/release');
                }
            },
            error:function (result) {
                $.Popup({
                    confirm: false,
                    template: result
                });
            }
        })
    } else {
        /*修改*/
        var isChange = $("#image").val();
        if(ISCHANGEIMG != isChange){/*不相等代表换了图片*/
            $.ajax({
                url: '/v1/upload/file',
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function () {
                    /*传图片之前做验证*/
                    $("#upload").modal('show');
                    $("#uploadContent").html('活动修改中，请稍后...');
                    if ($("#examine").val() == 1) {
                        var desc = "";
                        $("#template").find("input:checked").each(function (item) {
                            if (item == 0) {
                                desc += $(this).val()
                            } else {
                                desc += ("," + $(this).val());
                            }
                        });
                        $("#memberTemplate").val(desc);
                    }
                    return $("#updateCreateFrom").data('bootstrapValidator').isValid();
                },
                success:function (result) {
                    if(result.state == 200){
                        $("#image").val(result.data.url);
                        updateCreateFrom('/v1/activity/update');
                    }
                }
            })
        }else{
            if($("#updateCreateFrom").data('bootstrapValidator').isValid()){
                $("#upload").modal('show');
                $("#uploadContent").html('活动修改中，请稍后...');
                updateCreateFrom('/v1/activity/update');
            }
        }
    }
})

function updateCreateFrom(url) {
    $('#updateCreateFrom').ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        complete:function () {
            $("#upload").modal('hide');
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: result.successmsg
                });
                $("#activityList").show();
                $("#createModify").hide();
                $('#activity-list-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
}

function queryActivityById(id, type) {
    $.ajax({
        url: "/v1/activity/queryActivityById",
        type: 'POST',
        dataType: 'json',
        data: {activityId: id},
        success: function (result) {
            if (result.state == 200) {
                var datas = result.data;
                $("#avtivityId").val(datas.id);
                $("#choiceUser").val(datas.userId);
                $("#title").val(datas.title);
                $("#images").attr("src", "http://image.tiyujia.com/" + datas.imgUrls);
                $("#image").val(datas.imgUrls);
                ISCHANGEIMG = datas.imgUrls;
                $('#activity-summernote').summernote('code', datas.descContent);
                $("#activityStartTime").val(new Date(datas.startTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#activityEndTime").val(new Date(datas.endTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#address").val(datas.address);
                $('input[name=type]').eq(!datas.type).attr({"checked": "checked"});
                $("#price").val(datas.price);
                $("#signEndTime").val(new Date(datas.lastTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#maxPeople").val(datas.maxPeople);
                $("#phone").val(datas.phone);
                $("#visible").val(datas.visible);
                $("#examine").val(datas.examine);
                if (datas.examine == 1) {
                    isReviewed();
                    var template = (datas.memberTemplate).split(",");
                    var html = "";
                    if (type == 1) {
                        template.forEach(function (item, i) {
                            html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked disabled>" + item + "</label>";
                        });
                    } else {
                        template.forEach(function (item, i) {
                            html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked>" + item + "</label>";
                        });
                        html += "<a href='javascript:void (0)' onclick='choiceMore()' id='addBtn'>+</a>";
                    }
                    $("#template").empty();
                    $("#template").append(html)
                }
                if (type == 0) {/*0代表编辑，1代表预览*/
                    $('#updateCreateFrom')
                        .bootstrapValidator('removeField', 'imageR')
                        .data('bootstrapValidator').validate();
                }
            } else {
                $.Popup({
                    confirm: false,
                    template: result.successmsg
                })
            }
        }
    });
}
function userList(){
    /*查询创建活动时需要选择的用户*/
    $.ajax({
        url: "/v1/appUser/list/official/all",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            var user = '';
            result.rows.forEach(function (item, i) {
                user += '<option value=' + item.id + '>' + item.nickname + '</option>'
            })
            $("#choiceUser").html(user)
        }
    });
}
function createActivity() {
    userList();
    $("#listType").html("创建");
    $("#photoCover").html("选择文件");
    $("#createModify").show();
    $("#activityList").hide();
    $("#images").attr({'src': ''});
    $("#avtivityId").val('');
    $('#activity-summernote').summernote('reset');
    var html = '<label class="checkbox"><input type="checkbox" value="手机号码">手机号码</label><label class="checkbox"><input type="checkbox" value="姓名">姓名</label> <label class="checkbox"><input type="checkbox" value="身份证号码">身份证号码</label> <label class="checkbox"><input type="checkbox" value="性别">性别</label> <label class="checkbox"><input type="checkbox" value="年龄">年龄</label> <label class="checkbox"><input type="checkbox" value="地址">地址</label> <a href="javascript:void (0)" onclick="choiceMore()" id="addBtn">+</a>'
    $("#template").html(html);
    $("#userRequired").hide();
    $('#updateCreateFrom')[0].reset();
    $('#updateCreateFrom').data('bootstrapValidator').resetForm(true);
    $("#maxPeople").val("999");
    $('#updateCreateFrom').data('bootstrapValidator').validateField('maxPeople');
}
function manageLabel() {
    $("#manageLabel").show();
    $("#activityList").hide();
    $("#userRequired").hide();

}
/*是否需要审核*/
function isReviewed(obj) {
    var val = $("#examine").val();
    if (val == 1) {
        $("#userRequired").show(500);
        $('#updateCreateFrom').bootstrapValidator('addField', 'memberString', {
            validators: {
                notEmpty: {
                    message: '请至少选择一个比填项'
                }
            }
        });
    } else {
        $("#userRequired").hide(500);
        $('#updateCreateFrom').bootstrapValidator('removeField', 'memberString')
    }
}
/*修改活动选择更多*/
function choiceMore() {
    $("#addChoice").toggle(500);
    $("#addBtn").hide();
}
/*活动增加用户必填的字段*/
function createRequired() {
    var requiredVal = $("#requiredVal").val();
    if (requiredVal == '') {
        $("#userRequiredInput").html("必填字段不能为空");
        return;
    } else {
        $("#userRequiredInput").html("*");
    }
    var val = '<label class="checkbox"><input type="checkbox" checked value=' + requiredVal.trim() + '>' + requiredVal.trim() + '</label>';
    $("#addBtn").before(val);
    $("#requiredVal").val('');
    $("#addBtn").show();
    $("#addChoice").toggle(500);
}
/*创建活动选择更多*/
function choiceMoreRel() {
    $("#addChoiceRel").toggle(500);
    $("#addBtnRel").hide();
}

/*创建中type=file的样式处理*/
$('input[id=lefile]').change(function () {
    $('#updateCreateFrom').bootstrapValidator('addField', 'imageR', {
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

/*推荐中type=file的样式处理*/
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

