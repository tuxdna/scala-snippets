object observers {
  //
  // References:
  // http://stackoverflow.com/questions/9247066/scala-how-to-work-with-long-type-parameter-lists 
  // http://www.scala-lang.org/old/node/129
  // https://blogs.atlassian.com/2013/01/covariance-and-contravariance-in-scala/

  trait Event[T <: Enumeration] {
    def getType(): T
    def getTimestamp(): Long;
    def toString(): String;
  }

  trait EventHandler[T <: Event[Enumeration]] {
    def handle(event: T)
  }

  type EventT = Event[Enumeration]
  type HandlerT = EventHandler[EventT]

  trait Dispatcher {
    def getEventHandler(): HandlerT
    def register(evenType: EventT, handler: HandlerT)
  }

  //  trait EventHandler[T <: Event] {
  //    def handle(event: T)
  //  }
  //  
  //trait Dispatcher[E]{ 
  // 
  //  def getEventHandler():EventHandler[Event[E]] 
  //   
  //  def register(evenType:Event[E], handler:EventHandler[E]) 
  //   
  //}

}
