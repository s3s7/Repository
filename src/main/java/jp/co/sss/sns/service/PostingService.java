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
  
    // 記事削除機能
	public void deletePosting(int postingId) {

		// 記事に関する全情報を削除
		postingRepository.deleteByPostingId(postingId);
//			postRepository.deleteByUsername(username);
//			commentRepository.deleteByUsername(username);
	}
}
