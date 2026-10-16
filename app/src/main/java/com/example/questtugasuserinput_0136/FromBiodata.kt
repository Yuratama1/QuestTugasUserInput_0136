package com.example.questtugasuserinput_0136

import android.app.DatePickerDialog
import android.util.Patterns
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBiodata() {
    val horizontalPadding = dimensionResource(R.dimen.screen_padding)
    val fieldSpacing = dimensionResource(R.dimen.form_spacing)
    val headerHeight = dimensionResource(R.dimen.header_height)
    val cardPadding = dimensionResource(R.dimen.card_padding)
    val overlap = dimensionResource(R.dimen.card_overlap)

    val purple = colorResource(R.color.purple_primary)
    val borderColor = colorResource(R.color.form_border)
    val hintColor = colorResource(R.color.form_hint)
    val disabledColor = colorResource(R.color.form_disabled)
    val screenBackground = colorResource(R.color.screen_background)
    val textColor = colorResource(R.color.form_text)
    val white = colorResource(R.color.form_white)
    val popupBackground = colorResource(R.color.popup_background)

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

    // Popup otomatis menghilang setelah 5 detik.
    LaunchedEffect(showResult) {
        if (showResult) {
            delay(5000L)
            showResult = false
        }
    }

    val beginner = stringResource(R.string.experience_beginner)
    val intermediate = stringResource(R.string.experience_intermediate)
    val advanced = stringResource(R.string.experience_advanced)

    val experienceOptions = listOf(
        beginner,
        intermediate,
        advanced
    )

    val context = androidx.compose.ui.platform.LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = stringResource(R.string.date_format)

    val datePicker = remember(context, dateFormat) {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                birthDate = String.format(
                    Locale.getDefault(),
                    dateFormat,
                    day,
                    month + 1,
                    year
                )
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    val validEmail = Patterns.EMAIL_ADDRESS
        .matcher(email)
        .matches()

    val isFormValid =
        firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                validEmail &&
                birthDate.isNotBlank() &&
                experience.isNotBlank() &&
                (androidSkill || uiuxSkill || webSkill)

    val skillAndroid = stringResource(R.string.skill_android)
    val skillUiux = stringResource(R.string.skill_uiux)
    val skillWeb = stringResource(R.string.skill_web)

    val selectedSkills = listOfNotNull(
        if (androidSkill) skillAndroid else null,
        if (uiuxSkill) skillUiux else null,
        if (webSkill) skillWeb else null
    ).joinToString()

    // Box menjadi lapisan utama yang mengapungkan konten di atas Scaffold.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackground)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = screenBackground
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }

        Image(
            painter = painterResource(R.drawable.header_neon),
            contentDescription = stringResource(R.string.header_description),
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = horizontalPadding)
                .offset(y = headerHeight - overlap)
                .verticalScroll(rememberScrollState())
                .padding(bottom = cardPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    dimensionResource(R.dimen.form_corner_radius)
                ),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = white
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = dimensionResource(
                        R.dimen.card_elevation
                    )
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
                        fontSize = dimensionResource(
                            R.dimen.title_text_size
                        ).value.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.form_description),
                        modifier = Modifier.fillMaxWidth(),
                        color = hintColor,
                        fontSize = dimensionResource(
                            R.dimen.description_text_size
                        ).value.sp,
                        textAlign = TextAlign.Center
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
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = dimensionResource(
                                        R.dimen.field_text_size
                                    ).value.sp,
                                    color = textColor
                                ),
                                label = {
                                    Text(
                                        stringResource(R.string.birth_date),
                                        fontSize = dimensionResource(
                                            R.dimen.field_label_size
                                        ).value.sp
                                    )
                                },
                                trailingIcon = {
                                    Text(
                                        stringResource(R.string.date_picker_icon),
                                        color = textColor
                                    )
                                },
                                shape = RoundedCornerShape(
                                    dimensionResource(
                                        R.dimen.field_corner_radius
                                    )
                                ),
                                colors = fieldColors(
                                    purple = purple,
                                    borderColor = borderColor,
                                    textColor = textColor,
                                    hintColor = hintColor,
                                    white = white
                                )
                            )

                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable { datePicker.show() }
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = experience,
                                onValueChange = {},
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = dimensionResource(
                                        R.dimen.field_text_size
                                    ).value.sp,
                                    color = textColor
                                ),
                                label = {
                                    Text(
                                        stringResource(R.string.experience),
                                        fontSize = dimensionResource(
                                            R.dimen.field_label_size
                                        ).value.sp
                                    )
                                },
                                trailingIcon = {
                                    Text(
                                        stringResource(R.string.dropdown_icon),
                                        color = textColor
                                    )
                                },
                                shape = RoundedCornerShape(
                                    dimensionResource(
                                        R.dimen.field_corner_radius
                                    )
                                ),
                                colors = fieldColors(
                                    purple = purple,
                                    borderColor = borderColor,
                                    textColor = textColor,
                                    hintColor = hintColor,
                                    white = white
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
                        color = textColor,
                        fontSize = dimensionResource(
                            R.dimen.field_label_size
                        ).value.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SkillCheckbox(
                            label = skillAndroid,
                            checked = androidSkill,
                            onCheckedChange = { androidSkill = it },
                            textColor = textColor
                        )

                        SkillCheckbox(
                            label = skillUiux,
                            checked = uiuxSkill,
                            onCheckedChange = { uiuxSkill = it },
                            textColor = textColor
                        )

                        SkillCheckbox(
                            label = skillWeb,
                            checked = webSkill,
                            onCheckedChange = { webSkill = it },
                            textColor = textColor
                        )
                    }

                    Button(
                        onClick = { showResult = true },
                        enabled = isFormValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                dimensionResource(R.dimen.button_height)
                            ),
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.field_corner_radius)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = purple,
                            disabledContainerColor = disabledColor,
                            contentColor = white,
                            disabledContentColor = white
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.proceed),
                            fontSize = dimensionResource(
                                R.dimen.field_label_size
                            ).value.sp
                        )
                    }
                }
            }
        }
    }

    // Popup hanya berisi data biodata, tanpa tombol OK.
    if (showResult && isFormValid) {
        AlertDialog(
            onDismissRequest = { showResult = false },
            title = {
                Text(
                    text = stringResource(
                        R.string.result_title,
                        firstName,
                        lastName
                    ),
                    color = white,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(fieldSpacing)
                ) {
                    Text(
                        stringResource(R.string.result_email, email),
                        color = white
                    )
                    Text(
                        stringResource(R.string.result_birth_date, birthDate),
                        color = white
                    )
                    Text(
                        stringResource(R.string.result_experience, experience),
                        color = white
                    )
                    Text(
                        stringResource(R.string.result_skill, selectedSkills),
                        color = white
                    )
                }
            },
            confirmButton = {},
            containerColor = popupBackground,
            titleContentColor = white,
            textContentColor = white,
            shape = RoundedCornerShape(
                dimensionResource(R.dimen.popup_corner_radius)
            )
        )
    }
}

@Composable
private fun fieldColors(
    purple: Color,
    borderColor: Color,
    textColor: Color,
    hintColor: Color,
    white: Color
) = OutlinedTextFieldDefaults.colors(
    focusedTextColor = textColor,
    unfocusedTextColor = textColor,
    focusedLabelColor = purple,
    unfocusedLabelColor = hintColor,
    cursorColor = purple,
    focusedBorderColor = purple,
    unfocusedBorderColor = borderColor,
    focusedContainerColor = white,
    unfocusedContainerColor = white
)

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
                fontSize = dimensionResource(
                    R.dimen.field_label_size
                ).value.sp
            )
        },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = dimensionResource(
                R.dimen.field_text_size
            ).value.sp,
            color = colorResource(R.color.form_text)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen.field_corner_radius)
        ),
        colors = fieldColors(
            purple = colorResource(R.color.purple_primary),
            borderColor = borderColor,
            textColor = colorResource(R.color.form_text),
            hintColor = colorResource(R.color.form_hint),
            white = colorResource(R.color.form_white)
        )
    )
}

@Composable
private fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    textColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.checkbox_spacing)
        )
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(
                dimensionResource(R.dimen.checkbox_size)
            )
        )

        Text(
            text = label,
            fontSize = dimensionResource(
                R.dimen.skill_text_size
            ).value.sp,
            color = textColor
        )
    }
}
