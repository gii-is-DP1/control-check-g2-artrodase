package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {
	
	private FeedingRepository feedingRepo;
	
	@Autowired
    public FeedingService(FeedingRepository feedingRepo) {
		this.feedingRepo = feedingRepo;
	}

	public List<Feeding> getAll(){
        return feedingRepo.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return feedingRepo.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepo.findFeedingType(typeName);
    }

    @Transactional
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        return feedingRepo.save(p);       
    }

    
}
