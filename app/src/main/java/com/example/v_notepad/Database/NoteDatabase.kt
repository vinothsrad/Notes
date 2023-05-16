package com.example.v_notepad.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.v_notepad.Models.Note
import com.example.v_notepad.utilities.DATABASE_NAME
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


/*@Database(entities = [Note::class],version = 2,exportSchema = false)
abstract class NoteDatabase : RoomDatabase()
{
    abstract fun getNoteDao():NoteDao

    @OptIn(InternalCoroutinesApi::class)
    companion object{
        private const val DATABASE_NAME="NoteDatabase"

        @Volatile
        var instance:NoteDatabase?=null

        fun getDataBase(context: Context):NoteDatabase?
        {
            if(instance == null)
            {
                synchronized(NoteDatabase::class.java)
                {
                    if(instance == null)
                    {
                        instance= Room.databaseBuilder(context,NoteDatabase::class.java,
                            DATABASE_NAME).build()
                    }
                }
            }

            return instance
        }
    }
}*/
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context): NoteDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}