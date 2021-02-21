package rest.assured.demo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@XmlRootElement(name="CreateUserRequest")
public class CreateUserRequest {
    private String name;

    private String userName;

    private String email;

    private String phone;

    private String website;

    public String getName() {
        return name;
    }

    @XmlElement(name = "userName")
    public String getUserName() {
        return userName;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "website")
    public String getWebsite() {
        return website;
    }
}
