<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="js/searchBox.js"></script>
	<style type="text/css">
		#popDiv{
			position:absolute;
			z-index:1;
		}
    	.mouseOver{
    		background: #708090;
    		color:#FFFAFA;
    	}
    	.mouseOut{
    		background: #FFFAFA;
    		color:#000;
    	}
    </style>
</head>
  <body>
     <div class="header">
            <div class="header_container">
                <!--头部开始-->
                  <div class="top_bar clear">
                      <!--头部小导航-->
                      <div class="welcom fl">
                      	<ul class="top_links">
                      		<li>
						<c:choose>
							<c:when test="${empty sessionScope.user }">
								<a href="${shop}/ulogin.jsp"><font style="color:red;">亲 , 请登录</font></a>
							</c:when>
							<c:otherwise>
								<a href="#">欢迎你，${sessionScope.user.name }</a>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
			  </div>
                      <ul class="top_links fr">
                          <li><a href="${shop}/index.jsp">首页</a></li>
                          <li><a href="#">我的账户</a></li>
                          <li><a href="${shop}/showCar.jsp">购物车</a></li>
                          <li><a href="#">注册</a></li>
                      </ul>
                      <!--头部小导航结束-->
                      <!-- logo -->
                      <h1 class="logo clear fl"> <a href="index.jsp"><img src="${shop}/images/JD_logo.jpg" 
                      	style="width:220px;height:80px;" /></a> </h1>
                      <!-- 购物车结束 -->
                      <!-- 搜索框 -->
               
                   <div id="mydiv">
                   		<form action="${shop}/product_getSearchProduct.action" method="post">
                        	<input id="name" name="name" size="50" placeholder="请输入商品名称" type="text" 
                        		onKeyup="getMoreContents()" onBlur="keywordBlur()"
                        		onFocus="getMoreContents()" AutoComplete="off"/>
                       		<button type="submit"></button>
                       	</form>
	                    <!-- 下面是内容展示区域 -->
				   		<div id="popDiv">
					   		<table id="content-table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0">
					   			<tbody id="content_table_body">
					   				<!-- 动态查询出来的数据显示在这里 -->
					   			</tbody>
					   		</table>
					   	</div>
                    </div>
                 </div>
            </div>
        </div>
        <!-- 头部结束 -->

        <!-- 导航栏 -->
        <div class="navigation_container">
        <!---->
         <div class="nav">
            <ul class="primary_nav">
                <li><a href="index.jsp">所有商品</a>
                    <!--二级菜单-->
                    <ul class="sub_menu">
                        <li > <a href="#">裙装</a>
                            <ul>
                                <li><a href="#">短裙</a></li>
                                <li><a href="#">短裤 </a></li>
                                <li><a href="#">裤子</a></li>
                                <li><a href="#">卡其裤</a></li>
                                <li><a href="#">休闲裤</a></li>
                                <li><a href="#">牛仔裤</a></li>
                                <li><a href="#">风衣 / 运动夹克</a></li>
                            </ul>
                        </li>
                        <li> <a href="#">装饰品 </a>
                            <ul>
                                <li><a href="#"> 太阳镜</a></li>
                                <li><a href="#">围巾 </a></li>
                                <li><a href="#">发饰品 </a></li>
                                <li><a href="#">帽子和手套 </a></li>
                                <li><a href="#">生活时尚 </a></li>
                                <li><a href="#">牛仔系列 </a></li>
                                <li><a href="#">风衣 / 西服</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                    <!--二级菜单结束-->
                
                <li><a href="#">男装</a>
                    <!--二级菜单-->
                    <ul class="sub_menu">
                        <li> <a href="#">男士夏装</a>
                            <ul>
                                <li><a href="#">裤子</a></li>
                                <li><a href="#">休闲裤</a></li>
                                <li><a href="#">卡其裤</a></li>
                                <li><a href="#">牛仔裤</a></li>
                                <li><a href="#">风衣 / 运动夹克</a></li>
                            </ul>
                        </li>
                         <li> <a href="#">装饰品 </a>
                            <ul>
                                <li><a href="#"> 太阳镜</a></li>
                                <li><a href="#">围巾 </a></li>
                                <li><a href="#">发饰品 </a></li>
                                <li><a href="#">帽子和手套 </a></li>
                                <li><a href="#">生活时尚 </a></li>
                                <li><a href="#">牛仔系列 </a></li>
                                <li><a href="#">风衣 / 西服</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!--二级菜单结束-->
                </li>
                <li><a href="#">儿童</a></li>
                <li><a href="#">时尚</a></li>
                <li><a href="#">装饰品 </a></li>
            </ul>
        </div>
      </div>
        <!--导航栏结束-->
  </body>
</html>
