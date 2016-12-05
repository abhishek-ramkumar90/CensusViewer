function startLayout(){
	var mapHeight = $(window).height()- 90;
	$('#map').css('height', mapHeight);
}

function searchOverlay() {
		var bodyHeight = $(window).height()/2;
		var bodyWidth = $(window).width()/2;
		var passBoxHeight = $('.searchOverlay').height() / 2;
		var passBoxWidth = $('.searchOverlay').width() / 2;
		var passBoxTop = (bodyHeight - passBoxHeight);
		var passBoxLeft = (bodyWidth - passBoxWidth);
	$('.advanceSearch').click(function () {
			$('body').prepend('<div class="transpOverlay" style="background:url(images/bg_password.png) repeat left top; width:100%; height:100%;position:absolute; top:0; left:0; z-index:999;"></div>');
			$(this).next().insertAfter('.transpOverlay');
			$('.transpOverlay').next('.searchOverlay').css({ 'display': 'block', 'z-index': 1111, 'position': 'absolute', 'top': passBoxTop, 'left': passBoxLeft });
			$('.transpOverlay, .btnClose,.okButton').click(function () {
				//alert($(this).parent);
				$('.searchOverlay').css({ 'display': 'none' });
				$('.searchOverlay').appendTo('.searchWrapper');
				$('.transpOverlay').remove();
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
	})

	$('.okButton').click(function () {
	    $('.resultBlock').css('display', 'block');

	    $('.btnMinimize').click(function () {
	        $('.resultBlock').css('bottom', '-224px');
	    })
	    $('.btnRestore').click(function () {
	        $('.resultBlock').css('bottom', '0px');
	    })
	})

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
        var mainContentMaxHeight = $(window).height() - 90;
		var searchBoxWidth = $('.searchBoxWrapper').width() - 67;
        $('.tabelsWrapper').css('height', rightPaneMaxHeight);
        //alert(mainContentMaxHeight);
        $('.mainContentWrapper').css('height', mainContentMaxHeight);
		$('.searchBox').css('width', searchBoxWidth);
    });
    $(window).resize(function () {
        var rightPaneMaxHeight = $(window).height() - 139;
        var mainContentMaxHeight = $(window).height() - 90;
        var searchBoxWidth = $('.searchBoxWrapper').width() - 67;
        var origSize = $(window).width() - 324;
        var sidebarStatus = $('.sidebarWrapper').css('display');
        $('.tabelsWrapper').css('height', rightPaneMaxHeight);
        $('.mainContentWrapper').css('height', mainContentMaxHeight);
        $('.searchBox').css('width', searchBoxWidth);
        if (sidebarStatus == 'block') {
            $('.mainContentWrapper').css('width', origSize)
        } else {
            $('.mainContentWrapper').css('width', origSize+324)
        }

    });
})

function listBox(){
            $('#btnAdd').click(
                function (e) {
                    $('#list1 > option:selected').appendTo('#list2');
                    e.preventDefault();
                });

                $('#btnAddAll').click(
                function (e) {
                    $('#list1 > option').appendTo('#list2');
                    e.preventDefault();
                });

                $('#btnRemove').click(
                function (e) {
                    $('#list2 > option:selected').appendTo('#list1');
                    e.preventDefault();
                });

                $('#btnRemoveAll').click(
                function (e) {
                    $('#list2 > option').appendTo('#list1');
                    e.preventDefault();
                });
        }


$(document).ready(function () {
	startLayout();
	searchOverlay();
	buttonClicks();
	listBox();
})