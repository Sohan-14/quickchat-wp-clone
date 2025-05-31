package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPhoneInputDropdown(
    selectedCountry: Pair<String, String>,
    onCountrySelected: (Pair<String, String>) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val countries = listOf(
        "Bangladesh" to "+880",
        "India" to "+91",
        "USA" to "+1",
        "UK" to "+44",
        "Canada" to "+1",
        "Australia" to "+61",
        "Germany" to "+49",
        "France" to "+33",
        "Italy" to "+39",
        "Spain" to "+34",
        "Pakistan" to "+92",
        "Nepal" to "+977",
        "Sri Lanka" to "+94",
        "China" to "+86",
        "Japan" to "+81",
        "South Korea" to "+82",
        "Brazil" to "+55",
        "Mexico" to "+52",
        "Russia" to "+7",
        "South Africa" to "+27",
        "Nigeria" to "+234",
        "Egypt" to "+20",
        "UAE" to "+971",
        "Saudi Arabia" to "+966",
        "Turkey" to "+90",
        "Indonesia" to "+62",
        "Thailand" to "+66",
        "Malaysia" to "+60",
        "Singapore" to "+65",
        "Vietnam" to "+84"
    )

    var expanded by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Dropdown
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width((screenWidth.value * .7).dp)
                .clickable { expanded = true }
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                },
        ) {
            Text(
                text = selectedCountry.first,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown",
                tint = Color.Black
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text(country.first) },
                    onClick = {
                        onCountrySelected(country)
                        expanded = false
                    }
                )
            }
        }

        // Country code + phone number
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width((screenWidth.value * .8).dp)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                },
        ) {
            Text(
                text = selectedCountry.second,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(end = 8.dp)
            )

            TextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                label = { Text("Phone Number") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .alignByBaseline(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

