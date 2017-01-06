package sourceGallery.model.pojo;

import java.util.Date;

public class StudentSourcePojo {
	int num;
	int homeworkNum;
	String classes;
	String id;
	String name;
	String source;
	Date datecreated;
	int studentLevel;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getHomeworkNum() {
		return homeworkNum;
	}
	public void setHomeworkNum(int homeworkNum) {
		this.homeworkNum = homeworkNum;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}
	public int getStudentLevel() {
		return studentLevel;
	}
	public void setStudentLevel(int studentLevel) {
		this.studentLevel = studentLevel;
	}
	
}
