public interface SubscribeListener {
    void onResponse(PubSubResponse pubSubResponse);

    void onError();
}
