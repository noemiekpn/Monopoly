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

public abstract class CSVFormatModifier {

    private CSVFormatModifier() {
        // this class is not intended to be subclassed by clients
    }

    protected abstract void modify(MutableCSVFormat format);

    public static CSVFormatModifier delimiter(final char delimiter) {
        return new CSVFormatModifier() {

            @Override
            public void modify(MutableCSVFormat format) {
                format.setDelimiter(delimiter);
            }
        };
    }

    public static CSVFormatModifier quoteChar(final Character quoteChar) {
        return new CSVFormatModifier() {

            @Override
            public void modify(MutableCSVFormat format) {
                format.setQuoteChar(quoteChar);
            }
        };
    }

    public static CSVFormatModifier quoteChar(final char quoteChar) {
        return quoteChar(Character.valueOf(quoteChar));
    }

    public static CSVFormatModifier noQuoteChar() {
        return quoteChar(null);
    }

    public static CSVFormatModifier quotePolicy(final Quote quote) {
        return new CSVFormatModifier() {

            @Override
            public void modify(MutableCSVFormat format) {
                format.setQuotePolicy(quote);
            }
        };
    }

    public static CSVFormatModifier noQuotePolicy() {
        return quotePolicy(null);
    }

    public static CSVFormatModifier commentStart(final Character commentStart) {
        return new CSVFormatModifier() {

            @Override
            public void modify(MutableCSVFormat format) {
                format.setCommentStart(commentStart);
            }
        };
    }

    public static CSVFormatModifier noCommentStart() {
        return commentStart(null);
    }

    public static CSVFormatModifier escape(final Character escape) {
        return new CSVFormatModifier() {
            
            @Override
            protected void modify(MutableCSVFormat format) {
                format.setEscape(escape);
            }
        };
    }
    
    public static CSVFormatModifier escape(final char escape) {
        return escape(Character.valueOf(escape));
    }
    
    public static CSVFormatModifier noEscape() {
        return escape(null);
    }
    
}
