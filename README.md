REST-HelloWorld
===============

An example that shows how easy to make RESTful web service by using <a href="https://github.com/SalamaSoft/REST-framework">REST-framework</a>

------------------------------------------------------------------------------
* This demo is deployed online. <a href="http://www.salama.com.cn/REST-HelloWorld" target="_blank">see it online</a>
* <a href="https://dl.dropboxusercontent.com/u/48157648/doc/REST-HelloWorld/DebugJavaWebInEclipse.html">A guide about how to debug java web in eclipse (For Java newbies)</a>
  After configuring eclipse correctly, you would be able to visit the page: http://localhost:8080/testWS/
  This page is entry of other demo page.

* This demo project will demonstrate features below:

  --- Invoke the web service
      (odinary type such as String, int, etc. Multipart type which is uploading file). A typical code is like below:
     <br/>In JavaScript(client side):
     <br/>
<pre>
      $.ajax({
		url: WEB_APP + "/cloudDataService.do",
		type: "post",
		dataType: "text",
		data: {
			serviceType: "com.salama.test.ws.service.HelloWorldService",
			serviceMethod: "test1",
			responseType: respType,
			testParam: (new Date())
		},
		success: function(data) {
			$('#result').text(data);
		},
		error: function(p0, p1, p2) {
			$('#result').text('Error');
		}
	});
</pre>

      In java(server side):
<pre>      
		public class HelloWorldService {
		
			public static String test1(String testParam) {
				return "hellow world! \n" + testParam;
			}
		}
</pre>

  --- Load xml data into html dom in my way
  
  --- Handle login and access control by 'authTicket'
      In this framework it does not use the traditional way that stores login info in HttpSession.
      A wrapped container provides methods to allocate ticket(also called "access token" in some documents), get/set session objects, etc, 
      the operation is abstract and data could be stored in different place, e.g., stored in Redis. 
