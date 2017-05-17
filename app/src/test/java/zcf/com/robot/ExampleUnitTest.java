package zcf.com.robot;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.Date;

import zcf.com.robot.bean.ChatMessage;
import zcf.com.robot.bean.Result;
import zcf.com.robot.util.Aes;
import zcf.com.robot.util.Md5;
import zcf.com.robot.util.PostServer;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test

    public static ChatMessage sendChatMessage(String msg){
        ChatMessage chatMessage = new ChatMessage();
        String jsonRes = testAes(msg);
        Gson gson = new Gson();
        Result result = null;
        try
        {
            result = gson.fromJson(jsonRes, Result.class);
            chatMessage.setMsg(result.getText());
        } catch (Exception e)
        {
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;

    }


    public static String testAes(String cmd){
        String user="abc123";
        //图灵网站上的secret
        String secret = "eeebfc47e862ee61";
        //图灵网站上的apiKey
        String apiKey = "89f31425c5ae4e6c9ef7bbeb9fdf8ca6";
        //String cmd = "成语接龙";//测试用例
        //待加密的json数据
        String data = "{\"key\":\""+apiKey+"\",\"info\":\""+cmd+"\",\"userid\":\""+user+"\"}";
        //获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        //生成密钥
        String keyParam = secret+timestamp+apiKey;
        String key = Md5.MD5(keyParam);
        //加密
        Aes mc = new Aes(key);
        data = mc.encrypt(data);
        //封装请求参数
        JSONObject json = new JSONObject();
        json.put("key", apiKey);
        json.put("timestamp", timestamp);
        json.put("data", data);

        //请求图灵api
        String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
        //System.out.println(result);
        return result;
    }
}