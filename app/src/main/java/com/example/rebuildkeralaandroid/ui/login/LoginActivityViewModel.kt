package com.example.rebuildkeralaandroid.ui.login

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rebuildkeralaandroid.R
import com.example.rebuildkeralaandroid.data.LoggedInUserView
import com.example.rebuildkeralaandroid.data.model.ApiTokenModel
import com.example.rebuildkeralaandroid.ui.MainActivity
import com.example.rebuildkeralaandroid.utility.Utility
import com.example.rebuildkeralaandroid.viewModel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<TextInputLayout>(R.id.edtUserName)
        val password = findViewById<TextInputLayout>(R.id.edtPassword)
        val login = findViewById<Button>(R.id.btnSignin)

        loginViewModel = ViewModelProviders.of(this)
                .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            progressDialog.dismiss()
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.editText?.afterTextChanged {
            loginViewModel.loginDataChanged(
                    username.editText?.text.toString(),
                    password.editText?.text.toString()
            )
        }

        password.editText?.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                        username.editText?.text.toString(),
                        password.editText?.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                                username.editText?.text.toString(),
                                password.editText?.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                progressDialog = Utility.showLoadingDialog(applicationContext)
                loginViewModel.login(username.editText?.text.toString(), password.editText?.text.toString())!!
                        .observe(this@LoginActivity, Observer {
                            if (it.response != null) {
                                val tokenModel = it.response as ApiTokenModel
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                            }
                        })
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
                applicationContext,
                "$welcome $displayName",
                Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
