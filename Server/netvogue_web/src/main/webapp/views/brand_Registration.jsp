
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Brand Registration</title>
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

<body ng-app="netVogue" >

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="Netvogue.html">VEAWE</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="#">Designers</a></li>
						<li><a href="#">Retailers</a></li>
					</ul>
					<form class="navbar-form pull-right" accept-charset="UTF-8" action="j_spring_security_check" method="post" >
						<input id="j_username" name="j_username" class="span3" type="text" placeholder="Email"> <input
							id="j_password" name="j_password" class="span3" type="password" placeholder="Password">
						<button type="submit" style="font-family: 'Lato', sans-serif; font-weight: 100;" class="btn btn-inverse">Sign in</button>
					</form>
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
		<form novalidate  name="form">
			<h2 style="font-family: 'Lato', sans-serif; font-weight: 300;" >Hello Designers! Lets Get You Started</h2>
			<label style="font-family: 'Lato', sans-serif; font-weight: 400;" >Email Address</label>
			
			 <input type="text" ng-model="entity.email" class="span5"  maxlength="50" name="uEmail" required ng-pattern="/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/"
              ui-validate="{oldemail:emailchanged}" 	placeholder="Email Address">
			  <span ng-show="form.uEmail.$dirty && form.uEmail.$invalid">
              <span ng-show="form.uEmail.$error.required" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;" class="validation_msg">Required</span>
              <span ng-show="form.uEmail.$error.pattern" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;" class="validation_msg">Invalid</span>
              <span ng-show="form.uEmail.$error.oldemail" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;" class="validation_msg">This email is already
              registered</span>
              </span>
				
				
				 <label style="font-family: 'Lato', sans-serif; font-weight: 400;" >User Name</label>
				 
				  <input type="text" ng-model="entity.username" class="span5" placeholder="User Name"
				   name="uUserName" ng-maxlength="25" required maxlength="26" ui-validate="{oldusername:usernamechanged}" >
				   <span ng-show="form.uUserName.$dirty && form.uUserName.$invalid">
                   <span ng-show="form.uUserName.$error.required" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;" class="validation_msg">Required</span>
                   <span ng-show="form.uUserName.$error.oldusername" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;"
                   class="validation_msg"> Username registered</span>
                  </span>
				  
				 <label style="font-family: 'Lato', sans-serif; font-weight: 400;" >Password</label>
				<input type="password" ng-model="entity.password" class="span5" placeholder="Password" ng-model="entity.password" 
                                name="uPassword" required maxlength="10">
                                <span ng-show="form.uPassword.$dirty && form.uPassword.$invalid">
                                <span ng-show="form.uPassword.$error.required" class="validation_msg" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;">Required</span>
                            </span>
			 <label style="font-family: 'Lato', sans-serif; font-weight: 400;" >Confirm
				Password</label>
				
				
				
				
				
				 <input type="password" ng-model="confirmpassword" class="span5"
				placeholder="Confirm Password"  ng-model="confirmpassword" 
                                name="uConfirmPassword" required maxlength="10" same-as="{{entity.password}}">
                                <span ng-show="form.uConfirmPassword.$dirty && form.uConfirmPassword.$invalid">
                                <span ng-show="form.uConfirmPassword.$error.required" class="validation_msg" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;">
                                    Required</span>
                                     <span ng-show="form.uConfirmPassword.$error.sameAs" class="validation_msg" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;">
                                        Password Is Not Matching</span>
                            </span>
				
				
				
				 <label style="font-family: 'Lato', sans-serif; font-weight: 400;" >Brand Name</label> 
				 <input type="text" ng-model="entity.name"  name="uBrandName" required maxlength="25" class="span5" placeholder="Brand Name"> 
				 <span ng-show="form.uBrandName.$dirty && form.uBrandName.$invalid">
                 <span ng-show="form.uBrandName.$error.required" class="validation_msg" style="font-family: 'Lato', sans-serif; font-weight: 300;color:red;">Required</span>
                 </span>
				
				<label style="font-family: 'Lato', sans-serif; font-weight: 400;" >Primary
				Conatct</label>
				 <input type="text" class="span5" ng-model="entity.primarycontact"
				placeholder="Primary Conatct Person"> 
				<label style="font-family: 'Lato', sans-serif; font-weight: 400;" >Mobile
				 Number</label> 
				<input type="text" class="span5" ng-model="entity.mobile"
				placeholder="Primary Conatct Mobile"> 
				<label style="font-family: 'Lato', sans-serif; font-weight: 400;"
				class="checkbox"> <input type="checkbox"> I Agree to Terms & Conditions
				
			</label>
			<button type="submit" ng-disabled="form.$invalid"
                                data-loading-text="Please wait..." ng-click="addEntity($event)" class="btn  btn-large btn-inverse pull-right">Submit</button>
		</form>

		<!-- Example row of columns -->
		<div class="row">
		</div> 

		<hr>

		<footer>
		<p class="pull-right">
			<a href="#">Back to top</a>
		</p>
		<p>
		© 2012 Company, Inc.<a href="#">About</a> | <a href="#">Contact</a> | <a href="#">Privacy</a> | <a href="#">Terms</a>
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
