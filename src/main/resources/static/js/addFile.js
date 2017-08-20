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
function uploadExcel(fileId) {
    var name = document.getElementById(fileId).value;
    $("#showFile").html(name); 
    var postfix = name.substring(name.lastIndexOf(".") + 1);
    postfix = postfix.toLowerCase(); 
        $.ajaxFileUpload({
            url: '/files/upFile',
            secureuri: false,
            fileElementId: fileId,
            dataType: 'text/html',
            success: function(meg) {
            	console.log(msg)
              //  timer = setInterval('getResultmap()', 100);

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
        url: "/zhjs/yhjs/getResultmap?dateStr=" + new Date(),
        data: {
            "key": "todr"

        },
        success: function(meg) {
            console.log(meg)
            if (meg == -1) {
                $("#jindu").html("上传结束")
                clearInterval(timer);

            } else {
                var msg = eval('(' + meg + ')')
                $("#jindu").html(msg.key.message)
                if (msg.key.state == -1) {
                    clearInterval(timer);
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