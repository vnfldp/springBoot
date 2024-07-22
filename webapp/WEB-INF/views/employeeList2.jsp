<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" 
%><%@ include file="header.jsp"%>
<title>MANI TECH | 기본 정보</title>
<div class="container">
    <form name="frmSearch" action="/employee/list2" method="post">
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
	
	var list = ${list}; // 기본정보 목록
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
		    colNames : ['이름','생년월일','성별','연락처','이메일','주소','학력1','학력2','학력3','직위','경력년수','등급','코사여부','기사여부','비고'], 
			colModel:[
					{name:"EMPLOYEE_NAME" ,index:"EMPLOYEE_NAME",width:70 ,align:'center',hidden:false},
					{name:"BIRTHDATE"     ,index:"BIRTHDATE"    ,width:70 ,align:'center',hidden:false},
					{name:"GENDER"	      ,index:"GENDER"	    ,width:50 ,align:'center',hidden:false},
					{name:"PHONE"	      ,index:"PHONE"	    ,width:150,align:'center',hidden:false},
					{name:"EMAIL"	      ,index:"EMAIL"	    ,width:150,align:'left'  ,hidden:false},
					{name:"ADDR"	      ,index:"ADDR"	    	,width:200,align:'left'  ,hidden:false},
					{name:"EDU1"	      ,index:"EDU1"	    	,width:100,align:'left'  ,hidden:false},
					{name:"EDU2"	      ,index:"EDU2"	    	,width:100,align:'left'  ,hidden:false},
					{name:"EDU3"	      ,index:"EDU3"	    	,width:100,align:'left'  ,hidden:false},
					{name:"POSITION_CODE" ,index:"POSITION_CODE",width:100,align:'center',hidden:false},
					{name:"YEARS"	      ,index:"YEARS"	    ,width:50,align:'center' ,hidden:false},
					{name:"GRADE"	      ,index:"GRADE"	    ,width:50,align:'center' ,hidden:false},
					{name:"COSA_YN"	      ,index:"COSA_YN"	    ,width:50,align:'center' ,hidden:false},
					{name:"GISA_YN"	      ,index:"GISA_YN"	    ,width:50,align:'center' ,hidden:false},
					{name:"REMARKS"	      ,index:"REMARKS"	    ,width:100,align:'center',hidden:false}
			],
		    loadtext: "로딩중..",
		    //caption: "개발자 목록",

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
		$("#employeeName").val(''), 
		$("#addr").val(''), 
		$("#grade").val(''), 
		$("#projectName").val(''),
		$("#dev").val(''),
		
		$("#rows_tot").text(0);

		$("#jqGrid").clearGridData();
	}
		
	// 검색 버튼 클릭
	function getData() {
		 $.ajax({
			type: "post", 			//1.서버에 요청하는 방식(get/post) 
			dataType : "json",
			async:false,
			url: "/employee/list3", 		//2.서버에 요청되는 주소 
			data: {					//3.클라이언트가 서버에 전달할 데이터 
				employeeName: $("#employeeName").val(), 
				addr: $("#addr").val(), 
				grade: $("#grade").val(), 
				projectName: $("#projectName").val(),
				dev: $("#dev").val(),
				grade1: $("#express").is(":checked"),
				grade2: $("#advanced").is(":checked"),
				grade3: $("#intermediate").is(":checked"),
				grade4: $("#beginner").is(":checked"),
				
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