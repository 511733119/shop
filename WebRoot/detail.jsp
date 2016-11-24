<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>${product.name}</title>
	<%@include file="/public/head.jspf"%>
	<link rel="stylesheet" href="${shop}/css/detail.css" />
</head>
<body>
	<%@ include file="template/head.jsp" %>
    <div class="wrapper">
        <div class="section_container">
            <!-- 面包屑 -->
            <ul class="breadcrumb">
                <li>
                    <a href="#">首页</a>
                </li>
                <li>
                    <a href="#">女装</a>
                </li>
                <li class="active">
                    <a href="#">长裙</a>
                </li>
            </ul>
            <!-- 产品详情 -->
            <div id="product_detail">
                <!--详情左侧-->
                <div class="product_leftcol fl">
                    <div id="jingdong">
                        <div class="datu">
                            <img src="${shop}/image/product/${product.pic}" />
                            <div id="fangdajing"></div>
                        </div>
                    </div>
                </div>
                <!--详情左侧结束-->
                <!--详情右侧-->
                <div class="product_rightcol fr">
                    <div id="name">
                        <h1>${product.name}</h1> <strong>${product.xremark }</strong>
                    </div>
                    <ul id="summary">
                        <li id="summary-price">
                            <div class="dt">价&nbsp;格：</div>
                            <div class="dd"> <strong class="p-price" id="jd-price">￥${product.price}</strong>
                            </div>
                        </li>
                    </ul>
                    <form action="${shop}/sorder_addSorder.action?product.id=${product.id}" method="post">
                    <ul id="choose">
                        <li id="choose-amount">
                            <div class="dt">购买数量：</div>
                            <div class="dd">
								 <input name="number" value="1" size="2" >
                            </div>
                        </li>
                    </ul>
                    <div class="add_to_buttons">
                        <button type="submit" class="add_cart">加入购物车</button>
                    </div>
                    </form>
                </div>
                <!--详情右侧结束--> </div>
            <!--产品详情结束-->
            <!-- 产品列表 -->
            <div class="products_list products_slider clear">
                <h2 class="sub_title">类似商品</h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                	<c:forEach items="${sessionScope.similar_product}" var="similar_product">
                    <li>
                        <a href="${shop}/product_get.action?id=${product.id}&typeid=${product.category.id}" class="product_image">
                            <img src="image/product/${similar_product.pic}" style="width:220px;height:220px;"/>
                        </a>
                        <div class="product_info">
                        	<span style="color:red;font-size:17px;">￥${product.price }</span>
                            <h3><a href="#">${similar_product.name }</a></h3>
                            <small>${similar_product.remark}</small>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
            </div>
            <!--产品列表结束  -->
            <!-- 导航栏结束 -->
            <%@ include file="template/footer.jsp" %>
           </div>
        </div>
	</body>
</html>
