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

package com.octo.captcha.image.gimpy;

import com.octo.captcha.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.wordgenerator.DictionaryWordGenerator;
import com.octo.captcha.image.gimpy.wordgenerator.FileDictionnary;
import com.octo.captcha.image.gimpy.wordtoimage.BackgroundGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.ComposedWordToImage;
import com.octo.captcha.image.gimpy.wordtoimage.FontGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.TextPaster;
import com.octo.captcha.image.gimpy.wordtoimage.FilteredComposedWordToImage;
import com.octo.captcha.image.gimpy.wordtoimage.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.octo.captcha.image.gimpy.wordtoimage.textpaster.BaffleRandomTextPaster;
import com.jhlabs.image.EmbossFilter;
import com.jhlabs.image.SphereFilter;
import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.TwirlFilter;
import com.jhlabs.image.WaterFilter;
import com.jhlabs.image.MarbleFilter;
import com.jhlabs.image.WeaveFilter;
import com.jhlabs.image.CrystalizeFilter;
import com.jhlabs.image.TransformFilter;

import java.awt.Color;
import java.awt.image.ImageFilter;

/**
 * <p>Description: </p>
 * @author <a href="mailto:mag@octo.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class FilteredBaffleListGimpyEngine extends ListImageCaptchaEngine
{

    protected void buildInitialFactories()
    {


           //build filters
        EmbossFilter emboss = new EmbossFilter();
        SphereFilter sphere = new SphereFilter();
        RippleFilter rippleBack = new RippleFilter();
        RippleFilter ripple = new RippleFilter();
        TwirlFilter twirl = new TwirlFilter();
        WaterFilter water = new WaterFilter();
        MarbleFilter marble = new MarbleFilter();
        WeaveFilter weaves = new WeaveFilter();
        CrystalizeFilter crystal = new CrystalizeFilter();

        emboss.setBumpHeight(2.0f);

        ripple.setWaveType(RippleFilter.NOISE);
        ripple.setXAmplitude(3);
        ripple.setYAmplitude(3);
        ripple.setXWavelength(20);
        ripple.setYWavelength(10);
        ripple.setEdgeAction(TransformFilter.CLAMP);

        rippleBack.setWaveType(RippleFilter.NOISE);
        rippleBack.setXAmplitude(5);
        rippleBack.setYAmplitude(5);
        rippleBack.setXWavelength(10);
        rippleBack.setYWavelength(10);
        rippleBack.setEdgeAction(TransformFilter.CLAMP);

        water.setAmplitude(5);
        water.setAntialias(true);
        water.setWavelength(10);

        twirl.setAngle(3 / 360);

        sphere.setRefractionIndex(1);

        weaves.setUseImageColors(true);

        crystal.setScale(0.5f);
        crystal.setGridType(CrystalizeFilter.RANDOM);
        crystal.setFadeEdges(false);
        crystal.setEdgeThickness(0.2f);
        crystal.setRandomness(0.1f);


        //word generator
         WordGenerator words = new DictionaryWordGenerator(new FileDictionnary("toddlist"));
         //wordtoimage components
         TextPaster paster = new BaffleRandomTextPaster(new Integer(6), new Integer(8), Color.BLACK,new Integer(3),
                 Color.WHITE);
         BackgroundGenerator back = new UniColorBackgroundGenerator(new Integer(200), new Integer(100),Color.WHITE);
         //BackgroundGenerator back = new FunkyBackgroundGenerator(new Integer(200), new Integer(100));
         FontGenerator font = new TwistedAndShearedRandomFontGenerator(new Integer(30), new Integer(40));
        //Add factories
         WordToImage word2image = new ComposedWordToImage(font, back, paster);
        this.addFactory(new GimpyFactory(words, word2image));
        //build factories
        word2image = new FilteredComposedWordToImage(font, back, paster, new ImageFilter[]{water}, new ImageFilter[]{emboss}, new ImageFilter[]{ripple});
        this.addFactory(new GimpyFactory(words, word2image));
        //select filters for 2
        word2image = new FilteredComposedWordToImage(font, back, paster, new ImageFilter[]{rippleBack}, new ImageFilter[]{crystal}, new ImageFilter[]{ripple});
        this.addFactory(new GimpyFactory(words, word2image));
        //select filters for 3
        word2image = new FilteredComposedWordToImage(font, back, paster, new ImageFilter[]{rippleBack}, new ImageFilter[]{}, new ImageFilter[]{weaves});
        this.addFactory(new GimpyFactory(words, word2image));

    }
}
