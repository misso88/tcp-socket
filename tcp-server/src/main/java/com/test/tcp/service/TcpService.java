package com.test.tcp.service;

import com.test.tcp.common.exception.InvalidXmlException;
import com.test.tcp.domain.entity.Test;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
@NoArgsConstructor
@Service
public class TcpService extends Thread {

    private static Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;

    public TcpService (Socket socket) throws Exception {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    public void run() {
        try {
            receiveMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(!socket.isClosed()) socket.close();
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void receiveMessage() throws Exception {
        // 데이터 수신
        byte[] inputBytes = new byte[2048];
        int size = inputStream.read(inputBytes);
        String receivedMessage = new String(inputBytes, 0, size, "utf-8");
        log.info("Get Message : {}", receivedMessage);

        try {
            // XML to Object
            JAXBContext jaxbContext = JAXBContext.newInstance(Test.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Test test = (Test) unmarshaller.unmarshal(new StringReader(receivedMessage));

            sendMessage(receivedMessage);
        } catch (Exception e) {
            throw new InvalidXmlException();
        }
    }

    public static void sendMessage(String sendingMessage) throws Exception {
        // 데이저 송신
        byte[] outputBytes = sendingMessage.getBytes(StandardCharsets.UTF_8);
        outputStream.write(outputBytes);
        outputStream.flush();
        log.info("Message sent successfully!");
    }
}
