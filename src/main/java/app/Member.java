package app;

import org.json.JSONObject;

import util.DBMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Member extends User{

    private Connection conn = null;
    private PreparedStatement pres = null;

    public Member(int id , String name, String email, String password, String phone, Timestamp update_time ,String role, String shop_user_status) {
        super(id,name, email, password, phone, update_time ,role,shop_user_status);
    }
    
    public Member(String email, String password, String name, String phone){
        super(email, password, name, phone);
    }

    public Member(String email, String password){
        super(email, password);
    }

    public Member(){
        super(0,"", "", "", "",null ,"","");
    }

    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("user_id", getId());
        jso.put("user_name", getName());
        jso.put("user_email", getEmail());
        jso.put("user_password", getPassword());
        jso.put("user_phone", getPhone());
        jso.put("user_update_time", getUpdate_time());
        jso.put("user_role", getRole());
        jso.put("shop_user_status", getShop_user_status());

        return jso;
    }


}