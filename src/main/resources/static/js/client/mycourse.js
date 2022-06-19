


$(document).ready(function() {



	//default. load all object 
	window.onload = function() {
		loadAllCourse(1);

	};




	function loadAllCourse(pageinput) {

		console.log(datainput);
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "http://localhost:8080/api/client/user/course/getlist",
			success: function(data) {
				console.log(data);
				var trHTML = "";
				
				$.each(data.object.content, function(i, objCourse) {
					

				});


				//$('#tableExam').append(trHTML);
				$('tbody').html(trHTML);

			}, error: function(e) {
				alert("error");
				console.log("ERROR: ", e);
			}
		});


	}


});
