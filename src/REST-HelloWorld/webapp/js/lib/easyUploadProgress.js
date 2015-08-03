/**
 * @author XingGu Liu
 */
(function( window, undefined ) {
	var easyUploadProgress = new EasyUploadProgress();
	
	function EasyUploadProgress() {
		this.upload = function(args) {
		    var file;
		    if(args.file) {
		        file = args.file;
		    } else {
		        file = args.domFile.files[0];
		    }
			uploadImp(args.url, file, args.onProgress, args.onCompleted);
		};
		
		function uploadImp (
				url,
				file,
				onProgress,
				onCompleted
				) {
            var req = createXmlRequest();
            
            req.file = file;
            req.addEventListener('progress', 
                function(event) {
                   handleProgress(event, onProgress);
                }, false);
            if(req.upload) {
                req.upload.onprogress = function(event) {
                   handleProgress(event, onProgress);
                };
            }
            
            req.onreadystatechange = function(event) {
                 if(4 == this.readyState) {
                     //console.log("upload completed");
                     if(onCompleted) {
                         onCompleted(this);
                     }
                 }
            };
            
            req.open('post', url, true);
            //req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            req.send(file);
		}
		
        function handleProgress(event, callback) {
            var loaded, total;
            if(event.loaded) {
                loaded = event.loaded;
                total = event.total;
            } else {
                //old field name
                loaded = event.position;
                total = event.totalSize;
            }
            
            if(callback) {
                callback(total, loaded);
            }
        }
		
		
        function createXmlRequest() {
            var req;
            
            if (window.XMLHttpRequest) {
                req = new XMLHttpRequest();
            } else if (window.ActiveXObject)
            {
                try {
                    req = new ActiveXObject("Msxml2.XMLHTTP");
                } catch(e) {
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            
            return req;
        }
		
	}
	
	//Expose
	window.easyUploadProgress = easyUploadProgress;

})(window);

