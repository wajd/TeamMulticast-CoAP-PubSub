# TeamMulticast-CoAP-PubSub
This is the repository for the Multicast team's project for the course II1305 at KTH.


The goal of the project is to implement the IETS's RFC for CoAP publish-subscribe possibly as an API/library for general use, and then build a front-end client app to show live data published from a network of sensors through a broker to subscribed clients using the aforementioned API. The project is commissioned by the KTH Network Systems Laboratory. You can read more about it here: https://www.kth.se/cos/research/nslab

You can follow along our Project progress here: https://sites.google.com/view/team-multicast/home

This project was moved to a new repository: https://github.com/wajd/TeamMulticast-CoAP-PubSub 












Git instructions for team:

- When working on a story/task, make sure you're on the master branch first, then:
```
git pull origin master
git checkout -b [name of task] 
```

- When the task is COMPLETELY done and you want to merge it into master, make sure you're on your branch, then:
```
git rebase origin/master
```
Make sure the code in your branch compiles/tests/works then do a pull request.
