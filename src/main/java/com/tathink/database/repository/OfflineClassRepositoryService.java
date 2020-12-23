package com.tathink.database.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tathink.database.model.OfflineClass;

@Service
public class OfflineClassRepositoryService {
	@Autowired
	private OfflineClassRepository offlineClassRepository;
	
	public OfflineClass getClass(int seq){
		Optional<OfflineClass> offlineClass = null; 
		offlineClass = offlineClassRepository.findById(seq);
		
		if(offlineClass.isPresent() == false)
			return null;
		
		return offlineClass.get();
	}
	
	public OfflineClass findActiveClassByOfflineStudySeq(int offlineStudyInfoSeq)
	{
		LocalDate now = LocalDate.now();
		List<OfflineClass> listOfflineClass = null;
		listOfflineClass = offlineClassRepository.findActiveClassByOfflineStudyInfoSeq(offlineStudyInfoSeq, now, false, new Sort(Direction.DESC, "seq"));
		
		if(listOfflineClass.size() == 0)
			return null;
		
		return listOfflineClass.get(0);
	}
	
	public List<OfflineClass> getClassListByTeacherUsername(boolean bDelete, String teacherUserName, Sort sort){
		List<OfflineClass> listOfflineClass = null;
		listOfflineClass = offlineClassRepository.findClassListByTeacherUsername(teacherUserName, bDelete, sort);
			
		return listOfflineClass;
	}
	
	public List<OfflineClass> getClassListBySeqList(boolean bDelete, List<Integer> listSeq, Sort sort){
		List<OfflineClass> listOfflineClass = null;
		listOfflineClass = offlineClassRepository.findClassListBySeqList(listSeq, bDelete, sort);
			
		return listOfflineClass;
	}
	
	public Page<OfflineClass> getAllClassByBDeletePageable(boolean bDelete, String type, String word, Pageable sort){
		if(type.equals("ALL")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", "", "", word, "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "",  "", "", "", word, sort);
		}
		return null;
	}
	
	public Page<OfflineClass> getWaitClassByBDeletePageable(boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;				
	}
	
	public Page<OfflineClass> getApplyClassByBDeletePageable(boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;			
	}
	
	public Page<OfflineClass> getStartClassByBDeletePageable(boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;		
	}
	
	public Page<OfflineClass> getEndClassByBDeletePageable(boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;		
	}
	
	public Page<OfflineClass> getAllMyClassByBDeletePageable(boolean bDelete, List<Integer> listSeq, String type, String word, Pageable sort){
		if(type.equals("ALL")){
			return offlineClassRepository.findMyClassAllByBDeletePageable(bDelete, listSeq, "", "", "", word, "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findMyClassAllByBDeletePageable(bDelete, listSeq, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findMyClassAllByBDeletePageable(bDelete, listSeq, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findMyClassAllByBDeletePageable(bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findMyClassAllByBDeletePageable(bDelete, listSeq, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findMyClassAllByBDeletePageable(bDelete, listSeq, "", "", "", "", word, sort);
		}
		return null;		
	}
	
	public Page<OfflineClass> getApplyMyClassByBDeletePageable(boolean bDelete, List<Integer> listSeq, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findMyClassApplyByBDeletePageable(now, bDelete, listSeq, "", "", "", word, "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findMyClassApplyByBDeletePageable(now, bDelete, listSeq, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findMyClassApplyByBDeletePageable(now, bDelete, listSeq, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findMyClassApplyByBDeletePageable(now, bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findMyClassApplyByBDeletePageable(now, bDelete, listSeq, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findMyClassApplyByBDeletePageable(now, bDelete, listSeq, "", "", "", "", word, sort);
		}
		return null;			
	}
	
	public Page<OfflineClass> getWaitMyClassByBDeletePageable(boolean bDelete, List<Integer> listSeq, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findMyClassWaitByBDeletePageable(now, bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findMyClassWaitByBDeletePageable(now, bDelete, listSeq, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findMyClassWaitByBDeletePageable(now, bDelete, listSeq, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findMyClassWaitByBDeletePageable(now, bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findMyClassWaitByBDeletePageable(now, bDelete, listSeq, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findMyClassWaitByBDeletePageable(now, bDelete, listSeq, "", "", "", "", word, sort);
		}
		return null;
	}
	
	public Page<OfflineClass> getStartMyClassByBDeletePageable(boolean bDelete, List<Integer> listSeq, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findMyClassStartByBDeletePageable(now, bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findMyClassStartByBDeletePageable(now, bDelete, listSeq, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findMyClassStartByBDeletePageable(now, bDelete, listSeq, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findMyClassStartByBDeletePageable(now, bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findMyClassStartByBDeletePageable(now, bDelete, listSeq, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findMyClassStartByBDeletePageable(now, bDelete, listSeq, "", "", "", "", word, sort);
		}
		return null;
	}
	
	public Page<OfflineClass> getEndMyClassByBDeletePageable(boolean bDelete, List<Integer> listSeq, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findMyClassEndByBDeletePageable(now, bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findMyClassEndByBDeletePageable(now, bDelete, listSeq, word, "", "", "", "", sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findMyClassEndByBDeletePageable(now, bDelete, listSeq, "", word, "", "", "", sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findMyClassEndByBDeletePageable(now, bDelete, listSeq, "", "", word, "", "", sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findMyClassEndByBDeletePageable(now, bDelete, listSeq, "", "", "", word, "", sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findMyClassEndByBDeletePageable(now, bDelete, listSeq, "", "", "", "", word, sort);
		}
		return null;
	}
	
	public Page<OfflineClass> getApplyTeacherClassByBDeletePageable(boolean bDelete, String teacher, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		return offlineClassRepository.findClassApplyByBDeletePageable(now, bDelete, teacher, word, sort);
	}
	
	public Page<OfflineClass> getStartTeacherClassByBDeletePageable(boolean bDelete, String teacher, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		return offlineClassRepository.findClassStartByBDeletePageable(now, bDelete, teacher, word, sort);
	}
	
	public Page<OfflineClass> getEndTeacherClassByBDeletePageable(boolean bDelete, String teacher, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		return offlineClassRepository.findClassEndByBDeletePageable(now, bDelete, teacher, word, sort);
	}
	
	public Page<OfflineClass> getAllClassByBDeletePageable(String teacher, boolean bDelete, String type, String word, Pageable sort){
		if(type.equals("ALL")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, word, "", "", "", teacher, sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", word, "", "", teacher, sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", "", word, "", teacher, sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findAllClassByBDeletePageable(bDelete, "",  "", "", "", word, sort);
		}
		return null;
	}
		
	public Page<OfflineClass> getWaitClassByBDeletePageable(String teacher, boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, word, "", "", "", teacher, sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", word, "", "", teacher, sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", "", word, "", teacher, sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findWaitClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;				
	}
	
	public Page<OfflineClass> getApplyClassByBDeletePageable(String teacher, boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, word, "", "", "", teacher, sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", word, "", "", teacher, sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", "", word, "", teacher, sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findApplyClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;			
	}
	
	public Page<OfflineClass> getStartClassByBDeletePageable(String teacher, boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, word, "", "", "", teacher, sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", word, "", "", teacher, sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", "", word, "", teacher, sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findStartClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;		
	}
	
	public Page<OfflineClass> getEndClassByBDeletePageable(String teacher, boolean bDelete, String type, String word, Pageable sort){
		LocalDate now = LocalDate.now();
		if(type.equals("ALL")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("COURSE_CODE")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, word, "", "", "", teacher, sort);
		}else if(type.equals("COURSE_NAME")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", word, "", "", teacher, sort);	
		}else if(type.equals("CLASS_CODE")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", "", word, "", teacher, sort);
		}else if(type.equals("CLASS_NAME")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "", "", "", word, teacher, sort);
		}else if(type.equals("TEACHER")){
			return offlineClassRepository.findEndClassByBDeletePageable(now, bDelete, "",  "", "", "", word, sort);
		}
		return null;		
	}
	
	@Transactional
	public OfflineClass saveOfflinClass(OfflineClass classinfo){
				
		return offlineClassRepository.saveAndFlush(classinfo);
	}
	
	@Transactional
	public void deleteOfflinClass(String listCourse){
		
		OfflineClass classinfo = new OfflineClass();
		offlineClassRepository.delete(classinfo);
	}
}
