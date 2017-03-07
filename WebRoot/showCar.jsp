<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="/public/head.jspf"%>
	<link rel="stylesheet" href="${shop}/css/public.css" />
	<link rel="stylesheet" href="${shop}/css/style.css" />
</head>

	<script type="text/javascript">
		$(function(){
			$(".text").change(function(){
				//验证数据有效性，必须是自然数
				var number = this.value;
				if(!isNaN(number) && parseInt(number)==number && number>0){
					$(this).attr("lang",number);
					var pid = $(this).parents("tr:first").attr("lang");
					//发送ajax请求，传输当前的数量与商品的id，返回的是总价格
					$.post("sorder_updateSorder.action",{number:number,'product.id':pid},function(total){
						$("#total").html(total);
					},"text");
					//保留两位小数
					var price = (($(this).parent().prev().html())*number).toFixed(2);
					//更新单个商品小计
					$(this).parent().next().html(price);
				}else{
					this.value=$(this).attr("lang");
				}
			});
			$("#checkAll").change(function(){
				$(".checkbox").prop('checked',$(this).prop("checked"));
			});
			
			$(".checkbox").change(function(){
				if(false==$(this).prop("checked")){
					$("#checkAll").prop("checked",false);
				}
				if($(".checkbox:checked").length==$(".checkbox").length){
					$("#checkAll").prop("checked",true);
				}
			});
			
			$("#del").click(function(){
				var selectItem ="";
		        $(":checkbox:checked").each(function() {
		        	if($(this).val()==""){
		        		selectItem ="";
		        	}else{
		            	selectItem += $(this).val()+',';// 在数组中追加元素
		        	}
		        });
		        if (selectItem.length == "") {
		            alert("请选择要删除的选项！");
		        }  else {
		        	$("#ids").val(selectItem) ;
		        	//$("form").attr("action","${shop}/product_deleteByCheck.action");
			  		//$("form").submit();
			  		$.post("product_deleteByCheck.action",{ids:$("#ids").val()},function(result){
			  				$("#tr").each(function(){
			  					$(this).remove();
			  					alert(1);
			  					window.location.href="showCar.jsp";
			  				});
			  		},"text");
		        }
			});
		});
	</script>
<body>
	<div class="wrapper"> 
		<%@include file="template/head.jsp" %>
		
	    <div class="section_container">
		
			<!-- 购物车 -->
			<div id="shopping_cart">
				<div class="message success">我的购物车</div>
				<c:choose>
				<c:when test="${empty sessionScope.forder}">
					你的购物车空空的~&nbsp;&nbsp;&nbsp;<a href="${shop}/index.jsp"><font color="red">开始购物</font></a>
				</c:when>
				<c:otherwise>
				<form id="form">
					<input type="hidden" name="ids" id="ids">
					<input type="submit" id="del" value="删除">
				</form>
				<table class="data-table cart-table" cellpadding="0" cellspacing="0">
					<tr>
						<th class="align_center" width="10%"><input type="checkbox" id="checkAll" value=""/></th>
						<th class="align_center" width="35%" colspan="2">&nbsp;商品名称</th>
						<th class="align_center" width="10%">销售价格</th>
						<th class="align_center" width="20%">数量</th>
						<th class="align_center" width="15%">小计</th>
						<th class="align_center" width="10%">删除</th>
					</tr>
					<c:forEach items="${sessionScope.forder.sorderSet}" var="sorder">
						<tr id="tr" lang="${sorder.product.id }">
							<td class="align_center"><input type="checkbox" class="checkbox" value="${sorder.product.id }"></td>
							<td width="80px" class="align_center"><img src="${shop}/image/product/${sorder.product.pic}" width="80px"
								height="80px" />
							</td>
							<td class="align_center">${sorder.name }</a>
							</td>
							<td class="align_center vline">
								${sorder.price }
							</td>
							<td class="align_center vline">
								<input class="text" style="height: 20px;" value="${sorder.number}" lang="${sorder.number}">		
							</td>
							<td class="align_center vline">
								${sorder.price*sorder.number }
							</td>
							<td class="align_center vline"><a href="${shop}/product_deleteById.action?id=${sorder.product.id}" onClick="return window.confirm('确认删除此商品吗?')" class="remove"></a>
							</td>
						</tr>
					</c:forEach>
				</table>
				</c:otherwise>
				</c:choose>
				
				<!-- 结算 -->
				<div class="totals">
					<table id="totals-table">
						<tbody>
							<tr>
								<td width="60%" colspan="1" class="align_left">运费</td>
								<td class="align_right" style=""><span class="price">￥0.00</span>
								</td>
							</tr>
							<tr>
								<td width="60%" colspan="1" class="align_left total"><strong>总计</strong>
								</td>
								<td class="align_right">￥
									<span id="total" class="total">
									<c:choose>
										<c:when test="${empty sessionScope.forder.total}">0.00</c:when>
										<c:otherwise>${sessionScope.forder.total}</c:otherwise>
									</c:choose>
									</span>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="action_buttonbar">
							<a href="${shop}/user/confirm.jsp">
								<button type="button" class="checkout fr" style="background-color: #f38256;">订单确认
								</button>
							</a>
							<a href="${shop}/product_deleteAll.action" >
								<button type="button" class=" fr">清空购物车
								</button>
							</a>
							<a href="${shop}/index.jsp">
								<button type="button"  class="continue fr">继续购物
								</button>
							</a>
						<div style="clear:both"></div>
					</div>
				</div>
			</div>
			
		</div>
		<%@include file="template/footer.jsp" %>
	</div>
	
</body>
</html>
