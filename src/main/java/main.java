public class main {

    public static void main(String[] args) {

        PubSub.discover("127.0.0.1/.well-known/core", 5683, 5000);
        /*CoapClient client = new CoapClient("coap://127.0.0.1/.well-known/core:5683");

        System.out.println("SYNCHRONOUS");

        // synchronous
        String content1 = client.get().getResponseText();
        System.out.println("RESPONSE 1: " + content1);

        CoapResponse resp2 = client.post("payload", MediaTypeRegistry.TEXT_PLAIN);
        System.out.println("RESPONSE 2 CODE: " + resp2.getCode());

        // asynchronous

        System.out.println("ASYNCHRONOUS (press enter to continue)");

        client.get(new CoapHandler() {
            @Override public void onLoad(CoapResponse response) {
                String content = response.getResponseText();
                System.out.println("RESPONSE 3: " + content);
            }

            @Override public void onError() {
                System.err.println("FAILED");
            }
        });

        // wait for user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try { br.readLine(); } catch (IOException e) { }

        // observe

        System.out.println("OBSERVE (press enter to exit)");

        CoapObserveRelation relation = client.observe(
                new CoapHandler() {
                    @Override public void onLoad(CoapResponse response) {
                        String content = response.getResponseText();
                        System.out.println("NOTIFICATION: " + content);
                    }

                    @Override public void onError() {
                        System.err.println("OBSERVING FAILED (press enter to exit)");
                    }
                });

        // wait for user
        try { br.readLine(); } catch (IOException e) { }

        System.out.println("CANCELLATION");

        relation.proactiveCancel(); */
    }
}
