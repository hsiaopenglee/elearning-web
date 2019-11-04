package online.shixun.demo.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import online.shixun.demo.elearning.dto.BlogCategory;
import online.shixun.demo.elearning.dto.BlogWithBLOBs;
import online.shixun.demo.elearning.mapper.BlogCategoryMapper;
import online.shixun.demo.elearning.mapper.BlogMapper;

/**
 * 博客Service
 */
@Service
public class BlogService {

    private BlogMapper blogMapper;

    private BlogCategoryMapper categoryMapper;

    @Autowired
    public BlogService(BlogMapper blogMapper, BlogCategoryMapper categoryMapper) {
        this.blogMapper = blogMapper;
        this.categoryMapper = categoryMapper;
    }

	/**
	 * 获取首页展示的博客
	 *
	 * @return
	 */
	public List<BlogWithBLOBs> getHomeBlogs() {
		return blogMapper.getHomePageBlogs();
	}
	
    /**
     * 分类获取博客
     *
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @return
     */
    public Page<BlogWithBLOBs> getBlogByPage(int pageNo,int pageSize,long categoryId){
        PageHelper.startPage(pageNo,pageSize);
        return blogMapper.getBlogByCategory(categoryId);
    }

    /**
     * 获取各种博客类型名称以及数量
     *
     * @return
     */
    public List<BlogCategory> getBlogCountOfCategories(){
        return categoryMapper.getBlogCountOfCategories();
    }

    /**
     * 根据id获取博客
     *
     * @param id
     * @return
     */
    public BlogWithBLOBs getBlogById(long id){
        return blogMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取同种类型的其它博客
     *
     * @param blogCategoryId
     * @param blogId
     * @return
     */
    public List<BlogWithBLOBs> getRelatedBlogs(long blogCategoryId,long blogId){
        return blogMapper.getRelatedBlogs(blogCategoryId,blogId);
    }
}
