/**
 * 
 */
$(document).ready(function () {
				
				$(window).on('pageshow', function(event) {
					if(event.originalEvent.persisted) {
						location.reload();
					}
				})
				
				$("#search-input").change(function () {
					let text = $(this).val();
					console.log(text);
					window.location.href="search?text=" + text;
				})
				

			})