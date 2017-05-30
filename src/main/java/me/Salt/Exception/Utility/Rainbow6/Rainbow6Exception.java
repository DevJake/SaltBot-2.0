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
package me.Salt.Exception.Utility.Rainbow6;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.R6Error;

/**
 * Thrown when the Rainbow Six Siege Statistics utility tool encounters an exception.
 * <p>
 * Due to the nature of returned exceptions being very variant in their Json structure, an internal {@link R6Error}
 * container was required.
 */
public class Rainbow6Exception extends Exception {
    /**
     * The message of the exception.
     */
    private String message;
    /**
     * The {@link R6Error} container for this exception.
     */
    private R6Error error;
    
    public Rainbow6Exception(R6Error error) {
        this.error = error;
    }
    
    public Rainbow6Exception(String message, R6Error error) {
        super(message);
        this.error = error;
    }
    
    /**
     * @return {@link R6Error} - This exception's error container
     */
    public R6Error getError() {
        return error;
    }
}
