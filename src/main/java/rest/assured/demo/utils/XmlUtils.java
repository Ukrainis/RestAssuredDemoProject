package rest.assured.demo.utils;

import rest.assured.demo.pojo.CreateUserRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XmlUtils {
    private static Marshaller marshaller;

    public static String buildCreateUserRequestXmlBody(CreateUserRequest createUserRequest) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CreateUserRequest.class);
        getXmlMarshaller(jaxbContext);
        String xmlRequestBody;
        xmlRequestBody = getDeserializedRequest(createUserRequest);
        return xmlRequestBody;
    }

    private static <E> String getDeserializedRequest(E request) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(request, stringWriter);
        return stringWriter.toString();
    }

    private static void getXmlMarshaller(JAXBContext jaxbContext) throws JAXBException {
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
    }
}