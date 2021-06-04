// Java program to implement Max Heap
class MaxHeap01(private val maxsize: Int) {
    private val Heap: IntArray
    private var size = 0

    // Returns position of parent
    private fun parent(pos: Int): Int {
        return pos / 2
    }

    // Below two functions return left and
    // right children.
    private fun leftChild(pos: Int): Int {
        return 2 * pos
    }

    private fun rightChild(pos: Int): Int {
        return 2 * pos + 1
    }

    // Returns true of given node is leaf
    private fun isLeaf(pos: Int): Boolean {
        return if (pos > size / 2 && pos <= size) {
            true
        } else false
    }

    private fun swap(fpos: Int, spos: Int) {
        val tmp: Int
        tmp = Heap[fpos]
        Heap[fpos] = Heap[spos]
        Heap[spos] = tmp
    }

    // A recursive function to max heapify the given
    // subtree. This function assumes that the left and
    // right subtrees are already heapified, we only need
    // to fix the root.
    private fun maxHeapify(pos: Int) {
        if (isLeaf(pos)) return
        if (Heap[pos] < Heap[leftChild(pos)]
                || Heap[pos] < Heap[rightChild(pos)]) {
            if (Heap[leftChild(pos)]
                    > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos))
                maxHeapify(leftChild(pos))
            } else {
                swap(pos, rightChild(pos))
                maxHeapify(rightChild(pos))
            }
        }
    }

    // Inserts a new element to max heap
    fun insert(element: Int) {
        Heap[++size] = element

        // Traverse up and fix violated property
        var current = size
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current))
            current = parent(current)
        }
    }

    fun print() {
        for (i in 1..size / 2) {
            print(
                    " PARENT : " + Heap[i]
                            + " LEFT CHILD : " + Heap[2 * i]
                            + " RIGHT CHILD :" + Heap[2 * i + 1])
            println()
        }
    }

    // Remove an element from max heap
    fun extractMax(): Int {
        val popped = Heap[1]
        Heap[1] = Heap[size--]
        maxHeapify(1)
        return popped
    }

    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity.
    init {
        Heap = IntArray(maxsize + 1)
        Heap[0] = Int.MAX_VALUE
    }
}