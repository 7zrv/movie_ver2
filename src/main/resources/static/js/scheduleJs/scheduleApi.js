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