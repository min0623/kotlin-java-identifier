import Outer01.Inner01

internal class Outer01 {
    var num = 0

    // inner class
    private inner class Inner01 {
        fun print() {
            println("This is an inner class")
            println(num)
        }
    }

    // Accessing he inner class from the method within
    fun displayInner() {
        val inner = Inner01()
        inner.print()
    }

    fun increase() {
        num = num + 1
    }

    fun decrease() {
        num = num - 1
    }
}