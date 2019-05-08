public class main {

    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1";
        int port = 5683;
        long timeout = 5000;

        PubSub.discover(host,port, timeout, ".well-known/core");

        PubSub.create(host, port, timeout, "ps/", "topic", 0);

        PubSub.discover(host,port, timeout, ".well-known/core");

        PubSub.publish(host, port, "ps/topic", "500", 0);

        PubSub.read(host, port, "ps/topic");

        PubSub.fakeSubscribe(host, port, "ps/topic");
    }
}
