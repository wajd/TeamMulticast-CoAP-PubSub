import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Token;
import org.eclipse.californium.core.network.RandomTokenGenerator;
import org.eclipse.californium.core.network.config.NetworkConfig;

public class PubSub {

    private static final String SCHEME = "coap";
    private String host;
    private int port;
    private long timeout;
    private NetworkConfig config = NetworkConfig.createStandardWithoutFile();

    /**
     * Creates an instance of PubSub with the port set to 5683 (CoAP default port) and timeout 5000 milliseconds
     * @param host ip address of the broker
     */
    public PubSub(String host) {
        this.host = host;
        this.port = 5683;
        this.timeout = 5000;
    }

    /**
     * Creates an instance of PubSub with specified parameters
     * @param host ip address of the broker as a String
     * @param port number of the broker
     * @param timeout time the client waits for response (timeout = 0 -> waits indefinitely)
     */
    public PubSub(String host, int port, long timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    /**
     * @return an empty network configuration of the PubSub instance
     * which can be changed and then set with the setter function
     */
    public NetworkConfig getConfig() {
        return config;
    }

    /**
     * Sets the network configuration of the PubSub instance
     * @param config
     */
    public void setConfig(NetworkConfig config) {
        this.config = config;
    }

    /**
     * @return port number
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Sets port number
     * @param port number of the broker
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return host ip as a String
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets the host of the PubSub instance
     * @param host ip address of the broker as a String
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return timeout - time the client waits for response
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * Sets the timeout of the PubSub instance
     * Setting this property to 0 will result in methods waiting infinitely
     * @param timeout time the client waits for response
     */
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    /**
     * Sends a synchronous GET request to the broker without a query
     * @return CoapResponse which contains all the topics from the broker
     * @throws RuntimeException when the request times out
     */
    public CoapResponse discover() throws  RuntimeException {
        return discover("");
    }

    /**
     * Sends a synchronous GET request to the broker with a specified
     * To discover whether the broker supports CoAP PubSub protocol "rt=core.ps" query can be sent
     * @param query String e.g. ct=40
     * @return CoapResponse which contains the topics with the attributes specified by the query
     * @throws RuntimeException when the request times out
     */
    public CoapResponse discover(String query) throws RuntimeException {
        Request discover = Request.newGet();
        discover.getOptions().setUriPath(".well-known/core?" + query);

        CoapClient client = new CoapClient(SCHEME, this.getHost(), this.getPort());
        client.setTimeout(this.timeout);

        CoapResponse response = client.advanced(discover);

        return response;
    }

    /**
     * Sends a synchronous POST request to the broker which creates a topic at the broker
     * The topic has to be specified by name, ct and path uri
     * @param name String is the name of the topic
     * @param ct int is the content type of the topic (ct=40 for parent folder, ct=0 for plain text)
     * @param uri String or String[] is the path where the topic should be created (e.g. ps/t1/t2 or {[ps],[t1],[t2]})
     * @return CoapResponse which contains the broker's response to our request i.e. response code,...
     * @throws RuntimeException when the request times out
     */
    public CoapResponse create(String name, int ct, String... uri) throws RuntimeException {

        CoapClient client = new CoapClient(SCHEME, this.getHost(), this.getPort(), uri);
        client.setTimeout(this.timeout);

        StringBuilder sb = new StringBuilder().append("<").append(name).append(">;ct=").append(ct);
        String payload = sb.toString();

        Request req = Request.newPost();
        req.setPayload(payload);
        req.getOptions().setContentFormat(ct);

        CoapResponse res = client.advanced(req);

        return res;
    }

    /**
     * Sends a synchronous PUT request to the broker which publishes data to a topic
     * The topic is specified by path uri and ct
     * ct of the topic and the request has to match for data to be published
     * @param payload String is data to be published
     * @param ct int is the content type of the data (has to match ct of the topic)
     * @param uri String or String[] is the path of the topic to which data should be published
     * @return CoapResponse which contains the broker's response to our request i.e. response code,...
     * @throws RuntimeException when the request times out
     */
    public CoapResponse publish(String payload, int ct, String... uri) throws RuntimeException {
        CoapClient client = new CoapClient(SCHEME, this.getHost(), this.getPort(), uri);
        client.setTimeout(this.timeout);

        CoapResponse res = client.put(payload, ct);

        return res;
    }

    /**
     * Sends a synchronous GET request to the broker which retrieves data from the topic
     * @param uri String or String[] is the path of the topic from which the data should be read
     * @return CoapResponse which contains the broker's response to our request i.e. content, response code...
     * @throws RuntimeException when the request times out
     */
    public CoapResponse read(String... uri) throws RuntimeException {
        CoapClient client = new CoapClient(SCHEME, this.getHost(), this.getPort(), uri);
        client.setTimeout(this.timeout);

        CoapResponse res = client.get();

        return res;
    }

    /**
     * Sends a synchronous DELETE request to the broker which removes the specified topic from the broker
     * If the topic is a parent topic, the broker removes all of its children
     * @param uri String or String[] is the path of the topic which should be removed
     * @throws RuntimeException when the request times out
     */
    public CoapResponse remove(String... uri) throws RuntimeException {

        CoapClient client = new CoapClient(SCHEME, this.getHost(), this.getPort(), uri);
        client.setTimeout(this.timeout);
        CoapResponse res = client.delete();

        return res;
    }

    public class Subscription {
        private CoapClient client;
        private CoapObserveRelation relation;
        private String[] uri;
        private CoapHandler handler;

        //Constructor, does not subscribe
        public Subscription(CoapHandler handler, String... uri) {
            this.uri = uri;
            this.handler = handler;
            this.relation = null;
            this.client = null;
        }

        public CoapHandler getHandler() {
            return handler;
        }

        public void setHandler(CoapHandler handler) {
            this.handler = handler;
        }

        //call this method to subscribe, can use it to subscribe to same topic again
        public void subscribe() throws RuntimeException {

            Request req = new Request(CoAP.Code.GET);

            client = new CoapClient(SCHEME, getHost(), getPort(), uri);
            client.useExecutor();
            client.setTimeout(timeout);

            req.setURI(client.getURI());
            req.setObserve();

            config.set(NetworkConfig.Keys.TOKEN_SIZE_LIMIT, 4);
            RandomTokenGenerator rand = new RandomTokenGenerator(config);
            Token token = rand.createToken(false);
            req.setToken(token);

            relation = client.observe(req, handler);
        }

        //call to unsubscribe
        public void unsubscribe() {
            if (this.relation != null) {
                relation.proactiveCancel();
                int mid = relation.getCurrent().advanced().getMID();
                while (relation.getCurrent().advanced().getMID() == mid) ;
            }
            if (this.client != null)
                client.shutdown();
        }
    }
}
