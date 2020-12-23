package com.tathink.database.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tathink.database.model.Kit;

@Service
public class KitRepositoryService {
	@Autowired
	private KitRepository kitRepository;

	public Optional<Kit> findBySeq(int seq)
	{
		Optional<Kit> kit = null;
		
		kit = kitRepository.findById(seq);
		
		return kit;
	}

	public Page<Kit> getAllByCodeNameBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return kitRepository.findAllByBDeleteCodeNamePageable(bDelete, keyword, pageable);
	}
	
	public Page<Kit> getAllByCodeBDeletePageable(boolean bDelete, String code,Pageable pageable)
	{
		return kitRepository.findAllByBDeleteCodePageable(bDelete, code, pageable);
	}
	
	public Page<Kit> getAllByNameBDeletePageable(boolean bDelete, String name,Pageable pageable)
	{
		return kitRepository.findAllByBDeleteNamePageable(bDelete, name, pageable);
	}
	
	@Transactional
	public Kit AddKit(Kit kit)
	{
		return kitRepository.saveAndFlush(kit);
	}
	
	@Transactional
	public void DeleteKitList(String listKit)
	{
		String[] kitlist = listKit.split(":");
		
		for(int i=0; i<kitlist.length;i++){
			
			int seq = -1;
			try{
				seq = Integer.parseInt(kitlist[i]);
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			
			if(seq<0){
				return;
			}
			
			Optional<Kit> kit = null;
			
			kit = kitRepository.findById(seq);
			
			if(kit.isPresent() == false)
				return;			
						
			Kit tempKit = kit.get();
				
			kitRepository.delete(tempKit);
		}

	}
}
