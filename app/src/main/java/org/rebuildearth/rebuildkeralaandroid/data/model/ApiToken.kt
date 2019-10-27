package org.rebuildearth.rebuildkeralaandroid.data.model

import com.google.gson.annotations.Expose


data class RefreshTokenModel(val access: String)
data class LoginModel(
        @Expose
        val username: String,
        @Expose
        val password: String)