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
            //history.back();
            location.href = document.referrer;
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
            $(result.data).each(function(){
                $('#allReview').append($(
                `<li class="reviewList">
                    <img src="${this.posterImgPath}" alt="Poster" class="movie-poster" onerror="this.onerror=null; this.src='https://i.imgur.com/sC1kNP1.png';"/>
                    <div class="review-info">
                        <div>
                            <div class="title-control">
                                <span class='movie-title'>${this.title}</span>
        
                                <!-- 현재 로그인한 유저의 ID와 review.memberId를 비교하여 조건에 따라 버튼 표시 -->
                                <div class="control-button">
                                    <a href='/modify/review/${this.id}' class='btn-danger'>수정</a>
                                    <a href='javascript:void(0);' onClick='reviewDelete(${this.id});' class='btn-danger'>삭제</a>
                                </div>
                            </div>
                            <div class="rating-box">
                                <div class='rating-star'>
                                    ★★★★★
                                    <span style="width: ${this.rating * 10}%">★★★★★</span>
                                </div>
                                <span class="rating-value">${this.rating}</span>
                            </div>
                            <p class='review-content'>${this.content}</p>
                        </div>
                        <span class='review-date'>${dateFormat(this.mod_date)}</span>
                    </div>
                </li>`));
            });
            $("#totalReview").text(result.data.length);
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

function dateFormat(date){
    const d = new Date(date);
    let result = d.getFullYear() + '.' + ("0"+(d.getMonth()+1).toString()).slice(-2) + '.' + ("0"+(d.getDate()).toString()).slice(-2) + ' ';
    if(d.getHours() < 10)
        result += '0';
    result += d.getHours() + ':';
    if(d.getMinutes() < 10)
        result += '0';
    result += d.getMinutes();
    return result;
}

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
                                    <span class='review-author'>사용자${this.member_id}</span>
                                    <span class='review-date'>${dateFormat(this.mod_date)}</span>
                               </li>`;
            });
            if(result.data === "해당 영화에 등록된 리뷰가 아직 존재하지 않습니다."){
                reviewHtml += `<p class="review-none">${result.data}</p>`
            }
            else{
                setPageNation(result.data.totalPages, pageNum, 10);
            }
            $("#allReview").html(reviewHtml);
            $("#total").text(`(${result.data.totalElements}명)`);
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
