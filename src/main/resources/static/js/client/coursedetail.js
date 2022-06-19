$(document).ready(function() {

	// xác nhận thêm tài khoản
	$(document).on('click', '#btnRegisterCourse', function(event) {
		event.preventDefault();
		ajaxPostTaiKhoan();
	});

	function ajaxPostTaiKhoan() {
		let searchParams = new URLSearchParams(window.location.search);
		currentCourseId = searchParams.get('courseId');
		var data = { courseId: currentCourseId };
		console.log(data);

		// do post
		$.ajax({
			async: false,
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/api/user/client/course/add",
			enctype: 'multipart/form-data',
			data: JSON.stringify({ "courseId": currentCourseId }),
			success: function(response) {
				$('#nopBaiModal').modal('show');
				location.reload();
			},
			error: function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
	}




	(function($) {
		$.fn.serializeFormJSON = function() {

			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
	})(jQuery);

	// remove element by class name
	function removeElementsByClass(className) {
		var elements = document.getElementsByClassName(className);
		while (elements.length > 0) {
			elements[0].parentNode.removeChild(elements[0]);
		}
	}
});