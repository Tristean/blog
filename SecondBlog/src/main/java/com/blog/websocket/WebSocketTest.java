package com.blog.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.blog.entity.ChatMessage;
import com.blog.services.ChatMessageService;
import com.blog.util.JsonUtil;

/**
 * @ServerEndpoint ע����һ�����ε�ע�⣬���Ĺ�����Ҫ�ǽ�Ŀǰ���ඨ���һ��websocket��������,
 * ע���ֵ�������ڼ����û����ӵ��ն˷���URL��ַ,�ͻ��˿���ͨ�����URL�����ӵ�WebSocket��������
 */
@ServerEndpoint(value="/websocket",configurator=SpringConfigurator.class)
public class WebSocketTest {
    //��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
    private static int onlineCount = 0;

    //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
    private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();

    //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
    private Session session;

    /**
     * ���ӽ����ɹ����õķ���
     * @param session  ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //����set��
        addOnlineCount();           //��������1
        System.out.println("�������Ӽ��룡��ǰ��������Ϊ" + getOnlineCount());
    }

    /**
     * ���ӹرյ��õķ���
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //��set��ɾ��
        subOnlineCount();           //��������1
        System.out.println("��һ���ӹرգ���ǰ��������Ϊ" + getOnlineCount());
    }

    /**
     * �յ��ͻ�����Ϣ����õķ���
     * @param message �ͻ��˷��͹�������Ϣ
     * @param session ��ѡ�Ĳ���
     */
    @OnMessage
    public void onMessage(String message, Session session) {
    	String str=JsonUtil.parse(message);
        System.out.println("���Կͻ��˵���Ϣ"+str);
        //Ⱥ����Ϣ
        for(WebSocketTest item: webSocketSet){
            try {
                item.sendMessage(str);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * ��������ʱ����
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("��������");
        error.printStackTrace();
    }

    /**
     * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ��ӵķ�����
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
    
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketTest.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketTest.onlineCount--;
    }
}