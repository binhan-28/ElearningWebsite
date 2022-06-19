


$(document).ready(function() {



	//default. load all object baiGrammar
	window.onload = function() {
		loadAllGrammar(1);

	};




	function loadAllGrammar(pageinput) {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/api/testlogs/getbyuser" + "?page=" + pageinput,
			success: function(data) {

				console.log(data);
				var trHTML = "";
				var STT = 1;
				if (data.object != null) {
					$.each(data.object.content, function(i, objtestlog) {

						trHTML += '<tr>'
							+ '<td>' + STT + ' </td>'
							+ '<td>' + objtestlog.testName + '</td>'
							+ '<td>' + objtestlog.score + '</td>'
							+ '<td>' + objtestlog.time + '</td>'
							+ '<td>' + objtestlog.dateWork + '</td>'
							+ '</tr>';
						STT+=1;
					});
				}

				if (data.object.totalPages > 0) {
					$('.pagination').empty();
					for (var numberPage = 1; numberPage <= data.object.totalPages; numberPage++) {
						var li;
						if (numberPage == pageinput)
							li = '<a class="directpage active" id="direct.' + numberPage + '"> ' + numberPage + '</a>';
						else
							li = '<a class="directpage" id="direct.' + numberPage + '"> ' + numberPage + '</a>';
						$('.pagination').append(li);
					};


				};
				$('tbody').html(trHTML);

			}, error: function(e) {
				alert("error");
				console.log("ERROR: ", e);
			}
		});


	}


	$(document).on('click', '.directpage', function(event) {
		var directId = $(this).attr('id');
		var fields = directId.split('.');
		var page = fields[1];
		loadAllGrammar(page);
	});
	//add new baigrammar





});
