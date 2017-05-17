package zcf.com.robot.bean;


import java.util.Date;
/**
 * 加密请求测试类
 * @author 图灵机器人
 *
 */
public class ChatMessage {


    private String name;
    private String msg;
    private Type type;
    private Date data;


    public ChatMessage()
    {
    }



    public ChatMessage(String msg, Type type, Date date)
    {
        super();
        this.msg = msg;
        this.type = type;
        this.data = date;
    }



    public enum Type{
        INCOMING,OUTCOMING
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate(){return data;}
    public void setDate(Date data) {
        this.data=data;
    }

}
