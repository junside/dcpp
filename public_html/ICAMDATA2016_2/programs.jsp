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
				<a href="index.jsp">Home</a> / <strong>Programs</strong>			
			</div>						
			<h1>Conference Schedules</h1>

			<div class="article01">				
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="table10">
				<tr>
					<th width="10%">Time</th>
					<th width="18%">Sun (9/25)</th>
					<th width="18%">Mon (9/26)</th>
					<th width="18%">Tue (9/27)</th>
					<th width="18%">Wed (9/28)</th>
					<th width="18%" class="ttn">Thu (9/29)</th>
				</tr>
				<tr>
					<td>08:30</td>
					<td></td>
					<td>Registration</td>
					<td>Registration</td>
					<td></td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>09:00</td>
					<td></td>
					<td>Invited Talk 1</td>
					<td>Invited Talk 10</td>
					<td>Invited Talk 19</td>
					<td class="ttn">Invited Talk 25</td>
				</tr>
				<tr>
					<td>09:30</td>
					<td></td>
					<td>Invited Talk 2</td>
					<td>Invited Talk 11</td>
					<td>Invited Talk 20</td>
					<td class="ttn">Invited Talk 26</td>
				</tr>
				<tr>
					<td>10:00</td>
					<td></td>
					<td>Invited Talk 3</td>
					<td>Invited Talk 12</td>
					<td>Invited Talk 21</td>
					<td class="ttn">Invited Talk 27</td>
				</tr>
				<tr>
					<td>10:30</td>
					<td></td>
					<td class="tt05y">Coffee Break</td>
					<td class="tt05y">Coffee Break</td>
					<td class="tt05y">Coffee Break</td>
					<td class="ttny">Coffee Break</td>
				</tr>
				<tr>
					<td>11:00</td>
					<td></td>
					<td>Invited Talk 4</td>
					<td>Invited Talk 13</td>
					<td>Invited Talk 22</td>
					<td class="ttn">Invited Talk 28</td>
				</tr>
				<tr>
					<td>11:30</td>
					<td></td>
					<td>Invited Talk 5</td>
					<td>Invited Talk 14</td>
					<td>Invited Talk 23</td>
					<td class="ttn">Invited Talk 29</td>
				</tr>
				<tr>
					<td>12:00</td>
					<td></td>
					<td>Invited Talk 6</td>
					<td>Invited Talk 15</td>
					<td>Invited Talk 24</td>
					<td class="ttn">Invited Talk 30</td>
				</tr>
				<tr>
					<td>12:30</td>
					<td></td>
					<td class="tt05b">Lunch Break</td>
					<td class="tt05b">Lunch Break<br/>IPC Meeting</td>
					<td class="tt05b">Lunch Break</td>
					<td class="ttnb">Lunch Break<br/>Business Meeting</td>
				</tr>
				<tr>
					<td>14:00</td>
					<td></td>
					<td>Invited Talk 7</td>
					<td>Invited Talk 16</td>
					<td rowspan=9>Excursion</td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>14:30</td>
					<td></td>
					<td>Invited Talk 8</td>
					<td>Invited Talk 17</td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>15:00</td>
					<td></td>
					<td>Invited Talk 9</td>
					<td>Invited Talk 18</td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>15:30</td>
					<td></td>
					<td class="tt05y">Coffee Break</td>
					<td class="tt05y">Coffee Break</td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>16:00</td>
					<td></td>
					<td rowspan=5>Poster Session 1</td>
					<td rowspan=5>Poster Session 2</td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>16:30</td>
					<td></td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>17:00</td>
					<td></td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>17:30</td>
					<td></td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>18:00</td>
					<td></td>
					<td class="ttn"></td>
				</tr>
				<tr>
					<td>18:30</td>
					<td class="tt05r">Welcome Reception</td>
					<td></td>
					<td class="tt05r">Conference Dinner</td>
					<td></td>
					<td class="ttn"></td>
				</tr>
			</table>
			
		</div>
		<div class="clear">&nbsp;</div>
		
		<h1>Excursion</h1>

			<div class="article01">				
			<h4>To be updated.</h4>		

			
			
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