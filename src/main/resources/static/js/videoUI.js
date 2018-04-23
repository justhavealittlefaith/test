/*!
 * @name        videoUI
 * @author      Ultnema
 * @modified    Tuesday, Feb 06th, 2018


/* VARIABLES 
------------------------------------------------------- */

// Video
var $video = $("#video1");
var classVideo1ForProto = document.querySelector(".classVideo1ForProto");
var classVideo1ForNonProto = document.querySelector(".classVideo1ForNonProto");
var jsVideo = document.getElementById("video1");

// Video Control s
var $videoContainer = $("#videoContainer");
var $videoControls = $("#videoControls");
var $buttonControls = $("#buttonControls");
var $progressBar = $("#progressBar");
var $progress = $("#progress");
var $playButton = $("#play");
var $volumeSlider = $("#volumeSlider");
var $fullScreenBtn = $("#fullScreen");
var $duration = $("#duration");
var $spanMovieLength = $('#spanMovieLength');
   
/* VIDEO PLAYER 
------------------------------------------------------- */

// Toggles play/pause for the video
function playVideo() {      
	if($video[0].paused) {
		$video[0].play();
		$('#spanPause').show();
		$('.pause').show();
		$('#spanPlay').hide();
		$('.play').hide();
		// $playButton.html("Pause");
		// $buttonControls.hide();	     	
	} else {
		$video[0].pause();
		$('#spanPlay').show();
		$('.play').show();
		$('#spanPause').hide();
		$('.pause').hide();
		
		// $playButton.html("Play");		
	} 
}

function launchFullscreen(selector) {
  if(selector.requestFullscreen) {
    selector.requestFullscreen();
  } else if(selector.mozRequestFullScreen) {
    selector.mozRequestFullScreen();
  } else if(selector.webkitRequestFullscreen) {
    selector.webkitRequestFullscreen();
  } else if(selector.msRequestFullscreen) {
    selector.msRequestFullscreen();
  } else if(selector.oRequestFullscreen) {
    selector.oRequestFullscreen();
  }
}

function timeFormat(time){
	var minutes = parseInt(time / 60, 10);
	var m;
	if(minutes < 9.5){
		m = "0" + Math.round(minutes);
	}else{
		m = Math.round(minutes);
	}

	var seconds = parseInt(time % 60, 10);
	var s;
	if(seconds < 9.5){
		s = "0" + Math.round(seconds);
	}else{
		s = Math.round(seconds);
	}
	//return an object
	return{
		minutes:m,
		seconds:s
	};
}

//retrieve the duration of the movie
function movieLength(){
	movieLength.called = true;
	var i = setInterval(function(){
		if ($video[0].readyState > 0) {
			var length = jsVideo.duration;
			length = Math.round(length);
			var l = timeFormat(length);
			// (Put the minutes and seconds in the display)
			$spanMovieLength.html(l.minutes + ":" + l.seconds);
		}
		clearInterval(i);
	}, 200);
}

function durationToWatch(){
	var x = jsVideo.duration;
	x = x + 60*30; //convert to second
	x = x * 1000; //convert to millisecond
	// x = 10000;
	return x;
}
function triggerLogout(){
	//Being automatically logged out on protoPlaying.php cannot have language variables sent, so default is EN
	window.location = "../response.php?NewRelease";
}

function eventAfterVideoPlaying(){
	if(!movieLength.called){
		movieLength();
	}
}
function eventAfterProtoMoviePlaying(){
	if(!movieLength.called){
		movieLength();
		startTime();
		setTimeout(triggerLogout, durationToWatch());
		var timeAllowed = durationToWatch()/1000/60;
		$("#timeAllowed").html(Math.round(timeAllowed));
	}
}

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function startTime() {
    var d = new Date();
	var n = d.getTimezoneOffset();
	var n = n/60;
	if(n > 0){
		n = n - n - n; //For example, If your time zone is GMT-2, 120 will be returned.
		nHours = "-" + n;
	}else{
		n = n - n - n; //For example, If your time zone is GMT+2, -120 will be returned.
		nHours = "+" + n;
	}
	
    var x = document.getElementById("spanStartTime");
    var h = addZero(d.getHours());
    var m = addZero(d.getMinutes());
    var s = addZero(d.getSeconds());
    x.innerHTML = h + ":" + m + ":" + s + " (GMT" + nHours + ")";
}


// perform the following when document is ready
$(document).ready(function() {
//after the video is ready
$video[0].oncanplay = function(){

//----------------------------------------------
//specifically for videos on different web pages
//----------------------------------------------

// After the video is playing
if(classVideo1ForProto === null){
	classVideo1ForNonProto.onplaying = function() {
		eventAfterVideoPlaying();
	};
}else{
	classVideo1ForProto.onplaying = function(){
		eventAfterProtoMoviePlaying();
	};
	classVideo1ForProto.onended = function(){
		triggerLogout();
	};
}

//----------------------------------------------
//for every video
//----------------------------------------------

// disable mouse right-click
$video.bind("contextmenu",function(){
	return false;
});

//Play/pause on video click
$video.click(function() {
	playVideo();
});
$(".play").click(function() {
	playVideo();
});
$(".pause").click(function() {
	playVideo();
});

// Play/pause on spacebar 
$("body").on("keydown", function(e) {
	if(e.keyCode === 32 ) {	
		e.preventDefault();		
		playVideo();     
	}
});

// Video duration timer
$video.on("timeupdate", function() {
	var $videoTime = $video[0].currentTime;	
	var tickingTime = timeFormat($videoTime);
	$duration.html(tickingTime.minutes + ":" + tickingTime.seconds + " / ");
});

/* VIDEO CONTROLS
------------------------------------------------------- */

// // Progress bar
// $progressBar[0].addEventListener("change", function() {
	// var time = $video[0].duration * ($progressBar[0].value / 100);
	// $video[0].currentTime = time;
// }); 

// // Update progress bar as video plays
// $video[0].addEventListener("timeupdate", function() { 
	// var value = (100 / $video[0].duration) * $video[0].currentTime;
	// $progress.css("width", value+"%");	
// }); 

//hide and show videoControls
$videoContainer.mouseenter(function(){
	$videoControls.fadeIn(500);
});
$videoContainer.mouseleave(function(){
	$videoControls.fadeOut(500);
});

// Play/pause on button click
$playButton.click(function() {
	playVideo();
});

// Volume slider
$volumeSlider.on("change", function(){ 
	$video[0].volume = $volumeSlider[0].value;
});

$fullScreenBtn.click(function() {
	launchFullscreen($video[0]);
	// launchFullscreen($("#fullScreenWrapper")[0]);
	// launchFullscreen($videoControls[0]);
}); 

}
 } );