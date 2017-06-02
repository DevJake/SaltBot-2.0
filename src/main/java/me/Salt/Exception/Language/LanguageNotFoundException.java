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
package me.Salt.Exception.Language;

/**
 * Thrown when no language files can be found, or a language is missing required data.
 */
public class LanguageNotFoundException extends LanguageException {
    public LanguageNotFoundException() {
        super();
    }

    public LanguageNotFoundException(String message) {
        super(message);
    }

    public LanguageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LanguageNotFoundException(Throwable cause) {
        super(cause);
    }
}
