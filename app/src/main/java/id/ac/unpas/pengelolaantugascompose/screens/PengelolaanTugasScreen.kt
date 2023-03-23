package id.ac.unpas.pengelolaantugascompose.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import  androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.pengelolaantugascompose.model.InputDataTugas
import id.ac.unpas.pengelolaantugascompose.persistences.AppDatabase
import id.ac.unpas.pengelolaantugascompose.ui.theme.Green500
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PengelolaanTugasScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pengelolaan-tugas"
    ).build()
    val inputDataTugasDao = db.inputDataTugasDao()

    val list : LiveData<List<InputDataTugas>> = inputDataTugasDao.loadAll()
    val items : List<InputDataTugas> by list.observeAsState(initial = listOf())
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanTugas(inputDataTugasDao)

        Divider(modifier = Modifier.fillMaxWidth())

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {

                    Column(modifier = Modifier.weight(3f)) {
                        Row(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()) {

                            Column(Modifier.weight(2f)) {
                                Text(text = "Tanggal", fontSize = 15.sp)
                            }
                            Column(Modifier.weight(4f)) {
                                Text(text = ": " + item.tanggal, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Row(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()) {

                            Column(Modifier.weight(2f)) {
                                Text(text = "Judul", fontSize = 15.sp)
                            }
                            Column(Modifier.weight(4f)) {
                                Text(text = ": " + item.judul, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Row(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()) {

                            Column(Modifier.weight(2f)) {
                                Text(text = "Deskripsi", fontSize = 15.sp)
                            }
                            Column(Modifier.weight(4f)) {
                                Text(text = ": " + item.deskripsi, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Row(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()) {

                            Column(Modifier.weight(2f)) {
                                Text(text = "Deadline", fontSize = 15.sp)
                            }
                            Column(Modifier.weight(4f)) {
                                Text(text = ": " + item.deadline, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        val buttonHapusTugas = ButtonDefaults.buttonColors(
                            backgroundColor = Green500
                        )

                        Row(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()) {

                            Button(modifier = Modifier.weight(5f), onClick = {
                                scope.launch {
                                    withContext(Dispatchers.IO) {
                                        inputDataTugasDao.delete(item)
                                    }
                                }
                                Toast.makeText(context, "Tugas berhasil dihapus", Toast.LENGTH_SHORT).show()
                            }, colors = buttonHapusTugas) {
                                Text(
                                    text = "Hapus",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 15.sp
                                    ), modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}