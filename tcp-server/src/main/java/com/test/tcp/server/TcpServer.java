package com.test.tcp.server;

import com.test.tcp.service.TcpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@Component
public class TcpServer {

    private static final int TCP_PORT = 5000;

    public TcpServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true); // 타임아웃 시 동일 포트로 바인딩 가능

        try {
            // 포트 바인딩
            serverSocket.bind(new InetSocketAddress(TCP_PORT));
            log.info("Starting tcp server port : {}", TCP_PORT);

            while (true) {
                // 요청 대기, 요청 전까지 스레드는 대기 상태
                Socket socket = serverSocket.accept();
                log.info("Connected to port : " + socket.getPort());
                log.info("Connected to local port : " + socket.getLocalPort());

                TcpService tcpService = new TcpService(socket);
                tcpService.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(!serverSocket.isClosed()) serverSocket.close();
        }
    }
}
