


$(document).ready(function() {



	//default. load all object baiGrammar
	window.onload = function() {
		loadAllGrammar(1);

	};




	function loadAllGrammar(pageinput) {

		var namekey = "";
		var datainput = { keyWord: namekey, page: pageinput };
		console.log(datainput);
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/api/admin/grammar/getlist",
			data: JSON.stringify(datainput),
			success: function(data) {
				console.log(data);
				var trHTML = "";
				var index = 0;
				$.each(data.object.content, function(i, objgrammar) {
					if (index < 7) {
						indexval=index+1;
						var idname = "#post" + indexval;

						$(idname).text(objgrammar.grammarName);
						$(idname).attr("href", "/detailGram?idGram="+objgrammar.grammarId)
						index++;
					}

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
