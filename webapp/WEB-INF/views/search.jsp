<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>검색 화면</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 추가한 CSS */
        .form-check-inline {
            margin-right: 10px; /* 체크박스 간격 조정 */
        }
    </style>
</head>

<body>
    <div class="container mt-5">
        <form>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">개발자 등급</label>
                <div class="col-sm-9">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="junior" value="junior">
                        <label class="form-check-label" for="junior">전체</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="mid-level" value="mid-level">
                        <label class="form-check-label" for="mid-level">특급</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="senior" value="senior">
                        <label class="form-check-label" for="senior">고급</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="intermediate" value="intermediate">
                        <label class="form-check-label" for="intermediate">중급</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="beginner" value="beginner">
                        <label class="form-check-label" for="beginner">초급</label>
                    </div>
                </div>
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-primary">검색</button>
                </div>
            </div>
            <div class="form-group row">
                <label for="developerName" class="col-sm-2 col-form-label">개발자명</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="" placeholder="개발자명 입력">
                </div>
            </div>
            <div class="form-group row">
                <label for="searchTerm" class="col-sm-2 col-form-label">개발환경언어</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="" placeholder="검색어 입력">
                </div>
            </div>
            <div class="form-group row">
                <label for="searchTerm" class="col-sm-2 col-form-label">프로젝트</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="" placeholder="검색어 입력">
                </div>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS (선택 사항: 필요시 사용) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>