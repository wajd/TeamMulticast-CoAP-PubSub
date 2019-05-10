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

        code.setResponse(client.get().getCode());
        String content = client.get().getResponseText();
        String[] topicS = content.split(",");
        Topic[] topicT = new Topic[topicS.length];
        for (int i = 0; i < topicS.length; i++) {
            topicT[i] = new Topic(topicS[i]);
        }
        return topicT;
    }

//    /* Returns Confirmation Code */
//    public static CoAP.ResponseCode create(String host, int port, String path, Topic topic) {
//        CoapClient client = new CoapClient("coap", host, port, path);
//        String payload = topic.makeCreate();
//        CoapResponse resp = client.post(payload, 0);
//
//        return CoAP.ResponseCode.valueOf(resp.getCode().value);
//    }

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

    public static void fakeSubscribe(String host, int port, String path) throws InterruptedException {

        System.out.println("Fake Subscribe");

        String newData, oldData = null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        while (true) {
            TimeUnit.MILLISECONDS.sleep(5);

            CoapClient client = new CoapClient("coap", host, port, path);

            newData = client.get().getResponseText();
            if (!newData.equals(oldData)) {
                System.out.println();
                System.out.println(sdf.format(cal.getTime()) + ": " + newData);
                oldData = newData;
            }
        }
    }
}
