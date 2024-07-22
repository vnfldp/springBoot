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
		
		// 개발자등급 전체 체크
		$("#cbx_chkAll").click(function() {
			if($("#cbx_chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
			else $("input[name=chk]").prop("checked", false);
		});
		
		// 개발자등급 체크
		$("input[name=chk]").click(function() {
			var total = $("input[name=chk]").length;
			var checked = $("input[name=chk]:checked").length;
	
			if(total != checked) $("#cbx_chkAll").prop("checked", false);
			else $("#cbx_chkAll").prop("checked", true); 
		});
	});
</script>
    <div class="container mt-5">
        <div class="form-group row">
           <!-- <div class="col-sm-4">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="cbx_chkAll" value="">
                    <label class="form-check-label" for="cbx_chkAll">전체</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="express" name="chk">
                    <label class="form-check-label" for="express">특급</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="advanced" name="chk">
                    <label class="form-check-label" for="advanced">고급</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="intermediate" name="chk">
                    <label class="form-check-label" for="intermediate">중급</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="beginner" name="chk">
                    <label class="form-check-label" for="beginner">초급</label>
                </div>
            </div>-->
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
