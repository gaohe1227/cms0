var upFile = function(){
	    var fileObj = document.getElementById("upload-file"); // 获取文件对象  
        if(fileObj){  
           console.log(fileObj +"=="+fileObj.value)
             // FormData 对象  
                 var form = new FormData();   
                 form.append("file", fileObj);// 文件对象     
          
                 // XMLHttpRequest 对象  
                 var xhr = new XMLHttpRequest();      
                 xhr.open('POST','/files/upload',true); // 异步传输   
                 xhr.onload = function (e) {   
                    console.log("开始加载");
                  
                 };   
                 xhr.send(form);  
                 xhr.onreadystatechange = function () {
              		if (xhr.readyState==4 && xhr.status ==200) {
              			alert(xhr.responseText);
              		}  
                 }
        }else{  
            alert("未选择文件");  
        }  
};
var timer
function uploadExcel1(fileId) {
    var name = document.getElementById(fileId).value;
    $("#showFile").html(name); 
    var postfix = name.substring(name.lastIndexOf(".") + 1);
    postfix = postfix.toLowerCase(); 
        $.ajaxFileUpload({
            url: '/user/upUsers',
            type:"post",
            data: { "key": 'test', "name":'lunis' }, //此参数非常严谨，写错一个引号都不行
            secureuri: false,
            fileElementId: fileId,// //文件上传空间的id属性
            dataType: 'text/html',
            success: function(meg) {
            
            	console.log(meg)
            	$("#showFile").html(meg)
            	
              //  timer = setInterval('getResultmap()', 100);
            	//timer = setInterval('getResultmap()', 100); 

            },
            error: function(data, status, e) {
                //alert("上传失败");
            }
        }); 
}
 var resultmeg=null;
function uploadExcel(fileId) {
    var name = document.getElementById(fileId).value;
    $("#showFile").html(name); 
    var postfix = name.substring(name.lastIndexOf(".") + 1);
    postfix = postfix.toLowerCase(); 
    timer = setInterval('getResultmap()', 500);
        $.ajaxFileUpload({
            url: '/user/upUsers',
            type:"post", 
            secureuri: false,
            fileElementId: fileId,// //文件上传空间的id属性
            dataType: 'text/html',
            success: function(meg) { 
           	 clearInterval(timer);
        	 timer=null;
            	 console.log(meg)  
            resultmeg=meg;
            	$("#jindu").html(meg) 
            },
            error: function(data, status, e) {
                //alert("上传失败");
            }
        }); 
}

var timer = null;
function getResultmap() {

    $.ajax({
        type: "get",
        url: "/user/getResultmap?dateStr=" + new Date(),
        data: {
            "key": "todr"

        },
        success: function(meg) {
            console.log(meg+"---------------")
            if (meg == -1) {
                $("#jindu").html("上传结束")
                clearInterval(timer);
                $("#jindu").html(resultmeg)

            } else {
                var msg = eval('(' + meg + ')')
                $("#jindu").html(msg.todr.message);
                if (msg.todr.state == -10) {
                    clearInterval(timer);
                    $("#jindu").html(resultmeg);
                }

            }

        }

    });

}









function openBrower() {
    var ie = navigator.appName == "Microsoft Internet Explorer" ? true: false;
    if (ie) {
        document.getElementById("onFile").click();
        document.getElementById("_fileName").value = document.getElementById("_file").value;
    } else {
        var a = document.createEvent("MouseEvents");
        a.initEvent("click", true, true);
        document.getElementById("onFile").dispatchEvent(a);
    }

}