app.controller("orderCtrl", function($scope, $http) {
	$scope.orders = [];
	$scope.initialize = function() {
		//Load Orders
		$http.get("/rest/orders").then(resp => {
			$scope.orders = resp.data;
			$scope.orders.forEach(order => {
				order.createDate = new Date(order.createDate);
				
			})
			
		});
		

	}
	
	$scope.orderDetails = [];
	
	$scope.getAllOrderDetails = function(order){
		$http.get(`/rest/orderDetails/${order.id}`).then(resp => {
			$scope.orderDetails = resp.data
			$scope.totalPrice = 0;
			$scope.orderDetails.forEach(orderDetail => {
					$scope.totalPrice += orderDetail.price*orderDetail.quantity
				})
		})
	}
	
	$scope.form = {};
	$scope.edit = function(order){
		$scope.form = angular.copy(order);
		$(".nav-tabs button:eq(1)").tab("show")
		$scope.getAllOrderDetails(order)
	}
	$scope.initialize();
})