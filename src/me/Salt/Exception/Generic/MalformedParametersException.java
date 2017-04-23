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

package me.Salt.Exception.Generic;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class MalformedParametersException extends GenericBotException {
    public MalformedParametersException() {
        super();
    }

    public MalformedParametersException(String message) {
        super(message);
    }

    public MalformedParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedParametersException(Throwable cause) {
        super(cause);
    }
}