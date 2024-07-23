<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<title>MANI TECH | 프로젝트목록 </title>
<div class="container">
    <form name="frmSearch" action="/employee/EmployeeProjectList" method="post">
		<%@ include file="search2.jsp"%>
	</form>
	<br/>
	<div class="col-sm-5">총 <strong><span id="rows_tot">${totalCnt}</span></strong>개 등록되어 있습니다.</div>
	<table id="jqGrid"></table>
	<div id="gridpager"></div>
</div>
</body>
</html>
<script type="text/javascript">
	
	var list = ${list}; // 프로젝트 목록
	var cnt = ${totalCnt}; // 기본정보 개수
	
	// 개수 세팅
	$("#rows_tot").text(cnt);
	
	// 그리드 세팅	
	initData(list);
	
	// 그리드 데이터 세팅
	function initData(list) {
		var dataArr = []; 
		dataArr = list;
				 
		$("#jqGrid").jqGrid({
		    datatype: "local",
		    data: dataArr,
		    height: 350, 
		    width: 1080,
		    colNames : ['이름','프로젝트ID','프로젝트이름','프로젝트날짜','시작일','종료일','고객','근무회사','역할','OS','언어','DBMS','TOOL','COMMUNICATION','WAS','FRAMEWORK','기타'], 
			colModel:[
					{name:"EMPLOYEE_NAME"     ,index:"EMPLOYEE_NAME"     ,width:70  ,align:'center' ,hidden:false},
					{name:"PROJECT_ID"        ,index:"PROJECT_ID"        ,width:70  ,align:'left'   ,hidden:false},
					{name:"PROJECT_NAME"	  ,index:"PROJECT_NAME"	     ,width:50  ,align:'center' ,hidden:false},
					{name:"PROJECT_DATE"	  ,index:"PROJECT_DATE"	     ,width:150 ,align:'center' ,hidden:false},
					{name:"START_DATE"	      ,index:"START_DATE"	     ,width:150 ,align:'center' ,hidden:false},
					{name:"END_DATE"	      ,index:"END_DATE"	    	 ,width:200 ,align:'center' ,hidden:false},
					{name:"CLIENT"	          ,index:"CLIENT"	    	 ,width:100 ,align:'center' ,hidden:false},
					{name:"COMPANY"	          ,index:"COMPANY"	    	 ,width:100 ,align:'center' ,hidden:false},
					{name:"DEV_ROLE"	      ,index:"DEV_ROLE"	    	 ,width:100 ,align:'center' ,hidden:false},
					{name:"DEV_OS"            ,index:"DEV_OS"            ,width:100 ,align:'center' ,hidden:false},
					{name:"DEV_LANGUAGE"	  ,index:"DEV_LANGUAGE"	     ,width:50  ,align:'center' ,hidden:false},
					{name:"DEV_DBMS"	      ,index:"DEV_DBMS"	         ,width:50  ,align:'center' ,hidden:false},
					{name:"DEV_TOOL"	      ,index:"DEV_TOOL"	         ,width:50  ,align:'center' ,hidden:false},
					{name:"DEV_COMMUNICATION" ,index:"DEV_COMMUNICATION" ,width:50  ,align:'center' ,hidden:false},
					{name:"DEV_WAS"	          ,index:"DEV_WAS"	         ,width:100 ,align:'center' ,hidden:false},
					{name:"DEV_FRAMEWORK"	  ,index:"DEV_FRAMEWORK"	 ,width:100 ,align:'center' ,hidden:false},
					{name:"OTHER_DETAILS"	  ,index:"OTHER_DETAILS"	 ,width:100 ,align:'center' ,hidden:false}
			],
		    loadtext: "로딩중..",
		    //caption: "프로젝트 목록",

		    pager:"#gridpager",
		    rowNum:500,
			rowList:[10,30,50,100,200,500],  //rowNum 수 변경
			rownumbers : true,  //리스트 순번

		    //rownumbers:true,
		    //viewrecords:true,
		    //pgbuttons:true,
		    //pginput:true,
		    //shrinkToFit:true,
		    //sortable: false,
		    //loadComplete:function(data){},
		    //scroll:true,
		    //loadonce:false,
		    //hidegrid:true
		    }); 
	}
	
	// 초기화 - 검색어, 그리드
	function fn_init() {
		$("#employeeName").val('');
		$("#addr").val('');
		$("#grade").val(''); 
		$("#projectName").val('');
		$("#dev").val('');
		$("#rows_tot").text('0');

		$("#jqGrid").clearGridData();
	}
		
	// 검색 버튼 클릭
	function getData() {
		 $.ajax({
			type: "post", 			                           //1.서버에 요청하는 방식(get/post) 
			dataType : "json",
			async:false,
			url: "/employee/SearchEmployeeProjectList", //2.서버에 요청되는 주소 
			data: {					                           //3.클라이언트가 서버에 전달할 데이터 
				employeeName: $("#employeeName").val(), 
				addr        : $("#addr").val(), 
				grade       : $("#grade").val(), 
				projectName : $("#projectName").val(),
				dev         : $("#dev").val(),
				grade1      : $("#express").is(":checked"),
				grade2      : $("#advanced").is(":checked"),
				grade3      : $("#intermediate").is(":checked"),
				grade4      : $("#beginner").is(":checked"),
				
			},
			success: function(data){
			  $("#rows_tot").text(data.totalCnt);
			  //JSON.stringify(data.list)
			  $("#jqGrid").clearGridData();
			  $("#jqGrid").jqGrid('setGridParam',{'data' : data.list}).trigger('reloadGrid');
		    },
			error : function(request, status, error) {
		      console.log("code : " + request.status + "\r\nmessage : " + request.reponseText +" \r\n error : " + error);
		    }
		 });//end ajax 
	}

</script>

<%@ include file="footer.jsp"%>