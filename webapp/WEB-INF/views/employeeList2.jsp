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
	<br/>
	<div class="col-sm-5"><strong>> 프로젝트 상세 (총 <span id="detail_tot">0</span>개) </strong></div>
	<table id="jqGridDetail"></table>
	<br/>
	<div class="col-sm-5"><strong>> 상담 상세 (총 <span id="note_tot">0</span>개) </strong></div>
	<table id="jqGridNote"></table>
</div>
</body>
</html>
<style>
	.dialog {
	  position: fixed;
	  left: 0;
	  top: 0;
	  z-index: 10;
	  width: 100%;
	  height: 100%;
	  background: rgba(0, 0, 0, 0.4);
	}

	.dialog>.tb {
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  width: 100%;
	  height: 100%;
	}

	.dialog>.tb .inner {
	  width: 100%;
	  padding: 20px;
	  background: #fff;
	  border-radius: 16px;
	}

	.dialog .top {
	  display: flex;
	  align-item: center;
	  border-bottom: 1px solid #ddd;
	  justify-content: space-between;
	  padding-bottom: 15px;
	  margin-bottom: 15px;
	}

	.dialog .title {
	  font-weight: bold;
	  font-size: 20px;
	}

	.dialog .ct {
	  overflow-y: auto;
	  border-bottom-left-radius: 10px;
	  border-bottom-right-radius: 10px;
	  background-color: #fff;
	}
	
</style>
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
		    colNames : ['이름','생년월일','성별','연락처','이메일','주소','학력1','학력2','학력3','직위','경력년수','등급','코사여부','기사여부','비고','프로젝트수','상담수'], 
			colModel:[
					{name:"EMPLOYEE_NAME" ,index:"EMPLOYEE_NAME",width:70 ,align:'center',hidden:false},
					{name:"BIRTHDATE"     ,index:"BIRTHDATE"    ,width:80 ,align:'center',hidden:false},
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
					{name:"REMARKS"	      ,index:"REMARKS"	    ,width:100,align:'center',hidden:false},
					{name:"PRJ_CNT"	      ,index:"PRJ_CNT"	    ,width:70 ,align:'center',hidden:false},
					{name:"CALL_CNT"	  ,index:"CALL_CNT"	    ,width:70 ,align:'center',hidden:false}
			],
		    loadtext: "로딩중..",
		    //caption: "개발자 목록",

		    pager:"#gridpager",
		    rowNum:500,
			rowList:[10,30,50,100,200,500],  //rowNum 수 변경
			rownumbers : true,  //리스트 순번
			onCellSelect: function(rowId,iCol) {
				var rowData = $("#jqGrid").getRowData(rowId, iCol);
				
				if (iCol == 1) {
					// 상담내역 저장
					$("#popEmpNm").val(rowData.EMPLOYEE_NAME);
					$("#modal").modal("show");
					
				} else {
					// 개발자별 상세 조회
					getDetail(rowData.EMPLOYEE_NAME);
				}
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
			
			// 개발자별 프로젝트 목록 그리드 초기화
			$("#jqGridDetail").jqGrid({
			    datatype: "local",
			    data: "",
			    height: 200, 
			    width: 1080,
				colNames : ['이름','프로젝트ID','프로젝트명','참여기간','시작일','종료일','고객사','근무회사','역활','OS','언어','DBMS','TOOL','통신','WAS','Framwork','기타'],
				colModel:[
						{name:"EMPLOYEE_NAME"     ,index:"EMPLOYEE_NAME"     ,width:70 ,align:'center',hidden:false},
						{name:"PROJECT_ID"        ,index:"PROJECT_ID"        ,width:70 ,align:'center',hidden:false},
						{name:"PROJECT_NAME"      ,index:"PROJECT_NAME"      ,width:150,align:'left'  ,hidden:false},
						{name:"PROJECT_DATE"      ,index:"PROJECT_DATE"      ,width:150,align:'center',hidden:false},
						{name:"START_DATE"        ,index:"START_DATE"        ,width:80 ,align:'center',hidden:false},
						{name:"END_DATE"          ,index:"END_DATE"          ,width:80 ,align:'center',hidden:false},
						{name:"CLIENT"            ,index:"CLIENT"            ,width:100,align:'left'  ,hidden:false},
						{name:"COMPANY"           ,index:"COMPANY"           ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_ROLE"          ,index:"DEV_ROLE"          ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_OS"            ,index:"DEV_OS"            ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_LANGUAGE"      ,index:"DEV_LANGUAGE"      ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_DBMS"          ,index:"DEV_DBMS"          ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_TOOL"          ,index:"DEV_TOOL"          ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_COMMUNICATION" ,index:"DEV_COMMUNICATION" ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_WAS"           ,index:"DEV_WAS"           ,width:100,align:'left'  ,hidden:false},
						{name:"DEV_FRAMEWORK"     ,index:"DEV_FRAMEWORK"     ,width:100,align:'left'  ,hidden:false},
						{name:"OTHER_DETAILS"     ,index:"OTHER_DETAILS"     ,width:100,align:'left'  ,hidden:false}
				],
			    loadtext: "로딩중..",
			    //caption: "개발자 목록",

			    //pager:"#gridpager",
			    rowNum:500,
				//rowList:[10,30,50,100,200,500],  //rowNum 수 변경
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
				
			// 개발자별 상담 목록
			$("#jqGridNote").jqGrid({
			    datatype: "local",
			    data: "",
			    height: 200, 
			    width: 1080,
				colNames : ['상담일자','순번', '이름','상담내용','조치내용','등록자','등록시간'],
				colModel:[
						{name:"REG_DATE"     	,index:"REG_DATE"     	,width:70 ,align:'center',hidden:false},
						{name:"HISTORY_ID"     	,index:"HISTORY_ID"     ,width:70 ,align:'center',hidden:false},
						{name:"EMPLOYEE_NAME"   ,index:"EMPLOYEE_NAME"  ,width:70 ,align:'center',hidden:false},
						{name:"NOTE"            ,index:"NOTE"           ,width:200,align:'left'  ,hidden:false},
						{name:"ACTION"     		,index:"ACTION"         ,width:70 ,align:'center',hidden:false},
						{name:"REGID"           ,index:"REGID"          ,width:100,align:'center',hidden:false},
						{name:"REGDATE"         ,index:"REGDATE"        ,width:100,align:'center',hidden:false}
				],
			    loadtext: "로딩중..",
			    //caption: "개발자 목록",

			    //pager:"#gridpager",
			    rowNum:500,
				//rowList:[10,30,50,100,200,500],  //rowNum 수 변경
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
		// 검색어
		$("#employeeName").val(''); 
		$("#addr").val(''); 
		$("#grade").val(''); 
		$("#projectName").val('');
		$("#dev").val('');
		
		// 개발자 목록
		$("#rows_tot").text(0);
		$("#detail_tot").text(0);
		$("#note_tot").text(0);
		
		// 개발자별 프로젝트 목록
		$("#jqGrid").clearGridData();
		$("#jqGridDetail").clearGridData();
		$("#jqGridNote").clearGridData();
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
				//grade1: $("#express").is(":checked"),
				//grade2: $("#advanced").is(":checked"),
				//grade3: $("#intermediate").is(":checked"),
				//grade4: $("#beginner").is(":checked"),
				
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
	function getDetail(name) {
		 $.ajax({
			type: "post", 			//1.서버에 요청하는 방식(get/post) 
			dataType : "json",
			async:false,
			url: "/employee/detail", 		//2.서버에 요청되는 주소 
			data: {					//3.클라이언트가 서버에 전달할 데이터 
				employeeName: name, 
			},
			success: function(data){
			  // 개발자별 프로젝트 목록
			  $("#detail_tot").text(data.totalCnt);
			  //JSON.stringify(data.list)
			  $("#jqGridDetail").clearGridData();
			  $("#jqGridDetail").jqGrid('setGridParam',{'data' : data.list}).trigger('reloadGrid');
			  
			  // 개발자별 상담내용 목록
			  $("#note_tot").text(data.totalCnt2);
			  //JSON.stringify(data.list)
			  $("#jqGridNote").clearGridData();
			  $("#jqGridNote").jqGrid('setGridParam',{'data' : data.list2}).trigger('reloadGrid');
		    },
			error : function(request, status, error) {
		      console.log("code : " + request.status + "\r\nmessage : " + request.reponseText +" \r\n error : " + error);
		    }
		 });//end ajax 
	}
	
	//모달 닫기 버튼 클릭
	$(document).on('click', '#btn_close',  function(e){
		$("#modal").modal("hide");
	});
	

	$(document).on('click', '#pop_close',  function(e){
		$("#modal").modal("hide");
	});
	
	//상담내용 저장
	$(document).on('click', '#btn_save',  function(e){

		$.ajax({
			type: "post", 			//1.서버에 요청하는 방식(get/post) 
			dataType : "json",
			async:false,
			url: "/employee/save", 		//2.서버에 요청되는 주소 
			data: {					//3.클라이언트가 서버에 전달할 데이터 
				employeeName: $("#popEmpNm").val(), 
				note: $("#note").val(),
				regId: $("#regId").val()
			},
			success: function(data){
				// 개발자별 상세 조회
				getDetail($("#popEmpNm").val());
			},
			error : function(request, status, error) {
			     console.log("code : " + request.status + "\r\nmessage : " + request.reponseText +" \r\n error : " + error);
			}
		});//end ajax 

		$("#modal").modal("hide");
	});
	
</script>
<div class="modal" id="modal"  role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" >
	<div class="modal-dialog" style="width:850px;">
		<div class="modal-content" >
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">상담내용</h4>
				<button type="button" class="close"  id="pop_close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
			</div>
			<div class="modal-body">
				<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-4" data-widget-editbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" data-widget-togglebutton="false">
					<div role="content">
						<div class="widget-body">	
							<input type="text" class="form-control" id="popEmpNm">
							<label for="note" class="col-sm-4 col-form-label">내용</label>
							<input type="text" class="form-control" id="note" placeholder="내용 입력">
							<label for="regId" class="col-sm-4 col-form-label">등록자명</label>
							<input type="text" class="form-control" id="regId" placeholder="등록자 입력" value="관리자">
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="btn_close" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="btn_save">저장</button>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>