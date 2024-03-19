app=angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider){
    $routeProvider
    .when("/dashboad",{
        templateUrl:"dashboard/dashboard.html",
        controller:"dashboardCtrl"
    })
    .when("/product",{
        templateUrl:"product/product.html",
        controller:"productCtrl"
    })
    .when("/authorizing",{
		templateUrl:"authorizing/authorizing.html",
        controller:"authorizingCtrl"
	})
	.when("/order",{
		templateUrl:"order/orders.html",
        controller:"orderCtrl"
	})
	.when("/customer",{
		templateUrl:"customer/customer.html",
        controller:"customerCtrl"
	})
    .otherwise({
        templateUrl:"dashboard/dashboard.html",
        controller:"dashboardCtrl"
    })

})