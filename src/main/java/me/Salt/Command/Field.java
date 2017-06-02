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
package me.Salt.Command;

/**
 * This class acts as a container for command arguments. It allows for command arguments to be automagically managed
 * by a set of utilities, so that the command need only focus on <i>operation</i>, rather than painstaking argument
 * handling.
 */
public class Field {
    /**
     * The text that represents this parameter.
     */
    private String text;
    /**
     * The description of this parameter.
     */
    private String description;
    /**
     * A boolean representing if this parameter is required.
     */
    private boolean required;
    /**
     * The {@link Type} of parameter that this is.
     */
    private Type type;
    /**
     * The parameter's 'depth' is a value determining what argument position it must have. A depth of '2' indicates
     * that the second argument entered should be this field. A depth of '0' indicates that this argument can be at
     * <i>any</i> position in the entered text.
     */
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
        /**
         * A regular, good-old parameter
         */
        PARAMETER,
        /**
         * A parameter that follows the format of <code>-text:</code>, immediately followed by text, without a space.
         */
        DEFINER
    }
    // TODO: 29/05/2017 Perhaps auto-determine definers based on the type of data expected.
}
