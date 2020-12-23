var obj = [];

function createMyEditer(holderId, btnId, formId, content, skinUri)
{           
	var btn = document.getElementById(btnId);
	var form = document.getElementById(formId);
	var textarea = document.getElementById(holderId);
	
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: holderId,
        sSkinURI: skinUri,	
        htParams : {
            bUseToolbar : true,            
            bUseVerticalResizer : true,    
            bUseModeChanger : true,
        },
    	fOnAppLoad : function(){
    		obj.getById[holderId].exec("SET_IR",[content]);
    	}
    });
    
    
    btn.onclick = function(){
    	obj.getById[holderId].exec("UPDATE_CONTENTS_FIELD", []);
    	form.submit();
    };
    
    textarea.style.display="none";
}

function createMyEditerEx(holderId, btnId, formId, content, skinUri, checkFunc)
{           
	var btn = document.getElementById(btnId);
	var form = document.getElementById(formId);
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: holderId,
        sSkinURI: skinUri,	
        htParams : {
            bUseToolbar : true,            
            bUseVerticalResizer : true,    
            bUseModeChanger : true,
        },
    	fOnAppLoad : function(){
    		obj.getById[holderId].exec("SET_IR",[content]);
    	}
    });
    
    
    btn.onclick = function(){
    	obj.getById[holderId].exec("UPDATE_CONTENTS_FIELD", []);
    	if(checkFunc())
    	{
    		form.submit();
    	}
    };
}

function removeMyEditer()
{           
	$("#smartEditFrame").remove();
}