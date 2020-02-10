package family.track;

public class CreateUser {

    public  CreateUser(){}
    public String  name;
    public String email;
    public String password;
    public String code;
    public String isSharing;
    public String lng;
    public String lat;
    public String imageUrl;

    public CreateUser(String name, String email, String password, String code, String isSharing, String lng, String lat, String imageUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.isSharing = isSharing;
        this.lng = lng;
        this.lat = lat;
        this.imageUrl = imageUrl;
    }
}
