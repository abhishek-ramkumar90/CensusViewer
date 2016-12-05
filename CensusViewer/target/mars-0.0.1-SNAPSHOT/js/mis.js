var mapConfig,CXFServiceURL,webMethod,inputcol,txt,tempresp,count=0,response,tempgid,inputData,statusFlag;
var arr=[];
var tempArr = [];
var items = [];
var index=0;
var main;
var i=0;
var m=0;
var textitems = [];
var default_flag = false;
var text='';
var validation_flag=false;
var removeLast;
var Stylepresent=false;
var columnPosition=[];
var itemsOutput=[];
var itemsOutput2=[];
var advanceSettingOptions = new Array();
var tabletdIds = new Array();
var ok_flag=false;
var selectedCate=new Array();

removeID = function(idVal, strVal) {
	
		var sttemp = strVal.split(",");
		sttemp = remove_item(sttemp,idVal);
		
		strVal = "";
		for(var n1=0; n1<sttemp.length; n1++) {
			strVal = strVal + sttemp[n1] + ",";
		}
		strVal = strVal.substring(0,strVal.length-1);
		return strVal;
};

function DATA_ON_LOAD(){
	$.ajax
	(
			{
				type: "GET",
				async: false,
				url: "Config/Config.xml",
				dataType: "xml",
				success: function (xml) {
					$(xml).find('configuration').each
					(
							function () {
							//	mapConfig = $(this).find('mapConfig').text();
								CXFServiceURL=$(this).find('CXFServiceURL').text();
							}
					);
				}
			}
	);
	$('input:radio[value=b]').attr('checked', 'checked');
	$.ajax({  
		type:"GET",  
		url: CXFServiceURL+"StateDetailService/MARS/stateDetails",
		Accept: "application/json",
		contentType: "application/json; charset=utf-8",
		dataType: "json",  
		success: function(resp){  
			var markers =JSON.stringify(resp.State.states);
			var obj = $.parseJSON(markers);
			$.each(obj, function() {     
				var name=this.name;
				var statecode=this.statecode;
				$("#state").append("<option value='"+ statecode +"'>"+name+"</option>");
				$("#state option[value='" + statecode + "']").attr("st_code",statecode);
			}); 
		}
	});
	var selected = $("input[type='radio'][name='segment']:checked");
	if (selected.length > 0)
		sValue = selected.val();
	webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/d/Y/N/N ";
	$.ajax({  
		type:"GET",  
		url:webMethod,
		Accept: "application/json",
		async: false,
		contentType: "application/json; charset=utf-8",
		dataType: "json",  
		success: function(resp){  
			$("#category").append("<option value=''>Select</option>"); 
			var markers =JSON.stringify(resp.Category.categories);
			var obj = $.parseJSON(markers);
			$.each(obj, function() {     
				var categoryname=this.cat_name;
				var categoryid=this.cat_id;
					var catalt=this.cat_alt;
					for ( var i = 0; i < selectedCate.length; ++i) {
						 if(categoryid== selectedCate[i]){
						 $("#category").append("<option value='"+ categoryid+"'>"+categoryname+"</option>");
						 }
						 }
				$("#settings").append("<div class='categorysetting'><input type='checkbox' value='"+categoryid+"' id='"+catalt+"'>"+categoryname+"</div>");
			}); 
			$("#hospital").attr("checked","checked");
			$("#tot_work_p").attr("checked","checked");
			$("#tot_p").attr("checked","checked");
		},
		error: function(e){
			alert("error"+e);
		}
	});
	checkedCategory();
}



function ON_DISTRICT_CHANGE(){
	var val=$("#district").val();
	webMethod = CXFServiceURL+"VillageDetailService/MARS/Villagedetails";
	inputcol="{'jacksonVillageDetails':[{'distCode':'"+val+"'}]}";
	$.ajax({
		type: "POST",
		url: webMethod,
		data: inputcol,
		Accept: "application/json",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		ProcessData: true, 
		success: function(resp) {
			},
		error: function(e){
		}
	});
}



function ON_OK_CLICK(counter)
{
    textitems.length=0;
	var clear;
	for(clear=0;clear<=text;clear++)
		{
		$("#errorLabel"+clear).remove();
		}
	 for(text=0;text<m;text++)
		 {
		 var data = $("#test"+text).val();
		 var data1=$("#betweentest"+text).val();
	
		 //   if (isNaN(data)&& data!=undefined || data!="") {
		    if ((isNaN(data))&& data!=""&& data!=undefined) {
		    	if(data1==undefined)
		    		{
		    	$('.MainBoxdata').css('width', '700px');
		    	$('<label id="errorLabel'+text+'">*Enter Positive Numeric data </label>').insertAfter("#test"+text);
		    	validation_flag=true;
				return;
		    		}
		    	}
		    if ((isNaN(data1)&& data1!=undefined)&& data!="") {
		    	$('.MainBoxdata').css('width', '700px');
		    	$('<label id="errorLabel'+text+'">*Enter Positive Numeric data </label>').insertAfter("#betweentest"+text);
		    	validation_flag=true;
				return;
		    	
		    	}
		    if(data<0){
		    	$('.MainBoxdata').css('width', '700px');
		    	$('<label id="errorLabel'+text+'">*Enter Positive Numeric data </label>').insertAfter("#test"+text);
		    	validation_flag=true;
				return;
		    	}
		 }
	 var j=0;
	for(var k=0;k<m;k++)
		{
		var textVal = $("#test"+k).val();
		var textVal2=$("#betweentest"+k).val();
		if(textVal2 !== undefined) { 
		textitems[j]=textVal+":"+textVal2;
		j++;
		}
		else
		{
			textitems[j]=textVal;
			j++;
		}
		}
	var value;
	var col_name = []; 
	$('#subcategory :selected').each(function(i, selected){ 
		col_name[i] = $(selected).attr("col_name"); 
	});
	var value2=col_name.join(",");
	var segment= $("input[type='radio'][name='segment']:checked");
	if (segment.length > 0)
		selectedValue = segment.val();
	if ($("#district").val()==null)
	{
		var stcodes = []; 
		$('#state :selected').each(function(i, selected){ 
			stcodes[i] = $(selected).attr("st_code"); 
		});
		value=stcodes.join(",");
		 inputcol="{'jacksonStateColumnResult':[{'statecode':'"+value+"','columnnames':'"+value2+"'}]}";
		 webMethod = CXFServiceURL+"ResultService/state/subcategory/result";
		$.ajax({
			type: "POST",
			url: webMethod,
			data: inputcol,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			ProcessData: true, 
			success: function(resp) {
				fillResultGrid(resp);
			},
			error: function(e){
			}
		});
	}
	else{
		var val=$("#district").val();
		 inputcol="{'jacksonStDistDetails':[{'distCode':'"+val+"','columnnames':'"+value2+"'}]}";
		 webMethod = CXFServiceURL+"ResultService/state/district/subcategory/result";
		$.ajax({
			type: "POST",
			url: webMethod,
			data: inputcol,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			ProcessData: true, 
			success: function(resp) {
				fillResultGrid(resp);
			},
			error: function(e){
			}
		});
	}
	if ($("#state").val()==null)
	{
		return;
	}
}

function fillResultGrid(data) {
	$('.searchOverlay').css({ 'display': 'none' });
	$('.searchOverlay').appendTo('.searchWrapper');
	$('.transpOverlay').remove();
	var arr=[];
	var index=0;
	var ReceiptColModel=[];
	var ReceiptColNames=[];
	 if(data.Result.length==0)
	{
		alert("No Data found");
		$('.resultBlock').css('display', 'none');
		$('.searchOverlay').css({ 'display': 'none' });
		$('.searchOverlay').appendTo('.searchWrapper');
		$('.transpOverlay').remove();
		return;
	}
	var Gid='',Lat='',Lon='',Level='';
	$.each(data.Result.result, function() {      
		var row=this.row;
		var JSon = {};
		var index1=0;
		if(data.Result.result.length >=2){
			$.each(row, function(k,v) {
				var JSon1={};
				Value=v.name;
				var Type;
				if(v.name=="Gid" )
				{
					Type=v.intValue;
				}
				else{
					Type=v.value;
					if(v.value==null){
						Type=v.stringValue;
					}
				}
				JSon[Value] = Type;
				var name="name";
				var index2="index";
				var width ="width";
				if( v.name!="Latitude" && v.name!="Longitude" && v.name!="Level")
				{
					JSon1[name] =v.name;
					JSon1[index2] = v.name;
					JSon1[width] =410;
					ReceiptColModel[index1]=JSon1;
					ReceiptColNames[index1]=v.name;
					index1=index1+1;
				}
				if(v.name=="Gid"){Gid=v.intValue+','+Gid;}
				if(v.name=="Level"){Level=v.stringValue;}
				if(v.name=="Latitude"){Lat=v.doubleValue;}
				if(v.name=="Longitude"){Lon=v.doubleValue;}
			});		
		}else{
			$.each(data.Result.result.row, function(){
				var JSon1={};
				Value=this.name;
				Type=this.value;
				if(this.value==null){
					Type=this.stringValue;
				}
				JSon[Value] = Type;
				var name="name";
				var index2="index";
				var width ="width";
				if(this.name!="Latitude" && this.name!="Longitude" && this.name!="Level")
				{
					JSon1[name] =this.name;
					JSon1[index2] = this.name;
					JSon1[width] =300;
					ReceiptColModel[index1]=JSon1;
					ReceiptColNames[index1]=this.name;
					index1=index1+1;
				}
				if(this.name=="Gid"){Gid=this.intValue+','+Gid;}
				if(this.name=="Level"){Level=this.stringValue;}
				if(this.name=="Latitude"){Lat=this.doubleValue;}
				if(this.name=="Longitude"){Lon=this.doubleValue;}
			});
		}
		arr[index]=JSon;
		index=index+1;
	}); 
	mapzoomGIS(Gid,Lat,Lon,Level);
	jQuery("#ResultGrid").jqGrid('GridUnload');
	tempArr = arr;
	jQuery("#ResultGrid").jqGrid({
		data: arr,
		datatype: "local",
		colNames: ReceiptColNames,
		colModel: ReceiptColModel,
		loadonce: true,
		rowNum: 5, rowList: [5, 10, 20, 30], pager: '#ResultPager', recordpos: 'left', viewrecords: true, autowidth: true, height: 165, multiselect: true, shrinkToFit: false,
		loadComplete: function () {
			$("#ResultGrid input.cbox").prop('checked', true);
		},
		beforeSelectRow: function (rowid, e) {
			if (e.target.checked == true) {
				var celValue = $("#ResultGrid").jqGrid('getCell', rowid, 'Gid');
				var theArray = Gid.split(',');
				if(theArray.indexOf(celValue) < 0){ 
					theArray.push(celValue);
					Gid=theArray.join(',');
				}
				mapzoomGIS(Gid,Lat,Lon,Level);
				checkedValue = true;
			}
			else if (e.target.checked == false) {
				var celValue = $("#ResultGrid").jqGrid('getCell', rowid, 'Gid');
				var theArray = Gid.split(celValue);
				Gid=theArray.join('');
				mapzoomGIS(Gid,Lat,Lon,Level);
			}
		}
	});
	$("#cb_ResultGrid").hide();
	$("#ResultGrid input.cbox").prop('checked', true);
	$("#ResultGrid input.cbox").attr('gid', '1');
	jQuery("#ResultGrid").hideCol("Gid");
}


function ON_STATE_CHANGE(){
	$('#district').empty();
	$('#category').empty();
	var value=$("#state").val();
	var sValue = null;
	webMethod = CXFServiceURL+"DistrictDetailService/Country/districtDetails";
	inputcol="{'jacksonStateDetails':[{'statecode':'"+value+"'}]}";
	xhr=$.ajax({
		type: "POST",
		url: webMethod,
		data: inputcol,
		Accept: "application/json",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		ProcessData: true, 
		success: function(resp) {
			var markers =JSON.stringify(resp.districts.districts);
			var obj = $.parseJSON(markers);
			$.each(obj, function() {     
				var stateCode=this.stateCode;
				var name=this.name;
				var distcode=this.dist_code;
				$("#district").append("<option value='"+ distcode +":"+stateCode+"'>"+name+"</option>"); 
			});  
		},
		error: function(e){
		}
	});
	var selected = $("input[type='radio'][name='segment']:checked");
	//alert(selected);
	if (selected.length > 0)
		sValue = selected.val();
		if(stdisttemp!="")
			{
			webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/"+sValue+"/Y/N/N ";
			}
		else{
	webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/d/Y/N/N ";}
	$.ajax({  
		type:"GET",  
		url:webMethod,
		Accept: "application/json",
		async: false,
		contentType: "application/json; charset=utf-8",
		dataType: "json",  
		success: function(resp){  
			var markers =JSON.stringify(resp.Category.categories);
			var obj = $.parseJSON(markers);
			$("#category").append("<option value=''>Select</option>");
			 $("#settings").empty();
			 $.each(obj, function() { 
				 var categoryname=this.cat_name;
				 var categoryid=this.cat_id;
					var catalt=this.cat_alt; 
				 $("#settings").append("<div class='categorysetting'><input type='checkbox' value='"+categoryid+"' id='"+catalt+"'>"+categoryname+"</div>"); 
				 $("#hospital").attr("checked","checked");
					$("#tot_work_p").attr("checked","checked");
					$("#tot_p").attr("checked","checked");
					checkedCategory();
				 for ( var i = 0; i < selectedCate.length; ++i) {
				 if(categoryid== selectedCate[i]){
					// alert("categoryid  "+categoryid);
				 $("#category").append("<option value='"+ categoryid+"'>"+categoryname+"</option>");
				 }
				 }
				 });
		},
		error: function(e){
		}
	});
}
function ON_CATEGORY_CHANGE(segment){
	$("#subcategory").empty();
	var selected = $("input[type='radio'][name='segment']:checked");
	if (selected.length > 0)
		sValue = selected.val();
	var cat_id=$("#category").val();
	if(stdisttemp!="")
	{
	webMethod = CXFServiceURL+"SubCategoryDetailService/MARS/SubCategoryDetails/"+cat_id+"/"+segment+"/Y/Y ";
	$.ajax({  
		type:"GET",  
		url:webMethod,
		Accept: "application/json",
		contentType: "application/json; charset=utf-8",
		async: false,
		dataType: "json",  
		success: function(resp){  
			var markers =JSON.stringify(resp.SubCategory.subcategories);
			var obj = $.parseJSON(markers);
			if(obj.length>0)
			{
				$.each(obj, function() {     
					var sub_category=this.sub_category;
					var sub_cat_id=this.sub_cat_id;
					var column_name=this.column_name;
					var max=this.max;
					var min=this.min;
					$("#subcategory").append("<option value='"+column_name+":"+sub_category+"' >"+sub_category+"</option>"); 
					$("#subcategory option[value='" + sub_cat_id + "']").attr("col_name",column_name);  
					$("#subcategory option[value='" + sub_cat_id + "']").attr("max",max);  
					$("#subcategory option[value='" + sub_cat_id + "']").attr("min",min);  
				}); 
			}
			else
			{var sub_category=obj.sub_category;
			var sub_cat_id=obj.sub_cat_id;
			var column_name=obj.column_name;
			var max=obj.max;
			var min=obj.min;
			$("#subcategory").append("<option value='"+column_name+":"+sub_category+"' title='Minimum="+min+"-Maximum="+max+" '>"+sub_category+"</option>"); 
			$("#subcategory option[value='" + sub_cat_id + "']").attr("col_name",column_name);  
			$("#subcategory option[value='" + sub_cat_id + "']").attr("max",max);  
			$("#subcategory option[value='" + sub_cat_id + "']").attr("min",min);  }
		},
		error: function(e){
		}
	});
	}
	else
		{
		webMethod = CXFServiceURL+"SubCategoryDetailService/MARS/SubCategoryDetails/"+cat_id+"/d/Y/N ";
		$.ajax({  
			type:"GET",  
			url:webMethod,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			async: false,
			dataType: "json",  
			success: function(resp){  
				var markers =JSON.stringify(resp.SubCategory.subcategories);
				var obj = $.parseJSON(markers);
				if(obj.length>0)
				{
					$.each(obj, function() {     
						var sub_category=this.sub_category;
						var sub_cat_id=this.sub_cat_id;
						var column_name=this.column_name;
						var max=this.max;
						var min=this.min;
						$("#subcategory").append("<option value='"+column_name+":"+sub_category+"' >"+sub_category+"</option>"); 
						$("#subcategory option[value='" + sub_cat_id + "']").attr("col_name",column_name);  
						$("#subcategory option[value='" + sub_cat_id + "']").attr("max",max);  
						$("#subcategory option[value='" + sub_cat_id + "']").attr("min",min);  
					}); 
				}
				else
				{var sub_category=obj.sub_category;
				var sub_cat_id=obj.sub_cat_id;
				var column_name=obj.column_name;
				var max=obj.max;
				var min=obj.min;
				$("#subcategory").append("<option value='"+column_name+":"+sub_category+"' title='Minimum="+min+"-Maximum="+max+" '>"+sub_category+"</option>"); 
				$("#subcategory option[value='" + sub_cat_id + "']").attr("col_name",column_name);  
				$("#subcategory option[value='" + sub_cat_id + "']").attr("max",max);  
				$("#subcategory option[value='" + sub_cat_id + "']").attr("min",min);  }
			},
			error: function(e){
			}
		});
		}
}
function ON_RADIO_CLICK(value){
	$("#subcategory").empty();
	$("#finalGrid").empty();
	$("#columnNames").empty();
	i=0;
	items.length=0;
	textitems.length=0;
	m=0;
	//var stcod=$("#stdistgrid").jqgrid_getChecked();
	if(stcodetemp=="" && stdisttemp==""){
		alert("please select states or districts");
		validation_flag=true;
	}
	else{
		var isStateSelected="Y";
		if(stdisttemp!="")
			{
			var selected = $("input[type='radio'][name='segment']:checked");
			var sValue=null;
			sValue = selected.val();
			webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/"+sValue+"/"+isStateSelected+"/N/N ";
			}
		else
			{
			
		webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/d/"+isStateSelected+"/N/N ";}
		$.ajax({  
			type:"GET",  
			url:webMethod,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
			success: function(resp){  
				var markers =JSON.stringify(resp.Category.categories);
				var obj = $.parseJSON(markers);
				
				$("#category").append("<option value=''>Select</option>");
				$.each(obj, function() {     
					var categoryname=this.cat_name;
					var categoryid=this.cat_id;
					 for ( var i = 0; i < selectedCate.length; ++i) {
						 if(categoryid== selectedCate[i]){
							// alert("categoryid  "+categoryid);
						 $("#category").append("<option value='"+ categoryid+"'>"+categoryname+"</option>");
						 }
						 }
					
					
					//$("#category").append("<option value='"+ categoryid+"'>"+categoryname+"</option>"); 
				}); 
			},
			error: function(e){
			}
		});
	}
	}


function searchButtonOnClick(val1) {
	ok_flag = false;
	val1 = $.trim(val1);
	var vals = val1.split(' ');
	val = vals[0].toLowerCase();
	vals = val1.split(',');
	val = vals[0].toLowerCase();
	var storeSearch;
	$.ajax({
		url: CXFServiceURL+"SolrSearchDetailService/MARS/SolrSeach/"+val+" ",
		dataType: "json",
		data: {
		}, 
		success: function( data ) {
			storeSearch = new Array(data._results.length);
			for(var k=0; k< data._results.length; k++) {
				storeSearch[k] = new Array(2);
				storeSearch[k][0] = data._results[k].name;
				storeSearch[k][1] = data._results[k].village_code +","+data._results[k].town_code + ","+data._results[k].tehsil_code+ ", " + data._results[k].dist_code +","+data._results[k].state_code;
			}
			for(var m=0; m< storeSearch.length; m++) {
				if(val == storeSearch[m][0].toLowerCase()) {
					txt = storeSearch[m][1];
				}
			}
		}
	});
	var match = new Array();
	match = txt.split(',',5);
	var villagecode =  $.trim(match[0]);
	var towncode=match[1];
	//var tehsilcode=match[2];
	//var districtcode=match[3];
//	var statecode=match[4];
	inputcol=villagecode;
	if(towncode=="undefined"&& villagecode!= "undefined")
	{
		inputcol= match[0];
		webMethod = CXFServiceURL+"SolrPlaceDetailService/MARS/village/SolrSearchDetails";
		$.ajax({  
			type:"POST",  
			data:villagecode,//match[0] will contain village code
			url: webMethod,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
			success: function(resp){  
				
				fillSolrResultGrid(resp); 
			}
		});
	}else if(match[0]=="undefined"&& match[1]!="undefined")
	{
		inputcol= match[1];
		webMethod = CXFServiceURL+"SolrPlaceDetailService/MARS/towns/SolrSearchDetails";
		$.ajax({  
			type:"POST",  
			data:match[1],//match[1] will contain towncode
			url: webMethod,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
			success: function(resp){  
				fillSolrResultGrid(resp); 
			}
		});
	}
	else{
	}
	$('.resultBlock').css('display', 'block');

}



function SOLR_SEARCH(val){
	function log( message ) {
		$("#log").empty();
		$( "<div>" ).text( message ).prependTo( "#log" );
		var txt = $('#log').text().toString();
		var match = new Array();
		match = txt.split(',',5);
		var villagecode =  $.trim(match[0]);
		var towncode=match[1];
	//	var tehsilcode=match[2];
	//	var districtcode=match[3];
		//alert(match[4]);
		inputcol=villagecode;
		 $("#ResultGrid").trigger("reloadGrid");
 		 $(".resultBlock").find('ul').remove();
	 		$("#gridvillage").remove();
			$("#gridtown").remove();
			$(".resultBlockControl").parent().attr("class", "resultBlock");
		if(towncode=="undefined"&& villagecode!= "undefined")
		{
			inputcol= match[0];
			webMethod = CXFServiceURL+"SolrPlaceDetailService/MARS/village/SolrSearchDetails";
			$.ajax({  
				type:"POST",  
				data:villagecode,//match[0] will contain village code
				url: webMethod,
				Accept: "application/json",
				contentType: "application/json; charset=utf-8",
				dataType: "json",  
				success: function(resp){  
										fillSolrResultGrid(resp); 
				}
			});
		}else
			if(match[0]=="undefined"&& match[1]!="undefined")
			{
				inputcol= match[1];
				webMethod = CXFServiceURL+"SolrPlaceDetailService/MARS/towns/SolrSearchDetails";
				$.ajax({  
					type:"POST",  
					data:match[1],//match[1] will contain towncode
					url: webMethod,
					Accept: "application/json",
					contentType: "application/json; charset=utf-8",
					dataType: "json",  
					success: function(resp){  
						fillSolrResultGrid(resp); 
					}
				});
			}
			else
				if((($.trim(match[3]))!="undefined") && (match[4]!=undefined)){
					var distcode=$.trim(match[3]);
					webMethod = CXFServiceURL+"SolrPlaceDetailService/MARS/districts/SolrSearchDetails";
					$.ajax({  
						type:"POST",  
						data:distcode,//match[3] will contain districtcode
						url: webMethod,
						Accept: "application/json",
						contentType: "application/json; charset=utf-8",
						dataType: "json",  
						success: function(resp){  
							fillSolrResultGrid(resp); 
						}
					});
				}
	
				else if((match[4]!=undefined) && (($.trim(match[3]))=="undefined") )
				{
					//alert(match[4]);
					
					webMethod = CXFServiceURL+"SolrPlaceDetailService/MARS/states/SolrSearchDetails";
					$.ajax({  
						type:"POST",  
						data:match[4],//match[3] will contain districtcode
						url: webMethod,
						Accept: "application/json",
						contentType: "application/json; charset=utf-8",
						dataType: "json",  
						success: function(resp){  
							fillSolrResultGrid(resp); 
						}
					});
				}
	}  
	var list_output="";
	$( "#searchBox" ).autocomplete({
		source: function( request, response ) {
			$.ajax({
				url: CXFServiceURL+"SolrSearchDetailService/MARS/SolrSeach/"+val+" ",
				dataType: "json",
				data: {
				}, 
				success: function( data ) {
					ok_flag = false;
					response( $.map( data._results, function( item ) {

						list_output = "";

						if(item.name!=undefined) {
							list_output += item.name ;
						}

						if(item.tehsil_name!=undefined) {
							list_output += ", " + item.tehsil_name;
						}

						if(item.dist_name!=undefined) {
							list_output += ", " + item.dist_name ;
						}

						if(item.state_name!=undefined && ($.trim(item.name) != $.trim(item.state_name))) {
							list_output += ", "+ item.state_name;
						}

						return {
							label: list_output,
							value: list_output,
							label1: item.village_code +","+item.town_code + ","+item.tehsil_code+ ", " + item.dist_code +","+item.state_code,
						};
					}));
				}
			});
		},
		minLength: 3,
		select: function( event, ui ) {
			log( ui.item ?
					""+ ui.item.label1 :
						"Nothing selected, input was " + this.value);
			$('.resultBlock').css('display', 'block');
			$('.olControlScale, .olControlScaleLine').css('bottom', '294px');
			$('.olControlMousePosition').css('bottom', '267px');
			$('.resultBlock').css('bottom', '0px');
		},

		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		}, 
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		}
	});
}

function fillSolrResultGrid(resp,dis){
	response=resp.AutoComplete;
	arr[index]=response;
	var csolr=[];
	//$("#ResultGrid").trigger("reloadGrid");
	var gid=JSON.stringify(resp.AutoComplete.gid);
	var	lat=JSON.stringify(resp.AutoComplete.lat);
	var	level=JSON.stringify(resp.AutoComplete.level);
	var	longitude=JSON.stringify(resp.AutoComplete.longitude);
	$("#ResultGrid input:checkbox").each(function(){
    	var $this = $(this);
    	if($this.is(":checked")){
    		csolr.push($this.attr("id"));
    		
    	}
    	
    });
	for(var j=0; j<csolr.length; j++) {
    	 $('#'+csolr[j]).attr('checked','checked');
    }
	if(dis=='true'){
		index++;
		gid=gid+","+tempgid;
		mapzoomGIS(gid,lat,longitude,level,dis);
	}else{
		index++;
		gid=gid+","+tempgid;
		mapzoomGIS(gid,lat,longitude,level,dis);}
	jQuery("#ResultGrid").jqGrid('GridUnload');
	tempgid=gid;
	var ReceiptColNames = [
'Name', 
'Level', 
'Total Population', 
'Total Irrigated Area', 
'Electric Connections', 
'Education Institutions', 
'Number of Hospital',
'Number of Financial Institutions',
'Number of Drinking Water Sources',
'Total Number of Entertainment centers',
'Total Workers',
'Means of Transport',
'Annual Expanditure',
'Annual Income',
'Means of Communication',
'Types of Media',
'Types of Public Services',
'gid'
];
	var ReceiptColModel = [
{ name: 'name', index: 'name', width: 170 },
{ name: 'level', index: 'level', width: 170 },
{ name: 'tot_p', index: 'tot_p', width: 170 },
{ name: 'tot_irr', index: 'tot_irr', width: 170,hidden:true },
{ name: 'power', index: 'power', width: 170,hidden:true },
{ name: 'education', index: 'education', width: 170,hidden:true },
{ name:'hospital', index: 'hospital',width: 170},

{ name:'no_of_comm_inst', index: 'no_of_comm_inst',width: 170,hidden:true},
{ name:'no_of_watersrc', index: 'no_of_watersrc',width: 170,hidden:true},
{ name:'entertain', index: 'entertain',width: 170,hidden:true},
{ name:'tot_work_p', index: 'tot_work_p',width: 170,hidden:true},
{ name:'no_of_transport_mode', index: 'no_of_transport_mode', width: 170,hidden:true},
{ name:'tot_exp', index: 'tot_exp', width: 170,hidden:true},
{ name:'tot_inc', index: 'tot_inc', width: 170,hidden:true},
{ name:'no_of_comm_mode', index: 'no_of_comm_mode',width: 170,hidden:true},
{ name:'pap_mag_src', index: 'pap_mag_src',width: 170,hidden:true},
{ name:'service', index:'service',width: 170,hidden:true},
{ name: 'gid', index: 'gid', hidden:true 
}
];
	jQuery("#ResultGrid").jqGrid({
		data: arr,
		datatype: "local",
		colNames: ReceiptColNames,
		colModel: ReceiptColModel,
		caption: 'Report',
		rowNum: 5, rowList: [5, 10, 20, 30], pager: '#ResultPager', recordpos: 'left', viewrecords: true, autowidth: true, height: 165, multiselect: true, shrinkToFit: false,
		loadComplete: function () {
			CHANGE_SETTINGS();	
			if(ok_flag==false){
			
			}
		},
		beforeSelectRow: function (rowid, e) {
			if (e.target.checked == true) {
				var celValue = $("#ResultGrid").jqGrid ('getCell', rowid, 'gid');
				level = $("#ResultGrid").jqGrid ('getCell', rowid, 'level');
				gid=gid+','+celValue+',';
				mapzoomGIS(gid,lat,longitude,level);
				checkedValue = true;
			}
			else if (e.target.checked == false) {
				var celValue = $("#ResultGrid").jqGrid ('getCell', rowid, 'gid');
				level = $("#ResultGrid").jqGrid ('getCell', rowid, 'level');
				var theArray = gid.split(celValue+',');
				gid=theArray.join('');
				mapzoomGIS(gid,lat,longitude,level);
			}
			
		},
		gridComplete: function(){ 
            var ids = jQuery("#ResultGrid").jqGrid('getDataIDs'); 
            var cid;
            $("#ResultGrid input:checkbox").each(function(){
            	var $this = $(this);
            	 cid=$this.attr("id");
            	
            });
            $('#'+cid).attr('checked','checked');
            for(var j=0; j<csolr.length; j++) {
            	 $('#'+csolr[j]).attr('checked','checked');
            }
            
		}
	});
	$("#cb_ResultGrid").hide();
	
	
	
	
}
function myRound(value, places) {
	var multiplier = Math.pow(10, places);
	var na="Data Not Available";
	if(value==null){
		return na;
	}else{
		return (Math.round(value * multiplier) / multiplier);
	}
}
function LOAD_ST_DIST(){
	$("#stdistgrid").jqgrid({
		"name":"stdistgrid",
		"search":false,
		"refresh":false,
		"create":false,
		"delete":false,
		"showpage":false,
		"hidefooter":true,
		"hideheader":true
	});
	$.ajax({  
		type:"GET",  
		url: CXFServiceURL+"StateDetailService/MARS/stateDetails",
		Accept: "application/json",
		contentType: "application/json; charset=utf-8",
		async: false,
		dataType: "json",  
		success: function(resp){  
			var cols={};
			cols["name"]="Name";
			cols["tot_p"]="Total Population";
			cols["tot_irr"]="Total Irrigated Area";
			cols["power"]="Electric Connections";
			cols["education"]="Education Institutions";
			cols["hospital"]="Number of Hospital";
			//cols["tot_rec"]="Revenue";
			cols["no_of_comm_inst"]="Number of Financial Institutions";
			cols["no_of_watersrc"]="Number of Drinking Water Sources";
			cols["entertain"]="Total Number of Entertainment centers";
			cols["tot_work_p"]=" Total Workers";
			cols["no_of_transport_mode"]="Means of Transport";
			cols["tot_exp"]="Annual Expanditure";
			cols["tot_inc"]="Annual Income";
			cols["no_of_comm_mode"]="Means of Communication";
			cols["pap_mag_src"]="Types of Media";
			cols["service"]="Types of Public Services";
			$("#stdistgrid").jqgrid_buildHeader(cols,{"expand":true,"check":true});
			var cols2={};
			var index=1;
			$( resp.State.states ).each(function() {
				var json={};
				var stname=this.name;
				var totalp=myRound(this.tot_p,2);
				var irr=myRound(this.tot_irr,2);
				var pow=myRound(this.power,2);
				var edu=myRound(this.education,2);
				var hosp=this.hospital;
				//var rev=myRound(this.tot_rec,2);
				var comminst=this.no_of_comm_inst;
				var watersrc=this.no_of_watersrc;
				var ent=this.entertain;
				var workp=this.tot_work_p;
				var trans=this.no_of_transport_mode;
				var exp=this.tot_exp;
				var inc=this.tot_inc;
				var commu=this.no_of_comm_mode;
				var media=this.pap_mag_src;
				var service=this.service;
				var st_code=this.statecode;
				json["name"]=stname;
				json["tot_p"]=totalp;
				json["tot_irr"]=irr;
				json["power"]=pow;
				json["education"]=hosp;
				json["hospital"]=edu;
				//json["tot_rec"]=rev;
				json["no_of_comm_inst"]=comminst;
				json["no_of_watersrc"]=watersrc;
				json["entertain"]=ent;
				json["tot_work_p"]=workp;
				json["no_of_transport_mode"]=trans;
				json["tot_exp"]=exp;
				json["tot_inc"]=inc;
				json["no_of_comm_mode"]=commu;
				json["pap_mag_src"]=media;
				json["service"]=service;
				cols2[st_code]=json;
				index=index+1;
			});
			$("#stdistgrid").jqgrid_buildBody(cols2,{"expand":true,"check":true});
			$("#stdistgrid").jqgrid_resetCheckedItems();
		}
	});
	loadCheckedCategory();
}


function loadCheckedCategory() {
	var allElements = document.getElementsByTagName('td');
	var tempElements = new Array();
	for(var m=0; m<allElements.length; m++) {
		if(allElements[m].getAttribute('tdid') != null) {
			tempElements.push(allElements[m]);
			tabletdIds.push(allElements[m].getAttribute('id'));
		}
	}
	for(var k=0; k<advanceSettingOptions.length; k++) {
		if(document.getElementById(advanceSettingOptions[k]) == undefined || !document.getElementById(advanceSettingOptions[k]).checked) {
			for(var n=0; n<tempElements.length; n++) {
				if(tempElements[n].getAttribute('tdid') == advanceSettingOptions[k]) {
					tempElements[n].style.visibility = 'hidden';
					tempElements[n].style.display = 'none';
				}
			}
		} else {
			for(var j=0; j<tempElements.length; j++) {
				if(tempElements[j].getAttribute('tdid') == advanceSettingOptions[k]) {
					tempElements[j].style.visibility = 'visible';
					tempElements[j].style.display = 'table-cell';
				}
			}

		}
	}

}


function jQgridHandler(obj){

	$("#"+obj["id"]).jqgrid_setGridLoader("inline");
	st_code=obj["value"];
	if(obj["action"]=="check" && obj["name"]=="subgrid"){
		var str1= $("#"+obj["id"]).attr("id");
		$('body').append(
				$('<input/>')
				.attr('type', 'hidden')
				.attr('name', 'gridhide1')
				.attr('id', 'gridh')
				.val(str1)
		);
	}
	if(obj["action"]=="check"){
		$("#subcategory").empty();
		$("#finalGrid").empty();
		$("#columnNames").empty();
		i=0;
		items.length=0;
		textitems.length=0;
		itemsOutput.length=0;
		m=0;
		ON_STATE_CHANGE();
		if(!document.getElementById(statusFlag).checked) {
			stcodetemp = removeID(currentCheckId, stcodetemp);
		}
		if(stcodetemp=="" && stdisttemp=="")
		{
			$("#category").empty();
		}
	}
	if (obj["action"]=="changepage"){
		webMethod = CXFServiceURL+"DistrictDetailService/Country/districtDetails";
		inputcol="{'jacksonStateDetails':[{'statecode':'"+st_code+"'}]}";
		xhr=$.ajax({
			type: "POST",
			url: webMethod,
			data: inputcol,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			ProcessData: true, 
			success: function(resp) {
				var cols2={};
				var index=1;
				$( resp.districts.districts ).each(function() {
					var json={};
					var distname=this.name;
					var totalp=myRound(this.tot_p,2);
					var male=myRound(this.tot_m,2);
					var female=myRound(this.tot_f,2);
					//	var dist_code=this.dist_code;
					json["name"]=distname;
					json["tot_p"]=totalp;
					json["tot_m"]=male;
					json["tot_f"]=female;
					cols2[index]=json;
					index=index+1;
				});
				$("#"+obj["id"]).jqgrid_buildBody(cols2,{"expand":true,"check":true});
			},
			error: function(e){
			}
		});
		$("#"+obj["id"]).jqgrid_resetCheckedItems();
	}

	if (obj["action"]=="createsubgrid"){
		var cols={};
		var dist_code;
		cols["name"]="Name";
		cols["tot_p"]="Total Population";
		cols["tot_irr"]="Total Irrigated Area";
		cols["power"]="Electric Connections";
		cols["education"]="Education Institutions";
		cols["hospital"]="Number of Hospital";
		//cols["tot_rec"]="Revenue";
		cols["no_of_comm_inst"]="Number of Financial Institutions";
		cols["no_of_watersrc"]="Number of Drinking Water Sources";
		cols["entertain"]="Total Number of Entertainment centers";
		cols["tot_work_p"]=" Total Workers";
		cols["no_of_transport_mode"]="Means of Transport";
		cols["tot_exp"]="Annual Expanditure";
		cols["tot_inc"]="Annual Income";
		cols["no_of_comm_mode"]="Means of Communication";
		cols["pap_mag_src"]="Types of Media";
		cols["service"]="Types of Public Services";
		$("#"+obj["id"]).jqgrid({"name":"subgrid","create":false,"showpage":false,"hidefooter":true,"check":true});
		$("#"+obj["id"]).jqgrid_buildHeader(cols,{"expand":false,"check":true});
		webMethod = CXFServiceURL+"DistrictDetailService/Country/districtDetails";
		inputcol="{'jacksonStateDetails':[{'statecode':'"+st_code+"'}]}";
		xhr=$.ajax({
			type: "POST",
			url: webMethod,
			data: inputcol,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			async: false,
			dataType: "json",
			ProcessData: true, 
			success: function(resp) {
				var cols2={};
				$( resp.districts.districts ).each(function() {
					var json={};
					var distname=this.name;
					var totalp=myRound(this.tot_p,2);
					var irr=myRound(this.tot_irr,2);
					var pow=myRound(this.power,2);
					var edu=myRound(this.education,2);
					var hosp=myRound(this.hospital,2);
					//var rev=myRound(this.tot_rec,2);
					var comminst=myRound(this.no_of_comm_inst,2);
					var watersrc=myRound(this.no_of_watersrc,2);
					var ent=myRound(this.entertain,2);
					var workp=myRound(this.tot_work_p,2);
					var trans=myRound(this.no_of_transport_mode,2);
					var exp=myRound(this.tot_exp,2);
					var inc=myRound(this.tot_inc,2);
					var commu=myRound(this.no_of_comm_mode,2);
					var media=this.pap_mag_src;
					var service=this.service;
					dist_code=this.dist_code;
					var st_code=this.stateCode;
					var st_dist=dist_code+":"+st_code;
					json["name"]=distname;
					json["tot_p"]=totalp;
					json["tot_irr"]=irr;
					json["power"]=pow;
					json["education"]=edu;
					json["hospital"]=hosp;
					//json["tot_rec"]=rev;
					json["no_of_comm_inst"]=comminst;
					json["no_of_watersrc"]=watersrc;
					json["entertain"]=ent;
					json["tot_work_p"]=workp;
					json["no_of_transport_mode"]=trans;
					json["tot_exp"]=exp;
					json["tot_inc"]=inc;
					json["no_of_comm_mode"]=commu;
					json["pap_mag_src"]=media;
					json["service"]=service;
					cols2[st_dist]=json;
				});
				$("#"+obj["id"]).jqgrid_buildBody(cols2,{"expand":false,"check":true});
			},
			error: function(e){
			}
		});
		loadCheckedCategory();
	}
	$("#"+obj["id"]).jqgrid_setGridLoader("none");
}

function checkAll(obj) {
	$("#subcategory").empty();
	$("#finalGrid").empty();
	$("#columnNames").empty();
	i=0;
	items.length=0;
	textitems.length=0;
	itemsOutput.length=0;
	m=0;
	ON_STATE_CHANGE();
	if(stcodetemp=="" && stdisttemp=="")
		{
	$("#category").empty();
		}
}

function ON_OK_CLICK_NEW(){
	if(stcodetemp=="" && stdisttemp=="")
		{
		alert("please select state or district");
		validation_flag=true;
		return;
		}
	if((stcodetemp!="" && stdisttemp!="")||(stcodetemp=="" && stdisttemp!=""))
	{
		$("#ResultGrid").trigger("reloadGrid");
		if ($('#rural').is(":checked"))
		{ 
		
		   var categoryVal=$("#category").val();
 		   if(categoryVal=="")
				{
			alert("please select category and sub-categories");	
			
			return;
				}
			else if(columnNames==null && items=="")
			{
			alert("please select  sub-categories");
			validation_flag=true;
			return;
			}
			else if((items==""||items=="null") && textitems=="")
			{
			alert("please choose criteria and enter values ");
			validation_flag=true;
			return;
			}
			else if(items==null && textitems=="")
			{
			alert("please choose criteria and enter values ");
			validation_flag=true;
			return;
			}
			else if(textitems=="")
			{
			alert("please enter positive numeric value ");
			validation_flag=true;
			return;
			}
			else if(items!="" && textitems==",")
 		 	{
				 alert("please enter value for atleast one selected sub-category and criteria  ");
				 validation_flag=true;
				 return;
			}
 	
			 webMethod = CXFServiceURL+"ResultService/district/village/criteria/result ";
			  var items2=[];
			  var textitems2=[];
			  var j=0;
			  for(var i=0;i<items.length;i++)
				  {
		    	   if((items[i]==undefined && textitems[i]!="") ||(items[i]!=undefined && textitems[i]=="") || (items[i]==undefined && textitems[i]==undefined) || (items[i]==null && textitems[i]!=undefined )||(items[i]!=undefined && textitems[i]==undefined)||(items[i]=="null")||(items[i]==undefined && textitems[i]=="") ){
				   continue;
			      }
			items2[j]=items[i];
			textitems2[j]=textitems[i];
			var output2=[];
			var k=0;
			for (var m=0;m<items.length;m++)
				{
				if(items[m]==undefined || items[m]=="null" || (items[m]!=undefined && textitems[m]=="") )
					{
					continue;
					}
				output2[k]=itemsOutput[m];
				k++;
				}
				  j++;
				  }
				if(output2==undefined)
				{
					 alert("please choose criteria and enter value for atleast one chosen sub-category");
					 validation_flag=true;
				return;
				
				}
				 $("#ResultGrid").trigger("reloadGrid");
			 		 $(".resultBlock").find('ul').remove();
			 		$("#gridvillage").remove();
					$("#gridtown").remove();
					$(".resultBlockControl").parent().attr("class","resultBlock");
			 inputcol="{'jacksondetails':[{'stateDistrict':'"+stdisttemp+"','columns':'"+output2+"','criteria':'"+items2+"','value':'"+textitems2+"'}]}";
			$.ajax({
			    	type: "POST",
			        url: webMethod,
			       data: inputcol,
			        Accept: "application/json",
			        contentType: "application/json; charset=utf-8",
			        dataType: "json",
			        ProcessData: true, 
			        success: function(msg) {
			        	fillResultGrid(msg);
			        },
			        error: function(e){
			        	alert(e);
			        }
			    });
			
			
		}
		if($('#urban').is(":checked"))
			{
			 $(".resultBlock").find('ul').remove();
			var categoryVal=$("#category").val();
	 		   if(categoryVal=="")
					{
				alert("please select category and sub-categories");	
					}
				else if(columnNames==null && items=="")
				{
				alert("please select  sub-categories");
				validation_flag=true;
				return;
				}
				else if((items==""||items=="null") && textitems=="")
				{
				alert("please choose criteria and enter values ");
				validation_flag=true;
				return;
				}
				else if(items==null && textitems=="")
				{
				alert("please choose criteria and enter values ");
				validation_flag=true;
				return;
				}
				else if(textitems=="")
				{
				alert("please enter positive numeric value ");
				validation_flag=true;
				return;
				}
				else if(items=="" && textitems!="")
	 		 	{
			 alert("please choose criteria and enter value for atleast one chosen sub-category");		
				}
				else if(items!="" && textitems==",")
	 		 	{
					 alert("please enter value for atleast one selected sub-category and criteria  ");		
				}
			
		//	$('#divResultDetails').find('ul').hide();
			$("#ResultGrid").trigger("reloadGrid");
			webMethod = CXFServiceURL+"ResultService/state/dist/town/criteria/result";
			  var items2=[];
			  var textitems2=[];
			  var j=0;
			  for(var i=0;i<items.length;i++)
				  {
		    	   if((items[i]==undefined && textitems[i]!="") ||(items[i]!=undefined && textitems[i]=="") || (items[i]==undefined && textitems[i]==undefined) || (items[i]==null && textitems[i]!=undefined )||(items[i]!=undefined && textitems[i]==undefined)||(items[i]=="null" )||(items[i]==undefined && textitems[i]=="")){
				   continue;
			      }
			items2[j]=items[i];
			textitems2[j]=textitems[i];
			var output2=[];
			var k=0;
			for (var m=0;m<items.length;m++)
				{
				if(items[m]==undefined || items[m]=="null" || (items[m]!=undefined && textitems[m]=="") )
					{
					continue;
					}
				output2[k]=itemsOutput[m];
				k++;
				}
				  j++;
				  }
				if(output2==undefined)
				{
					 alert("please choose criteria and enter value for atleast one chosen sub-category");
				return;
				
				}
			/*	$(".resultBlock").find('ul').remove();
				$("#gridvillage").remove();
				$("#gridtown").remove();*/	  $("#ResultGrid").trigger("reloadGrid");
		 		 $(".resultBlock").find('ul').remove();
			 		$("#gridvillage").remove();
					$("#gridtown").remove();
					$(".resultBlockControl").parent().attr("class", "resultBlock");
			 inputcol="{'jacksondetails':[{'stateDistrict':'"+stdisttemp+"','columns':'"+output2+"','criteria':'"+items2+"','value':'"+textitems2+"'}]}";
			$.ajax({
			    	type: "POST",
			        url: webMethod,
			       data: inputcol,
			        Accept: "application/json",
			        contentType: "application/json; charset=utf-8",
			        dataType: "json",
			        ProcessData: true, 
			        success: function(msg) {
			        
			        	fillResultGrid(msg);
			        },
			        error: function(e){
			        }
			    });
		}
		if($('#both').is(":checked"))
		{
			var categoryVal=$("#category").val();
			var both_validation_flag=0;
	 		   if(categoryVal=="")
					{
				alert("please select category and sub-categories");	
				validation_flag=true;
				return;
					}
				else if(columnNames==null && items=="")
				{
				alert("please select  sub-categories");
				validation_flag=true;
				return;
				}
				else if((items==""||items=="null") && textitems=="")
				{
				alert("please choose criteria and enter values ");
				validation_flag=true;
				return;
				}
				else if(items==null && textitems=="")
				{
				alert("please choose criteria and enter values ");
				validation_flag=true;
				return;
				}
				else if(textitems=="")
				{
				alert("please enter positive numeric value ");
				validation_flag=true;
				return;
				}
				else if(items=="" && textitems!="")
	 		 	{
			 alert("please choose criteria and enter value for atleast one chosen sub-category");	
			 validation_flag=true;
			 return;
				}
				else if(items!="" && textitems==",")
	 		 	{
					 alert("please enter value for atleast one selected sub-category and criteria  ");	
					 validation_flag=true;
					 return;
				}
	 		 $("#ResultGrid").trigger("reloadGrid");
	 		$(".resultBlock").find('ul').remove();
	 		$("#gridvillage").remove();
			$("#gridtown").remove();
			 $("#divResultDetails").before('<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"><li class="ui-state-default ui-corner-top ui-tabs-active ui-state-active"><a href="#gridvillage" class="ui-tabs-anchor" id="tabvillage" >Village</a></li><li class="ui-state-default ui-corner-top"><a href="#gridtown" class="ui-tabs-anchor" id="tabtown">Town</a></li></ul> <div id="gridvillage"></div><div id="gridtown"></div>');
			 $(".resultBlock").attr('class','resultBlock ui-tabs ui-widget ui-widget-content ui-corner-all');
				$("#gridvillage").hide();
				$("#gridtown").hide();
				webMethod = CXFServiceURL+"ResultService/district/village/criteria/result ";
				  var items2=[];
				  var textitems2=[];
				  var j=0;
				  for(var i=0;i<items.length;i++)
					  {
			    	   if((items[i]==undefined && textitems[i]!="") ||(items[i]!=undefined && textitems[i]=="") || (items[i]==undefined && textitems[i]==undefined) || (items[i]==null && textitems[i]!=undefined )||(items[i]!=undefined && textitems[i]==undefined)||(items[i]=="null")||(items[i]==undefined && textitems[i]=="") ){
					   continue;
				      }
				items2[j]=items[i];
				textitems2[j]=textitems[i];
				var output2=[];
				var k=0;
				for (var m=0;m<items.length;m++)
					{
					if(items[m]==undefined || items[m]=="null" || (items[m]!=undefined && textitems[m]=="") )
						{
						continue;
						}
					output2[k]=itemsOutput[m];
					k++;
					}
					  j++;
					  }
					if(output2==undefined)
					{
						 alert("please choose criteria and enter value for atleast one chosen sub-category");
						 validation_flag=true;
					return;
					
					}
				 inputcol="{'jacksondetails':[{'stateDistrict':'"+stdisttemp+"','columns':'"+output2+"','criteria':'"+items2+"','value':'"+textitems2+"'}]}";
				$.ajax({
				    	type: "POST",
				        url: webMethod,
				       data: inputcol,
				        Accept: "application/json",
				        contentType: "application/json; charset=utf-8",
				        dataType: "json",
				        ProcessData: true, 
				        success: function(msg) {
				        	if(msg.Result.length==0)
			        		{
			        		alert("village data not available");
			        		both_validation_flag=both_validation_flag+1;
			        	 	$("#ResultGrid").hide();
			        		$('tr.ui-jqgrid-labels').hide();
			        		return;
			        		}
				            $("#gridvillage").html(fillResultGrid(msg));
				        },
				        error: function(e){
				        }
				    });
				 $("a[href=#gridvillage]").click(function(){
						$("#gridvillage").hide();
					 webMethod = CXFServiceURL+"ResultService/district/village/criteria/result ";
					  var items2=[];
					  var textitems2=[];
					  var j=0;
					  for(var i=0;i<items.length;i++)
						  {
				    	   if((items[i]==undefined && textitems[i]!="") ||(items[i]!=undefined && textitems[i]=="") || (items[i]==undefined && textitems[i]==undefined) || (items[i]==null && textitems[i]!=undefined )||(items[i]!=undefined && textitems[i]==undefined)||(items[i]=="null")|| (items[i]==undefined && textitems[i]=="")){
						   continue;
					      }
					items2[j]=items[i];
					textitems2[j]=textitems[i];
					var output2=[];
					var k=0;
					for (var m=0;m<items.length;m++)
						{
						if(items[m]==undefined || items[m]=="null" || (items[m]!=undefined && textitems[m]=="") )
							{
							continue;
							}
						output2[k]=itemsOutput[m];
						k++;
						}
						  j++;
						  }
						if(output2==undefined)
						{
							 alert("please choose criteria and enter value for atleast one chosen sub-category");
							 validation_flag=true;
						return;
						}
						 inputcol="{'jacksondetails':[{'stateDistrict':'"+stdisttemp+"','columns':'"+output2+"','criteria':'"+items2+"','value':'"+textitems2+"'}]}";
						$.ajax({
						    	type: "POST",
						        url: webMethod,
						       data: inputcol,
						        Accept: "application/json",
						        contentType: "application/json; charset=utf-8",
						        dataType: "json",
						        ProcessData: true, 
						        success: function(msg) {
						        	if(msg.Result.length==0)
					        		{
					        		alert("village data not available");
					        		if(both_validation_flag==1)
					        		{
					        				$('.resultBlock').css('display', 'none');
					        				$('.searchOverlay').css({ 'display': 'none' });
					        				$('.searchOverlay').appendTo('.searchWrapper');
					        				$('.transpOverlay').remove();
					        				return;
					        		}
					        	if(both_validation_flag==0)
					        		{
					        		both_validation_flag=both_validation_flag+1;
					        		//alert(both_validation_flag);
					        		}
					        		return;
					        		}
						        	fillResultGrid(msg);
						        },
						        error: function(e){
						        }
						    });
				});
				 $("a[href=#gridtown]").click(function(){
						$("#gridtown").hide();
					 webMethod = CXFServiceURL+"ResultService/state/dist/town/criteria/result";
					  var items2=[];
					  var textitems2=[];
					  var j=0;
					  for(var i=0;i<items.length;i++)
						  {
				    	   if((items[i]==undefined && textitems[i]!="") ||(items[i]!=undefined && textitems[i]=="") || (items[i]==undefined && textitems[i]==undefined) || (items[i]==null && textitems[i]!=undefined )||(items[i]!=undefined && textitems[i]==undefined)||(items[i]=="null")||(items[i]==undefined && textitems[i]=="") ){
						   continue;
					      }
					items2[j]=items[i];
					textitems2[j]=textitems[i];
					var output2=[];
					var k=0;
					for (var m=0;m<items.length;m++)
						{
						if(items[m]==undefined || items[m]=="null" || (items[m]!=undefined && textitems[m]=="") )
							{
							continue;
							}
						output2[k]=itemsOutput[m];
						k++;
						}
						  j++;
						  }
						if(output2==undefined)
						{
							 alert("please choose criteria and enter value for atleast one chosen sub-category");
							 validation_flag=true;
						return;
						}
						 inputcol="{'jacksondetails':[{'stateDistrict':'"+stdisttemp+"','columns':'"+output2+"','criteria':'"+items2+"','value':'"+textitems2+"'}]}";
						$.ajax({
						    	type: "POST",
						        url: webMethod,
						       data: inputcol,
						        Accept: "application/json",
						        contentType: "application/json; charset=utf-8",
						        dataType: "json",
						        ProcessData: true, 
						        success: function(msg) {
						        	if(msg.Result.length==0)
					        		{
					        		alert("town data not available");
					        	 	if(both_validation_flag==1)
					        		{
					        				$('.resultBlock').css('display', 'none');
					        				$('.searchOverlay').css({ 'display': 'none' });
					        				$('.searchOverlay').appendTo('.searchWrapper');
					        				$('.transpOverlay').remove();
					        				return;
					        		}
					        		if(both_validation_flag==0)
					        		{
					        		both_validation_flag=both_validation_flag+1;
					        		}
					        		return;
					        		}
						        	 $("#gridtown").html(fillResultGrid(msg));
						        },
						        error: function(e){
						        }
						    });
					});
		}
			}
	else{
		var categoryVal=$("#category").val();
		   if(categoryVal=="")
				{
			alert("please select category and sub-categories");	
			validation_flag=true;
			return;
				}
			else if(columnNames==null && items=="")
			{
			alert("please select  sub-categories");
			validation_flag=true;
			return;
			}
			else if((items==""||items=="null") && textitems=="")
			{
			alert("please choose criteria and enter values ");
			validation_flag=true;
			return;
			}
			else if(items==null && textitems=="")
			{
			alert("please choose criteria and enter values ");
			validation_flag=true;
			return;
			}
			else if(textitems=="")
			{
			alert("please enter positive numeric value ");
			validation_flag=true;
			return;
			}
			else if(items!="" && textitems==",")
		 	{
		 alert("please enter value for atleast one selected sub-category and criteria  ");	
		 validation_flag=true;
		 return;
			}
		$("#ResultGrid").trigger("reloadGrid");
		  webMethod = CXFServiceURL+"ResultService/state/criteria/result";
		  var items2=[];
		  var textitems2=[];
		  var j=0;
		  for(var i=0;i<items.length;i++)
			  {
	    	   if((items[i]==undefined && textitems[i]!="") ||(items[i]!=undefined && textitems[i]=="") || (items[i]==undefined && textitems[i]==undefined) || (items[i]==null && textitems[i]!=undefined )||(items[i]!=undefined && textitems[i]==undefined)||(items[i]=="null")||(items[i]==undefined && textitems[i]=="") ){
			   continue;
		      }
		items2[j]=items[i];
		textitems2[j]=textitems[i];
		var output2=[];
		var k=0;
		for (var m=0;m<items.length;m++)
			{
			if(items[m]==undefined || items[m]=="null" || (items[m]!=undefined && textitems[m]=="") )
				{
				continue;
				}
			output2[k]=itemsOutput[m];
		
			k++;
			}
			  j++;
			  }
			if(output2==undefined)
				{
				 alert("please choose criteria and enter value for atleast one chosen sub-category");
				 validation_flag=true;
				return;
				}
			$(".resultBlock").find('ul').remove();
		 inputcol="{'jacksondetails':[{'stcode':'"+stcodetemp+"','columns':'"+output2+"','criteria':'"+items2+"','value':'"+textitems2+"'}]}";
		   $.ajax({
			    	type: "POST",
			        url: webMethod,
			       data: inputcol,
			        Accept: "application/json",
			        contentType: "application/json; charset=utf-8",
			        dataType: "json",
			        ProcessData: true, 
			        success: function(msg) {
			        	fillResultGrid(msg);
			        },
			        error: function(e){
			        	alert("=4=error=="+e);
			        }
			    });
	}
}

function ADD_BUTTON_CLICK(counter)
{
counter++;
$("#Value").empty();
	var str=$("#subcategory").val();
	var foo = []; 
	$('#subcategory :selected').each(function(i, selected){ 
	  foo[m] = $(selected).attr("id"); 
	});
	var myarray = str.toString().split(',');
	main=myarray.length;
	for(var k = 0; k < myarray.length; k++){
		var Str2=myarray[k].toString().split(':');
		for(var j=0;j<Str2.length;j++) {
        if(counter>1000) {
        //  alert("Only 10 textboxes allow");
          return false;
        }   
        var g=j+1;
        if(Str2[g]!= undefined) {
			test = false;
			for(var k = 0; k < myarray.length; k++){
				sub_cat_values = myarray[k].toString().split(':');
				sub_cat_values[1] = $.trim(sub_cat_values[1]);
				for(var c=0;c<=m;c++) {
					var label1=$.trim($("#label"+c).text());
					if(label1 != sub_cat_values[1] ) {
						test = false;
					} else {
						test = true;
						break;
					}
					
				}
				if(test == false) {
					test = true;
					var cat_id=$("#category").val();
					var BoxesDiv = $(document.createElement('div')).attr("id", 'BoxesDiv' + counter).attr("class","MainBoxdata");
					var newTextBoxDiv1 = $(document.createElement('div')).attr("id", 'LabelDiv' + counter).attr("class","textlabeldata");
		        	var newTextBoxDiv2 = $(document.createElement('div')).attr("id", 'ComboBoxDiv' + counter).attr("class","selecrWrapperdata");
		        	var newTextBoxDiv3=$(document.createElement('div')).attr("id", 'TextBoxDiv' + counter).attr("class","textinputdata");
		        	//$("#columnNames").append("<option value='"+sub_cat_values[0]+"' selected='selected' id='OptionAB"+m+"'  >"+sub_cat_values[0]+"</option>"); 
		        	newTextBoxDiv1.html(' <label for="male" id="label'+m+'" id2='+cat_id+'  >'+sub_cat_values[1]+'</label><br></br>');
		        	newTextBoxDiv2.html(' <select id="operator" id2='+m+' columnId='+sub_cat_values[0]+'  ><option value="null">Select</option><option value=">" id="0">Greater Than</option><option value="=" id="1">Equal To</option><option value="<" id="2" >Less Than</option><option value="<>" id="3">Between</option></select> ');
		        	newTextBoxDiv3.html('<input type="text"  name="textbox'+ counter +'"   id="test'+m+ '"    value=""  size="20"     >');
					BoxesDiv.appendTo("div#finalGrid");
					newTextBoxDiv1.appendTo("div#BoxesDiv"+counter);
					newTextBoxDiv2.appendTo("div#BoxesDiv"+counter);
					newTextBoxDiv3.appendTo("div#BoxesDiv"+counter);
					 $("#subcategory :selected").remove();
					counter++;
					m++;
					default_flag = true;
			    }
				
			
			}
	}
  }
}
 return counter;
}
$("#operator").live('change',function() {
		  var value=$(this).val();
		  var id = $(this).attr("id2");
		  var columnName=$(this).attr("columnId");
		var optionid =$(this).children(":selected").attr("id");
	if(optionid=="3"){
		$('<input type="text" name="fname" id="betweentest'+id+'">').insertAfter("#test"+id);
	}
	else
		{
		 $("#betweentest"+id).remove();
		}
items[id]=value;
itemsOutput[id]=columnName;
return;
		});

$(".MainBoxdata").live('click',function(e) {
	if(e.ctrlKey)
		{
	  	  $(this).css("backgroundColor", "#99CCCC"); 
		  $(this).css("padding-top", "10px");
		  $(this).css("padding-left", "5px");
		  /*$(this).css("width", "100%");*/
		  $(this).css("height", "35px");
		  $(this).css("margin-bottom", "10px");
		  $(this).attr("Style1",1);
		}
	else
		{
	$(".MainBoxdata").css("backgroundColor", "#FFF"); 
	$(".MainBoxdata").removeAttr("Style1");
	  $(this).css("backgroundColor", "#99CCCC"); 
  $(this).css("padding-top", "10px");
  $(this).css("padding-left", "5px");
  /*$(this).css("width", "100%");*/
  $(this).css("height", "35px");
  $(this).css("margin-bottom", "10px");
  $(this).attr("Style1",1);
		}
});

function ON_RESET()
	{
	$("#subcategory").empty();
	  var segment=$('input[name=segment]:checked').val();
	var selected = $("input[type='radio'][name='segment']:checked");
	if (selected.length > 0)
		sValue = selected.val();
	var cat_id=$("#category").val();



	webMethod = CXFServiceURL+"SubCategoryDetailService/MARS/SubCategoryDetails/"+cat_id+"/d/Y/N ";
	$.ajax({  
		type:"GET",  
		url:webMethod,
		Accept: "application/json",
		contentType: "application/json; charset=utf-8",
		async: false,
		dataType: "json",  
		success: function(resp){  

			var markers =JSON.stringify(resp.SubCategory.subcategories);
			var obj = $.parseJSON(markers);
			if(obj.length>0)
			{
				$.each(obj, function() {     
					var sub_category=this.sub_category;
					var sub_cat_id=this.sub_cat_id;
					var column_name=this.column_name;
					var max=this.max;
					var min=this.min;
					$("#subcategory").append("<option value='"+column_name+":"+sub_category+"' >"+sub_category+"</option>"); 
					$("#subcategory option[value='" + sub_cat_id + "']").attr("col_name",column_name);  
					$("#subcategory option[value='" + sub_cat_id + "']").attr("max",max);  
					$("#subcategory option[value='" + sub_cat_id + "']").attr("min",min);  

				}); 
			}
			else
			{var sub_category=obj.sub_category;
			var sub_cat_id=obj.sub_cat_id;
			var column_name=obj.column_name;
			var max=obj.max;
			var min=obj.min;
			$("#subcategory").append("<option value='"+column_name+":"+sub_category+"' title='Minimum="+min+"-Maximum="+max+" '>"+sub_category+"</option>"); 
			$("#subcategory option[value='" + sub_cat_id + "']").attr("col_name",column_name);  
			$("#subcategory option[value='" + sub_cat_id + "']").attr("max",max);  
			$("#subcategory option[value='" + sub_cat_id + "']").attr("min",min);  }


		},
		error: function(e){
		
		}
	});

	
	$("#finalGrid").empty();
	$("#columnNames").empty();
	i=0;
	items.length=0;
	textitems.length=0;
	itemsOutput.length=0;
	m=0;
}

function BUTTON_REMOVE(counter){
	var inputDataLength=m;
	var boolean=true;
	var divid=[];
	if(boolean== true)
		{
mainloop:for(var i=0;i<inputDataLength;i++)
	{
		var parentDiv=$('#label'+i).parent("div").parent("div").attr('id');
		if($("#"+parentDiv).attr('Style1'))
			{
			Stylepresent=true;
          divid.push(parentDiv);
		  var sub_category=$("#label"+i ).text();
		  var catid=$("#label"+i ).attr("id2");
		  var cat_id=$("#category").val();
		  if(sub_category!="" && cat_id==catid)
			{
			  var collname=itemsOutput[i];
		 $("#subcategory").append("<option value='"+collname+":"+sub_category+"' >"+sub_category+"</option>");
		 $("#OptionAB"+i).remove();
		// if(items[i]!="undefined" && items[i]!=undefined )
		//	 {
			 delete items[i];
			 delete itemsOutput[i];
			// }
		 delete textitems[i];
			}
		}
		else
		{
		}
	}
	var i=0;
	while(i<divid.length)
		     {
		         var parentDiv=divid[i];
		         $("#"+parentDiv).remove();
		       i++;
		     }
		}
	else{
	var sub_category=$("#label"+m ).text();
	var columnname=$("#columnNames").val();
	var column_names=columnname.toString();
	var output=column_names.split(/[\s,]+/);
	var columnname1=output[output.length-1];
	$("#subcategory").append("<option value='"+columnname1+":"+sub_category+"' >"+sub_category+"</option>");
	$("div#BoxesDiv"+counter).remove();
		$("#OptionAB"+m).remove();
	}
	
	var i=0;
	$("select").each(function(){
 var id=$(this).attr("id2");
 if(id!=undefined)
	 {
	 $(this).attr("id2",i);
	 i++;
}
	});
	if(!$("div#finalGrid").children().length>0)
	{

items.length=0;
itemsOutput.length=0;
	}
}


function ADD_ALL_BUTTON_CLICK(counter)
{	itemsOutput2.length=0;
	counter++;
	$("#Value").empty();
	 $('#subcategory option').prop('selected', 'selected');
		var str=$("#subcategory").val();
		var myarray = str.toString().split(',');
		main=myarray.length;
		for(var k = 0; k < myarray.length; k++){
			var Str2=myarray[k].toString().split(':');
			for(var j=0;j<Str2.length;j++) {
	        if(counter>1000) {
	         // alert("Only 10 textboxes allow");
	          return false;
	        }   
	        var g=j+1;
	        if(Str2[g]!= undefined) {
				test = false;
				for(var k = 0; k < myarray.length; k++){
					sub_cat_values = myarray[k].toString().split(':');
					sub_cat_values[1] = $.trim(sub_cat_values[1]);
					for(var c=0;c<=m;c++) {
						var label1=$.trim($("#label"+c).text());
						if(label1 != sub_cat_values[1] ) {
							test = false;
						} else {
							test = true;
							break;
						}
						
					}
					if(test == false) {
						test = true;
						var cat_id=$("#category").val();
						var BoxesDiv = $(document.createElement('div')).attr("id", 'BoxesDiv' + counter).attr("class","MainBoxdata");
						var newTextBoxDiv1 = $(document.createElement('div')).attr("id", 'LabelDiv' + counter).attr("class","textlabeldata");
			        	var newTextBoxDiv2 = $(document.createElement('div')).attr("id", 'ComboBoxDiv' + counter).attr("class","selecrWrapperdata");
			        	var newTextBoxDiv3=$(document.createElement('div')).attr("id", 'TextBoxDiv' + counter).attr("class","textinputdata");
			        //	itemsOutput.push(sub_cat_values[0]);
			        	//$("#columnNames").append("<option value='"+sub_cat_values[0]+"' selected='selected' id='OptionAB"+m+"'  >"+sub_cat_values[0]+"</option>"); 
			        	newTextBoxDiv1.html(' <label for="male" id="label'+m+'" id2='+cat_id+' columnName='+sub_cat_values[0]+'  >'+sub_cat_values[1]+'</label><br></br>');
			        	newTextBoxDiv2.html(' <select id="operator" id2='+m+' columnId='+sub_cat_values[0]+'  ><option value="null">Select</option><option value=">" id="0">Greater Than</option><option value="=" id="1">Equal To</option><option value="<" id="2" >Less Than</option><option value="<>" id="3">Between</option></select> ');
			        	newTextBoxDiv3.html('<input type="text"  name="textbox'+ counter +'"   id="test'+m+ '"    value=""  size="20"     >');
						BoxesDiv.appendTo("div#finalGrid");
						newTextBoxDiv1.appendTo("div#BoxesDiv"+counter);
						newTextBoxDiv2.appendTo("div#BoxesDiv"+counter);
						newTextBoxDiv3.appendTo("div#BoxesDiv"+counter);
						 $("#subcategory :selected").remove();
						counter++;
						m++;
						default_flag = true;
				    }
				}
		}
	  }
	}
	 return counter;
	}

function REMOVE_ALL_BUTTON_CLICK(counter)
{
	$(".finalGrid").find("div").css('background-color', '#99CCCC');
	var inputDataLength=m;
	var boolean=true;
	var divid=[];
	if(boolean== true)
		{
mainloop:for(var i=0;i<inputDataLength;i++)
	{
		var parentDiv=$('#label'+i).parent("div").parent("div").attr('id');
		if($("#"+parentDiv).attr('style'))
			{
			Stylepresent=true;
          divid.push(parentDiv);
		  var sub_category=$("#label"+i ).text();
		  var catid=$("#label"+i ).attr("id2");
		  var cat_id=$("#category").val();
		  var collname=$("#label"+i).attr("columnName");
		  if(sub_category!="" && cat_id==catid)
			{
		
		 $("#subcategory").append("<option value='"+collname+":"+sub_category+"' >"+sub_category+"</option>");
		 $("#OptionAB"+i).remove();
		 if(items[i]!="undefined" && items[i]!=undefined )
			 {
			 delete items[i];
			 delete itemsOutput[i];
			 }
		 textitems.splice(i,1);
			}
		}
		else
		{
		}
	}
	var i=0;
	while(i<divid.length)
		     {
		         var parentDiv=divid[i];
		         $("#"+parentDiv).remove();
		       i++;
		     }
		}
	
	else{
	var sub_category=$("#label"+m ).text();
	var columnname=$("#columnNames").val();
	var column_names=columnname.toString();
	var output=column_names.split(/[\s,]+/);
	var columnname1=output[output.length-1];
	$("#subcategory").append("<option value='"+columnname1+":"+sub_category+"' >"+sub_category+"</option>");
	$("div#BoxesDiv"+counter).remove();
		$("#OptionAB"+m).remove();
	}
	var i=0;
	$("select").each(function(){
 var id=$(this).attr("id2");
 if(id!=undefined)
	 {
	 $(this).attr("id2",i);
	 i++;
}
	});
	if(!$("div#finalGrid").children().length>0)
	{

items.length=0;
itemsOutput.length=0;
	}
}

function CHANGE_SETTINGS(){
	var cm;
	var myGrid = $("#ResultGrid");
	var selected = new Array();
	$('#settings input:checked').each(function() {
		selected.push($(this).attr('id'));
	});
	var notselected = new Array();
	$('#settings "input:checkbox:not(:checked)"').each(function() {
		notselected.push($(this).attr('id'));
	});
     
	var getColumnSrcIndexByName = function(grid,columnName) {

		cm = grid.jqGrid('getGridParam','colModel');

		if(typeof cm =='undefined'){
			return -1;
		}

		var	i=0;
		var index=0;
		var l=cm.length;
		var cmName;
		while (i<l) {
			cmName = cm[i].name;
			i++;
			if (cmName===columnName) {
				return index;
			} else if (cmName!=='rn' && cmName!=='cb' && cmName!=='subgrid') {
				index++;
			}
		}


		//}
		return -1;	
	};

	for ( var i = 0; i < notselected.length; i++) {
		var index = getColumnSrcIndexByName($("#ResultGrid"),notselected[i]);
		
		if(index!=-1){
		var colPos=index+1;
		myGrid.jqGrid('hideCol', myGrid.getGridParam("colModel")[colPos].name);
		}

	}

	for ( var i = 0; i < selected.length; i++) {
			var index = getColumnSrcIndexByName($("#ResultGrid"),selected[i]);
			if(index !=-1){
			var colPos=index+1;
			if(index!=0){
				myGrid.jqGrid('showCol', myGrid.getGridParam("colModel")[colPos].name);
			}
		}
	}
	//}
}
function ExportExcel() {
	var test={};
	var JSon1 = new Array();
	var cols = new Array();
	var mydata;
	mya1 = $("#ResultGrid").getDataIDs();
	var data = $("#ResultGrid").getRowData(mya1[0]);   // Get First row to get the labels
	var selected = new Array();
	$('#settings input:checked').each(function() {
		selected.push($(this).attr('id'));
	});
	var colNames=new Array(); 
	var ii=2;
	colNames[0]="name";
	colNames[1]="level";
	for (var i in data) {
		for ( var j = 0; j < selected.length; ++j) {
			if(i==selected[j]){
				colNames[ii++] = i;
			}
		}
	}    
	var mya=new Array();
	mya=$("#ResultGrid").jqGrid('getGridParam','_index');
	var id='';
	for (id in mya) {
		if (mya.hasOwnProperty(id)) {
			mydata = $("#ResultGrid").jqGrid('getGridParam','data');
		}
	}
	jQuery.each(mydata, function(index, val) {
		var aa=val;
		var JSon= new Array();
		jQuery.each(aa, function(k, v){
			for(var m=0; m<colNames.length; m++) {
				var colname=colNames[m];
				if(k==colname){
					value = v;
					JSon.push(value);
					if ( (jQuery.inArray(k,cols))==-1) {
						cols.push(k);
					}

				}

			}

		});
		JSon1.push(JSon);
	});	
	test["columns"]=cols;
	test["rows"]= JSon1;
	test["columnnames"] = colNames;
	inputData = JSON.stringify(test);

}

function settingArray(){
	
	if($("#rural").is(":checked")){
		webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/r/Y/N/N ";
		$.ajax({  
			type:"GET",  
			url:webMethod,
			Accept: "application/json",
			async: false,
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
			success: function(resp){  
				$("#category").append("<option value=''>Select</option>"); 
				var markers =JSON.stringify(resp.Category.categories);
				var obj = $.parseJSON(markers);
				$("#settings").empty();
				$.each(obj, function() {     
					var categoryname=this.cat_name;
					var categoryid=this.cat_id; 
					var catalt=this.cat_alt; 
					$("#settings").append("<div class='categorysetting'><input type='checkbox' value='"+categoryid+"' id='"+catalt+"'>"+categoryname+"</div>");
				}); 
			},
			error: function(e){
				alert("error"+e);
			}
		});
		$("#tot_p").attr("checked","checked");
		$("#education").attr("checked","checked");
		$("#tot_irr").attr("checked","checked");
		$("#hospital").attr("checked","checked");
		$("#power").attr("checked","checked");
		loadCheckedCategory();
		checkedCategory();
	}
	if($("#urban").is(":checked")){
		webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/u/Y/N/N ";
		$.ajax({  
			type:"GET",  
			url:webMethod,
			Accept: "application/json",
			async: false,
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
			success: function(resp){  
				$("#category").append("<option value=''>Select</option>"); 
				var markers =JSON.stringify(resp.Category.categories);
				var obj = $.parseJSON(markers);
				$("#settings").empty();
				$.each(obj, function() {     
					var categoryname=this.cat_name;
					var categoryid=this.cat_id; 
					var catalt=this.cat_alt; 
					$("#settings").append("<div class='categorysetting'><input type='checkbox' value='"+categoryid+"' id='"+catalt+"'>"+categoryname+"</div>");
				}); 
			},
			error: function(e){
				alert("error"+e);
			}
		});
		$("#tot_p").attr("checked","checked");
		$("#education").attr("checked","checked");
		$("#tot_irr").attr("checked","checked");
		$("#hospital").attr("checked","checked");
		$("#power").attr("checked","checked");
		loadCheckedCategory();
		checkedCategory();

	}
	if($("#both").is(":checked")){
		var selected = $("input[type='radio'][name='segment']:checked");
		//alert(selected);
		if (selected.length > 0)
			sValue = selected.val();
		if(stdisttemp!="")
		{
		webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/"+sValue+"/Y/N/N ";
		}
	else{
webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/d/Y/N/N ";}
		
		$.ajax({  
			type:"GET",  
			url:webMethod,
			Accept: "application/json",
			async: false,
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
			success: function(resp){  
				$("#category").append("<option value=''>Select</option>"); 
				var markers =JSON.stringify(resp.Category.categories);
				var obj = $.parseJSON(markers);
				$("#settings").empty();
				$.each(obj, function() {     
					var categoryname=this.cat_name;
					var categoryid=this.cat_id; 
					var catalt=this.cat_alt;		
					$("#settings").append("<div class='categorysetting'><input type='checkbox' value='"+categoryid+"' id='"+catalt+"'>"+categoryname+"</div>"); 
				}); 
			},
			error: function(e){
				alert("error"+e);
			}
		});
	}
	$("#hospital").attr("checked","checked");
	$("#tot_work_p").attr("checked","checked");
	$("#tot_p").attr("checked","checked");
	loadCheckedCategory();
	checkedCategory();
}
function checkedCategory(){
	selectedCate = new Array();
	$('#settings input:checked').each(function() {
		selectedCate.push($(this).attr('value'));
		
	});
}
function loadCategoryOnSubmit(){
	$("#category").empty();
	var value;
	if($("#urban").is(":checked")){
		if(stdisttemp!="")
		{
		value='u';
		}
		else{
			value='d';
		}
	}
	if($("#rural").is(":checked")){
		if(stdisttemp!="")
		{
		value='r';
		}
		else{
			value='d';
		}
	}
	if($("#both").is(":checked")){
		if(stdisttemp!="")
		{
		value='b';
		}
		else{
			value='d';
		}
	}
		var isStateSelected="Y";
		webMethod = CXFServiceURL+"CategoryDetailService/MARS/CategoryDetails/"+value+"/"+isStateSelected+"/N/N ";
		$.ajax({  
			type:"GET",  
			url:webMethod,
			Accept: "application/json",
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
			success: function(resp){  
				var markers =JSON.stringify(resp.Category.categories);
				var obj = $.parseJSON(markers);

				$("#category").append("<option value=''>Select</option>");
				$.each(obj, function() {     
					var categoryname=this.cat_name;
					var categoryid=this.cat_id;
					for ( var i = 0; i < selectedCate.length; ++i) {
						if(categoryid== selectedCate[i]){

							$("#category").append("<option value='"+ categoryid+"'>"+categoryname+"</option>");
						}
					}	
				}); 
			},
			error: function(e){
			}
		});
	
}
