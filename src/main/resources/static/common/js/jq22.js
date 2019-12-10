// JavaScript Document
function b(){	
	t = parseInt(x.css('top'));
	y.css('top','19px');

	console.log('t>>>>>>>>>>>>>>>>>>>>>>>>>>>'+t);
	if(t == 18){
		t = 0;
	}
	x.animate({top: (t - 19) + 'px'},'slow');	//19为每个li的高度

	if(Math.abs(t) == h-19){ //19为每个li的高度
		y.animate({top:'0px'},'slow');

		z=x;
		x=y;
		y=z;

        console.log('Z：'+(z));
        console.log('x：'+(x));
        console.log('Y：'+(y));
	}

	setTimeout(b,3000);//滚动间隔时间 现在是3秒
}
$(document).ready(function(){

    //加载滚动数据
    $.ajax({
        url:'http://192.168.0.131:9999/wer/messageController/queryTop5MessageInfo',
        method:'get',
        dataType:'json',
		sync:false,
        success:function (res) {
            if(res.success){
                $('#msgListScroll').html(res.data);

                $('.swap').html($('.news_li').html());
                x = $('.news_li');
                y = $('.swap');
                h = $('.news_li li').length * 19; //19为每个li的高度
                setTimeout(b,3000);//滚动间隔时间 现在是3秒
            } else {
                $('#msgListScroll').html('');
            }
        },
        error:function (err) {
            alert('连接失败');
        }
    });

})