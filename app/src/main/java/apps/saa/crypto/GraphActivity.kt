package apps.saa.crypto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apps.saa.crypto.databinding.ActivityGraphBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

class GraphActivity : AppCompatActivity() {

    lateinit var binding: ActivityGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .title("Graph of Price")
            .subtitle("Bitcoin")
            .backgroundColor("#4b2b7f")
            .dataLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name("Bitcoin")
                    .data(arrayOf(16133.61, 16140.61, 14560.61, 16345.50, 17000.61, 18900.61, 16432.61, 16321.61, 16345.61, 16133.61, 16133.50))
            )
            )

        binding.aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }
}