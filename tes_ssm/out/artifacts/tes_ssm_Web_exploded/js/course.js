var courseId;
$(function(){
	findCourseByPage(1);
	$("#course_btn").click(function(){
		findCourseByPage(1);
	});
	//为搜索按钮添加事件
	$("#search_button").click(function (){
		return findCourseByPage(1);
	});
	$("#delete_btn").click(function(){
		var dom=getTrId(courseId,"delcourse");
		console.log("得到的删除行对象dom="+dom);
		return deleteCourse(courseId,dom);
	});
	$("#editCourse_form").submit(function(){
		//var dom=getTrId(courseId,"updatecourse");
		return updateCourse(courseId);
	});
	$("#addcourse_form").submit(function(){
		return addCourse();
	});
	
});
//为编辑超链接添加事件
function updateCourseClick(id){
	courseId=id;
}
//为删除超链接添加事件
function deleteCourseClick(id){
	courseId=id;
}
//获取id
function getTrId(courseId,type){
	return $("#"+type+courseId).parent().parent();
}
//新增的方法
function addCourse(){
	//1.获取页面数据
	var courseName=$("#add_courseName").val();
	var courseOrder=$("#add_recommended").val();
	var courseDesc=$("#addCoursePanel form textarea[name='addtextarea']").val();
	console.log("courseName="+courseName);
	console.log("courseOrder="+courseOrder);
	console.log("courseDesc="+courseDesc);
	//2.异步请求服务器
	$.ajaxFileUpload({
		url:basePath+"AddCourseServlet",
		secureuri:false,
		fileElementId:"add_picture",
		type:"post",
		dataType:"json",
		data:{"courseName":courseName,"courseOrder":courseOrder,"courseDesc":courseDesc},
		success:function(result){
			alert("进入成功事务了");
			if(result.status==0){
				alert(result.message);
			}else if(result.status==1){
				alert(result.message);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
	return false;
}
//编辑的方法
function updateCourse(courseId){
	//1.获取页面数据
	var courseName=$("#courseName").val();
	var courseOrder=$("#recommended").val();
	var courseDesc=$("#editCourse form textarea[name='courseDesc']").val();
	//2.异步请求服务器
	$.ajaxFileUpload({
		url:basePath+"UpdateCourseServlet",
		secureuri:false,
		fileElementId:"picture",
		type:"post",
		dataType:"text",
		data:{"courseId":courseId,"courseName":courseName,"courseOrder":courseOrder,"courseDesc":courseDesc},
		success:function(result){
			alert("进入成功事务了");
			if(result.status==0){
				alert(result.message);
			}else if(result.status==1){
				alert(result.message);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
	$('#editCourse').modal("toggle");
	$('#editCourse').on('hidden.bs.modal', function (e) {
		  findCourseByPage(1);
		})
	return false;
}
//删除的方法
function deleteCourse(courseId,dom){
	alert("进删除方法");
	$.ajax({
		url:basePath+"DeleteCourseServlet",
		type:"get",
		data:{"courseId":courseId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//数据库删除完毕
				alert(result.message);
				//删除页面的那个行tr
				dom.remove();
			}else if(result.status==1){
				alert(result.message);
			}
		},
		error:function(){
			alert("请求服务器失败");
		},
		async:true
	});
	//关闭当前删除确认modal模态框
	$("#deletecourse_modal").modal('toggle');
	return false;
}
//课程的分页
function findCourseByPage(currentPage){
	alert("进入分页了");
	var courseName=$("#course_name").val();
	if(courseName==""){
		courseName="undefined";
	}
	alert("搜索关键字="+courseName+" 当前页="+currentPage);
	$.ajax({
		url:basePath+"course/page/"+currentPage+"/"+courseName,
		type:"get",
		//data:{"currentPage":currentPage,"courseName":courseName},
		dataType:"json",
		success:function(result){
			alert("进入成功");
			$("#course_table tbody").html("");
			$("#course_page").html("");
			//alert("页面清空了");
			var page=result.data;
			var course=page.data;
			$(course).each(function(n,value){
				var tr='<tr>'+
	                '<td>'+(n+1)+'</td>'+
	                '<td><img src="images/coursehead/'+value.picture+'" alt="加载失败"></td>'+
	                '<td>'+value.name+'</td>'+
	                '<td>'+value.order+'</td>'+
	                '<td>2015-07-03</td>'+
	                '<td>'+value.desc+'</td>'+
	                '<td>'+
	                  '<a href="" onclick=updateCourseClick(\''+value.id+'\') id="updatecourse'+value.id+'" data-toggle="modal" data-target="#editCourse"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'+
	                  '<a href="" onclick=deleteCourseClick(\''+value.id+'\') id="delcourse'+value.id+'" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>'+
	                '</td>'+
	              '</tr>';
				$("#course_table tbody").append(tr);
			});
			if(page.aNum.length>0)
				//处理前一页
				var previoursPage='<li>'+
				          '<a href="javascript:findCourseByPage('+page.previousPage+');" aria-label="Previous">'+
				          	'<span aria-hidden="true">&laquo;</span>'+
				          '</a>'+
				          '</li>';
			//alert(page.previousPage);
			$("#course_page").append(previoursPage);
				//处理中间页
				$(page.aNum).each(function (n,value){
					var middlePage='<li><a href="javascript:findCourseByPage('+value+');">'+value+'</a></li>';
					$("#course_page").append(middlePage);
				});
				//处理下一页
				var nextPage='<li>'+
			          '<a href="javascript:findCourseByPage('+page.nextPage+');" aria-label="Next">'+
			            '<span aria-hidden="true">&raquo;</span>'+
			          '</a>'+
			       '</li>';
				$("#course_page").append(nextPage);
		},
		error:function(){
			alert("请求服务器失败");
		},
	});
}