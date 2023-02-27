package com.chslcompany.dictionary.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.chslcompany.dictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningJson(json : String) : List<Meaning> =
       jsonParser.fromJson<ArrayList<Meaning>>(
           json = json,
           type = object:TypeToken<ArrayList<Meaning>>(){}.type
       ) ?: emptyList()

    @TypeConverter
    fun toMeaningJson(meanings : List<Meaning>) : String =
        jsonParser.toJson(
            obj = meanings,
            type = object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
}