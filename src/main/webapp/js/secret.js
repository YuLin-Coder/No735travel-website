/*禁止查看js文件*/
$(function(){
    //屏蔽键盘事件
    document.onkeydown = function (){
        var e = event || window.event || arguments[0];
        //禁止F12
        if(e && e.keyCode == 123){
            return false;
            //禁止Ctrl+Shift+I
        }else if((e.ctrlKey) && (e.shiftKey) && (e.keyCode == 73)){
            return false;
            //禁止Shift+F10
        }else if((e.shiftKey) && (e.keyCode == 121)){
            return false;
            //禁止Ctrl+U
        }else if((e.ctrlKey) && (e.keyCode == 85)){
            return false;
        }
    };
    //禁止审查元素
    $(document).bind("contextmenu",function(e){
        return false;
    });
    //屏蔽鼠标右键
    document.oncontextmenu = function (){
        return false;
    };

    //屏蔽ctrl+s 保存
    window.onkeydown = function(e) {
        if (e.ctrlKey && e.keyCode == 83) {
            e.preventDefault();
            e.returnValue = false;
            return false;
        }
        //屏蔽ctrl+p 打印
        if (e.ctrlKey && e.keyCode == 80) {
            e.preventDefault();
            e.returnValue = false;
            return false;
        }
    };

    // 去除url中的参数
    // var url=window.location.href;//获取当前页面的url
    // if(url.indexOf("?")!=-1){ //判断是否存在参数
    //     url = url.replace(/(\?|#)[^'"]*/, '');//去除参数
    //     window.history.pushState({},0,url);
    // }
});

