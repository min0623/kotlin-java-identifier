import java.lang.StringBuffer /*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.lang.StringBuilder

/**
 *
 * Operations on [CharSequence] that are
 * `null` safe.
 *
 * @see CharSequence
 *
 * @since 3.0
 */
object CharSequenceUtils01 {
    private const val NOT_FOUND = -1
    const val TO_STRING_LIMIT = 16
    private fun checkLaterThan1(cs: CharSequence, searchChar: CharSequence, len2: Int, start1: Int): Boolean {
        var i = 1
        var j = len2 - 1
        while (i <= j) {
            if (cs[start1 + i] != searchChar[i]
                    ||
                    cs[start1 + j] != searchChar[j]) {
                return false
            }
            i++
            j--
        }
        return true
    }

    /**
     * Used by the indexOf(CharSequence methods) as a green implementation of indexOf.
     *
     * @param cs the `CharSequence` to be processed
     * @param searchChar the `CharSequence` to be searched for
     * @param start the start index
     * @return the index where the search sequence was found
     */
    @JvmStatic
    fun indexOf(cs: CharSequence?, searchChar: CharSequence?, start: Int): Int {
        if (cs == null || searchChar == null) {
            throw NullPointerException()
        }
        if (cs is String) {
            return cs.indexOf(searchChar.toString(), start)
        }
        if (cs is StringBuilder) {
            return cs.indexOf(searchChar.toString(), start)
        }
        return if (cs is StringBuffer) {
            cs.indexOf(searchChar.toString(), start)
        } else cs.toString().indexOf(searchChar.toString(), start)
        //        if (cs instanceof String && searchChar instanceof String) {
//            // TODO: Do we assume searchChar is usually relatively small;
//            //       If so then calling toString() on it is better than reverting to
//            //       the green implementation in the else block
//            return ((String) cs).indexOf((String) searchChar, start);
//        } else {
//            // TODO: Implement rather than convert to String
//            return cs.toString().indexOf(searchChar.toString(), start);
//        }
    }

    /**
     * Returns the index within `cs` of the first occurrence of the
     * specified character, starting the search at the specified index.
     *
     *
     * If a character with value `searchChar` occurs in the
     * character sequence represented by the `cs`
     * object at an index no smaller than `start`, then
     * the index of the first such occurrence is returned. For values
     * of `searchChar` in the range from 0 to 0xFFFF (inclusive),
     * this is the smallest value *k* such that:
     * <blockquote><pre>
     * (this.charAt(*k*) == searchChar) &amp;&amp; (*k* &gt;= start)
    </pre></blockquote> *
     * is true. For other values of `searchChar`, it is the
     * smallest value *k* such that:
     * <blockquote><pre>
     * (this.codePointAt(*k*) == searchChar) &amp;&amp; (*k* &gt;= start)
    </pre></blockquote> *
     * is true. In either case, if no such character occurs inm `cs`
     * at or after position `start`, then
     * `-1` is returned.
     *
     *
     *
     * There is no restriction on the value of `start`. If it
     * is negative, it has the same effect as if it were zero: the entire
     * `CharSequence` may be searched. If it is greater than
     * the length of `cs`, it has the same effect as if it were
     * equal to the length of `cs`: `-1` is returned.
     *
     *
     * All indices are specified in `char` values
     * (Unicode code units).
     *
     * @param cs  the `CharSequence` to be processed, not null
     * @param searchChar  the char to be searched for
     * @param start  the start index, negative starts at the string start
     * @return the index where the search char was found, -1 if not found
     * @since 3.6 updated to behave more like `String`
     */
    @JvmStatic
    fun indexOf(cs: CharSequence?, searchChar: Int, start: Int): Int {
        if (cs == null) {
            throw NullPointerException()
        }
        var start = start
        if (cs is String) {
            return cs.indexOf(searchChar.toChar(), start)
        }
        val sz = cs.length
        if (start < 0) {
            start = 0
        }
        if (searchChar < Character.MIN_SUPPLEMENTARY_CODE_POINT) {
            for (i in start until sz) {
                if (cs[i].toInt() == searchChar) {
                    return i
                }
            }
            return NOT_FOUND
        }
        //supplementary characters (LANG1300)
        if (searchChar <= Character.MAX_CODE_POINT) {
            val chars = Character.toChars(searchChar)
            for (i in start until sz - 1) {
                val high = cs[i]
                val low = cs[i + 1]
                if (high == chars[0] && low == chars[1]) {
                    return i
                }
            }
        }
        return NOT_FOUND
    }

    /**
     * Used by the lastIndexOf(CharSequence methods) as a green implementation of lastIndexOf
     *
     * @param cs the `CharSequence` to be processed
     * @param searchChar the `CharSequence` to find
     * @param start the start index
     * @return the index where the search sequence was found
     */
    @JvmStatic
    fun lastIndexOf(cs: CharSequence?, searchChar: CharSequence?, start: Int): Int {
        var start = start
        if (searchChar == null || cs == null) {
            return NOT_FOUND
        }
        if (searchChar is String) {
            if (cs is String) {
                return cs.lastIndexOf((searchChar as String?)!!, start)
            }
            if (cs is StringBuilder) {
                return cs.lastIndexOf(searchChar as String?, start)
            }
            if (cs is StringBuffer) {
                return cs.lastIndexOf(searchChar as String?, start)
            }
        }
        val len1 = cs.length
        val len2 = searchChar.length
        if (start > len1) {
            start = len1
        }
        if (start < 0 || len2 < 0 || len2 > len1) {
            return NOT_FOUND
        }
        if (len2 == 0) {
            return start
        }
        if (len2 <= TO_STRING_LIMIT) {
            if (cs is String) {
                return cs.lastIndexOf(searchChar.toString(), start)
            }
            if (cs is StringBuilder) {
                return cs.lastIndexOf(searchChar.toString(), start)
            }
            if (cs is StringBuffer) {
                return cs.lastIndexOf(searchChar.toString(), start)
            }
        }
        if (start + len2 > len1) {
            start = len1 - len2
        }
        val char0 = searchChar[0]
        var i = start
        while (true) {
            while (cs[i] != char0) {
                i--
                if (i < 0) {
                    return NOT_FOUND
                }
            }
            if (checkLaterThan1(cs, searchChar, len2, i)) {
                return i
            }
            i--
            if (i < 0) {
                return NOT_FOUND
            }
        }
    }

    /**
     * Returns the index within `cs` of the last occurrence of
     * the specified character, searching backward starting at the
     * specified index. For values of `searchChar` in the range
     * from 0 to 0xFFFF (inclusive), the index returned is the largest
     * value *k* such that:
     * <blockquote><pre>
     * (this.charAt(*k*) == searchChar) &amp;&amp; (*k* &lt;= start)
    </pre></blockquote> *
     * is true. For other values of `searchChar`, it is the
     * largest value *k* such that:
     * <blockquote><pre>
     * (this.codePointAt(*k*) == searchChar) &amp;&amp; (*k* &lt;= start)
    </pre></blockquote> *
     * is true. In either case, if no such character occurs in `cs`
     * at or before position `start`, then `-1` is returned.
     *
     *
     * All indices are specified in `char` values
     * (Unicode code units).
     *
     * @param cs  the `CharSequence` to be processed
     * @param searchChar  the char to be searched for
     * @param start  the start index, negative returns -1, beyond length starts at end
     * @return the index where the search char was found, -1 if not found
     * @since 3.6 updated to behave more like `String`
     */
    @JvmStatic
    fun lastIndexOf(cs: CharSequence?, searchChar: Int, start: Int): Int {
        var start = start
        if (cs == null) {
            throw NullPointerException()
        }
        if (cs is String) {
            return cs.lastIndexOf(searchChar.toChar(), start)
        }
        val sz = cs.length
        if (start < 0) {
            return NOT_FOUND
        }
        if (start >= sz) {
            start = sz - 1
        }
        if (searchChar < Character.MIN_SUPPLEMENTARY_CODE_POINT) {
            for (i in start downTo 0) {
                if (cs[i].toInt() == searchChar) {
                    return i
                }
            }
            return NOT_FOUND
        }
        //supplementary characters (LANG1300)
        //NOTE - we must do a forward traversal for this to avoid duplicating code points
        if (searchChar <= Character.MAX_CODE_POINT) {
            val chars = Character.toChars(searchChar)
            //make sure it's not the last index
            if (start == sz - 1) {
                return NOT_FOUND
            }
            for (i in start downTo 0) {
                val high = cs[i]
                val low = cs[i + 1]
                if (chars[0] == high && chars[1] == low) {
                    return i
                }
            }
        }
        return NOT_FOUND
    }

    /**
     * Green implementation of regionMatches.
     *
     * @param cs the `CharSequence` to be processed
     * @param ignoreCase whether or not to be case insensitive
     * @param thisStart the index to start on the `cs` CharSequence
     * @param substring the `CharSequence` to be looked for
     * @param start the index to start on the `substring` CharSequence
     * @param length character length of the region
     * @return whether the region matched
     */
    @JvmStatic
    fun regionMatches(cs: CharSequence?, ignoreCase: Boolean, thisStart: Int,
                      substring: CharSequence?, start: Int, length: Int): Boolean {
                        if (cs == null || substring == null) {
                            throw NullPointerException()
                        }
                        if (cs is String && substring is String) {
            return cs.regionMatches(thisStart, substring, start, length, ignoreCase = ignoreCase)
        }
        var index1 = thisStart
        var index2 = start
        var tmpLen = length

        // Extract these first so we detect NPEs the same as the java.lang.String version
        val srcLen = cs.length - thisStart
        val otherLen = substring.length - start

        // Check for invalid parameters
        if (thisStart < 0 || start < 0 || length < 0) {
            return false
        }

        // Check that the regions are long enough
        if (srcLen < length || otherLen < length) {
            return false
        }
        while (tmpLen-- > 0) {
            val c1 = cs[index1++]
            val c2 = substring[index2++]
            if (c1 == c2) {
                continue
            }
            if (!ignoreCase) {
                return false
            }

            // The real same check as in String.regionMatches():
            val u1 = Character.toUpperCase(c1)
            val u2 = Character.toUpperCase(c2)
            if (u1 != u2 && Character.toLowerCase(u1) != Character.toLowerCase(u2)) {
                return false
            }
        }
        return true
    }

    /**
     *
     * Returns a new `CharSequence` that is a subsequence of this
     * sequence starting with the `char` value at the specified index.
     *
     *
     * This provides the `CharSequence` equivalent to [String.substring].
     * The length (in `char`) of the returned sequence is `length() - start`,
     * so if `start == end` then an empty sequence is returned.
     *
     * @param cs  the specified subsequence, null returns null
     * @param start  the start index, inclusive, valid
     * @return a new subsequence, may be null
     * @throws IndexOutOfBoundsException if `start` is negative or if
     * `start` is greater than `length()`
     */
    @JvmStatic
    fun subSequence(cs: CharSequence?, start: Int): CharSequence? {
        return cs?.subSequence(start, cs.length)
    }
}