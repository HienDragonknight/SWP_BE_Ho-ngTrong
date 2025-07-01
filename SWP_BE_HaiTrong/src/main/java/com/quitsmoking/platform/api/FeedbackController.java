package com.quitsmoking.platform.api;

import com.quitsmoking.platform.dto.FeedbackRequest;
import com.quitsmoking.platform.dto.FeedbackResponse;
import com.quitsmoking.platform.dto.FeedbackUpdateRequest;
import com.quitsmoking.platform.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(@RequestBody FeedbackRequest request) {
        return ResponseEntity.ok(feedbackService.addFeedback(request));
    }


    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbacks(@PathVariable Long blogId) {
        return ResponseEntity.ok(feedbackService.getFeedbacksByBlogId(blogId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponse> update(@PathVariable Long id, @RequestBody FeedbackUpdateRequest request) {
        request.setId(id); // Gán ID từ path vào request
        FeedbackResponse updated = feedbackService.updateFeedback(request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

}
