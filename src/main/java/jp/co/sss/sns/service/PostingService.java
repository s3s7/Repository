package jp.co.sss.sns.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.repository.PostingRepository;

@Service
public class PostingService {
    private final PostingRepository postingRepository;

    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    public List<Posting> getPostingsInNewOrder(List<Posting> postingList) {
        Sort sort = Sort.by(Sort.Direction.DESC, "insertDate");
//    	 Sort sort =   Collections.sort(postingList);
        return postingRepository.findAll(sort);
        
        
    }
    
    public List<Posting> findAllByInsertDateAsc() {
        Sort sort = Sort.by("insertDate").ascending();
        return postingRepository.findAll(sort);
    }
}