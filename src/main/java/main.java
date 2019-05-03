public class main {

    public static void main(String[] args) {

       /* PubSub.discover("127.0.0.1", 5683, 5000,"/.well-known/core");

        PubSub.create("127.0.0.1",5683,5000,"ps","topic1",40);

        PubSub.discover("127.0.0.1", 5683, 5000,"/.well-known/core");

        PubSub.create("127.0.0.1",5683,5000,"ps/topic1","topic2",0);

        PubSub.create("127.0.0.1",5683,5000,"ps/topic1","topic3",0);

        PubSub.discover("127.0.0.1", 5683, 5000,"/.well-known/core");

        PubSub.publish("127.0.0.1", 5683, "ps/topic1/topic2", "hello",0);

        PubSub.read("127.0.0.1", 5683, "ps/topic1/topic2");

        PubSub.remove("127.0.0.1", 5683, "ps/topic1/topic2");

        PubSub.discover("127.0.0.1", 5683, 5000,"/.well-known/core"); */

        /*PubSub.create("127.0.0.1",5683,5000,"ps","topic1",40);
        PubSub.create("127.0.0.1",5683,5000,"ps/topic1","topic2",0); */

       PubSub.subscribe("127.0.0.1", 5683, "ps/topic1/topic2");

    }
}
