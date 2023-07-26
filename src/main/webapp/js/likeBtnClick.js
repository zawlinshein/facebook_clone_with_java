/**
 * 
 */
$(document).ready(function () {
$(".likeUnlikeBtn").click(function() {
				
	let id = $(this).attr("id");
	$(".like" + id).toggleClass("likeColor");
	console.log(id);
	let count = parseInt($("#like" + id).text());
	console.log(count);
	$.ajax({
			url:"likeunlike",
			method:"POST",
			data: {
				id: id
			},
			success:function() {
										
				if($(".like" + id).hasClass("likeColor")) {
					count++;
				} else {
					count--;
				}
				$("#like" + id).text(count);
			}
						
		})		
	})
})