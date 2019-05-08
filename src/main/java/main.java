import org.eclipse.californium.core.coap.CoAP;

public class main {

    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1";
        int port = 5683;
        long timeout = 5000;

        System.out.println("Discover topics: ");
        Topic[] topics = PubSub.discover(host, port, timeout, "/.well-known/core");

        System.out.println();
        printDiscover(topics);
        System.out.println();


        System.out.println("Create topic1 in ps: ");
        CoAP.ResponseCode code = PubSub.create(host, port, "ps/", new Topic("topic1", 40));
        if (code == CoAP.ResponseCode.CREATED) {
            System.out.println("CREATED");
        }
        System.out.println();

        System.out.println("Create topic2 and topic3 inside topic 1");

        PubSub.create(host, port, "ps/topic1", new Topic("topic2", 0));

        PubSub.create(host, port, "ps/topic1", new Topic("topic3", 0));


        System.out.println("Discover: ");
        topics = PubSub.discover(host, port, timeout,"/.well-known/core");

        System.out.println();
        printDiscover(topics);
        System.out.println();


        System.out.println("We publish a message: hello");
        CoAP.ResponseCode resp = PubSub.publish(host, port, topics[0], "hello");
        System.out.println(resp.toString());
        if (resp == CoAP.ResponseCode.CHANGED) {
            System.out.println("CHANGED");
        }
        System.out.println();

        System.out.println("We read a message from the topic: ");
        String s = PubSub.read(host, port, topics[0]);
        System.out.println(s);
        System.out.println();

        System.out.println("We delete topic2: ");
        CoAP.ResponseCode r = PubSub.remove(host, port, topics[0]);
        if (r == CoAP.ResponseCode.DELETED) {
            System.out.println("REMOVED");
        }
        System.out.println();

        System.out.println("We discover again:");
        topics = PubSub.discover(host, port, timeout, ".well-known/core");
        System.out.println();
        printDiscover(topics);
        System.out.println();

    }

    private static void printDiscover(Topic[] topics) {
        System.out.println("FOUND " + topics.length + " TOPICS:");
        for (Topic topic : topics) {
            System.out.println();
            System.out.println(topic.toString());
            System.out.println("Name:           " + topic.getName());
            System.out.println("Path:           " + topic.getPathAsString());
            System.out.println("Content type:   " + topic.getCt());
        }
    }
}
