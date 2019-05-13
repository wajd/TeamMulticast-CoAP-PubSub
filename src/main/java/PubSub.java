import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class PubSub {

    /* Returns array of Topic objects and Confirmation Code*/
    public static Topic[] discover(String host, int port, Code code, long timeout) {
        CoapClient client = new CoapClient("coap", host, port, "/.well-known/core");
        client.setTimeout(timeout);

        CoapResponse x = client.get();
        String content = x.getResponseText();
        code.setResponse(x.getCode());

        String[] topicS = content.split(",");
        Topic[] topicT = new Topic[topicS.length];
        for (int i = 0; i < topicS.length; i++) {
            topicT[i] = new Topic(topicS[i]);
        }
        return topicT;
    }

    /* Returns topic and Confirmation Code */
    public static Topic create(String host, int port, Code code, Topic topic) {
        CoapClient client = new CoapClient("coap", host, port, "ps");
        String payload = topic.makeCreate();

        code.setResponse(client.post(payload, 0).getCode());
        topic.setPath();

        return topic;
    }

    /* Returns topic and Confirmation Code */
    public static Topic create(String host, int port, Code code, Topic parent, Topic child) {
        CoapClient client = new CoapClient("coap", host, port, parent.getPath());
        String payload = child.makeCreate();

        code.setResponse(client.post(payload, 0).getCode());
        child.setPath();

        return child;
    }

    /* Returns Confirmation Code */
    public static void publish(String host, int port, Code code, Topic topic, String payload) {
        CoapClient client = new CoapClient("coap", host, port, topic.getPath());

        code.setResponse(client.put(payload, topic.getCt()).getCode());
    }

    /* Returns Content and Confirmation Code */
    public static String read(String host, int port, Code code, Topic topic) {
        CoapClient client = new CoapClient("coap", host, port, topic.getPath());

        CoapResponse x = client.get();
        String data = x.getResponseText();
        code.setResponse(x.getCode());
        return data;
    }

    /* Returns Confirmation Code */
    public static void remove(String host, int port, Code code, Topic topic) {
        CoapClient client = new CoapClient("coap", host, port, topic.getPath());

        code.setResponse(client.delete().getCode());
    }

    /* Gets a stream of Content */

    /* NOT FINAL */
    public static void subscribe(String host, int port, String path) {
        CoapClient client = new CoapClient("coap", host, port, path);

        CoapHandler handler = new CoapHandler() {
            @Override
            public void onLoad(CoapResponse coapResponse) {
                System.out.println(coapResponse.getResponseText());
            }

            @Override
            public void onError() {

            }
        };
        client.observe(handler);
        while (true) ;
    }
}
