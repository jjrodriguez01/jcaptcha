/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2000 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 */


package com.octo.captcha.image.gimpy.wordgenerator;

import java.util.Locale;

import junit.framework.TestCase;

/**
 * <p>Description: </p>
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class WordListTest extends TestCase {
    private WordList wordList;
    public static final String WORD1 = "test";
    public static final String WORD2 = "testtest";
    public static final String WORD3 = "te";


    /**
     * Constructor for WordListTest.
     * @param name
     */
    public WordListTest(String name) {
        super(name);
    }

    public void setUp() {
        this.wordList = new WordList(Locale.US);
        this.wordList.addWord(WORD1);
    }

    public void testGetLocale() {
        Locale expected = Locale.US;
        Locale test = this.wordList.getLocale();
        assertEquals(expected,test);
    }

    public void testAddWord() {
        String test = this.wordList.getNextWord(new Integer(WORD2.length()));
        assertNull(test);
        test = this.wordList.getNextWord(new Integer(WORD1.length()));
        assertNotNull(test);
        this.wordList.addWord(WORD2);
        test = this.wordList.getNextWord(new Integer(WORD1.length()));
        assertNotNull(test);
        test = this.wordList.getNextWord(new Integer(WORD2.length()));
        assertNotNull(test);

    }

    public void testGetNextWord() {
        String expected = WORD1;
        String test = this.wordList.getNextWord(new Integer(WORD1.length()));
        assertNotNull(test);
        assertEquals(expected,test);
        this.wordList.addWord(WORD2);
        test = this.wordList.getNextWord(new Integer(WORD2.length()));
        assertEquals(WORD2,test);
    }

    public void testGetMinWord() throws Exception
    {
        assertEquals(WORD1.length(),this.wordList.getMinWord().intValue());
        this.wordList.addWord(WORD2);
        assertEquals(WORD1.length(),this.wordList.getMinWord().intValue());
        this.wordList.addWord(WORD3);
        assertEquals(WORD3.length(),this.wordList.getMinWord().intValue());
    }

    public void testGetMaxWord() throws Exception
    {
        assertEquals(WORD1.length(),this.wordList.getMaxWord().intValue());
        this.wordList.addWord(WORD2);
        assertEquals(WORD2.length(),this.wordList.getMaxWord().intValue());
        this.wordList.addWord(WORD3);
        assertEquals(WORD2.length(),this.wordList.getMaxWord().intValue());

    }
}
