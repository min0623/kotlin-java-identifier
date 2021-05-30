class Stack<T> {
    private val capacity = 10
    private var pointer = 0
    private val objects = arrayOfNulls<Any>(capacity) as Array<T?>
    fun push(o: T) {
        require(pointer < capacity) { "Stack exceeded capacity!" }
        objects[pointer++] = o
    }

    fun pop(): T? {
        require(pointer > 0) { "Stack empty" }
        return objects[--pointer]
    }

    val isEmpty: Boolean
        get() = pointer <= 1
}
