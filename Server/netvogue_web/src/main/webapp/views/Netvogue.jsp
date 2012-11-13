<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>NetVogue</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400'
	rel='stylesheet' type='text/css' />
<!-- <link href="css/StyleSheet.css" rel="stylesheet" /> -->
<style>

/* GLOBAL STYLES
    -------------------------------------------------- */
/* Padding below the footer and lighter body text */
body {
	padding-bottom: 40px;
	color: #5a5a5a;
}

/* CUSTOMIZE THE NAVBAR
    -------------------------------------------------- */

/* Special class on .container surrounding .navbar, used for positioning it into place. */
.navbar-wrapper {
	position: relative;
	z-index: 10;
	margin-top: 20px;
	margin-bottom: -90px;
	/* Negative margin to pull up carousel. 90px is roughly margins and height of navbar. */
}

/* Remove border and change up box shadow for more contrast */
.navbar .navbar-inner {
	border: 0;
	-webkit-box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
	-moz-box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
	box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
}

/* Downsize the brand/project name a bit */
.navbar .brand {
	padding: 14px 20px 16px;
	/* Increase vertical padding to match navbar links */
	font-size: 16px;
	font-weight: bold;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, .5);
}

/* Navbar links: increase padding for taller navbar */
.navbar .nav>li>a {
	padding: 15px 20px;
}

/* Offset the responsive button for proper vertical alignment */
.navbar .btn-navbar {
	margin-top: 10px;
}

/* CUSTOMIZE THE NAVBAR
    -------------------------------------------------- */

/* Carousel base class */
.carousel {
	margin-bottom: 60px;
}

.carousel .container {
	position: absolute;
	right: 0;
	bottom: 0;
	left: 0;
}

.carousel-control {
	background-color: transparent;
	border: 0;
	font-size: 120px;
	margin-top: 0;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
}

.carousel .item {
	height: 500px;
}

/* .carousel img {
	min-width: 90%;
	height: 500px;
} */

.carousel-caption {
	background-color: transparent;
	position: static;
	max-width: 550px;
	padding: 0 20px;
	margin-bottom: 100px;
}

.carousel-caption h1,.carousel-caption .lead {
	margin: 0;
	line-height: 1.25;
	color: #fff;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
}

.carousel-caption .btn {
	margin-top: 10px;
}

/* MARKETING CONTENT
    -------------------------------------------------- */

/* Center align the text within the three columns below the carousel */
.marketing .span4 {
	text-align: center;
}

.marketing h2 {
	font-weight: normal;
}

.marketing .span4 p {
	margin-left: 10px;
	margin-right: 10px;
}

/* Featurettes
    ------------------------- */
.featurette-divider {
	margin: 80px 0; /* Space out the Bootstrap <hr> more */
}

.featurette {
	padding-top: 120px;
	/* Vertically center images part 1: add padding above and below text. */
	overflow: hidden;
	/* Vertically center images part 2: clear their floats. */
}

.featurette-image {
	margin-top: -120px;
	/* Vertically center images part 3: negative margin up the image the same amount of the padding to center it. */
}

/* Give some space on the sides of the floated elements so text doesn't run right into it. */
.featurette-image.pull-left {
	margin-right: 40px;
}

.featurette-image.pull-right {
	margin-left: 40px;
}

/* Thin out the marketing headings */
.featurette-heading {
	font-size: 50px;
	font-weight: 300;
	line-height: 1;
	letter-spacing: -1px;
}

/* RESPONSIVE CSS
    -------------------------------------------------- */
@media ( max-width : 979px) {
	.container.navbar-wrapper {
		margin-bottom: 0;
		width: auto;
	}
	.navbar-inner {
		border-radius: 0;
		margin: -20px 0;
	}
	.featurette {
		height: auto;
		padding: 0;
	}
	.featurette-image.pull-left,.featurette-image.pull-right {
		display: block;
		float: none;
		max-width: 40%;
		margin: 0 auto 20px;
	}
}

@media ( max-width : 767px) {
	.navbar-inner {
		margin: -20px;
	}
	.carousel {
		margin-left: -20px;
		margin-right: -20px;
	}
	.carousel .container {
		
	}
	.carousel-caption {
		width: 65%;
		padding: 0 70px;
		margin-bottom: 40px;
	}
	.carousel-caption h1 {
		font-size: 30px;
	}
	.carousel-caption .lead,.carousel-caption .btn {
		font-size: 18px;
	}
	.marketing .span4+.span4 {
		margin-top: 40px;
	}
	.featurette-heading {
		font-size: 30px;
	}
	.featurette .lead {
		font-size: 18px;
		line-height: 1.5;
	}
}
</style>
</head>
<body>



	<!-- NAVBAR
    ================================================== -->
	<!-- Wrap the .navbar in .container to center it on the page and provide easy way to target it with .navbar-wrapper. -->
	<div class="container navbar-wrapper">

		<div class="navbar navbar-inverse">
			<div class="navbar-inner">
				<!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <span class="brand" style="font-family: 'Lato', sans-serif; font-weight: 100;" href="#">VEAWE</span>
				<!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="#">Designers</a></li>
						<li><a href="#">Retailers</a></li>
						<!-- Read about Bootstrap dropdowns at http://twitter.github.com/bootstrap/javascript.html#dropdowns -->
					</ul>
					<form class="navbar-form pull-right" action="j_spring_security_check" method="post" >
						<input class="span3" type="text" id="j_username" name="j_username" placeholder="Email" /> <input
							id="j_password" name="j_password" class="span3" type="password" placeholder="Password" />
						<button type="submit"  style="font-family: 'Lato', sans-serif; font-weight: 100;" class="btn btn-inverse">Sign in</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!-- /.navbar-inner -->
		</div>
		<!-- /.navbar -->

	</div>
	<!-- /.container -->



	<!-- Carousel
    ================================================== -->
	<div id="myCarousel" class="carousel slide">
		<div class="carousel-inner">
			<div class="item">
				<img src="img/NY_FASHION_KARAN_3.jpeg" alt="" />
				<div class="container">
					<div class="carousel-caption">
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">A
							Market Place to</h1>
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">Showcase
							and Discover</h1>
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">Fashion
							Design</h1>
						<a class="btn btn-large btn-primary" href="#">Sign up today</a>
					</div>
				</div>
			</div>
			<div class="item active">
				<img src="img/NY_FASHION_KARAN_3.jpeg" alt="" />
				<div class="container">
					<div class="carousel-caption">
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">A
							Market Place to</h1>
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">Showcase
							and Discover</h1>
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">Fashion
							Design</h1>
					<a href="brand_Registration.html" style="font-family: 'Lato', sans-serif; font-weight: 100;" class="btn btn-large btn-inverse" href="#">Sign up as Designer</a>
					<a href="boutique_Registration.html" style="font-family: 'Lato', sans-serif; font-weight: 100;" class="btn btn-large btn-inverse" href="#">Sign up as Boutique</a>
						
					</div>
				</div>
			</div>
			<div class="item">
				<img src="img/NY_FASHION_KARAN_3.jpeg" alt="" />
				<div class="container">
					<div class="carousel-caption">
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">A
							Market Place to</h1>
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">Showcase
							and Discover</h1>
						<h1 style="font-family: 'Lato', sans-serif; font-weight: 100;">Fashion
							Design</h1>

						<a class="btn btn-large btn-primary" href="#">Browse gallery</a>
					</div>
				</div>
			</div>
		</div>
		<!-- <div class="es-nav">
						<span class="es-nav-prev" style="display: none; ">Previous</span>
						<span class="es-nav-next" style="display: none; ">Next</span>
					</div> -->
	 <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>
	<!-- /.carousel -->



	<!-- Marketing messaging and featurettes
    ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->

	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="span4">
				<img class="img" style="max-height:200px;max-width:240px;" src="img/donna_karan_adriana_lima_1.jpg" />
				<h2>Heading</h2>
				<p>Donec sed odio dui. Etiam porta sem malesuada magna mollis
					euismod. Nullam id dolor id nibh ultricies vehicula ut id elit.
					Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
					Praesent commodo cursus magna, vel scelerisque nisl consectetur et.</p>
				<!--    <p><a class="btn" href="#">View details »</a></p> -->
			</div>
			<!-- /.span4 -->
			<div class="span4">
				<img class="img" style="max-height:200px;max-width:240px;" src="img/donna_karan_adriana_lima_2.jpg" />
				<h2>Heading</h2>
				<p>Duis mollis, est non commodo luctus, nisi erat porttitor
					ligula, eget lacinia odio sem nec elit. Cras mattis consectetur
					purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo,
					tortor mauris condimentum nibh, ut fermentum massa justo sit amet
					risus.</p>
				<!--  <p><a class="btn" href="#">View details »</a></p> -->
			</div>
			<!-- /.span4 -->
			<div class="span4">
				<img class="img" style="max-height:200px;max-width:240px;" src="img/donna_karan_adriana_lima_3.jpg" />
				<h2>Heading</h2>
				<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
					egestas eget quam. Vestibulum id ligula porta felis euismod semper.
					Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
					nibh, ut fermentum massa justo sit amet risus.</p>
				<!--  <p><a class="btn" href="#">View details »</a></p> -->
			</div>
			<!-- /.span4 -->
		</div>
		<!-- /.row -->


		<!-- START THE FEATURETTES -->

		<hr class="featurette-divider" />

		<div class="featurette">
			<img class="featurette-image pull-right"
				src="img/Virtues-AW11634408972018427500.jpeg" />
			<h2 class="featurette-heading">
				First featurette headling. <span class="muted">It'll blow
					your mind.</span>
			</h2>
			<p class="lead">Donec ullamcorper nulla non metus auctor
				fringilla. Vestibulum id ligula porta felis euismod semper. Praesent
				commodo cursus magna, vel scelerisque nisl consectetur. Fusce
				dapibus, tellus ac cursus commodo.</p>
		</div>

		<hr class="featurette-divider" />

		<div class="featurette">
			<img class="featurette-image pull-left"
				src="img/Virtues-AW11634408972018427500.jpeg" />
			<h2 class="featurette-heading">
				Oh yeah, it's that good. <span class="muted">See for
					yourself.</span>
			</h2>
			<p class="lead">Donec ullamcorper nulla non metus auctor
				fringilla. Vestibulum id ligula porta felis euismod semper. Praesent
				commodo cursus magna, vel scelerisque nisl consectetur. Fusce
				dapibus, tellus ac cursus commodo.</p>
		</div>

		<hr class="featurette-divider" />

		<div class="featurette">
			<img class="featurette-image pull-right"
				src="img/Virtues-AW11634408972018427500.jpeg">
				<h2 class="featurette-heading">
					And lastly, this one. <span class="muted">Checkmate.</span>
				</h2>
				<p class="lead">Donec ullamcorper nulla non metus auctor
					fringilla. Vestibulum id ligula porta felis euismod semper.
					Praesent commodo cursus magna, vel scelerisque nisl consectetur.
					Fusce dapibus, tellus ac cursus commodo.</p>
		</div>

		<hr class="featurette-divider" />

		<!-- /END THE FEATURETTES -->


		<!-- FOOTER -->
		<footer>
		<p class="pull-right">
			<a href="#">Back to top</a>
		</p>
		<p>
		© 2012 Company, Inc.<a href="#">About</a> | <a href="#">Contact</a> | <a href="#">Privacy</a> | <a href="#">Terms</a>
		</p>
		</footer>

	</div>
	<!-- /.container -->



	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="lib/jquery/jquery.js" type="text/javascript"></script>
	<script src="lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
	<script>
		!function($) {
			$(function() {
				// carousel demo
				$('#myCarousel').carousel()
			})
		}(window.jQuery)
	</script>


</body>
</html>