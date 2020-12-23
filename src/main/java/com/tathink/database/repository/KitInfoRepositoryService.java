package com.tathink.database.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tathink.database.model.KitInfo;

@Service
public class KitInfoRepositoryService {
	@Autowired
	private KitInfoRepository kitInfoRepository;
	

	public List<KitInfo> getAllByCourseSeq(int courseSeq)
	{
		return kitInfoRepository.findByCourseSeq(courseSeq);
	}
	
	@Transactional
	public KitInfo AddKitInfo(KitInfo kitInfo)
	{
		return kitInfoRepository.saveAndFlush(kitInfo);
	}
	
	@Transactional
	public void AddKitInfoList(int courseSeq, String listKit)
	{
		
		List<KitInfo> oldList = kitInfoRepository.findByCourseSeq(courseSeq);
			
		if(listKit != null && listKit.length() > 0){
			String[] kitlist = listKit.split(":");
			
			int tempKitSeq=-1;
			
			for(int i=0; i<kitlist.length;i++){					
				
				try{
					tempKitSeq = Integer.parseInt(kitlist[i]);
				}catch(Exception e){
					e.printStackTrace();
					return;
				}	

				int index=0;
				for(KitInfo oldkit : oldList ){
					if(oldkit.getKitSeq() == tempKitSeq){
						oldList.remove(index);
						break;
					}
					index++;
				}			
				
	            KitInfo tempKit = kitInfoRepository.findByCourseSeqKitSeq(courseSeq, tempKitSeq);
	            
				if(tempKit==null){
					tempKit = new KitInfo();
					tempKit.setCourseSeq(courseSeq);				
					tempKit.setKitSeq(tempKitSeq);				
					kitInfoRepository.saveAndFlush(tempKit);
				}									
			}
		}	
		
		for(KitInfo oldkit : oldList ){
			kitInfoRepository.delete(oldkit);
		}

	}
}
