const learnBtn = document.getElementById('btn-learn');
const info = document.getElementById('info');
const infoBtn = document.getElementById('btn-info');
const tutorialBtn = document.getElementById('btn-tutorial')

$(learnBtn).click(function (){
    hideTutorial();
});

$(infoBtn).click(function (){
    showTutorial();
});

$(tutorialBtn).click(function (){
  showTutorial();
})

// This is used by the btn-info
function showTutorial() {

    // Deletes the cookie that skips the tutorial
    deleteCookie("skipTutorial");

    $(info).show("fast");
    $(devicesAlarm).hide("slow", function hideNext() {
        $(this).next("section").hide("slow", hideNext);
    });
}

// This is used by the btn-learn
function hideTutorial(){

    // Sets a cookie that will skip the tutorial for 5 days
    setCookie("skipTutorial", "True", 5);

    $(info).hide("fast");

    showDevices();
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

// The sidebar section begins here
const btnAlarm = document.getElementById('btn-alarm');
const btnLight = document.getElementById('btn-light');
const btnTemps = document.getElementById('btn-temp');
const btnMisc = document.getElementById('btn-misc');

const devicesAlarm = document.getElementById('alarms');
const devicesLight = document.getElementById('lights');
const devicesTemps = document.getElementById('temperatures');
const devicesMisc = document.getElementById('misc');

var devices = [false, false, false, false];

$(btnAlarm).click(function (){
  $(btnAlarm).toggleClass('clicked');
  $(devicesAlarm).toggle('slow');

  if(devices[0] == false){
    devices[0] = devicesAlarm;
  } else {
    devices[0] = false;
  }
});

$(btnLight).click(function (){
    $(btnLight).toggleClass('clicked');
    $(devicesLight).toggle('slow');

    if(devices[1] == false){
      devices[1] = devicesLight;
    } else {
      devices[1] = false;
    };
});

$(btnTemps).click(function (){
  $(btnTemps).toggleClass('clicked');
  $(devicesTemps).toggle('slow');

  if(devices[2] == false){
      devices[2] = devicesTemps;
    } else {
      devices[2] = false;
    }
})

$(btnMisc).click(function (){
  $(btnMisc).toggleClass('clicked');
  $(devicesMisc).toggle('slow');

  if(devices[3] == false){
    devices[3] = devicesMisc;
  } else {
    devices[3] = false;
  }
})

function showDevices() {

  for (let index = 0; index < devices.length; index++) {

    if(devices[index] != false){
      $(devices[index]).show("slow");
    } else{
      $(devices[index]).hide();
    }
  }
}
