<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Brand Registration</title>
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/StyleSheet.css" rel="stylesheet" />
    <style type="text/css">
        body
        {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
</head>
<body ng-app="netVogue">
    <!-- header -->
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                </a><a class="brand" href="Netvogue.html">NETVOGUE</a>
                <div class="nav-collapse pull-right">
                    <ul class="nav">
                        <li><a href="blog.html">Blog</a></li>
                        <li><a href="about_Us.html">About Us</a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Sign
                            Up <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="brand_Registration.html">Brand</a></li>
                                <li><a href="boutique_Registration.html">Boutique</a></li>
                            </ul>
                        </li>
                        <li class="dropdown" id="menu1"><a class="dropdown-toggle" data-toggle="dropdown"
                            href="#menu1">Login <b class="caret"></b></a>
                            <div class="login-menu">
                                <form style="margin: 0px" accept-charset="UTF-8" action="j_spring_security_check" method="post">
                                <fieldset class='textbox' style="padding: 10px">
                                    <div>
                                        <span>Email or username</span>
                                        <input id="j_username" name="j_username" style="margin-top: 8px" type="text" class="span4" placeholder="Email or username" />
                                        <span>Passsword</span>
                                        <input id="j_password" name="j_password" style="margin-top: 8px" type="password" class="span4" placeholder="Passsword" />
                                        <div>
                                            <input type="checkbox" />
                                            <span class="checkbx_text">Remember me</span> <span class="checkbx_text">|</span>
                                            <span class="checkbx_text"><a href="resend_password.html">Forgot password</a></span> <span class="login_btn pull-right">
                                                <button type="submit" class="btn btn-primary">
                                                    Log In</button>
                                            </span>
                                        </div>
                                    </div>
                                </fieldset>
                                </form>
                            </div>
                        </li>
                    </ul>
                </div>
                <!-- /.nav-collapse -->
            </div>
            <!-- /.container -->
        </div>
        <!-- /.navbar-inner -->
    </div>
    <!-- /.navbar -->
    <!-- header -->
    <!--body-->
    <!--body-->
    <div id="registration" class="container" ng-controller="MyCtrlRegistration">
        <div class="row">
            <div class="row">
                <legend class="subHeading">Register Brand</legend>
                <!-- left pannel-->
                <div class="span5">
                    <form novalidate class="form-horizontal css-form" name="form">
                    <div class="control-group pull-left span6">
                        <h4 style="text-decoration: underline; min-width: 200px;">
                            Contact Info</h4>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Email</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.email" class="span3" placeholder="Email" maxlength="50"
                                name="uEmail" required ng-pattern="/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/"
                                ui-validate="{oldemail:emailchanged}" />
                            <div ng-show="form.uEmail.$dirty && form.uEmail.$invalid">
                                <span ng-show="form.uEmail.$error.required" class="validation_msg">Email Is Required</span>
                                <span ng-show="form.uEmail.$error.pattern" class="validation_msg">Email Is Invalid</span>
                                <span ng-show="form.uEmail.$error.oldemail" class="validation_msg">This email is already
                                    registered</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Password</label>
                        <div class="controls">
                            <input type="password" ng-model="entity.password" class="span3" placeholder="Password"
                                name="uPassword" required ng-minlength="6" maxlength="12" />
                            <div ng-show="form.uPassword.$dirty && form.uPassword.$invalid">
                                <span ng-show="form.uPassword.$error.required" class="validation_msg">Password Is Required</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Confirm Password</label>
                        <div class="controls">
                            <input type="password" ng-model="confirmpassword" class="span3" placeholder="Confirm Password"
                                name="uConfirmPassword" required ng-minlength="6" maxlength="12" same-as="{{entity.password}}" />
                            <div ng-show="form.uConfirmPassword.$dirty && form.uConfirmPassword.$invalid">
                                <span ng-show="form.uConfirmPassword.$error.required" class="validation_msg">Confirm
                                    Password Is Required</span> <span ng-show="form.uConfirmPassword.$error.sameAs" class="validation_msg">
                                        Password Is Not Matching</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            User Name</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.username" class="span3" placeholder="User name"
                                name="uUserName" ng-maxlength="15" required maxlength="26" ui-validate="{oldusername:usernamechanged}" />
                            <div ng-show="form.uUserName.$dirty && form.uUserName.$invalid">
                                <span ng-show="form.uUserName.$error.required" class="validation_msg">User Name Is Required</span>
                                <span ng-show="form.uUserName.$error.maxlength" class="validation_msg">User Name Should
                                    Not Exceed 25 Characters</span> <span ng-show="form.uUserName.$error.oldusername"
                                        class="validation_msg">This Username is already registered</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Brand Name</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.name" class="span3" placeholder="Brand Name"
                                name="uBrandName" required maxlength="25" />
                            <div ng-show="form.uBrandName.$dirty && form.uBrandName.$invalid">
                                <span ng-show="form.uBrandName.$error.required" class="validation_msg">Brand Name Is
                                    Required</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Country</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.country" class="span3" placeholder="Country"
                                name="uCountry" required maxlength="25" ng-pattern="/^\w*$/" />
                            <div ng-show="form.uCountry.$dirty && form.uCountry.$invalid">
                                <span ng-show="form.uCountry.$error.required" class="validation_msg">Country Is Required</span>
                                <span ng-show="form.uCountry.$error.pattern" class="validation_msg">Country Is Invalid</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            State</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.state" class="span3" placeholder="State" name="uState"
                                required maxlength="25" ng-pattern="/^[A-Za-z ]*$/" />
                            <div ng-show="form.uState.$dirty && form.uState.$invalid">
                                <span ng-show="form.uState.$error.required" class="validation_msg">State Is Required</span>
                                <span ng-show="form.uState.$error.pattern" class="validation_msg">State Is Invalid</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            City</label>
                        <div class="controls">
                            <input type="text" id="txt_entity_city" data-items="7" ng-model="entity.city" class="span3" placeholder="City" name="uCity"
                                required maxlength="25" ng-pattern="/^\w*$/" ui-jq="typeahead" ui-options="{ source: cities }"/>
                            <div ng-show="form.uCity.$dirty && form.uCity.$invalid">
                                <span ng-show="form.uCity.$error.required" class="validation_msg">City Is Required</span>
                                <span ng-show="form.uCity.$error.pattern" class="validation_msg">City Is Invalid</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Address</label>
                        <div class="controls">
                            <textarea ng-model="entity.address" class="span3" rows="3" placeholder="Address"
                                name="uAddress" required ng-maxlength="200"></textarea>
                            <div ng-show="form.uAddress.$dirty && form.uAddress.$invalid">
                                <span ng-show="form.uAddress.$error.required" class="validation_msg">Address is Required</span>
                                <span ng-show="form.uAddress.$error.maxlength" class="validation_msg">Address Not To
                                    Exceed 200 Characters</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Zip Code</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.zipcode" class="span3" placeholder="Zip Code"
                                name="uZipCode" required ng-minlength="6" maxlength="6" ng-pattern="/^\s*(\-|\+)?(\d+|(\d*(\.\d*)))\s*$/" />
                            <div ng-show="form.uZipCode.$dirty && form.uZipCode.$invalid">
                                <span ng-show="form.uZipCode.$error.required" class="validation_msg">Zip Code Is Required</span>
                                <span ng-show="form.uZipCode.$error.pattern" class="validation_msg">Zip Code Is Numeric</span>
                                <span ng-show="form.uZipCode.$error.minlength" class="validation_msg">Zip Code: 6 Digits
                                </span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Mobile</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.mobile" class="span3" placeholder="Mobile" name="uMobile"
                                ng-minlength="10" maxlength="10" required ng-pattern="/^\s*(\-|\+)?(\d+|(\d*(\.\d*)))\s*$/" />
                            <div ng-show="form.uMobile.$dirty && form.uMobile.$invalid">
                                <span ng-show="form.uMobile.$error.required" class="validation_msg">Mobile Number Is
                                    Required</span> <span ng-show="form.uMobile.$error.pattern" class="validation_msg">Mobile
                                        Number Is Numeric</span> <span ng-show="form.uMobile.$error.minlength" class="validation_msg">
                                            Mobile Number: 10 Digits</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Telephone</label>
                        <div class="controls">
                            <input type="text" ng-model="entity.telephone" class="span3" placeholder="Telephone"
                                ng-minlength="8" name="uTelephone" required maxlength="12" ng-pattern="/^\s*(\-|\+)?(\d+|(\d*(\.\d*)))\s*$/" />
                            <div ng-show="form.uTelephone.$dirty && form.uTelephone.$invalid">
                                <span ng-show="form.uTelephone.$error.required" class="validation_msg">Telephone Number
                                    Is Required</span> <span ng-show="form.uTelephone.$error.pattern" class="validation_msg">
                                        Telephone Number Is Numeric</span> <span ng-show="form.uTelephone.$error.minlength"
                                            class="validation_msg">Telephone Number: 8 Digits</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Website</label>
                        <div class="controls">
                            <input type="url" ng-model="entity.website" class="span3" placeholder="http://wesite.com"
                                name="uWebsite" maxlength="50" ng-pattern="/^(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/" />
                            <div ng-show="form.uWebsite.$dirty && form.uWebsite.$invalid">
                                <span ng-show="form.uWebsite.$error.pattern" class="validation_msg">Website Url Is InValid</span>
                            </div>
                        </div>
                        <div class="divi2">
                        </div>
                        <label class="control-label">
                            Year Established:</label>
                        <div class="controls">
                            <input type="number" ng-model="entity.estdyear" class="span3" placeholder="Year Established"
                                name="uEstdyear" maxlength="4" required ng:pattern="/^\s*(\-|\+)?(\d+|(\d*(\.\d*)))\s*$/"
                                min="1900" max="2014" ng-minlength="4" />
                            <div ng-show="form.uEstdyear.$dirty && form.uEstdyear.$invalid">
                                <span ng-show="form.uEstdyear.$error.required" class="validation_msg">Year Of Establisment
                                    Is Required</span> <span ng-show="form.uEstdyear.$error.pattern" class="validation_msg">
                                        Year Of Establisment Is Numeric</span> <span ng-show="form.uEstdyear.$error.minlength || form.uEstdyear.$error.number"
                                            class="validation_msg"></span><span ng-show="form.uEstdyear.$error.min || form.uEstdyear.$error.max"
                                                class="validation_msg">The value must be in range 1900 to 2014</span>
                            </div>
                        </div>
                        
                           <div class="divi2">
                        </div>
                        <label class="control-label">
                            Price Range:</label>
                        <div class="controls">
                        	<input type="number" ng-model="entity.fromprice" class="spanpricerange" placeholder="From price"
	                     	 required />
                       -
	                        <input type="number" ng-model="entity.toprice" class="spanpricerange" placeholder="To price"
	                        required />  
                        </div>
                        
                    </div>
                    </form>
                </div>
                <!-- left pannel -->
                <!-- right pannel -->
                <form novalidate class="form-horizontal css-form">
                <div class="span7 pull-left">
                    <div class="span7">
                        <div style="width: 560px;" class="ProductSelection">
                            <div class="clr" style="height: 20px; float: left; width: 100%;">
                                <h4 style="text-decoration: underline; min-width: 200px;">
                                    Product Categories</h4>
                            </div>
                            <div class="divi2">
                            </div>
                            <div class="ProductCategory" ng-repeat="product in productscarried">
                                <input type="checkbox" ng-model="product.selected" /><span class="Title">{{product.productlinename}}</span>
                            </div>
                        </div>
                        <div style="clear: both;">
                            <h4 style="text-decoration: underline; min-width: 200px;">
                                Stockists</h4>
                        </div>
                        <div class="divi2">
                        </div>
                        <div>
                            <input type="text" id="txt_brands_carried" data-items="7" ng-model="brandsentered" placeholder="Enter Stockist Name" 
                            	ng-change="brandsenteredchanged(brandsentered)" ui-jq="typeahead" ui-options="{ source: brands }"/>
                            <button class="btn btn-primary" style="width: 75px;" ng-click="addBrandsCarried(brandsentered)">
                                ADD
                            </button>
                        </div>
                        <div class="divi2">
                        </div>
                        <div>
                            <ul class="n-wizard-brand-list">
                                <li class="size-5 n-wizard-brand" style="border-bottom: 1px solid #797979; padding: 4px 4px 2px 4px;"
                                    ng-repeat="brand in brandscarried.keys()"><a class="float-right orange" ng-click="removeBrandsCarried(brand)">
                                        X</a>{{brand}}<span class="clear"></span></li>
                            </ul>
                        </div>
                        <div class="divi2">
                        </div>
                        <div style="vertical-align: bottom;">
                            <button id="submitbtn" class="btn btn-primary dropdown-toggle pull-right" ng-disabled="form.$invalid"
                                data-loading-text="Please wait..." ng-click="addEntity($event)">
                                Submit
                            </button>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
   <!-- footer -->
	<div class="ywfooter">
		<div class="ywfooter_in">
			<ul class="footbar pull-left" style="padding-top:4px;" >
				<li  id="YourWindow"><span>© 2012 NETVOGUE</span> <span></li>
			</ul>

			<ul class="footbar pull-right">
				<li  id="itemPinterest1"><a
					onmouseover="document.getElementById('pinterest1').src ='img/facebook-on.jpeg';"
					onmouseout="document.getElementById('pinterest1').src ='img/facebook.jpeg';"
					href="#" title="Go to Facebook page"> <img id="pinterest1"
						src="img/facebook.jpeg" border="0" alt="Facebook" />
				</a></li>
				<li id="itemPinterest2"><a
					onmouseover="document.getElementById('pinterest2').src ='img/twitter-on.jpeg';"
					onmouseout="document.getElementById('pinterest2').src ='img/twitter.jpeg';"
					href="#" title="Go to Twitter page"><img id="pinterest2"
						src="img/twitter.jpeg" border="0" alt="Twitter"></a></li>

				<li id="itemPinterest3"><a
					onmouseover="document.getElementById('pinterest3').src ='img/Pinterest-logo-on.jpeg';"
					onmouseout="document.getElementById('pinterest3').src ='img/Pinterest.jpeg';"
					href="#" title="Go to Pinterest page"><img id="pinterest3"
						src="img/Pinterest.jpeg" border="0" alt="Pinterest"></a></li>

				<li id="itemPinterest4"><a
					onmouseover="document.getElementById('pinterest').src ='img/blog-on.jpeg';"
					onmouseout="document.getElementById('pinterest').src ='img/blog.jpeg';"
					href="#" title="Go to Blog page"><img id="pinterest"
						src="img/blog.jpeg" border="0" alt="Blog "></a></li>
			</ul>
		</div>
	</div>
	<!-- footer -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript">
        var entity = "brand";
    </script>
    <script src="lib/jquery/jquery.js" type="text/javascript"></script>
    <script src="lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
    <script src="lib/angular/angular.js" type="text/javascript"></script>
    <script src="js/regcontroller.js" type="text/javascript"></script>
</body>
</html>