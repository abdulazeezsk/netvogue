
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Veawe Login</title>
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
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container" ng-controller="MyCtrlRegistration">

		<!-- Main hero unit for a primary marketing message or call to action -->
		<!--  <div class="hero-unit">
        <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
        <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
      </div> -->
		<form novalidate name="form" action="j_spring_security_check" method="post">
			<h2 style="font-family: 'Lato', sans-serif; font-weight: 300;">Wrong
				Username/Email and password combination.</h2>
			<label>Username
				or email</label> <input type="text" id="j_username" name="j_username" class="span5"
				maxlength="50" required				
				placeholder="Username or email">
				
				
				
				 <label>Password</label>
			<input type="password" ng-model="entity.password" class="span5"
				placeholder="Password" id="j_password" name="j_password" 
				required maxlength="20">
				<span>
				Forgot your <a href="forgotpassword.html" title="" tabindex="-1">password</a>?
				</span>
				
				 <div class="captcha js-captcha hidden">
                            <script type="text/javascript">
                                var RecaptchaOptions = {
                                    theme: 'white',
                                    lang: 'en'
                                };
								</script>
                            <script type="text/javascript" src="http://www.google.com/recaptcha/api/challenge?k=6Lf_mdESAAAAAOxd0wxmGUUMRpd9S8Os45IPmq2t&amp;lang=en"></script>
                            <script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha.js"></script>
                            <div id="recaptcha_widget_div" class=" recaptcha_nothad_incorrect_sol recaptcha_isnot_showing_audio">
                                <div id="recaptcha_area">
                                </div>
                            </div>
                            <script>
                                Recaptcha.widget = Recaptcha
											.$("recaptcha_widget_div");
                                Recaptcha.challenge_callback();
								</script>
                        </div>
				
				<div class="span5">
			<button type="submit"
				data-loading-text="Please wait..." style="margin-right:20px;" 
				class="btn btn-inverse pull-right" >Submit</button>
				</div>
		</form>

		<!-- Example row of columns -->
		<div class="row"></div>

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
