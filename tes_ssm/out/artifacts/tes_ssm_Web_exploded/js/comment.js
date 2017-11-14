//@ sourceURL=comment.js
var allComment;//进入页面查询所有评论的结果
$(function(){
	findComment();
	$('[data-toggle="popover"]').popover();
	
	/**
	$("#chk_all").click(function() {
		if ($(":checkbox").prop("checked")){
			$(":checkbox").prop("checked",true);
		}
		else{
			$(":checkbox").removeAttr("checked");
		}
	});
	*/
	
	$(".btn-group button").click(function(){
		active($(this));
	});
	
	$("#pushPanel .btn-group button:last").click(function(){
		check_all(this);
	});
	
	$("#refresh_btn").click(function(){
		if($(this).children("i").hasClass("icon-spin")) {
			$(this).children("i").removeClass("icon-spin");
			end_push();
		} else {
			$(this).children("i").addClass("icon-spin");
			start_push();
			cycle();
		}
	});
	
	$(".nav-tabs li:last").click(function(){
		$("#refresh_btn i").removeClass("icon-spin");
		end_push();
	});
	
	$("#push_panels .btn-success").click(function(){
		pass(this);
	});
	
	$("#push_panels .btn-danger").click(function(){
		reject(this);
	});
	
	$("#comment_time").click(function(){
		time_order(this);
	});
	
});

function check_all(btn) {
	if($(btn).hasClass("active")) {
		$(btn).siblings("button").addClass("active");
	} else {
		$(btn).siblings("button").removeClass("active");
	}
}

//数据库查询评论数据
function findComment(){
	$.ajax({
		url:basePath+"/find/comment",
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//将查询数据传给全局变量，将所有评论存储在allComment中
				allComment=result;
			}else if(result.status==1){
				alert(result.message);
			}
		},
		error:function(){
			alert("目前无评论数据");
		}
	});
}
var i = 1;
var j = 0;

function start_push() {
	var comments=allComment.data;
	
	$(comments).each(function(n,value){
		var video=value.video;//comment中的视频信息
		var user=value.user;//comment中的用户信息
		var course=value.course;//comment中的课程信息
		// 拉取数据
		var $panel = 
	          '<div id="'+(n+1)+'"class="panel panel-default" style="display:none">'+
	            '<div class="text-center">' +
	              '<h3><a href="#">'+course.name+' '+video.title+'</a></h3>' +
	              '<div>' +
	                  '<span>用户：<a href="#">'+user.loginName+'</a></span> • <span>'+new Date(value.timestamp).toLocaleDateString()+'</span>' +
	              '</div>' +
	            '</div>' +      
	            '<div class="panel-body">' +
	              '<h4>'+value.content+'</h4>' +
	              '<div class="text-right">' +
	                  '<button type="button" class="btn btn-success">&nbsp;&nbsp;通&nbsp;&nbsp;过&nbsp;&nbsp;</button> ' +
	                  '<button type="button" class="btn btn-danger">&nbsp;&nbsp;拒&nbsp;&nbsp;绝&nbsp;&nbsp;</button>' +
	              '</div>' +
	            '</div>' +
	          '</div>';
		j = $("#push_panels").children("div").length;
		if(j<comments.length){
			$("#push_panels").append($panel);
		}else if(i==comments.length){
			j=0;
			i=1;
			findComment();
		}
	});
	
}

function cycle(){
	push_timer = setInterval(function(){
		var str="#"+i;
		if(i <= 7) {
			$(str).css("display","block");//两秒显示一条
			i++;//循环变量，选取下一条
			
			$("#push_panels .btn-success").click(function(){
				pass(this);
			});
			$("#push_panels .btn-danger").click(function(){
				reject(this);
			});
		}
	}, 2000);
	
}

function end_push() {
	clearInterval(push_timer);
}

function pass(btn) {
	$(btn).parent().parent().parent().remove();
}

function reject(btn) {
	$(btn).parent().parent().parent().remove();
}

function time_order(btn) {
	var $span = $(btn).children("span");
	if($span.hasClass("glyphicon-chevron-up")) {
		$span.removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
	} else {
		$span.removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
	}
}