<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>NetVogue</title>
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/StyleSheet.css" rel="stylesheet" />
    <style type="text/css">
        body
        {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
</head>
<body>
    <!-- header -->
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                </a><a class="brand" href="Netvogue.html">NETVOGUE</a>
                <!-- /.nav-collapse -->
            </div>
            <!-- /.container -->
        </div>
        <!-- /.navbar-inner -->
    </div>
    <!-- /.navbar -->
    <!-- header -->
    <!--body-->
    <div>
        <div class="row">
            <div class="minimum_height">
                <div class="page-canvas">
                    <div class="signin-wrapper" data-login-message="" style="margin-left: 65px;">
                        <h2>
                            Forgot your password?</h2>
                        <br></br>
                        <form action="" class="clearfix signin js-signin" method="post">
                        <fieldset>
                            <div>
                                <label class="control-label">
                                    Enter your email address</label>
                                <div>
                                    <input type="text" ng-model="entity.email" class="span5" placeholder="Enter your email address "
                                        maxlength="25" name="uEmail" />
                                    <button type="submit" class="btn btn-primary" style="position: relative; top: -5px;">
                                        Submit</button>
                                </div>
                            </div>
                        </fieldset>
                        </form>
                    </div>
                </div>
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
    <script src="lib/jquery/jquery.js" type="text/javascript"></script>
    <script src="lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
