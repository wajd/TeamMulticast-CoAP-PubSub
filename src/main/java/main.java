import com.google.appengine.repackaged.org.joda.time.Seconds;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;

import java.io.IOException;
import java.util.Arrays;

import java.util.concurrent.TimeUnit;

public class main {

    public static void main(String[] args) throws  RuntimeException, IOException, InterruptedException {

        String host = "127.0.0.1";
        int port = 5683;
        long timeout = 5000;
        BasicConfigurator.configure();

        PubSub my = new PubSub(host, port, timeout);

        System.out.println(Arrays.toString(my.get_Topics(my.discover(""))));

        System.out.println(my.create("ps", "topic1", 40));
        System.out.println(my.create("ps/topic1", "topic3", 0));

        SubscribeListener listener = new SubscribeListener() {
            @Override
            public void onResponse(String responseText) {
                System.out.println(responseText);
            }

            @Override
            public void onError() {
                System.out.println("ERROR");
            }
        };

        PubSub.Subscription subscription = my.new Subscription("ps/topic1/topic3", listener);
        subscription.subscribe();
        Thread.sleep(15000);
        subscription.unsubscribe();
    }
}
