package com.example.questtugasuserinput_0136

import androidx.compose.runtime.Composable

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
                                        .clickable { datePicker.show() }
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
                            onClick = { showResult = true },
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

                        if (showResult && isFormValid) {
                            Text(
                                text = """
                                    Nama: $firstName $lastName
                                    Email: $email
                                    Tanggal lahir: $birthDate
                                    Experience: $experience
                                    Skill: ${
                                    listOfNotNull(
                                        if (androidSkill) "Android" else null,
                                        if (uiuxSkill) "UI/UX" else null,
                                        if (webSkill) "Web" else null
                                    ).joinToString()
                                }
                                """.trimIndent(),
                                color = colorResource(R.color.form_text),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
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