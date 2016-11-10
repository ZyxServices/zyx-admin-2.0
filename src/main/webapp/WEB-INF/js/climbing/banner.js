/**
 * Created by ZYX on 2016/11/9.
 */
$(function(){
    initBanner(1);
})

function initBanner(area) {
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
    dataArray.push('<a class="withdraw p5" href="javascript:void(0)" title="withdraw">撤回</a>');
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
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
    'click .edit':function (e, value, row, index) {/*编辑有不是公用的部分*/
        AREA = row.area;
        MODEL = row.model;
        $("#bannerList").hide();
        $("#bannerEdit").show();
        $("#photoCover").html('选择图片');
        $("#images").attr({"src":""});
        if(row.model == ACTIVITYMODEL || row.model == DYNAMICMODEL){
            ISVALID = true;
            var imgUrl = $(row.image).attr("href");
            $("#preImage").html('<img src='+imgUrl+'>');
            bannerSequence(row.model,row.area,row.sequence,'sequence');
            $("#sequence").val(row.sequence);
        }else if(row.model == USERMODEL){
            ISVALID = true;
            bannerSequence(row.model,row.area,row.sequence,'sequence');
        }else if(row.model == LIVEMODEL){
            var imgUrl = $(row.image).attr("href");
            $("#preImage").html('<img src='+imgUrl+'>');
            if(row.area == HOMEPAGEAREA){
                $('input[name=area]').eq(0).attr({"checked":"checked"});
                $("#homepageSequence").show();
                $("#standSequence").hide();
                $("#standSequence").removeAttr("name");
                $("#homepageSequence").attr({"name":'sequence'});
                bannerSequence(row.model,row.area,row.sequence,"homepageSequence");
                $("#homepageSequence").val(row.sequence);
                $('#bannerForm').data('bootstrapValidator')
                    .updateStatus('sequence', 'NOT_VALIDATED', null)
                    .validateField('sequence');
                $("input[name=area]").change(function () {
                    var _val = $(this).val();
                    var option = '';
                    if(_val == 1){/*首页*/
                        $("#homepageSequence").show();
                        $("#standSequence").hide();
                        $("#standSequence").removeAttr("name");
                        $("#homepageSequence").attr({"name":'sequence'});
                        bannerSequence(row.model,row.area,row.sequence,"homepageSequence");
                        $('#bannerForm').data('bootstrapValidator')
                            .updateStatus('sequence', 'NOT_VALIDATED', null)
                            .validateField('sequence');
                    }else{
                        $("#standSequence").show();
                        $("#homepageSequence").hide();
                        $("#homepageSequence").removeAttr("name");
                        $("#standSequence").attr({"name":'sequence'});
                        bannerSequence(row.model,STANDAREA,'',"standSequence");
                        $('#bannerForm').data('bootstrapValidator')
                            .updateStatus('sequence', 'NOT_VALIDATED', null)
                            .validateField('sequence');
                    }
                })
            }else{
                $('input[name=area]').eq(1).attr({"checked":"checked"});
                $("#standSequence").show();
                $("#homepageSequence").hide();
                $("#homepageSequence").removeAttr("name");
                $("#standSequence").attr({"name":'sequence'});
                bannerSequence(row.model,row.area,row.sequence,"standSequence");
                $("#standSequence").val(row.sequence);
                $('#bannerForm').data('bootstrapValidator')
                    .updateStatus('sequence', 'NOT_VALIDATED', null)
                    .validateField('sequence');
                $("input[name=area]").change(function () {
                    var _val = $(this).val();
                    var option = '';
                    if(_val == 1){/*首页*/
                        $("#homepageSequence").show();
                        $("#standSequence").hide();
                        $("#standSequence").removeAttr("name");
                        $("#homepageSequence").attr({"name":'sequence'});
                        bannerSequence(row.model,HOMEPAGEAREA,'',"homepageSequence");
                        $('#bannerForm').data('bootstrapValidator')
                            .updateStatus('sequence', 'NOT_VALIDATED', null)
                            .validateField('sequence');
                    }else{
                        $("#standSequence").show();
                        $("#homepageSequence").hide();
                        $("#homepageSequence").removeAttr("name");
                        $("#standSequence").attr({"name":'sequence'});
                        bannerSequence(row.model,row.area,row.sequence,"standSequence");
                        $('#bannerForm').data('bootstrapValidator')
                            .updateStatus('sequence', 'NOT_VALIDATED', null)
                            .validateField('sequence');
                    }
                })
            }

        }else if(row.model == POSTMODEL || row.model == CIRCLEMODEL){
            if(row.area == CIRCLEAREA && row.model == POSTMODEL){/*精选圈子的帖子--有图*/
                $('input[name=area]').eq(1).attr({"checked":"checked"});
                $("#homepageSequence").hide();
                $("#circleSequence").show();
                var imgUrl = $(row.image).attr("href");
                $("#preImage").html('<img src='+imgUrl+'>');
                $("#homepageSequence").removeAttr("name");
                $("#circleSequence").attr({"name":'sequence'});
                bannerSequence(row.model,row.area,row.sequence,"circleSequence");
                $("input[name=area]").change(function () {
                    var _val = $(this).val();
                    var option = '';
                    if(_val == 1){/*首页*/
                        $("#imageWrap").hide();
                        $("#homepageSequence").show();
                        $("#circleSequence").hide();
                        $("#circleSequence").removeAttr("name");
                        $("#homepageSequence").attr({"name":'sequence'});
                        bannerSequence(row.model,HOMEPAGEAREA,'',"homepageSequence");
                        $('#bannerForm').data('bootstrapValidator')
                            .updateStatus('sequence', 'NOT_VALIDATED', null)
                            .validateField('sequence');
                    }else{
                        $("#imageWrap").show();
                        $("#circleSequence").show();
                        $("#homepageSequence").hide();
                        $("#homepageSequence").removeAttr("name");
                        $("#circleSequence").attr({"name":'sequence'});
                        bannerSequence(row.model,row.area,row.sequence,"circleSequence");
                        $('#bannerForm').data('bootstrapValidator')
                            .updateStatus('sequence', 'NOT_VALIDATED', null)
                            .validateField('sequence');
                    }
                })
                $("#homepageSequence").val(row.sequence);
                $('#bannerForm').data('bootstrapValidator').validateField('sequence');
            }else{
                if(row.area == HOMEPAGEAREA){/*首页的精选帖子--无图*/
                    $("#preImgWrap").hide();
                    $("#imageWrap").hide();
                    $("#homepageSequence").show();
                    $("#circleSequence").hide();
                    $('input[name=area]').eq(0).attr({"checked":"checked"});
                    bannerSequence(row.model,row.area,row.sequence,"homepageSequence");
                    $("input[name=area]").change(function () {
                        var _val = $(this).val();
                        var option = '';
                        if(_val == 1){/*帖子--首页*/
                            $("#imageWrap").hide();
                            $("#homepageSequence").show();
                            $("#circleSequence").hide();
                            $("#circleSequence").removeAttr("name");
                            $("#homepageSequence").attr({"name":'sequence'});
                            bannerSequence(row.model,row.area,row.sequence,"homepageSequence");
                            $('#bannerForm').data('bootstrapValidator')
                                .updateStatus('sequence', 'NOT_VALIDATED', null)
                                .validateField('sequence');
                            $('#bannerForm')
                                .bootstrapValidator('removeField', 'imageR')
                                .data('bootstrapValidator').validate();
                        }else{/*帖子--圈子--有图*/
                            $("#imageWrap").show();
                            $("#circleSequence").show();
                            $("#homepageSequence").hide();
                            $("#homepageSequence").removeAttr("name");
                            $("#circleSequence").attr({"name":'sequence'});
                            bannerSequence(row.model,CIRCLEAREA,'',"circleSequence");
                            $('#bannerForm').bootstrapValidator('addField', 'imageR', {
                                validators: {
                                    notEmpty: {
                                        message: '精选圈子的帖子必须有图'
                                    }
                                }
                            });
                            $('#bannerForm').data('bootstrapValidator')
                                .updateStatus('sequence', 'NOT_VALIDATED', null)
                                .validateField('sequence')
                                .updateStatus('imageR', 'NOT_VALIDATED', null)
                                .validateField('imageR');
                        }
                    })
                    $('#bannerForm').data('bootstrapValidator').validateField('sequence');
                }else if(row.area == CIRCLEAREA){/*精选圈子里的圈子--无图*/
                    $("#preImgWrap").hide();
                    $("#area").hide();
                    $("#imageWrap").hide();
                    $("#circleSequence").show();
                    bannerSequence(row.model,row.area,row.sequence,"circleSequence");
                    $('#bannerForm')
                        .bootstrapValidator('removeField', 'sequence')
                        .data('bootstrapValidator').validate();
                }
            }
        }
        $("#title").val(row.modelTitle);
        $("#devaId").val(row.id);
        if(row.state == "是"){
            $('input[name=state]').eq(0).attr({"checked":"checked"});
        }else{
            $('input[name=state]').eq(1).attr({"checked":"checked"});
        }

    }
}
/*
 * banner序列的请求
 * */
function bannerSequence(model, area, currentSequence, idObj) {
    $.ajax({
        url: "/v1/deva/sequence",
        type: 'POST',
        dataType: 'json',
        async:false,
        data: {model: model,area: area},
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
                    $.Popup({
                        confirm: false,
                        template: '活动banner序列号已满，请先删除其他序列号再推荐'
                    });
                    option = '<option value="">banner序列已满，请先删除</option>'
                }
                $("#"+idObj).html(option);
            }else{
                $.Popup({
                    confirm: false,
                    template: "未获取到banner序号，请刷新页面"
                })
            }
        }
    });
}
function changeBannerTable(obj) {
    var _val = $(obj).val();
    initBanner(_val);
}

