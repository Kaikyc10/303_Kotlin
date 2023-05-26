package com.example.cacaaotesouro

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlunoResponse(
    @SerializedName("ra") val ra: String,
    @SerializedName("qrCode") val qrCode: String,
    @SerializedName("photoBase64") val photoBase64: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("nome") val nome: String,
    @SerializedName("idade") val idade: Int,
    // Adicione outros campos conforme necessário
) : Parcelable