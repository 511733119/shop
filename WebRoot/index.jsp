<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ include file="/public/head.jspf" %>
    <%@ include file="template/head.jsp" %>
  </head>
  
  <body>
  	<div class="wrapper">
        <div class="section_container">
            <!--左侧导航-->
            <div id="side_nav">
                <div class="sideNavCategories">
                    <ul class="category departments">
                        <li class="header">分类</li>
                        <li><a href="#">毛衣</a></li>
                        <li><a href="#">针织衫 </a></li>
                        <li><a href="#">衬衫 </a></li>
                        <li><a href="#">T恤</a></li>
                        <li><a href="#">短裤</a></li>
                        <li><a href="#">牛仔 </a></li>
                        <li><a href="#">连身裙</a></li>
                        <li><a href="#">短裙 </a></li>
                    </ul>
                </div>
            </div>
            <!-- 右侧焦点图区域 -->
            <div id="main_content ">
                <div>
                	 <a href="#"><img src="images/big_pic.jpg" style="width:770px;height:400px; padding-top:5px;padding-left:10px;" /> </a>
                </div>
            </div>
        </div>

        <!-- 产品列表 -->
        <div class="products_list products_slider clear">
        	<!--  大循环开始 -->
        	<c:forEach items="${applicationScope.bigList}" var="list">
            <h2 class="sub_title">${list[0].category.type}</h2>
            <h4 style="font-weight: normal"><a href="${shop}/category_listProduct.action?id=${list[0].category.id}">查看全部 >></a></h4>
            
            
            <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                 <!--  小循环开始 -->
       		 <c:forEach items="${list}" var="product">
                <li>
                	 <a href="${shop}/product_get.action?id=${product.id}&typeid=${list[0].category.id}" class="product_image"><img src="${shop}/image/product/${product.pic}" style="width:220px;height:220px;"/></a>
                     <div class="product_info">
                     	<span style="color:red;font-size:17px;">￥${product.price }</span>
                     	<h3><a href="#">商品名称:${product.name }</a></h3>
                        <small>${product.remark }</small>
                     </div>
                </li>
                </c:forEach>
            </ul>
            </c:forEach>
        </div>
        <!--产品列表结束  -->
        
	 	<%@ include file="template/footer.jsp" %>
        
   	 </div>
</body>
</html>
