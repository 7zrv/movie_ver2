function calTime(datetime) {
    let runtime = parseInt($('#runtime').val());
    let result = new Date(datetime);
    result.setMinutes(result.getMinutes() + runtime);
    let date = result.getFullYear() + '-' + ('0' + (result.getMonth() + 1)).slice(-2) + '-' + ('0' + result.getDate()).slice(-2);
    let time = ('0' + result.getHours()).slice(-2) + ':' + ('0' + result.getMinutes()).slice(-2) + ':' + ('0' + result.getSeconds()).slice(-2);
    let resultTime = date + 'T' + time;
    $('#endTime').val(resultTime);
}

function scheduleDelete(scheduleId){
    $.ajax({
        type: 'DELETE',
        url: `/api/schedule/deleteSchedule/${scheduleId}`,
        success: function (result) {
            console.log(result);
            alert(result.message);
            location.reload();
        },
        error: function (result) {
            if (result.message === "상영일정 삭제 실패") {
                alert(result.message)
            } else {
                alert("error" + result)
            }
        }
    });
}

function createSchedule(data) {
    $.ajax({
        type: 'POST',
        url: `/api/schedule/createSchedule`,
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

function modifySchedule(data) {
    $.ajax({
        type: 'PATCH',
        url: `/api/schedule/modifyScheduleInfo`,
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