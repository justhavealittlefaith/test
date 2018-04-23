/*!
 * @name        main
 * @author      Ultnema
 * @modified    April 05th, 2018
*/

//for loader
document.onreadystatechange = function () {
  var state = document.readyState;
  if (state == 'complete') {
	  // document.getElementById("loader").style.display = "none";
	  $("#loader").fadeOut(500);
  }
}
