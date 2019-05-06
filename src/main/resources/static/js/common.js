var tme={};
tme.init=function(){
  tme.event();

}
// 配置事件
tme.event=function(){
  // tme.fnnav();
  tme.fnlink();
}
// 头部导航
tme.fnnav=function(){
  $('.nav li').hover(function(){
      $('span',this).stop(false,false).css('height','2px');
      $('span',this).stop(false,false).animate({
          left:'0%',
          width:'100%',
          right:'0'
      },200);

  },function(){
      $('span',this).stop(false,false).animate({
          left:'50%',
          width:'0'
      },200);
  });
}


tme.fnlink=function(){

  //20170816xg
  function fnnav(el){
    $(el).css({"opacity":"1","height":"auto"});
    }
    
  $(".js_link .select").hover(function(){
    $(this).find("ul").stop().slideDown(200,function(){
      fnnav(this);
      })
    },function(){
      $(this).find("ul").stop().fadeOut(200,function(){
       fnnav(this); 
        })  
  });
  $(".js_link .select ul").hover(function(){
    fnnav(this); 
    })
 //20170816xg end




}
$(document).ready( tme.init );