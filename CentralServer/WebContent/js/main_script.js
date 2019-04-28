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
		  });}

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
	{loggedin=true;
	status+="<div class=\"mr-auto navbar-nav\"><div class=\"mr-auto navbar-brand\"> Hello "+result+"</div></div>";
	status+="<button onclick=\"logout()\" class=\"btn btn-primary mr-sm-2\">Logout</button>";}
	$("#logstatus").html(status);
	loadoutputpinslist();
	loadinputpinslist();
	sensorgauges();
    $("#loginform").submit(function( event ) { console.log("loginform");event.preventDefault();login();});
    },
    dataType: "text"});
}

function logout(){
	$.ajax({url:"http://localhost:8080/CentralServer/Logout",success : function(result)
	    {logstatus();
	    if(result=="okay"){
	    	loggedin=false;
	    	console.log("loggedout");}
	    }});
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

function init(){
	logstatus();
	sensorgauges();
	loadinputpinslist();
	loadoutputpinslist();
	//drawchart();
	inputpinlog(5);
	$('#test1').html('<canvas data-type="radial-gauge" data-width="200" data-height="150" data-value="20.2" data-min-value="-10" data-max-value="50" data-title="°C"></canvas>');
}