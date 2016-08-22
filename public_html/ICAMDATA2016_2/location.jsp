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
				<a href="index.jsp">Home</a> / <a href="location.html"><strong>Location</strong></a>			
			</div>					
			
			<h1>Venue</h1>
			
			<div class="article01">Gunsan city is near to the West sea and located in Jeollabuk-do Province 
			in Republic of Korea. It is well known as a tumultuous historical sites, 
			such as Gunsan Customs Office, Chosun Bank Gunsan Brance, Warehouse of Korea Express Co.,Ltd etc.<br/>
			Also, Gunsan, located on the edge of central western coast, as a waist of the Korean peninsula, 
			is in 2~3 hours away from anywhere in Korea (231km away from Seoul and 337km away from Busan). 
			It is also the central city of Saemangeum, the biggest reclamation project after the origination of 
			the country, and a port city of 114 years of history with various international routes, 
			located 563km away from Qingdao, China, which is the shortest distance in Korea.<br/> 
			For more information, visit Gunsanâs official website : <a href="http://eng.gunsan.go.kr/index.gunsan">http://eng.gunsan.go.kr/index.gunsan</a>
			<br/>
			(If you want to more big size, click the map.)
			</div>			
			<br/>
			<div class="cont_img">
			<img style="WIDTH: 800px" onclick="window.open(this.src)" alt="Click the map" hspace="0" src="images/content/worldmap.png"/> 
			&lt;World Map&gt;
			</div>
			<div class="cont_img">
			<img style="WIDTH: 800px" onclick="window.open(this.src)" alt="Click the map" hspace="0" src="images/content/newgunsanmap.png"/>
			&lt;Location of Gunsan&gt;
			</div>
			
			<h1>Accommodation</h1>
			
					
			<h3>To be updated</h3>
			
			<div class="clear">&nbsp;</div>	
				
			<h1>Transportation</h1>	
					
			<div class="article01">
			You can get to the Gunsan Bus Terminal by Limousine bus at Bus stop No. <font color=red>9C</font>, Incheon International Airport.<br/>
			Bus stop No. <font color=red>9C</font> is located in outside of airport building.<br/><br/>
			
			<div class="cont_img"><img src="images/content/bus_map.png" alt="" width=622 /></div>
			
			<strong>Limousine bus Schedule at Incheon International Airport</strong>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="table10">
				<tr>
					<th width="11%">Route Name</th>
					<th width="11%">Bus Type</th>
					<th width="20%">Station</th>
					<th width="12%">Bus Stop No.</th>
					<th width="20%">Time line of<br/>departing Buses</th>
					<th width="13%">Travelling Time</th>
					<th width="13%"class="ttn">Fare</th>
				</tr>
				<tr>
					<td>Gunsan (Jeonra)</td>
					<td>Limousine</td>
					<td>Direct to Gunsan</td>
					<td>1st Floor 9C</td>
					<td>09:50, 12:50, 14:40, 16:40, 18:40, 20:40</td>
					<td>210 min.</td>
					<td class="ttn">KRW 23,700</td>
				</tr>
				<tr>
					<td>Gunsan (Jeonra)</td>
					<td>Limousine</td>
					<td>Songdo TransferÂ  Center - Daeya - Gunsan</td>
					<td>1st Floor 9C</td>
					<td>08:20, 11:00, 15:40, 17:40, 19:40, 21:40Â </td>
					<td>225 min.</td>
					<td class="ttn">KRW 23,700</td>
				</tr>
			</table>
			(You may prepare the bus fare in Korean Won or purchase the bus ticket at the bus ticket office at the airport in advance.)
			</div>
			
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