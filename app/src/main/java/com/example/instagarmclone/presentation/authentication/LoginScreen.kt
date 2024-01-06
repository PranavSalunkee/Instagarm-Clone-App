package com.example.instagarmclone.presentation.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instagarmclone.R
import com.example.instagarmclone.common.composables.BasicTextButton
import com.example.instagarmclone.common.composables.EmailField
import com.example.instagarmclone.common.composables.PasswordField
import com.example.instagarmclone.ui.theme.InstagarmCloneTheme
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    openAndPopUp:(String,String) -> Unit,
    open:()-> Unit
) {
    val uiState by loginViewModel.uiState
    LoginScreenContent(
        loginUiState = uiState,
        onEmailChange = loginViewModel::onEmailChange,
        onPasswordChange = loginViewModel::onPasswordChange,
        onButtonClick = {
            loginViewModel.onSignInClick(openAndPopUp)
        },
        onTextButtonClick = {
            open()
        }
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier, loginUiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onButtonClick:()-> Unit,
    onTextButtonClick:()-> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.instagram_wordmark),
            contentDescription = null,
            modifier = Modifier.size(width = 280.dp, height = 120.dp)
        )
        EmailField(
            value = loginUiState
                .email, onNewValue = onEmailChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
        )
        PasswordField(
            value = loginUiState.password,
            onNewValue = onPasswordChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = { onButtonClick()},
            Modifier
                .fillMaxWidth()
                .padding(24.dp, 4.dp)
        ) {
            Text(text = "Sign In")

        }
        BasicTextButton(text = "New User? Sign Up", modifier =  Modifier
            .fillMaxWidth()
            .padding(24.dp, 4.dp)) {
           onTextButtonClick()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    InstagarmCloneTheme {
        LoginScreenContent(
            loginUiState = LoginUiState(email = " abc@xyz.com"),
            onEmailChange = {},
            onPasswordChange = {},
            onButtonClick = {},
            onTextButtonClick = {})

    }

}


