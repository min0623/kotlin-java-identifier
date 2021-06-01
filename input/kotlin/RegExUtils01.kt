import java.util.regex.Pattern

/**
 *
 * Helpers to process Strings using regular expressions.
 * @see java.util.regex.Pattern
 *
 * @since 3.8
 */
object RegExUtils01 {
    /**
     *
     * Removes each substring of the text String that matches the given regular expression pattern.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `pattern.matcher(text).replaceAll("")`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     * <pre>
     * StringUtils.removeAll(null, *)      = null
     * StringUtils.removeAll("any", (Pattern) null)  = "any"
     * StringUtils.removeAll("any", Pattern.compile(""))    = "any"
     * StringUtils.removeAll("any", Pattern.compile(".*"))  = ""
     * StringUtils.removeAll("any", Pattern.compile(".+"))  = ""
     * StringUtils.removeAll("abc", Pattern.compile(".?"))  = ""
     * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("&lt;.*&gt;"))      = "A\nB"
     * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("(?s)&lt;.*&gt;"))  = "AB"
     * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("&lt;.*&gt;", Pattern.DOTALL))  = "AB"
     * StringUtils.removeAll("ABCabc123abc", Pattern.compile("[a-z]"))     = "ABC123"
    </pre> *
     *
     * @param text  text to remove from, may be null
     * @param regex  the regular expression to which this string is to be matched
     * @return  the text with any removes processed,
     * `null` if null String input
     *
     * @see .replaceAll
     * @see java.util.regex.Matcher.replaceAll
     * @see java.util.regex.Pattern
     */
    @JvmStatic
    fun removeAll(text: String?, regex: Pattern?): String? {
        return replaceAll(text, regex, "")
    }

    /**
     *
     * Removes each substring of the text String that matches the given regular expression.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `text.replaceAll(regex, "")`
     *  * `Pattern.compile(regex).matcher(text).replaceAll("")`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     *
     * Unlike in the [.removePattern] method, the [Pattern.DOTALL] option
     * is NOT automatically added.
     * To use the DOTALL option prepend `"(?s)"` to the regex.
     * DOTALL is also known as single-line mode in Perl.
     *
     * <pre>
     * StringUtils.removeAll(null, *)      = null
     * StringUtils.removeAll("any", (String) null)  = "any"
     * StringUtils.removeAll("any", "")    = "any"
     * StringUtils.removeAll("any", ".*")  = ""
     * StringUtils.removeAll("any", ".+")  = ""
     * StringUtils.removeAll("abc", ".?")  = ""
     * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\nB"
     * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
     * StringUtils.removeAll("ABCabc123abc", "[a-z]")     = "ABC123"
    </pre> *
     *
     * @param text  text to remove from, may be null
     * @param regex  the regular expression to which this string is to be matched
     * @return  the text with any removes processed,
     * `null` if null String input
     *
     * @throws  java.util.regex.PatternSyntaxException
     * if the regular expression's syntax is invalid
     *
     * @see .replaceAll
     * @see .removePattern
     * @see String.replaceAll
     * @see java.util.regex.Pattern
     *
     * @see java.util.regex.Pattern.DOTALL
     */
    @JvmStatic
    fun removeAll(text: String?, regex: String?): String? {
        return replaceAll(text, regex, "")
    }

    /**
     *
     * Removes the first substring of the text string that matches the given regular expression pattern.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `pattern.matcher(text).replaceFirst("")`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     * <pre>
     * StringUtils.removeFirst(null, *)      = null
     * StringUtils.removeFirst("any", (Pattern) null)  = "any"
     * StringUtils.removeFirst("any", Pattern.compile(""))    = "any"
     * StringUtils.removeFirst("any", Pattern.compile(".*"))  = ""
     * StringUtils.removeFirst("any", Pattern.compile(".+"))  = ""
     * StringUtils.removeFirst("abc", Pattern.compile(".?"))  = "bc"
     * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("&lt;.*&gt;"))      = "A\n&lt;__&gt;B"
     * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("(?s)&lt;.*&gt;"))  = "AB"
     * StringUtils.removeFirst("ABCabc123", Pattern.compile("[a-z]"))          = "ABCbc123"
     * StringUtils.removeFirst("ABCabc123abc", Pattern.compile("[a-z]+"))      = "ABC123abc"
    </pre> *
     *
     * @param text  text to remove from, may be null
     * @param regex  the regular expression pattern to which this string is to be matched
     * @return  the text with the first replacement processed,
     * `null` if null String input
     *
     * @see .replaceFirst
     * @see java.util.regex.Matcher.replaceFirst
     * @see java.util.regex.Pattern
     */
    @JvmStatic
    fun removeFirst(text: String?, regex: Pattern?): String? {
        return replaceFirst(text, regex, "")
    }

    /**
     *
     * Removes the first substring of the text string that matches the given regular expression.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `text.replaceFirst(regex, "")`
     *  * `Pattern.compile(regex).matcher(text).replaceFirst("")`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     *
     * The [Pattern.DOTALL] option is NOT automatically added.
     * To use the DOTALL option prepend `"(?s)"` to the regex.
     * DOTALL is also known as single-line mode in Perl.
     *
     * <pre>
     * StringUtils.removeFirst(null, *)      = null
     * StringUtils.removeFirst("any", (String) null)  = "any"
     * StringUtils.removeFirst("any", "")    = "any"
     * StringUtils.removeFirst("any", ".*")  = ""
     * StringUtils.removeFirst("any", ".+")  = ""
     * StringUtils.removeFirst("abc", ".?")  = "bc"
     * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\n&lt;__&gt;B"
     * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
     * StringUtils.removeFirst("ABCabc123", "[a-z]")          = "ABCbc123"
     * StringUtils.removeFirst("ABCabc123abc", "[a-z]+")      = "ABC123abc"
    </pre> *
     *
     * @param text  text to remove from, may be null
     * @param regex  the regular expression to which this string is to be matched
     * @return  the text with the first replacement processed,
     * `null` if null String input
     *
     * @throws  java.util.regex.PatternSyntaxException
     * if the regular expression's syntax is invalid
     *
     * @see .replaceFirst
     * @see String.replaceFirst
     * @see java.util.regex.Pattern
     *
     * @see java.util.regex.Pattern.DOTALL
     */
    @JvmStatic
    fun removeFirst(text: String?, regex: String?): String? {
        return replaceFirst(text, regex, "")
    }

    /**
     *
     * Removes each substring of the source String that matches the given regular expression using the DOTALL option.
     *
     * This call is a `null` safe equivalent to:
     *
     *  * `text.replaceAll(&quot;(?s)&quot; + regex, "")`
     *  * `Pattern.compile(regex, Pattern.DOTALL).matcher(text).replaceAll("")`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     * <pre>
     * StringUtils.removePattern(null, *)       = null
     * StringUtils.removePattern("any", (String) null)   = "any"
     * StringUtils.removePattern("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")  = "AB"
     * StringUtils.removePattern("ABCabc123", "[a-z]")    = "ABC123"
    </pre> *
     *
     * @param text
     * the source string
     * @param regex
     * the regular expression to which this string is to be matched
     * @return The resulting `String`
     * @see .replacePattern
     * @see String.replaceAll
     * @see Pattern.DOTALL
     */
    @JvmStatic
    fun removePattern(text: String?, regex: String?): String? {
        return replacePattern(text, regex, "")
    }

    /**
     *
     * Replaces each substring of the text String that matches the given regular expression pattern with the given replacement.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `pattern.matcher(text).replaceAll(replacement)`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     * <pre>
     * StringUtils.replaceAll(null, *, *)       = null
     * StringUtils.replaceAll("any", (Pattern) null, *)   = "any"
     * StringUtils.replaceAll("any", *, null)   = "any"
     * StringUtils.replaceAll("", Pattern.compile(""), "zzz")    = "zzz"
     * StringUtils.replaceAll("", Pattern.compile(".*"), "zzz")  = "zzz"
     * StringUtils.replaceAll("", Pattern.compile(".+"), "zzz")  = ""
     * StringUtils.replaceAll("abc", Pattern.compile(""), "ZZ")  = "ZZaZZbZZcZZ"
     * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("&lt;.*&gt;"), "z")                 = "z\nz"
     * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("&lt;.*&gt;", Pattern.DOTALL), "z") = "z"
     * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("(?s)&lt;.*&gt;"), "z")             = "z"
     * StringUtils.replaceAll("ABCabc123", Pattern.compile("[a-z]"), "_")       = "ABC___123"
     * StringUtils.replaceAll("ABCabc123", Pattern.compile("[^A-Z0-9]+"), "_")  = "ABC_123"
     * StringUtils.replaceAll("ABCabc123", Pattern.compile("[^A-Z0-9]+"), "")   = "ABC123"
     * StringUtils.replaceAll("Lorem ipsum  dolor   sit", Pattern.compile("( +)([a-z]+)"), "_$2")  = "Lorem_ipsum_dolor_sit"
    </pre> *
     *
     * @param text  text to search and replace in, may be null
     * @param regex  the regular expression pattern to which this string is to be matched
     * @param replacement  the string to be substituted for each match
     * @return  the text with any replacements processed,
     * `null` if null String input
     *
     * @see java.util.regex.Matcher.replaceAll
     * @see java.util.regex.Pattern
     */
    @JvmStatic
    fun replaceAll(text: String?, regex: Pattern?, replacement: String?): String? {
        return if (anyNull(text, regex, replacement)) {
            text
        } else regex!!.matcher(text).replaceAll(replacement)
    }

    /**
     *
     * Replaces each substring of the text String that matches the given regular expression
     * with the given replacement.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `text.replaceAll(regex, replacement)`
     *  * `Pattern.compile(regex).matcher(text).replaceAll(replacement)`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     *
     * Unlike in the [.replacePattern] method, the [Pattern.DOTALL] option
     * is NOT automatically added.
     * To use the DOTALL option prepend `"(?s)"` to the regex.
     * DOTALL is also known as single-line mode in Perl.
     *
     * <pre>
     * StringUtils.replaceAll(null, *, *)       = null
     * StringUtils.replaceAll("any", (String) null, *)   = "any"
     * StringUtils.replaceAll("any", *, null)   = "any"
     * StringUtils.replaceAll("", "", "zzz")    = "zzz"
     * StringUtils.replaceAll("", ".*", "zzz")  = "zzz"
     * StringUtils.replaceAll("", ".+", "zzz")  = ""
     * StringUtils.replaceAll("abc", "", "ZZ")  = "ZZaZZbZZcZZ"
     * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\nz"
     * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
     * StringUtils.replaceAll("ABCabc123", "[a-z]", "_")       = "ABC___123"
     * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
     * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
     * StringUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
    </pre> *
     *
     * @param text  text to search and replace in, may be null
     * @param regex  the regular expression to which this string is to be matched
     * @param replacement  the string to be substituted for each match
     * @return  the text with any replacements processed,
     * `null` if null String input
     *
     * @throws  java.util.regex.PatternSyntaxException
     * if the regular expression's syntax is invalid
     *
     * @see .replacePattern
     * @see String.replaceAll
     * @see java.util.regex.Pattern
     *
     * @see java.util.regex.Pattern.DOTALL
     */
    @JvmStatic
    fun replaceAll(text: String?, regex: String?, replacement: String?): String? {
        return if (anyNull(text, regex, replacement)) {
            text
        } else text!!.replace((regex?: "").toRegex(), replacement?: "")
    }

    /**
     *
     * Replaces the first substring of the text string that matches the given regular expression pattern
     * with the given replacement.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `pattern.matcher(text).replaceFirst(replacement)`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     * <pre>
     * StringUtils.replaceFirst(null, *, *)       = null
     * StringUtils.replaceFirst("any", (Pattern) null, *)   = "any"
     * StringUtils.replaceFirst("any", *, null)   = "any"
     * StringUtils.replaceFirst("", Pattern.compile(""), "zzz")    = "zzz"
     * StringUtils.replaceFirst("", Pattern.compile(".*"), "zzz")  = "zzz"
     * StringUtils.replaceFirst("", Pattern.compile(".+"), "zzz")  = ""
     * StringUtils.replaceFirst("abc", Pattern.compile(""), "ZZ")  = "ZZabc"
     * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("&lt;.*&gt;"), "z")      = "z\n&lt;__&gt;"
     * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("(?s)&lt;.*&gt;"), "z")  = "z"
     * StringUtils.replaceFirst("ABCabc123", Pattern.compile("[a-z]"), "_")          = "ABC_bc123"
     * StringUtils.replaceFirst("ABCabc123abc", Pattern.compile("[^A-Z0-9]+"), "_")  = "ABC_123abc"
     * StringUtils.replaceFirst("ABCabc123abc", Pattern.compile("[^A-Z0-9]+"), "")   = "ABC123abc"
     * StringUtils.replaceFirst("Lorem ipsum  dolor   sit", Pattern.compile("( +)([a-z]+)"), "_$2")  = "Lorem_ipsum  dolor   sit"
    </pre> *
     *
     * @param text  text to search and replace in, may be null
     * @param regex  the regular expression pattern to which this string is to be matched
     * @param replacement  the string to be substituted for the first match
     * @return  the text with the first replacement processed,
     * `null` if null String input
     *
     * @see java.util.regex.Matcher.replaceFirst
     * @see java.util.regex.Pattern
     */
    @JvmStatic
    fun replaceFirst(text: String?, regex: Pattern?, replacement: String?): String? {
        return if (text == null || regex == null || replacement == null) {
            text
        } else regex.matcher(text).replaceFirst(replacement)
    }

    /**
     *
     * Replaces the first substring of the text string that matches the given regular expression
     * with the given replacement.
     *
     * This method is a `null` safe equivalent to:
     *
     *  * `text.replaceFirst(regex, replacement)`
     *  * `Pattern.compile(regex).matcher(text).replaceFirst(replacement)`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     *
     * The [Pattern.DOTALL] option is NOT automatically added.
     * To use the DOTALL option prepend `"(?s)"` to the regex.
     * DOTALL is also known as single-line mode in Perl.
     *
     * <pre>
     * StringUtils.replaceFirst(null, *, *)       = null
     * StringUtils.replaceFirst("any", (String) null, *)   = "any"
     * StringUtils.replaceFirst("any", *, null)   = "any"
     * StringUtils.replaceFirst("", "", "zzz")    = "zzz"
     * StringUtils.replaceFirst("", ".*", "zzz")  = "zzz"
     * StringUtils.replaceFirst("", ".+", "zzz")  = ""
     * StringUtils.replaceFirst("abc", "", "ZZ")  = "ZZabc"
     * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\n&lt;__&gt;"
     * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
     * StringUtils.replaceFirst("ABCabc123", "[a-z]", "_")          = "ABC_bc123"
     * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_")  = "ABC_123abc"
     * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "")   = "ABC123abc"
     * StringUtils.replaceFirst("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum  dolor   sit"
    </pre> *
     *
     * @param text  text to search and replace in, may be null
     * @param regex  the regular expression to which this string is to be matched
     * @param replacement  the string to be substituted for the first match
     * @return  the text with the first replacement processed,
     * `null` if null String input
     *
     * @throws  java.util.regex.PatternSyntaxException
     * if the regular expression's syntax is invalid
     *
     * @see String.replaceFirst
     * @see java.util.regex.Pattern
     *
     * @see java.util.regex.Pattern.DOTALL
     */
    @JvmStatic
    fun replaceFirst(text: String?, regex: String?, replacement: String?): String? {
        return if (text == null || regex == null || replacement == null) {
            text
        } else text.replaceFirst(regex.toRegex(), replacement)
    }

    /**
     *
     * Replaces each substring of the source String that matches the given regular expression with the given
     * replacement using the [Pattern.DOTALL] option. DOTALL is also known as single-line mode in Perl.
     *
     * This call is a `null` safe equivalent to:
     *
     *  * `text.replaceAll(&quot;(?s)&quot; + regex, replacement)`
     *  * `Pattern.compile(regex, Pattern.DOTALL).matcher(text).replaceAll(replacement)`
     *
     *
     *
     * A `null` reference passed to this method is a no-op.
     *
     * <pre>
     * StringUtils.replacePattern(null, *, *)       = null
     * StringUtils.replacePattern("any", (String) null, *)   = "any"
     * StringUtils.replacePattern("any", *, null)   = "any"
     * StringUtils.replacePattern("", "", "zzz")    = "zzz"
     * StringUtils.replacePattern("", ".*", "zzz")  = "zzz"
     * StringUtils.replacePattern("", ".+", "zzz")  = ""
     * StringUtils.replacePattern("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")       = "z"
     * StringUtils.replacePattern("ABCabc123", "[a-z]", "_")       = "ABC___123"
     * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
     * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
     * StringUtils.replacePattern("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
    </pre> *
     *
     * @param text
     * the source string
     * @param regex
     * the regular expression to which this string is to be matched
     * @param replacement
     * the string to be substituted for each match
     * @return The resulting `String`
     * @see .replaceAll
     * @see String.replaceAll
     * @see Pattern.DOTALL
     */
    @JvmStatic
    fun replacePattern(text: String?, regex: String?, replacement: String?): String? {
        return if (anyNull(text, regex, replacement)) {
            text
        } else Pattern.compile(regex, Pattern.DOTALL).matcher(text).replaceAll(replacement)
    }

    @JvmStatic
    private fun allNotNull(vararg values: Any?): Boolean {
        for (`val` in values) {
            if (`val` == null) {
                return false
            }
        }
        return true
    }

    @JvmStatic
    private fun anyNull(vararg values: Any?): Boolean {
        return !allNotNull(*values)
    }
}