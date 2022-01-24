/*
 * Copyright 2022 Punch Through Design LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.punchthrough.blestarterappandroid.application

object applicationData {
    /* TPMS Tag Data*/
    public var temperature: Float = 0F
    public var pressure: Float = 0F
    public var batt: Int = 0
    public var tagType: Int = 0       //Front or rear

    /* GPS Data */
    public var lat: Float = 0F
    public var long: Float = 0F
    public var date: String = ""
    public var time: String = ""

    public var frameType: Int = 0       //If 1 then TPMS Tag, If 2 then GPS tag
    public var MqttPayload: String =""
    public fun isTag(): Int {
        return frameType
    }


}