package online.shixun.demo.elearning.dto;

import java.util.Date;
import online.shixun.demo.elearning.util.ResourcePathUtil;

/**
 * 所有Dto类的父类，包含一些通用的dto字段
 */
public class BaseDto {
    // dto ID
    private Long id;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取各种类型实体的图片地址
	 */
	public String getImage() {
		if(this instanceof Course) {
			return ResourcePathUtil.getCourseImage(id, ((Course) this).getImageSuffix());					
		}else if (this instanceof Blog) {
			return ResourcePathUtil.getBlogImage(id, ((Blog) this).getImageSuffix());
		}else if (this instanceof Slide) {
	        return ResourcePathUtil.getSlideImage(id, ((Slide)this).getImageSuffix());
	    } else if (this instanceof Student) {
	        Student student = (Student) this;
	        String imageSuffix = ((Student)this).getImageSuffix();
	        if (imageSuffix == null) {
	            // 如果用户暂未上传图片，使用默认图片
	            return ResourcePathUtil.getStudentImage(1, ".jpg");
	        }
	        return ResourcePathUtil.getStudentImage(student.getId(), imageSuffix);
	    } else if (this instanceof Teacher) {
	        return ResourcePathUtil.getTeacherImage(id, ((Teacher)this).getImageSuffix());
	    } else {
	        return "";
	    }
	}
}
