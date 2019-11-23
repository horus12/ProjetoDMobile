package com.ProjetoIntegrado.projetointegradod.ui.login

import android.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.ProjetoIntegrado.projetointegradod.data.LoginRepository
import com.ProjetoIntegrado.projetointegradod.data.Result

import com.ProjetoIntegrado.projetointegradod.R
import com.projetointegrado.projetointegradoFinal.Api
import com.projetointegrado.projetointegradoFinal.Login
import com.projetointegrado.projetointegradoFinal.LoginParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        aleatorio()

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
        return password.length > 5
    }

    private fun aleatorio(){

        val service = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        service.login(LoginParameters("INVALID","INVALID"))
            .enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {

                   if(response.isSuccessful)
                       home()
                    else


                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                }
            })

    }


    private fun home(){

    }


}
