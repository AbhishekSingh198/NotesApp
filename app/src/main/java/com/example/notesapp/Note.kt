package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Notes")
class Note (@ColumnInfo(name="note_title") val text:String,
    @ColumnInfo(name = "sub_title") var subTitle: String? = null,

    @ColumnInfo(name = "date_time") var dateTime: String? = null,

    @ColumnInfo(name = "note_text") var noteText: String? = null
):Serializable{
    @PrimaryKey(autoGenerate = true) var id=0
}