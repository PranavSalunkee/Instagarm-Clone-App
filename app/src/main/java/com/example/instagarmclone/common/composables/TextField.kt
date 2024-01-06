package com.example.instagarmclone.common.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun EmailField(value: String,
               onNewValue: (String) -> Unit,
               modifier: Modifier = Modifier
){
    OutlinedTextField(value = value,
        onValueChange ={onNewValue(it)},
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(text = "Email")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = null )
        }
    )

}

@Composable
fun UserNameField(value: String,
               onNewValue: (String) -> Unit,
               modifier: Modifier = Modifier
){
    OutlinedTextField(value = value,
        onValueChange ={onNewValue(it)},
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(text = "User Name")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null )
        }
    )

}

@Composable
fun PasswordField(value: String,
               onNewValue: (String) -> Unit,
               modifier: Modifier = Modifier
){
    var isVisible by remember { mutableStateOf(false) }

    val icon =
        if (isVisible) Icons.Default.Visibility
        else Icons.Default.VisibilityOff
    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(value = value,
        onValueChange ={onNewValue(it)},
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(text = "Password")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null )
        },
        trailingIcon = {
            IconButton(onClick = {isVisible = !isVisible}) {
                Icon(imageVector = icon, contentDescription = null )
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

    )

}

@Composable
fun ConfirmPasswordField(value: String,
                         onNewValue: (String) -> Unit,modifier: Modifier = Modifier){
    var isVisible by remember { mutableStateOf(false) }

    val icon =
        if (isVisible) Icons.Default.Visibility
        else Icons.Default.VisibilityOff
    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(value = value,
        onValueChange ={onNewValue(it)},
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(text = " Repeat Password")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null )
        },
        trailingIcon = {
            IconButton(onClick = {isVisible = !isVisible}) {
                Icon(imageVector = icon, contentDescription = null )
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

    )
}

@Composable
fun BasicTextButton(text: String, modifier: Modifier, action: () -> Unit) {
    TextButton(onClick = action, modifier = modifier) { androidx.compose.material.Text(text =(text)) }
}