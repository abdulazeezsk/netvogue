<!doctype html>
<html lang="en" ng-app="netvogue-admin">
<head>
<meta charset="utf-8">
<title>Veawe-Admin</title>
<link rel="stylesheet" href="css/app.css" />
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
</head>
<body>

	
	
	<!-- Nav bar start -->
	
	<div class="navbar navbar-inverse">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<a class="brand" href="#" name="top">Brand Name</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="#/home"><i class="icon-home icon-white"></i> Home</a></li>
					<li class="divider-vertical"></li>
					<li class="active"><a href="#/boutiques"><i class="icon-file icon-white"></i> Boutiques</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#/brands"><i class="icon-file icon-white"></i> Brands</a></li>
					<li class="divider-vertical"></li>
                  	<li><a href="#"><i class="icon-file icon-white"></i> Consumers</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#"><i class="icon-file icon-white"></i> Categories</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#/activate"><i class="icon-file icon-white"></i> Activate User</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#/deactivate"><i class="icon-file icon-white"></i> Deactivate User</a></li>
					<li class="divider-vertical"></li>
				</ul>
				<div class="btn-group pull-right">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i> admin	<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="icon-wrench"></i> Settings</a></li>
						<li class="divider"></li>
						<li><a href="#"><i class="icon-share"></i> Logout</a></li>
					</ul>
				</div>
			</div>
			<!--/.nav-collapse -->
		</div>
		<!--/.container-fluid -->
	</div>
	<!--/.navbar-inner -->
	
	<div ng-view></div>
</div>
<!--/.navbar -->
	<!-- Nav bar end -->

	<!--  User table code start  -->	

<!-- 	<div class="btn-toolbar">
		<button class="btn btn-primary">New User</button>
		<button class="btn">Import</button>
		<button class="btn">Export</button>
	</div>  -->


	<!-- Table end -->

	<!-- In production use:
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.0.4/angular.min.js"></script>
  -->
	<script src="lib/angular/angular.js"></script>
	<script src="js/app.js"></script>
	<script src="js/services.js"></script>
	<script src="js/controllers.js"></script>
	<script src="js/filters.js"></script>
	<script src="js/directives.js"></script>
</body>
</html>
