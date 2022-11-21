package apps.saa.crypto

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import apps.saa.crypto.adapter.LatestListingsAdapter
import apps.saa.crypto.adapter.SetOnItemClickListener
import apps.saa.crypto.data.Data
import apps.saa.crypto.databinding.ActivityMainBinding
import apps.saa.crypto.db.CryptoDatabase
import apps.saa.crypto.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModel.MainActivityViewModelFactory(CryptoDatabase.getDatabase(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = LatestListingsAdapter(object: SetOnItemClickListener{
            override fun onItemClicked(data: Data) {
                val intent = Intent(this@MainActivity, GraphActivity::class.java)
                startActivity(intent)
            }
        })

        prepareRecyclerView(adapter)

        viewModel.cryptoCoinsData.observe(this, Observer { newData ->
            adapter.submitList(newData)
        })

    }

    fun prepareRecyclerView(adapter: LatestListingsAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = adapter
    }
}