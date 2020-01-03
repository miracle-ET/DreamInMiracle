/*
@功能：购物车页面js
@作者：diamondwang
@时间：2013年11月14日
 */

//减少
function reduce_num() {
	var amount = $(this).parent().find(".amount");
	if (parseInt($(amount).val()) <= 1) {
		alert("商品数量最少为1");
	} else {
		$(amount).val(parseInt($(amount).val()) - 1);
	}
	// 小计
	var subtotal = parseFloat($(this).parent().parent().find(".col3 span")
			.text())
			* parseInt($(amount).val());
	$(this).parent().parent().find(".col5 span").text(subtotal.toFixed(2));
	// 总计金额
	var total = 0;
	$(".col5 span").each(function() {
		total += parseFloat($(this).text()-0);
	});

	$("#total").text(total.toFixed(2));
}

// 增加
function add_num() {
	var amount = $(this).parent().find(".amount");
	$(amount).val(parseInt($(amount).val()) + 1);
	// 小计
	var subtotal = parseFloat($(this).parent().parent().find(".col3 span")
			.text())
			* parseInt($(amount).val());
	$(this).parent().parent().find(".col5 span").text(subtotal.toFixed(2));
	// 总计金额
	var total = 0;
	$(".col5 span").each(function() {
		total += parseFloat($(this).text()-0);
	});

	$("#total").text(total.toFixed(2));
}

// 直接输入
function amount() {
	if (parseInt($(this).val()) < 1) {
		alert("商品数量最少为1");
		$(this).val(1);
	}
	// 小计
	var subtotal = parseFloat($(this).parent().parent().find(".col3 span")
			.text())
			* parseInt($(this).val());
	$(this).parent().parent().find(".col5 span").text(subtotal.toFixed(2));
	// 总计金额
	var total = 0;
	$(".col5 span").each(function() {
		total += parseFloat($(this).text()-0);
	});

	$("#total").text(total.toFixed(2));
}
