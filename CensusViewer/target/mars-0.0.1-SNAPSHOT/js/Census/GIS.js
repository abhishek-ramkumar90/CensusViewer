var map,bounds,State, District, Tehsil, Towns, Village,Airports,Sea,Water_polygon,Rail_network,Roads,Water_line,Areanames,Layer_hilight,towns, village, latlon, lat, lon, popup,markers,layers,temlayer,str,gid;
var tempgid='',id,level,name,statecode,jsonSting,MARSURL,MapServiceURL,MapFileURL,statename,Name,newtemplayer,CXFServiceURL,CONTEXT_ROOT;
var tot_p,tot_m,tot_f,p_lit_per, m_lit_per,f_lit_per,p_lit,m_lit,f_lit,webMethod,inputcol,idn,layrval,count,response;
var no_of_comm_inst,no_of_comm_mode,no_of_transport_mode,no_of_watersrc,tot_exp,tot_inc,tot_rec,tot_work_p,strG,service,pap_mag_src;
var maparr=[],layerss,temp_layerss;
var Branch=null;
var Region=null;
var Territory=null;
var highlighted=null;
var vectorLayer;
//function layer_click(val){
//alert("val===="+val);
//temp_layerss=temp_layerss+','+val;
//MAP_INIT();
//}

function DESTROY_DISTRICT_REGION()
{
	if(Layer_hilight!=''&& Layer_hilight!=undefined && Layer_hilight !=null){
		Layer_hilight.destroy();
		tempgid='';
	}
}
function RefreshBranch(){
	if(Branch!=''&& Branch!=undefined && Branch !=null){
		Branch.redraw(true);
	}
}
function RefreshRegion(){
	if(Region!=''&& Region!=undefined && Region !=null){
		Region.redraw(true);
	}
}
function RefreshTerritory(){
	if(Territory!=''&& Territory!=undefined && Territory !=null){
		Territory.redraw(true);
	}
}

function RefreshVector(){
	vectorLayer.destroyFeatures();
}


function MAP_INIT(){
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
								MapServiceURL = $(this).find('MapServiceURL').text();
								MapFileURL = $(this).find('MapFileURL').text();
								CXFServiceURL=$(this).find('CXFServiceURL').text();
								MARSURL = $(this).find('MARSURL').text();
								CONTEXT_ROOT = $(this).find('CONTEXT_ROOT').text();
							}
					);
				}
			}
	);
	if(regionMap==true){
		NProgress.start();
		map.zoomToScale(3460498.76953125,true);
		//alert("Region Map....");
		if(District!=''&& District!=undefined && District !=null){
	
			District.destroy();
		}
		//District.destroy();
		if(Branch!=''&& Branch!=undefined && Branch !=null){
			Branch.destroy();
		}
		if(Territory!=''&& Territory!=undefined && Territory !=null){
			Territory.destroy();
		}
		if(Layer_hilight!=''&& Layer_hilight!=undefined && Layer_hilight !=null){
			Layer_hilight.destroy();
			tempgid='';
		}
		if(Region!=''&& Region!=undefined && Region !=null){
			Region.redraw(true);
		}

		if(Region ==''|| Region== undefined || Region ==null){
			NProgress.set(0.5);
		layerss='District';
		temp_layerss='Region';
		layerss=layerss+','+temp_layerss;
		Region = new OpenLayers.Layer.WMS("Region Layer",
				MapServiceURL, { layers: layerss, 'format': 'png'},
				{ isBaseLayer: false,opacity:0.8,singleTile: true
				} );
		NProgress.set(0.7);
		map.addLayer(Region);
		NProgress.done();
		}
		//alert("done region!!!!");
}

	if(branchOkFlag==true ){
		map.zoomToScale(3460498.76953125,true);
		
		if(vectorLayer!=''&& vectorLayer!=undefined && vectorLayer !=null){
		RefreshVector();
		}
		
		if(District!=''&& District!=undefined && District !=null){
			District.destroy();
		}
		if(Region!=''&& Region!=undefined && Region !=null){
			Region.destroy();
		}
		if(Territory!=''&& Territory!=undefined && Territory !=null){
			Territory.destroy();
		}
		if(Layer_hilight!=''&& Layer_hilight!=undefined && Layer_hilight !=null){
			Layer_hilight.destroy();
			tempgid='';
		}
		if(Branch!=''&& Branch!=undefined && Branch !=null){
			Branch.redraw(true);
		}
		if(Branch ==''|| Branch== undefined || Branch ==null){
		layerss='District';
		temp_layerss='Branch';
		layerss=layerss+','+temp_layerss;
		Branch = new OpenLayers.Layer.WMS("Branch",
				MapServiceURL, { layers: layerss, 'format': 'png'},
				{
					isBaseLayer: false,opacity:0.8,singleTile: true
				} );
		map.addLayer(Branch);
		//Branch.refresh();
		}
}if(territoryMap==true){
/*	map.zoomToScale(156200,true);
	alert("MapFileURL"+MapFileURL);*/
	
 
	if(District!=''&& District!=undefined && District !=null){
		District.destroy();
	}
	
	if(Region!=''&& Region!=undefined && Region !=null){
		Region.destroy();
	}
	if(Branch!=''&& Branch!=undefined && Branch !=null){
		Branch.destroy();
	}
	if(Layer_hilight!=''&& Layer_hilight!=undefined && Layer_hilight !=null){
		Layer_hilight.destroy();
		tempgid='';
	}
	if(Territory!=''&& Territory!=undefined && Territory !=null){
		Territory.redraw(true);
	}
	if(Territory =='' || Territory== undefined || Territory ==null){
	//Territory=null;
	layerss='Village';
	temp_layerss='Territory,Towns';
	layerss=layerss+','+temp_layerss;
	Territory = new OpenLayers.Layer.WMS("Territory",
			MapFileURL, { layers: layerss, 'format': 'png'},
			{
				isBaseLayer: false,opacity:0.8,singleTile: true
			} );
		map.addLayer(Territory);	
	 vectorLayer = new OpenLayers.Layer.Vector("Overlay");
	 for(var i=0;i<latArray.length;i++)
		 {
		 var lat=latArray[i];
		 var long=longArray[i];
		 addMarker(lat,long);
		 }
/*	 addMarker(79.982118359375,18.917075273438);
	 addMarker(80.23480390625,18.996726152344);*/

	 
	/*	var feature = new OpenLayers.Feature.Vector(
		 new OpenLayers.Geometry.Point(79.982118359375,18.917075273438),
		 {
				isBaseLayer: false,opacity:0.1,singleTile: true,transparent:true
			},
		 {externalGraphic: 'img/marker.png', graphicHeight: 21, graphicWidth: 16});
		var feature2 = new OpenLayers.Feature.Vector(
				 new OpenLayers.Geometry.Point(80.23480390625,18.996726152344),
				 {
						isBaseLayer: false,opacity:0.1,singleTile: true,transparent:true
					},
				 {externalGraphic: 'img/marker.png', graphicHeight: 21, graphicWidth: 16});
		vectorLayer.addFeatures(feature);
		vectorLayer.addFeatures(feature2);
		map.addLayer(vectorLayer);
		 var lonLat = new OpenLayers.LonLat(79.982118359375,18.917075273438)
	       .transform(
	         new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
	         map.getProjectionObject() // to Spherical Mercator Projection
	       );
		 var zoom=7;*/
		// map.setCenter (lonLat, zoom);
	}
	
	
	
	
	
	

	//alert("done!!!!");
}
		
	if(branchOkFlag==false && regionMap == false && territoryMap==false ){
		//alert("false...."+MapServiceURL);
		map = new OpenLayers.Map('map');
		var wms = new OpenLayers.Layer.WMS(
				"India",
				"http://vmap0.tiles.osgeo.org/wms/vmap0",
				{'layers':'basic'},
				{
					singleTile: false,
					numZoomLevels: 21
				}
		);
		// MapServiceURL='http://172.16.1.7:8888/cgi-bin/mapserv.exe?map=D:/Mars/Mars.map';
		layerss='District';
		temp_layerss='Tehsil,Towns,Village,rail_network,areanames,shipping_port,roads_NonHighway,Roads_Highway,airports';
//		if(temp_layerss==''||temp_layerss==null||temp_layerss==undefined){
//		layerss=layerss;
//		}else{ layerss=layerss+','+temp_layerss;}	
		layerss=layerss+','+temp_layerss;
		bounds = new OpenLayers.Bounds(71.91815,21.91085,82.53095,14.96749);
		District = new OpenLayers.Layer.WMS("Layer1111",
				MapServiceURL, { layers: layerss, 'format': 'png'},
				{ isBaseLayer: false,opacity:0.8,singleTile: false

				} );
		map.addLayers([wms,District]);		
		if (!map.getCenter()) map.zoomToMaxExtent();
		map.zoomToExtent(bounds, false);


		map.addControl(new OpenLayers.Control.PanZoomBar());
		map.addControl(new OpenLayers.Control.LayerSwitcher({'ascending': true}));
		map.addControl(new OpenLayers.Control.MousePosition());
		map.addControl(new OpenLayers.Control.Scale());
		map.addControl(new OpenLayers.Control.ScaleLine());
		map.addControl(new OpenLayers.Control.KeyboardDefaults());
		//map.setCenter(new OpenLayers.LonLat(78.4989639062,20.081630234375),6);
		//$('.olControlPanZoomBar div').addClass('olButton');
		$('.olControlLayerSwitcher').addClass('olButton');
		$(".myClass").click(function(e) {
			e.stopPropagation();
		});
		
		map.events.register('click', map, findMapClick);

	}


}	

function addMarker(lat,long)
{
	var feature = new OpenLayers.Feature.Vector(
			 new OpenLayers.Geometry.Point(lat,long),
			 {
					isBaseLayer: false,opacity:0.1,singleTile: true,transparent:true
				},
			 {externalGraphic: 'img/marker.png', graphicHeight: 21, graphicWidth: 16});
	vectorLayer.addFeatures(feature);
	map.addLayer(vectorLayer);
	 var lonLat = new OpenLayers.LonLat(lat,long)
     .transform(
       new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
       map.getProjectionObject() // to Spherical Mercator Projection
     );
	 var zoom=12;
	 map.setCenter (lonLat, zoom);1

}
/*function findMapClickAlert(event){
	//updateRegionStateDist(selectedDist,selectedState,resp);
	if(mapColorFlag==false){
		alert("mapColorFlag");
		alert("District does not belong to the Region Selected");
	}else{
		findMapClick(event);
	}
}*/
function findMapClick(event) {
	
	
	
	var responseDist;
	var responseState;
	var responseData;
	if(branchOkFlag==true){
		temlayer="District";
	}
	if(territoryMap==true)
		{
		temlayer="Village";
		//mapColorFlag=true;
		}
	else{
	ok_flag=false;
	temlayer='';
	var scal=map.getScale();
	if(scal<"300000000" && scal>"3460000"){
		temlayer="District";	
	}                                 
	else if(scal<"3460001" && scal>"865000"){
		temlayer="Tehsil";	
	}
	else if(scal<"865001" && scal>"216200"){
		temlayer="Towns";	
	}								
	else if(scal<"216201" && scal>="50"){
		temlayer="Village";	
	}
	}
	latlon = map.getLonLatFromPixel(event.xy);
	var XY = latlon.toString().split(',');
	var tempLat = XY[1].toString().split('=');
	var tempLon = XY[0].toString().split('=');
	var LatN=tempLat.toString().split(',');
	var LonN=tempLon.toString().split(',');
	inputcol="{'lat':'"+LatN[1]+"','lon':'"+LonN[1]+"','layer':'"+temlayer+"'}";
	webMethod =CXFServiceURL+"MapService/"+temlayer+"/"+inputcol; 
	str=null;
	name='';
	str = '<div id=mydiv>';
	str += '<table cellpadding="3" cellspacing="0" style="font-family:Verdana; font-size:xx-small;">';
	str += '<tr><td colspan="2" style="font-size:x-small;color:Blue;font-weight:bold">Attributes</td><td colspan="2" style="font-size:x-small;color:Blue;font-weight:bold">Value</td><tr>';
	$.ajax({
		type:'GET',
		url:webMethod,	
		Accept: "application/json",
		async: false,
		dataType: "json",
		success: function(resp) {
			var markers =JSON.stringify(resp);
			var json_parsed = $.parseJSON(markers);
			responseDist = resp.AutoComplete.distcode;
			responseState=resp.AutoComplete.statecode;
			responseData = resp;
			response=resp.AutoComplete;
			maparr[count]=response;
			id=json_parsed.AutoComplete.gid;
			level=json_parsed.AutoComplete.level;
			Name=json_parsed.AutoComplete.name;
			statecode=json_parsed.AutoComplete.statecode;
			tot_p=json_parsed.AutoComplete.tot_p;
			tot_irr=json_parsed.AutoComplete.tot_irr;
			power=json_parsed.AutoComplete.power;
			education=json_parsed.AutoComplete.education;
			health=json_parsed.AutoComplete.hospital;
			no_of_comm_inst=json_parsed.AutoComplete.no_of_comm_inst;
			no_of_comm_mode=json_parsed.AutoComplete.no_of_comm_mode;
			no_of_transport_mode=json_parsed.AutoComplete.no_of_transport_mode;
			no_of_watersrc=json_parsed.AutoComplete.no_of_watersrc;
			tot_exp=json_parsed.AutoComplete.tot_exp;
			tot_inc=json_parsed.AutoComplete.tot_inc;
			tot_rec=json_parsed.AutoComplete.tot_rec;
			tot_work_p=json_parsed.AutoComplete.tot_work_p;
			service=json_parsed.AutoComplete.service;
			pap_mag_src=json_parsed.AutoComplete.pap_mag_src;

			str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Name </font></b></td>';
			str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +Name+ '</font></td></tr>';

			str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Level </font></b></td>';
			str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +level+ '</font></td></tr>';

			if(document.getElementById('both').checked){

				if(document.getElementById('tot_p').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Population </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_p+ '</font></td></tr>';
				}

				if(document.getElementById('hospital').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Number of Hospital </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +health+ '</font></td></tr>';

				}

				if(document.getElementById('tot_work_p').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Workers </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_work_p+ '</font></td></tr>';

				}



				if(document.getElementById('no_of_transport_mode').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b> Means of Transport </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_transport_mode+ '</font></td></tr>';

				}
				if(document.getElementById('no_of_watersrc').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Number of Drinking Water Sources </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_watersrc+ '</font></td></tr>';

				}
				if(document.getElementById('pap_mag_src').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Types of Media </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +pap_mag_src+ '</font></td></tr>';
				}
				if(document.getElementById('education').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Education Institutions </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +education+ '</font></td></tr>';

				}

				if(document.getElementById('no_of_comm_inst').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Commercial</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_comm_inst+ '</font></td></tr>';

				}
				if(document.getElementById('tot_exp').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Government Expendicture</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_exp+ '</font></td></tr>';

				}
				if(document.getElementById('tot_inc').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Government Income</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_inc+ '</font></td></tr>';

				}
				if(document.getElementById('tot_irr').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Irrigation</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_irr+ '</font></td></tr>';

				}

				if(document.getElementById('service').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Types of Public Services</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +service+ '</font></td></tr>';
				}




			}
			else if(document.getElementById('urban').checked){

				if(document.getElementById('tot_p').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Population </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_p+ '</font></td></tr>';
				}


				if(document.getElementById('tot_irr').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Irrigated Area </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_irr+ '</font></td></tr>';

				}
				if(document.getElementById('power').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Electric Connections</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +power+ '</font></td></tr>'; 
				}

				if(document.getElementById('education').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Education Institutions </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +education+ '</font></td></tr>';

				}

				if(document.getElementById('hospital').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Number of Hospital </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +health+ '</font></td></tr>';

				}

				if(document.getElementById('no_of_comm_inst').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Number of Financial Institutions </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_comm_inst+ '</font></td></tr>';

				}
				if(document.getElementById('entertain').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Number of Entertainment centers </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +health+ '</font></td></tr>';

				}

				if(document.getElementById('tot_work_p').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Workers </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_work_p+ '</font></td></tr>';

				}
				if(document.getElementById('no_of_transport_mode').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b> Means of Transport </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_transport_mode+ '</font></td></tr>';

				}
				if(document.getElementById('tot_exp').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Annual Expanditure </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_exp+ '</font></td></tr>';

				}

				if(document.getElementById('tot_inc').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Annual Income </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_inc+ '</font></td></tr>';

				}

				if(document.getElementById('service').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Types of Public Services</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +service+ '</font></td></tr>';
				}


			}
			
			else if(document.getElementById('rural').checked){

				if(document.getElementById('tot_p').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Population </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_p+ '</font></td></tr>';
				}


				if(document.getElementById('tot_irr').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Irrigated Area </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +tot_irr+ '</font></td></tr>';

				}
				if(document.getElementById('power').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Electric Connections</font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +power+ '</font></td></tr>'; 
				}

				if(document.getElementById('education').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Education Institutions </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +education+ '</font></td></tr>';

				}

				if(document.getElementById('hospital').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Number of Hospital </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +health+ '</font></td></tr>';

				}


				if(document.getElementById('no_of_comm_inst').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Number of Financial Institutions </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_comm_inst+ '</font></td></tr>';

				}

				if(document.getElementById('no_of_watersrc').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Number of Drinking Water Sources </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_watersrc+ '</font></td></tr>';

				}
				if(document.getElementById('entertain').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Total Number of Entertainment centers </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +health+ '</font></td></tr>';

				}


				if(document.getElementById('no_of_transport_mode').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b> Means of Transport </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_transport_mode+ '</font></td></tr>';

				}
				if(document.getElementById('no_of_comm_mode').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Means of Communication </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +no_of_comm_mode+ '</font></td></tr>';

				}

				if(document.getElementById('pap_mag_src').checked){ 
					str += '<tr><td style="font-family:Verdana"; colspan=2><font color="black"><b>Types of Media </font></b></td>';
					str += '<td colspan=2; style="font-family:Verdana"><font color="black">' +pap_mag_src+ '</font></td></tr>';
				}


			}

			str += '</table>';
			str += '</div>';
			updateRegionStateDist(responseDist,responseState,responseData);
			if(mapColorFlag==false){
				makePopup();
			}
			
				else{
			
				fillSolrResultGrid(resp,'true');
				$('.resultBlock').css('display', 'block');
				$('.olControlScale, .olControlScaleLine').css('bottom', '294px');
				$('.olControlMousePosition').css('bottom', '267px');

			}

		},
		error: function(e){
			alert("No Feature Found");

		}
	});
	//updateRegionStateDist(responseDist,responseState,responseData);	 


}	
function makePopup(){
	if(str!=null && str!=''&& str!=undefined){
		if(str.length>300){
			if (popup != null) {
				popup.destroy();
			}
			popup = new OpenLayers.Popup.FramedCloud(
					"chicken",
					latlon,
					null,
					str,
					null,
					true
			);
			latlon = null;
			popup.autoSize = true;
			str='';
			map.addPopup(popup); 	
		}
	}
}
function showlayer(idn,layrval) {
	temlayer=layrval;
	if(str!=null && str!=''&& str!=undefined){
		if(str.length>300){
			if (popup != null) {
				popup.destroy();
			}
			popup = new OpenLayers.Popup.FramedCloud(
					"chicken",
					latlon,
					null,
					str,
					null,
					true
			);
			latlon = null;
			popup.autoSize = true;
			str='';
			map.addPopup(popup); 	
		}
	}
	tempgid=idn;
	newtemplayer=temlayer;	

	if(Layer_hilight!=''&& Layer_hilight!=undefined && Layer_hilight !=null){
		unhighlightWMS();
		highlightWMS(tempgid,layrval);

	}
	else{

		highlightWMS(tempgid,layrval);
	}  

}
function mapzoomGIS(gid,lat,log,level,dis){
	if(dis=='true'){
		if(level=='"DISTRICT"'||level=="DISTRICT"){
			showlayer(gid+',',"District");
		}
		if(level=='"TEHSIL"'||level=="TEHSIL"){
			showlayer(gid+',',"Tehsil");
		}
		if(level=='"TOWN"'||level=="TOWN"){
			showlayer(gid+',',"Towns");

		}
		if(level=='"village"'||level=="village"){
			showlayer(gid+',',"Village");

		}	
	}/*else if(branchOkFlag==true){
		showlayer(gid+',',"District");
	}
	*/else{
		if(level=='"DISTRICT"'||level=="DISTRICT"){
			showlayer(gid+',',"District");
			map.setCenter(new OpenLayers.LonLat(lat,log),7);
		}
		if(level=='"STATE"'||level=="STATE"){
			showlayer(gid+',',"State");
			map.setCenter(new OpenLayers.LonLat(lat,log),7);
		}

		if(level=='"TEHSIL"'||level=="TEHSIL"){
			showlayer(gid+',',"Tehsil");
			map.setCenter(new OpenLayers.LonLat(lat,log),8);
		}
		if(level=='"TOWN"'||level=="TOWN"){
			showlayer(gid+',',"Towns");
			map.setCenter(new OpenLayers.LonLat(lat,log),10);
		}
		if(level=='"village"'||level=="village"){
			showlayer(gid+',',"Village");
			map.setCenter(new OpenLayers.LonLat(lat,log),13);

		}

	}

}

function highlightWMS(idn,layrval) {
	strG =  idn.split(',');
	var wms_url= MapServiceURL+"&singleTile:true,";
	sld = '<?xml version="1.0" encoding="utf-8"?>';
	sld+= '<sld:StyledLayerDescriptor version="1.0.0">';
	sld+= '<sld:NamedLayer><sld:Name>'+layrval+'</sld:Name><sld:UserStyle><sld:Name>'+layrval+'</sld:Name><sld:FeatureTypeStyle><sld:Rule>';
	sld+= '<ogc:Filter >';
	sld+= '<Or>';
	for(var m=0;m<strG.length;m++){
		sld+= '<ogc:PropertyIsEqualTo>';
		sld+= '<ogc:PropertyName>gid</ogc:PropertyName>';
		sld+= '<ogc:Literal>'+strG[m]+'</ogc:Literal>';
		sld+= '</ogc:PropertyIsEqualTo>';
	}
	sld+= '</Or></ogc:Filter>';
	sld+= '<sld:PolygonSymbolizer><sld:Fill><sld:CssParameter name="fill">#B22222</sld:CssParameter><sld:CssParameter name="fill-opacity">0.8</sld:CssParameter></sld:Fill>';
	sld+= '<sld:Stroke><sld:CssParameter name="stroke">#B22222</sld:CssParameter><sld:CssParameter name="stroke-opacity">0.8</sld:CssParameter><sld:CssParameter name="stroke-width">2</sld:CssParameter></sld:Stroke></sld:PolygonSymbolizer>';
	sld+= '</sld:Rule>';
	sld+= '</sld:FeatureTypeStyle></sld:UserStyle></sld:NamedLayer></sld:StyledLayerDescriptor>';					
	Layer_hilight = new OpenLayers.Layer.WMS(
			"HighlightWMS",
			wms_url,
			{layers: layrval,
				transparent: true
			},
			{
				visibility: false,
				isBaseLayer: false,
				opacity: 0.7,
				singleTile: true
			}
	);
	map.addLayers([Layer_hilight]);
	Layer_hilight.mergeNewParams({sld_body:sld});
	Layer_hilight.setVisibility(true);
}
function addHighlightLayer(){
	
}
function highlighChecked(ids,layrval) {
	var i;
	if(highlighted!=''&& highlighted!=undefined && highlighted !=null  ){
		highlighted.destroy();
	}
		var wms_url= MapServiceURL+"&singleTile:true,";
	sld = '<?xml version="1.0" encoding="utf-8"?>';
	sld+= '<sld:StyledLayerDescriptor version="1.0.0">';
	sld+= '<sld:NamedLayer><sld:Name>'+layrval+'</sld:Name><sld:UserStyle><sld:Name>'+layrval+'</sld:Name><sld:FeatureTypeStyle><sld:Rule>';
	sld+= '<ogc:Filter >';
	if(ids.length == 1){
		sld+= '<ogc:PropertyIsEqualTo>';
		sld+= '<ogc:PropertyName>gid</ogc:PropertyName>';
		sld+= '<ogc:Literal>'+ids[0]+'</ogc:Literal>';
		sld+= '</ogc:PropertyIsEqualTo>';
	}
	if(ids.length >1){
		sld+= '<Or>';
	for(i=0;i<ids.length;i++){
		sld+= '<ogc:PropertyIsEqualTo>';
		sld+= '<ogc:PropertyName>gid</ogc:PropertyName>';
		sld+= '<ogc:Literal>'+ids[i]+'</ogc:Literal>';
		sld+= '</ogc:PropertyIsEqualTo>';
	}	
	sld+= '</Or>';
	}
	sld+= '</ogc:Filter>';
	sld+= '<sld:PolygonSymbolizer><sld:Fill><sld:CssParameter name="fill">#B22222</sld:CssParameter><sld:CssParameter name="fill-opacity">0.8</sld:CssParameter></sld:Fill>';
	sld+= '<sld:Stroke><sld:CssParameter name="stroke">#B22222</sld:CssParameter><sld:CssParameter name="stroke-opacity">0.8</sld:CssParameter><sld:CssParameter name="stroke-width">2</sld:CssParameter></sld:Stroke></sld:PolygonSymbolizer>';
	sld+= '</sld:Rule>';
	sld+= '</sld:FeatureTypeStyle></sld:UserStyle></sld:NamedLayer></sld:StyledLayerDescriptor>';	
		highlighted = new OpenLayers.Layer.WMS(
			"HighlightSingle",
			wms_url,
			{layers: layrval,
				transparent: true
			},
			{
				visibility: false,
				isBaseLayer: false,
				opacity: 0.7,
				singleTile: true
			}

	);

	map.addLayers([highlighted]);
	highlighted.mergeNewParams({sld_body:sld});
	highlighted.setVisibility(true);
}
function unhighlightChecked(){
	if(highlighted!=''&& highlighted!=undefined && highlighted !=null){
		highlighted.destroy(true);
	}	
}
function unhighlightWMS() {
	if(Layer_hilight!=''&& Layer_hilight!=undefined && Layer_hilight !=null){
	Layer_hilight.destroy();
	}
}
function destroyHighlight(){
	if(highlighted!=''&& highlighted!=undefined && highlighted !=null){
		highlighted.destroy(true);
	}
	if(Layer_hilight!=''&& Layer_hilight!=undefined && Layer_hilight !=null){
		Layer_hilight.destroy(true);
		}
}
function zoomToDist(){
	map.zoomToScale(3460000,true);
	//alert("zoomed to dist");
}
