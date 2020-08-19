package com.binar.chaptertujuhretrofit.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.chaptertujuhretrofit.R
import com.binar.chaptertujuhretrofit.pojo.GetPersonsResponse
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(), UpdatePersonPresenter.Listener {
    private lateinit var presenter: UpdatePersonPresenter
    private lateinit var result: GetPersonsResponse.Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        intent.getParcelableExtra<GetPersonsResponse.Result>("PERSON")?.let {
            result = it
        }

        presenter = UpdatePersonPresenter(this)

        etFirstName.setText(result.firstName)
        etLastName.setText(result.lastName)



        btnUpdatePerson.setOnClickListener {
            result.apply {
                firstName = etFirstName.text.toString()
                lastName = etLastName.text.toString()
            }

            presenter.updatePerson(result)
        }
    }

    override fun onUpdatePersonSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onUpdatePersonFailed(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}