package ch16

object ex04 extends App {
  val xml = <html>
              <head>
                <title> My list of jokes </title>
              </head>
              <body>
                <h1>This is my list of jokes </h1>
                <ul>
                  <li>Classic jokes </li>
                  <li>Math jokes </li>
                  <li>Food jokes</li>
                </ul>
                <hr/>
                <ol>
                  <li>Crazy jokes</li>
                  <li>Ethnic jokes</li>
                  <li>Golf jokes</li>
                </ol>
                <hr/>
                <img src="image1.jpg" alt="my image" height="100" width="100"/>
                <img src="image2.jpg" height="200" width="200"/>
                <img src="image3.jpg" alt="TODO" height="100" width="100"/>
                <img src="image4.jpg" height="200" width="200"/>
              </body>
            </html>

  val f = (xml \\ "img") filter (x => x match {
    case n @ <img/> => {
      val a = n.attribute("alt")
      a match {
        case Some(p) => p.text.isEmpty()
        case _ => true
      }
    }
  })

  f foreach println
}