package online.shixun.demo.elearning.dto;

import java.util.Date;
import java.util.List;

public class Course extends BaseDto {
    private String brief;

    private String description;

    private Double duration;

    private Integer classSize;

    private Long lecturerId;

    private String title;

    private Date releaseDate;

    private Long categoryId;

    private Integer currentEnrollNo;

    private String imageSuffix;
    
  //课程关联的讲师
    private Teacher teacher;
    
    //课程关联的章节列表
    private List<CourseChapter> courseChapterList;
    
    //课程所属的课程类型
    private CourseCategory courseCategory;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getClassSize() {
        return classSize;
    }

    public void setClassSize(Integer classSize) {
        this.classSize = classSize;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCurrentEnrollNo() {
        return currentEnrollNo;
    }

    public void setCurrentEnrollNo(Integer currentEnrollNo) {
        this.currentEnrollNo = currentEnrollNo;
    }

    public String getImageSuffix() {
        return imageSuffix;
    }

    public void setImageSuffix(String imageSuffix) {
        this.imageSuffix = imageSuffix == null ? null : imageSuffix.trim();
    }

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<CourseChapter> getCourseChapterList() {
		return courseChapterList;
	}

	public void setCourseChapterList(List<CourseChapter> courseChapterList) {
		this.courseChapterList = courseChapterList;
	}

	public CourseCategory getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(CourseCategory courseCategory) {
		this.courseCategory = courseCategory;
	}
    
}