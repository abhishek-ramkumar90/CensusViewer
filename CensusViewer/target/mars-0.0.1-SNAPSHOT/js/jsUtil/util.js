function getAjax(serviceUrl) {

	var  returnObj;
	$.ajax({  
		type : "GET",  
		url : serviceUrl,
		Accept : "application/json",
		contentType : "application/json; charset=utf-8",
		dataType : "json", 
		async: false,
		success : function(resp){ 
			var obj=JSON.stringify(resp);
			returnObj =  resp;
		}
	});
	return returnObj;
}

function postAjax(serviceUrl, inputData) {
	//alert("post ajax");
	var returnObj;
	$.ajax({  
		type : "POST",  
		url : serviceUrl,
		Accept : "application/json",
		contentType : "application/json; charset=utf-8",
		async: false,
		data : inputData,
		dataType : "json", 
		async: false,
		success : function(resp){  
			returnObj =  resp;
		}
	});
	//alert("returnObj==="+returnObj);
	return returnObj;
}
function postAjaxArray(serviceUrl, inputData) {
	//alert("post ajax");
	var returnObj;
	$.ajax({  
		type : "POST",  
		url : serviceUrl,
		Accept : "application/json",
		contentType : "application/json; charset=utf-8",
		async: false,
		 data: JSON.stringify({ paramName: inputData }),
		dataType : "json", 
		async: false,
		success : function(resp){  
			returnObj =  resp;
		}
	});
	//alert("returnObj==="+returnObj);
	return returnObj;
}

function postAjaxNoInput(serviceUrl) {
//	alert("post ajax");
	var returnObj;
	$.ajax({  
		type : "POST",  
		url : serviceUrl,
		Accept : "application/json",
		contentType : "application/json; charset=utf-8",
		async: false,
		dataType : "json", 
		async: false,
		success : function(resp){  
			returnObj =  resp;
		}
	});
	//alert("returnObj==="+returnObj);
	return returnObj;
}


function validateOnId(id)
{
    var regex =  /^[a-zA-Z0-9\s]{2,30}$/;
    var ctrl =  document.getElementById(id);
    if (regex.test(ctrl.value)) {
        return true;
    }
    else {
        return false;
    }
}

function validateOnValue(value)
{
    var regex = /^[a-zA-Z0-9\s]{2,30}$/;
    //var ctrl =  document.getElementById(id);
    if (regex.test(value)) {
        return true;
    }
    else {
        return false;
    }
}

function removeChildNodes(id) {
	
	var myNode = document.getElementById(id);
	if(myNode != null){
		while (myNode.firstChild) {
		    myNode.removeChild(myNode.firstChild);
		}	
	}
	
} 

