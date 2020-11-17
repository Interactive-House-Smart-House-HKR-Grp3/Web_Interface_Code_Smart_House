const learnBtn = document.getElementById('btn-learn');
const devices = document.getElementById('devices');
const info = document.getElementById('info');
const infoBtn = document.getElementById('btn-info');

$(learnBtn).click(function (){
    hideTutorial();
});

$(infoBtn).click(function (){
    showTutorial();
});

// This is used by the btn-info
function showTutorial() {

    // Deletes the cookie that skips the tutorial
    deleteCookie("skipTutorial");

    $(info).show("slow");
    $(devices).hide("fast", function hideNext() {
        $(this).next("section").hide("fast", hideNext);
    });
}

// This is used by the btn-learn
function hideTutorial(){

    // Sets a cookie that will skip the tutorial for 1 day
    setCookie("skipTutorial", "True", 1);

    $(info).hide("slow");
    $(devices).show("fast", function showNext() {
        $(this).next("section").show("fast", showNext);
    });
}

// Sets a cookie with the name, the value, and days until expiration
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

// Gets a cookie using the key name
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

// Deletes *** ALL *** cookies with keyValue of cname
function deleteCookie(cname) {
    // Deletes the cookie by setting expiration data to an already passed date
    // This will delete the cookie on session end (closing / refresh)
document.cookie = cname + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;"; 
}


function skipTutorial() {
  var skip = getCookie("skipTutorial");
  if (skip != "") {
      hideTutorial();
  } else {
      showTutorial();
  }
}