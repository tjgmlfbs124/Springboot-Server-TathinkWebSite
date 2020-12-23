package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.OfflineLesson;
import com.tathink.database.model.OfflineLessonList;

@Service
public class OfflineLessonRepositoryService {
	@Autowired
	private OfflineLessonRepository offlineLessonRepository;
	
	public List<OfflineLesson> getAllByOfflineClassSeq(int classSeq, Sort sort)
	{
		return  offlineLessonRepository.findAllByOfflineClassSeq(classSeq, sort);
	}
	
	@Transactional
	public void deleteLessonList(String listLesson)
	{					
		if(listLesson != null && listLesson.length() > 0){
			String[] lessonList = listLesson.split(":");
			
			int tempLessonSeq=-1;
			
			for(int i=0; i<lessonList.length;i++){					
				
				try{
					tempLessonSeq = Integer.parseInt(lessonList[i]);
					Optional<OfflineLesson> tempLesson = offlineLessonRepository.findById(tempLessonSeq);
					
					if(tempLesson.isPresent()){
						offlineLessonRepository.delete(tempLesson.get());
					}					
				}catch(Exception e){
					e.printStackTrace();
					return;
				}				
			}									
		}	
	}
	
	@Transactional
	public void saveOfflineLesson(int classSeq, OfflineLessonList list){
		try{
			for(int i =0; i<list.getLessonList().size(); i++){
				OfflineLesson tempLesson = new OfflineLesson();
				if(list.getLessonList().get(i).getSeq()>=0){
					tempLesson.setSeq(list.getLessonList().get(i).getSeq());
				}
				
				tempLesson.setClassSeq(classSeq);
				tempLesson.setLessonOrder(list.getLessonList().get(i).getLessonOrder());
				tempLesson.setContent(list.getLessonList().get(i).getContent());
				
				offlineLessonRepository.saveAndFlush(tempLesson);
			}			
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
}
