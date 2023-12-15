package com.digitalarchitects.rmc_app.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.ui.theme.Shapes

/*
 * Composable components shared across different screens
 */

/**
 * Composable that displays RENT MY CAR in text
 */
@Composable
fun RmcLogoText() {
    androidx.compose.material.Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                append("RENT ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("MY")
                }
                append(" CAR")
            }
        }, style = MaterialTheme.typography.displayLarge, color = Color(0xFFC00000)
    )
}


/**
 * Composable that show the topBar with navigation and title
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RmcAppBar(
    @StringRes title: Int,
    navigationIcon: ImageVector,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            RmcOutlinedIconButton(
                icon = navigationIcon,
                label = title,
                onClick = navigateUp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        },
        title = {
            Text(
                text = stringResource(title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

/**
 * Default spacer
 */
@Composable
fun RmcSpacer(height: Int = 24) {
    Spacer(modifier = Modifier.height(height.dp))
}

/**
 * Composable that displays User icon
 */
@Composable
fun RmcUserIcon(
    @DrawableRes userIcon: Int,
    modifier: Modifier = Modifier,
    size: Dp,
    onClick: () -> Unit

) {
    Image(
        modifier = modifier
            .size(size)
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(CircleShape)
            .border(1.dp, colorResource(R.color.purple_200), CircleShape)
            .clickable { onClick },
        contentScale = ContentScale.Crop,
        painter = painterResource(userIcon),
        contentDescription = null
    )
}

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

@Composable
fun RmcTextField(
    label: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    placeholder: String? = null,
    isPassword: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    // TODO: Add error handling
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.None
    )
) {
    OutlinedTextField(
        shape = MaterialTheme.shapes.small,
        label = { Text(text = label) },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.error,
            unfocusedTextColor = MaterialTheme.colorScheme.scrim
        ),
        keyboardOptions = keyboardOptions,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        maxLines = 1,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = icon?.let {
            {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        },
        placeholder = placeholder?.let {
            {
                Text(
                    text = placeholder,
                    color = MaterialTheme.colorScheme.primary
                )
            }
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
 * Stateless Button Composables
 */
@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = true) {
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

@Composable
fun RmcFilledButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    color: Color = MaterialTheme.colorScheme.primary,
    value: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(color),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun RmcFilledTonalButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    value: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    FilledTonalButton(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun RmcOutlinedButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    value: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun RmcFilledIconButton(
    icon: ImageVector,
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(label),
        )
    }
}

@Composable
fun RmcFilledTonalIconButton(
    icon: ImageVector,
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.filledTonalIconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(label),
        )
    }
}

@Composable
fun RmcOutlinedIconButton(
    icon: ImageVector,
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(label)
        )
    }
}

@Composable
fun RmcImgFilledIconButton(
    @DrawableRes image: Int,
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(label),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
        )
    }
}

@Composable
fun RmcFloatingActionButton(
    icon: ImageVector,
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        text = {
            Text(
                text = stringResource(label),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        },
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
    )
}

/**
 * Composable consisting out of two dividers with the text "or" in the middle
 */
@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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

/**
 * Old top bottom from My vehicles - don't know what to do with it yet keeping for reference.
 */
@Composable
fun RmcTopButton() {
    Row(
        modifier = Modifier
            .height(40.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /* TODO */ }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
                    .border(1.dp, Color.LightGray, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.close),
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Gray
                )
            }
        }
        Text(
            text = stringResource(R.string.my_vehicles),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

