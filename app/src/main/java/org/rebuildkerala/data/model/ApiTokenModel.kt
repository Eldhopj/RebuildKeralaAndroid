package org.rebuildkerala.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ApiTokenModel(val refresh: String,
                         val access: String)