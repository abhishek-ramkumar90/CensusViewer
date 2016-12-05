function branchUpdateGetData(selectedRow) {
	document.getElementById('updatebuttonbranch').style.visibility='visible';
	webMethod = CXFServiceURL+"BranchService/branchDetails";
	var respObj = getAjax(webMethod);
	var selectedBranchId;
	var selectedBranchName;
	var selectedBranchZoneId;
	var selectedBranchZoneName;
	var selectedBranchRegionId;
	var selectedBranchRegionName;
	var selectedBranchBrDistricts;
	$(respObj.BranchDetails.branchJsonList).each(function(){
		if($.trim(selectedRow) == $.trim(this.branchId)) {
			selectedBranchId = this.branchId;
			selectedBranchZoneId = this.zoneId;
			selectedBranchZoneName = this.zoneName;
			selectedBranchRegionId = this.regionId;
			selectedBranchRegionName = this.regionName;
			selectedBranchName = this.branchName;
			selectedBranchBrDistricts = this.brDistricts;
			//alert("found one" + this.branchId + "--"  + this.zoneId + "--" +this.regionId +"--"  + JSON.stringify(this.brDistricts));
		}
	});
	
	
	webMethod1 = CXFServiceURL+"BranchService/districtlistupdate";
	var regCode=$("#regionSelectionBranch").val();
	var input="{'regionId':'"+selectedBranchRegionId+"', 'branchId':'" + selectedBranchId + "'}";
	var obj1=postAjax(webMethod1,input);
	
	//alert(selectedBranchId + "--" +selectedBranchZoneId + "--" +selectedBranchZoneName + "--" + JSON.stringify(selectedBranchBrDistricts));
	
	$("#accordionBranchDist").empty();

	$( obj1.DistrictList.dists ).each(function() {
		var distCode = this.distCode;
		var distName = this.distName;
		var	stateCode=this.stateCode;
		$("#accordionBranchDist").append('<li id="zone'+distCode+'" value='+distName+'><a ><input id="'+stateCode+':'+distCode+'" type="checkbox" />'+distName+'</a><li>');
	});

	clearSelect("zoneSelectionBranch");
	clearSelect("regionSelectionBranch");

	
	$(function () {
		$('#branchName').val(selectedBranchName);
		$('#branchId').val(selectedBranchId);
		$('#zoneSelectionBranch').append("<option value='"+selectedBranchZoneId+"'>"+selectedBranchZoneName+"</option>");
		$('#regionSelectionBranch').append("<option value='"+selectedBranchRegionId+"'>"+selectedBranchRegionName+"</option>");
		$("#zoneSelectionBranch").attr("disabled", "disabled");
		$("#regionSelectionBranch").attr("disabled", "disabled");
		
	});

	
	
	if((selectedBranchBrDistricts)!="" && (selectedBranchBrDistricts.length)>1){
		$.each(selectedBranchBrDistricts,function() {
			var checkId = $.trim(this.stateCode) + ':' + $.trim(this.distCode);
			
			document.getElementById(checkId).checked = true;
		});
	}
	else if(selectedBranchBrDistricts!="")
	{
		var checkId = $.trim(selectedBranchBrDistricts.stateCode) + ':' + $.trim(selectedBranchBrDistricts.distCode);
	
		document.getElementById(checkId).checked = true;
	}
}

function branchUpdateSaveData() {
	
	//alert("ok branch update save data");
	var branchName=$("#branchName").val();
	var branchId = $("#branchId").val();

	var selected = new Array();
	$('#accordionBranchDist input:checked').each(function() {
	    selected.push($(this).attr('id'));
	});

	input="{'branchName':'"+branchName+"','branchId':'"+branchId+"','stDist':'"+selected+"'}";
//	alert("input=="+input);
	webMethod = CXFServiceURL+"BranchService/updateBranch";
	postAjax(webMethod,input);
	gridLoadBranch();
	branchReset();
	$("#zoneSelectionBranch").removeAttr("disabled");
	$("#regionSelectionBranch").removeAttr("disabled");
	document.getElementById('okBtnBranchForm').style.visibility='visible';
	RefreshBranch();
	DESTROY_DISTRICT_REGION();
	destroyHighlight();
	
}

function branchDeleteChecked() {
	var s; 
	
	s = jQuery("#branchResult").jqGrid('getGridParam','selarrrow');
 //	alert("-h-" + s);
 	var jsonObj = new Array();
 	$(s).each(function() {
	   //alert(this);
	   jsonObj.push(this);
	});
 	if(jsonObj.length == 0){
 		alert("Please Select a Record");
 		return;
 	}
 	//alert(jsonObj.length);
 	input="{'branchId':["+jsonObj+"]}";
 	//alert("input---" + input);
 	webMethod = CXFServiceURL+"BranchService/deleteBranch";
	postAjax(webMethod,input);
	 jQuery("#branchResult").jqGrid('GridUnload');
	 gridLoadBranch();
	 branchReset();
	 document.getElementById('okBtnBranchForm').style.visibility='visible';
	 RefreshBranch();
	 DESTROY_DISTRICT_REGION();
	 checkedDistBranch.length = 0;
	 destroyHighlight();
}

function branchReset() {
	$(function () {
		$("#branchName").val("");
		$("#branchId").val("");
	});
	
	clearSelect("zoneSelectionBranch");
	selectBranchZone("zoneSelectionBranch");
	
	clearSelect("regionSelectionBranch");
	removeChildNodes("accordionBranchDist");
	$("#zoneSelectionBranch").removeAttr("disabled");
	$("#regionSelectionBranch").removeAttr("disabled");
	RefreshBranch();
	DESTROY_DISTRICT_REGION();
	destroyHighlight();
	checkedDistBranch.length = 0;
}

function selectBranchZone(zoneSelectionBranch) {
	
	//alert("zoneSelectionBranch");
	clearSelect(zoneSelectionBranch);
	var selectBox = document.getElementById(zoneSelectionBranch);
	createOptionElem("","Select",selectBox);
	
	webMethod = CXFServiceURL+"ZoneRegionService/ZoneList ";
	var obj1=getAjax(webMethod);
	
	if( (obj1.ZoneRegionList)!="" && (obj1.ZoneRegionList.zoneRegionList.length)>1 ) {
	
		for(var k=0; k<obj1.ZoneRegionList.zoneRegionList.length; k++ ) {
			var zoneId = $.trim(obj1.ZoneRegionList.zoneRegionList[k].zoneId);
			var zoneName = $.trim(obj1.ZoneRegionList.zoneRegionList[k].zoneName);
		
			createOptionElem(zoneId,zoneName,selectBox);
		}
	}else{
		var zoneId = $.trim(obj1.ZoneRegionList.zoneRegionList.zoneId);
		var zoneName = $.trim(obj1.ZoneRegionList.zoneRegionList.zoneName);
	
		createOptionElem(zoneId,zoneName,selectBox);
	}
	
	return;
}

function selectBranchRegion(zoneSelectionBranch,regionSelectionBranch) {
	
	var zoneCode=$("#"+zoneSelectionBranch).val();
	clearSelect(regionSelectionBranch);
	var selectBox1 = document.getElementById(regionSelectionBranch);
	createOptionElem("","Select",selectBox1);
	
	var input="{'zoneDetails':'"+zoneCode+"'}";
	webMethod = CXFServiceURL+"BranchService/regions/list";
	var respObj = postAjax(webMethod, input);
	
	if((respObj.RegionListBr)!=""&&(respObj.RegionListBr.regionlist.length)>1) {
		for(var k=0; k<respObj.RegionListBr.regionlist.length; k++ ) {
			var regionId = $.trim(respObj.RegionListBr.regionlist[k].regionId);
			var regionName = $.trim(respObj.RegionListBr.regionlist[k].regionName);
			//alert(k + "--" + regionId + "--" + regionName);
			createOptionElem(regionId,regionName,selectBox1);
		}
	}else{
		var regionId = $.trim(respObj.RegionListBr.regionlist.regionId);
		var regionName = $.trim(respObj.RegionListBr.regionlist.regionName);
		//alert(k + "--" + regionId + "--" + regionName);
		createOptionElem(regionId,regionName,selectBox1);
	}
	return;
}

function createOptionElem(zoneId, zoneName, select) {
	option = document.createElement("option");
	option.setAttribute("value", zoneId);
	option.innerHTML = zoneName;
	select.appendChild(option);
}

function removeChildNodes(selectedId) {
	var myNode = document.getElementById(selectedId);
	while (myNode.firstChild) {
	    myNode.removeChild(myNode.firstChild);
	}
}