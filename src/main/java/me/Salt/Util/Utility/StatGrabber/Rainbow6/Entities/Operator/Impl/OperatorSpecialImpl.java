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
package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Operator.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Operator.OperationSpecial;

public class OperatorSpecialImpl implements OperationSpecial {
    private String raw; //Typical incoming data might be "operatorpvp_glaz_sniperkill": "92"
    private String operatorName; //TODO perhaps add builder class
    private String specialName;
    private int value;
    private String description;
    
    public OperatorSpecialImpl(String raw, String operatorName, String specialName, int value, String description) {
        this.raw = raw;
        this.operatorName = operatorName;
        this.specialName = specialName;
        this.value = value;
        this.description = description;
    }
    
    @Override
    public String getRaw() {
        return raw;
    }
    
    @Override
    public String getOperatorName() {
        return operatorName;
    }
    
    @Override
    public String getSpecialName() {
        return specialName;
    }
    
    @Override
    public int getValue() {
        return value;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}
