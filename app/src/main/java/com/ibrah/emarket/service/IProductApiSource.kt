import com.ibrah.emarket.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface IProductApiSource {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}
