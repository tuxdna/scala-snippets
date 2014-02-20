package ch16

object ex06 extends App {

  val xml = <html>
              <head>
                <title> My list of jokes </title>
              </head>
              <body>
                <div class="otherprojects">
                  <div class="otherprojects-item">
                    <a href="http://www.wiktionary.org/">
                      <span class="icon">
                        <img src="//upload.wikimedia.org/wikipedia/meta/3/3b/Wiktionary-logo_sister_1x.png" width="35" height="35" alt=""/>
                      </span>
                      Wiktionary
                    </a>
                  </div>
                  <div class="otherprojects-item">
                    <a href="http://www.wikivoyage.org/">
                      <span class="icon">
                        <img src="//upload.wikimedia.org/wikipedia/meta/7/74/Wikivoyage-logo_sister_1x.png" width="35" height="35" alt=""/>
                      </span>
                      Wikivoyage
                    </a>
                  </div>
                  <div class="otherprojects-item">
                    <a href="http://commons.wikimedia.org/">
                      <span class="icon">
                        <img src="//upload.wikimedia.org/wikipedia/meta/9/90/Commons-logo_sister_1x.png" width="35" height="47" alt="" style="vertical-align: top;"/>
                      </span>
                      Commons
                    </a>
                  </div>
                </div>
              </body>
            </html>

  val f = (xml \\ "a") map { x => (x.text.trim(), x.attribute("href").get) } foreach (a => println(s"${a._1} => ${a._2}"))

}
