package online.shixun.demo.elearning.dto;

import java.util.List;

import online.shixun.demo.elearning.util.ResourcePathUtil;

public class Comment extends BaseDto {
    private String content;

    private Long referenceId;

    private Long replyCommentId;

    private Long replyUserId;

    private Long studentId;

    private String typeCode;
    
    //评论对象的标题
    private String replySubjectTitle;
    
    //子评论列表
    private List<Comment> subCommentList;
    
    //评论学生
    private Student student;
    
    //回复评论管理员的名称
    private String replyUserName;
    
    //回复评论管理员图片后缀
    private String replyUserImageSuffix;
    
    //回复评论管理员的图片
    public String getReplyUserImage() {
    	return ResourcePathUtil.getUserImage(replyUserId, replyUserImageSuffix);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Long getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(Long replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

	public String getReplySubjectTitle() {
		return replySubjectTitle;
	}

	public void setReplySubjectTitle(String replySubjectTitle) {
		this.replySubjectTitle = replySubjectTitle;
	}

	public List<Comment> getSubCommentList() {
		return subCommentList;
	}

	public void setSubCommentList(List<Comment> subCommentList) {
		this.subCommentList = subCommentList;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public String getReplyUserImageSuffix() {
		return replyUserImageSuffix;
	}

	public void setReplyUserImageSuffix(String replyUserImageSuffix) {
		this.replyUserImageSuffix = replyUserImageSuffix;
	}
    
    
}