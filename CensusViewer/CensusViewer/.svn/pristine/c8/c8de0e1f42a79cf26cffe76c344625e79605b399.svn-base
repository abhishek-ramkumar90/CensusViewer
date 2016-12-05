function startLayout(){
	var mapHeight = $(window).height()- 126;
	$('#map, .sidebarWrapper').css('height', mapHeight);	
	var sidebarState = $('.sidebarWrapper').css('display');
	if(sidebarState == 'none'){
		$('.mainContentsWrapper').css({'margin-left': '0', 'width': '100%'} );
	}else{	
	}	
}

function paneResize(){
	var origSize = $(window).width()/2;
	//var origSize = origSize + 'px';
	//alert(origSize);
	$('.switch').click(function(){
		/*var origSize = $(window).width() /2-1;
		$('.sidebarWrapper, .switch').css('display', 'block');
		$('.mainContentWrapper').css({ 'margin-left': '50%', 'width': origSize });
		//$('.switch').css({ 'left': '50%' });
		$('.switch').attr({ 'src': 'images/close_reasult.png',
		    'title': 'Close result pane'
		});	*/
			
	});
	$('.switch').toggle(function(){	
		var origSize = $(window).width() /2-1;
		$('.sidebarWrapper, .switch').css('display', 'block');
		$('.mainContentWrapper').css({ 'margin-left': '50%', 'width': origSize });
		//$('.switch').css({ 'left': '50%' });
		$('.switch').attr({ 'src': 'images/close_reasult.png',
		    'title': 'Close result pane'
		});	
		}, function(){
			$('.sidebarWrapper').css('display', 'none');
			$('.mainContentWrapper').css({ 'margin-left': '0', 'width': '100%' });
			//$('.switch').css({ 'left': '50%' });
			$('.switch').attr({ 'src': 'images/open_reasult.png',
			    'title': 'Opene result pane'
			});	
		});
	
}


/*function startLayout(){
	var mapHeight = $(window).height()- 90;
	$('#map').css('height', mapHeight);
}*/
function searchOverlay() {
		var bodyHeight = $(window).height()/2;
		var bodyWidth = $(window).width()/2;
		var passBoxHeight = $('.searchOverlay').height() / 2;
		var passBoxWidth = $('.searchOverlay').width() / 2;
		var passBoxTop = (bodyHeight - passBoxHeight);
		var passBoxLeft = (bodyWidth - passBoxWidth);
	$('.advanceSearch').click(function () {
			$('body').prepend('<div class="transpOverlay" style="background:url(images/bg_password.png) repeat left top; width:100%; height:100%;position:absolute; top:0; left:0; z-index:1010;"></div>');
			$(this).next().insertAfter('.transpOverlay');
			$('.transpOverlay').next('.searchOverlay').css({ 'display': 'block', 'z-index': 1111, 'position': 'absolute', 'top': passBoxTop, 'left': passBoxLeft });
			$('.transpOverlay, .btnClose').click(function () {
				//alert($(this).parent);
				$('.searchOverlay').css({ 'display': 'none' });
				$('.searchOverlay').appendTo('.searchWrapper');
				$('.transpOverlay').remove();
			});
			$('.okButton').click(function(){
				if(validation_flag == false){
					$('.searchOverlay').css({ 'display': 'none' });
					$('.searchOverlay').appendTo('.searchWrapper');
					$('.transpOverlay').remove();
					}else{
						
					}
			});
		
		});
}

function buttonClicks(){

	$('.btnLegend').click(function () {
	    var displayStatus = $(this).next().css('display');
	    if (displayStatus == 'none') {
	        $(this).addClass('activeBtnLegend');
	        $(this).next().css('display', 'block');
	    } else {
	        $(this).removeClass('activeBtnLegend');
	        $(this).next().css('display', 'none');
	    }
	});

	$('.okButton').click(function () {
	    $('.resultBlock').css('display', 'block');
	    $('.olControlScale, .olControlScaleLine').css('bottom', '294px');
	    $('.olControlMousePosition').css('bottom', '267px');
        $('.resultBlock').css('bottom', '0px');
	});


	$(function () {
	    $(this).mouseup(function (hit) {
	        if (!($(hit.target).parents('.userLabels').length > 0)) {
	            $('.labelsContent').css('display', 'none');
	            $('.activeBtnLegend').removeClass('activeBtnLegend');
	        }
	    });
	});
}

$(function () {
    $(window).load(function () {
        var rightPaneMaxHeight = $(window).height() - 139;
        var mainContentMaxHeight = $(window).height() - 86;
		var searchBoxWidth = $('.searchBoxWrapper').width() - 67;
        $('.tabelsWrapper').css('height', rightPaneMaxHeight);
        $('#mainContentWrapper, #sidebarWrapper').css('height', mainContentMaxHeight);
        //alert($('.sidebarWrapper').height());
		$('.searchBox').css('width', searchBoxWidth);
       // $('#tabs, #tabs2').children("div").css('height', mainContentMaxHeight-64);
    });
    $(window).resize(function () {
        var rightPaneMaxHeight = $(window).height() - 139;
        var mainContentMaxHeight = $(window).height() - 90;
       // alert(mainContentMaxHeight);
        var searchBoxWidth = $('.searchBoxWrapper').width() - 67;
        var origSize = $(window).width()/2;
        var sidebarStatus = $('.sidebarWrapper').css('display');
        $('.tabelsWrapper').css('height', rightPaneMaxHeight);
        $('#mainContentWrapper, #sidebarWrapper').css('height', mainContentMaxHeight);
        $('.searchBox').css('width', searchBoxWidth);
        $('#tabs').children("div").css('height', mainContentMaxHeight-64);
        if (sidebarStatus == 'block') {
            $('.mainContentWrapper').css('width', origSize-1);
        } else {
            $('.mainContentWrapper').css('width', origSize*2);
        }

    });
})

function listBox(){
            $('#btnAdd').click(
                function (e) {
                    $('#subcategory > option:selected').appendTo('#selectedCategory');
                    e.preventDefault();
                });

                $('#btnAddAll').click(
                function (e) {
                    $('#subcategory > option').appendTo('#selectedCategory');
                    e.preventDefault();
                });

                $('#btnRemove').click(
                function (e) {
                    $('#selectedCategory > option:selected').appendTo('#subcategory');
                    e.preventDefault();
                });

                $('#btnRemoveAll').click(
                function (e) {
                    $('#selectedCategory > option').appendTo('#subcategory');
                    e.preventDefault();
                });
        }

function rgtSlideIn(){
	$(".rightSlideInWrapper .rightSlideInContent").css('display', 'none');
	$(".rightSlideInWrapper h4 a").click(function(){
			$(".rightSlideInWrapper .rightSlideInContent").css('display', 'none');
			$(".rightSlideInWrapper h4 a").removeClass('active');
			$(this).parent().next().css({'display':'block'});
			$(".rightSlideInWrapper").css({'right':'0'});
			$(this).addClass('active');
	});
	$(".rightSlideInWrapper .slideRightArrow").click(function(){
			$(".rightSlideInWrapper").css({'right':'-478px'});
			$(".rightSlideInWrapper .rightSlideInContent").css('display', 'none');
			$(".rightSlideInWrapper h4 a").removeClass('active');
	});
}
function moreOptions(){
	structMoreOptions = $('.other').html();
	$('.other .lessOptions').remove();
	$('.lessOptions').live('click', function(){
		$(this).parent().remove();
	});
	$('.moreOptions').live("click", function(){
		$(structMoreOptions).insertAfter($(this).parent());
		
	});
	
$("#saveChannels").click(function(){
	var winHeight = $(window).height()/2;
	var winWidth = $(window).width()/2;
	$(".overlay").css('display', 'block');
	$("#channelName").css({'display': 'block', 
							'position':'absolute',
							'top': winHeight - 50,
							'left': winWidth -143 
				});
});	
	
}



$(document).ready(function () {

	/*$("#channelName").dialog();*/
	$('.btnMinimize').click(function () {
        $('.resultBlock').css('bottom', '-224px');
	    $('.olControlScale, .olControlScaleLine').css('bottom', '61px');
	    $('.olControlMousePosition').css('bottom', '35px');
    });
    $('.btnRestore').click(function () {
        $('.resultBlock').css('bottom', '0px');
	    $('.olControlScale, .olControlScaleLine').css('bottom', '294px');
	    $('.olControlMousePosition').css('bottom', '267px');
    });
	startLayout();
	paneResize();
	searchOverlay();
	buttonClicks();
	listBox();
	moreOptions();
	rgtSlideIn();

    $("#eventsBlockAccord").accordion({heightStyle: "fill", collapsible: true});
    
    /*$("#saveChannels").click(function(){
		$("#channelName").dialog('open');
	});*/
    
});