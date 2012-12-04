
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Boutique Registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/StyleSheet.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400'
	rel='stylesheet' type='text/css' />
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<!-- <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet"> -->

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<!-- <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png"> -->
</head>

<body ng-app="netVogue">

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				 <a class="brand" href="Netvogue.html"  style="padding-top: 6px; padding-bottom: 0px;">
           			<!--  <img class="AngularJS-small" src="img/Veawe_Logo.png"> -->
           			   <img class="AngularJS-small" src="img/AngularJS-small.png">
          			</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="brands.html">Brands</a></li>
						<li><a href="retailers.html">Retailers</a></li>
						<!-- <li><a href="#">Apply</a></li> -->
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container"  ng-controller="MyCtrlRegistration">
		<form novalidate name="form"  action="/resetPassword" method="post">
			<h2 style="font-family: 'Lato', sans-serif; font-weight: 300;">Forgot
				Your password ?</h2>
			<div class="span6"><label>Username
				or email</label> <input type="text" ng-model="entity.email" class="span5"
				maxlength="50" name="uEmail" required
				ng-pattern="/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/"
				ui-validate="{oldemail:emailchanged}"
				placeholder="Username or email">
				<button type="submit" style="margin-bottom: 10px;"
				data-loading-text="Please wait..." 
				class="btn btn-inverse">Submit</button>
			</div>
		</form>

		<!-- Example row of columns -->
		<div class="row" style="min-height:350px;"></div>
		<hr>
		<footer>
			<p class="pull-right">
				<a href="#">Back to top</a>
			</p>
			<p>
				© 2012 Company, Inc.<a href="#">About</a> | <a href="#">Contact</a>
				| <a href="#">Privacy</a> | <a href="#">Terms</a>
			</p>
		</footer>

	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script type="text/javascript">
		var entity = "boutique";
	</script>
	<script src="lib/jquery/jquery.js" type="text/javascript"></script>
	<script src="lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
	<script src="lib/angular/angular.js" type="text/javascript"></script>
	<script src="js/regcontroller.js" type="text/javascript"></script>


</body>
</html>
