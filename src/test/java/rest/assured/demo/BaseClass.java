package rest.assured.demo;

import rest.assured.demo.specifications.RequestSpecifications;
import rest.assured.demo.specifications.ResponseSpecifications;

public class BaseClass {
    RequestSpecifications requestSpecifications;
    ResponseSpecifications responseSpecifications;

    public BaseClass() {
        requestSpecifications = new RequestSpecifications();
        responseSpecifications = new ResponseSpecifications();
    }
}
