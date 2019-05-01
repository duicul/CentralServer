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
	//console.log("removeinputpin"+pin_no)
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
	//console.log("removeoutputpin"+pin_no)
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
	var confirmpassword=$("#pass_txt_signup_confirm").val();
	var email=$("#email_txt_signup").val();
	var address=$("#address_txt_signup").val();
	var phone=$("#phone_txt_signup").val();
	var info=$("#info_txt_signup").val();
	regex=new RegExp(".+@.+\..+")
	
	$("#user_txt_signup").css("border-color","")
	$("#user_txt_signup").css("border-width","")
	$("#email_txt_signup").css("border-color","")
	$("#email_txt_signup").css("border-width","")
	$("#pass_txt_signup").css("border-color","")
	$("#pass_txt_signup").css("border-width","")
	$("#pass_txt_signup_confirm").css("border-color","")
	$("#pass_txt_signup_confirm").css("border-width","")

	if(!regex.test(email)){
		$("#email_txt_signup").css("border-color","red")
		$("#email_txt_signup").css("border-width","thick")
		$("#signup_msg").css("color","red")
		$("#signup_msg").html("Wrong e-mail format")
		return;}
	
	if(password.length<8){
		$("#pass_txt_signup").css("border-color","red")
		$("#pass_txt_signup").css("border-width","thick")
		$("#signup_msg").css("color","red")
		$("#signup_msg").html("Password needs to be at least 8 characters long")
		return;}
	
	if(confirmpassword!=password){		
		$("#pass_txt_signup").css("border-color","red")
		$("#pass_txt_signup").css("border-width","thick")
		$("#pass_txt_signup_confirm").css("border-color","red")
		$("#pass_txt_signup_confirm").css("border-width","thick")
		$("#signup_msg").css("color","red")
		$("#signup_msg").html("Password confirm does not match")
		return;}
		
	$.post(url,
			  {"user": user,
			   "password":password,
			   "email": email,
			   "address":address,
			   "phone": phone,
			   "info":info},
			  function(data, status){
				   if(data=="okay"){boxaddpins_signup()}
				   
				   else {
					    $("#user_txt_signup").css("border-color","red")
						$("#user_txt_signup").css("border-width","thick")
						$("#signup_msg").css("color","red")
						$("#signup_msg").html("User already exists")
				   }
				 
			  });	
	
}

function changepassword(){
	var formData = new FormData();
	var xmlhttp = new XMLHttpRequest();
	var url = "/CentralServer/ChangePassword";
	var password=$("#pass_txt_update").val();
	var oldpassword=$("#oldpass_txt_update").val();
	$("#pass_txt_update").css("border-color","")
	$("#pass_txt_update").css("border-width","")
	$("#pass_txt_update_confirm").css("border-color","")
	$("#pass_txt_update_confirm").css("border-width","")
	
	if(password.length<8){
		$("#change_password_msg").css("color","red")
		$("#change_password_msg").html("Password needs to be at least 8 characters long")
		$("#pass_txt_update").css("border-color","red")
		$("#pass_txt_update").css("border-width","thick")
		return;}
	
	if(password!=oldpassword){
		$("#change_password_msg").css("color","red")
		$("#change_password_msg").html("ConfirmPassword does not match")
		$("#pass_txt_update").css("border-color","red")
		$("#pass_txt_update").css("border-width","thick")
		$("#pass_txt_update_confirm").css("border-color","red")
		$("#pass_txt_update_confirm").css("border-width","thick")
		return;}
	
	$.post(url,
			  {"oldpassword":oldpassword,
				"password":password},
			  function(data, status){
				   if(data=="okay"){loaduserinfo();
 									alert("Updated");}
					  else alert("Error");
			  });	
}

function updateuser(){
	var formData = new FormData();
	var xmlhttp = new XMLHttpRequest();
	var url = "/CentralServer/UpdateUser";
	var email=$("#email_txt_update").val();
	var address=$("#address_txt_update").val();
	var phone=$("#phone_txt_update").val();
	var info=$("#info_txt_update").val();
	regex=new RegExp(".+@.+\..+")
	if(!regex.test(email)){
		$("#email_txt_update").css("border-color","red")
		$("#email_txt_update").css("border-width","thick")
		$("#update_user_msg").css("color","red")
		$("#update_user_msg").html("Wrong e-mail format")
		return;}
	
	$.post(url,
			  {"email": email,
			   "address":address,
			   "phone": phone,
			   "info":info},
			  function(data, status){
				   if(data=="okay"){logstatus();
 									loaduserinfo();
 									alert("Updated");}
					  else alert("Error");
			  });	
	
	
}

function loadoutputpinslist(){
	if(loggedin==true){
  $.ajax({url:"/CentralServer/OutputPinsList",success : function(result)
     {$("#outputpinslist").html("<hr/>"+result);}});}
   else $("#outputpinslist").html("");}

function togglepin(pin_no){
	if(loggedin==true)
	  $.ajax({url:"/CentralServer/ToggleOutputPin?pin_no="+pin_no,success : function(result)
		     {loadoutputpinslist();}});}

function toggleinputpin(pin_no){
	if(loggedin==true)
	  $.ajax({url:"/CentralServer/ToggleInputPin?pin_no="+pin_no,success : function(result)
		     {sensorgauges();}});}

function loadinputpinslist(pin_no){
	if(loggedin==true){
	$.ajax({url:"/CentralServer/InputPinsList",success : function(result)
	     {$("#inputpinslist").html(result);}});}
	else $("#inputpinslist").html("");}

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
			  if(data=="okay"){
				  loggedin=true;
				  logstatus();}
			  else alert("Wrong Username/Password");
		  });}

function logstatus()
{ $.ajax({url:"/CentralServer/LogStatus",success : function(result)
    {status=""
	if(result=="error"){
	//console.log("logstatus error")
    status="<form class=\"form-inline\" id=\"loginform\">";
	status+="<div class=\"input-group\">";
	status+="<input type=\"text\" class=\"form-control\" placeholder=\"UserName\" id=\"user_txt\" width=\"30\" />"/*+"</div>"*/;
	status+="<input type=\"password\" class=\"form-control\" placeholder=\"Password\" id=\"pass_txt\" width=\"30\" />"/*+"</div>"*/;
	status+="<input type=\"submit\" class=\"btn btn-primary\" value=\"Login\" />"/*+"</div>"*/;
	status+="</div></form>";
	loggedin=false;
	}
    else{
    //console.log("logstatus okay")
    loggedin=true;
	boxaddpins_signup();
	status+="<div class=\"mr-auto navbar-brand\">Hello "+result+"</div>";
	status+="<i class=\"pointer fas fa-cog fa-3x\" onclick=\"loaduserinfo()\"></i> "
	status+="<div style=\"width:3em\"></div>"
	status+=" <button onclick=\"logout()\" class=\"btn btn-primary mr-sm-2\">Logout</button>";}
	$("#logstatus").html(status);
	loadoutputpinslist();
	loadinputpinslist();
	sensorgauges(true);
    $("#loginform").submit(function( event ) { console.log("loginform");event.preventDefault();login();});
    $("#signupform").submit(function( event ) { console.log("signupform");event.preventDefault();signup();});
    },
    dataType: "text"});
//console.log(loggedin)
}

function logout(){
	$.ajax({url:"/CentralServer/Logout",success : function(result)
	    {logstatus();
	    if(result=="okay"){
	    	loggedin=false;
	    	init()
	    	//console.log("loggedout");
	    	//location.reload()
	    	$("#graphdiv").css("width","")
	    	$("#graphdiv").css("height","")
	    	$("#graphdiv").html("")
	    }
	    }});
}

function inputpinlog(pin){
	if(loggedin==false)
		return;
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

function loaduserinfo(){
	$("#graphdiv").css("width","")
	$("#graphdiv").css("height","")
	if(loggedin==false)
		return;
	$.ajax({url:"/CentralServer/UserForm",success : function(result)
	    {//console.log(result);
	    if(result=="error")
	    	return;
	    $("#graphdiv").html(result)
	    $("#updateuserform").submit(function( event ) { event.preventDefault();updateuser();});
	    $("#updatepasswordform").submit(function( event ) {event.preventDefault();changepassword();});
	    }
	    });
}
	

function inputpinlogsensors(){
	if(loggedin==false)
		return;
	$.ajax({url:"/CentralServer/InputPinTopLogSensors",success : function(result)
	    {//console.log(result);
		if(result=="error")
	       return;
		}
	    });
}

function sensorgauges(repeat){
	if(loggedin==true){
		loadinputpinslist();
		refresh="<div class=\"row\"><div class=\"col\">"
		refresh+="<i class=\"fas fa-sync pointer fa-2x\" onclick=\"sensorgauges()\">Refresh</i>"
			
		/*refresh+="</div><div class=\"col\">"
		refresh+=" <i class=\"btn pointer far fa-bell fa-2x\">Alerts</i> ";*/
		refresh+="</div></div>"
		$.ajax({url:"/CentralServer/SensorGauges",success : function(result){
			$("#sensorgauges").html(refresh+result);}});

		if(repeat==true){
			//console.log("sensor gauges repeat")
			setTimeout(function(){sensorgauges(true);},refreshtime);
		}
	}else $("#sensorgauges").html("");
}

function boxaddpins_signup(){
	var data_pins="";
	if(loggedin==true){
	data_pins+="<div class=\"row\">"
	data_pins+="<div class=\"col\"><input type=\"text\" placeholder=\"Name\" id=\"inp_pin_name\"/></div>";
	data_pins+="<div class=\"col\"><select id=\"inp_pin_no\">";
	for(var i=2;i<=26;i++)
		 data_pins+="<option value=\""+i+"\">"+i+"</option>"
	data_pins+="</select></div>"
	sensors=["DHT11","DHT22","PIR"]
	data_pins+="<div class=\"col\"><select id=\"inp_pin_sensor\">"
	for(i in sensors)
			 data_pins+="<option value=\""+sensors[i]+"\">"+sensors[i]+"</option>"
	data_pins+="</select></div>"
	data_pins+="<div class=\"col\"><input type=\"button\" class=\"btn btn-success\" onclick=\"addinputpin()\" value=\"Addinputpin\"></button></div>"
	data_pins+="</div>"
	data_pins+="<hr />"
	data_pins+="<div class=\"row\">"
	data_pins+="<div class=\"col\"><input type=\"text\" placeholder=\"Name\" id=\"out_pin_name\"/></div>";
	data_pins+="<div class=\"col\"><select id=\"out_pin_no\">";
	for(var i=2;i<=26;i++)
		 data_pins+="<option value=\""+i+"\">"+i+"</option>"
	data_pins+="</select></div>"
	data_pins+="<div class=\"col\"></div>"	
	data_pins+="<div class=\"col\"><input type=\"button\" class=\"btn btn-success\" onclick=\"addoutputpin()\" value=\"Addoutputpin\"></button></div>"	
	data_pins+="</div></div>"
	}
	else{
	data_pins="<form class=\"form-inline\" id=\"signupform\">";
    data_pins+="<div class=\"row\"><div class=\"col\">User Name</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"UserName\" id=\"user_txt_signup\" width=\"30\" /></div></div>";
	data_pins+="<div class=\"row\"><div class=\"col\">Password</div><div class=\"col\"><input type=\"password\" class=\"form-control\" placeholder=\"Password\" id=\"pass_txt_signup\" width=\"30\" /></div></div>";
	data_pins+="<div class=\"row\"><div class=\"col\">Confirm Password</div><div class=\"col\"><input type=\"password\" class=\"form-control\" placeholder=\"ConfirmPassword\" id=\"pass_txt_signup_confirm\" width=\"30\" /></div></div>";
	data_pins+="<div class=\"row\"><div class=\"col\">E-mail</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"E-mail\" id=\"email_txt_signup\" width=\"30\" /></div></div>";
	data_pins+="<div class=\"row\"><div class=\"col\">Address</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"Address\" id=\"address_txt_signup\" width=\"30\" /></div></div>";
	data_pins+="<div class=\"row\"><div class=\"col\">Phone</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"Phone\" id=\"phone_txt_signup\" width=\"30\" /></div></div>";
	data_pins+="<div class=\"row\"><div class=\"col\">Info</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"Info\" id=\"info_txt_signup\" width=\"30\" /></div></div>";
	data_pins+="<div class=\"row\"><div class=\"col\"></div><div class=\"col\"><input type=\"submit\" class=\"btn btn-primary\" value=\"SignUp\" /></div></div>";
	data_pins+="</form>";
	data_pins+="<div id=\"signup_msg\"></div>"	
	}
	$("#boxaddpins").html(data_pins);
}
function init(){
	loggedin=false;
	logstatus();
	loadinputpinslist();
	loadoutputpinslist();
	boxaddpins_signup();}