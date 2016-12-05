

var selectedTypeVal;
var rootId;


function saveIntermediary() {
	ShowTree();
	var intermediaryName = $("#intermediaryName").val();
	if($.trim(intermediaryName) == "") {
		alert("Please enter Intermediary name.")
		return;
	}
	var typeVal = $("#typeList").val();
	if($.trim(typeVal) == "new") {
		typeVal = $("#newInterm").val();
		if($.trim(typeVal) == "") {
			alert("Please enter New Intermediary.");
			return;
		}
	} else {
		if($.trim(typeVal) == "select") {
			alert("Please select a Intermediary.");
			return;
		}
	}

	var intermZoneList = $("#intermZoneList").val();
	if($.trim(intermZoneList) == "select") {
		alert("Please select a Zone.");
		return;
	}

	var intermRegionList = $("#intermRegionList").val();
	if($.trim(intermRegionList) == "select") {
		alert("Please select a Region.");
		return;
	}

	var intermBranchList = $("#intermBranchList").val();
	if($.trim(intermBranchList) == "select") {
		alert("Please select a Branch.");
		return;
	}

	var intermTerritoryList = $("#intermTerritoryList").val();
	if($.trim(intermTerritoryList) == "select") {
		alert("Please select a Territory.");
		return;
	}

	var villTownList = $("#villTownList").val();
	if($.trim(villTownList) == "select") {
		alert("Please select a Village/Town.");
		return;
	}

	var intermAddress = $("#intermAddress").val();
	/*if($.trim(intermAddress) == "select") {
		alert("Please select a Village/Town.");
		return;
	}*/

	var parentTypeList = $("#parentTypeList").val();
	var childTypeList = $("#childTypeList").val();
	var root =  document.getElementById("root").checked;

	var parentTypeListDetails = $("#parentTypeListDetails").val();

	if($("#currentRoot").attr("rootid") != undefined || $("#currentRoot").attr("rootid") == "") {
		rootId = $("#currentRoot").attr("rootid");
	} else {
		rootId = "";
	}
	var childId= $("#childTypeListDetails").val();

	alert("ChildIds---" + childId);

	var input = "{'intermName':'"+ intermediaryName +"','intermType':'"+typeVal+"','zoneId':'"+intermZoneList+"','regId':'"+intermRegionList+"','branchId':'"+intermBranchList+"','terrId':'"+intermTerritoryList+"','villTownId':'"+villTownList+"','address':'"+intermAddress+"','childId':'"+childId+"','root':'"+root+"','rootId':'"+rootId+"','parentId':'"+ parentTypeListDetails +"'}";

	webMethod = CXFServiceURL+"IntermService/saveIntermediary";
	var respObj = postAjax(webMethod,input);
	alert("respObj - " + JSON.stringify(respObj));

}

function ShowTree()
{
	webMethod=CXFServiceURL+"/CRUDIntermediaryService/getIntermediaryDetails";
	var input="{'IntermId':'100'}";
	var respObj = postAjax(webMethod,input);
	if((JSON.stringify(respObj.IntermediaryDetails.intermediaryCollection))==undefined)
	{
		$('.tree').append('<tbody><tr class="treegrid-1"><td>Producer</td><td>Additional info</td><td>Additional info</td><td>Additional info</td></tr></tbody>');
		$('.tree').treegrid();
	}
	else
	{
		$('.tree').append('<tr class="treegrid-1"><td>'+respObj.IntermediaryDetails.type+'</td><td>'+respObj.IntermediaryDetails.name+'</td><td>'+respObj.IntermediaryDetails.status+'</td></tr>');
		if(JSON.stringify(respObj.IntermediaryDetails.intermediaryCollection)==undefined)
		{
			$('.tree').treegrid();
			return;
		}
		else{
			var i=1;
			fillgrid(respObj.IntermediaryDetails.intermediaryCollection,i);
			$('.tree').treegrid();	
		}
	}
}

function fillgrid( respObj,i)
{
	var j=i;
	if((respObj.length)==undefined) {
		if(respObj.intermediaryCollection==undefined) {	
			$('.tree').append('<tr class="treegrid-'+(++i)+' treegrid-parent-'+j+'"><td>'+respObj.type+'</td><td>'+respObj.name+'</td><td>'+respObj.status+'</td></tr>');
		} else {
			$('.tree').append('<tr class="treegrid-'+(++i)+' treegrid-parent-'+j+'"><td>'+respObj.type+'</td><td>'+respObj.name+'</td><td>'+respObj.status+'</td></tr>');
			fillgrid(respObj.intermediaryCollection,i);

		}
	}
	else{
		$.each(respObj,function(){
			if(this.intermediaryCollection==undefined) {
				$('.tree').append('<tr class="treegrid-'+(++i)+' treegrid-parent-'+j+'"><td>'+this.type+'</td><td>'+this.name+'</td><td>'+this.status+'</td></tr>');
			} else {
				$('.tree').append('<tr class="treegrid-'+(++i)+' treegrid-parent-'+j+'"><td>'+this.type+'</td><td>'+this.name+'</td><td>'+this.status+'</td></tr>');
				fillgrid((this.intermediaryCollection),i);//a

			}

		});
	}
}


/*function fillgrid( respObj,i)
{
	var j=i;
	alert(i);
	var length;
	if(respObj.length==undefined)
		{
		length=1;
		}
	else
	{
		length=respObj.length;
	}
		for(var f=0;f<length;f++){
			if(length==1){
				var jsonVal=respObj;}
			else{
			var jsonVal = respObj[f];}
			alert("json - " + jsonVal + "---------" + JSON.stringify(jsonVal)  +"-----"+jsonVal.intermId);	
			if(jsonVal.intermediaryCollection==undefined) {
				$('.tree').append('<tr class="treegrid-'+(++i)+' treegrid-parent-'+j+'"><td>'+jsonVal.type+'</td><td>'+jsonVal.name+'</td><td>'+jsonVal.status+'</td></tr>');
			} else {
				$('.tree').append('<tr class="treegrid-'+(++i)+' treegrid-parent-'+j+'"><td>'+jsonVal.type+'</td><td>'+jsonVal.name+'</td><td>'+jsonVal.status+'</td></tr>');
				fillgrid((jsonVal.intermediaryCollection),i);

			}
			++i;
		}
}*/




function typeList() {
	webMethod = CXFServiceURL+"IntermService/interm/types";
	clearSelect("typeList");
	var selectBox = document.getElementById("typeList");
	createOptionElem("select","Select",selectBox);
	var respObj = getAjax(webMethod);
	$('#currentRoot').val(respObj.Intermediary.rootType);
	$('#currentRoot').attr("rootId",respObj.Intermediary.rootId);
	if((respObj.Intermediary.intermType)!="" && (respObj.Intermediary.intermType.entry.length)>1) {
		for(var k=0; k<respObj.Intermediary.intermType.entry.length; k++ ) {
			if($.trim(respObj.Intermediary.rootType) != $.trim(respObj.Intermediary.intermType.entry[k].value)) {
				var intermType = $.trim(respObj.Intermediary.intermType.entry[k].value);
				createOptionElem(intermType,intermType,selectBox);
			}
		}

	}else{
		if((respObj.Intermediary.intermType)!="" && $.trim(respObj.Intermediary.rootType) != $.trim(respObj.Intermediary.intermType.entry.value)) {
			var intermType = $.trim(respObj.Intermediary.intermType.entry.value);
			createOptionElem(intermType,intermType,selectBox);
		}
	}
	createOptionElem("new","New",selectBox);
	if((respObj.Intermediary.intermType) == "") {
		document.getElementById("root").checked = true;
	}
	checkParentChild();
	selectBranchZone("intermZoneList");
}

function isNew(selectedOption) {
	var typeVal = $("#"+selectedOption).val();
	document.getElementById("parent").checked = false;
	document.getElementById("child").checked = false;
	document.getElementById("root").checked = false;
	if($.trim(typeVal) == "new") {
		document.getElementById("newIntermDiv").style.display = "block";
		if($("#currentRoot").attr("rootid") == undefined || $("#currentRoot").attr("rootid") == "") {
			document.getElementById("root").checked = true;
		} else {
			document.getElementById("root").checked = false;
		}
	} else {
		document.getElementById("newIntermDiv").style.display = "none";
		selectedTypeVal = typeVal;
		clearSelect("typeList");
		var selectBox = document.getElementById("typeList");
		createOptionElem("select","Select",selectBox);
		var respObj = getAjax(webMethod);
		if((respObj.Intermediary)!="" && (respObj.Intermediary.intermType.length)>1) {
			for(var k=0; k<respObj.Intermediary.intermType.length; k++ ) {
				var intermType = $.trim(respObj.Intermediary.intermType[k]);
				createOptionElem(intermType,intermType,selectBox);
			}
		}else{
			var intermType = $.trim(respObj.Intermediary.intermType);
			createOptionElem(intermType,intermType,selectBox);
		}
		alert(JSON.stringify(respObj));
		createOptionElem("new","New",selectBox);
	}
}

function checkParentChild() {
	if(document.getElementById("root").checked == true) {
		document.getElementById("parentdiv").style.display = "none";
		document.getElementById("childdiv").style.display = "none";
	} else {
		document.getElementById("parentdiv").style.display = "block";
		document.getElementById("childdiv").style.display = "block";
	}
}

function checkParent() {
	if(document.getElementById("parent").checked == true) {
		webMethod = CXFServiceURL+"IntermService/interm/types";
		clearSelect("parentTypeList");
		var selectBox = document.getElementById("parentTypeList");
		createOptionElem("select","Select",selectBox);
		var respObj = getAjax(webMethod);
		if((respObj.Intermediary)!="" && (respObj.Intermediary.intermType.entry.length)>1) {
			for(var k=0; k<respObj.Intermediary.intermType.entry.length; k++ ) {
				if($.trim(selectedTypeVal) != $.trim(respObj.Intermediary.intermType.entry[k].value)) {
					alert($.trim(selectedTypeVal) != $.trim(respObj.Intermediary.intermType.entry[k].value) + "-----------------compare");
					var intermType = $.trim(respObj.Intermediary.intermType.entry[k].value);
					createOptionElem(intermType,intermType,selectBox);
				}
			}

		}else{
			if($.trim(selectedTypeVal) != $.trim(respObj.Intermediary.intermType.entry.value) ) {
				var intermType = $.trim(respObj.Intermediary.intermType.entry.value);
				createOptionElem(intermType,intermType,selectBox);
			}
		}
		if(respObj.Intermediary == "") {
		}
	}
}

function checkChild() {
	if(document.getElementById("child").checked == true) {
		webMethod = CXFServiceURL+"IntermService/interm/types";
		clearSelect("childTypeList");
		var selectBox = document.getElementById("childTypeList");
		createOptionElem("select","Select",selectBox);
		var respObj = getAjax(webMethod);
		if(respObj.Intermediary == "") {
		}
	}
}

function selectedIntermediaryTerritory(intermBranchList,intermTerritoryList) {

	webMethod = CXFServiceURL+"TerritoryService/branch/terrlist";
	var branchCode=$("#"+intermBranchList).val();
	var input="{'branchId':'"+branchCode+"'}";
	var respObj = postAjax(webMethod,input);
	clearSelect(intermTerritoryList);
	var selectBox1 = document.getElementById(intermTerritoryList);
	createOptionElem("","Select",selectBox1);
	if((respObj.TerritoryDetails)!="" && (respObj.TerritoryDetails.terrList.length)>1) {
		for(var k=0; k<respObj.TerritoryDetails.terrList.length; k++ ) {
			var terrId = $.trim(respObj.TerritoryDetails.terrList[k].terrId);
			var terrName = $.trim(respObj.TerritoryDetails.terrList[k].terrName);
			createOptionElem(terrId,terrName,selectBox1);
		}
	}else{
		var terrId = $.trim(respObj.TerritoryDetails.terrList.terrId);
		var terrName = $.trim(respObj.TerritoryDetails.terrList.terrName);
		createOptionElem(terrId,terrName,selectBox1);
	}
	return;
}

function selectedTerritoryVillTown(intermTerritoryList,villTownList) {
	webMethod = CXFServiceURL+"TerritoryService/territory/villtown";
	var terrCode=$("#"+intermTerritoryList).val();
	var input="{'territoryId':'"+terrCode+"'}";
	var respObj = postAjax(webMethod,input);
	clearSelect(villTownList);
	var selectBox1 = document.getElementById(villTownList);
	createOptionElem("","Select",selectBox1);
	if((respObj.TerrVillTownDetails)!="" && (respObj.TerrVillTownDetails.terVillTown.length)>1) {
		for(var k=0; k<respObj.TerrVillTownDetails.terVillTown.length; k++ ) {
			var villTownCode = $.trim(respObj.TerrVillTownDetails.terVillTown[k].villTownCode);
			var villTownName = $.trim(respObj.TerrVillTownDetails.terVillTown[k].villTownName);
			createOptionElem(villTownCode,villTownName,selectBox1);
		}
	}else{
		var villTownCode = $.trim(respObj.TerrVillTownDetails.terVillTown.villTownCode);
		var villTownName = $.trim(respObj.TerrVillTownDetails.terVillTown.villTownName);
		createOptionElem(villTownCode,villTownName,selectBox1);
	}
	return;
}

function getSelectedParentDetails(parentTypeList,parentTypeListDetails) {
	document.getElementById("parentTypeListDetailsDiv").style.display = "block";
	webMethod = CXFServiceURL+"IntermService/getIntermediaryName";
	var parentVal=$("#"+parentTypeList).val();
	var input="{'intmType':'"+parentVal+"'}";
	var respObj = postAjax(webMethod,input);
	clearSelect(parentTypeListDetails);
	var selectBox1 = document.getElementById(parentTypeListDetails);
	createOptionElem("","Select",selectBox1);
	if((respObj.IntmNameList)!="" && (respObj.IntmNameList.intmList.length)>1) {
		for(var k=0; k<respObj.IntmNameList.intmList.length; k++ ) {
			var intmId = $.trim(respObj.IntmNameList.intmList[k].intmId);
			var intmName = $.trim(respObj.IntmNameList.intmList[k].intmName);
			createOptionElem(intmId,intmName,selectBox1);
		}
	}else{
		var intmId = $.trim(respObj.IntmNameList.intmList.intmId);
		var intmName = $.trim(respObj.IntmNameList.intmList.intmName);
		createOptionElem(intmId,intmName,selectBox1);
	}
	return;
}


function getSelectedChild(parentTypeListDetails,childTypeList) {
	webMethod = CXFServiceURL+"IntermService/getIntermediaryChild";
	var parentVal=$("#"+parentTypeListDetails).val();
	var input="{'intmId':'"+parentVal+"'}";
	var respObj = postAjax(webMethod,input);
	clearSelect(childTypeList);
	var selectBox1 = document.getElementById(childTypeList);
	createOptionElem("","Select",selectBox1);
	var names = [];
	var uniqueNames = [];
	if((respObj.IntmNameList)!="" && (respObj.IntmNameList.intmList.length)>1) {
		for(var k=0; k<respObj.IntmNameList.intmList.length; k++ ) {
			var intmType = $.trim(respObj.IntmNameList.intmList[k].intermType);
			names.push(intmType);
		}
	}else{
		var intmType = $.trim(respObj.IntmNameList.intmList.intermType);
		names.push(intmType);
	}
	$.each(names, function(i, el){
		if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	for(var k1=0; k1< uniqueNames.length; k1++) {
		createOptionElem(uniqueNames[k1],uniqueNames[k1],selectBox1);
	}
	return;
}


function getSelectedChildDetails(childTypeList,childTypeListDetails) {
	document.getElementById("childTypeListDetailsDiv").style.display = "block";
	webMethod = CXFServiceURL+"IntermService/getIntermediaryChild";
	var parentVal=$("#parentTypeListDetails").val();
	var childTypeListVal = $("#childTypeList").val();
	var input="{'intmId':'"+parentVal+"'}";
	var respObj = postAjax(webMethod,input);
	clearSelect(childTypeListDetails);
	var selectBox1 = document.getElementById(childTypeListDetails);
	createOptionElem("","Select",selectBox1);
	if((respObj.IntmNameList)!="" && (respObj.IntmNameList.intmList.length)>1) {
		for(var k=0; k<respObj.IntmNameList.intmList.length; k++ ) {
			if($.trim(childTypeListVal) == $.trim(respObj.IntmNameList.intmList[k].intermType)) {
				var intmId = $.trim(respObj.IntmNameList.intmList[k].intmId);
				var intmName = $.trim(respObj.IntmNameList.intmList[k].intmName);
				createOptionElem(intmId,intmName,selectBox1);
			}
		}
	}else{
		if($.trim(childTypeListVal) == $.trim(respObj.IntmNameList.intmList.intermType)) {
			var intmId = $.trim(respObj.IntmNameList.intmList.intmId);
			var intmName = $.trim(respObj.IntmNameList.intmList.intmName);
			createOptionElem(intmId,intmName,selectBox1);
		}
	}
	return;
}

function intermediaryReset() {
	$('#intermediaryName').val("");
	typeList();
	$('#newInterm').val("");
	document.getElementById("newIntermDiv").style.display = "none";
	clearSelect("intermRegionList");
	clearSelect("intermBranchList");
	clearSelect("intermTerritoryList");
	clearSelect("villTownList");
	$('#intermAddress').val("");
	document.getElementById("parent").checked = false;
	document.getElementById("child").checked = false;
	document.getElementById("root").checked = false;
	clearSelect("parentTypeList");
	clearSelect("parentTypeListDetails");
	document.getElementById("parentTypeListDetailsDiv").style.display = "none";
	clearSelect("childTypeList");
}