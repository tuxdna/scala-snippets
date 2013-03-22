
type UnaryOp[T, R] = T => R

def run[R]( gen: UnaryOp[Int,R] ) {
  for(x <- 1 to 5) println(gen(x))
}

run( x => -x )
run( x => Array.fill(x)("*").mkString )
