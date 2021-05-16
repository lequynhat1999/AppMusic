package com.example.appmusic;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//@Generated("jsonschema2pojo")
public class User  implements Serializable { // convert những biến trong json về dạng biến của java

        @SerializedName("Id") //convert từ json về java
        @Expose                // thực hiện phương thức convert về
        private String id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("User")
        @Expose
        private String user;
        @SerializedName("Pass")
        @Expose
        private String pass;
        @SerializedName("Email")
        @Expose
        private String email;

        public String getId() {
        return id;
        }

        public void setId(String id) {
        this.id = id;
        }

        public String getName() {
        return name;
        }

        public void setName(String name) {
        this.name = name;
        }

        public String getUser() {
        return user;
        }

        public void setUser(String user) {
        this.user = user;
        }

        public String getPass() {
        return pass;
        }

        public void setPass(String pass) {
        this.pass = pass;
        }

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}