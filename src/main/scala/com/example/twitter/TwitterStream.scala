//package com.example.twitter
//
//import twitter4j._
//
//object Util {
//
//  val consumerKey = "55zpnEqQyYVp6bni4VBQyTOgU"
//  val consumerSecret = "yKV0hIYSCI15ZtY9DuTtuiqUqbtCjQGf13QjNpThdXpgVM5UUV"
//  val accessToken = "96958743-eLHT8WTRIfSrPVMv18DBjPI8D0VNNCWNm8FOWf6TQ"
//  val accessTokenSecret = "zWLuVtIsxmNp6udqAbKe7n0LJz1w94K4QuLZOiw91UBi3"
//
//  val config = new twitter4j.conf.ConfigurationBuilder()
//    .setOAuthConsumerKey(consumerKey)
//    .setOAuthConsumerSecret(consumerSecret)
//    .setOAuthAccessToken(accessToken)
//    .setOAuthAccessTokenSecret(accessTokenSecret)
//    .build
//
//  def simpleStatusListener = new StatusListener() {
//    def onStatus(status: Status) { println(status.getText) }
//    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
//    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
//    def onException(ex: Exception) { ex.printStackTrace }
//    def onScrubGeo(arg0: Long, arg1: Long) {}
//    def onStallWarning(warning: StallWarning) {}
//  }
//}
//
//object StatusStreamer {
//  def main(args: Array[String]) {
//    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
//    twitterStream.addListener(Util.simpleStatusListener)
//    twitterStream.sample
//    Thread.sleep(2000)
//    twitterStream.cleanUp
//    twitterStream.shutdown
//  }
//}
//
//object FollowIdsStreamer {
//  def main(args: Array[String]) {
//    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
//    twitterStream.addListener(Util.simpleStatusListener)
//    twitterStream.filter(new FilterQuery(Array(1344951,5988062,807095,3108351)))
//    Thread.sleep(10000)
//    twitterStream.cleanUp
//    twitterStream.shutdown
//  }
//}
//
//
//object SearchStreamer {
//  def main(args: Array[String]) {
//    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
//    twitterStream.addListener(Util.simpleStatusListener)
//    twitterStream.filter(new FilterQuery().track(args))
//    Thread.sleep(10000)
//    twitterStream.cleanUp
//    twitterStream.shutdown
//  }
//}
//
//object AustinStreamer {
//  def main(args: Array[String]) {
//    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
//    twitterStream.addListener(Util.simpleStatusListener)
//    val austinBox = Array(Array(-97.8,30.25),Array(-97.65,30.35))
//    twitterStream.filter(new FilterQuery().locations(austinBox))
//    Thread.sleep(10000)
//    twitterStream.cleanUp
//    twitterStream.shutdown
//  }
//}
//
//object LocationStreamer {
//  def main(args: Array[String]) {
//    val boundingBoxes = args.map(_.toDouble).grouped(2).toArray
//    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
//    twitterStream.addListener(Util.simpleStatusListener)
//    twitterStream.filter(new FilterQuery().locations(boundingBoxes))
//    Thread.sleep(10000)
//    twitterStream.cleanUp
//    twitterStream.shutdown
//  }
//}
//
//
//
//
//object TwitterStream {
//
//}