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

import android.annotation.SuppressLint
import android.util.Log
import com.punchthrough.blestarterappandroid.application.applicationData
import java.nio.ByteBuffer
import timber.log.Timber

class applicationLogic {
    /* Add methods to Process Data and Send to MqTT*/
    @SuppressLint("LogNotTimber")
    public fun processData(byteData: ByteArray) {
        val stringData = String(byteData)
        val appData = applicationData
        /* Tag Data */
        val compByte : Byte = 9
        if(byteData[1] == compByte){
            // Its a Tag Data String
            val lstValues: List<String> = stringData.split("\t")
            appData.tagType = lstValues[0].toInt()
            //appData.tagType = stringData.substring(0).toInt()
            val tempByte1 = (lstValues[1].toInt()/100).toString()
            val tempByte2 = (lstValues[1].toInt()%100).toString()

            val stringTmp = "$tempByte1.$tempByte2"
            //val buffer = ByteBuffer.wrap(tempByte).int
            //val tempFloat = buffer.getFloat(2)
            appData.temperature = stringTmp.toFloat()
            Log.d(
                "APP",
                "Tag Type: ${appData.tagType} Temperature: $stringTmp byte1: $tempByte1 byte2: $tempByte2"
            )

        }

    }

}