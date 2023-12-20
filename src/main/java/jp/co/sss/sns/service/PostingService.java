package jp.co.sss.sns.service;

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

    public List<Posting> getPostingsInNewOrder() {
        Sort sort = Sort.by(Sort.Direction.DESC, "insertDate");
        return postingRepository.findAll(sort);
    }
}