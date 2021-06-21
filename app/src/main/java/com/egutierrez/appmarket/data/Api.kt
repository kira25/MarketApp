import com.egutierrez.appmarket.model.*
import okhttp3.RequestBody


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//Object: convierte Singleton a la clase para devolver una unica instancia
object Api {

    //URL COMPLETA = https://marketapp2021.herokuapp.com/

    //1. Configurar la URL BASE
    private val builder: Retrofit.Builder =
        Retrofit.Builder().baseUrl("https://marketapp2021.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())

    //2. Configurar los METODOS
    interface ApiInterface {
        //Define el que de las cosas
        @POST("api/usuarios/login")
        suspend fun userLogin(@Body loginRequest: LoginRequest): Response<UsuarioDto>

        //Suspend: esperamos la respuesta
        @POST("/api/usuarios/crear-cuenta")
        suspend fun userRegister(@Body registerRequest: RegisterRequest): Response<UsuarioDto>

        //Devuelve la lista de generos
        @GET("/api/usuarios/obtener-generos")
        suspend fun getGenders(): Response<GeneroDto>

    }

    //3. Regresar una instancia de Retrofit
    fun build(): ApiInterface {
        return builder.build().create(ApiInterface::class.java)

    }

}