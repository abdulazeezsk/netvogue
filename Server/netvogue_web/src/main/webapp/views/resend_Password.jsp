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
                </a><a class="brand" href="Netvogue.html">Net Vogue</a>
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
    <div class="footer">
        <div class="footer_in">
            <span>© 2012 NET VOGUE</span>
        </div>
    </div>
    <!-- footer -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="lib/jquery/jquery.js" type="text/javascript"></script>
    <script src="lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
