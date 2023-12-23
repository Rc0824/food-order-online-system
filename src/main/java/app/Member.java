package app;

import org.json.JSONObject;

import util.DBMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Member extends User{

    private Connection conn = null;
    private PreparedStatement pres = null;

    public Member(int id , String name, String email, String password, String phone, Timestamp update_time ,String role) {
        super(id,name, email, password, phone, update_time ,role);
    }
    
    public Member(String email, String password, String name, String phone){
        super(email, password, name, phone);
    }

    public Member(){
        super(0,"", "", "", "",null ,"");
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