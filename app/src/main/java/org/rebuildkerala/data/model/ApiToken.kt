package org.rebuildkerala.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize


data class RefreshTokenModel(val access: String)
data class LoginModel(
        @Expose
        val username: String,
        @Expose
        val password: String)