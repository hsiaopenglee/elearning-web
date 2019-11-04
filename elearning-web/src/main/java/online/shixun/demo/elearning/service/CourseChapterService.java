package online.shixun.demo.elearning.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.demo.elearning.dto.CourseChapter;
import online.shixun.demo.elearning.dto.LearningProgress;
import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.mapper.CourseChapterMapper;
import online.shixun.demo.elearning.mapper.LearningProgressMapper;

@Service
public class CourseChapterService extends BaseService {
    final private CourseChapterMapper courseChapterMapper;
    final private LearningProgressMapper learningProgressMapper;
    @Autowired
    public CourseChapterService(CourseChapterMapper courseChapterMapper, LearningProgressMapper learningProgressMapper) {
        this.courseChapterMapper = courseChapterMapper;
        this.learningProgressMapper = learningProgressMapper;
    }
    
    //根据ID获取课程章节
    public CourseChapter getCourseChapterById(long id) {
        return courseChapterMapper.selectByPrimaryKey(id);
    }
    /**
     * 更新用户学习课程的记录，如果之前不存在该用户的课程学习记录，则自动创建
     *
     * @param courseId
     * @param courseChapterId
     */
    public int updateUserCourseLearningProgress(long courseId, long courseChapterId) {
        Student student = getCurrentUser();
        LearningProgress progress = new LearningProgress();
        progress.setChapterId(courseChapterId);
        progress.setStudentId(student.getId());
        progress.setCourseId(courseId);
        progress.setUpdateTime(new Date());
        int currentLearningIndex = 1;
        Long ProgressId = learningProgressMapper.ifProgressExits(student.getId(), courseId);
        if (ProgressId != null) { // 当前用户已经学习过此课程，直接更新学习进度
            progress.setId(ProgressId);
            int chapterIndex = learningProgressMapper.getChapterIndexByChapterId(courseChapterId);
            currentLearningIndex  = chapterIndex;
            learningProgressMapper.updateLearningProgress(progress, chapterIndex);
        } else { // 当前用户未学习此课程，插入学习进度
            progress.setId(nextId());
            learningProgressMapper.insert(progress);
        }
        return currentLearningIndex;
    }
}