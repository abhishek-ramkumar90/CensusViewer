
	var selectedRegionName;
function regionUpdateGetData(selectedRow) {
	webMethod=CXFServiceURL+"RegionCreationService/RegionGridList";
	var regObj=getAjax(webMethod);
	var selectedRegionId;
	var selectedRegionZoneId;
	var selectedRegionZoneName;
	var selectedRegionDistricts;
	$(regObj.RegionList.regions).each(function(){
		if($.trim(selectedRow) == $.trim(this.regionId)) {
			selectedRegionZoneId = this.zoneId;
			selectedRegionZoneName = this.zoneName;
			selectedRegionId = this.regionId;
			selectedRegionName = this.region;
			selectedRegionDistricts = this.districts;
		}
	});
	$(function () {
		$('#regionName').val(selectedRegionName);
		$('#regionId').val(selectedRegionId);
		$("#zoneSelection").val(selectedRegionZoneId);
		$("#zoneSelection").attr("disabled", "disabled");
	});
	webMethod = CXFServiceURL+'RegionStateService/RegionStateList/'+selectedRegionZoneId+'';
	var obj1=getAjax(webMethod);
	if((obj1.RegionStateList)!=""&&(obj1.RegionStateList.stateDetails.length)>1)
	{
		$("#accordion-1").empty();
		$.each(obj1.RegionStateList.stateDetails,function() {
			var stateid1=this.state_id;
			var stateName1=this.state_name;
			FILL_ACCORDION_UPDATE(stateid1,stateName1,selectedRow);
		});
	}
	else if((obj1.RegionStateList)!="")
	{
		$("#accordion-1").empty();
		var stateid1=obj1.RegionStateList.stateDetails.state_id;
		var stateName1=obj1.RegionStateList.stateDetails.state_name;
		FILL_ACCORDION_UPDATE(stateid1,stateName1,selectedRow);
	}
	document.getElementById('updatebuttonregion').style.visibility='visible';
	$(selectedRegionDistricts).each(function(){
		var checkId = 'dist'+':' + $.trim(this.stateId) + ':' + $.trim(this.distId);
		document.getElementById(checkId).checked = true;
	});
}

function regionUpdateSaveData() {
	var regionId = $("#regionId").val();
	var regionName=$("#regionName").val();
	if(regionName=="")
	{
		alert("Please select a region from the grid to update ");
		return;
	}
	var zoneid=$("#zoneSelection option:selected").attr("value");
	 var myarr2=[];
		var checkBoxId;
		var selectedIds = "";
	 $("#accordion-1 input:checked" )
	 .each(function() {
			checkBoxId=this.id;
				 if($(this).is(":checked"))
					{
		    		 var districtname=$(this).attr("districtName");
		    		 var districtName={};
		    		 districtName["districtName"]=districtname;
		    		 var districtNameMarker=JSON.stringify(districtName);
		    		 if(districtname!=undefined)
		    		 myarr2.push(districtNameMarker);
		    		 var districtid=$(this).attr("distid");
		    		 var districtId={};
		    		 districtId["districtId"]=districtid;
		    		 var districtIdMarker=JSON.stringify(districtId);
		    		 if(districtid!=undefined)
		    		 myarr2.push(districtIdMarker);
		    		 var stateId=$(this).attr("stateid");
		    		 var stateid={};
		    		 stateid["stateId"]=stateId;
		    		 var stateIdMarker=JSON.stringify(stateid);
		    		 if(stateId!=undefined)
		    		 myarr2.push(stateIdMarker);
		    		 var lat=$(this).attr("latitude");
		    		 var latitude={};
		    		 latitude["latitude"]=lat;
		    		 var latitideMarker=JSON.stringify(latitude);
		    		 if(lat!=undefined)
		    		 myarr2.push(latitideMarker);
		    		 var long=$(this).attr("longitude");
		    		 var longitude={};
		    		 longitude["longitude"]=long;
		    		 var longitudeMarker=JSON.stringify(longitude);
		    		 if(long!=undefined)
		    		 myarr2.push(longitudeMarker);
		    		 if(stateId!=undefined && districtid!=undefined && districtname!=undefined)
		    			 {
		    		 var selt =  $(this).attr("stateid") + ':' + $(this).attr("distid") + ':' + $(this).attr("districtName") + ","
		    		 selectedIds = selectedIds + selt;
		    			 }
					}
	 });
	 selectedIds = selectedIds.substring(0, selectedIds.length-1);
	if(checkBoxId==undefined)
	{
		alert("Region updation incomplete.Please select states");
		return;
	}
	var regionName=$("#regionName").val();

	if(regionNameArray.length==0)
	{
		regionNameArray.push(regionName);
	}

	else
	{
		for(var i=0;i<regionNameArray.length;i++)
		{
			if(regionName==selectedRegionName)
				{
				break;
				}
			else if(regionName==regionNameArray[i] )
			{
				alert("Region name "+regionName+" already created please create a new Region");
				return;
			}
		}
		regionNameArray.push(regionName);
	}
	webMethod = CXFServiceURL+"RegionCreationService/updateRegion";
	inputcol = "{'regId':'"+regionId+"','regName':'"+regionName+"','stDist':'"+selectedIds+"'}";
	var respObj = postAjax(webMethod, inputcol);
	webMethod=CXFServiceURL+"RegionCreationService/RegionGridList";
	var regObj=getAjax(webMethod);
	jsonObj =[];
	$(regObj.RegionList.regions).each(function(){
		var region=this.region;
		var regionId=this.regionId;
		var zoneName = this.zoneName;
		item = {};
		item ["id"] =regionId;
		item ["RegionName"] =region;
		item ["ZoneName"] = zoneName;
		jsonObj.push(item);
	});
/*	$(function () {
		$('#regionName').val("");
		 $("#zoneSelection").val('');
		 var zoneId=$("#zoneSelection").val();
		 alert(zoneId);
		 if(zoneId==""||zoneId==null)
		 {$("#accordion-1").empty();}
			//$("#ZoneFormList").show();
	});*/
	RESET_REGION();
	ALL_GRID_REGION(jsonObj);
	$("#zoneSelection").removeAttr("disabled");
	document.getElementById('updatebuttonregion').style.visibility='hidden';
	document.getElementById('okBtnRegionform').style.visibility='visible';
	regionZoneBoolean=false;
	RefreshRegion();
	DESTROY_DISTRICT_REGION();
}

function regionDeleteChecked() {
	
	jQuery("#branchResult").jqGrid('GridUnload');
	jQuery("#terrResult").jqGrid('GridUnload');
	$("#zoneSelection").removeAttr("disabled");
	regionNameArray.length=0;
	var s; 
	s = jQuery("#list2").jqGrid('getGridParam','selarrrow');
 	var jsonObj = new Array();
 	$(s).each(function() {
	   jsonObj.push(this);
	});
 	input="{'regionId':["+jsonObj+"]}";
 	webMethod = CXFServiceURL+"RegionCreationService/deleteRegion";
	postAjax(webMethod,input);
	webMethod=CXFServiceURL+"RegionCreationService/RegionGridList";
	var regObj=getAjax(webMethod);
	jsonObj =[];
	$(regObj.RegionList.regions).each(function(){
		var region=this.region;
		regionNameArray.push(region);
		var regionId=this.regionId;
		var zoneName = this.zoneName;
		item = {};
		item ["id"] =regionId;
		item ["RegionName"] =region;
		item ["ZoneName"] = zoneName;
		jsonObj.push(item);
	});
	ALL_GRID_REGION(jsonObj);
	document.getElementById('updatebuttonregion').style.visibility='hidden';
	document.getElementById('okBtnRegionform').style.visibility='visible';
	RefreshRegion();
	
	DESTROY_DISTRICT_REGION();
	RESET_REGION();
}