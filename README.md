REST-HelloWorld
===============

An example that shows how easy to make RESTful web service by using <a href="https://github.com/SalamaSoft/REST-framework">REST-framework</a>

------------------------------------------------------------------------------
* <a href="https://dl.dropboxusercontent.com/u/48157648/doc/REST-HelloWorld/DebugJavaWebInEclipse.html">A guide about how to debug java web in eclipse (For Java newbies)</a>
  After configuring eclipse correctly, you would be able to visit the page: http://localhost:8080/testWS/
  This page is entry of other demo page.

* This demo project will demonstrate features below:

  --- About how to invoke web service, how diliver parameters
      (odinary type such as String, int, etc. Multipart type, means uploading file)
      A typical code is like below:
      
     In JavaScript(client side):
      <pre><code>
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
      </code></pre>

      In java(server side):
      <pre><code>
		public class HelloWorldService {
		
			public static String test1(String testParam) {
				return "hellow world! \n" + testParam;
			}
		}
      </code></pre>
  
  --- About how to load xml data into html in an easy way
      (There is a nice way and only xml format can do like this)
      
  --- About how to make login and control the access privilege
      In this framework it does not use the traditional way that stores login info in HttpSession or Cookie.
      A wrapped container provides methods to allocate ticket(also called "access token" in some web site), get/set session objects, etc, 
      it is agile to change persistence base, e.g., storing in Redis. 
  
  --- About how to configure wrapped session(to store some key-values) that persist in Redis.
      (Just config and need no more code, and that is also open source)
