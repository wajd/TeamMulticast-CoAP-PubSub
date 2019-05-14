import org.apache.log4j.BasicConfigurator;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.coap.CoAP;

import java.io.IOException;
import java.util.Arrays;

import java.util.concurrent.TimeUnit;

public class main {

    public static void main(String[] args) throws  RuntimeException, IOException, InterruptedException {


        BasicConfigurator.configure();

        PubSub my = new PubSub("130.229.129.191",5683,5000);



         //System.out.println (my.discover("?ct=40"));

        System.out.println(Arrays.toString(my.get_Topics(my.discover(""))));




        CoapObserveRelation re = PubSub.subscribe(host,port,"ps/topic1/topic3");
        TimeUnit.SECONDS.sleep(30);
        PubSub.unsubscribe(re);
    }
}
