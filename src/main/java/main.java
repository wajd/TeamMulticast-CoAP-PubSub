import org.apache.log4j.BasicConfigurator;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;

import java.io.IOException;
import java.util.Arrays;

import java.util.concurrent.TimeUnit;

public class main {

    public static void main(String[] args) throws  RuntimeException, IOException, InterruptedException {

        String host = "130.229.136.93";
        int port = 5683;
        long timeout = 5000;
        BasicConfigurator.configure();

        PubSub my = new PubSub(host, port, timeout);

        System.out.println(Arrays.toString(my.get_Topics(my.discover(""))));

        System.out.println(my.create("ps", "topic1", 40));
        System.out.println(my.create("ps/topic1", "topic3", 0));

        CoapHandler handler = new CoapHandler() {
            @Override
            public void onLoad(CoapResponse coapResponse) {
                System.out.println(coapResponse.getResponseText());
            }

            @Override
            public void onError() {

            }
        };

        CoapObserveRelation re = my.subscribe("ps/topic1/topic3", handler);
        TimeUnit.SECONDS.sleep(10);
        //my.unsubscribe(re);
    }
}
