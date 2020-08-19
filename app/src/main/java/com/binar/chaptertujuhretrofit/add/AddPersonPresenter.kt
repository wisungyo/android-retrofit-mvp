package com.binar.chaptertujuhretrofit.add

import com.binar.chaptertujuhretrofit.pojo.PostPersonBody
import com.binar.chaptertujuhretrofit.pojo.PostPersonResponse
import com.binar.chaptertujuhretrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPersonPresenter(val listener: Listener) {

    fun addPerson(firstName : String, lastName: String){
//        val jsonBody = JSONObject()
//        jsonBody.put("first_name", firstName)
//        jsonBody.put("last_name", lastName)

        val person =
            PostPersonBody(
                firstName,
                lastName
            )

        ApiClient.instance.addPerson(person).enqueue(object : Callback<PostPersonResponse> {
            override fun onFailure(call: Call<PostPersonResponse>, t: Throwable) {
                listener.onAddPersonFailure(t.toString())
            }

            override fun onResponse(
                call: Call<PostPersonResponse>,
                response: Response<PostPersonResponse>
            ) {
                listener.onAddPersonSuccess("Add Success")
            }
        })
    }

    interface Listener{
        fun onAddPersonSuccess(successMessage: String)
        fun onAddPersonFailure(failureMessage: String)
    }
}