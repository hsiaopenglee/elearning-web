package online.shixun.demo.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import online.shixun.demo.elearning.dto.Comment;
import online.shixun.demo.elearning.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 发表评论
	 * @param comment 用户提交的评论
	 */
	@PostMapping("/post")
	@PreAuthorize("isAuthenticated()")
	public String postComment(Comment comment, Model model) {
		long insertedCommentId = commentService.postComment(comment);
		
		//添加用户提交的评论
		Comment fullCommentInfo = commentService.getCommentById(insertedCommentId);
		model.addAttribute("comment", fullCommentInfo);
		return "layout/comment-section::commentSection";
	}

}
