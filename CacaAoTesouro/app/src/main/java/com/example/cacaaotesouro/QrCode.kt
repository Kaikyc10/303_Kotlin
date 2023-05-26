package com.example.cacaaotesouro

import com.google.gson.annotations.SerializedName

data class QrCode(
    @SerializedName("code") val code: String,
    @SerializedName("imageData") val imageData: String,
    // Outros campos do QR Code
)
