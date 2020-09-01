package com.binar.chaptertujuhretrofit.main
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.chaptertujuhretrofit.pojo.GetPersonsResponse
import com.binar.chaptertujuhretrofit.R
import com.binar.chaptertujuhretrofit.main.PersonAdapter.ViewHolder
import kotlinx.android.synthetic.main.person_item.view.*

class PersonAdapter(val listPerson : List<GetPersonsResponse.Result>, val presenter: MainPresenter) : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPerson.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvID.text = "ID : ${listPerson[position].iD.toString()}"
        holder.itemView.tvCreatedAt.text = "Created at : ${listPerson[position].createdAt}"
        holder.itemView.tvUpdatedAt.text = "Updated at : ${listPerson[position].updatedAt}"
        holder.itemView.tvDeletedAt.text = "Deleted at : ${listPerson[position].deletedAt}"
        holder.itemView.tvFirstName.text = "Firstname : ${listPerson[position].firstName}"
        holder.itemView.tvLastName.text  = "Lastname : ${listPerson[position].lastName}"
        holder.itemView.setOnClickListener {
            presenter.goToUpdateActivity(listPerson[position])
        }

        holder.itemView.ivDelete.setOnClickListener {
            presenter.deletePerson(listPerson[position])
        }
    }
}