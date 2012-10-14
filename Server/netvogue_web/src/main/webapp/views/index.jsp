<!doctype html>
<html lang="en" ng-app="netVogue" ng-controller="CtrlMain">
<head>
    <meta charset="utf-8">
    <title ng-Bind-Template="Netvogue : {{title}}"></title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="css/StyleSheet.css" rel="stylesheet" type="text/css" />
    <link href="css/jquery.fileupload-ui.css" rel="stylesheet" type="text/css" />
    <link href="css/select2.css" rel="stylesheet" type="text/css" />
    <link href="css/styles.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
 	<link href="css/jquery.fancybox.css" rel="stylesheet" type="text/css" />
    <link href="css/elastislide.css" rel="stylesheet" type="text/css" />
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="css/jquery.tree.css" rel="stylesheet" type="text/css"  />
    <link href="css/angular-ui.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        body
        {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
</head>
<body>
    <div ng-include src="'templates/header.htm'">
    </div>
    <div ng-view>
    </div>
    <!-- footer -->
    <div class="footer">
        <div class="footer_in">
            <span>� 2012 NET VOGUE</span></div>
    </div>
    <!-- footer -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="lib/jquery/jquery.js" type="text/javascript"></script>
    <script src="lib/jquery/jquery-ui.min.js" type="text/javascript"></script>
    <script src="lib/jquery/jquery.placeholder.js"  type="text/javascript"></script>
    <script src="lib/bootstrap/bootstrap.min.js" 	type="text/javascript"></script>
    <script src="lib/jquery/jquery.progressbar.js" 	type="text/javascript"></script>
   <script src="lib/jquery/jquery.masonry.js" 		type="text/javascript"></script>
   <script src="lib/jquery/jquery.infinitescroll.min.js" 	type="text/javascript"></script>
   <script src="lib/jquery/jquery.fancybox.pack.js" 	type="text/javascript"></script>
   <script src="lib/jquery/jquery.elastislide.js" 	type="text/javascript"></script>
   
    <script src="lib/angular/angular.js" 			type="text/javascript"></script>
    <script src="lib/angular/angular-ui.js" 		type="text/javascript"></script>
    <script src="lib/angular/select2.js" 					type="text/javascript"></script>
    <script src="lib/bootstrap/bootstrap-navlist.js" type="text/javascript"></script>
    <script src="lib/wr.js" 						 type="text/javascript"></script>
    <script src="http://js.pusher.com/1.12/pusher.min.js"></script>
    <div style="display: none;">
    	<div id="firsttimelogin">${firsttimelogin}</div>
    	<div id="isbrand">${isbrand}</div>
    	<div id="brandname">${brandname}</div>
    </div>
    <script src="js/objects.js"						type="text/javascript"></script>
    <script src="js/app.js" 						type="text/javascript"></script>
    <script src="js/services.js" 					type="text/javascript"></script>
    <script src="js/controllers.js" 				type="text/javascript"></script>
    <script src="js/addeditcontrollers.js" 			type="text/javascript"></script>
    <script src="js/filters.js" 					type="text/javascript"></script>
    <script src="js/directives.js" 					type="text/javascript"></script>
    <script src="js/constants.js" 					type="text/javascript"></script>
    <script src="js/main.js" 						type="text/javascript"></script>
    <script src="js/utils.js" 						type="text/javascript"></script>
  
  	
    
	<script src="lib/fileupload/jquery.iframe-transport.js"></script>
	<script src="lib/fileupload/jquery.fileupload.js"></script>
	<script src="lib/fileupload/load-image.min.js"></script>
</body>
</html>