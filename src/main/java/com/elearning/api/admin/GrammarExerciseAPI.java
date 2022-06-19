package com.elearning.api.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.elearning.entities.GrammarExercise;
import com.elearning.service.GrammarExerciseService;

@RestController
@RequestMapping("/api/admin/grammarexercise")
public class GrammarExerciseAPI {

	@Autowired
	private GrammarExerciseService grammarExerciseService;

	@GetMapping("/getall")
	public ResponseEntity<Object> findAllByPartAndLevel(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "6") int pagesize, @RequestParam(defaultValue = "") String part,
			@RequestParam(defaultValue = "") String level) {
		return ResponseEntity.ok(grammarExerciseService.findAllListGrammarExerciseByLevelAdmin(page, pagesize, level));
	}

	@GetMapping("/getinfor/{id}")
	public ResponseEntity<GrammarExercise> getInfor(@PathVariable long id) {
		Optional<GrammarExercise> bn = grammarExerciseService.findGrammarExerciseById(id);
		return bn.isPresent() ? new ResponseEntity<>(bn.get(), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@ModelAttribute GrammarExercise grammarExerciseInfor, HttpServletRequest request) {
		try {
			System.out.println(grammarExerciseInfor.toString());
			GrammarExercise grammarExercise = grammarExerciseService.save(grammarExerciseInfor, request);
			saveFileForgrammarExercise(grammarExercise, request);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (IllegalStateException | IOException e) {
			System.out.println("vao day");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		grammarExerciseService.deleteGrammarExercise(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// lưu ảnh của bài đọc vào thư mục
	private void saveFileForgrammarExercise(GrammarExercise objgrammarExercise, HttpServletRequest request)
			throws IllegalStateException, IOException {

		MultipartFile fileImage = objgrammarExercise.getImage();
		MultipartFile fileExcel = objgrammarExercise.getFileExcel();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path pathImage = Paths.get(rootDirectory + "/static/file/images/grammarexercise/grammarExerciseId="
				+ objgrammarExercise.getId() + ".png");
		Path pathExcel = Paths.get(rootDirectory + "/static/file/excel/grammarexercise/grammarExerciseId="
				+ objgrammarExercise.getId() + ".xlsx");
		fileImage.transferTo(new File(pathImage.toString()));
		fileExcel.transferTo(new File(pathExcel.toString()));
	}
}
