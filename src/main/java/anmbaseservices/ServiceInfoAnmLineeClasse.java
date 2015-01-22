
package anmbaseservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "ServiceInfoAnmLineeClasse", targetNamespace = "http://m.anm.it/srv/", wsdlLocation = "http://m.anm.it/srv/ServiceInfoAnmLinee.asmx?WSDL")
public class ServiceInfoAnmLineeClasse
    extends Service
{

    private final static URL SERVICEINFOANMLINEECLASSE_WSDL_LOCATION;
    private final static WebServiceException SERVICEINFOANMLINEECLASSE_EXCEPTION;
    private final static QName SERVICEINFOANMLINEECLASSE_QNAME = new QName("http://m.anm.it/srv/", "ServiceInfoAnmLineeClasse");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://m.anm.it/srv/ServiceInfoAnmLinee.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICEINFOANMLINEECLASSE_WSDL_LOCATION = url;
        SERVICEINFOANMLINEECLASSE_EXCEPTION = e;
    }

    public ServiceInfoAnmLineeClasse() {
        super(__getWsdlLocation(), SERVICEINFOANMLINEECLASSE_QNAME);
    }

    public ServiceInfoAnmLineeClasse(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns ServiceInfoAnmLineeClasseSoap
     */
    @WebEndpoint(name = "ServiceInfoAnmLineeClasseSoap")
    public ServiceInfoAnmLineeClasseSoap getServiceInfoAnmLineeClasseSoap() {
        return super.getPort(new QName("http://m.anm.it/srv/", "ServiceInfoAnmLineeClasseSoap"), ServiceInfoAnmLineeClasseSoap.class);
    }

    private static URL __getWsdlLocation() {
        if (SERVICEINFOANMLINEECLASSE_EXCEPTION!= null) {
            throw SERVICEINFOANMLINEECLASSE_EXCEPTION;
        }
        return SERVICEINFOANMLINEECLASSE_WSDL_LOCATION;
    }

}