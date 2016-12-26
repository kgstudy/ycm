package homework.model.pojo;

import java.util.Date;

import org.springframework.stereotype.Component;


public class HomeworkPojo {
	int num;
	String title;
	String writer;
	String content;
	Date writedate;	
	int click;
	int adminLevel;
	int studentLevel;
	String correctRate;
	String answer;
	String source;
	String output;
	String input;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}
	public int getStudentLevel() {
		return studentLevel;
	}
	public void setStudentLevel(int studentLevel) {
		this.studentLevel = studentLevel;
	}
	public String getCorrectRate() {
		return correctRate;
	}
	public void setCorrectRate(String correctRate) {
		this.correctRate = correctRate;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
	
}
