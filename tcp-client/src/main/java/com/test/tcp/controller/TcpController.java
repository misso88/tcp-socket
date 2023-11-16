package com.test.tcp.controller;

import com.test.tcp.service.TcpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TcpController {

    private final TcpService tcpService;

    @PostMapping("/test/tcp")
    public String TcpConnection(@RequestBody String msg) {
        tcpService.connect();
        return tcpService.sendMessage(msg);
    }
}
