package online.shixun.demo.elearning.util;

/**
 * 路径工具类
 *
 */

public class ResourcePathUtil {
	private static String RESOURCE_SERVER = "http://localhost:8089/images";
	private static String COURSE_IMG_PREFIX = "/course/course-";
	private static String SLIDE_IMG_PREFIX = "/slide/slide-";
	private static String BLOG_IMG_PREFIX = "/blog/blog-";
	private static String COURSE_CHAPTER_VIDEO = "/chapter/chapter-";
	private static String USER_IMG_PREFIX = "/user/user-";
	private static String TEACHER_IMG_PREFIX = "/teacher/teacher-";
	private static String STUDENT_IMG_PREFIX = "/student/student-";

	/**
	 * 获取课程图片路径
	 */
	public static String getCourseImage(long id, String suffix) {
		return RESOURCE_SERVER + COURSE_IMG_PREFIX + "1" + suffix;
	}

	// 获取轮播图图片路径
	public static String getSlideImage(long id, String suffix) {
		return RESOURCE_SERVER + SLIDE_IMG_PREFIX + id + suffix;
	}

	// 获取博客图片路径
	public static String getBlogImage(long id, String suffix) {
		return RESOURCE_SERVER + BLOG_IMG_PREFIX + "1" + suffix;
	}

	/**
	 * 获取用户图片路径
	 *
	 * @param id
	 * @param suffix
	 * @return
	 */
	public static String getUserImage(long id, String suffix) {
		return RESOURCE_SERVER + USER_IMG_PREFIX + "1" + suffix;
	}

	/**
	 * 获取学生图片
	 *
	 * @param id
	 * @param suffix
	 * @return
	 */
	public static String getStudentImage(long id, String suffix) {
		return RESOURCE_SERVER + STUDENT_IMG_PREFIX + "1" + suffix;
	}

	/**
	 * 获取章节视频资源地址
	 *
	 * @param id
	 * @return
	 */
	public static String getCourseVideo(long id) {
		return RESOURCE_SERVER + COURSE_CHAPTER_VIDEO + "default" + ".mp4";
	}

	/**
	 * 获取讲师图片路径
	 *
	 * @param id
	 * @param suffix
	 * @return
	 */
	public static String getTeacherImage(long id, String suffix) {
		return RESOURCE_SERVER + TEACHER_IMG_PREFIX + "1" + suffix;
	}

}
