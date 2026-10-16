package com.example.questtugasuserinput_0136

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBiodata() {
    val horizontalPadding = dimensionResource(R.dimen.screen_padding)
    val fieldSpacing = dimensionResource(R.dimen.form_spacing)
    val headerHeight = dimensionResource(R.dimen.header_height)
    val cardPadding = dimensionResource(R.dimen.card_padding)

    val purple = colorResource(R.color.purple_primary)
    val borderColor = colorResource(R.color.form_border)
    val hintColor = colorResource(R.color.form_hint)
    val disabledColor = colorResource(R.color.form_disabled)
    val screenBackground = colorResource(R.color.screen_background)

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }

    var experienceExpanded by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }

    // Popup hilang otomatis setelah 5 detik.
    LaunchedEffect(showResult) {
        if (showResult) {
            delay(5000)
            showResult = false
        }
    }

    val experienceOptions = listOf(
        "Beginner",
        "Intermediate",
        "Advanced"
    )

    val calendar = Calendar.getInstance()

    val datePicker = DatePickerDialog(
        androidx.compose.ui.platform.LocalContext.current,
        { _, year, month, day ->
            birthDate = "%02d/%02d/%04d".format(
                day,
                month + 1,
                year
            )
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val validEmail = android.util.Patterns.EMAIL_ADDRESS
        .matcher(email)
        .matches()

    val isFormValid =
        firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                validEmail &&
                birthDate.isNotBlank() &&
                experience.isNotBlank() &&
                (androidSkill || uiuxSkill || webSkill)

    Scaffold(
        containerColor = screenBackground
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(screenBackground)
        ) {

            Image(
                painter = painterResource(R.drawable.header_neon),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = horizontalPadding)
                    .padding(bottom = cardPadding)
                    .offset(y = headerHeight - 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(
                        dimensionResource(R.dimen.form_corner_radius)
                    ),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = colorResource(R.color.form_white)
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(cardPadding),
                        verticalArrangement = Arrangement.spacedBy(fieldSpacing)
                    ) {

                        Text(
                            text = stringResource(R.string.form_title),
                            modifier = Modifier.fillMaxWidth(),
                            color = purple,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Text(
                            text = stringResource(R.string.form_description),
                            modifier = Modifier.fillMaxWidth(),
                            color = hintColor,
                            fontSize = 10.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(fieldSpacing))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(fieldSpacing)
                        ) {
                            FormTextField(
                                value = firstName,
                                onValueChange = { firstName = it },
                                label = stringResource(R.string.first_name),
                                modifier = Modifier.weight(1f),
                                borderColor = borderColor
                            )

                            FormTextField(
                                value = lastName,
                                onValueChange = { lastName = it },
                                label = stringResource(R.string.last_name),
                                modifier = Modifier.weight(1f),
                                borderColor = borderColor
                            )
                        }

                        FormTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = stringResource(R.string.email),
                            modifier = Modifier.fillMaxWidth(),
                            borderColor = borderColor,
                            keyboardType = KeyboardType.Email
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(fieldSpacing)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                OutlinedTextField(
                                    value = birthDate,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            datePicker.show()
                                        },
                                    readOnly = true,
                                    singleLine = true,
                                    label = {
                                        Text(
                                            stringResource(R.string.birth_date),
                                            fontSize = 10.sp
                                        )
                                    },
                                    trailingIcon = {
                                        Text(
                                            text = "▦",
                                            modifier = Modifier.clickable {
                                                datePicker.show()
                                            }
                                        )
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = purple,
                                        unfocusedBorderColor = borderColor
                                    )
                                )

                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .clickable {
                                            datePicker.show()
                                        }
                                )
                            }

                            Box(modifier = Modifier.weight(1f)) {
                                OutlinedTextField(
                                    value = experience,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            experienceExpanded = true
                                        },
                                    readOnly = true,
                                    singleLine = true,
                                    label = {
                                        Text(
                                            stringResource(R.string.experience),
                                            fontSize = 10.sp
                                        )
                                    },
                                    trailingIcon = {
                                        Text(
                                            text = "⌄",
                                            modifier = Modifier.clickable {
                                                experienceExpanded = true
                                            }
                                        )
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = purple,
                                        unfocusedBorderColor = borderColor
                                    )
                                )

                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .clickable {
                                            experienceExpanded = true
                                        }
                                )

                                DropdownMenu(
                                    expanded = experienceExpanded,
                                    onDismissRequest = {
                                        experienceExpanded = false
                                    }
                                ) {
                                    experienceOptions.forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(option) },
                                            onClick = {
                                                experience = option
                                                experienceExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Text(
                            text = stringResource(R.string.skill_title),
                            color = colorResource(R.color.form_text),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SkillCheckbox(
                                label = stringResource(R.string.skill_android),
                                checked = androidSkill,
                                onCheckedChange = { androidSkill = it }
                            )

                            SkillCheckbox(
                                label = stringResource(R.string.skill_uiux),
                                checked = uiuxSkill,
                                onCheckedChange = { uiuxSkill = it }
                            )

                            SkillCheckbox(
                                label = stringResource(R.string.skill_web),
                                checked = webSkill,
                                onCheckedChange = { webSkill = it }
                            )
                        }

                        Button(
                            onClick = {
                                showResult = true
                            },
                            enabled = isFormValid,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = purple,
                                disabledContainerColor = disabledColor,
                                contentColor = Color.White,
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.proceed),
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }
    }

    // Popup hanya menampilkan data biodata.
    if (showResult && isFormValid) {
        AlertDialog(
            onDismissRequest = {
                showResult = false
            },
            title = {
                Text(
                    text = "$firstName $lastName",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Email: $email",
                        color = Color.White
                    )

                    Text(
                        text = "Tanggal Lahir: $birthDate",
                        color = Color.White
                    )

                    Text(
                        text = "Experience: $experience",
                        color = Color.White
                    )

                    Text(
                        text = "Skill: " + listOfNotNull(
                            if (androidSkill) "Android" else null,
                            if (uiuxSkill) "UI/UX" else null,
                            if (webSkill) "Web" else null
                        ).joinToString(),
                        color = Color.White
                    )
                }
            },
            confirmButton = {},
            containerColor = Color(0xFF29292F),
            titleContentColor = Color.White,
            textContentColor = Color.White,
            shape = RoundedCornerShape(28.dp)
        )
    }
}

@Composable
private fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    borderColor: Color,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(
                text = label,
                fontSize = 10.sp
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.purple_primary),
            unfocusedBorderColor = borderColor
        )
    )
}

@Composable
private fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(30.dp)
        )

        Text(
            text = label,
            fontSize = 9.sp
        )
    }
}
