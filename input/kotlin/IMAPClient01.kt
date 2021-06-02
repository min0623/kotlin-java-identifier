/*
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
import org.apache.commons.net.imap.IMAP
import kotlin.Throws
import java.io.IOException
import org.apache.commons.net.imap.IMAPCommand
import org.apache.commons.net.imap.IMAPReply
import java.lang.StringBuilder

/**
 * The IMAPClient class provides the basic functionalities found in an
 * IMAP client.
 */
class IMAPClient01 : IMAP() {
    // --------- commands available in all states
    /**
     * Send a CAPABILITY command to the server.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs
     */
    @Throws(IOException::class)
    fun capability(): Boolean {
        return doCommand(IMAPCommand.CAPABILITY)
    }

    /**
     * Send a NOOP command to the server.  This is useful for keeping
     * a connection alive since most IMAP servers will timeout after 10
     * minutes of inactivity.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun noop(): Boolean {
        return doCommand(IMAPCommand.NOOP)
    }

    /**
     * Send a LOGOUT command to the server.  To fully disconnect from the server
     * you must call disconnect().
     * A logout attempt is valid in any state.  If
     * the client is in the not authenticated or authenticated state, it enters the
     * logout on a successful logout.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun logout(): Boolean {
        return doCommand(IMAPCommand.LOGOUT)
    }
    // --------- commands available in the not-authenticated state
    // STARTTLS skipped - see IMAPSClient.
    // AUTHENTICATE skipped - see AuthenticatingIMAPClient.
    /**
     * Login to the IMAP server with the given username and password.  You
     * must first connect to the server with
     * [connect ][org.apache.commons.net.SocketClient.connect]
     * before attempting to login.  A login attempt is only valid if
     * the client is in the NOT_AUTH_STATE.
     * After logging in, the client enters the AUTH_STATE.
     *
     * @param username  The account name being logged in to.
     * @param password  The plain text password of the account.
     * @return True if the login attempt was successful, false if not.
     * @throws IOException If a network I/O error occurs in the process of
     * logging in.
     */
    @Throws(IOException::class)
    fun login(username: String, password: String): Boolean {
        if (state != IMAPState.NOT_AUTH_STATE) {
            return false
        }
        if (!doCommand(IMAPCommand.LOGIN, "$username $password")) {
            return false
        }
        state = IMAPState.AUTH_STATE
        return true
    }
    // --------- commands available in the authenticated state
    /**
     * Send a SELECT command to the server.
     * @param mailboxName The mailbox name to select.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun select(mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.SELECT, quoteMailboxName(mailboxName))
    }

    /**
     * Send an EXAMINE command to the server.
     * @param mailboxName The mailbox name to examine.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun examine(mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.EXAMINE, quoteMailboxName(mailboxName))
    }

    /**
     * Send a CREATE command to the server.
     * @param mailboxName The mailbox name to create.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun create(mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.CREATE, quoteMailboxName(mailboxName))
    }

    /**
     * Send a DELETE command to the server.
     * @param mailboxName The mailbox name to delete.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun delete(mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.DELETE, quoteMailboxName(mailboxName))
    }

    /**
     * Send a RENAME command to the server.
     * @param oldMailboxName The existing mailbox name to rename.
     * @param newMailboxName The new mailbox name.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun rename(oldMailboxName: String?, newMailboxName: String?): Boolean {
        return doCommand(IMAPCommand.RENAME, quoteMailboxName(oldMailboxName) + " " + quoteMailboxName(newMailboxName))
    }

    /**
     * Send a SUBSCRIBE command to the server.
     * @param mailboxName The mailbox name to subscribe to.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun subscribe(mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.SUBSCRIBE, quoteMailboxName(mailboxName))
    }

    /**
     * Send a UNSUBSCRIBE command to the server.
     * @param mailboxName The mailbox name to unsubscribe from.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun unsubscribe(mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.UNSUBSCRIBE, quoteMailboxName(mailboxName))
    }

    /**
     * Send a LIST command to the server.
     * Quotes the parameters if necessary.
     * @param refName The reference name
     * If empty, indicates that the mailbox name is interpreted as by SELECT.
     * @param mailboxName The mailbox name.
     * If empty, this is a special request to
     * return the hierarchy delimiter and the root name of the name given
     * in the reference
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun list(refName: String?, mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.LIST, quoteMailboxName(refName) + " " + quoteMailboxName(mailboxName))
    }

    /**
     * Send an LSUB command to the server.
     * Quotes the parameters if necessary.
     * @param refName The reference name.
     * @param mailboxName The mailbox name.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun lsub(refName: String?, mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.LSUB, quoteMailboxName(refName) + " " + quoteMailboxName(mailboxName))
    }

    /**
     * Send a STATUS command to the server.
     * @param mailboxName The reference name.
     * @param itemNames The status data item names.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun status(mailboxName: String?, itemNames: Array<String?>?): Boolean {
        require(!(itemNames == null || itemNames.size < 1)) { "STATUS command requires at least one data item name" }
        val sb = StringBuilder()
        sb.append(quoteMailboxName(mailboxName))
        sb.append(" (")
        for (i in itemNames.indices) {
            if (i > 0) {
                sb.append(" ")
            }
            sb.append(itemNames[i])
        }
        sb.append(")")
        return doCommand(IMAPCommand.STATUS, sb.toString())
    }

    /**
     * Send an APPEND command to the server.
     * @param mailboxName The mailbox name.
     * @param flags The flag parenthesized list (optional).
     * @param datetime The date/time string (optional).
     * @param message The message to append.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     * @since 3.4
     */
    @Throws(IOException::class)
    fun append(mailboxName: String?, flags: String?, datetime: String?, message: String): Boolean {
        val args = StringBuilder(quoteMailboxName(mailboxName))
        if (flags != null) {
            args.append(" ").append(flags)
        }
        if (datetime != null) {
            args.append(" ")
            if (datetime[0] == DQUOTE) {
                args.append(datetime)
            } else {
                args.append(DQUOTE).append(datetime).append(DQUOTE)
            }
        }
        args.append(" ")
        // String literal (probably not used much - if at all)
        if (message.startsWith(DQUOTE_S) && message.endsWith(DQUOTE_S)) {
            args.append(message)
            return doCommand(IMAPCommand.APPEND, args.toString())
        }
        args.append('{').append(message.toByteArray(charset(__DEFAULT_ENCODING)).size).append('}') // length of message
        val status = sendCommand(IMAPCommand.APPEND, args.toString())
        return (IMAPReply.isContinuation(status) // expecting continuation response
                && IMAPReply.isSuccess(sendData(message)) // if so, send the data
                )
    }

    /**
     * Send an APPEND command to the server.
     * @param mailboxName The mailbox name.
     * @param flags The flag parenthesized list (optional).
     * @param datetime The date/time string (optional).
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Deprecated("""(3.4) Does not work; the message body is not optional.
      Use {@link #append(String, String, String, String)} instead.""")
    @Throws(IOException::class)
    fun append(mailboxName: String?, flags: String?, datetime: String?): Boolean {
        var args = mailboxName
        if (flags != null) {
            args += " $flags"
        }
        if (datetime != null) {
            args += if (datetime[0] == '{') {
                " $datetime"
            } else {
                " {$datetime}"
            }
        }
        return doCommand(IMAPCommand.APPEND, args)
    }

    /**
     * Send an APPEND command to the server.
     * @param mailboxName The mailbox name.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Deprecated("""(3.4) Does not work; the message body is not optional.
      Use {@link #append(String, String, String, String)} instead.""")
    @Throws(IOException::class)
    fun append(mailboxName: String?): Boolean {
        return append(mailboxName, null, null)
    }
    // --------- commands available in the selected state
    /**
     * Send a CHECK command to the server.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun check(): Boolean {
        return doCommand(IMAPCommand.CHECK)
    }

    /**
     * Send a CLOSE command to the server.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun close(): Boolean {
        return doCommand(IMAPCommand.CLOSE)
    }

    /**
     * Send an EXPUNGE command to the server.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun expunge(): Boolean {
        return doCommand(IMAPCommand.EXPUNGE)
    }

    /**
     * Send a SEARCH command to the server.
     * @param charset The charset (optional).
     * @param criteria The search criteria.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun search(charset: String?, criteria: String?): Boolean {
        var args: String? = ""
        if (charset != null) {
            args += "CHARSET $charset"
        }
        args += criteria
        return doCommand(IMAPCommand.SEARCH, args)
    }

    /**
     * Send a SEARCH command to the server.
     * @param criteria The search criteria.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun search(criteria: String?): Boolean {
        return search(null, criteria)
    }

    /**
     * Send a FETCH command to the server.
     *
     * @param sequenceSet The sequence set to fetch (e.g. 1:4,6,11,100:*)
     * @param itemNames The item names for the FETCH command. (e.g. BODY.PEEK[HEADER.FIELDS (SUBJECT)])
     * If multiple item names are requested, these must be enclosed in parentheses, e.g. "(UID FLAGS BODY.PEEK[])"
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     * @see .getReplyString
     * @see .getReplyStrings
     */
    @Throws(IOException::class)
    fun fetch(sequenceSet: String, itemNames: String): Boolean {
        return doCommand(IMAPCommand.FETCH, "$sequenceSet $itemNames")
    }

    /**
     * Send a STORE command to the server.
     * @param sequenceSet The sequence set to update (e.g. 2:5)
     * @param itemNames The item name for the STORE command (i.e. [+|-]FLAGS[.SILENT])
     * @param itemValues The item values for the STORE command. (e.g. (\Deleted) )
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun store(sequenceSet: String, itemNames: String, itemValues: String): Boolean {
        return doCommand(IMAPCommand.STORE, "$sequenceSet $itemNames $itemValues")
    }

    /**
     * Send a COPY command to the server.
     * @param sequenceSet The sequence set to fetch.
     * @param mailboxName The mailbox name.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun copy(sequenceSet: String, mailboxName: String?): Boolean {
        return doCommand(IMAPCommand.COPY, sequenceSet + " " + quoteMailboxName(mailboxName))
    }

    /**
     * Send a UID command to the server.
     * @param command The command for UID.
     * @param commandArgs The arguments for the command.
     * @return `true` if the command was successful,`false` if not.
     * @throws IOException If a network I/O error occurs.
     */
    @Throws(IOException::class)
    fun uid(command: String, commandArgs: String): Boolean {
        return doCommand(IMAPCommand.UID, "$command $commandArgs")
    }

    /**
     * The status data items defined in RFC 3501.
     */
    enum class STATUS_DATA_ITEMS {
        /** The number of messages in the mailbox.  */
        MESSAGES,

        /** The number of messages with the \Recent flag set.  */
        RECENT,

        /** The next unique identifier value of the mailbox.  */
        UIDNEXT,

        /** The unique identifier validity value of the mailbox.  */
        UIDVALIDITY,

        /** The number of messages which do not have the \Seen flag set.  */
        UNSEEN
    }

    /**
     * The search criteria defined in RFC 3501.
     */
    enum class SEARCH_CRITERIA {
        /** All messages in the mailbox.  */
        ALL,

        /** Messages with the \Answered flag set.  */
        ANSWERED,

        /**
         * Messages that contain the specified string in the envelope
         * structure's BCC field.
         */
        BCC,

        /**
         * Messages whose internal date (disregarding time and time zone)
         * is earlier than the specified date.
         */
        BEFORE,

        /**
         * Messages that contain the specified string in the body of the
         * message.
         */
        BODY,

        /**
         * Messages that contain the specified string in the envelope
         * structure's CC field.
         */
        CC,

        /** Messages with the \Deleted flag set.  */
        DELETED,

        /** Messages with the \Draft flag set.  */
        DRAFT,

        /** Messages with the \Flagged flag set.  */
        FLAGGED,

        /**
         * Messages that contain the specified string in the envelope
         * structure's FROM field.
         */
        FROM,

        /**
         * Messages that have a header with the specified field-name (as
         * defined in [RFC-2822]) and that contains the specified string
         * in the text of the header (what comes after the colon).  If the
         * string to search is zero-length, this matches all messages that
         * have a header line with the specified field-name regardless of
         * the contents.
         */
        HEADER,

        /** Messages with the specified keyword flag set.  */
        KEYWORD,

        /**
         * Messages with an [RFC-2822] size larger than the specified
         * number of octets.
         */
        LARGER,

        /**
         * Messages that have the \Recent flag set but not the \Seen flag.
         * This is functionally equivalent to "(RECENT UNSEEN)".
         */
        NEW,

        /** Messages that do not match the specified search key.  */
        NOT,

        /**
         * Messages that do not have the \Recent flag set.  This is
         * functionally equivalent to "NOT RECENT" (as opposed to "NOT
         * NEW").
         */
        OLD,

        /**
         * Messages whose internal date (disregarding time and time zone)
         * is within the specified date.
         */
        ON,

        /** Messages that match either search key.  */
        OR,

        /** Messages that have the \Recent flag set.  */
        RECENT,

        /** Messages that have the \Seen flag set.  */
        SEEN,

        /**
         * Messages whose [RFC-2822] Date: header (disregarding time and
         * time zone) is earlier than the specified date.
         */
        SENTBEFORE,

        /**
         * Messages whose [RFC-2822] Date: header (disregarding time and
         * time zone) is within the specified date.
         */
        SENTON,

        /**
         * Messages whose [RFC-2822] Date: header (disregarding time and
         * time zone) is within or later than the specified date.
         */
        SENTSINCE,

        /**
         * Messages whose internal date (disregarding time and time zone)
         * is within or later than the specified date.
         */
        SINCE,

        /**
         * Messages with an [RFC-2822] size smaller than the specified
         * number of octets.
         */
        SMALLER,

        /**
         * Messages that contain the specified string in the envelope
         * structure's SUBJECT field.
         */
        SUBJECT,

        /**
         * Messages that contain the specified string in the header or
         * body of the message.
         */
        TEXT,

        /**
         * Messages that contain the specified string in the envelope
         * structure's TO field.
         */
        TO,

        /**
         * Messages with unique identifiers corresponding to the specified
         * unique identifier set.  Sequence set ranges are permitted.
         */
        UID,

        /** Messages that do not have the \Answered flag set.  */
        UNANSWERED,

        /** Messages that do not have the \Deleted flag set.  */
        UNDELETED,

        /** Messages that do not have the \Draft flag set.  */
        UNDRAFT,

        /** Messages that do not have the \Flagged flag set.  */
        UNFLAGGED,

        /** Messages that do not have the specified keyword flag set.  */
        UNKEYWORD,

        /** Messages that do not have the \Seen flag set.  */
        UNSEEN
    }

    /**
     * The message data item names for the FETCH command defined in RFC 3501.
     */
    enum class FETCH_ITEM_NAMES {
        /** Macro equivalent to: (FLAGS INTERNALDATE RFC822.SIZE ENVELOPE).  */
        ALL,

        /** Macro equivalent to: (FLAGS INTERNALDATE RFC822.SIZE).  */
        FAST,

        /** Macro equivalent to: (FLAGS INTERNALDATE RFC822.SIZE ENVELOPE BODY).  */
        FULL,

        /** Non-extensible form of BODYSTRUCTURE or the text of a particular body section.  */
        BODY,

        /** The [MIME-IMB] body structure of the message.  */
        BODYSTRUCTURE,

        /** The envelope structure of the message.  */
        ENVELOPE,

        /** The flags that are set for this message.  */
        FLAGS,

        /** The internal date of the message.  */
        INTERNALDATE,

        /** A prefix for RFC-822 item names.  */
        RFC822,

        /** The unique identifier for the message.  */
        UID
    }

    companion object {
        private const val DQUOTE = '"'
        private const val DQUOTE_S = "\""

        /**
         * Quote an input string if necessary.
         * If the string is enclosed in double-quotes it is assumed
         * to be quoted already and is returned unchanged.
         * If it is the empty string, "" is returned.
         * If it contains a space
         * then it is enclosed in double quotes, escaping the
         * characters backslash and double-quote.
         *
         * @param input the value to be quoted, may be null
         * @return the quoted value
         */
        private fun quoteMailboxName(input: String?): String? {
            if (input == null) { // Don't throw NPE here
                return null
            }
            if (input.isEmpty()) {
                return "\"\"" // return the string ""
            }
            // Length check is necessary to ensure a lone double-quote is quoted
            if (input.length > 1 && input.startsWith("\"") && input.endsWith("\"")) {
                return input // Assume already quoted
            }
            return if (input.contains(" ")) {
                // quoted strings must escape \ and "
                "\"" + input.replace("([\\\\\"])".toRegex(), "\\\\$1") + "\""
            } else input
        }
    }
}