import org.apache.log4j.BasicConfigurator;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;

public class main {

    public static void main(String[] args) throws  RuntimeException{

        String host = "127.0.0.1";
//        BasicConfigurator.configure();//for logger

        /*constructor*/
        PubSub my = new PubSub(host);

    }
}
