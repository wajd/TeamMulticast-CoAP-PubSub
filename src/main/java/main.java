import org.eclipse.californium.core.coap.CoAP;

public class main {

    public static void main(String[] args) throws InterruptedException {

        Code code = new Code();
        System.out.println("Create topic1 in ps: ");
        PubSub.create("127.0.0.1", 5683, code, new Topic("topic1", 40));
        System.out.println(code.toString());

//        System.out.println();
//        System.out.println("Discover topics: ");
//        Topic[] topics = PubSub.discover("127.0.0.1", 5683, 5000, "/.well-known/core");
//
//        System.out.println();
//        System.out.println("FOUND " + topics.length + " TOPICS:");
//        for (int i = 0; i < topics.length; i++) {
//            System.out.println();
//            System.out.println(topics[i].toString());
//            System.out.println("Name:           " + topics[i].getName());
//            System.out.println("Path:           " + topics[i].getPathString());
//            System.out.println("Content type:   " + topics[i].getCt());
//        }
//        System.out.println();
//
//        System.out.println("Create topic2 and topic3 inside topic 1");
//
//        Topic topic2 = new Topic("topic2", 0);
//        Topic topic3 = new Topic("topic3", 0);
//
//        PubSub.create("127.0.0.1", 5683, topics[0], topic2);
//        PubSub.create("127.0.0.1", 5683, topics[0], topic3);
//
////        PubSub.fakeSubscribe("127.0.0.1", 5683, "ps/topic1/topic2");
//
//        System.out.println("Discover: ");
//        topics = PubSub.discover("127.0.0.1", 5683, 5000, "/.well-known/core");
//
//        System.out.println();
//        System.out.println("FOUND " + topics.length + " TOPICS:");
//        for (int i = 0; i < topics.length; i++) {
//            System.out.println();
//            System.out.println(topics[i].toString());
//            System.out.println("Name:           " + topics[i].getName());
//            System.out.println("Path:           " + topics[i].getPathString());
//            System.out.println("Content type:   " + topics[i].getCt());
//        }
//        System.out.println();
//
//
//        System.out.println("We publish a message: hello");
//        CoAP.ResponseCode resp = PubSub.publish("127.0.0.1", 5683, topics[0], "hello");
//        System.out.println(resp.toString());
//        if (resp == CoAP.ResponseCode.CHANGED) {
//            System.out.println("CHANGED");
//        }
//        System.out.println();
//
//        System.out.println("We read a message from the topic: ");
//        String s = PubSub.read("127.0.0.1", 5683, topics[0]);
//        System.out.println(s);
//        System.out.println();
//
//        System.out.println("We delete " + topics[0].getName() + ": ");
//        CoAP.ResponseCode r = PubSub.remove("127.0.0.1", 5683, topics[0]);
//        if (r == CoAP.ResponseCode.DELETED) {
//            System.out.println("REMOVED");
//        }
//        System.out.println();
//
//        System.out.println("We discover again:");
//        topics = PubSub.discover("127.0.0.1", 5683, 5000, "/.well-known/core");
//        System.out.println("FOUND " + topics.length + " TOPICS:");
//        for (int i = 0; i < topics.length; i++) {
//            System.out.println();
//            System.out.println(topics[i].toString());
//            System.out.println("Name:           " + topics[i].getName());
//            System.out.println("Path:           " + topics[i].getPathString());
//            System.out.println("Content type:   " + topics[i].getCt());
//        }
    }
}
