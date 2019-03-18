package wq.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.spi.CopyOnWrite;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wq
 * @date 2019/3/17
 */

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接，总数：{}", webSocketSet.size());
    }

    @OnClose
    public void onClose(Session session) {
        webSocketSet.remove(this);
        log.info("【websock消息】，有连接断开，连接总数={}" , webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【websock消息】，收到新的消息，msg={}" , message);
    }

    public void sendMessage(String message) {
        for(WebSocket webSocket : webSocketSet) {
            log.info("【websock消息】广播消息, message={}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("【websock消息】，广播消息失败，e={}" , e);
            }
        }
    }


}
