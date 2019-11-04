package online.shixun.demo.elearning.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LearningProgress extends BaseDto {
    private Long courseId;

    private Long chapterId;

    private Long studentId;
    
    //课程
    private Course course;
    
    //章节标题
    private String chapterTitle;
    //课程进度(章节序号/课程章节数)
    private double progress;

    public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	//get进度的时候，转成百数整数的形式
	public int getProgress() {
		BigDecimal bigDecimal =new BigDecimal(progress);
		//四舍五入
		double precent = bigDecimal.setScale(2,RoundingMode.HALF_UP).doubleValue();
		return (int)(precent*100);
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}