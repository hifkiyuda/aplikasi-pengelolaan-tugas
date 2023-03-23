package id.ac.unpas.pengelolaantugascompose.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.pengelolaantugascompose.model.InputDataTugas
import id.ac.unpas.pengelolaantugascompose.persistences.InputDataTugasDao
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanTugas(inputDataTugasDao: InputDataTugasDao) {
    val context = LocalContext.current
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val judul = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val deadline = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {

        Text(
            text = "Aplikasi Pengelolaan Tugas",
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
        )

        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = tanggal.value,
            onValueChange = {
                tanggal.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )

        OutlinedTextField(
            label = { Text(text = "Judul") },
            value = judul.value,
            onValueChange = {
                judul.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Masukkan judul tugas") }
        )

        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Masukkan deskripsi tugas") }
        )

        OutlinedTextField(
            label = { Text(text = "Deadline") },
            value = deadline.value,
            onValueChange = {
                deadline.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )

        val buttonSimpanTugas = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue
        )

        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red
        )

        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {

            Button(modifier = Modifier.weight(5f), onClick = {
                if (tanggal.value.text.isBlank() || judul.value.text.isBlank() || deskripsi.value.text.isBlank() || deadline.value.text.isBlank()) {
                    Toast.makeText(context, "Silakan isi form dengan lengkap", Toast.LENGTH_SHORT).show()
                } else {
                    val id = uuid4().toString()
                    val item = InputDataTugas(
                        id,
                        tanggal.value.text,
                        judul.value.text,
                        deskripsi.value.text,
                        deadline.value.text
                    )

                    scope.launch {
                        inputDataTugasDao.insertAll(item)
                    }
                    tanggal.value = TextFieldValue("")
                    judul.value = TextFieldValue("")
                    deskripsi.value = TextFieldValue("")
                    deadline.value = TextFieldValue("")
                    Toast.makeText(context, "Tugas berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                }
            }, colors = buttonSimpanTugas) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                tanggal.value = TextFieldValue("")
                judul.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
                deadline.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}