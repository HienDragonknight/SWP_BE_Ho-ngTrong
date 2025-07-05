package com.quitsmoking.platform.api;

import com.quitsmoking.platform.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private  PaymentService paymentService;

    @PostMapping("/create-payment/{fee}")
    public ResponseEntity<Map<String, String>> createPaymentUrl(@PathVariable long fee, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put("url", paymentService.createPaymentUrl(fee, request));
        return ResponseEntity.ok(map);
    }

    @GetMapping("/vn-pay-callback")
    public void payCallbackHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        paymentService.handlePaymentCallBack(request, response);
    }

}
