package com.digitalarchitects.rmc_app.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.ui.theme.Shapes

/*
 * Composables shared across different screens
 */

/**
 * Composable that displays a Text component with a specific styling for NormalText
 */
@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .padding(bottom = 15.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = MaterialTheme.colorScheme.primary
    )
}

/**
 * Composable that displays a Text component with a specific styling for small HeadingText
 */
@Composable
fun SmallHeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )
}

/**
 * Composable that displays a Text component with a specific styling for a large HeadingText
 */
@Composable
fun LargeHeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )
}

/**
 * Stateless Composable that displays an OutlinedTextField with a label and leading icon
 */
@Composable
fun MyTextFieldComponent(
    labelValue: String,
    icon: ImageVector,
    onTextSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    )
) {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = Shapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        maxLines = 1,
        value = textValue,
        onValueChange = {
            textValue = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    )
}

/**
 * Stateless Composable that displays an OutlinedTextField with a label, leading icon, trialing
 * specific for passwords
 */
@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    icon: ImageVector,
    onTextSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Next
    ),
) {

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = Shapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        maxLines = 1,
        value = password,
        onValueChange = {
            password = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        trailingIcon = {

            val iconImage = if (passwordVisible) {
                Icons.Filled.Visibility
            } else Icons.Filled.VisibilityOff

            val description = if (passwordVisible) {
                stringResource(id = R.string.hide_password)
            } else stringResource(id = R.string.show_password)

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else PasswordVisualTransformation()
    )
}

/**
 * Stateless CheckboxComponent which consists out of a CheckBox and HyperLinkTextComponent
 */
@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var checkedState by remember { mutableStateOf(false) }
        Checkbox(checked = checkedState,
            onCheckedChange = { checkedState = !checkedState }
        )
        HyperlinkTextComponent(value = value, onTextSelected)
    }
}

/**
 * Composable that displays a ClickableText for the Privacy Police and Terms of Use
 */
@Composable
fun HyperlinkTextComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{$span}")

                if (span.item == termsAndConditionsText || span.item == privacyPolicyText) {
                    onTextSelected(span.item)
                }

            }
    })
}

/**
 * Stateless Button Composable
 */
@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary
                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }
    }
}

/**
 * Composable consisting out of two dividers with the text "or" in the middle
 */
@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

/**
 * Composable that displays a ClickableText depending on the screen you are in
 */
@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        },
    )
}

/**
 * Stateless Text Composable with an underline
 */
@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )

}