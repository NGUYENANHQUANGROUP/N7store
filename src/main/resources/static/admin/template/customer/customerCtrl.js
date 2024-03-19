app.controller("customerCtrl", function($scope, $http){
	$scope.customers = [];
	
		$scope.initialize = function() {
		//load product
		$http.get("/rest/accounts").then(resp => {
			$scope.customers = resp.data;
		});

	}
	$scope.initialize();
})