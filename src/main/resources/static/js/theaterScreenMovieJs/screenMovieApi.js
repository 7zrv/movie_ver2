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

function getAllScMovies(){
    $.ajax({
        type: 'GET',
        url: `/api/screenMovie/getAllScreenMovies`,
        success: function (result) {
            console.log(result);
            $(result.data).each(function(){
                $('.scMovie-list').append($(
                    `<li>
                        <button type="button" id="${this.id}" onclick="$('#movieId').val(${this.id}); $('#runtime').val(${this.runtime});">
                            <span class="title">${this.title}</span>
                        </button>
                    </li>`
                ));
            });
        },
        error: function (result) { // 실패시
            if (result.message === "조회 실패") {
                alert(result.message + "\n" + result.data)
            }
            else{
                alert("error" + result)
            }
        }
    });
}

function getScreenMovieList(theaterId) {
    let list = [];
    $.ajax({
        type: 'GET',
        url: `/api/screenMovie/getScreenMovies/${theaterId}`,
        success: function (result) { // 성공적으로 수행 시 response를 data라는 인자로 받는다.
            console.log(result);
            //alert(result.message);
            if(typeof result.data == "string") {
                $('#tableBody').append($(`<tr>${result.data}</tr>`));
                $("#totalScreen").text(0);
            }
            else
                $("#totalScreen").text(result.data.length);
            $(result.data).each(function(){
                list.push(this.id);
                $('#tableBody').append($(
                    `<tr>
                        <td>
                            <input type="checkbox" class="delMovies" name="delMovies" value=${this.id}>
                        </td>
                        <td>${this.title}</td>
                        <td>${this.openingDate}</td>
                        <td>${this.id}</td>
                    </tr>`));
            });
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
    return list;
}

function getTheaterList(movieId){
    let list = [];
    $.ajax({
        type: 'GET',
        url: `/api/screenMovie/getTheaters/${movieId}`,
        async: false,//해당 함수가 끝난 후 순차적으로 실행
        success: function (result) {
            console.log(result);
            $(result.data).each(function(){
                list.push(this.id);
            });
        },
        error: function (result) { // 실패시
            if (result.message === "조회 실패") {
                alert(result.message + "\n" + result.data)
            }
            else{
                alert("error" + result)
            }
        }
    });
    $('.localTheater-list li button').each(function () {
        if(list.includes(parseInt(this.id))) {
            $(this).attr("disabled", false);
        }
        else{
            $(this).attr("disabled", true);
        }
    });
}

function getScMovieList(theaterId) {
    let list = [];
    $.ajax({
        type: 'GET',
        url: `/api/screenMovie/getScreenMovies/${theaterId}`,
        async: false,//해당 함수가 끝난 후 순차적으로 실행
        success: function (result) {
            console.log(result);
            $(result.data).each(function(){
                list.push(this.id);
            });
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
    $('.scMovie-list li button').each(function () {
        if(list.includes(parseInt(this.id))) {
            $(this).attr("disabled", false);
        }
        else{
            $(this).attr("disabled", true);
        }
    });
}

function getAllMovie(pageNum, list){
    $.ajax({
        type: 'GET',
        url: `/api/movie/getAllMovieInfo?pageNum=${pageNum-1}`,
        success: function (result) {
            let movieHtml = ``;
            let disabled = "";
            console.log(result);
            $(result.data.content).each(function(){
                if(list.includes(this.id))
                    disabled = "disabled";
                else
                    disabled = "";
                movieHtml += `<tr>
                                    <td>
                                        <input type="checkbox" class="addMovies" name="addMovies" value=${this.id} ${disabled}>
                                    </td>
                                    <td>${this.title}</td>
                                    <td>${this.openingDate}</td>
                                    <td>${this.country}</td>
                                    <td>${this.runtime}</td>
                                    <td>${this.id}</td>
                                </tr>`
            });
            $("#movieTbody").html(movieHtml);
            $("#totalMovie").text(result.data.totalElements);
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