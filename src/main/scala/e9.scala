object e9 extends App {
  for(
    a <- 0 to 1000;
    b <- a to 1000;
    c <- b to 1000 if( a+b+c == 1000 && (a*a + b*b == c*c))
  ) {
		println (a,b,c, a*b*c)
  }
}
