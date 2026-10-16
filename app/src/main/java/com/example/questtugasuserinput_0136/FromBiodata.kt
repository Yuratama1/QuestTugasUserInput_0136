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

}