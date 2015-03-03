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

import com.octo.captcha.CaptchaException;

import junit.framework.TestCase;

/**
 * <p>Description: </p>
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class ComposeDictionaryWordGeneratorTest extends TestCase {

    protected ComposeDictionaryWordGenerator composeDictionaryWordGenerator;
    protected String[] wordList = {"1","12","123","123456"};
    protected int[]lengths = {1,2,3,6};
    
    protected String[] badwordList = {"a","b","c","d"};
    protected int[]badlengths = {1,1,1,1};

    protected String[] emptywordList = {};
    protected int emptylength = 10;
    /**
     * Constructor for ComposeDictionaryWordGeneratorTest.
     * @param name
     */
    public ComposeDictionaryWordGeneratorTest(String name) {
        super(name);
    }

   /**
    * This method is the setup for each testcase.
    */
    protected void setUp() throws Exception {
        super.setUp();
        this.composeDictionaryWordGenerator = 
            new ComposeDictionaryWordGenerator(new ArrayDictionary(wordList));
    }

    /**
     * This testcase verify if the class works normaly.
     */
    public void testGetWordIntegerLocale() {
        for(int i=0;i<lengths.length;i++){
            String test = this.composeDictionaryWordGenerator.getWord(
                new Integer(this.lengths[i]),Locale.FRENCH);
            assertNotNull(test);
            assertTrue(test.length() > 0);
            assertEquals(lengths[i],test.length());       
        }
    }
    
    /**
     * This testcase werify if it works with words with one letter in the
     * dictionary.
     */
    public void testSmallWordWithOneLetter() {
        this.composeDictionaryWordGenerator = 
            new ComposeDictionaryWordGenerator(new ArrayDictionary(this.badwordList));
        for(int i=0;i<badlengths.length;i++){
                            String test = this.composeDictionaryWordGenerator.getWord(
                                new Integer(this.badlengths[i]),Locale.FRENCH);
                            assertNotNull(test);
                            assertTrue(test.length() > 0);
                            assertEquals(badlengths[i],test.length());       
                        }
    }
    
    /**
     * this testcase verify if it works with words with no words in the
     * dictionnary. We verify if the CaptchaException is correctly trapped.
     */
    public void testEmptyDictionnary() {
        this.composeDictionaryWordGenerator = 
            new ComposeDictionaryWordGenerator(new ArrayDictionary(this.emptywordList));
        try {
            String test = this.composeDictionaryWordGenerator.getWord(
                            new Integer(this.emptylength),Locale.FRENCH);
            fail("Shouldn't use empty dictionnary");
        }catch(CaptchaException e) {
            assertNotNull(e);
        }
    }
}