$(document).ready(function(){
	var setTime;
	var leng;
	var bIndex = "";

	function auto(){
		btnNext();
	}

	function btnNext(){
		bInit();
		var obj = $('#main_event_block_w ul').eq(0).find('li');

		if(!obj.is(":animated")){
			if(obj.eq(bIndex).css('right') == '0px'){
				obj.eq(bIndex).css({'right':'-100%'});
				obj.eq(bIndex).animate({'left':'-100%'},500);
				bIndex = bIndex+1;
				if(bIndex > leng-1){
					bIndex = 0;
				}
				obj.eq(bIndex).animate({'right':'0%'},500);
				$('#main_event_block_w ul').eq(1).find('li').removeClass('actived').end().find("li:eq("+bIndex+")").addClass('actived');
			}
		}
	}
	function btnPrev(){
		bInit();
		var obj = $('#main_event_block_w ul').eq(0).find('li');
		if(!obj.is(":animated")){
			obj.eq(bIndex).animate({'right':'-100%'},500);
			bIndex = bIndex-1;
			if(bIndex < 0 ){
				bIndex = obj.length-1;
			}
			obj.eq(bIndex).css({'left':'-100%','right':'0%'}).animate({'left':'0%'},500);
			$('#main_event_block_w ul').eq(1).find('li').removeClass('actived').end().find("li:eq("+bIndex+")").addClass('actived');
		}
	}

	$('.main_btn_next').click(function(){
		btnNext();
		$('.pause').removeClass('pause').addClass('start');
		clearInterval(setTime);
	});

	$('.main_btn_prev').click(function(){
		btnPrev();
		$('.pause').attr('class','start');
		clearInterval(setTime);
	});

	function bInit(){
		$('#main_event_block_w ul').eq(0).find('li').each(function(index){
			$('#main_event_block_w ul').eq(0).find('li').eq(index).css('left','');
		});
	}

	function rTime(){
		$('#main_event_block_w ul').eq(0).find('li').eq(0).css('right','0');
		leng= $('#main_event_block_w ul').eq(0).find('li').length;
		
		if (leng <= 1){
			$(".btn_l_wrap, .btn_r_wrap").hide();
			return ;
		}

		for(i=0;i<leng;i++){
			if($('#main_event_block_w ul').eq(0).find('li').eq(i).css('right') == '0px'){
				bIndex = i;
			}
		}

		$('#main_event_block_w ul').eq(0).find('li').each(function(index){

			$('#main_event_block_w ul').eq(1).append('<li class="page_n"><a href="javascript:void(0);"></a></li>');
		});
		$('#main_event_block_w ul').eq(1).append('<li class=""><a href="javascript:void(0);" class="pause">일시정지</a></li>');
		$('#main_event_block_w ul').eq(1).find('li').eq(0).removeClass('page_n').addClass('page_n actived');

		$('#main_event_block_w ul').eq(1).find('li').click(function() {
			bInit();
			var val = $(this).index();
			var choice =  $(this).index();

			if(choice == $('#main_event_block_w ul').eq(1).find('li').length -1){			// li의 마지막은 재생 정지버튼이 들어가있기에 재생중지 및 동작으로 작동해야함
				if($('.pause').length != 0){
					$('.pause').removeClass('pause').addClass('start');
					clearInterval(setTime);
				}
				else{
					$('.start').removeClass('start').addClass('pause');
					setTime = setInterval(auto,8000);
				}
			}
			else{
				if (bIndex != val && !$('#main_event_block_w ul').eq(0).find('li').is(":animated")){		// 현재 보고있는 배너 버튼과 애니메이션이 동작하면 동작하지 않게.
					if (bIndex > val)
					{
						var obj = $('#main_event_block_w ul').eq(0).find('li');
						obj.eq(bIndex).animate({'right':'-100%'},500);
						bIndex = val;
						obj.eq(bIndex).css({'left':'-100%','right':'0%'}).animate({'left':'0%'},500);
						$('#main_event_block_w ul').eq(1).find('li').removeClass('actived').end().find("li:eq("+bIndex+")").addClass('actived');
						$('.pause').attr('class','start');
						clearInterval(setTime);
					}
					else{
						var obj = $('#main_event_block_w ul').eq(0).find('li');
						obj.eq(bIndex).css({'right':'-100%'});
						obj.eq(bIndex).animate({'left':'-100%'},500);
						bIndex = val;
						obj.eq(bIndex).animate({'right':'0%'},500);
						$('#main_event_block_w ul').eq(1).find('li').removeClass('actived').end().find("li:eq("+bIndex+")").addClass('actived');
						$('.pause').attr('class','start');
						clearInterval(setTime);
					}
				}
			}
		});
		setTime = setInterval(auto,8000);
	}
		

	//setTimeout(topBannerShow,1000);
	setTimeout(rTime,1000);

});