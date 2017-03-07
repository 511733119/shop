<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/public/head.jspf"%>
</head>
<body>
	<div class="wrapper">
		 <%@include file="../template/head.jsp" %>

		<div class="section_container">
			<div class="pay-skip">
				<h1>感谢您在本商城购物</h1>
				<div class="payskip-inner">
					<div>
						<span>订单号:</span><strong>${sessionScope.oldForder.id}</strong><span>&#91;请您记住这个号码，以便在付款和查询时使用&#93;</span>
					</div>
					<div>
						<span>支付金额：</span><strong>￥${sessionScope.oldForder.total}</strong>
					</div>
					<div>
						<span>支付方式：</span><img class="zffs" src="${shop}/images/yibao.png" alt="" />
					</div>
					<div>
						<span>支付银行：</span><img class="zfyh" src="${shop}/image/bank/${requestScope.pd_FrpId}.gif"  />
					</div>
					<div>
					    <form name="yeepay" action='https://www.yeepay.com/app-merchant-proxy/node' method='POST' target="_blank">
							<input type='hidden' name='p0_Cmd'   value='${requestScope.p0_Cmd}'>
							<input type='hidden' name='p1_MerId' value='${requestScope.p1_MerId}'>
							<input type='hidden' name='p2_Order' value='${requestScope.p2_Order}'>
							<input type='hidden' name='p3_Amt'   value='${requestScope.p3_Amt}'>
							<input type='hidden' name='p4_Cur'   value='${requestScope.p4_Cur}'>
							<input type='hidden' name='p5_Pid'   value='${requestScope.p5_Pid}'>
							<input type='hidden' name='p6_Pcat'  value='${requestScope.p6_Pcat}'>
							<input type='hidden' name='p7_Pdesc' value='${requestScope.p7_Pdesc}'>
							<input type='hidden' name='p8_Url'   value='${requestScope.p8_Url}'>
							<input type='hidden' name='p9_SAF'   value='${requestScope.p9_SAF}'>
							<input type='hidden' name='pa_MP'    value='${requestScope.pa_MP}'>
							<input type='hidden' name='pd_FrpId' value='${requestScope.pd_FrpId}'>
							<input type="hidden" name="pr_NeedResponse"  value="${requestScope.pr_NeedResponse}">
							<input type='hidden' name='hmac' value='${requestScope.hmac}'>
							<input type='submit' value="去支付" style="width: 80px; height: 40px;" />
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../template/footer.jsp" %>
	</div>
</body>
</html>
