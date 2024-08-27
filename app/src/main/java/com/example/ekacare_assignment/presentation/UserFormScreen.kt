import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ekacare_assignment.R
import com.example.ekacare_assignment.presentation.UserViewModel
import com.example.ekacare_assignment.ui.theme.Blue
import com.example.ekacare_assignment.ui.theme.Black
import com.example.ekacare_assignment.ui.theme.Grey
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(viewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val validationError by viewModel.validationError.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val userCount by viewModel.userCount.collectAsState()

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text(text = "Success") },
            text = {
                Text(text = "Data saved successfully!" + "\n" + "Total Users data saved locally: $userCount")
            }
        )
    }

    val poppinsFont = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Bold),
        Font(R.font.poppins_regular, FontWeight.Light)
    )

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Tell Us About You!",
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(top = 30.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Please provide all the required information",
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                if (it.length <= 30) {
                    name = it
                }
            },
            label = { Text("Name", fontFamily = poppinsFont, fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Grey,
                unfocusedBorderColor = Black,
                focusedLabelColor = Grey,
                unfocusedLabelColor = Black,
                focusedTrailingIconColor = Grey,
                unfocusedTrailingIconColor = Black,
                focusedTextColor = Grey,
                unfocusedTextColor = Black,
                cursorColor = Grey
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = age,
            onValueChange = {
                if (it.length <= 3) {
                    age = it
                }
            },
            label = { Text("Age", fontFamily = poppinsFont, fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Grey,
                unfocusedBorderColor = Black,
                focusedLabelColor = Grey,
                unfocusedLabelColor = Black,
                focusedTrailingIconColor = Grey,
                unfocusedTrailingIconColor = Black,
                focusedTextColor = Grey,
                unfocusedTextColor = Black,
                cursorColor = Grey
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = dob,
            onValueChange = { },
            label = {
                Text(
                    "Date of Birth",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Date"
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Grey,
                unfocusedBorderColor = Black,
                focusedLabelColor = Grey,
                unfocusedLabelColor = Black,
                focusedTrailingIconColor = Grey,
                unfocusedTrailingIconColor = Black,
                focusedTextColor = Grey,
                unfocusedTextColor = Black,
                cursorColor = Grey
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = address,
            onValueChange = {
                if (it.length <= 80) {
                    address = it
                }
            },
            label = { Text("Address", fontFamily = poppinsFont, fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
            maxLines = 3,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Grey,
                unfocusedBorderColor = Black,
                focusedLabelColor = Grey,
                unfocusedLabelColor = Black,
                focusedTrailingIconColor = Grey,
                unfocusedTrailingIconColor = Black,
                focusedTextColor = Grey,
                unfocusedTextColor = Black,
                cursorColor = Grey
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Display validation error if any
        validationError?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                if (viewModel.validateAndInsertUser(name, age, dob, address)) {
                    name = ""
                    age = ""
                    dob = ""
                    address = ""
                    showDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text(
                text = "Save",
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Color.White
            )
        }

        if (showDatePicker) {
            val datePickerDialog = DatePickerDialog(
                context,
                R.style.CustomDatePickerDialog,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    dob = dateFormat.format(selectedDate.time)
                    showDatePicker = false
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.setOnDismissListener {
                showDatePicker = false
            }

            datePickerDialog.show()
        }
    }
}