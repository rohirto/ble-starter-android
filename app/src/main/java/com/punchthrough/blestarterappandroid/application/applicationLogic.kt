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

class ApplicationLogic {
    /* Add methods to Process Data and Send to MqTT*/
    public val appData = applicationData
    public fun processData(byteData: ByteArray) {
        var stringData = ""
        try {
             stringData = String(byteData).substringBefore("\n")
        }catch( ex: IndexOutOfBoundsException){
            //Out of bounds
        }

        /* Tag Data */
        val compByte : Byte = 9
        if(byteData[1] == compByte){
            try{
                val lstValues: List<String> = stringData.split("\t")
                appData.tagType = lstValues[0].toInt()
                //appData.tagType = stringData.substring(0).toInt()
                var tempByte1 = (lstValues[1].toInt()/100).toString()
                var tempByte2 = (lstValues[1].toInt()%100).toString()
                var stringTmp = "$tempByte1.$tempByte2"
                appData.temperature = stringTmp.toFloat()
                //val buffer = ByteBuffer.wrap(tempByte).int
                //val tempFloat = buffer.getFloat(2)
                tempByte1 = (lstValues[2].toInt()/100).toString()
                tempByte2 = (lstValues[2].toInt()%100).toString()
                stringTmp = "$tempByte1.$tempByte2"
                appData.pressure = stringTmp.toFloat()
                tempByte1 = lstValues[3].trim()
                //tempByte2 = (lstValues[3].toInt()%100).toString()
                stringTmp = tempByte1
                appData.batt = stringTmp.toInt()
                appData.frameType = 1    //Tag Data

                Log.d(
                    "APP",
                    "${lstValues[3]}"
                )

                appData.MqttPayload = "{\"Tag_Type\":${appData.tagType}, \"Temperature\":${appData.temperature}, \"Pressure\":${appData.pressure}, \"Battery\":${appData.batt}}"


            }catch(ex: NumberFormatException){
                Log.d(
                    "APP",
                    "Number Format Error!"
                )

            }
            // Its a Tag Data String

        }
        else if(byteData[10] == compByte)
        {
            try{
                //Its a GPS data String
                val lstValues: List<String> = stringData.split("\t")
                appData.date = lstValues[0]
                appData.time = lstValues[1]
                appData.lat = lstValues[2].toFloat()
                appData.long = lstValues[3].toFloat()
                appData.frameType = 2 //GPS Data
                Log.d(
                    "APP",
                    "Date: ${appData.date} Time: ${appData.time} Latitude: ${appData.lat} Longitude: ${appData.long}"
                )
                appData.MqttPayload = "{\"Latitude\":${appData.lat}, \"Longitude\":${appData.long}}"


            }catch(ex: NumberFormatException){
                Log.d(
                    "APP",
                    "Number Format Error!"
                )

            }
        }

    }
    public fun initMQTT(){


    }
    public fun sendData(){
        //Send Data to cloud over MQTT
    }

}