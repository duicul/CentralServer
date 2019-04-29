var loggedin;
var refreshtime=30000;

function addinputpin(){
	pin=$("#inp_pin_no").val()
	name=$("#inp_pin_name").val()
	sensor=$("#inp_pin_sensor").val()
	if(name.length==0)
		{alert("Enter Pin Name");
		return;}
	var retVal = confirm("Add input pin "+pin+" "+name);
    if( retVal == false ) {
       return;}
	$.ajax({url:"/CentralServer/AddInputPin?pin="+pin+"&name="+name+"&sensor="+sensor,success : function(result)
	    {if(result=="error")
	    	return;
        if(result=="okay"){
        	alert("Pin "+pin +" added")
        	sensorgauges()
        }
	}});
}

function addoutputpin(){
	pin=$("#out_pin_no").val()
	name=$("#out_pin_name").val()
	if(name.length==0)
		{alert("Enter Pin Name");
		return;}
	var retVal = confirm("Add output pin "+pin+" "+name);
    if( retVal == false ) {
       return;}
		$.ajax({url:"/CentralServer/AddOutputPin?pin="+pin+"&name="+name,success : function(result)
		    {if(result=="error")
		    	return;
	        if(result=="okay"){
	        	alert("Pin "+pin +" added")
	        	loadoutputpinslist()
	        }
		}});
}

function removeinputpin(pin_no){
	 var retVal = confirm("Remove input pin "+pin_no);
     if( retVal == false ) {
        return;}
	console.log("removeinputpin"+pin_no)
	$.ajax({url:"/CentralServer/RemoveInputPin?pin="+pin_no,success : function(result)
	    {if(result=="error")
	    	return;
        if(result=="okay"){
        	alert("Pininput "+pin_no +" removed")
        	sensorgauges()
        }
	}});
}

function removeoutputpin(pin_no){
	 var retVal = confirm("Remove output pin "+pin_no);
     if( retVal == false ) {
        return;}
	console.log("removeoutputpin"+pin_no)
	$.ajax({url:"/CentralServer/RemoveOutputPin?pin="+pin_no,success : function(result)
	    {if(result=="error")
	    	return;
        if(result=="okay"){
        	alert("Pinoutput "+pin_no +" removed")
        	loadoutputpinslist()
        }
	}});
}

function signup(){
	var formData = new FormData();
	var xmlhttp = new XMLHttpRequest();
	var url = "/CentralServer/SignUp";
	var user=$("#user_txt_signup").val();
	var password=$("#pass_txt_signup").val();
	var email=$("#email_txt_signup").val();
	var address=$("#address_txt_signup").val();
	var phone=$("#phone_txt_signup").val();
	var info=$("#info_txt_signup").val();
	regex=new RegExp(".+@.+\..+")
	if(!regex.test(email)){
		alert("Wrong Email format")
		return;
	}
		
	$.post(url,
			  {"user": user,
			   "password":password,
			   "email": email,
			   "address":address,
			   "phone": phone,
			   "info":info},
			  function(data, status){
				  logstatus();
				  if(data=="okay"){ console.log("signup");}
				  else alert("Wrong Username/Password");
				  //location.reload();
			  });	
	
}

function loadoutputpinslist(){
  $.ajax({url:"/CentralServer/OutputPinsList",success : function(result)
     {$("#outputpinslist").html(result);}});  }

function togglepin(pin_no){
	  $.ajax({url:"/CentralServer/ToggleOutputPin?pin_no="+pin_no,success : function(result)
		     {loadoutputpinslist();}});}

function toggleinputpin(pin_no){
	  $.ajax({url:"/CentralServer/ToggleInputPin?pin_no="+pin_no,success : function(result)
		     {sensorgauges();}});}

function loadinputpinslist(pin_no){
	$.ajax({url:"/CentralServer/InputPinsList",success : function(result)
	     {$("#inputpinslist").html(result);}}); 
	if(loggedin==true)
		setTimeout(loadinputpinslist,refreshtime);}

function login()
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
			  if(data=="okay"){
				  loggedin=true;
				  console.log("loggedin");}
			  else alert("Wrong Username/Password");
		  });}

function logstatus()
{ $.ajax({url:"/CentralServer/LogStatus",success : function(result)
    {status=""
	if(result=="error"){
	console.log("logstatus error")
    status="<form class=\"form-inline\" id=\"loginform\">";
	status+="<div class=\"input-group\">";
	status+="<input type=\"text\" class=\"form-control\" placeholder=\"UserName\" id=\"user_txt\" width=\"30\" />"/*+"</div>"*/;
	status+="<input type=\"password\" class=\"form-control\" placeholder=\"Password\" id=\"pass_txt\" width=\"30\" />"/*+"</div>"*/;
	status+="<input type=\"submit\" class=\"btn btn-primary\" value=\"Login\" />"/*+"</div>"*/;
	status+="</div></form>";
	loggedin=false;
	}
    else{
    console.log("logstatus okay")
    loggedin=true;
	boxaddpins_signup();
	status+="<div class=\"mr-auto navbar-nav\"><div class=\"mr-auto navbar-brand\"> Hello "+result+"</div></div>";
	status+="<button onclick=\"logout()\" class=\"btn btn-primary mr-sm-2\">Logout</button>";}
	$("#logstatus").html(status);
	loadoutputpinslist();
	loadinputpinslist();
	sensorgauges(true);
    $("#loginform").submit(function( event ) { console.log("loginform");event.preventDefault();login();});
    $("#signupform").submit(function( event ) { console.log("signupform");event.preventDefault();signup();});
    },
    dataType: "text"});
console.log(loggedin)
}

function logout(){
	$.ajax({url:"/CentralServer/Logout",success : function(result)
	    {logstatus();
	    if(result=="okay"){
	    	loggedin=false;
	    	init()
	    	console.log("loggedout");
	    	//location.reload()
	    	$("#graphdiv").css("width","")
	    	$("#graphdiv").css("height","")
	    	$("#graphdiv").html("")
	    }
	    }});
}

function inputpinlog(pin){
	/*if(loggedin==false)
		return;*/
	$("#graphdiv").css("width","100%")
	$("#graphdiv").css("height","300px")
	$.ajax({url:"/CentralServer/InputPinLog?pin="+pin,success : function(result)
	    {if(result=="error")
	    	return;
		//$("#graph").html(result)
		//console.log(eval(result))
		var chart = new CanvasJS.Chart("graphdiv", {
			animationEnabled: true,
			title:{
				text: "SensorData"
			},
			axisY:{
				includeZero: true
			},
			data:eval(result)//JSON.stringify(eval('('+result+')'))
		});
		chart.render();
		}
	    });
}

function sensorgauges(repeat){
	if(loggedin==true){
		refresh="<i class=\"fas fa-sync pointer\" onclick=\"sensorgauges()\">Refresh</i><br/> "
		$.ajax({url:"/CentralServer/SensorGauges",success : function(result){
			$("#sensorgauges").html(refresh+result);}
		});
		console.log("sensor gauges "+loggedin+" "+repeat)
		if(repeat==true){
			console.log("sensor gauges repeat")
			setTimeout(function(){sensorgauges(true);},refreshtime);
		}
	}else $("#sensorgauges").html("");
}

function boxaddpins_signup(){
	var data_pins="";
	if(loggedin==true){
	data_pins+="<div class=\"row\">"
	data_pins+="<div class=\"col\">"
	data_pins+="<input type=\"text\" placeholder=\"Name\" id=\"inp_pin_name\"/> ";
	data_pins+=" <select id=\"inp_pin_no\">";
	for(var i=2;i<=26;i++)
		 data_pins+="<option value=\""+i+"\">"+i+"</option>"
	data_pins+="</select> "
	sensors=["DHT11","DHT22","PIR"]
	data_pins+=" <select id=\"inp_pin_sensor\">"
	for(i in sensors)
			 data_pins+="<option value=\""+sensors[i]+"\">"+sensors[i]+"</option>"
	data_pins+="</select> "
	data_pins+=" <input type=\"button\" class=\"btn btn-success\" onclick=\"addinputpin()\" value=\"Addinputpin\"></button>"
	data_pins+="<div class=\"row\">"
	data_pins+="<div class=\"col\">"
	data_pins+="<input type=\"text\" placeholder=\"Name\" id=\"out_pin_name\"/> ";
	data_pins+=" <select id=\"out_pin_no\">";
	for(var i=2;i<=26;i++)
		 data_pins+="<option value=\""+i+"\">"+i+"</option>"
	data_pins+="</select>"
	data_pins+=" <input type=\"button\" class=\"btn btn-success\" onclick=\"addoutputpin()\" value=\"Addoutputpin\"></button>"	
	data_pins+="</div></div>"
	}
	else{
	data_pins="<script>loggedin=false</script><form class=\"form-inline\" id=\"signupform\">";
    data_pins+="<div class=\"input-group\">";
    data_pins+="<input type=\"text\" class=\"form-control\" placeholder=\"UserName\" id=\"user_txt_signup\" width=\"30\" />";
	data_pins+="<input type=\"password\" class=\"form-control\" placeholder=\"Password\" id=\"pass_txt_signup\" width=\"30\" />";
	data_pins+="<input type=\"text\" class=\"form-control\" placeholder=\"e-mail\" id=\"email_txt_signup\" width=\"30\" />";
	data_pins+="<input type=\"text\" class=\"form-control\" placeholder=\"Address\" id=\"address_txt_signup\" width=\"30\" />";
	data_pins+="<input type=\"text\" class=\"form-control\" placeholder=\"Phone\" id=\"phone_txt_signup\" width=\"30\" />";
	data_pins+="<input type=\"text\" class=\"form-control\" placeholder=\"Info\" id=\"info_txt_signup\" width=\"30\" />";
	data_pins+="<br />";
	data_pins+="<input type=\"submit\" class=\"btn btn-primary\" value=\"SignUp\" />";
	data_pins+="</div></form>";
		
	}
	$("#boxaddpins").html(data_pins);
}
function init(){
	loggedin=false;
	logstatus();
	loadinputpinslist();
	loadoutputpinslist();
	boxaddpins_signup();
	sensorgauges(true);
}