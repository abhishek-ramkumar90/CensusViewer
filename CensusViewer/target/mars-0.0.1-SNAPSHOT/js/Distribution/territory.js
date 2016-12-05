function territoryUpdateGetData(selectedRow) {
	document.getElementById('updatebuttonterritory').style.visibility='visible';
	//get the data.
	webMethod = CXFServiceURL+"TerritoryService/territoryDetails";
	var respObj = getAjax(webMethod);
	var selectedTerrBranchId;
	var selectedTerrBranchName;
	var selectedTerrDetails;
	var selectedTerrName;
	var selectedTerrZoneId;
	var selectedTerrZoneName;
	var selectedTerrRegionId;
	var selectedTerrRegionName;
	var selectedTerrId;
	
	$(respObj.TerritoryDetails.territoryJsonList).each(function(){
		
		if($.trim(selectedRow) == $.trim(this.territoyId)) {
			selectedTerrBranchId = this.branchId;
			selectedTerrDetails = this.stateDistVillTown;
			selectedTerrName = this.territoryName;
			selectedTerrId = this.territoyId;
			selectedTerrZoneId = this.zoneId;
			selectedTerrZoneName = this.zoneName;
			selectedTerrRegionId = this.regionId;
			selectedTerrRegionName = this.regionName;
			selectedTerrBranchName = this.branchName;
		}
	});
	
	webMethod1 = CXFServiceURL+"TerritoryService/villageListUpdate";
	var input="{'branchId':'"+selectedTerrBranchId+"','territoryId':'" + selectedTerrId + "'}";
	var obj1=postAjax(webMethod1,input);
	webMethod2 = CXFServiceURL+"TerritoryService/townListUpdate";
	var obj2=postAjax(webMethod2,input);
//	alert("1--" + JSON.stringify(obj2));
//	alert("2--" + JSON.stringify(obj1.VillageList.villageList));

	$("#accordionTerrVill").empty();
	$(obj1.VillageList.villageList).each(function() {
		var distCode = this.distId;
		var villId = this.villageId;
		var villName=this.villageName;
		var stateCode=this.stateCode;
	
		$("#accordionTerrVill").append('<li id="'+villId+'" value='+villName+'><a ><input id="v:'+stateCode+':'+distCode+':'+villId+'"  type="checkbox" />'+villName+'</a><li>');
	});
	
	clearSelect("zoneSelectionTerritory");
	clearSelect("regionSelectionTerritory");
	clearSelect("branchSelectionTerritory");
	
	$(function () {
		//$("#zoneSelectionTerritory").attr("disabled", true);
		$('#territoryName').val(selectedTerrName);
		$('#territoryId').val(selectedTerrId);
		$('#zoneSelectionTerritory').append("<option value='"+selectedTerrZoneId+"'>"+selectedTerrZoneName+"</option>");
		$('#regionSelectionTerritory').append("<option value='"+selectedTerrRegionId+"'>"+selectedTerrRegionName+"</option>");
		$('#branchSelectionTerritory').append("<option value='"+selectedTerrBranchId+"'>"+selectedTerrBranchName+"</option>");
		$("#zoneSelectionTerritory").attr("disabled", "disabled");
		$("#regionSelectionTerritory").attr("disabled", "disabled");
		$("#branchSelectionTerritory").attr("disabled", "disabled");
	});
	
	jQuery.each( selectedTerrDetails, function( i, val ) {
		//$( "#" + i ).append( document.createTextNode( " - " + val ) );
		//alert(i + "--" + val.level);
		if($.trim(val.level) == 'village') {
			var checkId = 'v'+':' + $.trim(val.statecode) + ':' + $.trim(val.distCode) + ':' + $.trim(val.villTownCode);
			//$('#'+checkId).attr('checked', true);
			document.getElementById(checkId).checked = true;
		}
	});

	
	$(obj2.TownList.townList).each(function() {
		var distCode = this.distId;
		var townId = this.townId;
		var townName=this.townName;
		var stateCode=this.stateCode;
		$("#accordionTerrVill").append('<li id="'+townId+'" value='+townName+'><a ><input id="t:'+stateCode+':'+distCode+':'+townId+'"  type="checkbox" />'+townName+'</a><li>');
	});
	
	jQuery.each( selectedTerrDetails, function( i, val ) {
	
		if($.trim(val.level) == 'TOWN') {
			var checkId = 't'+':' + $.trim(val.statecode) + ':' + $.trim(val.distCode) + ':' + $.trim(val.villTownCode);
			document.getElementById(checkId).checked = true;
		}
	});
}

function territoryUpdateSaveData() {
	
	//alert("ok terr update save data");
	//input="{'branchName':'ABC','zoneId':'ZN206','regId':'R218','regName':'ABCJKAHSDFKLH',stDist:'27:05,27:06'}";
	var territoryName=$("#territoryName").val();
	var territoryId = $("#territoryId").val();
//	var zoneId=$("#zoneSelectionTerritory").val();
//	var regId=$("#regionSelectionTerritory").val();
//	var branchId=$("#branchSelectionTerritory").val();
	var selected = new Array();
	$('#accordionTerrVill input:checked').each(function() {
	    selected.push($(this).attr('id'));
	});
//	alert("selected=="+selected);
	input="{'territoryId':'"+territoryId+"','territoryName':'"+territoryName+"','stDist':'"+selected+"'}";
	//alert("input=="+input);
	webMethod = CXFServiceURL+"TerritoryService/updateTerritory";
	postAjax(webMethod,input);
	$("#zoneSelectionTerritory").removeAttr("disabled");
	$("#regionSelectionTerritory").removeAttr("disabled");
	$("#branchSelectionTerritory").removeAttr("disabled");
	territoryReset();
	gridLoadTerritory();
	document.getElementById('okbuttonterritory').style.visibility='visible';
	document.getElementById('updatebuttonterritory').style.visibility='hidden';
	RefreshTerritory();
	DESTROY_DISTRICT_REGION();
	
	
}


function territoryDeleteChecked() {
	var s; 
	s = jQuery("#terrResult").jqGrid('getGridParam','selarrrow');
 	//alert("-h-" + s);
 	var jsonObj = new Array();
 	$(s).each(function() {
	   //alert(this);
	   jsonObj.push(this);
	});
 	
 	//alert(jsonObj.length);
 	input="{'territoryId':["+jsonObj+"]}";
 	if(jsonObj.length == 0){
 		alert("Please Select a Record");
 		return;
 	}
 	//alert("input--" + input);
 	webMethod = CXFServiceURL+"TerritoryService/deleteTerritory";
	postAjax(webMethod,input);
	jQuery("#terrResult").jqGrid('GridUnload');
	gridLoadTerritory();
	territoryReset();
	RefreshTerritory();
	DESTROY_DISTRICT_REGION();
 	
}

function selectedTerritoryBranch(regionSelectionTerritory,branchSelectionTerritory) {
	var regionCode=$("#"+regionSelectionTerritory).val();
	var input="{'regId':'"+regionCode+"'}";
	clearSelect(branchSelectionTerritory);
	var selectBox1 = document.getElementById(branchSelectionTerritory);
	createOptionElem("","Select",selectBox1);
	webMethod = CXFServiceURL+"TerritoryService/branch/list";
	var respObj = postAjax(webMethod, input);
	if((respObj.BranchList)!=""&&(respObj.BranchList.branchlist.length)>0) {
		for(var k=0; k<respObj.BranchList.branchlist.length; k++ ) {
			var branchId = $.trim(respObj.BranchList.branchlist[k].branchId);
			var branchName = $.trim(respObj.BranchList.branchlist[k].branchName);
			createOptionElem(branchId,branchName,selectBox1);
		}
	}else{
		var branchId = $.trim(respObj.BranchList.branchlist.branchId);
		var branchName = $.trim(respObj.BranchList.branchlist.branchName);
		createOptionElem(branchId,branchName,selectBox1);
	}
	return;
}

function territoryReset() {
	$(function () {
		$("#territoryName").val("");
		$("#territoryId").val("");
	});
	clearSelect("zoneSelectionTerritory");
	selectBranchZone("zoneSelectionTerritory");
	clearSelect("regionSelectionTerritory");
	clearSelect("branchSelectionTerritory");
	$("#zoneSelectionTerritory").attr("disabled", false);
	$("#regionSelectionTerritory").attr("disabled", false);
	$("#branchSelectionTerritory").attr("disabled", false);
	document.getElementById('updatebuttonterritory').style.visibility='hidden';
	document.getElementById('okbuttonterritory').style.visibility='visible';
	removeChildNodes("accordionTerrVill");
}
