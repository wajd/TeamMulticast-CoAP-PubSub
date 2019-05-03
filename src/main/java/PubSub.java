import org.eclipse.californium.core.*;


public class PubSub {
    public static String[] discover(String host, int port, long timeout, String path){
        CoapClient client = new CoapClient("coap", host, port,path);
        client.setTimeout(timeout);
        String content = client.get().getResponseText();
        String[] topics = content.split(",");
        for(int i = 0; i < topics.length; i++) {
            System.out.println("FOUND TOPIC " + (i+1) + ":      " + topics[i]);
        }
        return topics;
    }

    public static void create(String host, int port, long timeout, String path, String name, int ct){
        CoapClient client = new CoapClient("coap", host, port,path);
    StringBuilder sb = new StringBuilder().append("<").append(name).append(">;ct=").append(ct);
    String payload = sb.toString();
        CoapResponse resp = client.post(payload,0);
        System.out.println(resp.isSuccess());
        System.out.println(resp.getResponseText());
    }

    public static void publish(String host, int port, String path, String payload, int ct){
        CoapClient client = new CoapClient("coap", host, port,path);
        client.put(payload,ct);
    }

    public static void read(String host, int port, String path){
        CoapClient client = new CoapClient("coap", host, port,path);
        String data = client.get().getResponseText();
        System.out.println(data);
    }

    public static void remove(String host, int port, String path){
        CoapClient client = new CoapClient("coap", host, port,path);
        client.delete();
    }
    public static void subscribe(String host, int port, String path ){

        CoapObserveRelation client = new CoapObserveRelation()
    }
}