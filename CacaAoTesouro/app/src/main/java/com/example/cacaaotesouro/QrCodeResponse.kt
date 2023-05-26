package com.example.cacaaotesouro

import com.google.gson.annotations.SerializedName

data class QrCodeResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("imageData") val imageData: String?,
    // Outros campos da resposta do QR Code
)
