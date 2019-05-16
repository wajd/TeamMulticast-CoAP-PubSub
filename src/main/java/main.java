import org.apache.log4j.BasicConfigurator;
import java.io.IOException;
import java.util.Arrays;

public class main {

    public static void main(String[] args) throws  RuntimeException, IOException, InterruptedException {

        String host = "127.0.0.1";
//        BasicConfigurator.configure();//for logger

        /*constructor*/
        PubSub my = new PubSub(host);

        //create
        System.out.println(my.create("ps", "topic1", 40).getCode().name());
        System.out.println(my.create("ps", "topic3", 0).getResponseText());
        System.out.println(my.create("ps/topic1", "topic4", 0).isSuccess());


        //Discover
        System.out.println(my.discover().getResponseText());

        //create again
        System.out.println(my.create("ps", "topic2", 40).getResponseText());
        System.out.println(my.create("ps/topic1","topic5", 40).getCode().name());

        System.out.println("Discover");

        //Discover with query
        System.out.println(my.discover("rt=core.ps").getResponseText());
        System.out.println(my.discover( "ct=40").getResponseText());



        //Publish
        System.out.println(my.publish("ps/topic3", "Hello", 0).getCode().name());

        //Read
        System.out.println(my.read("ps/topic3").getResponseText());

        //Remove
        System.out.println(my.remove("ps/topic1/topic5").getCode().name());

        System.out.println(my.discover().getResponseText());

//        /*Subscribe/Unsub*/
//
//        //create listener
//        SubscribeListener listener = new SubscribeListener() {
//            @Override
//            public void onResponse(String responseText) {
//                System.out.println("topic3: " + responseText);
//            }
//
//            @Override
//            public void onError() {
//                System.out.println("ERROR");
//            }
//        };
//
//        //subscription constructor
//        PubSub.Subscription subscription = my.new Subscription("ps/topic1/topic4", listener);
//        //subscribe
//        subscription.subscribe();
//
//        //wait 15 seconds
//        Thread.sleep(15000);
//
//        //unsubscribe
//        subscription.unsubscribe();
    }
}
