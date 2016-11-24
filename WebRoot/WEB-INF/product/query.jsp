<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    <style type="text/css">
    	body{
    		margin:1px;
    	}
    	.searchbox{
    		margin:-3;
    	}
    </style>
    <script type="text/javascript">
    $(function(){
    	 $('#dg').datagrid({    
    	        url:'product_queryJoinCategory.action',
    	        loadMsg:'请等待…………',
    	        queryParams:{name:''},
    	        //确定id字段为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页的时候被选中的记录不会丢失
    	        idField:'id',
    	       //显示斑马线
    	       striped:true,
    	       //自动适应列
    	       fitColumns:true,
    	       //数据显示在同一行
    	       nowrap:true,
    	       //是否分页
    	       pagination:true,
    	       singleSelect:false,
    	       toolbar: [{
    	   			iconCls: 'icon-add',
    	   			text:'添加商品',
    	   			handler: function(){
    	   				parent.$("#win").window({
    	   					title:'添加类别',
    	   					width:500,
    	   					height:600,
    	   					content:'<iframe src="send_product_save.action" frameborder="0" width="100%" height="100%"/>'
    	   				});
    	   			}
    	  	 	},'-',{
    	   			iconCls: 'icon-edit',
    	   			text:'更新商品',
    	   			handler: function(){
    	   				
    	   			}
    	  	 	},'-',{
    	   			iconCls: 'icon-remove',
    	   			text:'删除商品',
    	   			handler: function(){
    	   				
    	   			}
    	  	 	},'-',{
    	   			text:"<input id='ss' name='search'>"
    	  	 	}],
  
    	        // 同列属性，但是这些列将会被冻结在左侧
    	        frozenColumns:[[
    	             {field:'xyz',checkbox:true},
    	        	 {field:'id',title:'商品编号',width:100}
    	        ]],
    	        columns:[[    
    	            {field:'name',title:'商品名称',width:200},
    	            {field:'remark',title:'简单介绍',width:300},
    	            {field:'category.type',title:'商品类别',width:100,align:'right',
    	            	formatter: function(value,row,index){
    	    				if(row.category!=null && row.category.type!=null){
    	    					return row.category.type;
    	    				}
    	    			}	
    	            }
    	        ]]    
    	    });
    	 //把普通的文本框转化为搜索文本框
    	 $('#ss').searchbox({ 
    		 //触发查询事件
    		 searcher:function(value,name){ 
    			 //获取当前查询的关键字，通过dg加载相应的信息
    		 	//alert(value + "," + name);
    			 $('#dg').datagrid('load',{name:value});
    		 }, 
    		 prompt:'类别' 
    		 });
    });
   
    </script>
  </head>
  
  <body>
  	<table id="dg"></table>
  </body>
</html>
