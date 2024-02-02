function hallDelete(hallId){
    $.ajax({
        type: 'DELETE',
        url: `/api/hall/deleteHall/${hallId}`,
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

function createHall(data, theaterId) {
    $.ajax({
        type: 'POST',
        url: `/api/hall/createHall/${theaterId}`,
        headers : { "content-type": "application/json;charset=UTF-8"},
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);
            alert(result.message);
            //history.back();
            location.href = document.referrer;
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

function modifyHall(data, theaterId, hallId) {
    $.ajax({
        type: 'PATCH',
        url: `/api/hall/modifyHall/${theaterId}/${hallId}`,
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

function getHallInfo(hallId){
    $.ajax({
        type: 'GET',
        url: `/api/hall/getHallInfo/${hallId}`,
        success: function (result) {
            console.log(result);
            $("#name").attr('value', result.data.name);
            $("#seats").attr('value', result.data.seats);
            $("#floor").attr('value', result.data.floor);
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

function getHalls(theaterId){
    $.ajax({
        type: 'GET',
        url: `/api/hall/getHallsByTheater/${theaterId}`,
        success: function (result) {
            let totalSeat = 0;
            let hallHtml = ``;
            console.log(result);
            $(result.data).each(function(){
                totalSeat += this.seats;
                hallHtml += `<tr>
                                    <td>${this.name}</td>
                                    <td>${this.seats}석</td>
                                    <td>${this.floor}층</td>
                                    <td><a href='/modify/hall/${theaterId}/${this.id}' class="controlBtn" style="color:royalblue; border-color: royalblue;">수정</a>
                                        <a href='javascript:void(0);' onClick='hallDelete(${this.id});' class="controlBtn" style="color:tomato; border-color: tomato">삭제</a></td>
                                </tr>`
            });
            $("#tableBody").append(hallHtml);
            $("#totalHall").text(result.data.length);
            $("#totalSeat").text(totalSeat);
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

function getTheaterHalls(theaterId){
    $.ajax({
        type: 'GET',
        url: `/api/hall/getHallsByTheater/${theaterId}`,
        success: function (result) {
            let hallList = ``;
            console.log(result);
            $(result.data).each(function(){
                hallList +=
                    `<li>
                        <button type="button" onclick="$('#hallId').val(${this.id}); $('ul.hall-list li').removeClass(); $(this.parentNode).addClass('active')">
                            <span class="name">${this.name}</span>
                        </button>
                    </li>`
            });
            $(".hall-list").html(hallList);
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