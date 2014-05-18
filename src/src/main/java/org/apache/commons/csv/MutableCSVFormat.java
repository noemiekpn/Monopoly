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

package src.main.java.org.apache.commons.csv;

import static src.main.java.org.apache.commons.csv.Constants.CR;
import static src.main.java.org.apache.commons.csv.Constants.LF;

class MutableCSVFormat {

    private char delimiter;
    private Character quoteChar;
    private Quote quotePolicy;
    private Character commentStart;
    private Character escape;
    private boolean ignoreSurroundingSpaces;
    private boolean ignoreEmptyLines;
    private String recordSeparator;
    private String nullToString;
    private String[] header;

    public MutableCSVFormat(CSVFormat format) {
        delimiter = format.getDelimiter();
        quoteChar = format.getQuoteChar(); 
        quotePolicy = format.getQuotePolicy();
        commentStart = format.getCommentStart();
        escape = format.getEscape();
        ignoreSurroundingSpaces = format.getIgnoreSurroundingSpaces();
        ignoreEmptyLines = format.getIgnoreEmptyLines();
        recordSeparator = format.getRecordSeparator();
        nullToString = format.getNullToString();
        header = header == null ? null : format.getHeader().clone();
    }
    
    public CSVFormat create() {
        validate();
        return new CSVFormat(delimiter, quoteChar, quotePolicy, commentStart,
                escape, ignoreSurroundingSpaces, ignoreEmptyLines,
                recordSeparator, nullToString, header);
    }

    /**
     * Verifies the consistency of the parameters and throws an IllegalStateException if necessary.
     *
     * @throws IllegalStateException
     */
    private void validate() throws IllegalStateException {
        if (quoteChar != null && delimiter == quoteChar.charValue()) {
            throw new IllegalStateException(
                    "The quoteChar character and the delimiter cannot be the same ('" + quoteChar + "')");
        }

        if (escape != null && delimiter == escape.charValue()) {
            throw new IllegalStateException(
                    "The escape character and the delimiter cannot be the same ('" + escape + "')");
        }

        if (commentStart != null && delimiter == commentStart.charValue()) {
            throw new IllegalStateException(
                    "The comment start character and the delimiter cannot be the same ('" + commentStart + "')");
        }

        if (quoteChar != null && quoteChar.equals(commentStart)) {
            throw new IllegalStateException(
                    "The comment start character and the quoteChar cannot be the same ('" + commentStart + "')");
        }

        if (escape != null && escape.equals(commentStart)) {
            throw new IllegalStateException(
                    "The comment start and the escape character cannot be the same ('" + commentStart + "')");
        }

        if (escape == null && quotePolicy == Quote.NONE) {
            throw new IllegalStateException("No quotes mode set but no escape character is set");
        }
    }
    
    public char getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(char delimiter) {
        if (isLineBreak(delimiter)) {
            throw new IllegalArgumentException("The delimiter cannot be a line break");
        }
        this.delimiter = delimiter;
    }

    public Character getQuoteChar() {
        return this.quoteChar;
    }

    public void setQuoteChar(Character quoteChar) {
        if (isLineBreak(quoteChar)) {
            throw new IllegalArgumentException("The quoteChar cannot be a line break");
        }
        this.quoteChar = quoteChar;
    }

    public Quote getQuotePolicy() {
        return this.quotePolicy;
    }

    public void setQuotePolicy(Quote quotePolicy) {
        this.quotePolicy = quotePolicy;
    }

    public Character getCommentStart() {
        return this.commentStart;
    }

    public void setCommentStart(Character commentStart) {
        this.commentStart = commentStart;
    }

    public Character getEscape() {
        return this.escape;
    }

    public void setEscape(Character escape) {
        this.escape = escape;
    }

    public boolean isIgnoreSurroundingSpaces() {
        return this.ignoreSurroundingSpaces;
    }

    public void setIgnoreSurroundingSpaces(boolean ignoreSurroundingSpaces) {
        this.ignoreSurroundingSpaces = ignoreSurroundingSpaces;
    }

    public boolean isIgnoreEmptyLines() {
        return this.ignoreEmptyLines;
    }

    public void setIgnoreEmptyLines(boolean ignoreEmptyLines) {
        this.ignoreEmptyLines = ignoreEmptyLines;
    }

    public String getRecordSeparator() {
        return this.recordSeparator;
    }

    public void setRecordSeparator(String recordSeparator) {
        this.recordSeparator = recordSeparator;
    }

    public String getNullToString() {
        return this.nullToString;
    }

    public void setNullToString(String nullToString) {
        this.nullToString = nullToString;
    }

    public String[] getHeader() {
        return this.header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }
    
    private static boolean isLineBreak(final Character c) {
        return c != null && isLineBreak(c.charValue());
    }
    
    private static boolean isLineBreak(final char c) {
        return c == LF || c == CR;
    }
}
