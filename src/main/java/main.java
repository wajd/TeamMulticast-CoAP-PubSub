import org.eclipse.californium.core.coap.CoAP;

public class main {

    public static void main(String[] args) throws InterruptedException {

        String host = "130.229.144.121";
        int port = 5683;
        Code code = new Code();

        System.out.println("Create topic1 in ps: ");
        PubSub.create(host, port, code, new Topic("topic1", 40));
        System.out.println(code.toString());

        System.out.println();
        System.out.println("Discover topics: ");
        Topic[] topics = PubSub.discover(host, port, code, 5000);
        System.out.println(code.toString());

        System.out.println("FOUND " + topics.length + " TOPICS:");
        for (int i = 0; i < topics.length; i++) {
            System.out.println();
            System.out.println(topics[i].toString());
            System.out.println("Name:           " + topics[i].getName());
            System.out.println("Path:           " + topics[i].getPathString());
            System.out.println("Content type:   " + topics[i].getCt());
        }
        System.out.println();

        System.out.println("Create topic2 and topic3 inside topic 1");

        Topic topic2 = new Topic("topic2", 0);
        Topic topic3 = new Topic("topic3", 0);

        PubSub.create(host, port, code, topics[0], topic2);
        System.out.println(code.toString());
        PubSub.create(host, port, code, topics[0], topic3);
        System.out.println(code.toString());

//        PubSub.fakeSubscribe("127.0.0.1", 5683, "ps/topic1/topic2");

        System.out.println("Discover: ");
        topics = PubSub.discover(host, port, code, 5000);
        System.out.println(code.toString());

        System.out.println("FOUND " + topics.length + " TOPICS:");
        for (int i = 0; i < topics.length; i++) {
            System.out.println();
            System.out.println(topics[i].toString());
            System.out.println("Name:           " + topics[i].getName());
            System.out.println("Path:           " + topics[i].getPathString());
            System.out.println("Content type:   " + topics[i].getCt());
        }
        System.out.println();


        System.out.println("We publish a message: hello");
        PubSub.publish(host, port, code, topics[0], "hello");
        if (code.response == CoAP.ResponseCode.CHANGED) {
            System.out.println(code.toString() + "CHANGED");
        }
        System.out.println();

        System.out.println("We read a message from the topic: ");
        String s = PubSub.read(host, port, code, topics[0]);
        System.out.println(code.toString());
        System.out.println(s);
        System.out.println();

        System.out.println("We delete " + topics[0].getName() + ": ");
        PubSub.remove(host, port, code, topics[0]);
        if (code.response == CoAP.ResponseCode.DELETED) {
            System.out.println(code.toString() + " REMOVED");
        }
        System.out.println();

        System.out.println("We discover again:");
        topics = PubSub.discover(host, port, code, 5000);
        System.out.println(code.toString());
        System.out.println("FOUND " + topics.length + " TOPICS:");
        for (int i = 0; i < topics.length; i++) {
            System.out.println();
            System.out.println(topics[i].toString());
            System.out.println("Name:           " + topics[i].getName());
            System.out.println("Path:           " + topics[i].getPathString());
            System.out.println("Content type:   " + topics[i].getCt());
        }
    }
}
