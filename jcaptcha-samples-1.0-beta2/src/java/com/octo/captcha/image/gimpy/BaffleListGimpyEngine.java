
package com.octo.captcha.image.gimpy;

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

import com.octo.captcha.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.wordgenerator.FileDictionnary;
import com.octo.captcha.image.gimpy.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.BackgroundGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.ComposedWordToImage;
import com.octo.captcha.image.gimpy.wordtoimage.FontGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.TextPaster;
import com.octo.captcha.image.gimpy.wordtoimage.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.fontgenerator.RandomFontGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.textpaster.BaffleRandomTextPaster;
import com.octo.captcha.image.gimpy.WordGenerator;
import com.octo.captcha.image.gimpy.WordToImage;
import com.octo.captcha.image.gimpy.GimpyFactory;

import java.awt.Color;

/**
 * <p>Description: </p>
 * @author <a href="mailto:mag@octo.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class BaffleListGimpyEngine extends ListImageCaptchaEngine
{

    protected void buildInitialFactories()
    {
        //word generator
        WordGenerator dictionnaryWords = new ComposeDictionaryWordGenerator(new FileDictionnary("toddlist"));
        //wordtoimage components
        TextPaster randomPaster = new BaffleRandomTextPaster(new Integer(8), new Integer(15), Color.BLACK,new Integer(3),Color.BLUE);
        BackgroundGenerator back = new UniColorBackgroundGenerator(new Integer(200), new Integer(100),Color.BLUE);
        //BackgroundGenerator back = new FunkyBackgroundGenerator(new Integer(200), new Integer(100));
        FontGenerator shearedFont = new RandomFontGenerator(new Integer(20), new Integer(25));
        //word2image 1
        WordToImage word2image = new ComposedWordToImage(shearedFont, back, randomPaster);
        
        
        this.addFactory(new GimpyFactory(dictionnaryWords, word2image));
    }
}
