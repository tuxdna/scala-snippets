
Actor

 * storage / state
 * computation
 * communication


Actors are composed into Actor Systems

Each actor has an address

Mailbox

Axioms

When an actor receives a message it could:

 * create more actors
 * send messages to actors it knows
 * designate how to handle next message
 

Conceptually Actor would process one message at a time


What happens if an Actor sends message to itself?

 * Notion of a Future.
 * Future avoids a deadlock, because there is nothing to wait on.


There is a many to many relation among actors and addresses

Message delivery gurantee is best effort. Typically atmost once.



References:

 * [What is Actor Model?](https://channel9.msdn.com/Shows/Going+Deep/Hewitt-Meijer-and-Szyperski-The-Actor-Model-everything-you-wanted-to-know-but-were-afraid-to-ask)

 * [Concurrency in Erlang vs Scala](https://rocketeer.be/articles/concurrency-in-erlang-scala/)

 * [Akka tutorials](http://rerun.me/2014/09/11/introducing-actors-akka-notes-part-1/)

 * [Managing Congested Actors](http://www.nurkiewicz.com/2013/07/managing-congested-actors-in-akka.html)

 * [Do's and Dont's When Deploying Akka in Production](http://boldradius.com/blog-post/U-jexSsAACwA_8nr/dos-and-donts-when-deploying-akka-in-production)

 * [An introduction to CRDTs](http://www.cakesolutions.net/teamblogs/how-to-build-a-distributed-counter)

