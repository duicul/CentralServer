var loggedin=false;
var refreshtime=30000;

function drawchart(){
	var training_steps=[];
	training_steps.push({x:1,y:3});
	training_steps.push({x:100,y:30});
	var training_steps1=[];
	training_steps1.push({x:10,y:30});
	training_steps1.push({x:70,y:50});
	var chart = new CanvasJS.Chart("trainingSteps", {
		animationEnabled: true,
		theme: "light2",
		title:{
			text: "ErrorOnSteps"
		},
		axisY:{
			includeZero: true
		},
		data: [{        
			type: "line",       
			dataPoints: training_steps,
			name : "Gogu",
			showInLegend : true
		},{        
			type: "line",       
			dataPoints: training_steps1
		}]
	});
	chart.render();
}

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
	$.ajax({url:"http://localhost:8080/CentralServer/AddInputPin?pin="+pin+"&name="+name+"&sensor="+sensor,success : function(result)
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
		$.ajax({url:"http://localhost:8080/CentralServer/AddOutputPin?pin="+pin+"&name="+name,success : function(result)
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
	$.ajax({url:"http://localhost:8080/CentralServer/RemoveInputPin?pin="+pin_no,success : function(result)
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
	$.ajax({url:"http://localhost:8080/CentralServer/RemoveOutputPin?pin="+pin_no,success : function(result)
	    {if(result=="error")
	    	return;
        if(result=="okay"){
        	alert("Pinoutput "+pin_no +" removed")
        	loadoutputpinslist()
        }
	}});
}

function loadoutputpinslist(){
  $.ajax({url:"http://localhost:8080/CentralServer/OutputPinsList",success : function(result)
     {$("#outputpinslist").html(result);}});  }

function togglepin(pin_no){
	  $.ajax({url:"http://localhost:8080/CentralServer/ToggleOutputPin?pin_no="+pin_no,success : function(result)
		     {loadoutputpinslist();}});}
function loadinputpinslist(pin_no){
	$.ajax({url:"http://localhost:8080/CentralServer/InputPinsList",success : function(result)
	     {$("#inputpinslist").html(result);}}); 
	if(loggedin==true)
		setTimeout(loadinputpinslist,refreshtime);}

function login(user,password)
{var formData = new FormData();
var xmlhttp = new XMLHttpRequest();
var url = "http://localhost:8080/CentralServer/Login";
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
			  //location.reload();
		  });
boxaddpins();}

function logstatus()
{ $.ajax({url:"http://localhost:8080/CentralServer/LogStatus",success : function(result)
    {status=""
	if(result=="error"){
    status="<script>loggedin=false</script><form class=\"form-inline\" id=\"loginform\">";
	status+="<div class=\"input-group\">";
	status+="<input type=\"text\" class=\"form-control\" placeholder=\"UserName\" id=\"user_txt\" width=\"30\" />"/*+"</div>"*/;
	status+="<input type=\"password\" class=\"form-control\" placeholder=\"Password\" id=\"pass_txt\" width=\"30\" />"/*+"</div>"*/;
	status+="<input type=\"submit\" class=\"btn btn-primary\" value=\"Login\" />"/*+"</div>"*/;
	status+="</div></form>";
	loggedin=false;
	}
    else
	{loggedin=true;boxaddpins();
	status+="<div class=\"mr-auto navbar-nav\"><div class=\"mr-auto navbar-brand\"> Hello "+result+"</div></div>";
	status+="<button onclick=\"logout()\" class=\"btn btn-primary mr-sm-2\">Logout</button>";}
	$("#logstatus").html(status);
	loadoutputpinslist();
	loadinputpinslist();
	sensorgauges();
    $("#loginform").submit(function( event ) { console.log("loginform");event.preventDefault();login();});
    },
    dataType: "text"});
boxaddpins();
}

function logout(){
	$.ajax({url:"http://localhost:8080/CentralServer/Logout",success : function(result)
	    {logstatus();
	    if(result=="okay"){
	    	loggedin=false;
	    	console.log("loggedout");}
	    }});
	boxaddpins();
}

function inputpinlog(pin){
	/*if(loggedin==false)
		return;*/
	$.ajax({url:"http://localhost:8080/CentralServer/InputPinLog?pin="+pin,success : function(result)
	    {if(result=="error")
	    	return;
		//$("#graph").html(result)
		//console.log(eval(result))
		var chart = new CanvasJS.Chart("graphdiv", {
			animationEnabled: true,
			theme: "light2",
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

function sensorgauges()
{$.ajax({url:"http://localhost:8080/CentralServer/SensorGauges",success : function(result)
    {$("#sensorgauges").html(result);},
    dataType: "text"
	}
	);
	if(loggedin==true)
		setTimeout(sensorgauges,refreshtime);
}

function boxaddpins(){
	var data_pins="";
	if(loggedin==true){
	data_pins+="<div class=\"row\">"
	data_pins+="<div class=\"col\">"
	data_pins+="<input type=\"text\" placeholder=\"Name\" id=\"inp_pin_name\"/>";
	data_pins+="<select id=\"inp_pin_no\">";
	for(var i=2;i<=26;i++)
		 data_pins+="<option value=\""+i+"\">"+i+"</option>"
	data_pins+="</select>"
	sensors=["DHT11","DHT22","PIR"]
	data_pins+="<select id=\"inp_pin_sensor\">"
	for(i in sensors)
			 data_pins+="<option value=\""+sensors[i]+"\">"+sensors[i]+"</option>"
	data_pins+="</select>"
	data_pins+="<input type=\"button\" onclick=\"addinputpin()\" value=\"Addinputpin\"></button>"
	data_pins+="</div></div>"
		
	data_pins+="<div class=\"row\">"
	data_pins+="<div class=\"col\">"
	data_pins+="<input type=\"text\" placeholder=\"Name\" id=\"out_pin_name\"/>";
	data_pins+="<select id=\"out_pin_no\">";
	for(var i=2;i<=26;i++)
		 data_pins+="<option value=\""+i+"\">"+i+"</option>"
	data_pins+="</select>"
	data_pins+="<input type=\"button\" onclick=\"addoutputpin()\" value=\"Addoutputpin\"></button>"	
	data_pins+="</div></div>"
	}
	$("#boxaddpins").html(data_pins);
}
function init(){
	logstatus();
	sensorgauges();
	loadinputpinslist();
	loadoutputpinslist();
	//drawchart();
	boxaddpins();
	//inputpinlog(5);
	//$('#test1').html('<canvas data-type="radial-gauge" data-width="200" data-height="150" data-value="20.2" data-min-value="-10" data-max-value="50" data-title="°C"></canvas>');
}