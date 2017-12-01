<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="BankApp">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="static/css/style_PageAccueil.css">
<title>Gest Bank, LA banque en ligne</title>
</head>
<body ng-controller="MainController as mainCtrl">
<ng-include src="'<c:url value='/static/views/entete.html' />'"></ng-include>
<div class="Ma_vue" ng-view></div>
<ng-include src="'<c:url value='/static/views/piedDePage.html' />'"></ng-include>

	<script type="text/javascript" src="<c:url value='/static/js/utils/angular.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/utils/angular-route.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/app.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/config.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/controller/ctrl_principal.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/controller/ctrl_espace_public.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/controller/ctrl_espace_client.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/controller/ctrl_espace_conseiller.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/controller/ctrl_espace_admin.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/service/service_utilisateur.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/service/service_compte.js' />"></script>
</body>
</html>