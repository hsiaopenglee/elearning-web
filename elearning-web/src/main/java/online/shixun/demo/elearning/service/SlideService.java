package online.shixun.demo.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.demo.elearning.dto.Slide;
import online.shixun.demo.elearning.mapper.SlideMapper;

//轮播图Service
@Service
public class SlideService {
	final private SlideMapper slideMapper;
	
	@Autowired
	public SlideService(SlideMapper slideMapper) {
		this.slideMapper = slideMapper;
	}
	
	//获取在首页上展示的轮播图
	public List<Slide> getHomePageSlides(){
		return slideMapper.getHomePageSlides();
	}

}
