/*  jQgrid - jQuery Data Grid v0.4
 *  jQgrid is distributed under the terms of the MIT license
 *  For more information visit http://jqframework.com/jqgrid
 *  Copyright (C) 2009  jqframework.com
 * Do not remove this copyright message
 */
var idlist1 = new Array();
var idlist2 = new Array();
var stdisttemp = ""; 
var stcodetemp = "";
var currentCheckId = "";

remove_item = function(arr,value){
    for(b in arr ){
        if($.trim(arr[b]) == $.trim(value)){
            arr.splice(b,1);
            break;
        }
    }
    return arr;
}

currentID = function(obj) {
	
	currentCheckId = obj.getAttribute("rowid");
}

insertID = function(obj) {
	
	var currid = obj.getAttribute("rowid");
	var flagcode = false;
	statusFlag = obj.id;
	
	if(currid.indexOf(":") == -1) {
		if(stcodetemp != "" && document.getElementById(obj.id).checked) {
			var stcodetemparr = stcodetemp.split(",");
			for(var k=0; k< stcodetemparr.length; k++) {
				if($.trim(stcodetemparr[k]) ==  $.trim(currid)) {
					flagcode = true;
					break;
				}
			}
			
			if(flagcode == false) {
				stcodetemp = stcodetemp + "," + currid;
				flagcode = true;
			}
		} else if(stcodetemp != "" && !document.getElementById(obj.id).checked) {
			
			var stcodetemparr = stcodetemp.split(",");
			stcodetemp ="";
			for(var k=0; k< stcodetemparr.length; k++) {
				if($.trim(stcodetemparr[k]) !=  $.trim(currid)) {
					stcodetemp = stcodetemparr[k] + "," + stcodetemp;
				}
			}
			stcodetemp = stcodetemp.substring(0,stcodetemp.length-1);
			
		} else {
			stcodetemp = currid;
		}
	} else {
		if(stdisttemp != "" && document.getElementById(obj.id).checked) {
			var stdisttemparr = stdisttemp.split(",");
			for(var k=0; k< stdisttemparr.length; k++) {
				if($.trim(stdisttemparr[k]) ==  $.trim(currid)) {
					flagcode = true;
					break;
				}
			}
			
			if(flagcode == false) {
				stdisttemp = stdisttemp + "," + currid;
			}
		} else if(stdisttemp != "" && !document.getElementById(obj.id).checked) {
			var stdisttemparr = stdisttemp.split(",");
			var tempflag1 = false;
			stdisttemp ="";
			for(var k=0; k< stdisttemparr.length; k++) {
				if($.trim(stdisttemparr[k]) !=  $.trim(currid)) {
					stdisttemp =  stdisttemparr[k] + "," + stdisttemp;
					tempflag1 = true;
				}
				
			}
			stdisttemp = stdisttemp.substring(0,stdisttemp.length-1);
		} else {
			stdisttemp = currid;
		}
	}
}

$.jQgrid = {
    imgPath: "themes/default/images",
    checkitems: {},
    sortItemOrder: {},
    sortitems: {},
    lastcheck: {},
    cols: {},
    limitOpt: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50],
    language: {
        pre_total: "total",
        post_total: "rows",
        create: "create record",
        update: "update record",
        "delete": "delete record(s)",
        copy: "copy record",
        search: "search record(s)",
        refresh: "refresh",
        excelimport: "excel import",
        excelexport: "excel export",
        msupdate: "mass update"
    },
    gridButton: ["search", "refresh", "create", "update", "msupdate", "copy", "delete", "excelimport", "excelexport"],
    clickRow: function (obj, column, rowid) {
        var dataobj = {};
        dataobj.id = obj;
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.action = "clickrow";
        dataobj.value = rowid;
        dataobj.parentid = $("#" + obj).jqgrid_getParentid();
        dataobj.maingrid = $("#" + obj + "_gridLoaderId").val();
        jQgridHandler(dataobj);
        /*$.jQgrid.selectRowItem(obj, rowid)*/
    },
    sortOrder: function (id, column, action, ord) {
        if ($.jQgrid.sortItemOrder[id] == null || $.jQgrid.sortItemOrder[id] == undefined) {
            $.jQgrid.sortItemOrder[id] = ""
        }
        var columns = $.jQgrid.sortItemOrder[id].split(",");
        $.jQgrid.sortItemOrder[id] = "";
        if (action == "remove") {
            for (var i = 0; i < columns.length; i++) {
                var item = columns[i].split(" ");
                if (item[0] != column) {
                    $.jQgrid.sortItemOrder[id] += "," + columns[i]
                }
            }
        } else {
            if (action == "add") {
                $.jQgrid.sortItemOrder[id] = "";
                var addCol = true;
                for (var i = 0; i < columns.length; i++) {
                    var item = columns[i].split(" ");
                    if (item[0] != "") {
                        if (item[0] == column) {
                            addCol = false;
                            $.jQgrid.sortItemOrder[id] += "," + column + " " + ord
                        } else {
                            $.jQgrid.sortItemOrder[id] += "," + columns[i]
                        }
                    }
                }
                if (addCol) {
                    $.jQgrid.sortItemOrder[id] += "," + column + " " + ord
                }
            }
        }
        $.jQgrid.sortItemOrder[id] = $.jQgrid.sortItemOrder[id].substr(1)
    },
    sortColumn: function (id, column) {
        if ($.jQgrid.sortitems[id] == null) {
            $.jQgrid.sortitems[id] = {}
        }
        var columns = column.split(",");
        for (var i = 0; i < columns.length; i++) {
            $.jQgrid.sortitems[id][columns[i]] = "y"
        }
    },
    cleanSortColumns: function (obj) {
        if ($.jQgrid.sortItemOrder[obj] == null) {
            $.jQgrid.sortItemOrder[obj] = ""
        }
        var columns = $.jQgrid.sortItemOrder[obj].split(",");
        for (var i = 0; i < columns.length; i++) {
            var content = $("#" + obj + "_hd_" + columns[i]).html();
            var pos = content.toLowerCase().indexOf("<img");
            content = $("#" + obj + "_hd_" + columns[i]).html().substr(0, pos);
            $("#" + obj + "_hd_" + columns[i]).html(content)
        }
        $.jQgrid.sortItemOrder[obj] = ""
    },
    sort: function (obj, column) {
        if ($.jQgrid.sortitems[obj][column] == "y") {
            var content = $("#" + obj + "_hd_" + column).html();
            var sorting = "";
            if (content.indexOf("gif") == -1) {
                sorting = "desc";
                content += ' <img src="' + $.jQgrid.imgPath + '/sort_desc.gif"/>';
                $("#" + obj + "_hd_" + column).html(content);
                $.jQgrid.sortOrder(obj, column, "add", "desc")
            } else {
                if (content.indexOf("sort_desc") != -1) {
                    sorting = "asc";
                    var pos = content.toLowerCase().indexOf("<img");
                    content = $("#" + obj + "_hd_" + column).html().substr(0, pos);
                    content += ' <img src="' + $.jQgrid.imgPath + '/sort_asc.gif"/>';
                    $("#" + obj + "_hd_" + column).html(content);
                    $.jQgrid.sortOrder(obj, column, "add", "asc")
                } else {
                    if (content.indexOf("sort_asc") != -1) {
                        sorting = "";
                        var pos = content.toLowerCase().indexOf("<img");
                        content = $("#" + obj + "_hd_" + column).html().substr(0, pos);
                        $("#" + obj + "_hd_" + column).html(content);
                        $.jQgrid.sortOrder(obj, column, "remove", "")
                    }
                }
            }
            var dataobj = {};
            dataobj.id = obj;
            dataobj.name = $.jQgrid.getName(obj);
            dataobj.action = "sort";
            dataobj.value = $.jQgrid.sortItemOrder[obj];
            jQgridHandler(dataobj)
        }
    },
    /*checkAllItems: function (obj) {
        $.each($("#" + obj + "_datagrid > tbody tr"), function (index, value) {
            var id = this.id.split("_");
            var selected = $("#checkall_" + obj).is(":checked");
            var rid = "";
            for (var i = 0; i < id.length; i++) {
                if (obj.indexOf(id[i]) == -1) {
                    rid += "_" + id[i]
                }
            }
            rid = rid.substr(1);
            if (selected && $("#check_" + obj + "_row_" + rid).is(":checked")) {
                $("#" + obj + "_" + rid).removeClass("clicked")
            } else {
                if (!selected && !$("#check_" + obj + "_row_" + rid).is(":checked")) {
                    $("#" + obj + "_" + rid).addClass("clicked")
                }
            }
            $("#check_" + obj + "_row_" + rid).attr("checked", selected);
            $.jQgrid.selectItem(obj, rid, selected)
        });
        var dataobj = {};
        dataobj.id = obj;
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.action = "checkall";
        dataobj.value = $("#" + obj).jqgrid_getChecked();
        jQgridHandler(dataobj)
    },*/
    checkAllItems1: function (obj) {
    	//alert("stdisttemp 1-"+ stdisttemp );
    	//alert("obj - " + obj);
    	if(obj.indexOf("subgrid") == -1) {
	    	var selected = document.getElementById("checkall_"+obj);
	    	stcodetemp = "";
	    	if(selected.checked) {
	    		for(var k=0; k<idlist1.length; k++) {
	    			//alert("rowid -"+ "check_"+obj+"_row_"+idlist1[k]);
	    			if(!document.getElementById("check_"+obj+"_row_"+idlist1[k]).checked) {
	    				//alert("if");
	    				document.getElementById("check_"+obj+"_row_"+idlist1[k]).checked = true;
	    				stcodetemp = stcodetemp + idlist1[k] + ",";
	    				$("#" + obj + "_" + idlist1[k]).addClass("clicked");
	    			}
	    		}
	    		
	    		stcodetemp = stcodetemp.substring(0,stcodetemp.length-1);
	    		
	    	} else {
	    		stcodetemp = "";
	    		for(var k=0; k<idlist1.length; k++) {
	    			if(document.getElementById("check_"+obj+"_row_"+idlist1[k]).checked) {
	    				document.getElementById("check_"+obj+"_row_"+idlist1[k]).checked = false;
	    				$("#" + obj + "_" + idlist1[k]).removeClass("clicked");
	    				
	    			}
	    		}
	    	}
	    	//alert("final -" + stcodetemp);
    	} else {
    		//alert("else part");
    		
    		var tempids = new Array();
    		var ids = obj.split("_");
    		//alert(ids[1]);
    		
    		var selected = document.getElementById("checkall_"+obj);
	    	
    		for(var k=0; k<idlist2.length; k++) {
    			var tempidarray = idlist2[k].split(":");
    			
    			if($.trim(tempidarray[1]) == $.trim(ids[1])) {
    				tempids.push(idlist2[k]);
    			} 
    		}
    		
    		
	    	if(selected.checked) {
	    		
	    		if(stdisttemp != "") {
	    			stdisttemp = stdisttemp + ",";
				}
	    		for(var k=0; k<tempids.length; k++) {
	    			if(!document.getElementById("check_"+obj+"_row_"+tempids[k]).checked) {
	    				document.getElementById("check_"+obj+"_row_"+tempids[k]).checked = true;
	    				stdisttemp = stdisttemp + tempids[k] + ",";
	    				document.getElementById(obj+"_"+ tempids[k]).className = "clicked"; 
	    			}
	    		}
	    		stdisttemp = stdisttemp.substring(0,stdisttemp.length-1);
	    		
	    	} else {
	    		for(var k=0; k<tempids.length; k++) {
	    			if(document.getElementById("check_"+obj+"_row_"+tempids[k]).checked) {
	    				document.getElementById("check_"+obj+"_row_"+tempids[k]).checked = false;
	    				document.getElementById(obj+"_"+ tempids[k]).removeAttribute("class");
	    			}
	    		}
	    		
	    		var stdisttemp1 = stdisttemp.split(",");
	    	//	alert("stdisttemp before1-" + stdisttemp);
	    		stdisttemp = "";
	    		
	    		//alert("stdisttemp before2-" + stdisttemp);
	    	//	alert("tempids -" + tempids);
	    		for(var l=0; l<tempids.length; l++) {
	    			remove_item(stdisttemp1,tempids[l]);
	    		}
	    		
	    		/*for(var l=0; l<tempids.length; l++) {
	    			for(var k=0; k<stdisttemp1.length; k++) {
	    				if($.trim(stdisttemp1[k]) != $.trim(tempids[l])) {
	    					stdisttemp = stdisttemp + stdisttemp1[k] + ",";
	    				}
	    			}
	    		}*/
	    		
	    		for(var n=0; n<stdisttemp1.length; n++) {
	    			stdisttemp = stdisttemp + stdisttemp1[n] + ",";
	    		}
	    		stdisttemp = stdisttemp.substring(0,stdisttemp.length-1);
	    	}
	    	
    		//var id = this.id.split("_");
	    	//alert("stdisttemp -" + stdisttemp);
    		
    	}
    	//alert("idlist1 - " + idlist1.length);
    	
        /*$.each($("#" + obj + "_datagrid > tbody tr"), function (index, value) {
            var id = this.id.split("_");
            var selected = $("#checkall_" + obj).is(":checked");
            var rid = "";
            for (var i = 0; i < id.length; i++) {
                if (obj.indexOf(id[i]) == -1) {
                    rid += "_" + id[i]
                }
            }
            rid = rid.substr(1);
            if (selected && $("#check_" + obj + "_row_" + rid).is(":checked")) {
                $("#" + obj + "_" + rid).removeClass("clicked")
            } else {
                if (!selected && !$("#check_" + obj + "_row_" + rid).is(":checked")) {
                    $("#" + obj + "_" + rid).addClass("clicked")
                }
            }
            $("#check_" + obj + "_row_" + rid).attr("checked", selected);
            $.jQgrid.selectItem(obj, rid, selected)
        });*/
    	
/*    	$.each($("#" + obj + "_datagrid > tbody tr"), function (index, value) {
            var id = this.id.split("_");
            var selected = $("#checkall_" + obj).is(":checked");
            var rid = "";
            for (var i = 0; i < id.length; i++) {
                if (obj.indexOf(id[i]) == -1) {
                    rid += "_" + id[i]
                }
            }
            rid = rid.substr(1);

        });
*/    	
        var dataobj = {};
        dataobj.id = obj;
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.action = "checkall";
        dataobj.value = $("#" + obj).jqgrid_getChecked();
        jQgridHandler(dataobj)
    },
    changeRecLimit: function (id, value) {
        var dataobj = {};
        dataobj.id = id;
        dataobj.name = $.jQgrid.getName(id);
        dataobj.action = "changereclimit";
        dataobj.value = {};
        dataobj.value["page"] = $("#" + id + "_currpage").val();
        dataobj.value["reclimit"] = value;
        jQgridHandler(dataobj)
    },
    paging: function (id, action) {
        try {
            var checkaction = $("#" + id + "_" + action).attr("src").indexOf("off");
            if (checkaction > -1) {
                return
            }
        } catch (e) {}
        var currpage = 0;
        try {
            currpage = parseInt($("#" + id + "_currpage").val())
        } catch (e) {}
        var lastpage = 0;
        try {
            lastpage = parseInt($("#" + id + "_lastpage").html())
        } catch (e) {}
        var totalrows = 0;
        try {
            totalrows = parseInt($("#" + id + "_totalrows").html())
        } catch (e) {}
        if (lastpage > 0) {
            var checkpage = currpage;
            if (action == "first") {
                currpage = 1;
                $("#" + id + "_currpage").val(1)
            } else {
                if (action == "last") {
                    currpage = lastpage;
                    $("#" + id + "_currpage").val(lastpage)
                } else {
                    if (action == "prev" && currpage > 1) {
                        currpage = currpage - 1;
                        $("#" + id + "_currpage").val(currpage)
                    } else {
                        if (action == "next" && currpage < lastpage) {
                            currpage = parseInt(currpage) + 1;
                            $("#" + id + "_currpage").val(currpage)
                        } else {
                            var page = 0;
                            try {
                                page = parseInt(action);
                                if (!isNaN(page)) {
                                    checkpage = -1;
                                    if (page > 0 && page <= lastpage) {
                                        $("#" + id + "_currpage").val(page)
                                    }
                                } else {
                                    if (isNaN(page)) {
                                        currpage = 1;
                                        $("#" + id + "_currpage").val(currpage)
                                    }
                                }
                            } catch (e) {}
                        }
                    }
                }
            } if (checkpage != currpage) {
                $.jQgrid.changePagingImg(id, currpage, lastpage);
                var dataobj = {};
                dataobj.id = id;
                dataobj.name = $.jQgrid.getName(id);
                dataobj.action = "changepage";
                dataobj.value = {};
                dataobj.value["page"] = currpage;
                dataobj.value["reclimit"] = $("#" + id + "_reclimit").val();
                jQgridHandler(dataobj)
            }
        }
    },
    changePagingImg: function (id, currpage, total) {
        if ($("#" + id + "_first").html() == null) {
            return
        }
        if (currpage == total && currpage == 1) {
            $("#" + id + "_first").attr("src", $("#" + id + "_first").attr("src").replace(/\/first.gif/, "/off-first.gif"));
            $("#" + id + "_prev").attr("src", $("#" + id + "_prev").attr("src").replace(/\/prev.gif/, "/off-prev.gif"));
            $("#" + id + "_last").attr("src", $("#" + id + "_last").attr("src").replace(/\/last.gif/, "/off-last.gif"));
            $("#" + id + "_next").attr("src", $("#" + id + "_next").attr("src").replace(/\/next.gif/, "/off-next.gif"));
            $("#" + id + "_last").css({
                cursor: "auto"
            });
            $("#" + id + "_next").css({
                cursor: "auto"
            });
            $("#" + id + "_first").css({
                cursor: "auto"
            });
            $("#" + id + "_prev").css({
                cursor: "auto"
            })
        } else {
            if (currpage == 1) {
                $("#" + id + "_first").attr("src", $("#" + id + "_first").attr("src").replace(/\/first.gif/, "/off-first.gif"));
                $("#" + id + "_prev").attr("src", $("#" + id + "_prev").attr("src").replace(/\/prev.gif/, "/off-prev.gif"));
                $("#" + id + "_last").attr("src", $("#" + id + "_last").attr("src").replace(/\/off-last.gif/, "/last.gif"));
                $("#" + id + "_next").attr("src", $("#" + id + "_next").attr("src").replace(/\/off-next.gif/, "/next.gif"));
                $("#" + id + "_last").css({
                    cursor: "pointer"
                });
                $("#" + id + "_next").css({
                    cursor: "pointer"
                });
                $("#" + id + "_first").css({
                    cursor: "auto"
                });
                $("#" + id + "_prev").css({
                    cursor: "auto"
                })
            } else {
                if (currpage == total) {
                    $("#" + id + "_first").attr("src", $("#" + id + "_first").attr("src").replace(/\/off-first.gif/, "/first.gif"));
                    $("#" + id + "_prev").attr("src", $("#" + id + "_prev").attr("src").replace(/\/off-prev.gif/, "/prev.gif"));
                    $("#" + id + "_last").attr("src", $("#" + id + "_last").attr("src").replace(/\/last.gif/, "/off-last.gif"));
                    $("#" + id + "_next").attr("src", $("#" + id + "_next").attr("src").replace(/\/next.gif/, "/off-next.gif"));
                    $("#" + id + "_last").css({
                        cursor: "auto"
                    });
                    $("#" + id + "_next").css({
                        cursor: "auto"
                    });
                    $("#" + id + "_first").css({
                        cursor: "pointer"
                    });
                    $("#" + id + "_prev").css({
                        cursor: "pointer"
                    })
                } else {
                    $("#" + id + "_first").attr("src", $("#" + id + "_first").attr("src").replace(/\/off-first.gif/, "/first.gif"));
                    $("#" + id + "_prev").attr("src", $("#" + id + "_prev").attr("src").replace(/\/off-prev.gif/, "/prev.gif"));
                    $("#" + id + "_last").attr("src", $("#" + id + "_last").attr("src").replace(/\/off-last.gif/, "/last.gif"));
                    $("#" + id + "_next").attr("src", $("#" + id + "_next").attr("src").replace(/\/off-next.gif/, "/next.gif"));
                    $("#" + id + "_last").css({
                        cursor: "pointer"
                    });
                    $("#" + id + "_next").css({
                        cursor: "pointer"
                    });
                    $("#" + id + "_first").css({
                        cursor: "pointer"
                    });
                    $("#" + id + "_prev").css({
                        cursor: "pointer"
                    })
                }
            }
        }
    },
    setTitle: function (id, title) {
        if (title.length > 40) {
            $("#" + id + "_gridTitle").html(title.substr(0, 40) + "...")
        } else {
            $("#" + id + "_gridTitle").html(title)
        }
    },
    hideshow: function (id) {
        var image = $("#" + id + "_gridResize").attr("src");
        if (image.indexOf("min.gif") != -1) {
            image = image.replace(/min.gif/, "max.gif");
            $("#" + id + "_gridResize").attr("src", image);
            $("#" + id + "_gridBody").css("display", "none");
            $("#" + id + "_gridFooter").css("display", "none")
        } else {
            if (image.indexOf("max.gif") != -1) {
                image = image.replace(/max.gif/, "min.gif");
                $("#" + id + "_gridResize").attr("src", image);
                $("#" + id + "_gridBody").css("display", "block");
                $("#" + id + "_gridFooter").css("display", "block")
            }
        }
    },
    buildHeader: function (id, data, options) {
        $("#" + id + "_gridcheck").val(options.check);
        $("#" + id + "_gridexpand").val(options.expand);
        var extra = "";
        if (options.hideheader) {
            extra = ' style="display:none" '
        }
        if (options == undefined) {
            options = {}
        }
        var str = "<tr>";
        if (options.check) {
            //str += '<td width="20"' + extra + '><input type="checkbox" id="checkall_' + id + '" class="cbox" onclick="$(\'#' + id + "').jqgrid_checkall();\"></td>"
            str += '<td width="20"' + extra + '><input type="checkbox" id="checkall_' + id + '" class="cbox" onclick="$(\'#' + id + "').jqgrid_checkall1();checkAll(this);\"></td>"
        } else {
            $("#" + id + "_gridcheckshow").css("display", "none");
        } if (options.expand) {
            str += '<td width="20"' + extra + ">&nbsp;</td>";
        }
        var count = 0;
        for (var obj in data) {
            str += "<td onclick=\"$('#" + id + "').jqgrid_sort('" + obj + '\');" tdid= "'+obj+'" id="' + id + "_hd_" + obj + '" nowrap="nowrap"' + extra + ">" + data[obj] + "</td>";
            count++
        }
        this.cols[id] = count;
        str += "</tr>";
        $("#" + id + "_datagrid > thead").html(str)
    },
    buildBody: function (id, data, options) {
    	
    	//alert("options -"+ document.getElementById(advanceSettingOptions[0]).checked);
    	
        if (options == undefined) {
            options = {}
        }
        var str = "";
        for (var cid in data) {
        	
        	if(cid.indexOf(":") == -1) {
        		if($.inArray(cid, idlist1) == -1) {
        			idlist1.push(cid);
        		}
        	} else {
        		if($.inArray(cid, idlist2) == -1) {
        			idlist2.push(cid);
        		}
        		
        	}
        	
            str += '<tr id="' + id + "_" + cid + '">';
            if ($("#" + id + "_gridcheck").val() == "true") {
                //str += '<td><input type="checkbox" id="check_' + id + "_row_" + cid + '" rowid= "'+cid+'" class="cbox" onclick="$(\'#' + id + "').jqgrid_checkItem('" + cid + "');\"></td>"
                str += '<td><input type="checkbox" id="check_' + id + "_row_" + cid + '" rowid= "'+cid+'" class="cbox" onclick="insertID(this);currentID(this);$(\'#' + id + "').jqgrid_checkItem1('" + cid + "');\"></td>"
            }
            if ($("#" + id + "_gridexpand").val() == "true") {
                str += '<td><img id="' + id + "_" + cid + '_sub" src="' + ($.jQgrid.imgPath) + '/plus.gif" onclick="$(\'#' + id + "').jqgrid_subgrid(this.id,'" + cid + '\');" border="0"/></td>'
            }
            var count = 0;
            for (var obj in data[cid]) {
                str += "<td onclick=\"$('#" + id + "').jqgrid_clickRow('" + obj + "','" + cid + '\');" tdid= "'+obj+'" id="' + id + "_" + obj + "_" + cid + '" nowrap="nowrap">' + data[cid][obj] + "</td>"
            }
            str += "</tr>"
        }
        $("#" + id + "_datagrid > tbody").html(str);
       // $("#" + id + "_datagrid > tbody tr:even").addClass("alt")
    },
    /*selectItem: function (obj, id, selected) {
        if ($.jQgrid.checkitems[obj] == null) {
            $.jQgrid.checkitems[obj] = {}
        }
        if ($.jQgrid.checkitems[obj][id] == undefined) {
            $.jQgrid.lastcheck[obj] = id;
            $.jQgrid.checkitems[obj][id] = "y"
        } else {
            if ($.jQgrid.checkitems[obj][id] == "y" && !selected) {
                $.jQgrid.checkitems[obj][id] = "n"
            } else {
                if ($.jQgrid.checkitems[obj][id] == "n" && selected) {
                    $.jQgrid.lastcheck[obj] = id;
                    $.jQgrid.checkitems[obj][id] = "y"
                }
            }
        }
        $("#" + obj + "_" + id).toggleClass("clicked");
        $.jQgrid.countSelectedItem(obj);
        var dataobj = {};
        dataobj.id = obj;
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.action = "check";
        dataobj.value = id;
        jQgridHandler(dataobj)
    },*/
    
    selectItem1: function (obj, id, selected) {
    	//alert(obj+"-"+id+"-"+selected);
    	var flag = false;
    	
    	if(selected) {
    		document.getElementById("check_"+obj+"_row_"+id).checked = true;
    		//alert(obj+"_"+ id);
    		document.getElementById(obj+"_"+ id).setAttribute("class", "clicked");
    		//alert("done checked ");
    	} else {
    		document.getElementById("check_"+obj+"_row_"+id).checked = false;
    		document.getElementById(obj+"_"+ id).removeAttribute("class");
    		//alert("done un checked ");
    	}

    	if(id.indexOf(":") == -1) {
    		var count = 0;
    		
    		for(var m=0; m<idlist1.length; m++ ) {
    			if(document.getElementById("check_" + obj + "_row_" + idlist1[m]).checked) {
    				flag = true;
    				count++;
    			} else {
    				flag = false;
    				count--;
    			}
    		}
    		
    		if(flag == true && count == idlist1.length) {
    			document.getElementById("checkall_" + obj).checked = true;
    		} else {
    			document.getElementById("checkall_" + obj).checked = false;
    		}
    	} else {
    		
    		flag = false;
    		var count = 0;
    		var ids = id.split(":");
    		var tempidlist = new Array();
    		//alert("checkall_"+obj);
    		
    		for(var k=0; k<idlist2.length; k++) {
    			var tempidarray = idlist2[k].split(":");
    			
    			if($.trim(tempidarray[1]) == $.trim(ids[1])) {
    				tempidlist.push(idlist2[k]);
    			} 
    		}
    		//alert("check_" + obj + "_row_" + tempidlist[0]);
    		for(var m=0; m<tempidlist.length; m++ ) {
    			if(document.getElementById("check_" + obj + "_row_" + tempidlist[m]).checked) {
    				flag = true;
    				count++;
    			} else {
    				flag = false;
    				count--;
    			}
    		}
    		
    		if(flag == true && count == tempidlist.length) {
    			document.getElementById("checkall_" + obj).checked = true;
    		} else {
    			document.getElementById("checkall_" + obj).checked = false;
    		}
    		
    	}
    	
    	
       if ($.jQgrid.checkitems[obj] == null) {
            $.jQgrid.checkitems[obj] = {};
        }
        if ($.jQgrid.checkitems[obj][id] == undefined) {
            $.jQgrid.lastcheck[obj] = id;
            $.jQgrid.checkitems[obj][id] = "y";
        } else {
            if ($.jQgrid.checkitems[obj][id] == "y" && !selected) {
                $.jQgrid.checkitems[obj][id] = "n";
            } else {
                if ($.jQgrid.checkitems[obj][id] == "n" && selected) {
                    $.jQgrid.lastcheck[obj] = id;
                    $.jQgrid.checkitems[obj][id] = "y";
                }
            }
        }
    	
        $.jQgrid.countSelectedItem(obj);
        var dataobj = {};
        dataobj.id = obj;
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.action = "check";
        dataobj.value = id;
        jQgridHandler(dataobj);
    },
    
    selectRowItem: function (obj, id) {
        if ($.jQgrid.checkitems[obj] == null) {
            $.jQgrid.checkitems[obj] = {}
        }
        $.jQgrid.cleanCheckedItems(obj);
        if ($.jQgrid.checkitems[obj][id] == undefined) {
            $.jQgrid.lastcheck[obj] = id;
            $.jQgrid.checkitems[obj][id] = "y";
            $("#check_" + obj + "_row_" + id).attr("checked", true)
        } else {
            if ($.jQgrid.checkitems[obj][id] == "y") {
                $.jQgrid.checkitems[obj][id] = "n";
                $("#check_" + obj + "_row_" + id).attr("checked", false)
            } else {
                if ($.jQgrid.checkitems[obj][id] == "n") {
                    $.jQgrid.lastcheck[obj] = id;
                    $.jQgrid.checkitems[obj][id] = "y";
                    $("#check_" + obj + "_row_" + id).attr("checked", true)
                }
            }
        }
        $("#" + obj + "_" + id).toggleClass("clicked");
        $.jQgrid.countSelectedItem(obj);
        var dataobj = {};
        dataobj.id = obj;
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.action = "check";
        dataobj.value = id;
        jQgridHandler(dataobj)
    },
    countSelectedItem: function (obj) {
        var count = 0;
        try {
            for (var key in $.jQgrid.checkitems[obj]) {
                if ($.jQgrid.checkitems[obj][key] == "y" && key != "") {
                    count++
                }
            }
        } catch (e) {}
        $("#" + obj + "_selecteditem").html(count)
    },
    /*checkItem: function (obj, id) {
        var selected = $("#check_" + obj + "_row_" + id).is(":checked");
        $.jQgrid.selectItem(obj, id, selected)
    },*/
    checkItem1: function (obj, id) {
        //var selected = $("#check_" + obj + "_row_" + id).is(":checked");
    	var selected = document.getElementById("check_" + obj + "_row_" + id).checked;
    	//alert("selected value " + selected);
        $.jQgrid.selectItem1(obj, id, selected);
    },
    resetCheckedItems: function (obj) {
        try {
            for (var id in $.jQgrid.checkitems[obj]) {
                if ($.jQgrid.checkitems[obj][id] == "y") {
                    $("#check_" + obj + "_row_" + id).attr("checked", true);
                    $("#" + obj + "_" + id).addClass("clicked")
                }
            }
        } catch (e) {}
    },
    cleanCheckedItems: function (obj) {
        try {
            for (var id in $.jQgrid.checkitems[obj]) {
                if ($.jQgrid.checkitems[obj][id] == "y") {
                    $("#check_" + obj + "_row_" + id).attr("checked", false);
                    $("#" + obj + "_" + id).removeClass("clicked")
                }
            }
            $.jQgrid.checkitems[obj] = {};
            $.jQgrid.lastcheck[obj] = "";
            $.jQgrid.countSelectedItem(obj)
        } catch (e) {}
    },
    subgrid: function (obj, id, cid) {
        var image = $("#" + id).attr("src");
        var row_id = id.substr(0, id.length - 4);
        var dataobj = {};
        dataobj.id = row_id + "_subgrid_div";
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.value = cid;
        if (image.indexOf("plus.gif") != -1) {
            $("#" + id).attr("src", image.replace(/plus.gif/, "minus.gif"));
            $.jQgrid.opensubgrid(obj, row_id);
            dataobj.action = "createsubgrid";
            jQgridHandler(dataobj)
        } else {
            if (image.indexOf("minus.gif") != -1) {
                $("#" + id).attr("src", image.replace(/minus.gif/, "plus.gif"));
                $.jQgrid.closesubgrid(obj, row_id);
                dataobj.action = "closesubgrid";
                jQgridHandler(dataobj)
            }
        }
    },
    opensubgrid: function (obj, row_id) {
        var mainGrid = "";
        if ($("#" + obj + "_gridLoaderId").val()) {
            mainGrid = $("#" + obj + "_gridLoaderId").val()
        } else {
            mainGrid = $("#" + row_id + "_subgrid_div_gridLoaderId").val()
        }
        var checkshow = "";
        if ($("#" + obj + "_gridcheck").val()) {
            checkshow = $("#" + obj + "_gridcheck").val()
        } else {
            checkshow = $("#" + row_id + "_subgrid_div_gridcheck").val()
        }
        var data = "";
        data += '<tr id="' + row_id + '_subgrid">';
        if (checkshow == "true") {
            data += "<td>&nbsp;</td>"
        }
        data += '<td><input type="hidden" id="' + row_id + '_subgrid_div_gridexpand"/><input type="hidden" id="' + row_id + '_subgrid_div_gridcheck"/><input type="hidden" id="' + row_id + '_subgrid_div_gridLoaderId" value="' + mainGrid + '"/><img src="' + ($.jQgrid.imgPath) + '/line3.gif"/></td>';
        data += '<td colspan="' + $.jQgrid.cols[obj] + '">';
        data += '<div id="' + row_id + '_subgrid_div" style="width:100%;overflow:hidden"><img src="' + ($.jQgrid.imgPath) + '/loading.gif"/></div>';
        data += "</td>";
        data += "</tr>";
        $("#" + row_id).after(data);
       // $("#" + obj + "_datagrid > tbody tr:even").addClass("alt")
    },
    closesubgrid: function (obj, row_id) {
        var data = "";
        var sid = row_id + "_subgrid";
        $("#" + sid).remove();
       // $("#" + obj + "_datagrid > tbody tr:even").addClass("alt")
    },
    action: function (obj, action) {
        var dataobj = {};
        dataobj.id = obj;
        dataobj.name = $.jQgrid.getName(obj);
        dataobj.action = action;
        if (action == "copy" || action == "update") {
            dataobj.value = $("#" + obj).jqgrid_getLastCheck()
        } else {
            if (action == "delete") {
                dataobj.value = $("#" + obj).jqgrid_getChecked()
            } else {
                dataobj.value = ""
            }
        }
        jQgridHandler(dataobj)
    },
    getName: function (id) {
        return $("#" + id + "_gridName").val()
    },
    getTitle: function (id) {
        return $("#" + id + "_gridTitle").html()
    },
    setName: function (id, value) {
        $("#" + id + "_gridName").val(value)
    },
    getParentid: function (id) {
        return $("#" + id + "_gridParentid").val()
    },
    setParentid: function (id, value) {
        $("#" + id + "_gridParentid").val(value)
    },
    setPagingValue: function (id, obj) {
        if (!obj) {
            obj = Array();
            obj.currpage = 0;
            obj.lastpage = 0;
            obj.totalrows = 0
        }
        $("#" + id + "_currpage").val(obj.currpage);
        $("#" + id + "_lastpage").html(obj.lastpage);
        $("#" + id + "_totalrows").html(obj.totalrows);
        $.jQgrid.changePagingImg(id, obj.currpage, obj.lastpage)
    },
    init: function (id, options) {
        if (options == undefined) {
            options = {}
        }
        var str = "";
        if (id.indexOf("_subgrid") == -1) {
            str += '<div class="gridHeader" id="' + id + '_gridHeader"><img id="' + id + '_gridLoader" style="float:left;display:none" src="' + ($.jQgrid.imgPath) + '/loader.gif"/><div class="gridHeaderTitle" id="' + id + '_gridTitle">' + $("#" + id).attr("title") + '</div><div class="gridHeaderButton"><img id="' + id + '_gridResize" style="cursor:pointer" onclick="$(\'#' + id + '\').jqgrid_hideshow();" src="' + ($.jQgrid.imgPath) + '/min.gif"/></div></div>'
        }
        str += '<div class="gridBody" id="' + id + '_gridBody">';
        str += '<input type="hidden" id="' + id + '_gridName" value="' + options.name + '"/>';
        str += '<input type="hidden" id="' + id + '_gridParentid" value="' + options.parentid + '"/>';
        str += '<input type="hidden" id="' + id + '_gridLoaderId" value="' + id + '"/>';
        str += '<input type="hidden" id="' + id + '_gridcheck"/>';
        str += '<input type="hidden" id="' + id + '_gridexpand"/>';
        str += '<div class="gridBodyCol">';
        str += '<table class="gridstyle" id="' + id + '_datagrid">';
        str += "<thead></thead>";
        str += '<tbody id="' + id + '_datagridbody"></body>';
        str += "<tfoot></tfoot>";
        str += "</table>";
        str += "</div>";
        if (id.indexOf("_subgrid") == -1) {
            if (navigator.appName == "Microsoft Internet Explorer") {
                str += "<br/>"
            }
        }
        str += "</div>";
        if (options.hidefooter) {
            str += '<div class="gridFooter" id="' + id + '_gridFooter" style="display:none">'
        } else {
            str += '<div class="gridFooter" id="' + id + '_gridFooter">'
        }
        str += '<div class="gridFooterIcon">';
        str += '<span class="gridTickIcon" id="' + id + '_gridcheckshow">';
        str += '<span class="selected_total" id="' + id + '_selecteditem">0</span>';
        str += "</span>";
        str += '<span id="' + id + '_datagrid_icon"></span>';
        for ($i = 0; $i < $.jQgrid.gridButton.length; $i++) {
            if (options[$.jQgrid.gridButton[$i]]) {
                str += '<img id="' + id + "_gridBtn_" + $.jQgrid.gridButton[$i] + '" src="' + ($.jQgrid.imgPath) + "/" + ($.jQgrid.gridButton[$i]) + '.gif" style="cursor:pointer;" onclick="$(\'#' + id + "').jqgrid_action('" + ($.jQgrid.gridButton[$i]) + '\');" alt="' + $.jQgrid.language[$.jQgrid.gridButton[$i]] + '" title="' + $.jQgrid.language[$.jQgrid.gridButton[$i]] + '"/>'
            }
        }
        str += " ";
        if (options.showpage) {
            str += '<img id="' + id + '_first" style="cursor:pointer;" onclick="$(\'#' + id + "').jqgrid_paging('first');\" src=\"" + ($.jQgrid.imgPath) + '/first.gif"/>';
            str += '<img id="' + id + '_prev" style="cursor:pointer" onclick="$(\'#' + id + "').jqgrid_paging('prev');\" src=\"" + ($.jQgrid.imgPath) + '/prev.gif"/>';
            str += '<input type="text" id="' + id + '_currpage" class="pagebox" value="0" onchange="$(\'#' + id + '\').jqgrid_paging(this.value);"/>/<span class="paging" id="' + id + '_lastpage">0</span>';
            str += '<img id="' + id + '_next" style="cursor:pointer" onclick="$(\'#' + id + "').jqgrid_paging('next');\" src=\"" + ($.jQgrid.imgPath) + '/next.gif"/>';
            str += '<img id="' + id + '_last" style="cursor:pointer" onclick="$(\'#' + id + "').jqgrid_paging('last');\" src=\"" + ($.jQgrid.imgPath) + '/last.gif"/>';
            str += '<select id="' + id + '_reclimit" class="gridRowOption" onchange="$(\'#' + id + "').jqgrid_changeRecLimit(this.value);\">";
            for (var i = 0; i < $.jQgrid.limitOpt.length; i++) {
                str += '<option value="' + $.jQgrid.limitOpt[i] + '">' + $.jQgrid.limitOpt[i] + "</option>"
            }
            str += "</select>";
            str += ' <span class="paging" id="' + id + '_totalrowspre">' + $.jQgrid.language.pre_total + '</span> <span class="paging" id="' + id + '_totalrows">0</span> <span class="paging" id="' + id + '_totalrowspost">' + $.jQgrid.language.post_total + "</span>"
        }
        str += "</div>";
        $("#" + id).html(str)
    }
};
$.fn.extend({
    jqgrid: function (options) {
        return this.each(function () {
            $.jQgrid.init(this.id, options)
        })
    },
    /*jqgrid_checkall: function () {
        return this.each(function () {
            $.jQgrid.checkAllItems(this.id)
        })
    },*/
    jqgrid_checkall1: function () {
    	//alert("this is -" + this.id);
    	//alert("list1 -" + idlist1);
    	//alert("list2 -" + idlist2);
        return this.each(function () {
        	//alert("this is -" + this.id);
            $.jQgrid.checkAllItems1(this.id)
        })
    },
    /*jqgrid_checkItem: function (id) {
        return this.each(function () {
            $.jQgrid.checkItem(this.id, id)
        })
    },*/
    jqgrid_checkItem1: function (id) {
        return this.each(function () {
            $.jQgrid.checkItem1(this.id, id)
        })
    },
    jqgrid_subgrid: function (id, cid) {
        return this.each(function () {
            $.jQgrid.subgrid(this.id, id, cid)
        })
    },
    jqgrid_buildHeader: function (cols, options) {
        return this.each(function () {
            $.jQgrid.buildHeader(this.id, cols, options)
        })
    },
    jqgrid_buildBody: function (cols, options) {
        return this.each(function () {
            $.jQgrid.buildBody(this.id, cols, options)
        })
    },
    jqgrid_setTitle: function (title) {
        return this.each(function () {
            $.jQgrid.setTitle(this.id, title)
        })
    },
    jqgrid_hideshow: function () {
        return this.each(function () {
            $.jQgrid.hideshow(this.id)
        })
    },
    jqgrid_paging: function (value) {
        return this.each(function () {
            $.jQgrid.paging(this.id, value)
        })
    },
    jqgrid_setLanguage: function (obj) {
        $.each(obj, function (index, value) {
            $.jQgrid.language[index] = value
        })
    },
    jqgrid_getName: function () {
        var name = "";
        this.each(function () {
            if (this.id != undefined) {
                name = $.jQgrid.getName(this.id)
            }
        });
        return name
    },
    jqgrid_getTitle: function () {
        var name = "";
        this.each(function () {
            if (this.id != undefined) {
                name = $.jQgrid.getTitle(this.id)
            }
        });
        return name
    },
    jqgrid_getParentid: function () {
        var pid = "";
        this.each(function () {
            if (this.id != undefined) {
                pid = $.jQgrid.getParentid(this.id)
            }
        });
        return pid
    },
    jqgrid_setParentid: function (value) {
        return this.each(function () {
            $.jQgrid.setParentid(this.id, value)
        })
    },
    jqgrid_setName: function (value) {
        return this.each(function () {
            $.jQgrid.setName(this.id, value)
        })
    },
    jqgrid_action: function (action) {
        return this.each(function () {
            $.jQgrid.action(this.id, action)
        })
    },
    jqgrid_setPagingValue: function (obj) {
        return this.each(function () {
            $.jQgrid.setPagingValue(this.id, obj)
        })
    },
    jqgrid_getLastCheck: function () {
        var id = "";
        this.each(function () {
            if (this.id != undefined && $.jQgrid.lastcheck[this.id]) {
                id = $.jQgrid.lastcheck[this.id]
            }
        });
        return id
    },
    jqgrid_clickRow: function (column, rowid) {
        return this.each(function () {
            $.jQgrid.clickRow(this.id, column, rowid)
        })
    },
    jqgrid_sortColumn: function (column) {
        return this.each(function () {
            $.jQgrid.sortColumn(this.id, column)
        })
    },
    jqgrid_sort: function (column) {
        return this.each(function () {
            $.jQgrid.sort(this.id, column)
        })
    },
    jqgrid_getChecked: function () {
        var id = "";
        this.each(function () {
            if (this.id != undefined && $.jQgrid.checkitems[this.id]) {
                $.each($.jQgrid.checkitems[this.id], function (pid, val) {
                    if (val == "y") {
                        id += "," + pid
                    }
                })
            }
        });
        return id.substr(1)
    },
    jqgrid_resetCheckedItems: function () {
        return this.each(function () {
            $.jQgrid.resetCheckedItems(this.id)
        })
    },
    jqgrid_cleanCheckedItems: function () {
        return this.each(function () {
            $.jQgrid.cleanCheckedItems(this.id)
        })
    },
    jqgrid_cleanSortColumns: function () {
        return this.each(function () {
            $.jQgrid.cleanSortColumns(this.id)
        })
    },
    jqgrid_getRecLimit: function () {
        var limit = "";
        this.each(function () {
            if (this.id != undefined) {
                limit = $("#" + this.id + "_reclimit").val()
            }
        });
        return limit
    },
    jqgrid_setRecLimit: function (value) {
        return this.each(function () {
            limit = $("#" + this.id + "_reclimit").val(value)
        })
    },
    jqgrid_changeRecLimit: function (value) {
        return this.each(function () {
            $.jQgrid.changeRecLimit(this.id, value)
        })
    },
    jqgrid_getCurrentPage: function () {
        var page = "";
        this.each(function () {
            if (this.id != undefined) {
                page = $("#" + this.id + "_currpage").val()
            }
        });
        return page
    },
    jqgrid_setGridLoader: function (value) {
        this.each(function () {
            $("#" + $("#" + this.id + "_gridLoaderId").val() + "_gridLoader").css("display", value)
        })
    }
});