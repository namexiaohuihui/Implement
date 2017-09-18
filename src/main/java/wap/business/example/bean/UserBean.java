package wap.business.example.bean;

/**
 * Created by ${XiaoHuiHui} on 2017/9/18 on 10:06.
 * XiaoHiiHui [704866169@qq.com]
 */
public class UserBean {
    private  String id ;
    private  String password ;
    private  String open_id ;
    private  String phone ;
    private  String add_time ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", open_id='" + open_id + '\'' +
                ", phone='" + phone + '\'' +
                ", add_time='" + add_time + '\'' +
                '}';
    }
}
