function theaterDelete(theaterId){
    $.ajax({
        type: 'DELETE',
        url: `/api/theater/deleteTheater/${theaterId}`,
        success: function (result) {
            console.log(result);
            alert(result.message);
            location.reload();
        },
        error: function (result) {
            if (result.message == "삭제 실패") {
                alert(result.message + "\n" + result.data)
            } else {
                alert("error" + result)
            }
        }
    });
}

function createTheater(data) {
    $.ajax({
        type: 'POST',
        url: `/api/theater/createTheater`,
        headers : { "content-type": "application/json;charset=UTF-8"},
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);
            alert(result.message);
            history.back();
            if (result.message == "등록 실패") {
                alert("이미 리뷰를 작성하였습니다.")
            }
        },
        error: function (result) {
            if (result.message == "등록 실패") {
                alert(result.message + "\n" + result.data);
            }
            else{
                alert("error" + result);
            }
        }
    });
}

function modifyTheater(data, theaterId) {
    $.ajax({
        type: 'PATCH',
        url: `/api/theater/modifyTheater/${theaterId}`,
        headers : { "content-type": "application/json;charset=UTF-8"},
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);
            alert(result.message);
            history.back();
        },
        error: function (result) {
            if (result.message == "수정 실패") {
                alert(result.message + "\n" + result.data);
            }
            else{
                alert("error" + result);
            }
        }
    });
}

function getAllTheater(pageNum){
    $.ajax({
        type: 'GET',
        url: `/api/theater/getAllTheater?page=${pageNum-1}`,
        success: function (result) {
            let theaterHtml = ``;
            console.log(result);
            $(result.data.content).each(function(){
                theaterHtml += `<tr>
                                    <td>${this.id}</td>
                                    <td>${this.area}</td>
                                    <td>${this.address}</td>
                                    <td>${this.number}</td>
                                    <td>${this.halls}관</td>
                                    <!-- <td> -->
                                        <!-- 상영 중인 영화 목록을 반복하여 표시할 수 있습니다. -->
                                        <!-- <span th:each="movieId : " th:text=""></span> -->
                                    <!-- </td> -->
                                    <td><a href='/modify/theater/${this.id}' class="controlBtn" style="color:royalblue; border-color: royalblue;">수정</a>
                                        <a href='javascript:void(0);' onClick='theaterDelete(${this.id});' class="controlBtn" style="color:tomato; border-color: tomato">삭제</a></td>
                                </tr>`
            });
            $("#tableBody").html(theaterHtml);
            setPageNation(result.data.totalPages, pageNum, 5);
        },
        error: function (result) { // 실패시
            if (result.message == "조회 실패") {
                alert(result.message + "\n" + result.data);
            }
            else{
                alert("error" + result);
            }
        }
    });
}

function getTheaterInfo(theaterId){
    $.ajax({
        type: 'GET',
        url: `/api/theater/getTheaterInfo/${theaterId}`,
        success: function (result) {
            console.log(result);
            $("#area").val(result.data.area);
            $("#address").val(result.data.address);
            $("#number").val(result.data.number);
            //alert(result.message);
        },
        error: function (result) { // 실패시
            if (result.message == "조회 실패") {
                alert(result.message + "\n" + result.data)
            }
            else{
                alert("error" + result)
            }
        }
    });
}



