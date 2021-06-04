import java.util.Arrays
import java.util.Comparator

/**
 * Sorts and returns arrays in the fluent style.
 *
 * @since 3.12.0
 */
object ArraySorter02 {

    /**
     * Sorts and returns the given array.
     *
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
     */
    @JvmStatic
    fun sort(array: ByteArray?): ByteArray? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
     */
    @JvmStatic
    fun sort(array: CharArray?): CharArray? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
     */
    @JvmStatic
    fun sort(array: DoubleArray?): DoubleArray? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
     */
    @JvmStatic
    fun sort(array: FloatArray?): FloatArray? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
     */
    @JvmStatic
    fun sort(array: IntArray?): IntArray? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
     */
    @JvmStatic
    fun sort(array: LongArray?): LongArray? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
     */
    @JvmStatic
    fun sort(array: ShortArray?): ShortArray? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param <T> the array type.
     * @param array the array to sort.
     * @return the given array.
     * @see Arrays.sort
    </T> */
    @JvmStatic
    fun <T> sort(array: Array<T>?): Array<T>? {
        Arrays.sort(array)
        return array
    }

    /**
     * Sorts and returns the given array.
     *
     * @param <T> the array type.
     * @param array the array to sort.
     * @param comparator the comparator to determine the order of the array. A `null` value uses the elements'
     * [natural ordering][Comparable].
     * @return the given array.
     * @see Arrays.sort
    </T> */
    @JvmStatic
    fun <T> sort(array: Array<T>?, comparator: Comparator<in T>?): Array<T>? {
        Arrays.sort(array, comparator)
        return array
    }

}