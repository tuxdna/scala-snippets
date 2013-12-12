package ch16

object ex05 extends App {
  val xml = <html>
              <head>
                <title> My list of jokes </title>
              </head>
              <body>
                <h1>This is my list of jokes </h1>
                <hr/>
                <img alt="my image" height="100" width="100"/>
                <img src="image1.jpg" alt="my image" height="100" width="100"/>
                <img src="image2.jpg" height="200" width="200"/>
                <img src="image3.jpg" alt="TODO" height="100" width="100"/>
                <img src="image4.jpg" height="200" width="200"/>
              </body>
            </html>

  val f = (xml \\ "img") map { _.attribute("src") } filter {
    _ match {
      case Some(a) => true; case _ => false
    }
  } foreach (a => println(a.get))
}