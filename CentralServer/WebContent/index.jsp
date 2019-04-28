<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<style>
.main_col{
background-color:rgb(100,120,180);
}
</style>
<%/*
<script src="/CentralServer/js/bootstrap.js"></script>*/ %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="http://localhost:8080/CentralServer/js/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="http://localhost:8080/CentralServer/js/main_script.js?v=1.3"></script>
<script src="http://localhost:8080/CentralServer/js/gauge.min.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<meta charset="ISO-8859-1">
<title>Central Server</title>
</head>
<body onload="init()">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="logstatus">
</nav>
<div class="row">
<div class="col-4" id="sensorgauges">
</div>
<div class="col-6 main_col">
<div class="row" id="boxaddpins">Data</div><br>
<div class="row" id="outputpinslist">Data</div>
<br>
<div class="row" id="inputpinslist">Data</div>
<div id="graphdiv" style="height: 300px; width: 100%;"></div>
<div id="test1"></div>
</div>
</div>

</body>
</html>