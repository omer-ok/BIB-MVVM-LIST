package com.cristopher.mauratzjarl.data.network.responces


import com.cristopher.mauratzjarl.Utilz.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T:Any> apiRequest(call: suspend() -> Response<T>):T{

        val responce = call.invoke()

        if(responce.isSuccessful){
            return responce.body()!!
        }else{
            val error =responce.errorBody()?.string()

            val message = StringBuilder()
            error?.let {
                try {

                    message.append(JSONObject(it).getString("message"))

                }catch (e :JSONException){}
                message.append("\n")
            }
            message.append("Error Code ${ responce.code()}")

            throw ApiException(message.toString())
        }

    }
}