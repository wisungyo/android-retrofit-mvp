package com.binar.chaptertujuhretrofit.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.chaptertujuhretrofit.add.AddPersonActivity
import com.binar.chaptertujuhretrofit.pojo.GetPersonsResponse
import com.binar.chaptertujuhretrofit.R
import com.binar.chaptertujuhretrofit.update.EditActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainPresenter.Listener {
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()
        presenter = MainPresenter(this)
        presenter.getPersonList()

        fabAddActivity.setOnClickListener {
            presenter.goToAddActivity()
        }
    }

    fun setUpRecyclerView(listPerson : List<GetPersonsResponse.Result>){
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter =
            PersonAdapter(listPerson, presenter)
    }

    override fun onPersonListSuccess(personList: MutableList<GetPersonsResponse.Result>) {
        setUpRecyclerView(personList)
    }

    override fun onGetPersonListFailure(errMessage: String) {
        Toast.makeText(this,"Error : $errMessage",Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun goToAddActivity() {
        val goToAddActivity = Intent(this, AddPersonActivity::class.java)
        startActivity(goToAddActivity)
    }

    override fun goToUpdateActivity(result: GetPersonsResponse.Result) {
        val goToUpdateActivity = Intent(this, EditActivity::class.java)
        goToUpdateActivity.putExtra("PERSON",result)
        startActivity(goToUpdateActivity)
    }

    override fun onPersonDeleteSuccess(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        presenter.getPersonList()
    }

    override fun onPersonDeleteFailed(errMessage: String) {
        Toast.makeText(this,"Error : $errMessage",Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        presenter.getPersonList()
    }
}