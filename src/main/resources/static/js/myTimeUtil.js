function convertDateTimeString(dateTime)
{           
	var date = new Date(dateTime);
	
	return ""+date.getFullYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDate()+"일  "+date.getHours()+"시 "+date.getMinutes()+"분 "+date.getSeconds()+"초";
}

