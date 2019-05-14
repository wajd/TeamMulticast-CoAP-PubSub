import org.eclipse.californium.core.*;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Token;
import org.eclipse.californium.core.network.config.NetworkConfig;

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

    public static CoapObserveRelation subscribe(String host, int port, String path)  {


        NetworkConfig coap= NetworkConfig.createStandardWithoutFile();




        CoapClient client = new CoapClient("coap", host, port, path);
        client.useExecutor();
        client.setTimeout(5000L);
       

        Request req = new Request(CoAP.Code.GET);

        req.setURI("coap://"+host+":"+port+"/"+path);
        req.setObserve();

        byte i[] = {0x21};
        Token tt = new Token(i);
        req.setToken(tt);

        System.out.println(Utils.prettyPrint(req));

        CoapObserveRelation re = client.observeAndWait(req,new CoapHandler() {
            @Override
            public void onLoad(CoapResponse response) {


                System.out.println(Utils.prettyPrint(response));

            }

            @Override
            public void onError() {

                System.out.println(" SOMETHING IS WRONG ");
            }
        });

        return  re;
    }

    public static void unsubscribe(CoapObserveRelation relation) {

        relation.proactiveCancel();
        System.out.println("unsubed");

    }

}
