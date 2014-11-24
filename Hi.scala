object Hi {
    def main(args: Array[String]) {
         println("Hi!")

         val numList = List(1,2,3,4,5,6,7,8,9,10)
         var retVal = for { a <- numList
                            if a != 3; if a < 8
            } yield a
         for (b <- retVal) {
            println( "Value of a = " + b);
         }

         println (" adding " + addInt(5, 7))

    }

    def addInt( a:Int, b:Int) : Int = {
        var sum = 0
        sum = a + b
        return sum
    }
}
