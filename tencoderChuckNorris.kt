
fun main() {
    display()
}

fun how_muchInRange( chain : String ) : MutableList<MutableList<String>>  {
    var count  = 1
    var i = 1
    var search = chain[0]
    var sequenceInArray : MutableList<MutableList<String>> = mutableListOf()

    while(i < chain.length){
        if(search == chain[i]){
            count += 1
        }
        else {
            sequenceInArray.add(arrayListOf("$search","${count}"))
            search = chain[i]
            count = 1
        }
        i++
    }
    sequenceInArray.add(arrayListOf("$search","${count}"))
    return  sequenceInArray

}

fun encodeToChuckNorris(arr: MutableList<MutableList<String>> ) : String{
    var res : String = ""
    arr.forEach{
        var middleBit = if(it[0]== "1") "0" else "00"
        res += String.format("%s %s ",middleBit, "0".repeat(it[1].toInt()))
    }
    return res

}

fun decodeToBinary( str: String) : String{
    var binOfString = ""
    var newStr = str.trimEnd().split(" ").toMutableList()
    for(index in 1..newStr.lastIndex step 2){
        binOfString += if(newStr[index-1] == "0")  newStr[index].replace('0', '1') else newStr[index]
    }
    return  binOfString
}

fun decodToChar (str : String) : String {
    var result = ""
    for (i in 0..str.lastIndex step 7){
        val charString = str.substring(i,i +7)
        result += Integer.parseInt(charString, 2).toChar()
    }
    return result
}
fun encodeToBinary(str : String): String{
    var listOfBinary : MutableList <String> = mutableListOf()
    var stringBinair = ""
    for(i in str){
        val temp = Integer.toBinaryString(i.code)
        if ( i != '>') {
            stringBinair = "" + String.format("%07d", temp.toInt())
            listOfBinary.add(stringBinair)
        }
    }
    return  listOfBinary.joinToString("")
}
fun display(){

    var space = false
    do {
        if(space){
            println("Please input operation (encode/decode/exit):")
        }
        else{
            println()
            println("Please input operation (encode/decode/exit):")
        }
        var userAnswer = readln().replaceFirst("> ", "")
        when(userAnswer){
            "encode" ->{
                println("Input string:")
                val ansEncode = readln().replaceFirst("> ", "")
                println("Encoded string:")
                println(encodeToChuckNorris(how_muchInRange(encodeToBinary(ansEncode))))

            }
            "decode"->{
                println("Input encoded string:")
                val ansDecode = readln()!!
                val copyDecode = ansDecode.replaceFirst("> ", "")
                if(iSValidEncoded(copyDecode)){
                    println("Decoded string:")
                    println(decodToChar(decodeToBinary(copyDecode)))

                }
                else {println("Encoded string is not valid.")
                    space = true
                }
            }
            "exit"-> println("Bye!")
            else-> {
                println("There is no '$userAnswer' operation")
            }
        }
        println()
    }while(userAnswer != "exit")
}

fun iSValidEncoded ( str: String) : Boolean{
    try {
        if(decodeToBinary(str).length % 7 == 0 && isCorrectZero(str)){
            return true
        }
    }catch (e: StringIndexOutOfBoundsException){
        return false
    }
    return  false
}

fun isCorrectZero (str: String ) : Boolean{
    var newStr = str.trimEnd().split(" ").toMutableList()
    var isCorrect : Boolean = false
    var isIncorrect : Boolean = true
    for(index in 1..newStr.lastIndex step 2){
     if((newStr[index-1] == "0" || newStr[index-1] == "00" )&& newStr.size % 2 == 0 ){
          isCorrect = true
     }else{
        isIncorrect = false
     }
    }
    return  isCorrect && isIncorrect
}
