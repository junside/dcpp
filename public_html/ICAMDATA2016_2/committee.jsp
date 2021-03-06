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
				<a href="index.jsp">Home</a> / <strong>Committees</strong>		
			</div>						
			<h1>International Program Committee</h1>
			
			<h4>
				&#8226;	Beatriz Barbuy, Brazil<br/>
				&#8226;	Jacek Bieron, Poland<br/>
				&#8226;	Bastiaan Braams, IAEA<br/>
				&#8226;	Igor Bray, Australia<br/>
				&#8226;	Nancy Brickhouse, USA (vice-chair)<br/>
				&#8226;	James Colgan, USA<br/>
				&#8226;	Chenzhong Dong, China<br/>
				&#8226;	Gordon Drake, Canada<br/>
				&#8226;	Stephan Fritzsche, Germany<br/>
				&#8226;	John Gillaspy, USA<br/>
				&#8226;	Jelle Kaastra, Netherlands<br/>
				&#8226;	Izumi Murakami, Japan<br/>
				&#8226;	Nobuyuki Nakamura, Japan<br/>
				&#8226;	Gerry O'Sullivan, Ireland<br/>
				&#8226;	Juliet Pickering, UK<br/>
				&#8226;	Evelyne Roueff, France<br/>
				&#8226;	Laurence Rothman, USA<br/>
				&#8226;	Stefan Schippers, Germany<br/>
				&#8226;	Slava Shevelko, Russia<br/>
				&#8226;	Jianguo Wang, China<br/>
				&#8226;	Wolfgang Wiese, USA<br/>
				&#8226;	Jung-Sik Yoon, Korea (Chair)</h4>
				
			<div class="clear">&nbsp;</div>
			<h1>Local Conference Committee</h1>		
			<h4>
				&#8226;	Hyuck Cho (Chungnam National University)<br/>
				&#8226;	Mi-Young Song (NFRI)<br/>
				&#8226;	Jun-Hyoung Park (NFRI)<br/>
				&#8226;	Sun-Min Han (NFRI)<br/>
				&#8226;	Se-Young Kim (NFRI)</h4>
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