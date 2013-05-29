 
/*
* selectTag标签必需包含的js文件
* create by fenghanhao on 2011.03.08
*/
    /**
     * 当指定父元素时，根据父元素的值，过滤本元素的下列列表，并默认选中
     * parentId 父对象的元素html id 属性
     * targetId 加载数据的元素html id 属性
     * basePath  app前缀路径
     * defaultValue  默认选中值
     */

	function getDataByParent(parentId, targetId, basePath, 
	    parentKey,dictCode,listKey,listValue,headerKey,headerValue,
	    value, beforeSend, complete) {
		
		var parentVal = $("#" + parentId).val()+"";
		if(parentVal == null){
			return;
		}
		var requestMap = {
			dictCode : dictCode,
			parentKey : parentKey,
			parentValue : parentVal
		};

		var url = basePath + "/" + "selectTagAction!findDatas.action";
		$.ajax({
			url : url,
			type : "POST",
			async: true,
			data : requestMap,
			dataType : "json",
			beforeSend : function() {
				emptyTargetControl(targetId);
				eval(beforeSend + ";");
			},
			complete : function() {
				eval(complete + ";");
			},
			error: function(request) {
				alert("出错了，请稍候再试");
			},
			success : function(returnData) {
				var jsonValue = returnData.jsonValue;
				if(jsonValue != ''){
					josnValue = '(' + jsonValue + ');';
				}
				var datas = eval(jsonValue);
				setTargetControl(targetId, datas, listKey, listValue, value);
			}
		});
	}
	
	function setTargetControl(targetId, datas, listKey, listValue, value){
		
		if(null == datas){
			return;
		}
		var target = document.getElementById(targetId);
		var nodeName = target.nodeName;
		var len = datas.length;
		for ( var i = 0; i < len; i++) {
			var data = datas[i];
			var itemCode = data[listKey];
			var itemValue = data[listValue];
			if(nodeName == "DIV"){
				setRadioControl(target, itemCode, itemValue, value);
			}else{
				setSelectControl(target, itemCode, itemValue, value);
			}
		}
	}
	
	function setSelectControl(target, itemCode, itemValue, value){
		
		if (itemCode == value || itemValue == value) {
				$(target).append("<option value=" + itemCode
						+ " selected=selected >" + itemValue + "</option>");
		} else {
			$(target).append("<option value=" + itemCode 
					+ " >" + itemValue + "</option>");
		}
	}
	
	function setRadioControl(target, itemCode, itemValue, value){
		if (itemCode == value || itemValue == value) {
			$(target).append("<input type='radio' " 
						+ $(target).attr("title")
						+ " value=" + itemCode
						+ " checked='checked' />" + itemValue);
		} else {
			$(target).append("<input type='radio' " 
						+ $(target).attr("title")
						+ " value=" + itemCode 
						+ " >" + itemValue);
		}
	}
	
	function attacheParentEvent(parentId, targetId, basePath, parentKey, dictCode,
		listKey, listValue, headerKey, headerValue, value, beforeSend, complete) {
		
		var parent = document.getElementById(parentId);
		if($(parent).val() == null){
			return;
		}
		var nodeName = parent.nodeName;
		if(nodeName == "DIV"){
			attacheParentContainerEvent(parentId, targetId, basePath, parentKey, dictCode,
				listKey, listValue, headerKey, headerValue, value, beforeSend, complete);
		}else{
			attacheParentControlEvent(parentId, targetId, basePath, parentKey, dictCode,
				listKey, listValue, headerKey, headerValue, value, beforeSend, complete);
		}
	}
	
	function attacheParentContainerEvent(parentId, targetId, basePath, parentKey, dictCode,
		listKey, listValue, headerKey, headerValue, value, beforeSend, complete){
		var parent = document.getElementById(parentId);
		var cns = parent.childNodes;
		var len = cns.length;
		for(var i=0; i<len; i++){
			if(!cns[i].id){
				continue;
			}
			attacheParentControlEvent(cns[i].id, targetId, basePath, parentKey, dictCode,
				listKey, listValue, headerKey, headerValue, value, beforeSend, complete);
		}
	}
	
	function attacheParentControlEvent(parentId, targetId, basePath, parentKey, dictCode,
		listKey, listValue, headerKey, headerValue, value, beforeSend, complete){
		
		var parent = document.getElementById(parentId);
		var type = $(parent).attr("type");
		if (type == "select-one" || type == 'select-multiple') {
			parent.attachEvent("onchange", 
				attacheLinkEvent(parentId, targetId, basePath, parentKey, dictCode,
						listKey, listValue, headerKey, headerValue, value, beforeSend, complete));
		} else if(type == "radio"){
			parent.attachEvent("onclick", 
				attacheLinkEvent(parentId, targetId, basePath, parentKey, dictCode,
						listKey, listValue, headerKey, headerValue, value, beforeSend, complete));
		}
	}
	
	var attacheLinkEvent = 
		function(parentId, targetId, basePath, parentKey, dictCode,
						listKey, listValue, headerKey, headerValue, value, beforeSend, complete){
			return function(){
				getDataByParent(parentId, targetId, basePath, parentKey, dictCode,
					listKey, listValue, headerKey, headerValue, value, beforeSend, complete);
			}
		}
	
	function emptyTargetControl(targetId){
		
		var target = document.getElementById(targetId);
		var nodeName = target.nodeName;
		if(nodeName == "DIV"){
			emptyRadioControl(target);
		}else{
			emptySelectControl(target);
		}
	}
	
	function emptySelectControl(target){
		var len = target.length;
		for(var i=1; i<len; i++){
			target.options.remove(1);
		}
	}
	
	function emptyRadioControl(target){
	
		var childs = target.childNodes;
		var len = childs.length;
		for(var i=len-1; i>=0; i--) {      
    		target.removeChild(childs[i]);      
    	}
	}