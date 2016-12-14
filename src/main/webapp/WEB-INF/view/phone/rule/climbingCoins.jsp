<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/12/14
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
    <title>攀岩币</title>
</head>
<style>
    ul, li {
        list-style: none;
        margin: 0;
        padding: 0;
    }
    body {
        -webkit-touch-callout: none;
        -webkit-font-smoothing: antialiased;
        font-smoothing: antialiased;
        -webkit-text-size-adjust: none;
        -moz-text-size-adjust: none;
        -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        margin: 0;
        padding: 0;
        color: #000;
        word-wrap: break-word;
        font-size: 14px;
        font-family: "Helvetica Neue", "Roboto", "Segoe UI", sans-serif;
        line-height: 20px;
        text-rendering: optimizeLegibility;
        -webkit-backface-visibility: hidden;
        -webkit-user-drag: none;
        -ms-content-zooming: none;
        background-color: #f7f7f7;
        /*overflow-y: scroll;*/
    }

    .header {
        width: 100%;
        position: fixed;
        z-index: 2000   ;
        top: 0;
        left: 0;
        height: 45px;
        /*padding: 0 12px;*/
        line-height: 45px;
    }
    .header-Cd80204 {
        background-color: #d80204;
        color: #FFFFFF;
    }

    .header .title {
        width: 100%;
        text-align: center;
    }

    .header .title::after {
        float: left;
    }

    .header .back-button {
        float: left;
    }
    .shop-car .title{
        border-top: rgb(229, 229, 229) solid 1px;
        border-bottom: rgb(229, 229, 229) solid 1px;
        padding-left: 43px;
        height: 30px;
        line-height: 30px;
        font-weight: 500;
    }
    .shop-car button{
        background: none;
        float: right;
        /* margin: 6px; */
        height: 30px;
        border: none;
        padding-right: 13px;
    }
    .shop-car ul {
        padding: 50px 0 10px 0;
    }
    .shop_content.shop-car ul li{
        height: 20px;
    }
    /*服务协议*/
    .Software_agreement  .title{
        font-size: 16px;
        font-weight: 700;
        width: 100%;
        text-align: center;
    }
    .Software_agreement .agreemnet ul{
        padding: 10px 0px;
    }
    .Software_agreement .agreemnet ul li{
        text-indent: 2em;
        padding: 8px 0;
        margin: 0 15px;
    }
    .Software_agreement .agreemnet ul li:first-child{
        padding:8px 14px ;
    }
    .content {
        margin-top: 60px;
        /*overflow-y: scroll;*/
        /*border-bottom:#333333 1px solid ;*/
    }
    .shop {
        margin: 60px 0 55px 0;
    }
    .rule {
        padding: 20px 20px 10px 20px;
        background-color: white;
        margin-top: 10px;
    }
    .QaBtn{
        padding: 4px 8px;
        border: 1px solid #ff702a;
        border-radius: 3px;
        margin-right: 10px;
    }
    .aborder{
        border: 1px solid #999999;
    }
    .answer{
        margin-top: 10px;
        color: #999999;
        line-height: 25px;
    }
    .question .QaBtn{
        color: #ff702a;
    }
</style>
<body>
<div class="header header-Cd80204">
    <!--<div class="back-button"><</div>-->
    <!--<div class="header-function-button">编辑</div>-->
    <div class="title">攀岩币规则</div>
</div>
<div class="shop content Software_agreement"  style="margin-top: 45px;">
    <div class="agreemnet rule" style="margin-top: 0;">
        <div class="question"><span class="QaBtn">Q</span>什么是攀岩币?</div>
        <div class="answer"><span class="QaBtn aborder">A</span> 只要登录趣攀岩，就能加入攀岩币系统，每日进行发布动态、装备、活动，及分享，均能得到攀岩币；</div>
    </div>
    <div class="agreemnet rule">
        <div class="question"><span class="QaBtn">Q</span>攀岩币能做什么?</div>
        <div class="answer"><span class="QaBtn aborder">A</span> 累积一定数量的攀岩币，可以参加官方发布的抽奖活动，抽取自己感兴趣的奖品，每周都会有新鲜奖品上线哦！</div>
    </div>
    <div class="agreemnet rule">
        <div class="question"><span class="QaBtn">Q</span>如何快速获取攀岩币</div>
        <div class="answer"><span class="QaBtn aborder">A</span> ①完成每日固定任务即可获得；②发布装备秀、活动并且获得相应点赞量可以获得；</div>
    </div>
    <div class="agreemnet rule">
        <div class="question"><span class="QaBtn">Q</span>如何快速获取攀岩币</div>
        <div class="answer"><span class="QaBtn aborder">A</span> ①完成每日固定任务即可获得；②发布装备秀、活动并且获得相应点赞量可以获得；</div>
    </div>
    <div class="agreemnet rule">
        <div class="question"><span class="QaBtn">Q</span>作弊惩罚机制</div>
        <div class="answer"><span class="QaBtn aborder">A</span> 趣攀岩将对恶意刷攀岩币的用户做出以下处理：没收所有攀岩币，做封号处理，并不接受任何形式的申诉；</div>
    </div>
    <div class="agreemnet rule">
        <div class="question"><span class="QaBtn">Q</span>相关说明</div>
        <div class="answer"><span class="QaBtn aborder">A</span>攀岩币相关活动最终解释权归趣攀岩所有。</div>
    </div>
</div>
</body>
</html>
