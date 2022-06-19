package com.elearning.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "grammar_exercise")
public class GrammarExercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int level;

	@Transient
	@JsonIgnore
	private MultipartFile image;
	@JsonIgnore
	@Transient
	private MultipartFile fileExcel;

	@Column(columnDefinition = "TEXT")
	private String script;

	@OneToMany(mappedBy = "grammarExercise", cascade = CascadeType.ALL)
	@JsonBackReference
	List<GrammarQuestion> grammarQuestions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public MultipartFile getFileExcel() {
		return fileExcel;
	}

	public void setFileExcel(MultipartFile fileExcel) {
		this.fileExcel = fileExcel;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public List<GrammarQuestion> getGrammarQuestions() {
		return grammarQuestions;
	}

	public void setGrammarQuestions(List<GrammarQuestion> grammarQuestions) {
		this.grammarQuestions = grammarQuestions;
	}

	@Override
	public String toString() {
		return "ReadingExercise name=" + name;
	}

}
