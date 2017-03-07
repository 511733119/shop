<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    <script type="text/javascript">
    	$(function(){
			var dg = parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg");
    		var rows= dg.datagrid("getSelections");
			//完成数据的回显
			$('#ff').form('load',{
				id:rows[0].id,
				type:rows[0].type,
				hot:rows[0].hot,
				'account.id':rows[0].account.id
			});
			//对管理员的下拉列表框进行远程加载
			$("#cc").combobox({
				url:'account_query.action',
				valueField:'id',
				textField:'login',
				panelHeight:'auto',
				panelWidth:120,
				width:120,
				editable:false
			});
    		$("input[name=type]").validatebox({
    			required:true,
    			missingMessage:'请输入类别名称'
    		});
    		//窗体弹出默认禁用验证
    		$("#ff").form("disableValidation");
    		//注册button事件
    		$("#btn").click(function(){
    			//开启验证
    			$("#ff").form("enableValidation");
    			//如果验证成功，则提交数据
    			if($("#ff").form("validate")){
    				//提交数据
    				$("#id").text(rows[0].id);
    				$('#ff').form('submit',{
    					
    					url:'category_update.action',
    					success:function(){
    						//关闭当前窗体
    						parent.$("#win").window("close");
    						//刷新页面
    						dg.datagrid("reload");
    					}
    				});
    			}
    			
    		});
    	});
    </script>
  </head>
  
  <body>
	<form id="ff" method="post">   
		<div>   
	        <input class="easyui-validatebox" type="hidden" id="id" name="id" value=""/>   
	    </div>  
	    <div>   
	        <label for="type">类别名称:</label>   
	        <input class="easyui-validatebox" type="text" name="type"/>   
	    </div> 
	    <div>   
	        <label for="hot">热点:</label>   
	      	  热点<input type="radio" name="hot" value="true" /> 
	      	  非热点<input type="radio" name="hot" value="false" checked="checked"/>     
	    </div><br>
	    <div>   
	        <label for="account">所属管理员:</label>  
			 <!-- 加载管理员数据 -->
			 <input id="cc" name="account.id"> 
	    </div><br>
	    <div>
	    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新</a>
	    </div> 
	</form>  

  </body>
</html>
