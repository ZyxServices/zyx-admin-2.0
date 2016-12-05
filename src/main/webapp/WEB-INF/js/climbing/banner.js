/**
 * Created by ZYX on 2016/11/9.
 */
$(function(){
    initBanner();
    /*banner表单验证*/
    $("#bannerForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'sequence': {
                validators: {
                    notEmpty: {
                        message: 'banner序号不能为空'
                    }
                }
            }
        }
    });
})

function initBanner() {
    $("#banner-list-table").bootstrapTable('destroy');
    $("#banner-list-table").bootstrapTable({
        url: "/v2/deva/list",
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
                area: $('#b-area').val(),
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search,
                appType:$("#appType").val()
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
            item.area = item.area == 1 ? "首页":"求约";
            item.model = item.model == 1 ? "教程攻略":"求约";
            item.state = item.state == 1 ? "激活":"未激活";
            item._image = item.imageUrl;
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                item.imageUrl = '<a href="http://image.tiyujia.com/'+item.imageUrl+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>';
            }
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
    if(row.state == "未激活"){
        dataArray.push('<a class="withdraw p5" href="javascript:void(0)" title="withdraw">取消撤回</a>');
    }else{
        dataArray.push('<a class="withdraw p5" href="javascript:void(0)" title="withdraw">撤回</a>');
    }
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
/*table事件*/
var operateEvents = {
    'click .withdraw':function (e, value, row, index) {
        var _state = '';
        if(row.state == "激活"){
            _state = 0;
        }else{
            _state = 1;
        }
        $.Popup({
            title: '撤回',
            template: '确定撤回该banner？',
            saveEvent: function () {
                $.ajax({
                    url: "/v2/deva/cancel",
                    async: false,
                    type: "post",
                    data:{id:row.id,state:_state},
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: result.successmsg
                            })
                            $('#banner-list-table').bootstrapTable('refresh');
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
                    url: "/v2/deva/delete?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#banner-list-table').bootstrapTable('refresh');
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
    },
    'click .edit':function (e, value, row, index) {/*编辑有不是公用的部分*/
        row.model = row.model == "教程攻略" ? "1":"2";
        row.area = row.area == "首页" ? "1":"2";
        $("#devaId").val(row.id);
        $("#model").val(row.model);
        $("#area").val(row.area);
        $("#imageUrl").val(row._image);
        $("#preImage").html('<img src="http://image.tiyujia.com/'+ row._image+'">');
        $("#createEditAppType").val(row.appType);
        getBannerSequence(row.area,row.sequence,row.appType);
        $("#sequence").val(row.sequence);
        EDITAREA = row.area;
        EDITSEQUENCE = row.sequence;
        $("#images").attr("src", "");
        $("#bannerList").hide();
        $("#bannerEdit").show();
        $('#bannerForm').data('bootstrapValidator')
            .updateStatus('sequence', 'NOT_VALIDATED', null)
            .validateField('sequence');
    }
}
/*当前编辑的area和sequence*/
var EDITAREA = '';
var EDITSEQUENCE = '';
/*
 * banner序列的请求
 * */
function getBannerSequence(area, currentSequence,appType) {
    $.ajax({
        url: "/v2/deva/sequence",
        type: 'POST',
        dataType: 'json',
        async:false,
        data: {area: area,appType:appType},
        success: function (result) {
            if(result.state == 200){
                var bannerNoArr = result.data;
                var option = '';
                if(currentSequence){
                    bannerNoArr.push(currentSequence);
                }
                if(result.data != ''){
                    bannerNoArr = bannerNoArr.sort(function(a,b){/*排序*/
                        return a - b
                    });
                    for(var i = 0;i < bannerNoArr.length; i++){
                        option += '<option>'+result.data[i]+'</option>';
                    }
                }else{
                    option = '<option value="">banner序列已满，请先删除</option>'
                }
                $("#sequence").html(option);
            }else{
                $.Popup({
                    confirm: false,
                    template: "未获取到banner序号，请刷新页面"
                })
            }
        }
    });
}
/*改变推荐位置*/
function changeDevArea(obj) {
    var area = $(obj).val();
    var appType = $("#createEditAppType").val()
    if(EDITAREA == area){/*当前自己的序列号*/
        getBannerSequence(area,EDITSEQUENCE,appType);
        $("#sequence").val(EDITSEQUENCE);
    }else{/*不存在序列号的时候*/
        getBannerSequence(area,'',appType);
    }
}
/*
 * 修改推荐
 * */
$("#confirmDeva").click(function () {
    if($("#lefile")[0].files[0]== undefined){/*未上传新的图片*/
        if($("#bannerForm").data('bootstrapValidator').isValid()){
            confirmDevaSubmit();
        }
    }else{
        var formData = new FormData();
        formData.append('avatar', $("#lefile")[0].files[0]);
        $.ajax({
            url: 'http://119.61.66.55:18100/v2/upload',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                return $("#bannerForm").data('bootstrapValidator').isValid();
            },
            success:function (result) {
                if(result.state == 200){
                    $("#imageUrl").val(result.data.url);
                    confirmDevaSubmit();
                }
            }
        })
    }
});

function confirmDevaSubmit() {
    $('#bannerForm').ajaxSubmit({
        url: '/v2/deva/update',
        type: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: "修改成功"
                });
                $("#bannerList").show();
                $("#bannerEdit").hide();
                $('#banner-list-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
}


/*
 * 图片的上传和预览
 * */
$('#lefile').change(function () {
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#images").attr("src", objUrl);
        }
    }else{
        console.log($("#lefile")[0].files[0]);
        $("#photoCover").html("选择图片");
        $("#images").attr("src", "");
    }
});
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

