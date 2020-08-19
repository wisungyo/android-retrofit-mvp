package com.binar.chaptertujuhretrofit.update

import com.binar.chaptertujuhretrofit.network.ApiClient
import com.binar.chaptertujuhretrofit.pojo.GetPersonsResponse
import com.binar.chaptertujuhretrofit.pojo.PutPersonBody
import com.binar.chaptertujuhretrofit.pojo.PutPersonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePersonPresenter(val listener: Listener) {

    fun updatePerson(result: GetPersonsResponse.Result){
        val objectPut = PutPersonBody(result.firstName, result.lastName)
        ApiClient.instance.updatePerson(objectPut, result.iD.toString()).enqueue(object :
            Callback<PutPersonResponse> {

            override fun onFailure(call: Call<PutPersonResponse>, t: Throwable) {
                t.message?.let {
                    listener.onUpdatePersonFailed(it)
                }
            }

            override fun onResponse(
                call: Call<PutPersonResponse>,
                response: Response<PutPersonResponse>
            ) {
                listener.onUpdatePersonSuccess("Sukses mengubah Data Person")
            }
        })
    }


    interface Listener{
        fun onUpdatePersonSuccess(message: String)
        fun onUpdatePersonFailed(errorMessage: String)
    }
}