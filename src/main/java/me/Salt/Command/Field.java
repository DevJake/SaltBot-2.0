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

package main.java.me.Salt.Command;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 17/04/2017.
 * <p>
 * Used for help commands
 */
public class Field {
    private String text;
    private String description;
    private boolean required;
    private Type type;
    private int depth; //The 'depth' of the argument. Depth 2 would indicate it must be the second argument. A depth of 0 indicates it must merely be present in the text.

    public Field(String text, String description, boolean required, Type type) {

        this.text = text;
        this.description = description;
        this.required = required;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return required;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        PARAMETER,
        DEFINER
    }
}
