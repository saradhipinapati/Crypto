package apps.saa.crypto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import apps.saa.crypto.data.Data
import apps.saa.crypto.databinding.CellCcLatestBinding
import com.bumptech.glide.Glide
import java.math.RoundingMode
import java.text.DecimalFormat

interface SetOnItemClickListener {
    fun onItemClicked(data: Data)
}

class LatestListingsAdapter(val clickListener: SetOnItemClickListener): ListAdapter<Data, LatestListingsAdapter.CCViewHolder>(DiffCallback) {

    val IMAGE_BASE_URL = "https://s2.coinmarketcap.com/static/img/coins/64x64/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CCViewHolder {
        return CCViewHolder(
            CellCcLatestBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CCViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    inner class CCViewHolder(private var binding: CellCcLatestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Data) {
            binding.ccName.text = item.name

            // Latest price
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            var roundoff = df.format(item.quote.uSD.price)
            binding.latestPrice.text = "$ " + roundoff.toString()

            // Market cap
            roundoff = df.format(item.quote.uSD.marketCap)
            binding.marketCap.text = "$ " + roundoff.toString()

            // Volume 24H
            roundoff = df.format(item.quote.uSD.volume24h)
            binding.vol24H.text = "$ " + roundoff.toString()

            // Image
            val imgUrl = IMAGE_BASE_URL + item.id + ".png"
            Glide.with(binding.root.context).load(imgUrl).into(binding.ccLogo)

            // Click event
            binding.parent.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }

}