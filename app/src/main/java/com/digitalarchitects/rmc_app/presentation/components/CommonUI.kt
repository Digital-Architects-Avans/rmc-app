package com.digitalarchitects.rmc_app.presentation.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.LocalGasStation
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.PriceChange
import androidx.compose.material.icons.rounded.Straighten
import androidx.compose.material.ripple.rememberRipple
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
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
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
        }, style = MaterialTheme.typography.displayLarge, color = colorResource(id = R.color.primary_red)
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
 * Default divider
 */
@Composable
fun RmcDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_medium)),
        color = MaterialTheme.colorScheme.outlineVariant,
        thickness = 1.dp
    )
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
            .clickable { onClick() },
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
fun ButtonComponent(
    value: String, onButtonClicked: () -> Unit,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .padding(4.dp),
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
                fontWeight = FontWeight.Normal
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
        modifier = modifier
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

@Composable
fun RmcSwitch(
    value: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = 80.dp,
    iconSize: Dp = 80.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clickable { onToggle(!value) }
    ) {
        val icon = if (value) Icons.Default.ToggleOn else Icons.Default.ToggleOff
        val tint =
            if (value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(iconSize) // Set the size of the icon here
        )
    }
}

@Composable
fun RmcMapVehicleCluster(
    number: Int,
) {
    Surface(
        modifier = Modifier
            .size(dimensionResource(R.dimen.icon_size_large)),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        border = BorderStroke(1.dp, Color.White)
    ) {
        Box(contentAlignment = Alignment.Center) {
            androidx.compose.material.Text(
                text = number.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
fun RmcMapVehicleItem() {
    Surface(
        modifier = Modifier
            .size(dimensionResource(R.dimen.icon_size_normal)),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        border = BorderStroke(1.dp, Color.White)
    ) {
        Box {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 2.dp, bottom = 4.dp),
                imageVector = Icons.Rounded.DirectionsCar,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun RmcVehicleListItem(
    vehicle: Vehicle,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.Black
                ),
                onClick = {
                    onClick(vehicle.vehicleId)
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (vehicle.imgLink != 1) {
            Image(
                painter = painterResource(id = R.drawable.civic),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size_medium))
                    .clip(RoundedCornerShape(8.dp))
                    .padding(end = dimensionResource(R.dimen.padding_large))
            )
        }
        Column {
            Text(
                text = vehicle.licensePlate,
                style = MaterialTheme.typography.displaySmall,
                color = colorResource(id = R.color.primary_red)
            )
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_small)),
                text = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RmcIconLabel(
                    label = "Eindhoven",
                    icon = Icons.Rounded.LocationOn
                )
                RmcIconLabel(
                    label = vehicle.price.toString(),
                    icon = Icons.Rounded.PriceChange
                )
            }
        }
    }
}

@Composable
fun RmcTextBadge(
    label: String,
    labelTextColor: Color,
    labelBackgroundColor: Color
) {
    Surface(
        color = labelBackgroundColor,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = labelTextColor,
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.padding_extra_small),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
        )
    }
}

@Composable
fun RmcIconLabel(
    label: String,
    icon: ImageVector,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.icon_size_normal))
                .padding(end = dimensionResource(R.dimen.padding_small)),
            tint = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Composable
fun RmcVehicleDetails(
    vehicle: Vehicle,
    showAvailability: Boolean
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = vehicle.licensePlate,
                style = MaterialTheme.typography.displayMedium,
                color = colorResource(id = R.color.primary_red)
            )
            if (showAvailability) {
                if (vehicle.availability) {
                    RmcTextBadge(
                        label = stringResource(R.string.available),
                        labelTextColor = MaterialTheme.colorScheme.primary,
                        labelBackgroundColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                } else {
                    RmcTextBadge(
                        label = stringResource(R.string.unavailable),
                        labelTextColor = MaterialTheme.colorScheme.error,
                        labelBackgroundColor = MaterialTheme.colorScheme.errorContainer
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_small)),
            text = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RmcIconLabel(
                label = "Eindhoven",
                icon = Icons.Rounded.LocationOn
            )
            RmcIconLabel(
                label = vehicle.price.toInt().toString(),
                icon = Icons.Rounded.PriceChange
            )
        }
    }

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 160.dp, width = 20.dp)
            .padding(
                top = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_large)
            ),
        contentScale = ContentScale.Crop,
        painter = painterResource(R.drawable.civic),
        contentDescription = null
    )

    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        //Text(
        //    modifier = Modifier
        //        .padding(
        //            bottom = dimensionResource(R.dimen.padding_small)
        //        ),
        //    text = "A cheap car to go away for a day.",
        //    style = MaterialTheme.typography.bodyMedium,
        //)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RmcIconLabel(
                label = vehicle.vehicleClass,
                icon = Icons.Rounded.Straighten
            )
            RmcIconLabel(
                label = vehicle.engineType.toString(),
                icon = Icons.Rounded.LocalGasStation
            )
        }
    }
}

@Composable
fun RmcVehicleDetailsOwner(
    vehicle: Vehicle,
    showAvailability: Boolean,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = vehicle.licensePlate,
                style = MaterialTheme.typography.displayMedium,
                color = Color(0xFFC00000)
            )
            val (labelText, labelTextColor, labelBackgroundColor) = when (vehicle.availability) {
                true -> Triple(
                    stringResource(R.string.available),
                    colorResource(id = R.color.primary_green_text),
                    colorResource(id = R.color.primary_green_bg)
                )

                false -> Triple(
                    stringResource(R.string.unavailable),
                    MaterialTheme.colorScheme.error,
                    MaterialTheme.colorScheme.errorContainer
                )
            }
            if (showAvailability) {
                RmcTextBadge(
                    label = labelText,
                    labelTextColor = labelTextColor,
                    labelBackgroundColor = labelBackgroundColor
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_small)),
            text = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RmcIconLabel(
                label = "Eindhoven",
                icon = Icons.Rounded.LocationOn
            )
            RmcIconLabel(
                label = vehicle.price.toInt().toString(),
                icon = Icons.Rounded.PriceChange
            )
        }
    }

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 240.dp, width = 20.dp)
            .padding(
                top = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_large)
            ),
        contentScale = ContentScale.Crop,
        painter = painterResource(R.drawable.civic),
        contentDescription = null
    )

    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RmcIconLabel(
                label = vehicle.vehicleClass,
                icon = Icons.Rounded.Straighten
            )
            RmcIconLabel(
                label = vehicle.engineType.toString(),
                icon = Icons.Rounded.LocalGasStation
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
            thickness = 1.dp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(Modifier.weight(1f)) {
                RmcFilledTonalButton(
                    value = stringResource(id = R.string.delete_vehicle),
                    onClick = {
                        onDeleteClick()
                    }
                )
            }
            Column(Modifier.weight(1f)) {
                RmcFilledButton(
                    value = stringResource(id = R.string.edit_vehicle),
                    onClick = {
                        onEditClick()
                    }
                )
            }
        }
    }
}


@Composable
fun RmcRentalDetailsOwner(
    rental: Rental,
    vehicle: Vehicle,
    user: User,
    onRejectClick: () -> Unit,
    onAcceptClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rental.date.toString(),
                style = MaterialTheme.typography.displayMedium,
                color = colorResource(id = R.color.primary_red)
            )
            val (rentalStatus, labelTextColor, backgroundColor) = when (rental.status) {
                RentalStatus.PENDING ->
                    Triple(
                        stringResource(R.string.pending),
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondaryContainer
                    )

                RentalStatus.APPROVED ->
                    Triple(
                        stringResource(R.string.approved),
                        colorResource(id = R.color.primary_green_text),
                        colorResource(id = R.color.primary_green_bg)
                    )

                RentalStatus.DENIED ->
                    Triple(
                        stringResource(R.string.denied),
                        MaterialTheme.colorScheme.error,
                        MaterialTheme.colorScheme.errorContainer
                    )
                RentalStatus.CANCELLED ->
                    Triple(
                        stringResource(R.string.cancelled),
                        MaterialTheme.colorScheme.error,
                        MaterialTheme.colorScheme.errorContainer
                    )
            }
            RmcTextBadge(
                label = rentalStatus,
                labelTextColor = labelTextColor,
                labelBackgroundColor = backgroundColor
            )
        }
        Row {
            RmcUserIcon(userIcon = R.drawable.usericon,
                size = dimensionResource(R.dimen.image_size_medium),
                onClick = {})
            SmallHeadingTextComponent(value = "${user.firstName} ${user.lastName}")
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        Column(Modifier.weight(1f)) {
            RmcFilledTonalButton(
                value = stringResource(id = R.string.reject_rental),
                onClick = {
                    onRejectClick()
                }
            )
        }
        Column(Modifier.weight(1f)) {
            RmcFilledButton(
                value = stringResource(id = R.string.accept_rental),
                onClick = {
                    onAcceptClick()
                }
            )
        }
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
        thickness = 1.dp
    )

    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small)),
        ) {
            Text(
                text = vehicle.licensePlate,
                style = MaterialTheme.typography.displayMedium,
                color = colorResource(id = R.color.primary_red)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_small)),
                text = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 240.dp, width = 20.dp)
            .padding(
                top = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_large)
            ),
        contentScale = ContentScale.Crop,
        painter = painterResource(R.drawable.civic),
        contentDescription = null
    )

    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_extra_large)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RmcIconLabel(
                label = "Eindhoven",
                icon = Icons.Rounded.LocationOn
            )
            RmcIconLabel(
                label = vehicle.price.toInt().toString(),
                icon = Icons.Rounded.PriceChange
            )
        }
    }

}