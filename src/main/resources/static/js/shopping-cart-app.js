const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http, $window) {
	$scope.cart = { // co tac dung trong toan bo giao dien
		items: [],
		// Thêm sản phẩm vào giỏ hàng
		add(id) {
			var item = this.items.find(item => item.id == id); // kiểm tra xem trong items có sản phẩm mới thêm vào khoong
			if (item) { // nếu có thì chỉ tăng số lượng
				item.qty++;
				this.saveToLocalStorage();
			} else { // nếu chưa có thì tải sp từ trên server về thông qua rest
				$http.get(`/rest/products/${id}`).then(resp => {
					alert('Đã thêm sản phẩm vào giỏ hàng !');
					resp.data.qty = 1; // sau khi tải về gán sl = 1;
					this.items.push(resp.data); // thêm vào ds giỏ hàng (items)
					this.saveToLocalStorage(); // lưu vào local
				})
			}
		},
		add_qty(id) {
			var qty = parseInt($("#quantityInput").val(), 10);
			var item = this.items.find(item => item.id == id); // kiểm tra xem trong items có sản phẩm mới thêm vào khoong
			if (item) { // nếu có thì chỉ tăng số lượng
				if (!isNaN(qty)) {
					item.qty += qty;
					this.saveToLocalStorage();
				} else {
					alert("Invalid quantity value");
				}
			} else { // nếu chưa có thì tải sp từ trên server về thông qua rest
				if (!isNaN(qty)) {
					$http.get(`/rest/products/${id}`).then(resp => {
						alert('Đã thêm sản phẩm vào giỏ hàng !');
						resp.data.qty = qty; // sau khi tải về gán sl = 1;
						this.items.push(resp.data); // thêm vào ds giỏ hàng (items)
						this.saveToLocalStorage(); // lưu vào local
					})
				} else {
					alert("Invalid quantity value");
				}

			}
		},

		// Xóa sản phẩm khỏi giỏ hàng
		remove(id) {
			var index = this.items.findIndex(item => item.id == id); // tìm vị trí của sp trong giỏ hàng thông qua id truyền vào
			this.items.splice(index, 1); // khi có vị trí dùng phương thức splice để xóa 1 phần tử ra khỏi mảng
			this.saveToLocalStorage(); // xóa xong thì lưu lại
		},

		// Xóa sạch giỏ hàng
		clear() {
			this.items = []; // cho mảng thành rỗng
			this.saveToLocalStorage(); // lưu lại
		},

		// Tính tổng số lượng các mặt hàng trong giỏ hàng
		get count() {
			return this.items.map(item => item.qty) // dùng map để lấy qty
				.reduce((total, qty) => total += qty, 0); // tính tổng qty
		},

		// Tính tổng thành tiền của các mặt hàng trong giỏ hàng
		get amount() {
			return this.items.map(item => item.qty * item.product_price) // dùng map để lấy qty * price ra giá
				.reduce((total, qty) => total += qty, 0);
		},

		// Hàm lưu vào local
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items)); // dùng angular copy (items) và đổi mảng mặt hàng (items) sang JSON
			localStorage.setItem("cart", json); // lưu chuối json và localStorage với tên là "cart"
		},

		// Đọc giỏ hàng từ localStorage
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart"); // lấy "cart" từ localstorage
			json != null ? this.items = JSON.parse(json) : this.items = []; // nếu có thì chuyển sang JSON và gán vào item còn không thì gán rỗng
		},

		// tăng số lượng sp khi bấm
		increase(id) {
			var item = this.items.find(item => item.id == id); // tìm item thông qua id
			item.qty++;
			this.saveToLocalStorage();
		},

		// giảm số lượng sản phẩm khi bấm
		reduce(id) {
			var item = this.items.find(item => item.id == id); // tìm item thông qua id
			if (item.qty > 1) { // sl lớn hơn 1 mới cho giảm
				item.qty--;
			}
			this.saveToLocalStorage();
		}
	}

	$scope.cart.loadFromLocalStorage(); // tải lại toàn bộ mặt hàng trong local khi ứng dụng khởi tạo

	$scope.placeOrder = function() {
    if ($scope.paymentMethod == 1) {
        $scope.order.dh();
    } else {
		$scope.order.go()
    }
}

	$scope.order = {
		createDate: new Date(), // lấy ngày hiện tại
		address: "",
		phonenumber: $("#username").text(),
		orderStatus: { id: "CXN" }, // CHỜ XN
		orderMethod: { id: 1 },
		account: { username: $("#username").text() }, // lấy username trong input có id là username
		fullname: "",
		get orderDetails() { // các mặt hàng trong giỏ chuyển sang chi tiết hóa đơn
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.product_price,
					quantity: item.qty
				}
			});
		},
		go(){
			if (this.address == null || this.address == undefined || this.address.length <= 0) {
				alert("Vui lòng nhập địa chỉ giao hàng !")
			} else if (this.phonenumber == null || this.phonenumber == undefined || this.phonenumber.length <= 7) {
				alert("Vui lòng nhập số điện thoại !")
			} else {
				this.orderMethod.id = parseInt($scope.paymentMethod);
				localStorage.setItem("orderVNPay", JSON.stringify(angular.copy(this)));
				console.log(localStorage.getItem("orderVNPay"))
				$window.location.href = '/api/payment/create_payment';
				}
		},
		dh() {
			if (this.address == null || this.address == undefined || this.address.length <= 0) {
				alert("Vui lòng nhập địa chỉ giao hàng !")
			} else if (this.phonenumber == null || this.phonenumber == undefined || this.phonenumber.length <= 7) {
				alert("Vui lòng nhập số điện thoại !")
			} else {
				this.orderMethod.id = parseInt($scope.paymentMethod);
				var order = angular.copy(this); // lấy order hiện tại
				
				$http.post("/rest/orders", order).then(resp => { // post order lên đại chỉ
					alert("Đặt hàng thành công !");
					$scope.cart.clear(); // xóa giỏ hàng
					location.href = "/order/detail/" + resp.data.id; // chuyển sang trang chi tiết đơn hàng
				}).catch(error => {
					alert("Đặt hàng thất bại !");
					console.log(error);
				})
			}

		},
		
		
		
	}
	



})

app.controller("orderVNPay", function($scope, $http, $window){
	var orderVNPay = localStorage.getItem("orderVNPay");
  console.log(orderVNPay);
	$http.post("/rest/orders", orderVNPay).then(resp => { // post order lên đại chỉ
					alert("Đặt hàng thành công !");
					$scope.cart.clear(); // xóa giỏ hàng
					localStorage.removeItem("orderVNPay")
					location.href = "/order/detail/" + resp.data.id; // chuyển sang trang chi tiết đơn hàng
				}).catch(error => {
					alert("Đặt hàng thất bại !");
					console.log(error);
				})
})
