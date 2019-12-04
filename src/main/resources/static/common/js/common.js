/* 提供公共js */
//服务器访问地址
var server_url = "http://www.baidu.com";

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if(o[this.name]) {
            if(!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

function Appendzero(obj) {
    if(obj<10) return "0" +""+ obj;
    else return obj;
}

//获取当前日期
function getCurrDate() {
    var date = new Date();

    var year = date.getFullYear();
    var month = (date.getMonth()+1);
    var day = date.getDate();

    //返回当前日期
    return year+'-'+Appendzero(month)+'-'+Appendzero(day);
}