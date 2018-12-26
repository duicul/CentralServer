function loadoutputpinslist(){
  $.ajax({url:"/CentralServer/OutputPinsList",success : function(result)
     {$("#outputpinslist").html(result);}});  }

function togglepin(pin_no){
	  $.ajax({url:"/CentralServer/ToggleOutputPin?pin_no="+pin_no,success : function(result)
	     {window.alert("pinchanged "+pin_no); }});
	  loadoutputpinslist();
	  }
function loadinputpinslist(pin_no){
	$.ajax({url:"/CentralServer/InputPinsList",success : function(result)
	     {$("#inputpinslist").html(result);}}); }

function init(){
	loadoutputpinslist();
	setInterval(loadinputpinslist(), 1000);
}