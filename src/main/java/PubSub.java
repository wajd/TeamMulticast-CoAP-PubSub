import org.eclipse.californium.core.CoapClient;

public class PubSub {
    public static String[] discover(String host, int port, long timeout){
        CoapClient client = new CoapClient("coap", host, port,"");
        client.setTimeout(timeout);
        String content = client.get().getResponseText();
        String[] topics = content.split(",");
        for(int i = 0; i < topics.length; i++) {
            System.out.println("FOUND TOPIC " + (i+1) + ":" + topics[i]);
        }
        return topics;
    }
}