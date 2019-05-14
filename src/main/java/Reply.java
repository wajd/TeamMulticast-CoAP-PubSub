import org.eclipse.californium.core.coap.CoAP;

public class Reply {
    private CoAP.ResponseCode responseCode;
    private String value;
    private Topic topic;

    public Reply(String value){
        this.value = value;
    }

    public Reply(Topic topic){
        this.topic = topic;
        this.value = null;
    }
    public <T> T getContent(){
        if(!(value==null))
            return (T)value;
        else
            return (T)topic;
    }
}