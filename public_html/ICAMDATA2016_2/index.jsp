<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE> ICAMDATA 2016 </TITLE>
<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
<!--[if lte IE 6]>
<link rel="stylesheet" type="text/css" href="css/ie6_or_less.css" />
<![endif]-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script>
var nCount = 0;
var $img1 = null;

jQuery(document).ready(function(){
	// 초기화.
	init();
	// 타이머 시작.
	start();
});
		
function init(){			
	//1. image Element 구하기.
	this.$img1 = $("#img1");
}
function start(){
	// 1초에 한번씩 changeImage()함수를 호출하는 타이머를 실행시킵니다.
	setInterval(this.changeImage,3000);
}

// 이미지 변경은 여기에서
function changeImage(){
	this.nCount++;
	//현재 숫자값이 11을 넘게 되면 다시 1로 만들어주면 되겠죠?
	if(this.nCount>=16)
		this.nCount	= 1;
	
	//2. image 속성 변경하기.
	this.$img1.attr("src","images/img"+this.nCount+".jpg");
}

</script>
</head>
<body id="type-d">
<div id="wrap">
	<div id="header">
		<div id="site-name">ICAMDATA 2016</div>
		<div id="site-name-m">The 10th International Conference on Atomic and Molecular Data and Their Applications</div>
		<div id="site-name-s">Gunsan, Republic of Korea, September 25 ~ 29, 2016</div>
		<ul id="nav">
			<li class="first"><a href="index.jsp">Home</a></li>
			<li><a href="informations.jsp">Informations</a>
			</li>
			<li><a href="abstract.jsp">Abstract</a>
			</li>
			<li><a href="registration.jsp">Registration</a>
			</li>
			<li><a href="programs.jsp">Programs</a>
			</li>
			<li><a href="location.jsp">Location</a>
			</li>
			<li><a href="committee.jsp">Committees</a>
			</li>
			<li><a href="http://physics.nist.gov/Icamdata/Homepage/charter.html">ICAMDATA Conference</a>
				<ul>
				<li><a href="http://physics.nist.gov/Icamdata/Homepage/charter.html">ICAMDATA Charter</a></li>
				<li class="last"><a href="http://physics.nist.gov/Icamdata/Homepage/icamdata.html">ICAMDATA History</a></li>
				</ul>
			</li>
			<li><a href="contact.jsp">Contact us</a>
			</li>
		</ul>
	</div>
	
	<div id="content-wrap">
		
		<div id="content">
		
			<div id="breadcrumb">
				<a href="index.jsp">Home</a>			
			</div>
						
			<h1>Welcome</h1>
			
					
			<p class="middle_desc">We are pleased to announce that the 10th International Conference on Atomic and
			 Molecular Data and Their Applications (ICAMDATA 2016) hosted by National Fusion 
			 Research Institute will be held in Gunsan, Republic of Korea.
			 <br/><br/>			 
			 ICAMDATA 2016 is a continuing series of international conferences since 1997 that promotes the use of 
			 atomic and molecular data in various fields of science and technology, and provides a forum for 
			 interaction of AM data producers and users and for information exchange on AM data needs and availability,
			 and fosters cross-disciplinary cooperation between the AM data producers and users and coordination of 
			 AM data activities and databases worldwide.			 
			 <br/><br/>
			 The conference will focus on the following topics:
			 </p>
			<br/>
			<div class="article03_box">
			<p><strong>I. Application and needs of atomic and molecular data</strong></p>	
			<ul>		
			<li>Astrophysics and atmospheric physics</li>
			<li>Magnetic and inertial fusion</li>
			<li>Laboratory and industrial plasmas</li>
			<li>Lighting science and technology</li>
			<li>Biomedicine and biophysics</li>
			<li>Combustion and environmental sciences and technology</li>
			<li>Surface physics, gaseous electronics, solid state optics and spectroscopy, optoelectronics, etc.</li>			
			</ul>

			<p><strong>II. Atomic and molecular data collections, assessment and dissemination</strong></p>	
			<ul>		
			<li>Data collection and selection, critical evaluation, estimate of uncertainties</li>
			<li>Databases, data exchange and dissemination</li>
			<li>Data center activities, standardization of data formats</li>
			</ul>
			
			<p><strong>III. Experimental and theoretical atomic and molecular data generation</strong></p>	
			<ul>		
			<li>Atomic and molecular structure and spectroscopy</li>
			<li>Electron collisions with atoms and molecules</li>
			<li>Heavy particle collisions</li>
			<li>Particle - surface interactions</li>			
			</ul>
			
			</div>
			
			<p class="middle_desc">
			 The scientific program will include invited talks, a panel discussion and, posters. Both experimental 
			 and theoretical contributions will be welcome.
			 </p>
		
			<div class="clear">&nbsp;</div>
			
		</div>
		<div id="footer">
			<a href="http://www.nfri.re.kr/"><img src="wsimages/nfri_logo.png" alt="National Fusion Research Institute" width=110 height=35/></a>
			<p class="title">Plasma Technology Research Center, National Fusion Research Institute</p>
			<p>Address : 37 Dongjangsan-ro, Gunsan-si, Jeollabuk-do, Korea</p>  
			<p>Tel : +82-63-440-4210, Fax : +82-63-466-7001</p>
		</div>
		<div id="sidebar">
			<div class="featurebox">
			<h3>Gunsan, Korea</h3>
			<p align="center">
			   <img id=img1 src="images/img1.jpg" />
			</p>
			</div>
			<br/>
			<p align="center">
			<embed src="http://www.weatherlet.com/weather.swf?locid=KSXX0046&unit=m" title="Free Online Weather for WordPress, Blogspot, Blogger, Drupal, TypePad, MySpace, Facebook, Bebo, Piczo, Xanga, Freewebs, Netvibes, Pageflakes, iGoogle and other blogs and web pages" quality="high" wmode="transparent" bgcolor="#CC00CC" width="184" height="76" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" /><br>
			<a href="http://www.weatherlet.com/?locid=KSXX0046&cityname=Kunsan%2C+South+Korea&unit=m" title="Kunsan, South Korea Local Weather" target="_blank"><small>Gunsan(Kunsan), South Korea Local Weather</small></a></p>
              
		</div>		
	</div>
	
</div>
</body>
</html>