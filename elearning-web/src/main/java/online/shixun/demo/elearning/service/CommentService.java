package online.shixun.demo.elearning.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.demo.elearning.dto.Comment;
import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.mapper.CommentMapper;
import online.shixun.demo.elearning.util.DictionaryConstant;

//评论Service
@Service
public class CommentService extends BaseService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	/**
	 * 获取和博客关联的评论
	 * @param subjectId 博客ID
	 */
	public List<Comment> getBlogCommentsById(long subjectId){
		return getComments(DictionaryConstant.COMMENT_TYPE_BLOG, subjectId);
	}
	
	/**
	 * 按类别获取评论
	 * 
	 * @param commentType
	 * @parram subjectId
	 */
	private List<Comment> getComments(String commentType,long subjectId){
		List<Comment> comments = commentMapper.getComments(commentType, subjectId, -1);
		for(Comment comment : comments) {
			List<Comment> subComments = commentMapper.getComments(commentType, subjectId, comment.getId());
			comment.setSubCommentList(subComments);
		}
		return comments;
	}
	
	//获取课程章节关联的评论的数量（包含对评论的评论）
	public int getCourseCommentCountById(long subjectId) {
		return getCommentsCount(DictionaryConstant.COMMENT_TYPE_COURSE,subjectId);
	}
	
	//获取博客关联的评论的数量（包含对评论的评论）
	public int getBolgCommentsById(long subjectId) {
		return getCommentsCount(DictionaryConstant.COMMENT_TYPE_BLOG,subjectId);
	} 
	
	//按id获取评论
	public Comment getCommentById(long id) {
		return commentMapper.getCommentById(id);
	}
	
	//发表评论
	public long postComment(Comment comment) {
		Student student = getCurrentUser();
		comment.setStudentId(student.getId());
		long generatedCommentId = nextId();
		comment.setId(generatedCommentId);
		comment.setCreateTime(new Date());
		comment.setUpdateTime(new Date());
		
		commentMapper.insert(comment);
		return generatedCommentId;
	}
	
	//按类别获取评论数量
	private int getCommentsCount(String commentType,long subjectId) {
		return commentMapper.getSubjectCommentCount(commentType, subjectId);
	}
	
	//删除评论，这里是级联删除，具体设置参考数据库设计
	public int deleteCommentById(long commentId) {
		return commentMapper.deleteByPrimaryKey(commentId);
	}
	
	//获取课程章节关联的评论
	public List<Comment> getCommentsByCourseId(long subjectId){
		return getComments(DictionaryConstant.SLID_TYPE_COURSE, subjectId);
	}
	
	//获取用户发表过的所有评论
	public List<Comment> getUserComments(){
		long userId = getCurrentUser().getId();
		List<Comment> comments = commentMapper.getCommentsByUserId(userId, new DictionaryConstant());
		return comments;
	}
	

}
