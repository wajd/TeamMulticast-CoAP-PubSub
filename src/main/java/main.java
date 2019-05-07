import org.eclipse.californium.core.coap.CoAP;

public class main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Discover topics: ");
        Topic[] topics = PubSub.discover("127.0.0.1", 5683, 5000, "/.well-known/core");

        System.out.println();
        System.out.println("FOUND " + topics.length + " TOPICS:");
        for (int i = 0; i < topics.length; i++) {
            System.out.println();
            System.out.println(topics[i].makeURI());
            System.out.println("Name:           " + topics[i].getName());
            System.out.println("Path:           " + topics[i].getPath());
            System.out.println("Content type:   " + topics[i].getCt());
        }
        System.out.println();


        System.out.println("Create topic1 in ps: ");
        CoAP.ResponseCode code = PubSub.create("127.0.0.1", 5683, "ps", new Topic("topic1", 40));
        if (code == CoAP.ResponseCode.CREATED) {
            System.out.println("CREATED");
        }
        System.out.println();

        System.out.println("Create topic2 and topic3 inside topic 1");

        PubSub.discover(host,port, timeout, ".well-known/core");

        PubSub.create("127.0.0.1", 5683, "ps/topic1", new Topic("topic3", 0));


        System.out.println("Discover: ");
        topics = PubSub.discover("127.0.0.1", 5683, 5000, "/.well-known/core");

        System.out.println();
        System.out.println("FOUND " + topics.length + " TOPICS:");
        for (int i = 0; i < topics.length; i++) {
            System.out.println();
            System.out.println(topics[i].makeURI());
            System.out.println("Name:           " + topics[i].getName());
            System.out.println("Path:           " + topics[i].getPath());
            System.out.println("Content type:   " + topics[i].getCt());
        }
        System.out.println();


        System.out.println("We publish a message: hello");
        CoAP.ResponseCode resp = PubSub.publish("127.0.0.1", 5683, topics[0], "hello");
        System.out.println(resp.toString());
        if (resp == CoAP.ResponseCode.CHANGED) {
            System.out.println("CHANGED");
        }
        System.out.println();

        System.out.println("We read a message from the topic: ");
        String s = PubSub.read("127.0.0.1", 5683, topics[0]);
        System.out.println(s);
        System.out.println();

        System.out.println("We delete topic2: ");
        CoAP.ResponseCode r = PubSub.remove("127.0.0.1", 5683, topics[0]);
        if (r == CoAP.ResponseCode.DELETED) {
            System.out.println("REMOVED");
        }
        System.out.println();

        System.out.println("We discover again:");
        topics = PubSub.discover("127.0.0.1", 5683, 5000, "/.well-known/core");
        System.out.println("FOUND " + topics.length + " TOPICS:");
        for (int i = 0; i < topics.length; i++) {
            System.out.println();
            System.out.println(topics[i].makeURI());
            System.out.println("Name:           " + topics[i].getName());
            System.out.println("Path:           " + topics[i].getPath());
            System.out.println("Content type:   " + topics[i].getCt());
        }

    }
}
