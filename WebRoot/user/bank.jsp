
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/public/head.jspf"%>
</head>
<body>
	<div class="wrapper">
		<%@include file="../template/head.jsp"%>

		<div class="section_container">
			<!-- 购物车 -->
			<ul class="breadcrumb">
				<li><a href="#">加入购物车</a></li>
				<li><a href="#">确认订单信息</a></li>
				<li class="active"><a href="#">完成订单</a></li>
			</ul>
			<!-- 确认订单信息 -->
			<div class="pay-step">
				<!-- 订购人确认 -->
				<div class="person-check check">
					<h1>您的订单已经生成</h1>
					<div class="person-checkinner">
						<div>
							<span>订单号</span>：<span>${sessionScope.oldForder.id}</span>
						</div>
						<div>
							<span>收货人</span>：<span>${sessionScope.oldForder.name}</span>
						</div>
						<div>
							<span>送货地址</span>：<span>${sessionScope.oldForder.address}</span>
						</div>
						<div>
							<span>邮政编码</span>：<span>${sessionScope.oldForder.post}</span>
						</div>
						<div>
							<span>支付金额</span>：<span>${sessionScope.oldForder.total}</span>
						</div>
					</div>
				</div>
				<div class="pay">
					<div class="pay-inner">
						<div class="fl">支付方式:</div>
						<div class="fl yibao-logo">
							<a href=""><img src="${shop}/images/yibao.png" width="110"
								height="35" alt="" /></a>
						</div>
					</div>
				</div>
				<div class="select-bank">
					<form action="${shop}/pay_goBank.action" method="post">
						<h1>请选择支付银行</h1>
						<!--  {fn:indexOf(name,'.'))}  {fn:substring(zip, 6, -1)} -->
						<div>
							<ul>
								<c:forEach items="${applicationScope.bankList}" var="bankImage">
									<li><input type="radio" name="pd_FrpId"
										value="${fn:substring(bankImage,0,fn:indexOf(bankImage,'.'))}" />&nbsp;
										<img src="${shop}/image/bank/${bankImage}" /></li>
								</c:forEach>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="reminder">
							<span>请确保您的银行卡已开通网银支付功能，否则没有办法完成支付&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</div>
						<div class="pay-submit">
							<div class="pay-inner">
								<input type="submit" style="width: 80px; height: 40px;"
									value="确认提交" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<%@include file="../template/footer.jsp"%>
	</div>
</body>
</html>
