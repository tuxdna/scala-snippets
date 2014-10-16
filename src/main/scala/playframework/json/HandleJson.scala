package playframework.json


import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.JsObject
import play.api.libs.json._

object HandleJson {
  val jstring = """
{  
   "responseHeader":{  },
   "response":{  
      "numFound":5,
      "start":0,
      "docs":[  
         {  
            "product_id":"11"
         },
         {  
            "product_id":"12"
         },
         {  
            "product_id":"13"
         },
         {  
            "product_id":"14"
         },
         {  
            "product_id":"15"
         }
      ]
   }
}
"""

  def main(args: Array[String]) {
    val json = Json.parse(jstring)
    println(json)
    val productToRemove: Set[Int] = Set(11, 14)
    val docs = (json \ "response" \ "docs").as[List[JsObject]]
    val d2 = docs.filter { x =>
      val y = x \ "product_id"
      productToRemove.contains(y.as[String].toInt)
    }

    val productJsonTransformer = (__ \ 'response \ 'docs).json.update(
      __.read[JsValue].map { o =>
        val elems =
          o.as[List[JsObject]].filter { x =>
            val y = x \ "product_id"
            !productToRemove.contains(y.as[String].toInt)
          }
        Json.toJson(elems)
      })

    println(Json.prettyPrint(json.transform(productJsonTransformer).get))

  }
}