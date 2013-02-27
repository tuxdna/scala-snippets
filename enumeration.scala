object Breed extends Enumeration {
  val dobernam = Value("Doberman Pinscher")
  val yorkie = Value("Yorkshire Terrier")
  val scottie = Value("Scottish Terrier")
  val dane = Value("Great Dane")
  val portie = Value("Portugese Water Dog")
}

println("ID\tbreed")

for(breed <- Breed.values) println(breed.id + "\t" + breed)

println("ID\tbreed")

Breed.values.filter( _.toString.endsWith("Terrier")).foreach(println)
