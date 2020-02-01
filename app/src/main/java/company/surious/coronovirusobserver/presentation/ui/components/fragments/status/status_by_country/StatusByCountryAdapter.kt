package company.surious.coronovirusobserver.presentation.ui.components.fragments.status.status_by_country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import company.surious.coronovirusobserver.databinding.ItemStatusByCountryBinding

class StatusByCountryAdapter :
    RecyclerView.Adapter<StatusByCountryAdapter.StatusByCountryViewHolder>() {
    private val data = ArrayList<StatusByCountryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusByCountryViewHolder =
        StatusByCountryViewHolder(
            ItemStatusByCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = data.size

    fun updateData(updatedData: List<StatusByCountryModel>) {
        data.clear()
        data.addAll(ArrayList(updatedData))
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StatusByCountryViewHolder, position: Int) {
        val model = data[holder.adapterPosition]
        holder.binding.statusModel = model
    }

    inner class StatusByCountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemStatusByCountryBinding = DataBindingUtil.bind(view)!!
    }
}