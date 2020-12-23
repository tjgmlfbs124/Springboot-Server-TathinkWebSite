$(document).ready(function(){

	//GNB
	function fe_gnb(){
		var $gnbWrap = $(".gnbWrap");
		var $gnb = $("#gnb");
		var $subMenu = $("#gnb ul li ul");

		$subMenu.hide();


		$gnb.hoverIntent(function() {
			$gnbWrap.stop().animate({height: "319px"}, 300);
			$subMenu.slideDown(300);
			$gnb.css("height", "319px");
		}, function() {
		});
		$("#header").hoverIntent(function() {
		}, function() {
			$gnbWrap.animate({height: "91px"}, 300);
			$subMenu.slideUp(300);
			$gnb.css("height", "91px");
		});
		$gnb.on("focusin", function(){
			$gnbWrap.stop().animate({height: "319px"}, 300);
			$subMenu.slideDown(300);
		});

		/*$("#gnb ul li ul li a").last().on("focusout", function(){
		 if($("#gnb ul li ul li a").last().parent().parent().parent().hasClass('last')) {
		 $gnbWrap.stop().animate({height: "71px"}, 300);
		 $subMenu.slideUp(100);
		 }
		 });*/

		$("#container").on("focusin", function(){
			$gnbWrap.stop().animate({height: "91px"}, 300);
			$subMenu.slideUp(100);
		});

		$("h1").on("focusin", function(){
			$gnbWrap.stop().animate({height: "91px"}, 300);
			$subMenu.slideUp(100);
		});
	}
		
	
	//tab 메뉴
	function fe_tabMenu(){
		var $menu = $(".tab ul li");
		var $tabCont = $(".tabContWrap .tabCont");
		

		$menu.on("click", function(){
			var $attr = $(this).children().attr("href");
			if($attr.indexOf('.htm') != -1){
				location.href=$attr;
			}else{
				$tabCont.hide();
				$menu.removeClass("present");
				$(this).addClass("present");
				$(".tabContWrap").find($attr).show();
				return false;
			}
		});
	}	
	
	

	//실행함수
	fe_gnb();
	//fe_gnbFix();
	//fe_accordion();
	fe_tabMenu();
	
	$('.starRev span').click(function(){
		  $(this).parent().children('span').removeClass('on');
		  $(this).addClass('on').prevAll('span').addClass('on');
		  return false;
		});

});

function checkClassState(classInfoData, bManager)
{
	var now = new Date();
	var startRegDate = new Date(classInfoData.startRegDate);
	var endRegDate = new Date(classInfoData.endRegDate +" 23:59:59");
	var startStudyDate = new Date(classInfoData.startStudyDate);
	var endStudyDate = new Date(classInfoData.endStudyDate +" 23:59:59");
	
	if( now < startRegDate){
		return "대기중";
	}else if ( now >= startRegDate &&  now <= endRegDate){
		return "신청중";
	}else if ( now > endRegDate &&  now < startStudyDate){
		return "신청마감";
	}else if ( now >= startStudyDate &&  now <= endStudyDate){
		var tempStr = "수강중";
		if(bManager==true){
			tempStr="강의중";
		}
		return tempStr;
	}else if ( now > endStudyDate){
		return "종강";
	}else{
		return "-";
	}	
}

function checkMemberClassState(classInfoData)
{
	var now = new Date();
	var startRegDate = new Date(classInfoData.startRegDate);
	var endRegDate = new Date(classInfoData.endRegDate +" 23:59:59");
	var startStudyDate = new Date(classInfoData.startStudyDate);
	var endStudyDate = new Date(classInfoData.endStudyDate +" 23:59:59");
	
	if ( now >= startRegDate &&  now <= endRegDate){
		return "신청중";
	}else if( now > endRegDate &&  now < startStudyDate){
		return "대기중";		
	}else if ( now >= startStudyDate &&  now <= endStudyDate){
		return "강의중";
	}else if ( now > endStudyDate){
		return "종강";
	}else{
		return "-";
	}	
}

function calcRemainDay(classInfoData){
	
	var tempday = new Date();
	var today = new Date(tempday.getFullYear(), tempday.getMonth(), tempday.getDate());
	
	var dateArray1 = classInfoData.startStudyDate.split("-");
	var day1 = new Date(dateArray1[0], Number(dateArray1[1])-1, dateArray1[2]);
	
	var dateArray2 = classInfoData.endStudyDate.split("-");
	var day2 = new Date(dateArray2[0], Number(dateArray2[1])-1, dateArray2[2]);
	
	var diff;
	
	if(today<day1){
		diff = (day2.getTime() - day1.getTime());
	}else{
		diff = (day2.getTime() - today.getTime());	    
	}
	
	diff = Math.ceil(diff / (1000 * 3600 * 24));
	
    return diff;
}

function convertDateTimeString(dateTime)
{           
	var date = new Date(dateTime);
	
	return ""+date.getFullYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDate()+"일  "+date.getHours()+"시 "+date.getMinutes()+"분 "+date.getSeconds()+"초";
}

