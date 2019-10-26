package com.example.rebuildkeralaandroid.viewModel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rebuildkeralaandroid.R
import com.example.rebuildkeralaandroid.data.model.ApiResponse
import com.example.rebuildkeralaandroid.data.model.LoginModel
import com.example.rebuildkeralaandroid.repo.RegisterRepo
import com.example.rebuildkeralaandroid.ui.login.LoginFormState
import com.example.rebuildkeralaandroid.ui.login.LoginResult

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private var usersRepo = RegisterRepo.getInstance(application)

    fun login(username: String, password: String): MutableLiveData<ApiResponse>? {
        return usersRepo.userLogin(LoginModel(username, password))
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}
