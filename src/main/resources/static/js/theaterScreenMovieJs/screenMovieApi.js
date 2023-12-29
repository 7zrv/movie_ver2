function updateScMovie(data, theaterId) {
    $.ajax({
        type: 'POST',
        url: `/api/screenMovie/updateScreenMovie/${theaterId}`,
        headers : { "content-type": "application/json;charset=UTF-8"},
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);
            alert(result.message);
            location.reload();
        },
        error: function (result) {
            if (result.message == "추가 실패") {
                alert(result.message + "\n" + result.data);
            }
            else{
                alert("error" + result);
            }
        }
    });
}

function scMovieDelete(data, theaterId){
    $.ajax({
        type: 'DELETE',
        url: `/api/screenMovie/deleteScreenMovie/${theaterId}`,
        headers : { "content-type": "application/json;charset=UTF-8"},
        data: JSON.stringify(data),
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

function getScreenMovieList(theaterId) {
    $.ajax({
        type: 'GET',
        url: `/api/screenMovie/getScreenMovies/${theaterId}`,
        success: function (result) { // 성공적으로 수행 시 response를 data라는 인자로 받는다.
            console.log(result);
            //alert(result.message);
            $(result.data).each(function(){
                $('#tableBody').append($(
                    `<tr name="movieBox">
                    <td>
                        <input type="checkbox" id="delMovies" name="delMovies" value=${this.id}>
                    </td>
                    <td>${this.title}</td>
                    <td>${this.openingDate}</td>
                    <td>${this.id}</td>
                </tr>`));
            });
            $("#totalScreen").text(result.data.length);
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

function getAllMovie(pageNum){
    $.ajax({
        type: 'GET',
        url: `/api/movie/getAllMovieInfo?page=${pageNum-1}`,
        success: function (result) {
            let movieHtml = ``;
            console.log(result);
            $(result.data).each(function(){
                movieHtml += `<tr>
                                    <td>
                                        <input type="checkbox" id="addMovies" name="addMovies" value=${this.id}>
                                    </td>
                                    <td>${this.title}</td>
                                    <td>${this.openingDate}</td>
                                    <td>${this.country}</td>
                                    <td>${this.runtime}</td>
                                    <td>${this.id}</td>
                                </tr>`
            });
            $("#movieTbody").html(movieHtml);
            $("#totalMovie").text(result.data.length);
            //setPageNation(result.data.totalPages, pageNum, 5);
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