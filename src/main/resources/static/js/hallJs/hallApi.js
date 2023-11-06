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
            history.back();
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