/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

// (1) 스토리 로드하기
let page = 0;
function storyLoad() {
	$.ajax({
		url:`/api/image?page=${page}`,
		dataType:"json"
	}).done(res=>{
		console.log(res);
		res.data.content.forEach((image)=>{
			let storyItem = getStoryItem(image);
			$("#storyList").append(storyItem);
		})
	}).fail(error=>{
		console.log(error);
	});

}
storyLoad();
function getStoryItem(image) {
	let item = `<div class="story-list__item">
\t<div class="sl__item__header">
\t\t<div>
\t\t\t<img class="profile-image" src="/upload/${image.user.profileImageUrl}"
\t\t\t\t onerror="this.src='/images/person.jpeg'" />
\t\t</div>
\t\t<div>${image.user.username}</div>
\t</div>

\t<div class="sl__item__img">
\t\t<img src="/upload/${image.postImageUrl}" />
\t</div>

\t<div class="sl__item__contents">
\t\t<div class="sl__item__contents__icon">

\t\t\t<button>
\t\t\t\t<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>
\t\t\t</button>
\t\t</div>

\t\t<span class="like"><b id="storyLikeCount-1">3 </b>likes</span>

\t\t<div class="sl__item__contents__content">
\t\t\t<p>${image.caption}</p>
\t\t</div>

\t\t<div id="storyCommentList-1">

\t\t\t<div class="sl__item__contents__comment" id="storyCommentItem-1"">
\t\t\t<p>
\t\t\t\t<b>Lovely :</b> 부럽습니다.
\t\t\t</p>

\t\t\t<button>
\t\t\t\t<i class="fas fa-times"></i>
\t\t\t</button>

\t\t</div>

\t</div>

\t<div class="sl__item__input">
\t\t<input type="text" placeholder="댓글 달기..." id="storyCommentInput-1" />
\t\t<button type="button" onClick="addComment()">게시</button>
\t</div>

</div>
</div>`;

	return item;

}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
	// console.log("윈도우 스크롤탑",$(window).scrollTop());
	// console.log("문서의 높이",$(document).height());
	// console.log("윈도우 높이",$(window).height());

	let checkNum = $(window).scrollTop() - ($(document).height()-$(window).height());
	console.log(checkNum);

	if (checkNum< 1 && checkNum > -1){
		page++;
		storyLoad();
	}
});


// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	if (likeIcon.hasClass("far")) {
		likeIcon.addClass("fas");
		likeIcon.addClass("active");
		likeIcon.removeClass("far");
	} else {
		likeIcon.removeClass("fas");
		likeIcon.removeClass("active");
		likeIcon.addClass("far");
	}
}

// (4) 댓글쓰기
function addComment() {

	let commentInput = $("#storyCommentInput-1");
	let commentList = $("#storyCommentList-1");

	let data = {
		content: commentInput.val()
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-2""> 
			    <p>
			      <b>GilDong :</b>
			      댓글 샘플입니다.
			    </p>
			    <button><i class="fas fa-times"></i></button>
			  </div>
	`;
	commentList.prepend(content);
	commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment() {

}







