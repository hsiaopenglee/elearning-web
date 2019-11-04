package online.shixun.demo.elearning.dto;

import java.math.BigDecimal;

import online.shixun.demo.elearning.util.ResourcePathUtil;

public class CourseChapter extends BaseDto {
	private String title;

	private String brief;

	private Long courseId;

	private BigDecimal duration;

	private Integer chapterIndex;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief == null ? null : brief.trim();
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public Integer getChapterIndex() {
		return chapterIndex;
	}

	public void setChapterIndex(Integer chapterIndex) {
		this.chapterIndex = chapterIndex;
	}

	public String getVideo() {
		return ResourcePathUtil.getCourseVideo(super.getId());
	}
}