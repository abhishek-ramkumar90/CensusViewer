


function zoneUpdateGetData(selectedRow) {
	document.getElementById('updatebuttonzone').style.visibility='visible';
	webMethod=CXFServiceURL+'ZoneRegionService/ZoneList';
	var obj2=getAjax(webMethod);
	var selectedZoneId;
	var selectedZoneName;
	var selectedZoneStateDetails;
	$(obj2.ZoneRegionList.zoneRegionList).each(function(){
		if($.trim(selectedRow) == $.trim(this.zoneId)) {
			selectedZoneId = this.zoneId;
			selectedZoneName = this.zoneName;
			selectedZoneStateDetails = this.stateDetails;
		}
	});
	$(function () {
		$('#ZoneTextBox').val(selectedZoneName);
		$('#zoneId').val(selectedZoneId);
		$("#ZoneLabel").hide();
		$("#ZoneFormList").hide();

	});
	$("#accordion-2").empty();
	webMethod = CXFServiceURL+"StateDetailService/MARS/stateDetails";
	var obj1=getAjax(webMethod);
	$( obj1.State.states ).each(function() {
		var stname=this.name;
		var st_code=this.statecode;
		$("#accordion-2").append('<li id="zone'+st_code+'" value='+stname+' ><a href="#"><input id="checkbox'+st_code+'"  type="checkbox" state_code='+st_code+' state_name='+stname+' />'+stname+'</a><li>');
	});
	$(selectedZoneStateDetails).each(function(){
		var checkId = 'checkbox' + this.stateId;
		document.getElementById(checkId).checked = true;
	});
}
function zoneUpdateSaveData() {
	var zoneName=$("#ZoneTextBox").val();
	if(zoneName=="")
		{
		alert("please enter a Zone Name to update the details");
		return;
		}
	var zoneId = $("#zoneId").val();
	var myarr=[];
	var checkBoxId;
	$("#accordion-2 :checked" )
	.each(function() {
		checkBoxId=this.id;
		var stateName=$("#"+checkBoxId).attr("state_name");
		var statename = {};
		statename["stateName"] =stateName;
		var marker=JSON.stringify(statename);
		myarr.push(marker);
		var stateCode=$("#"+checkBoxId).attr("state_code");
		var stateid = {};
		stateid["stateId"] = stateCode;
		var marker1=JSON.stringify(stateid);
		myarr.push(marker1);
	});
	if(checkBoxId==undefined)
	{
		alert("Zone Updation incomplete.Please select states");
		return;
	}
	webMethod=CXFServiceURL+'ZoneCreationService/ZoneUpdate/{"ZoneName":'+zoneName+',"ZoneId":'+zoneId+',"StateNames":['+myarr+']}"';
	var obj=getAjax(webMethod);
	webMethod=CXFServiceURL+'ZoneRegionService/ZoneList';
	var obj2=getAjax(webMethod);
	jsonObj =[];
	var ZoneId;
	var ZoneName;
	$(obj2.ZoneRegionList.zoneRegionList).each(function(){
		 ZoneId=this.zoneId;
		 ZoneName=this.zoneName;
		zoneNameArray.push(ZoneName);
		item = {};
		item ["id"] = ZoneId;
		item ["ZoneName"] = ZoneName;
		jsonObj.push(item);
	});
	var mydata1 =jsonObj; 
	ALL_GRID(mydata1);	
/*	$(function () {
		$('#ZoneTextBox').val("");
		$("#ZoneLabel").show();
		 $("#ZoneFormList").val('');
		 var zoneId=$("#ZoneFormList").val();
		 if(zoneId==""||zoneId==null)
		 {$("#accordion-2").empty();}
			$("#ZoneFormList").show();
	});*/
	RESET_ZONE();
	document.getElementById('updatebuttonzone').style.visibility='hidden';
	document.getElementById('Button1').style.visibility='visible';
	$("#zoneSelection").empty();
	regionZoneBoolean=true;
}

function zoneDeleteChecked() {
	jQuery("#list2").jqGrid('GridUnload');
	jQuery("#branchResult").jqGrid('GridUnload');
	jQuery("#terrResult").jqGrid('GridUnload');
	regionNameArray.length=0;
	var s; 
	s = jQuery("#list1").jqGrid('getGridParam','selarrrow');
if(s!="")
	{
	document.getElementById('Button1').style.visibility='visible';
 	var jsonObj = new Array();
 	$(s).each(function() {
	   jsonObj.push(this);
	});
 	input="{'zoneId':["+jsonObj+"]}";
 	webMethod1 = CXFServiceURL+"ZoneCreationService/deleteZone";
	postAjax(webMethod1,input);
	webMethod=CXFServiceURL+'ZoneRegionService/ZoneList';
	var obj2=getAjax(webMethod);
	jsonObj =[];
	$(obj2.ZoneRegionList.zoneRegionList).each(function(){
		var ZoneId=this.zoneId;
		var ZoneName=this.zoneName;
		zoneNameArray.push(ZoneName);
		item = {};
		item ["id"] = ZoneId;
		item ["ZoneName"] = ZoneName;
		jsonObj.push(item);
	});
	var mydata1 =jsonObj; 
	ALL_GRID(mydata1);	
	$("#zoneSelection").empty();
	regionZoneBoolean==true;
	}
}