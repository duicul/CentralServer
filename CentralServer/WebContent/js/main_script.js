
function loadoutputpinslist(){
  $.ajax({url:"/CentralServer/OutputPinsList",success : function(result)
     {$("#outputpinslist").html(result);}});  }

function togglepin(pin_no){
	  $.ajax({url:"/CentralServer/ToggleOutputPin?pin_no="+pin_no,success : function(result)
		     {loadoutputpinslist();}});}
function loadinputpinslist(pin_no){
	$.ajax({url:"/CentralServer/InputPinsList",success : function(result)
	     {$("#inputpinslist").html(result);}}); }

function login(user,password)
{var formData = new FormData();
var xmlhttp = new XMLHttpRequest();
var url = "/CentralServer/Login";
var user=$("#user_txt").val();
var password=$("#pass_txt").val();
$.post(url,
		  {"user": user,
		   "password":password},
		  function(data, status){
			  logstatus();
		  });}

function logstatus()
{ $.ajax({url:"/CentralServer/LogStatus",success : function(result)
    {$("#logstatus").html(result);
    loadoutputpinslist();
    loadinputpinslist();  
    $("#loginform").submit(function( event ) { console.log("loginform");event.preventDefault();login();});
    }});
}

function logout(){
	$.ajax({url:"/CentralServer/Logout",success : function(result)
	    {logstatus();}});
}



function init(){
	loadoutputpinslist();
	loadinputpinslist();
	setInterval(loadinputpinslist,30000);
	logstatus();
}