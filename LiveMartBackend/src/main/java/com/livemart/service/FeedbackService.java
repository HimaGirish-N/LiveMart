package com.livemart.service;

import com.livemart.model.Feedback;
import com.livemart.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository repo;

    public FeedbackService(FeedbackRepository repo) {
        this.repo = repo;
    }

    public Feedback addFeedback(Feedback f) {
        return repo.save(f);
    }

    public List<Feedback> getFeedbackByProduct(Long productId) {
        return repo.findByProduct_Id(productId);
    }
}
