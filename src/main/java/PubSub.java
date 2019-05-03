import org.eclipse.californium.core.CoapClient;

public class PubSub {

    public static String discover(String address, int port) {
        CoapClient client = new CoapClient(address + ":" + port + "/.well-known/core?rt=core.ps");

        return client.get().getResponseText();
    }
}
