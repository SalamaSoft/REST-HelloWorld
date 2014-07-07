var WEB_APP = "/testWS";

function viewSource() {
	var curHtmlName = String(window.location);
	var index = curHtmlName.lastIndexOf('/');
	curHtmlName = curHtmlName.substring(index + 1);
	window.location = "viewSource.html?html=" + curHtmlName;
}
