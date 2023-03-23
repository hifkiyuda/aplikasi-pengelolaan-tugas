package id.ac.unpas.pengelolaantugascompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InputDataTugas(
    @PrimaryKey val id: String,
    val tanggal: String,
    val judul: String,
    val deskripsi: String,
    val deadline: String
)
