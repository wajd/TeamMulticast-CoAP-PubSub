import org.eclipse.californium.core.coap.CoAP;

public class Code {

    CoAP.ResponseCode response;

    public Code(){

    }

    public Code(CoAP.ResponseCode response) {
        this.response = response;
    }

    public CoAP.ResponseCode getResponse() {
        return response;
    }

    public void setResponse(CoAP.ResponseCode response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return response.toString();
    }
}
