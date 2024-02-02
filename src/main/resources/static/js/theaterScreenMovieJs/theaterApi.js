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
            //history.back();
            location.href = document.referrer;
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
                                    <td><a href="/halls/${this.id}" style="color: #73685d;">${this.halls}관</a>
                                        <a href='/create/hall/${this.id}' class="addBtn">추가</a></td>
                                    <td><a href="/screenMovies/${this.id}" style="color: #73685d;">${this.screenMovies}편</a></td>
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

function getAllTheaterArea(){
    $.ajax({
        type: 'GET',
        url: `/api/theater/getAllTheaterArea`,
        success: function (result) {
            console.log(result);
            $(result.data).each(function(i, obj){
                let areaHtml =
                    `<li>
                        <button type="button" onclick="$('ul.localTheater-list li').removeClass(); $(this.parentNode).addClass('active'); getTheaterHalls(${obj.id});">
                            <span class="area">${obj.area}</span>
                        </button>
                    </li>`
                $("button[name='local']").each(function (i, btn){
                    if(obj.local === $(btn).text()){
                        //console.log($(btn).text());
                        $(btn).parent().children(".localTheater-container").children().append($(areaHtml));
                    }
                });
                $('#localTheater-list0').append($(areaHtml));
            });
            $('#totalTheaterBtn').append(`(${result.data.length})`);
        },
        error: function (result) {
            if (result.message === "조회 실패") {
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
            $("#area").attr('value', result.data.area);
            $("#address").attr('value', result.data.address);
            $("#number").attr('value', result.data.number);
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

function getLocalTheaters(local, pageNum) {
    $.ajax({
        type: 'GET',
        url: `/api/theater/getLocalTheaters?page=${pageNum-1}`,
        headers : { "content-type": "application/json;charset=UTF-8"},
        data : {
            local : local
        },
        dataType: "json",
        success: function (result) {
            let theaterHtml = ``;
            console.log(result);
            $(result.data.content).each(function(){
                theaterHtml += `<tr>
                                    <td>${this.id}</td>
                                    <td>${this.area}</td>
                                    <td>${this.address}</td>
                                    <td>${this.number}</td>
                                    <td><a href="/halls/${this.id}" style="color: #73685d;">${this.halls}관</a>
                                        <a href='/create/hall/${this.id}' class="addBtn">추가</a></td>
                                    <td><a href="/screenMovies/${this.id}" style="color: #73685d;">${this.screenMovies}편</a></td>
                                    <td><a href='/modify/theater/${this.id}' class="controlBtn" style="color:royalblue; border-color: royalblue;">수정</a>
                                        <a href='javascript:void(0);' onClick='theaterDelete(${this.id});' class="controlBtn" style="color:tomato; border-color: tomato">삭제</a></td>
                                </tr>`
            });
            $("#tableBody").html(theaterHtml);
            setPageNation(result.data.totalPages, pageNum, 5);
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


