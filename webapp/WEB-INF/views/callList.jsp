<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" 
%><%@ include file="header.jsp"%>
<title>MANI TECH | 상담내용</title>
<div class="container">
    <form name="frmSearch" action="/employee/list2" method="post">
		<%@ include file="search3.jsp"%>
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
	
	$("#rows_tot").text(cnt);
	
	// 그리드 세팅	
	initData(list);
	
	// 그리드 초기하
	function initData(list) {
		var dataArr = []; 
		dataArr = list;
				 
		// 개발자 목록 그리드 초기화
		$("#jqGrid").jqGrid({
		    datatype: "local",
		    data: dataArr,
		    height: 300, 
		    width: 1080,
		    colNames : ['상담일자','이름','순번','상담내용','조치내용','등록일자','등록자','수정일자','수정자'], 
			colModel:[
					{name:"REG_DATE" 	  ,index:"REG_DATE"			,width:80 ,align:'center'	,hidden:false},
					{name:"EMPLOYEE_NAME" ,index:"EMPLOYEE_NAME"    ,width:100 ,align:'center'	,hidden:false},
					{name:"HISTORY_ID"	  ,index:"HISTORY_ID"	    ,width:70 ,align:'center'	,hidden:false},
					{name:"NOTE"	      ,index:"NOTE"	    		,width:200,align:'left'		,hidden:false},
					{name:"ACTION"	      ,index:"ACTION"	   		,width:200,align:'left'  	,hidden:false},
					{name:"REGDATE"	      ,index:"REGDATE"	    	,width:100,align:'center'  	,hidden:false},
					{name:"REGID"	      ,index:"REGID"	    	,width:100,align:'center'  	,hidden:false},
					{name:"MODDATE"	      ,index:"MODDATE"	    	,width:100,align:'center'  	,hidden:false},
					{name:"MODID"	      ,index:"MODID"	    	,width:100,align:'center'  	,hidden:false}
			],
		    loadtext: "로딩중..",
		    //caption: "개발자 목록",

		    pager:"#gridpager",
		    rowNum:500,
			rowList:[10,30,50,100,200,500],  //rowNum 수 변경
			rownumbers : true,  //리스트 순번
			onCellSelect: function(rowId,iCol) {
				var rowData = $("#jqGrid").getRowData(rowId, iCol);
				
				// 상담내용 상세 조회
				getDetail(rowData.REG_DATE, rowData.EMPLOYEE_NAME, rowData.HISTORY_ID);
			},

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
		// 검색어
		$("#employeeName").val(''); 
		$("#addr").val(''); 
		$("#grade").val(''); 
		$("#projectName").val('');
		$("#dev").val('');
		
		// 상담내용 목록
		$("#rows_tot").text(0);
		$("#jqGrid").clearGridData();
		
		// 상담내용 조회
		
	}
		
	// 검색 버튼 클릭
	function getData() {
		 $.ajax({
			type: "post", 			//1.서버에 요청하는 방식(get/post) 
			dataType : "json",
			async:false,
			url: "/call/list3", 		//2.서버에 요청되는 주소 
			data: {					//3.클라이언트가 서버에 전달할 데이터 
				startDate: $("#startDate").val(), 
				endDate: $("#endDate").val(),
				employeeName: $("#employeeName").val(), 
				note: $("#note").val()
			},
			success: function(data){
				$("#rows_tot").text(data.totalCnt);
				//JSON.stringify(data.list)
				$("#jqGrid").clearGridData();
				$("#jqGrid").jqGrid('setGridParam',{'data' : data.list}).trigger('reloadGrid');

			  	$("#detail_tot").text(0);
			  	$("#jqGridDetail").clearGridData();
		    },
			error : function(request, status, error) {
		      console.log("code : " + request.status + "\r\nmessage : " + request.reponseText +" \r\n error : " + error);
		    }
		 });//end ajax 
	}

	// 그리드 클릭 이벤트
	function getDetail(regData, name, callId) {
		 $.ajax({
			type: "post", 			//1.서버에 요청하는 방식(get/post) 
			dataType : "json",
			async:false,
			url: "/call/detail", 		//2.서버에 요청되는 주소 
			data: {					//3.클라이언트가 서버에 전달할 데이터 
				regData: regData, 
				employeeName: name, 
				historyId: callId
			},
			success: function(data){
			  console.log(data.list);
			  // 상담내용 상세
			  // 상담일자
			  // 순번
			  // 개발자명
			  // 상담내용
			  // 조치내용
			  // 등록자
			  
		    },
			error : function(request, status, error) {
		      console.log("code : " + request.status + "\r\nmessage : " + request.reponseText +" \r\n error : " + error);
		    }
		 });//end ajax 
	}
	
</script>
<%@ include file="footer.jsp"%>