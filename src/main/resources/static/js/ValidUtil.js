function isValidMobile(mobile)
{           
	var check = /^(01[016789]{1}|070|02|0[3-9]{1}[0-9]{1})-[0-9]{3,4}-[0-9]{4}$/;
	
	if(!check.test(mobile)) 
	{
			return false;
	}
	
	return true;
}

function isValidEmail(email)
{           
	var check = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	
	if(!check.test(email)) 
	{
			return false;
	}
	
	return true;
}

