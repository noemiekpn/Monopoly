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

package org.apache.commons.csv;

import static org.apache.commons.csv.CSVFormatModifier.delimiter;
import static org.apache.commons.csv.CSVFormatModifier.escape;
import static org.apache.commons.csv.CSVFormatModifier.noEscape;
import static org.apache.commons.csv.CSVFormatModifier.quoteChar;
import static org.apache.commons.csv.CSVFormatModifier.noQuoteChar;
import static org.apache.commons.csv.CSVFormatModifier.quotePolicy;
import static org.apache.commons.csv.CSVFormatModifier.noQuotePolicy;
import static org.apache.commons.csv.CSVFormatModifier.commentStart;
import static org.apache.commons.csv.CSVFormatModifier.noCommentStart;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test case for creating CSVFormats using CSVFormatModifier
 */
public class CSVFormatModifierTest {
    
    @Test
    public void changeDelimiter() {
        CSVFormat modified = CSVFormat.DEFAULT.with(delimiter('#'));
        assertEquals('#', modified.getDelimiter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void delimiterCannotBeLF() throws Exception {
        CSVFormat.DEFAULT.with(delimiter(Constants.LF));
    }

    @Test(expected = IllegalArgumentException.class)
    public void delimiterCannotBeCR() throws Exception {
        CSVFormat.DEFAULT.with(delimiter(Constants.CR));
    }
    
    @Test
    public void changeQuoteChar() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(quoteChar('#'));
        assertEquals(Character.valueOf('#'), modified.getQuoteChar());
    }

    @Test(expected = IllegalArgumentException.class)
    public void quoteCharCannotBeLF() throws Exception {
        CSVFormat.DEFAULT.with(quoteChar(Constants.LF));
    }

    @Test(expected = IllegalArgumentException.class)
    public void quoteCharCannotBeCR() throws Exception {
        CSVFormat.DEFAULT.with(quoteChar(Constants.CR));
    }
    
    @Test
    public void removeQuoteChar() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(noQuoteChar());
        assertNull(modified.getQuoteChar());
    }

    @Test
    public void changeQuotePolicy() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(quotePolicy(Quote.ALL));
        assertEquals(Quote.ALL, modified.getQuotePolicy());
    }

    @Test
    public void removeQuotePolicy() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(noQuotePolicy());
        assertNull(modified.getQuotePolicy());
    }
    
    @Test
    public void changeCommentStart() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(commentStart('#'));
        assertEquals(Character.valueOf('#'), modified.getCommentStart());
    }

    @Test
    public void removeCommentStart() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(noCommentStart());
        assertNull(modified.getCommentStart());
    }
    
    @Test
    public void changeEscape() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(escape('#'));
        assertEquals(Character.valueOf('#'), modified.getEscape());
    }
    
    @Test
    public void removeEscape() throws Exception {
        CSVFormat modified = CSVFormat.DEFAULT.with(noEscape());
        assertNull(modified.getEscape());
    }
    
    // test of consistency checks
    
    @Test(expected = IllegalStateException.class)
    public void delimiterAndQuoteCharMustBeDifferent() throws Exception {
        CSVFormat.DEFAULT.with(delimiter('#'), quoteChar('#'));
    }

    @Test(expected = IllegalStateException.class)
    public void delimiterAndCommentStartMustBeDifferent() throws Exception {
        CSVFormat.DEFAULT.with(delimiter('#'), commentStart('#'));
    }
    
    @Test(expected = IllegalStateException.class)
    public void delimiterAndEscapeMustBeDifferent() throws Exception {
        CSVFormat.DEFAULT.with(delimiter('#'), escape('#'));
    }

    @Test(expected = IllegalStateException.class)
    public void escapeAndCommentStartMustBeDifferent() throws Exception {
        CSVFormat.DEFAULT.with(commentStart('#'), escape('#'));
    }
    
    @Test(expected = IllegalStateException.class)
    public void quoteCharAndCommentStartMustBeDifferent() throws Exception {
        CSVFormat.DEFAULT.with(quoteChar('#'), commentStart('#'));
    }
    
    @Test(expected = IllegalStateException.class)
    public void quotePolicyMustNotBeNoneIfEscapeIsNull() throws Exception {
        CSVFormat.DEFAULT.with(quotePolicy(Quote.NONE), noEscape());
    }
    
}
