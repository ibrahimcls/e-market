package com.ibrah.emarket.common

import androidx.room.TypeConverter
import com.ibrah.emarket.model.Product
import org.json.JSONObject

class Converter {
    @TypeConverter
    fun fromProduct(product: Product):String{
        val jsonObject = JSONObject()
        jsonObject.put("id",product.id)
        jsonObject.put("model",product.model)
        jsonObject.put("name",product.name)
        jsonObject.put("brand",product.brand)
        jsonObject.put("image",product.image)
        jsonObject.put("createdAt",product.createdAt)
        jsonObject.put("description",product.description)
        jsonObject.put("price",product.price)
        return jsonObject.toString()
    }

    @TypeConverter
    fun toProduct(value: String): Product{
        val jsonObject = JSONObject(value)
        val id =jsonObject.getString("id")
        val model =jsonObject.getString("model")
        val name =jsonObject.getString("name")
        val brand =jsonObject.getString("brand")
        val image =jsonObject.getString("image")
        val createdAt =jsonObject.getString("createdAt")
        val description =jsonObject.getString("description")
        val price =jsonObject.getString("price")
        return Product(id, createdAt,name,image,price,model,description,brand)
    }
}