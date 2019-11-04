package online.shixun.demo.elearning.dto;

public class BlogCategory extends BaseDto {
    private String description;

    private String name;
    
    //属于此种博客类型的博客的数量
    private int blogCount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public int getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}
    
    
}