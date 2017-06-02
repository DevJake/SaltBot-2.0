/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.Salt.Util.Utility.Games.CardsAgainstDiscord.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CaDUtilTest {
    @Test
    public void getRandomWhiteCardNotNull() throws Exception {
        assertNotNull(CaDUtil.getRandomWhiteCard());
    }
    
    @Test
    public void getRandomBlackCardNotNull() throws Exception {
        assertNotNull(CaDUtil.getRandomBlackCard());
    }
    
    @Test
    public void getRandomWhiteCardsNotNull() throws Exception {
        assertNotNull(CaDUtil.getRandomWhiteCards(4));
    }
    
    @Test
    public void getRandomBlackCardsNotNull() throws Exception {
        assertNotNull(CaDUtil.getRandomBlackCards(4));
    }
    
    @Test
    public void getRandomWhiteCardsSizeCheck() throws Exception {
        assertEquals(4, CaDUtil.getRandomWhiteCards(4).size());
    }
    
    @Test
    public void getRandomBlackCardsSizeCheck() throws Exception {
        assertEquals(4, CaDUtil.getRandomBlackCards(4).size());
    }
    
    @Test
    public void getRandomWhiteCardsExpectNull() throws Exception {
        Assert.assertNull(CaDUtil.getRandomWhiteCards(999999999));
    }
    
    @Test
    public void getRandomBlackCardsExpectNull() throws Exception {
        Assert.assertNull(CaDUtil.getRandomBlackCards(999999999));
    }
}