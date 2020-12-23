package com.tathink.database.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.tathink.controller.model.ReqClassReportWrite;
import com.tathink.database.model.ClassReport;
import com.tathink.database.model.Member;

@Service
public class ClassReportRepositoryService {
	@Autowired
	private ClassReportRepository classReportRepository;
	
	public List<ClassReport> getAllByClassSeq(int classSeq, String username, Sort sort){
		return classReportRepository.findAllByClassSeq(classSeq, username, sort);
	}
	
	public List<ClassReport> getAllByHomeworkSeq(int homeworkSeq, Sort sort){
		return classReportRepository.findAllByHomeworkSeq(homeworkSeq, sort);
	}
	
	public ClassReport getByHomeworkSeqUsername(int homeworkSeq, String username, Sort sort){
		return classReportRepository.findAllByHomeworkSeq(homeworkSeq, username, sort);
	}
	
	public ClassReport findBySeq(int seq)
	{
		return classReportRepository.findById(seq).get();
	}
	
	@Transactional
	public void removeReportByListSeq(String[] listSeqList)
	{
		for(String strSeq : listSeqList)
		{
			try
			{
				classReportRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		classReportRepository.flush();
	}
	
	@Transactional
	public void removeBySeq(int seq)
	{		
		classReportRepository.deleteById(seq);
		
		classReportRepository.flush();
	}
	
	@Transactional
	public ClassReport saveReport(ReqClassReportWrite reportInfo, Member member)
	{
		ClassReport report = null;
		
		if(reportInfo.getSeq().equals("-1"))
		{
			report = new ClassReport();
			
			report.setClassSeq(Integer.parseInt(reportInfo.getClassSeq()));
			report.setHomeworkSeq(Integer.parseInt(reportInfo.getHomeworkSeq()));
			report.setWriter(member);
			report.setRegDate(LocalDateTime.now());
			
			report = classReportRepository.saveAndFlush(report);
			
			reportInfo.setSeq(""+report.getSeq());
			report.setFiles(reportInfo.getFiles(null));
			
			classReportRepository.saveAndFlush(report);
		}
		else
		{
			report = classReportRepository.findById(Integer.parseInt(reportInfo.getSeq())).get();
			
			report.setFiles(reportInfo.getFiles(report.getFiles()));
			classReportRepository.saveAndFlush(report);
		}
		
		return report;			
	}
	
	@Transactional
	public void saveReportResult(String seqList, String pointList)
	{
		String[] reportSeqList = seqList.split(":");
		String[] reportPointList = pointList.split(":");
		
		if(reportPointList.length != reportPointList.length)
			return;
		
		for(int i=0; i<reportSeqList.length;i++){	
			
			int seq = -1;
			String point=null;
			try{
				seq = Integer.parseInt(reportSeqList[i]);
				point = reportPointList[i];
			}catch(Exception e){
				e.printStackTrace();
				break;
			}
			
			if(seq<0){
				continue;
			}

			Optional<ClassReport> classReport = null;				
			classReport = classReportRepository.findById(seq);
			
			if(classReport.isPresent()==false)
				continue;
			ClassReport tempClass =  classReport.get();
			tempClass.setPoint(point);			
			
			classReportRepository.save(tempClass);
		}
		return;
	}
}
