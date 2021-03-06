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
package com.octo.captcha.image;

import java.awt.image.BufferedImage;

import junit.framework.TestCase;

import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.image.gimpy.WordGenerator;
import com.octo.captcha.image.gimpy.WordToImage;
import com.octo.captcha.image.gimpy.wordgenerator.DummyWordGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.SimpleWordToImage;

/**
 * <p>Description: </p>
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class ImageCaptchaTest extends TestCase {
    private ImageCaptcha pixCaptcha;

    /**
     * Constructor for ImageCaptchaTest.
     * @param name
     */
    public ImageCaptchaTest(String name) {
        super(name);
    }

    /**
     * this method is for initialisation for all the test cases
     */
    public void setUp() throws Exception {
        super.setUp();

        WordGenerator words = new DummyWordGenerator("TESTING");
        WordToImage word2image = new SimpleWordToImage();
        ImageCaptchaFactory factory = new GimpyFactory(words, word2image);
        pixCaptcha = factory.getImageCaptcha();
    }

    /**
     * This test is for verifying if the question of the captcha
     * is correctly instantiated.
     */
    public void testGetQuestion() {
        assertNotNull(pixCaptcha.getQuestion());
    }

    /**
     * This test is for verifying if the challenge of the captcha
     * is correctly instantiated.
     */
    public void testGetChallenge() {
        assertNotNull(pixCaptcha.getChallenge());
        assertTrue("Captcha challenge is not a BufferedImage",pixCaptcha.getImageChallenge() instanceof BufferedImage);
    }

    /**
     * This test is for verifying if the response of the captcha
     * is valid.
     */
//    public void testValidateResponse() {
//        assertTrue(pixCaptcha.validateResponse(pixCaptcha.getResponse()).booleanValue());
//    }



    public void testGetImageChallenge() throws Exception
    {
        assertEquals(pixCaptcha.getImageChallenge(),pixCaptcha.getChallenge());
    }
    
    /**
     * This test is for verifying if the question of the captcha
     * is correctly instantiated.
     */
//    public void testGetResponse() {
//        assertNotNull(pixCaptcha.getResponse());
//    }
}
