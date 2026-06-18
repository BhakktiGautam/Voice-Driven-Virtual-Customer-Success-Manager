package com.vcsm.controller;

import com.vcsm.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/ask")
    public ResponseEntity<Map<String, Object>> ask(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String response = chatbotService.getResponse(message);

        Map<String, Object> result = new HashMap<>();
        result.put("question", message);
        result.put("answer", response);
        result.put("success", true);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/faqs")
    public ResponseEntity<List<Map<String, Object>>> getAllFaqs() {
        return ResponseEntity.ok(chatbotService.getAllFaqs());
    }

    @GetMapping("/faq/{id}")
    public ResponseEntity<Map<String, Object>> getFaqById(@PathVariable int id) {
        Map<String, Object> faq = chatbotService.getFaqById(id);
        if (faq == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faq);
    }
}