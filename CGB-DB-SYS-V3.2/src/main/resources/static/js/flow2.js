$(function() {
	// 加载购物车里的商品
	doLoadAllCommodity();
	// 加载收货地址
	doFindAllAddress();
	// 给保存信息按钮绑定点击事件
	$(".confirm_btn").click(saveUpdateAddress);
	// 给提交按钮绑定事件
	$("#submit_button").click(doPayMoney);
});

// 显示收货人信息
function showUserAddress() {
	$("#address_modify").show();
	$(".address_info").show();
	$(".address_select").hide();
}

// 购物车商品支付
function doPayMoney() {
	var payMoney = $("#submit_button").data("pay_money");
	var array = window.location.href.split('?')[1].split('=')[1].split(',');
	array = JSON.stringify(array);
	var add1 = $("#name_tel").text();
	var add2 = $("#address_default").text();
	var address = (add1 + " " + add2).split(" ");
	var name = address[0];
	var add1 = address[1];
	var add2 = address[2];
	var add4 = address[3];
	var mobile = address[4];
	var params = {
		"payMoney" : payMoney,
		"array" : array,
		"name" : name,
		"add1" : add1,
		"add2" : add2,
		"add4" : add4,
		"mobile" : mobile
	};
	var url = "cart/doPayMoney";
		if (confirm("确认支付?")) {
			$.getJSON(url, params, function(result) {
				if (result.state == 1) {
					window.location.href = "flow3";
				} else {
					alert(result.message);
				}
			});
		}
	
}

// 保存收货地址
function saveUpdateAddress() {
	var id = $(".none").data("addressId");
	var display = $("form[name=address_form]").css('display');
	if (display == 'none') {
		showUserAddress();
		doLoadUserDefaultAddress();
	} else {
		if (id) {
			// 修改收货地址
			doEditAddressById(id);
		} else {
			// 新增收货地址
			doInsertNewAddress();
		}
	}
}

// 新增收货地址
function doInsertNewAddress() {
	var address = getUserAddresses();
	var url = "add/doAddNewAddress";
	$.getJSON(url, address, function(result) {
		if (result.state == 1) {
			doFindAllAddress();
		} else {
			alert(result.message);
		}
	});
}

// 修改收货地址
function doEditAddressById(id) {
	var url = "add/doUpdateAddressById";
	var address = getUserAddresses();
	address.id = id;
	$.getJSON(url, address, function(result) {
		if (result.state == 1) {
			clearForm();
			doFindAllAddress();
		} else {
			alert(result.message);
		}
	});
}

// 编辑页面的显示
function editAddressById(id) {
	var param = {
		"id" : id
	};
	var url = "add/doFindAddressById";
	$.getJSON(url, param, function(result) {
		if (result.state == 1) {
			if (result.data) {
				doEditAddress(result.data);
			}
		} else {
			alert(result.message);
		}
	});
}
function doEditAddress(data) {
	$("input[name=name]").val(data.name);
	$("#add1").children().each(function() {
		if ($(this).val() == data.add1)
			$(this).attr('selected', true);
	});
	$("#add2").children().each(function() {
		if ($(this).val() == data.add2)
			$(this).attr('selected', true);
	});
	$("input[name=add4]").val(data.add4);
	$("input[name=mobile]").val(data.mobile);
	$(".none").data("addressId", data.id);
}

// 设置默认地址
function flowSetDefaultAddress() {
	var id = $(this).prop("name");
	var url = "add/doSetUserAddresses";
	var param = {
		"id" : id
	};
	$.getJSON(url, param, function(result) {
		if (result.state == 1) {
			alert("设置成功");
			$(".address_select li.cur").removeClass("cur").find("input").attr("checked",false);
			$(this).parent().addClass("cur").find("input").attr("checked",true);
		} else {
			alert(result.message);
		}
	});
}

// 删除收货地址
function flowDeleteAddress() {
	var url = "add/doDeleteUserAddresses";
	var li = $(this).parent();
	var id = $(this).prop("name");
	var param = {
		"id" : id
	};
	$.getJSON(url, param, function(result) {
		if (result.state == 1) {
			if (confirm("确认删除?")) {
				li.remove();
			}
		} else {
			alert(result.message);
		}
	});
}

// 查询所有的收货地址
function doFindAllAddress() {
	var url = "add/doFindUserAddresses";
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			if (result.data) {
				if (result.data) {
					showUserAddress();
					doLoadAAddresses(result.data);
					// 设为默认地址
					$(".set").click(flowSetDefaultAddress);
					// 删除
					$(".delete").click(flowDeleteAddress);
					// 显示编辑页面
					$(".new_address").click(new_address);
					$(".new_address").parent().siblings().find("input").click(
							otherInputs);
					// 编辑收货地址
					$(".update").click(new_address);
					$(".update").parent().siblings().find("input[class=sameC]")
							.click(otherInputs);
				} else {
					$(".address_select").html("请添加收货地址");
				}
			}
		} else {
			alert(result.message);
		}
	});
}

// 加载收货地址
function doLoadAAddresses(data) {
	$(".address_select>ul").remove();
	var ul = $("<ul></ul>");
	for (var i = 0; i < data.length; i++) {
		var li = $('<li>'
				+ '<input type="radio" class="sameC" name="address"/>'
				+ data[i].name + ' ' + data[i].add1 + ' ' + data[i].add2 + ' '
				+ data[i].add4 + ' ' + data[i].mobile + ' ' + '<a>设为默认地址</a> '
				+ '<a>编辑</a> ' + '<a>删除</a> ' + '</li>');
		if (data[i].type == 1 || data.length==1) {
			li = $('<li class="cur">'
					+ '<input type="radio" class="sameC" name="address" checked="checked" />'
					+ data[i].name + ' ' + data[i].add1 + ' ' + data[i].add2
					+ ' ' + data[i].add4 + ' ' + data[i].mobile + ' '
					+ '<a class="set" name="' + data[i].id + '">设为默认地址</a> '
					+ '<a class="update" name="' + data[i].id + '">编辑</a> '
					+ '<a class="delete" name="' + data[i].id + '">删除</a> '
					+ '</li>');
		}
		ul.append(li);
	}
	li = $('<li><input type="radio" name="address" class="new_address"  />使用新地址</li>')
	ul.append(li);
	$(".address_select").prepend(ul);

	// 加载当前用户默认收货地址
	doLoadUserDefaultAddress();
}

// 加载购物车里的商品
function doLoadAllCommodity() {
	var url = "cart/doFindAllCommodity";
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			if (result.data)
				loadAllCommodity(result.data);
		} else {
			alert(result.message);
		}
	});
}

// 加载需要支付的商品
function loadAllCommodity(data) {
	var array = window.location.href.split('?')[1].split('=')[1].split(',');
	var sum = 0;
	for (var i = 0; i < data.length; i++) {
		var price = data[i].price;
		
		sum = sum + (price * array[i]);
		var tr = $("<tr></tr>");
		var td1 = $("<td class='col1'><a><img src='" + data[i].url + "'/></a>"
				+ "<strong><a>" + data[i].name + "</a></strong></td>");
		var td2 = $("<td class='col2'><p>" + data[i].note + "</p></td>");
		var td3 = $("<td class='col3'>￥" + price + "</td>");
		var td4 = $("<td class='col4'>" + array[i] + "</td>");
		var td5 = $("<td class='col5'>￥<span>" + (price * array[i])
				+ "<span></td>");
		tr.append(td1);
		tr.append(td2);
		tr.append(td3);
		tr.append(td4);
		tr.append(td5);
		$("#tbCommodityId").append(tr);
	}
	// 显示总金额
	$("#sum_total").append(
			"<span>" + data.length + " 件商品，总商品金额：</span><em>￥" + sum.toFixed(2)
					+ "</em>");
	// 获取返现和运费(有需求再加)

	// 显示应付总额
	$("#actual_total").append("<em>￥" + sum.toFixed(2) + "</em>");
	$(".fillin_ft").find('strong').html("￥" + sum.toFixed(2) + "元");
	$("#submit_button").data("pay_money", sum.toFixed(2));
}

// 加载当前用户默认收货地址
function doLoadUserDefaultAddress() {
	var text = $(".address_select li.cur").text().split(' ');
	if(text){
		$("#name_tel").html(text[0] + " " + text[1]);
		$("#address_default").html(text[2] + " " + text[3] + " " + text[4]);
	}else{
		$("#name_tel").html("请添加收货地址");
	}
	
}

//显示修改区域
function new_address() {
	clearForm();
	$("form[name=address_form]").show();
	$(this).parent().addClass("cur").siblings().removeClass("cur");
	var id = $(this).prop("name");
	console.log(id);
	if (id && !id.isNaN)
		editAddressById(id);
}

//隐藏修改区域
function otherInputs() {
	$("form[name=address_form]").hide();
	$(this).parent().addClass("cur").siblings().removeClass("cur");
}

// 清空修改或新增地址的表单信息
function clearForm() {
	$("input[name=name]").val("");
	$("#add1").children().each(function() {
		if ($(this).val() == "请选择")
			$(this).attr('selected', true);
	});
	$("#add2").children().each(function() {
		if ($(this).val() == "请选择")
			$(this).attr('selected', true);
	});
	$("input[name=add4]").val("");
	$("input[name=mobile]").val("");
	$(".none").data("addressId", "");
}
