function reviewDelete(reviewId){
    $.ajax({
        type: 'DELETE',
        url: `/api/review/deleteReview/${reviewId}`,
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

function createReview(data, memberId, movieId) {
    $.ajax({
        type: 'POST',
        url: `/api/review/createReview/${memberId}/${movieId}`,
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

function modifyReview(data, memberId, reviewId) {
    $.ajax({
        type: 'PATCH',
        url: `/api/review/modifyReview/${memberId}/${reviewId}`,
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

function getModifyReview(memberId, reviewId){
    $.ajax({
        type: 'GET',
        url: `/api/review/modifyReview/${memberId}/${reviewId}`,
        success: function (result) {
            console.log(result);
            $("#rating").val(result.data.rating);
            $("#content").val(result.data.content);
            alert(result.message);

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

function getMyReviewList(memberId) {
    $.ajax({
        type: 'GET',
        url: `/api/review/getMyReview/${memberId}`,
        success: function (result) {
            console.log(result);
            alert(result.message);
            $(result.data).each(function(){
                $('#allReview').append($(
                `<li class="reviewList">
                    <div class="rating-control">
                        <div class="rating-box">
                            <div class='rating-star'>
                                ★★★★★
                                <span style="width: ${this.rating * 10}%">★★★★★</span>
                            </div>
                            <span class="rating-value">${this.rating}</span>
                        </div>

                        <!-- 현재 로그인한 유저의 ID와 review.memberId를 비교하여 조건에 따라 버튼 표시 -->
                        <div class="control-button">
                            <a href='/modify/review/${this.id}' class='btn-danger'>수정</a>
                            <a href='javascript:void(0);' onClick='reviewDelete(${this.id});' class='btn-danger'>삭제</a>
                        </div>
                    </div>
                    <p class='review-content'>${this.content}</p>
                    <p class='review-author'>${this.title}</p>
                </li>`));
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
/*
function getMovieReviewList(movieId) {
    $.ajax({
        type: 'GET',
        url: `/api/review/getMovieReview/${movieId}`,
        success: function (result) { // 성공적으로 수행 시 response를 data라는 인자로 받는다.
            console.log(result);
            alert(result.message);
            $(result.data).each(function(){
                $('#allReview').append($(
                `<li class="reviewList">
                    <div class="rating-control">
                        <div class="rating-box">
                            <div class='rating-star'>
                                ★★★★★
                                <span style="width: ${this.rating * 10}%">★★★★★</span>
                            </div>
                            <span class="rating-value">${this.rating}</span>
                        </div>

                        <!-- 현재 로그인한 유저의 ID와 review.memberId를 비교하여 조건에 따라 버튼 표시 -->
                        <div class="control-button">
                            <a href='/modify/review/${this.id}' class='btn-danger'>수정</a>
                            <a href='javascript:void(0);' onClick='reviewDelete(${this.id});' class='btn-danger'>삭제</a>
                        </div>
                    </div>
                    <p class='review-content'>${this.content}</p>
                    <p class='review-author'>사용자${this.member_id}</p>
                </li>`));
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
*/
function getMovieReviewListWithPage(movieId, pageNum) {
    $.ajax({
        type: 'GET',
        url: `/api/review/getMovieReviewWithPage/${movieId}?page=${pageNum-1}`,
        success: function (result) { // 성공적으로 수행 시 response를 data라는 인자로 받는다.
            let reviewHtml = ``;
            console.log(result);
            $(result.data.content).each(function(){
                reviewHtml += `<li class="reviewList">
                                    <div class="rating-control">
                                        <div class="rating-box">
                                            <div class='rating-star'>
                                                ★★★★★
                                                <span style="width: ${this.rating * 10}%">★★★★★</span>
                                            </div>
                                            <span class="rating-value">${this.rating}</span>
                                        </div>
                
                                        <!-- 현재 로그인한 유저의 ID와 review.memberId를 비교하여 조건에 따라 버튼 표시 -->
                                        <div class="control-button">
                                            <a href='/modify/review/${this.id}' class='btn-danger'>수정</a>
                                            <a href='javascript:void(0);' onClick='reviewDelete(${this.id});' class='btn-danger'>삭제</a>
                                        </div>
                                    </div>
                                    <p class='review-content'>${this.content}</p>
                                    <p class='review-author'>사용자${this.member_id}</p>
                               </li>`;
            });
            $("#allReview").html(reviewHtml);
            setPageNation(result.data.totalPages, pageNum, 10);
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