package app;

import java.sql.Timestamp;

public class Shop extends User{
    
    public Shop(int id,String name, String email, String password, String phone,Timestamp update_time, String role) {
        super(id,name, email, password, phone,update_time,role);
    }
    
}
