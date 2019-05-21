# TeamMulticast-CoAP-PubSub
This is the repository for the Multicast team's project for the course II1305 at KTH.

The goal of the project is to implement the IETS's RFC for CoAP publish-subscribe as an API/library for general use alongside Californium, and then build a front-end client Android app to show live data published from a network of sensors through a broker to subscribed clients using the aforementioned API. 
The project is commissioned by the KTH Network Systems Laboratory. You can read more about it here: https://www.kth.se/cos/research/nslab

You can take a look at the IETS's RFC for CoAP publish-subscribe draft here: https://www.ietf.org/id/draft-ietf-core-coap-pubsub-08.txt

You can follow along our Project progress here: https://sites.google.com/view/team-multicast/home

You can see the repository for the Android app here: https://github.com/ihilal/Noisy

## Available functionality:
- Discover: allows you to find a list of available topics on the broker
    - You may provide a query to limit the list you get back
    - If ```rt=core.ps``` is provided as a query, you can check whether the broker supports CoAP Publish-Subscribe
- Create: allows you to create a new topic on the broker with the given uri and name and content type
- Publish: allows you to publish content to the given uri of a topic on the broker
- Read: allows you to read the content on the specified topic uri
- Remove: allows you to remove a topic on the broker
- Subscription: given a topic uri and a listener (CoapHandler), it allows you to:
    - Subscribe to the given topic
    - Unsubscribe from the given topic

## Installation:
- First, add Californium dependencies to your project: https://github.com/eclipse/californium/tree/2.0.0-M14
    - Should use the M14 tag
- Then:
    - follow instructions on https://jitpack.io/#wajd/TeamMulticast-CoAP-PubSub and use the Tag 1.0 for your build system
    - Alternatively, you may compile it to a Jar file yourself and use that 
    - Or copy paste the project into your project directory

## Support: 
Open an issue here on GitHub

## Contributing:
- Open to contribution but contributions will be reviewed
- Discuss with wajd@kth.se

## Authors and Acknowledgements:
- Petr Kocián
- Ahmad Hussain
- Ismail Hilal
- Matias Carlander-Reuterfelt Gallo
- Wajd Tohme
- Zainab Al-Saadi
- Tuna Gersil

## License: 
GNU GPLv3 license

## Project Status:
This project was developed for the course II1305 at KTH and the course is over, so the project will likely not develop further but you are welcome to contribute