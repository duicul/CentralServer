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

function getoutputgauge(pin){
	if(loggedin==true){
		  $.ajax({url:"/CentralServer/GetOutputGauge?pin="+pin,success : function(result)
		     {//console.log(result);
		     $("#out_pin_"+pin).html(result);}});}
	else $("#out_pin_"+pin).html("");
	
}

function loadoutputpinslist(){
	if(loggedin==true){
		//console.log("loadoutputpinslist")
  $.ajax({url:"/CentralServer/OutputGauges",success : function(result)
     {$("#outputpinslist").html(result);}});}
   else $("#outputpinslist").html("");}

function togglepin(pin_no){
	if(loggedin==true)
	  $.ajax({url:"/CentralServer/ToggleOutputPin?pin_no="+pin_no,success : function(result)
		     {//loadoutputpinslist();
		     getoutputgauge(pin_no);
		     }
	  }
	  );
	}

function toggleinputpin(pin_no){
	if(loggedin==true)
	  $.ajax({url:"/CentralServer/ToggleInputPin?pin_no="+pin_no,success : function(result)
		     {sensorgauges();}});}

function loadinputpinslist(){
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
		//console.log(result)
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

function outputpinlog(pin){
	if(loggedin==false)
		return;
	$("#graphdiv").css("width","100%")
	$("#graphdiv").css("height","300px")
	$.ajax({url:"/CentralServer/OutputPinLog?pin="+pin,success : function(result)
	    {if(result=="error")
	    	return;
		//$("#graph").html(result)
		console.log(eval(result))
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
		loadoutputpinslist();
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

function loadconditionlist(pin){
	console.log("loadconditionlist "+pin)
	$.ajax({url:"/CentralServer/ConditionList?pin="+pin,success : function(result)
	    {//console.log(result);
	    $("#conditionlist").html(result)
	    }
	});
}

function loadconditionform(pin){
	$.ajax({url:"/CentralServer/ConditionForm?pin="+pin,success : function(result)
	    {//console.log(result);
	    $("#conditionform").html(result)
	    }
	});
}


/*function showconditionform(pin,sensor){
	data="<div class=\"row\">"
	data+="<div class=\"col\">"
	data+="Temperature <select id=\"temp_cond_op\" onchange=\"displaycondition_none()\">";
	data+="<option value=\"\">none</option>"
	data+="<option value=\"<\"><</option>"
	data+="<option value=\">\">></option>"
	data+="</select>"
	data+="<input type=\"text\" placeholder=\"Temperature value\" id=\"temp_cond_val\"/>";
	data+="</div>"
	
	data+="<div class=\"col\">"
	data+="Humidity <select id=\"hum_cond_op\" onchange=\"displaycondition_none()\">";
	data+="<option value=\"\">none</option>"
	data+="<option value=\"<\"><</option>"
	data+="<option value=\">\">></option>"
	data+="</select>"
	data+="<input type=\"text\" placeholder=\"Humidity value\" id=\"hum_cond_val\"/>";
	data+="</div>"
		
	data+="<div class=\"col\">PinNo <br/>"
	data+="<select id=\"cond_pin_out_no\">";
	for(var i=2;i<=26;i++)
	data+="<option value=\""+i+"\">"+i+"</option>"
	data+="</select>"
	data+="</div>"
		
	data+="<div class=\"col\">Value <br/>"
	data+="<select id=\"cond_pin_out_val\">";
	data+="<option value=\"0\">0</option>"
	data+="<option value=\"1\">1</option>"
	data+="</select>"
	data+="</div>"	
		
	data+="<div class=\"col\">"
	data+="<input type=\"button\" class=\"btn btn-success\" onclick=\"addcondition("+pin+",'"+sensor+"')\" value=\"AddCondition\"></button>"	
	data+="</div>"
			
	data+="</div>"
	$("#conditionform").html(data)
} */

function showcondition(pin){
	if(loggedin==false||pin==undefined)
		return;
	$("#graphdiv").css("width","")
	$("#graphdiv").css("height","")
	$("#graphdiv").html("")
	$("#graphdiv").html("<div class=\"row\"><div class=\"col\" id=\"conditionlist\"></div></div><hr/><div class=\"row\"><div class=\"col\" id=\"conditionform\"></div></div>")
	loadconditionlist(pin)
	loadconditionform(pin)
}

function displaycondition_none(){
	if($("#hum_cond_op").val()=="")
		$("#hum_cond_val").css("visibility","hidden")
	else $("#hum_cond_val").css("visibility","visible")

	if($("#temp_cond_op").val()=="")
		$("#temp_cond_val").css("visibility","hidden")
	else $("#temp_cond_val").css("visibility","visible")
}

function addcondition(pin,sensor){
	
	cond=""
	if(pin==undefined)
		return;
	console.log("addcondition"+pin+" "+sensor)
	
	if(sensor=="DHT11"||sensor=="DHT22"){
		op_hum=$("#hum_cond_op").val()
		op_temp=$("#temp_cond_op").val()
		val_hum=$("#hum_cond_val").val()
		val_temp=$("#temp_cond_val").val()
		
		if($("#hum_cond_op").val()==""&&$("#temp_cond_op").val()==""){
			alert("No condition added")
			return;}
	
		if(isNaN(val_hum)||isNaN(val_temp)){
			alert("Values have to be numbers")
			return;}
	
		if(($("#hum_cond_op").val()!=""&&val_hum.length==0)||($("#temp_cond_op").val()!=""&&val_temp.length==0)){
			alert("Enter values")
			return;}	
	
		if($("#hum_cond_op").val()==""){
			val_hum=""}
	
		if($("#temp_cond_op").val()==""){
			val_temp=""}
	
		cond=op_temp+""+val_temp+" "+op_hum+""+val_hum}
	
	else if(sensor=="PIR"){
		time_cond=$("#pir_cond_val").val()
		if(time_cond==""){
			alert("No condition added")
			return;}
		if(isNaN(time_cond)){
			alert("Values have to be numbers")
			return;}
		cond=time_cond
		}
	else {
		alert("Sensor not known")
		return;}
	
	console.log(cond)
	
	var retVal = confirm("Add condition ?");
    if( retVal == false ) {
       return;}
    
    url="/CentralServer/AddCondition"
    
    $.post(url,
			  {"pin_in": pin,
			   "pin_out":$("#cond_pin_out_no").val(),
			   "val": $("#cond_pin_out_val").val(),
			   "cond":cond,},
			  function(data, status){
				   if(data=="okay"){loadconditionlist(pin,sensor);}
				   
				   else {alert($("#cond_pin_out_no").val()+" is not an output pin")}
				 
			  });
	
}

function addconditionout(pin){
	time_start=$("#time_start_val").val()
	time_end=$("#time_end_val").val()
	val=$("#cond_pin_out_val").val()
	if(time_end<time_start){
		alert("Time start should be before time end")
		return;}
	url="/CentralServer/AddConditionOut"
	 $.post(url,
			  {"pin": pin,
			   "time_start":time_start,
			   "time_end": time_end,
			   "val":val,},
			  function(data, status){
				   if(data=="okay"){loadconditionlist(pin);}
				   
				   else {alert($("#cond_pin_out_no").val()+" is not an output pin")}
				 
			  });
}

function removecondition(cid,pin){
	 var retVal = confirm("Remove condition ? ");
     if( retVal == false ) {
        return;}
	$.ajax({url:"/CentralServer/RemoveCondition?cid="+cid+"&pin="+pin,success : function(result)
	    {if(result=="error")
	    	return;
	   loadconditionlist(pin)
	    }
	});
	
}

function init(){
	loggedin=false;
	logstatus();
	loadinputpinslist();
	loadoutputpinslist();
	boxaddpins_signup();}