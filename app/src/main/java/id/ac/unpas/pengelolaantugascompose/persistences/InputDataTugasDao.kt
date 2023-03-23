package id.ac.unpas.pengelolaantugascompose.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.pengelolaantugascompose.model.InputDataTugas

@Dao
interface InputDataTugasDao {
    @Query("SELECT * FROM InputDataTugas")
    fun loadAll(): LiveData<List<InputDataTugas>>
    @Query("SELECT * FROM InputDataTugas WHERE id = :id")
    fun find(id: String): InputDataTugas?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: InputDataTugas)
    @Delete
    fun delete(item: InputDataTugas)
}