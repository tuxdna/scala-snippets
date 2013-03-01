// 5. Make a class Student with read-write JavaBeans properties name (of type String)
// and id (of type Long). What methods are generated? (Use javap to check.) Can
// you call the JavaBeans getters and setters in Scala? Should you?

import scala.reflect.BeanProperty
object ex05 extends App {
  class Student {
    @BeanProperty var name: String = _
    @BeanProperty var id: Long = _
  }
}

// OUTPUT:
// $ javap -private ./'ex05$Student'
// Compiled from "ex05.scala"
// public class ex05$Student extends java.lang.Object implements scala.ScalaObject{
//     private java.lang.String name;
//     private long id;
//     public java.lang.String name();
//     public void name_$eq(java.lang.String);
//     public void setName(java.lang.String);
//     public long id();
//     public void id_$eq(long);
//     public void setId(long);
//     public long getId();
//     public java.lang.String getName();
//     public ex05$Student();
// }
