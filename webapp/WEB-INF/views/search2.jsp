<%@ page contentType="text/html; charset=UTF-8" %>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 추가한 CSS */
        .form-check-inline {
            margin-right: 10px; /* 체크박스 간격 조정 */
        }
		.form-group {
		    margin-bottom: 0.2rem;
		}
    </style>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
    <div class="container mt-5">
        <div class="form-group row">
				<div class="col-sm-2">
				    <input type="text" class="form-control" id="employeeName" placeholder="개발자명 입력">
				</div>
				<div class="col-sm-2">
				    <input type="text" class="form-control" id="addr" placeholder="주소 입력">
				</div>
				<div class="col-sm-2">
				    <input type="text" class="form-control" id="grade" placeholder="등급 입력">
				</div>
				<div class="col-sm-2">
				    <input type="text" class="form-control" id="projectName" placeholder="프로젝트명 입력">
				</div>
				<div class="col-sm-2">
				    <input type="text" class="form-control" id="dev" placeholder="개발환경 및 언어 입력">
				</div>
				<div class="col-sm-2">
					<button id="btnSel" onclick="getData();return false;" class="btn btn-primary">검색</button>
					<button id="btnReset" onclick="fn_init();return false;" class="btn btn-info">초기화</button>
				</div>
		</div>
		<div class="form-group row">
        </div>
    </div>
