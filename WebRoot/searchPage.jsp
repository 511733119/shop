<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ include file="/public/head.jspf" %>
  	<script type="text/javascript" src="js/jquery.pagination.js"></script>
    <link href="css/pagination.css" rel="stylesheet" type="text/css"/>
    <%@ include file="template/head.jsp" %>
    <script type="text/javascript">
		$(document).ready(function(){
			function handlePaginationClick(new_page_index,pagination_container){
					$("input[name=pageNum]").val(new_page_index+1);
					$("#proform").attr("action","${shop}/product_getSearchProduct2.action");
			  		$("#proform").submit();
			  		return false;
		  	}
			
			$("#News-Pagination").pagination(${proList.totalRecord},{
				items_per_page:${proList.pageSize}, //每页显示多少条数据
				current_page:${proList.currentPage}-1, //当前显示第几页数据
				num_display_entries:5, //分页显示的条目数
				next_text:"下一页",
				prev_text:"上一页",
				num_edge_entries:2, //连接分页主体，显示的条目数
				callback:handlePaginationClick
			});
		});
	</script>
  </head>
  <body>
  	<div class="wrapper">
        <!-- 产品列表 -->
        <div class="products_list products_slider clear">
        	<div>
	            <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
	                 <!--  循环开始 -->
	       		 <c:forEach items="${proList.dataList}" var="product">
	                <li>
	                	 <a href="${shop}/product_get.action?id=${product.id}&typeid=${product.category.id}" class="product_image"><img src="${shop}/image/product/${product.pic}" style="width:220px;height:220px;"/></a>
	                     <div class="product_info">
	                     	<span style="color:red;font-size:17px;">￥${product.price }</span>
	                     	<h3><a href="#">商品名称:${product.name }</a></h3>
	                        <small>${product.remark}</small>
	                     </div>
	                </li>
	             </c:forEach>
	            </ul>
            </div>
            <div id="News-Pagination" style="margin-left:auto;margin-right:auto;"></div>
        </div>
        <form id="proform">
        	<input type="hidden" name="pageNum">
        </form>
         
        <!--产品列表结束  -->
        
	 	<%@ include file="template/footer.jsp" %>
       
   	 </div>
</body>
</html>
  