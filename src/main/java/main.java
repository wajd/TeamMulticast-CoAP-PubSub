import org.eclipse.californium.core.WebLink;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Set;

public class main {

    public static void main(String[] args) throws  RuntimeException, IOException, InterruptedException {

        String host = "130.229.148.241";
//        BasicConfigurator.configure();//for logger

        /*constructor*/
        PubSub my = new PubSub(host);

//        //Discover rt core
//        System.out.println("Discover core.ps");
//        System.out.println(my.discover("rt=core.ps").getResponseText());

        //Create
        System.out.println("Create topics");
        System.out.println(my.create("ps", "topic1", 40).getResponseText());
        System.out.println(my.create("ps", "topic2", 0).getResponseText());
        System.out.println(my.create("ps", "topic3", 40).getResponseText());
        System.out.println(my.create("ps/topic1", "topic4", 0).getResponseText());
        System.out.println(my.create("ps/topic1","topic5", 40).getResponseText());

//        //Discover with query
//        System.out.println("Discover w query ct=40");
//        System.out.println(Converter.getWebLinks(my.discover("ct=40")));

        //Discover
        System.out.println("Discover");

        Set<WebLink> links = Converter.getWebLinks(my.discover());
        System.out.println(links.toString());

        System.out.println("weblinks getarray");
        WebLink[] weblinkArray = Converter.getArray(links);

        for(int i=0; i<weblinkArray.length;i++){
            System.out.println(weblinkArray[i]);
        }

        System.out.println("Extract all topics");
        Set<WebLink> allWebLinks = Converter.extractAllTopics(links);
        for (WebLink w: allWebLinks)
            System.out.println(w.toString());

        System.out.println("Make a weblink");
        WebLink webLink = Converter.makeWebLink("ps/topic1", 0);
        System.out.println(webLink.toString());
        System.out.println(webLink.getURI());
        System.out.println(Converter.getCT(webLink));
        System.out.println(Converter.getCTString(Converter.getCT(webLink)));
        System.out.println("change CT");
        Converter.setCT(webLink, 40);
        System.out.println(Converter.getCT(webLink));
        System.out.println("get name");
        System.out.println(Converter.getName(webLink));
        System.out.println("get parent uri");
        System.out.println(Converter.getParentUri(webLink));
        System.out.println("get parent weblink");
        System.out.println(Converter.getParent(webLink).toString());

        System.out.println("get Sub topics of topic1");
        Set<WebLink> subtopics = Converter.getSubTopics(webLink, allWebLinks);
        for (WebLink w: subtopics)
            System.out.println(w.toString());

        System.out.println("TTEEFGGFDF");
        System.out.println(Converter.getUri(weblinkArray[0]));


//        //Publish
//        System.out.println("Publish");
//        System.out.println(my.publish("ps/topic3", "Hello", 0).getCode().name());
//
//        //Read
//        System.out.println("Read");
//        System.out.println(my.read("ps/topic3").getResponseText());
//
//        //Remove
//        System.out.println("Remove");
//        System.out.println(my.remove("ps/topic1/topic5").getCode().name());
//
//        /*Subscribe/Unsub*/
//
//        //Create handler
//        CoapHandler handler = new CoapHandler() {
//            @Override
//            public void onLoad(CoapResponse coapResponse) {
//                System.out.println("topic4: " + coapResponse.getResponseText());
//            }
//
//            @Override
//            public void onError() {
//                System.out.println("ERROR");
//            }
//        };
//
//        //subscription constructor
//        PubSub.Subscription subscription = my.new Subscription("ps/topic1/topic4", handler);
//        //subscribe
//        subscription.subscribe();
//
//        //wait 15 seconds
//        Thread.sleep(15000);
//
//        //unsubscribe
//        subscription.unsubscribe();
//
//        subscription.setHandler(new CoapHandler() {
//            @Override
//            public void onLoad(CoapResponse coapResponse) {
//                System.out.println("Different handler: " + coapResponse.getResponseText());
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });
//
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
