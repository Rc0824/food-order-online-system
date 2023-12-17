package app;

import org.json.JSONObject;
import java.sql.Timestamp;

public class Member extends User{

    public Member(int id , String name, String email, String password, String phone, Timestamp update_time ,String role) {
        super(id,name, email, password, phone, update_time ,role);
    }

    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("user_id", getId());
        jso.put("user_name", getName());
        jso.put("user_gmail", getEmail());
        jso.put("user_password", getPassword());
        jso.put("user_phone", getPhone());
        jso.put("user_update_time", getUpdate_time());
        jso.put("user_role", getRole());
        return jso;
    }
    
}