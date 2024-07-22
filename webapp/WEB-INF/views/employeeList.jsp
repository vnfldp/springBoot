<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" 
%><%@ include file="header.jsp"%>

<title>MANI TECH | 기본 정보</title>

    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
						<br/>
                        <div class="row">
                            <form name="frmSearch" action="/employee/list" method="post">
								<%@ include file="search.jsp"%>
                        	</form>
                        </div>
						<br/>
						<div class="col-sm-5">총 <strong>${totalCnt}<span id="rows_tot"> </span></strong>개 등록되어 있습니다.</div>
                        <div class="table-responsive">
                            <table class="table table-striped" style="width:1100px">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>사원명</th>
                                    <th>생년월일</th>
                                    <th>성별</th>
                                    <th>전화번호</th>
                                    <th>이메일</th>
                                    <th>주소</th>
                                    <th>학력1</th>
									<th>학력2</th>
									<th>학력3</th>
									<th>직위</th>
									<th>경력년수</th>
									<th>기술등급</th>
									<th>코사증빙여부</th>
									<th>정보처리여부</th>
									<th>비고내용</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="list" varStatus="status" >
                                 <tr>
                                     <td>${totalCnt - status.index}</td>
                                     <td>${list.EMPLOYEE_NAME}</td>
									 <td>${list.BIRTHDATE}</td>
									 <td>${list.GENDER}</td>
									 <td>${list.PHONE}</td>
									 <td>${list.EMAIL}</td>
									 <td>${list.ADDR}</td>
									 <td>${list.EDU1}</td>
									 <td>${list.EDU2}</td>
									 <td>${list.EDU3}</td>
									 <td>${list.POSITION_CODE}</td>
									 <td>${list.YEARS}</td>
									 <td>${list.GRADE}</td>
									 <td>${list.COSA_YN}</td>
									 <td>${list.GISA_YN}</td>
									 <td>${list.REMARKS}</td>
                                 </tr>
								</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- row -->
    </div><!-- wrapper -->    
	<script>
	$(document).ready(function() {
		$('#btnSearch').click(function() {
	   		fn_search_clicked();
		});
		
       	//검색어 엔터키 조회
       	$('#searchVal').keydown(function(e) {
       		if (e.keyCode == 13) {
       			e.preventDefault();
       			$('#btnSearch').trigger('click');
       			return false;
       		} else {
       			return true;
       		}
       	})
	});
    </script>
<%@ include file="footer.jsp"%>