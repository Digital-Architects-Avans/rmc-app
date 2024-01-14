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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.LocalGasStation
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.PriceChange
import androidx.compose.material.icons.rounded.SportsScore
import androidx.compose.material.icons.rounded.Straighten
import androidx.compose.material.icons.rounded.Toys
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.domain.model.PlaceItem
import com.digitalarchitects.rmc_app.domain.model.Rental
import com.digitalarchitects.rmc_app.domain.model.RentalStatus
import com.digitalarchitects.rmc_app.domain.model.User
import com.digitalarchitects.rmc_app.domain.model.Vehicle
import com.digitalarchitects.rmc_app.domain.util.millisToLocalDateConverter
import com.digitalarchitects.rmc_app.domain.util.validateDate
import kotlinx.datetime.LocalDate

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
        },
        style = MaterialTheme.typography.displayLarge,
        color = colorResource(id = R.color.primary_red)
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
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_medium)),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

/**
 * Composable that displays User icon
 */
@Composable
fun RmcUserIcon(
    imageSrc: String?,
    modifier: Modifier = Modifier,
    size: Dp,
    onClick: () -> Unit
) {
    val imageUrl = if (imageSrc.isNullOrBlank()) {
        null // Set to null for AsyncImage to display default icon
    } else {
        imageSrc
    }
    Log.d("RmcUserIcon", "Loading image from URL: $imageUrl")

    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current).data(imageUrl)
            .crossfade(true).build(),
        error = painterResource(R.drawable.loading_img),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.profile_picture),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clickable { onClick() }
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
        style = MaterialTheme.typography.bodyLarge
    )
}

/**
 * Text field composable
 */
@Composable
fun RmcTextField(
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconButtonClick: () -> Unit = {},
    placeholder: String? = null,
    isPassword: Boolean = false,
    maxLines: Int = 1,
    value: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.None
    )
) {
    OutlinedTextField(
        shape = MaterialTheme.shapes.small,
        label = {
            Text(
                text = label,
                style = if (isError) MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.error
                ) else MaterialTheme.typography.bodyLarge,
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            errorContainerColor = MaterialTheme.colorScheme.error,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.error,
            unfocusedTextColor = MaterialTheme.colorScheme.scrim,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = keyboardOptions,
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        value = value ?: "", // Provide an empty string if value is null
        enabled = enabled,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            }
        },
        trailingIcon = trailingIcon?.let {
            {
                IconButton(onClick = {
                    onTrailingIconButtonClick()
                }) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null
                    )
                }
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
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
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
                    .size(18.dp),
                tint = Color.White
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
                tint = MaterialTheme.colorScheme.primary,
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
            .fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
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
    enabled: Boolean = true,
    modifier: Modifier
) {
    FilledIconButton(
        onClick = onClick,
        enabled = enabled,
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
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    FilledTonalIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.filledTonalIconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        enabled = enabled,
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
fun RmcBadge(value: String) {
    Badge {
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun RmcFloatingActionButton(
    icon: ImageVector,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    number: Int? = null,
    onClick: () -> Unit
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
            if (number == null) {
                Text(
                    text = stringResource(label),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text(
                    text = stringResource(label, number),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

        },
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
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            thickness = 1.dp,
            color = Color.Gray
        )
        Text(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = Color.Gray
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            thickness = 1.dp,
            color = Color.Gray
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
        style = MaterialTheme.typography.bodyLarge,
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
 * Stateless Text Composable with an underline for Forgot Password
 */
@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline
    )

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
    ownerView: Boolean = false,
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
        val vehicleImage = if (vehicle.imgLink == 1) getImageByLicensePlate(vehicle.licensePlate) else vehicle.imgLink
        Image(
            painter = painterResource(id = vehicleImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.padding_large))
                .size(dimensionResource(R.dimen.image_size_medium))
                .clip(RoundedCornerShape(8.dp))
        )
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = vehicle.licensePlate,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.primary_red),
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_extra_small))
                )
                if (ownerView) {
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
                } else {
                    RmcTextBadge(
                        label = "€ ${vehicle.price.toInt()},- ",
                        labelTextColor = MaterialTheme.colorScheme.primary,
                        labelBackgroundColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
            }
            Text(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small)),
                text = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
                style = MaterialTheme.typography.titleMedium,
            )
            val addressAsList = vehicle.address.split(",")
            val detailedAddress = addressAsList[0] + ", " + addressAsList[1]
            RmcIconLabel(
                label = detailedAddress,
                icon = Icons.Rounded.LocationOn
            )
            if (ownerView) {
                RmcIconLabel(
                    label = vehicle.price.toInt().toString(),
                    icon = Icons.Rounded.PriceChange
                )
            }
        }
    }
}

@Composable
fun RmcRentalListItem(
    rental: Rental,
    vehicle: Vehicle,
    user: User,
    ownerView: Boolean = false,
    onItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.Black
                ),
                onClick = { onItemClick() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    if (ownerView) {
                        Text(
                            text = rental.date.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_extra_small))
                        )
                        Text(
                            text = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_extra_small))
                        )
                        Text(
                            text = "${user.firstName} ${user.lastName}",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    } else {
                        Text(
                            text = rental.date.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_extra_small))
                        )
                        Text(
                            text = "${vehicle.year} - ${vehicle.brand} ${vehicle.model}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_extra_small))
                        )
                    }

                }
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
                RmcSpacer(4)
                RmcTextBadge(
                    label = rentalStatus,
                    labelTextColor = labelTextColor,
                    labelBackgroundColor = backgroundColor
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
                    vertical = dimensionResource(id = R.dimen.padding_extra_small),
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
    isAvailable: Boolean = vehicle.availability,
    ownerView: Boolean = false
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
            if (ownerView) {
                if (isAvailable) {
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
            } else {
                RmcTextBadge(
                    label = "€ ${vehicle.price.toInt()},- ",
                    labelTextColor = MaterialTheme.colorScheme.primary,
                    labelBackgroundColor = MaterialTheme.colorScheme.secondaryContainer
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
                .padding(bottom = dimensionResource(R.dimen.padding_large)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RmcIconLabel(
                label = vehicle.address,
                icon = Icons.Rounded.LocationOn
            )
            if (ownerView) {
                RmcIconLabel(
                    label = vehicle.price.toInt().toString(),
                    icon = Icons.Rounded.PriceChange
                )
            }
        }
    }
    val vehicleImage = if (vehicle.imgLink == 1) getImageByLicensePlate(vehicle.licensePlate) else vehicle.imgLink
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(184.dp)
            .padding(bottom = dimensionResource(R.dimen.padding_large)),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = vehicleImage),
        contentDescription = null
    )
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        Text(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)),
            text = vehicle.description,
            style = MaterialTheme.typography.bodyMedium,
        )
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
    location: String,
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
                label = location,
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

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("\"${vehicle.description}\"")
                }
            },
            modifier = Modifier
                .padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
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
fun RmcRentalDetails(
    rental: Rental,
    vehicle: Vehicle,
    user: User,
    ownerView: Boolean = false,
    showRejectButton: Boolean,
    showAcceptButton: Boolean = false,
    onRejectClick: () -> Unit = {},
    onAcceptClick: () -> Unit = {},
    onRouteClick: () -> Unit = {},
    onCancelRentalClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_large))
    ) {
        // Show rental details
        Row(
            modifier = Modifier.fillMaxWidth(),
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
        RmcSpacer(16)
        // Show renter details for owner
        if (ownerView) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RmcUserIcon(
                    userIcon = R.drawable.usericon,
                    size = dimensionResource(R.dimen.image_size_small),
                    onClick = {}
                )
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            RmcSpacer(16)
        }
        // Show rent stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        ) {
            RmcIconLabel(
                label = stringResource(id = R.string.stats_price_euro, rental.price.toInt()),
                icon = Icons.Rounded.PriceChange
            )
            RmcIconLabel(
                label = stringResource(id = R.string.stats_distance_km, rental.distanceTravelled.toInt()),
                icon = Icons.Rounded.SportsScore
            )
            RmcIconLabel(
                label = stringResource(id = R.string.stats_rental_points, rental.score),
                icon = Icons.Rounded.Toys
            )
        }
        RmcSpacer(16)
        // Show owner actions
        if (ownerView) {
            Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))) {
                Column(Modifier.weight(1f)) {
                    RmcFilledButton(
                        value = stringResource(id = R.string.reject_rental),
                        icon = Icons.Filled.Cancel,
                        color = colorResource(id = R.color.primary_red),
                        isEnabled = showRejectButton,
                        onClick = {
                            onRejectClick()
                        }
                    )
                }
                Column(Modifier.weight(1f)) {
                    RmcFilledButton(
                        value = stringResource(id = R.string.accept_rental),
                        icon = Icons.Rounded.Check,
                        color = colorResource(id = R.color.primary_green),
                        isEnabled = showAcceptButton,
                        onClick = {
                            onAcceptClick()
                        }
                    )
                }
            }
            // Show renter actions
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))) {
                Column(Modifier.weight(1f)) {
                    RmcFilledButton(
                        value = stringResource(id = R.string.cancel_rental),
                        icon = Icons.Filled.Cancel,
                        color = colorResource(id = R.color.primary_red),
                        isEnabled = showRejectButton,
                        onClick = {
                            onCancelRentalClick()
                        }
                    )
                }
                Column(Modifier.weight(1f)) {
                    RmcFilledButton(
                        value = stringResource(id = R.string.route_to_car),
                        icon = Icons.Rounded.Check,
                        color = colorResource(id = R.color.primary_green),
                        onClick = {
                            onRouteClick()
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun RmcRentalDetailsOwner(
    rental: Rental,
    vehicle: Vehicle,
    user: User,
    showRejectButton: Boolean,
    showAcceptButton: Boolean,
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
            RmcUserIcon(imageSrc = user.profileImageSrc,
                size = dimensionResource(R.dimen.image_size_medium),
                onClick = {})
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        Column(Modifier.weight(1f)) {
            if (showRejectButton) {
                RmcFilledTonalButton(
                    value = stringResource(id = R.string.reject_rental),
                    onClick = {
                        onRejectClick()
                    }
                )
            }
        }
        Column(Modifier.weight(1f)) {
            if (showAcceptButton) {
                RmcFilledButton(
                    value = stringResource(id = R.string.accept_rental),
                    onClick = {
                        onAcceptClick()
                    }
                )
            }
        }
    }
}


@Composable
fun MyRentalDetails(
    rental: Rental,
    vehicle: Vehicle,
    location: String,
    user: User,
    showButtons: Boolean,
    onCancelRentalClick: () -> Unit,
    onRouteClick: () -> Unit
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
            RmcUserIcon(imageSrc = user.profileImageSrc,
                size = dimensionResource(R.dimen.image_size_medium),
                onClick = {})
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }

    if (showButtons) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(Modifier.weight(1f)) {
                RmcFilledTonalButton(
                    value = stringResource(id = R.string.cancel_rental),
                    onClick = {
                        onCancelRentalClick()
                    }
                )
            }
            Column(Modifier.weight(1f)) {
                RmcFilledButton(
                    value = stringResource(id = R.string.route_to_car),
                    onClick = {
                        onRouteClick()
                    }
                )
            }
        }
    }
}


@Composable
fun <T> AutoCompleteUI(
    modifier: Modifier,
    query: String,
    queryLabel: String,
    useOutlined: Boolean = false,
    colors: TextFieldColors? = null,
    onQueryChanged: (String) -> Unit = {},
    predictions: List<T>,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onItemClick: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LazyColumn(
        state = lazyListState,
        modifier = modifier.heightIn(max = TextFieldDefaults.MinHeight * 6)
    ) {

        item {
            QuerySearch(
                query = query,
                label = queryLabel,
                leadingIcon = Icons.Filled.LocationOn,
                useOutlined = useOutlined,
                colors = colors,
                onQueryChanged = onQueryChanged,
                onDoneActionClick = {
                    onDoneActionClick()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
                onClearClick = {
                    onClearClick()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            )
        }

        if (predictions.isNotEmpty()) {
            items(predictions) { prediction ->
                Row(
                    Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .fillMaxWidth()
                        .clickable {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            onItemClick(prediction)
                        }
                ) {
                    itemContent(prediction)
                }
            }
        }
    }
}

@Composable
fun QuerySearch(
    modifier: Modifier = Modifier,
    useOutlined: Boolean,
    query: String,
    label: String,
    leadingIcon: ImageVector? = null,
    colors: TextFieldColors?,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onQueryChanged: (String) -> Unit
) {

    var showClearButton by remember { mutableStateOf(false) }

    if (useOutlined) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    showClearButton = (focusState.isFocused)
                },
            shape = MaterialTheme.shapes.small,
            value = query,
            onValueChange = onQueryChanged,
            label = { Text(text = label, style = MaterialTheme.typography.bodyLarge) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                if (showClearButton) {
                    IconButton(onClick = {
                        onClearClick()
                    }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear")
                    }
                }
            },
            keyboardActions = KeyboardActions(onDone = {
                onDoneActionClick()
            }),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                errorBorderColor = MaterialTheme.colorScheme.error,
                errorTextColor = MaterialTheme.colorScheme.error,
                unfocusedTextColor = MaterialTheme.colorScheme.scrim,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary
            )
        )
    } else {

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    showClearButton = (focusState.isFocused)
                },
            value = query,
            onValueChange = onQueryChanged,
            label = { Text(text = label) },
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            trailingIcon = {
                if (showClearButton) {
                    IconButton(onClick = { onClearClick() }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear")
                    }
                }

            },
            keyboardActions = KeyboardActions(onDone = {
                onDoneActionClick()
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            colors = colors ?: TextFieldDefaults.colors(
            )
        )
    }
}


@Composable
fun AddressEdit(
    query: String,
    modifier: Modifier,
    addressPlaceItemPredictions: List<PlaceItem>,
    onQueryChanged: (String) -> Unit,
    onClearClick: () -> Unit,
    onDoneClick: () -> Unit,
    onItemClick: (PlaceItem) -> Unit
) {
    AutoCompleteUI(
        modifier = Modifier.fillMaxWidth(),
        query = query,
        queryLabel = stringResource(id = R.string.vehicle_location),
        useOutlined = true,
        onQueryChanged = onQueryChanged,
        predictions = addressPlaceItemPredictions,
        onClearClick = onClearClick,
        onDoneActionClick = onDoneClick,
        onItemClick = onItemClick
    ) {
        Text(text = it.address, style = MaterialTheme.typography.bodyLarge)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RmcDatePickerDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis >= System.currentTimeMillis()
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        millisToLocalDateConverter(it)
    } ?: LocalDate(2024, 1, 1)

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                onClick = {
                    onDateSelected(selectedDate)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.apply))
            }
        },
        dismissButton = {
            OutlinedButton(
                modifier = Modifier,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                onClick = { onDismiss() }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

// Form to reserve a vehicle
@Composable
fun RmcDateTextField(
    date: LocalDate?,
    onValueChange: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(date?.toString() ?: "") }
    var isDateValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RmcTextField(
                label = stringResource(R.string.date),
                leadingIcon = Icons.Filled.CalendarMonth,
                trailingIcon = Icons.Filled.EditCalendar,
                onTrailingIconButtonClick = { showDatePicker = true },
                value = textFieldValue.ifEmpty { null },
                onValueChange = {
                    textFieldValue = it
                    isDateValid = validateDate(it)
                },
                isError = !isDateValid, // Set isError based on date validation
                enabled = true,
                placeholder = stringResource(id = R.string.date_placeholder), // Placeholder text
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        // Show an error message if the date is not valid
        if (!isDateValid) {
            Text(
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.padding_small))
                    .padding(top = dimensionResource(R.dimen.padding_extra_small)),
                text = stringResource(R.string.invalid_date),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
        // Show DatePickerDialog if the state is true
        if (showDatePicker) {
            RmcDatePickerDialog(
                onDateSelected = {
                    onValueChange(it)
                    textFieldValue = it.toString()
                    showDatePicker = false
                    isDateValid = true // Reset validation on new date selection
                },
                onDismiss = {
                    showDatePicker = false
                }
            )
        }
    }
}

fun getImageByLicensePlate(licensePlate: String): Int {
    return when (licensePlate) {
        "3-SKS-35" -> R.drawable.x5 // 2015 BMW X5
        "G-888-VS" -> R.drawable.rs6 // 2020 RS6 Avant
        "52-KJS-9" -> R.drawable.roadster // 2010 Tesla Roadster
        "YW-790-2" -> R.drawable.yaris // 2011 Toyota Yaris
        "GX-495-K" -> R.drawable.civic // 2020 Honda Cicic
        "JR-888-P" -> R.drawable.focus // 2019 Ford Focus
        "GK-19-NP" -> R.drawable.malibu // 1980 Chevrolet Malibu
        "DH-71-47" -> R.drawable.roadmaster // 1955 Buick Roadmaster
        "TR-912-J" -> R.drawable.mx5 // 2012 Mazda MX-5
        "V-512-XE" -> R.drawable.raptor // 2023 F-150 Raptor
        else -> R.drawable.car
    }
}