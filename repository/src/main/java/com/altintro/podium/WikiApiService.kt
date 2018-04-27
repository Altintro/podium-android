package com.altintro.podium

import com.altintro.podium.Model.*
import com.example.repository.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface WikiApiService {

    //-----------------------------------Authentication-----------------------------------

    @POST("users/emailConnect")
    fun emailConnect(@Query("email") email: String): Observable<ResponseEmailConnect>

    @POST("users/emailRegister")
    fun emailRegister(@Body userRegister: UserRegister): Observable<ResponseEmailConnect>

    @GET("users/tokens")
    fun tokens(@Header("x-access-token") token: String): Observable<ResponseTokens>

    @POST("users/facebookConnect")
    fun facebookConnect(@Query("fbToken") fbToken: String): Observable<ResponseTokens>

    @POST("users/refreshToken")
    fun refreshToken(@Header("x-refresh-token") refreshToken: String): Observable<ResponseRefreshToken>

    @POST("users/login")
    fun login(@Body userRegister: UserRegister): Observable<ResponseAuth>



    //-----------------------------------User-----------------------------------
    @GET("users")
    fun getUsers(): Observable<ResponseSearchUsers>

    @GET("users/{id}")
    fun getUserDetail(@Header("x-access-token") token: String,
                      @Path("id") userId: String,
                      @Query("games") games: String): Observable<ResponseSearchUsers>

    @DELETE("users/{id}")
    fun deleteUser(@Header("x-access-token") token: String,
                   @Path("id") userId: String)

    @GET("users/me")
    fun getMyProfile(@Header("x-access-token") token: String): Observable<ResponseSearchMyProfile>


    //-----------------------------------Games-----------------------------------
    @GET("games")
    fun getGames(): Observable<ResponseSearchGame>

    @GET("games/{id}/detail")
    fun getGameDetail(@Path("id") id: String): Observable<ResponseGameDetail>


    @POST("games")
    fun setGame(@Header("x-access-token") token: String,
                @Body game: GameCreation): Observable<ResponseGameSubscription>

    @POST("games/{id}/join")
    fun subscribeToGame(@Header("x-access-token") token: String,
                        @Path("id") id: String): Observable<ResponseGameSubscription>

    @DELETE("games/{id}")
    fun deleteGame(@Header("x-access-token") token: String,
                   @Path("id") userId: String)

    //-----------------------------------Sports-----------------------------------
    @GET("sports")
    fun getSports(): Observable<ResponseSearchSport>


    //-----------------------------------Tournaments-----------------------------------
    //Coming next
    /*@GET("tournaments")
    fun getTournaments(@Header("x-access-token") token: String,
                       @Query("name") name: String,
                       @Query("type") type: String,
                       @Query("limit") limit: String,
                       @Query("fields") fields1: String,
                       @Query("fields") fields2: String,
                       @Query("sort") sort: String): Observable<ResponseSearchTournament>

    @GET("tournaments/{id}")
    fun getTournamentsDetail(@Header("x-access-token") token: String,
                             @Path("id") id: String,
                             @Query("participants") participants: String): Observable<ResponseSearchTournament>


    @POST("tournaments")
    fun setTournaments(@Header("x-access-token") token: String,
                       @Body game: TournamentCreation)

    @POST("tournaments/signup/{id}")
    fun subscribeToTournaments(@Header("x-access-token") token: String,
                               @Path("id") id: String)

    @DELETE("tournaments/{id}")
    fun deleteTournaments(@Header("x-access-token") token: String,
                          @Path("id") userId: String)*/


    //----------------------------------------------------------------------

    companion object {
        fun create(): WikiApiService {
            val logging : HttpLoggingInterceptor = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)


            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.PODIUM_API)
                    .client(httpClient.build())
                    .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }
}