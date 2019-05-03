

public class main {

    public static void main(String[] args) {
        String response = PubSub.discover("127.0.0.1", 5683);
        System.out.println(response);
    }
}