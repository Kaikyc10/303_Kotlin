import com.example.cacaaotesouro.Aluno
import com.example.cacaaotesouro.AlunoResponse
import com.example.cacaaotesouro.QrCode
import com.example.cacaaotesouro.QrCodeResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("qr-codes/{codeId}")
    fun getQrCode(@Path("codeId") codeId: String): Call<QrCodeResponse>

    @POST("alunos")
    fun saveAluno(@Body aluno: Aluno): Call<AlunoResponse>

    @GET("alunos/{ra}")
    fun getAluno(@Path("ra") ra: String): Call<AlunoResponse>

    @GET("alunos")
    fun getAllAlunos(): Call<List<AlunoResponse>>

    @PUT("alunos/{ra}")
    fun updateAluno(@Path("ra") ra: String, @Body aluno: Aluno): Call<AlunoResponse>

    @DELETE("alunos/{ra}")
    fun deleteAluno(@Path("ra") ra: String): Call<Unit>

    // Adicione outros endpoints abaixo conforme necessário

    @POST("qr-codes")
    fun createQrCode(@Body qrCode: QrCode): Call<QrCodeResponse>

    // Exemplo de endpoint para atualizar um QR Code
    @PUT("qr-codes/{codeId}")
    fun updateQrCode(@Path("codeId") codeId: String, @Body qrCode: QrCode): Call<QrCodeResponse>

    // Exemplo de endpoint para excluir um QR Code
    @DELETE("qr-codes/{codeId}")
    fun deleteQrCode(@Path("codeId") codeId: String): Call<Unit>

    // ...
}
