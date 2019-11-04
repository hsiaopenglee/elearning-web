package online.shixun.demo.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;

import online.shixun.demo.elearning.dto.BlogCategory;
import online.shixun.demo.elearning.dto.BlogWithBLOBs;
import online.shixun.demo.elearning.dto.Comment;
import online.shixun.demo.elearning.service.BlogService;
import online.shixun.demo.elearning.service.CommentService;
import online.shixun.demo.elearning.util.DictionaryConstant;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CommentService commentService;
	
	/**
	 * 博客首页，默认获取第1页博客数据
	 * 
	 * @param model
	 * @param categoryId 博客类别 -1:所有类别
	 */
	@GetMapping("/page/{categoryId}")
	public String blogPage(@PathVariable long categoryId,Model model) {
		//获取第1页博客列表
		Page<BlogWithBLOBs> blogPage = blogService.getBlogByPage(1, 10, categoryId);
		model.addAttribute("blogPage", blogPage);
		//添加博客类别列表
		List<BlogCategory> blogCategories = blogService.getBlogCountOfCategories();
		model.addAttribute("blogCategories", blogCategories);
		return "pages/blog";
	}
	
	//按页获取博客数据，响应前端ajax请求
	@GetMapping("/page/{pageNo}/{categoryId}")
	public String blogPage(@PathVariable int pageNo,@PathVariable long categoryId, Model model) {
		Page<BlogWithBLOBs> blogPage = blogService.getBlogByPage(pageNo, 10, categoryId);
		System.out.println("currentPage:" + blogPage.getPageNum());
		model.addAttribute("blogPage", blogPage);
		return "layout/blog-list";
	}
	
	//博客详情页面
	@GetMapping("/detail/{blogId}")
	public String blogDetailPage(@PathVariable long blogId,Model model) {
		//添加填写博客
		BlogWithBLOBs blog = blogService.getBlogById(blogId);
		model.addAttribute("blog", blog);
		
		//添加填写blog评论
		List<Comment> comments = commentService.getBlogCommentsById(blogId);
		model.addAttribute("comments", comments);
		
		//添加blog评论数量
		int commentCount = commentService.getBolgCommentsById(blogId);
		model.addAttribute("commentCount", commentCount);
		
		//添加评论类别
		model.addAttribute("commentType", DictionaryConstant.COMMENT_TYPE_BLOG);
		
		//添加博客分类
		List<BlogCategory> blogCategories = blogService.getBlogCountOfCategories();
		model.addAttribute("blogCategories", blogCategories);
		
		//添加关联博客
		List<BlogWithBLOBs> relatedBlogs = blogService.getRelatedBlogs(blog.getCategory(), blogId);
		model.addAttribute("relatedBlogs", relatedBlogs);
		
		return "pages/blog-single";
	}

}
