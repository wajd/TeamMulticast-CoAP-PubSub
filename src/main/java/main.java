
public class main {

    public static void main(String[] args) throws  RuntimeException{

        String host = "";
//        BasicConfigurator.configure();//for logger

        /*constructor*/
        PubSub my = new PubSub(host);

        //Discover rt core
        System.out.println("Discover core.ps");
        System.out.println(my.discover("rt=core.ps").getResponseText());
    }
}
