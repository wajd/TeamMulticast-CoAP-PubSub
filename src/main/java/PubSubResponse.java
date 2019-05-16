import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.WebLink;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.LinkFormat;
import org.eclipse.californium.core.coap.OptionSet;

import java.util.Set;

public class PubSubResponse {
    private CoapResponse coapResponse;

    public PubSubResponse(CoapResponse coapResponse){
        this.coapResponse = coapResponse;
    }

    public CoAP.ResponseCode getCode() {
        return this.coapResponse.getCode();
    }

    public boolean isSuccess() {
        return CoAP.ResponseCode.isSuccess(this.coapResponse.getCode());
    }

    public String getResponseText() {
        return this.coapResponse.getResponseText();
    }

    public byte[] getPayload() {
        return this.coapResponse.getPayload();
    }

    public OptionSet getOptions() {
        return this.coapResponse.getOptions();
    }

    public Topic[] getTopics() {
        Set<WebLink> links = LinkFormat.parse(this.coapResponse.getResponseText());
        Topic[] topics = new Topic[links.size()];

        int i = 0;
        for (WebLink x : links) {
            topics[i] = new Topic(x);
            i++;
        }
        return topics;
    }

}
