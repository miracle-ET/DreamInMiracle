$(function() {
	// 页面加载完显示该用户购物车里所有商品信息
	doFindAllCommodity();
	//给结算按钮绑定点击事件
	$(".checkout").click(loadFlow2AndCount);
});

function loadFlow2AndCount(){
	var count = $("input[class='amount']");
	var array = [];
	count.each(function(){
		array.push($(this).val());
	});
	if($("#tbCommodityId").children().length>0){
		window.location.href="flow2?str="+array;
	}else{
		alert("请先添加商品");
	}
	
}

// 删除商品信息
function doDeleteCommodityById() {
	var param = {
		"id" : $(this).prop("name")
	};
	var url = "cart/doDeleteCommodityById";
	$.getJSON(url, param, function(result) {
		if (result.state == 1) {
			$("#tbCommodityId").html("");
			$("#tfCommodityId").html("");
			if (confirm("确认删除?"))
				doFindAllCommodity();
		} else {
			alert(result.message)
		}
	});
}

// 页面加载完显示该用户购物车里所有商品信息
function doFindAllCommodity() {
	var url = "cart/doFindAllCommodity";
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			if (result.data) {
				doLoadAllCommodity(result.data);
				$(".reduce_num").click(reduce_num);
				$(".add_num").click(add_num);
				$(".amount").blur(amount);
				// 删除商品
				$(".deleteCom").click(doDeleteCommodityById);
			}else{
				alert("请先添加商品")
			}
		} else {
			alert(result.message);
		}
	});
}

// 加载购物内所有商品
function doLoadAllCommodity(data) {
	var sum = 0;
	for (var i = 0; i < data.length; i++) {
		var price = data[i].price;
		sum = sum + price;
		var tr = $("<tr></tr>");
		var td1 = $("<td class='col1'><a><img src='" + data[i].url + "'/></a>"
				+ "<strong><a>" + data[i].name + "</a></strong></td>");
		var td2 = $("<td class='col2'><p>" + data[i].note + "</p></td>");
		var td3 = $("<td class='col3'>￥<span>" + price + "</span></td>");
		var td4 = $('<td class="col4"><a href="javascript:;" class="reduce_num"></a><input type="text" name="amount" value="1" class="amount"/><a href="javascript:;" class="add_num"></a></td>');
		var td5 = $("<td class='col5'>￥<span>" + price + "<span></td>");
		var td6 = $("<td class='col6'><a class='deleteCom' name='" + data[i].id
				+ "'>删除</a></td>");
		tr.append(td1);
		tr.append(td2);
		tr.append(td3);
		tr.append(td4);
		tr.append(td5);
		tr.append(td6);
		$("#tbCommodityId").append(tr);
	}
	$("#tfCommodityId").append(
			"<tr><td colspan='6'>购物金额总计： <strong>￥ <span id='total'>"
					+ sum.toFixed(2) + "</span></strong></td></tr>");
}
