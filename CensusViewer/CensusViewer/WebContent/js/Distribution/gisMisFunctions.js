var mapColorFlag=true;
var mapColorFlag1=true;
var clickedGid=[];
function updateRegionStateDist(selectedDist,selectedState,resp) {
	var stDist=selectedState+":"+selectedDist;
	var gid=resp.AutoComplete.gid;
	var level=resp.AutoComplete.level;
	if(branchMap==true){
		if(jQuery.inArray( selectedDist, branchDistArr )==-1){
			mapColorFlag=false;
			alert("District does not belong to the Region Selected");
		}else{
			mapColorFlag=false;
			checkedDistBranch.push(gid);
			highlighChecked(checkedDistBranch,"District");
			checkDistBranch(stDist,level);	
		}
	}if(territoryMap==true){
		clickedVill=resp.AutoComplete.villId;
		if(jQuery.inArray( clickedVill, terrVillTownArr )==-1){
			mapColorFlag=false;
			alert("Village does not belong to the Branch Selected");
		}else{
			mapColorFlag=true;
			var villCode=resp.AutoComplete.villId;
			var stDistVill=selectedState+":"+selectedDist+":"+villCode;
			if(level=="village"){
			checkVillTerritory(stDistVill,level);
			}
		}
		if(level=="DISTRICT"){
			
		}
	}if(regionMap==true){
		if(jQuery.inArray( selectedDist,districtArr )==-1){
			mapColorFlag=false;
			alert("District does not belong to the Zone Selected");
			
		}else{
			mapColorFlag=false;
			checkedDistRegn.push(gid);
			highlighChecked(checkedDistRegn,"District");
			checkDistRegion(stDist,level);	
		}
			
		
	}
}
function showBranch(){
	branchFlag=true;
	//MAP_INIT();
}
