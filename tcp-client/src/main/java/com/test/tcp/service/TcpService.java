package com.test.tcp.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
@NoArgsConstructor
@Service
public class TcpService {

    private static final int TCP_PORT = 5000;

    private static Socket socket;

    public void connect() {
        // 연결 요청
        socket = new Socket();
        log.info("Request to connect to port : {}", TCP_PORT);

        try {
            socket.connect(new InetSocketAddress(TCP_PORT));
            log.info("connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sendMessage(String sendingMessage) {
        String receivedMessage = "";

        try {
            // 데이저 송신
            OutputStream outputStream = socket.getOutputStream();
            byte[] outputBytes = sendingMessage.getBytes(StandardCharsets.UTF_8);
            outputStream.write(outputBytes);
            outputStream.flush();
            log.info("Message sent successfully!");

            // 데이터 수신
            InputStream inputStream = socket.getInputStream();
            byte[] inputBytes = new byte[2048];
            int size = inputStream.read(inputBytes);
            receivedMessage = new String(inputBytes, 0, size, "utf-8");
            log.info("Get Message : {}", receivedMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return receivedMessage;
    }
}
