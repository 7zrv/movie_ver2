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
                    <td>${this.id}</td>
                    <td>${this.title}</td>
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
}