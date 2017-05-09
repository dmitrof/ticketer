<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="<c:url value="/resources/angular.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/controller.js"/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/eventCategory.css"/>">

<html>
<head>
    <title>Добро пожаловать</title>
</head>
<body>
<h4> Добро пожаловать! </h4>

<div class="main-app" ng-app="myApp" ng-init="$ctrl.test('<c:url value="/" />');" ng-controller="customersCtrl as $ctrl" >
    <%--<input type="hidden" ng-model="$ctrl.baseDir" value="<c:url value="/" />" />--%>

    <div class="main-info" >
    <button ng-click="$ctrl.buttonClick();" ng-disabled = "$ctrl.buttonDisabled" ng-model="$ctrl.buttonDisabled" >Заказать</button>
    <br>
    <br>
    Стоимость заказа: <input type="number" ng-model="$ctrl.sum" maxlength="6" required>
    Id пользователя: <input type="number" ng-model="$ctrl.userId" maxlength="10" required>
    </div>
    <div class="event-section" ng-repeat="event in $ctrl.languageSettings.eventInfoList" ng-if="$ctrl.languageSettings.eventMap[event.id].length">
        <div class="event-name">{{event.name}}</div>
        <div class="market-section" ng-repeat="market in $ctrl.languageSettings.eventMap[event.id]">
           <div class="market-name"> {{market.name}} </div>

            <select class="seat-select" id="select_{{event.id}}_{{market.id}}" ng-click="dropselects($event)" ng-model="$ctrl.selects[event.id]"
                    ng-options="activity.name for activity in $ctrl.languageSettings.marketMap[market.id]">
                <option value=""></option>
            </select>

        </div>
    </div>

</div>

</body>
</html>