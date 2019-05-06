import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.observe.NotificationListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class PubSub {

    /* Returns array of Topic objects */
    public static Topic[] discover(String host, int port, long timeout, String path) {
        CoapClient client = new CoapClient("coap", host, port, path);
        client.setTimeout(timeout);
        String content = client.get().getResponseText();
        String[] topicS = content.split(",");
        Topic[] topicT = new Topic[topicS.length];
        for (int i = 0; i < topicS.length; i++) {
            topicT[i] = new Topic(topicS[i]);
        }
        return topicT;
    }

    /* Returns Confirmation Code */
    public static void create(String host, int port, String path, Topic topic) {
        CoapClient client = new CoapClient("coap", host, port, path);
        String payload = topic.makeCreate();
        CoapResponse resp = client.post(payload, 0);
        System.out.println(resp.isSuccess());
        System.out.println(resp.getResponseText());
    }

    /* Returns Confirmation Code */
    public static void publish(String host, int port, String path, String payload, int ct) {
        CoapClient client = new CoapClient("coap", host, port, path);
        client.put(payload, ct);
    }

    /* Returns Content */
    public static void read(String host, int port, String path) {
        CoapClient client = new CoapClient("coap", host, port, path);
        String data = client.get().getResponseText();
        System.out.println(data);
    }

    /* Returns Confirmation Code */
    public static void remove(String host, int port, String path) {
        CoapClient client = new CoapClient("coap", host, port, path);
        client.delete();
    }
    public static void subscribe(String host, int port, String path){
        CoapClient client = new CoapClient("coap",host, port, path);
        CoapHandler handler = new CoapHandler() {
            @Override
            public void onLoad(CoapResponse coapResponse) {
                System.out.println(coapResponse.getResponseText());
            }

            @Override
            public void onError() {
                System.out.println("ERROR");
            }
        };
        client.observe(handler);
        while (true) ;

    }
    public static void fakeSubscribe(String host, int port, String path) throws InterruptedException {

        System.out.println("Fake Subscribe");

        String newData, oldData=null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        while(true) {
            TimeUnit.SECONDS.sleep(5);

            CoapClient client = new CoapClient("coap", host, port, path);

            newData = client.get().getResponseText();
            if(!newData.equals(oldData)){
                System.out.println();
                System.out.println(sdf.format(cal.getTime())+": "+newData);
            }
        }
    }
}