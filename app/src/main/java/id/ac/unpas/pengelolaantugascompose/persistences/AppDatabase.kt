package id.ac.unpas.pengelolaantugascompose.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.pengelolaantugascompose.model.InputDataTugas

@Database(entities = [InputDataTugas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inputDataTugasDao(): InputDataTugasDao
}