function movieDelete(movieId){
    $.ajax({
        type: 'DELETE',
        url: `/api/movie/deleteMovieInfo/${movieId}`,
        success: function (result) {
            console.log(result);
            alert(result.message);
            //location.reload();
        },
        error: function (result) {
            if (result.message === "영화 삭제 실패") {
                alert(result.message + "\n" + result.data)
            } else {
                alert("error" + result)
            }
        }
    });
}

function updateMovie(data, movieId) {
    const formData = new FormData(data);
    let json = {};
    let set = [];

    formData.forEach((value, key) => {
        if(key.includes('genre')){
            if(value.trim() !== "")
                set.push(value);
            json[key] = set;
        }
        else
            json[key] = value;
    });

    $.ajax({
        type: 'PATCH',
        url: `/api/movie/updateMovieInfo/${movieId}`,
        headers : { "content-type": "application/json;charset=UTF-8"},
        data: JSON.stringify(json),
        success: function (result) {
            console.log(result);
            alert(result.message);
            //history.back();
            //location.href = document.referrer;
        },
        error: function (result) {
            if (result.message === "영화 업데이트 실패") {
                alert(result.message + "\n" + result.data);
            }
            else{
                alert("error" + result);
            }
        }
    });
}

function getUpdateMovieInfo(movieId){
    $.ajax({
        type: 'GET',
        url: `/api/movie/getDetailMovieInfo/${movieId}`,
        success: function (result) {
            console.log(result);
            $("#title").attr('value', result.data.title);
            $("#director").attr('value', result.data.director);
            $("#cast").attr('value', result.data.cast);
            $("#country").attr('value', result.data.country);
            $("#runtime").attr('value', result.data.runtime);
            $("#openingDate").attr('value', result.data.openingDate);
            $("#content").text(result.data.content);
            $('#age').val(result.data.age).prop("selected",true);
            $("#genre").attr('value', result.data.genre[0]);
            for(let i=1; i<result.data.genre.length; i++){
                $("#genreList").append($(`<li class="genre-row" style="margin-bottom:5px;">
                                        <input type="text" id="genre${i}" name="genre" value="${result.data.genre[i]}">
                                        <button name="delField" onclick="this.parentNode.parentNode.removeChild(this.parentNode);">-</button>
                                      </li>`));
            }
        },
        error: function (result) { // 실패시
            if (result.message === "영화 상세정보 조회 실패") {
                alert(result.message + "\n" + result.data);
            }
            else{
                alert("error" + result);
            }
        }
    });
}

function createMovie(data, files) {
    const formData = new FormData(data);
    let newFormData = new FormData();
    let json = {};
    let set = [];

    formData.forEach((value, key) => {
        if(key.includes('genre')){
            if(value.trim() !== "")
                set.push(value);
            json[key] = set;
        }
        else
            json[key] = value;
    });

    newFormData.append("requestDto", new Blob([JSON.stringify(json)], {type: "application/json;charset=UTF-8"}));
    newFormData.append("posterImage", document.getElementById('imgForm')[0].files[0]);
    files.forEach((file) => {
        newFormData.append("previewImages", file);
    });

    $.ajax({
        type: 'POST',
        url: `/api/movie/uploadMovieInfo`,
        data: newFormData,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        dataType: "json",
        cache: false,
        success: function (result) {
            console.log(result);
            alert(result.message);
            location.reload();
        },
        error: function (result) {
            if (result.message === "예기치 않은 오류 발생") {
                alert(result.message);
            }
            else{
                alert("error" + result);
                console.log(result);
            }
        }
    });
}