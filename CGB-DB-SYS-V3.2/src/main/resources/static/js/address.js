$(function() {
	// 加载收货地址
	doFindAddresses();
	// 给购物车按钮绑定悬停事件
	$("#findShopObjects").mouseover(doLoadShopObjects);
	// 给收货地址绑定点击事件
	$(".addressUI").click(doFindAddresses);
	// 给删除按钮绑定点击事件
	$("#oldAddress").on("click", ".delete", doDeleteAddress);
	// 给设置默认地址按钮绑定点击事件
	$("#oldAddress").on("click", ".set", doSetAddress);
	// 给设置默认地址按钮绑定点击事件
	$("#oldAddress").on("click", ".update", doFindAddressById);
	// 给保存按钮绑定点击事件
	$(".address_form").on("click", ".save_address", doSaveAddressById);
});

// 保存收货地址
function doSaveAddressById() {
	var id = $(".address_bd").data("addressId");
	if (id) {
		// 修改收货地址
		doUpateAddressById(id);
	} else {
		// 新增收货地址
		doAddNewAddress();
	}
}

// 新增收货地址
function doAddNewAddress() {
	var address = getUserAddresses();
	var url = "add/doAddNewAddress";
	$.getJSON(url, address, function(result) {
		if (result.state == 1) {
			alert("添加成功");
			window.location.reload();
		} else {
			alert(result.message);
		}
	});
}

// 获取用户输入的信息
function getUserAddresses() {
	var name = $("input[name='name']").val();
	var add1 = $("#add1 option:selected").val();
	var add2 = $("#add2 option:selected").val();
	var add4 = $("input[name='add4']").val();
	var mobile = $("input[name='mobile']").val();
	var type = 0;
	if ($("input[name='type']").attr("checked"))
		type = 1;
	var params = {
		"name" : name,
		"add1" : add1,
		"add2" : add2,
		"add4" : add4,
		"mobile" : mobile,
		"type" : type
	};
	return params;
}

// 修改收货地址
function doUpateAddressById(id) {
	var url = "add/doUpdateAddressById";
	var address = getUserAddresses();
	address.id = id;
	$.getJSON(url, address, function(result) {
		if (result.state == 1) {
			alert("修改成功");
			window.location.reload();
		} else {
			alert(result.message);
		}
	});
}

// 点击修改查询当前收货地址信息
function doFindAddressById() {
	var param = {
		"id" : $(this).prop("name")
	};
	var url = "add/doFindAddressById";
	$.getJSON(url, param, function(result) {
		if (result.state == 1) {
			if (result.data) {
				doloadAddress(result.data);
			}
		} else {
			alert(result.message);
		}
	});
}

// 加载需要修改的收货地址
function doloadAddress(data) {
	$("input[name='name']").val(data.name);
	$("#add1").children().each(function() {
		if ($(this).val() == data.add1)
			$(this).attr('selected', true);
	});
	$("#add2").children().each(function() {
		if ($(this).val() == data.add2)
			$(this).attr('selected', true);
	});
	$("input[name='add4']").val(data.add4);
	$("input[name='mobile']").val(data.mobile);
	if (data.type == 1)
		$("input[name='type']").attr("checked", true);
	$(".address_bd").data("addressId", data.id);
}


// 设置默认地址
function doSetAddress() {
	var id = $(this).prop("name");
	var url = "add/doSetUserAddresses";
	var param = {
		"id" : id
	};
	$.getJSON(url, param, function(result) {
		if (result.state == 1) {
			alert("设置成功");
			doFindAddresses();
		} else {
			alert(result.message);
		}
	});
}

// 删除收货地址
function doDeleteAddress() {
	var url = "add/doDeleteUserAddresses";
	var dl = $(this).parent().parent();
	var id = $(this).prop("name");
	var param = {
		"id" : id
	};
	$.getJSON(url, param, function(result) {
		if (result.state == 1) {
			if (confirm("确认删除?")) {
				dl.remove();
			}
		} else {
			alert(result.message);
		}
	});
}

// 点击收货地址查询所有的收货地址
function doFindAddresses() {
	var url = "add/doFindUserAddresses";
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			if (result.data) {
				if (result.data) {
					$("#oldAddress").html("");
					loadOldAddress(result.data);
				} else {
					$("#oldAddress").html("请添加收货地址");
				}
			}
		} else {
			alert(result.message);
		}
	});
}

// 加载已有的收货地址(第一个为默认收货地址)
function loadOldAddress(data) {
	if (!data)
		$("#oldAddress").html("请添加收货地址")
	doLoadAllAddress(data);// 加载收货地址
}

// 加载收货地址
function doLoadAllAddress(data) {
	for (var i = 0; i < data.length; i++) {
		var dl = $("<dl name=" + data[i].id + "></dl>");
		if (i == 1) {
			dl = $("<dl class='last' name=" + data[i].id + "></dl>");
		}
		var dt = $("<dt>" + (i + 1) + "." + data[i].name + " " + data[i].add1
				+ " " + data[i].add2 + " " + data[i].add4 + " "
				+ data[i].mobile + " </dt>");
		var dd = $("<dd></dd>");
		var a = $(" <a href='#' class='update' name=" + data[i].id + ">修改</a> "
				+ " <a href='#' class='delete' name=" + data[i].id + ">删除</a> "
				+ " <a href='#' class='set' name=" + data[i].id
				+ ">设置默认地址</a> ");
		dd.append(a);
		dl.append(dt);
		dl.append(dd);
		$("#oldAddress").append(dl);
	}

}

// 鼠标悬浮在去"购物车结算"上时查询该用户购物车中的商品
function doLoadShopObjects() {
	$(".prompt").html("");
	var url = "user/doFindUserCommodity";
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			$(".prompt").html("点击去支付");
		} else {
			alert(result.message);
		}
	});
}