package com.appsnica.graficasandroid


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.enums.Align
import com.anychart.enums.Anchor
import com.anychart.enums.LegendLayout
import com.anychart.enums.Position
import com.appsnica.graficasandroid.databinding.ActivityPiechartBinding


class PiechartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPiechartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPiechartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val anyChartView = binding.anyChartView // Accede a la vista del gráfico a través de ViewBinding
        anyChartView.setProgressBar(binding.progressBar) // Establece un ProgressBar mientras el gráfico se carga

        // Crear el gráfico circular (pie chart)
        val pie = AnyChart.pie()

        // Configurar los datos simulados para "La Perfecta"
        val data: MutableList<DataEntry> = ArrayList() // Crea una lista mutable de entradas de datos
        data.add(ValueDataEntry("Managua", 6371664)) // Agrega los datos de producción por planta
        data.add(ValueDataEntry("Masaya", 789622))
        data.add(ValueDataEntry("León", 7216301))
        data.add(ValueDataEntry("Granada", 1486621))
        data.add(ValueDataEntry("Jinotega", 1200000))

        // Asignar los datos al gráfico
        pie.data(data)

        // Establecer el título del gráfico
        pie.title("Producción por planta en 2023 (en kg)")

        // Configurar las etiquetas del gráfico
        pie.labels().position("outside") // Las etiquetas se colocan fuera del gráfico

        // Configurar el título de la leyenda
        pie.legend().title().enabled(true) // Habilita el título de la leyenda
        pie.legend().title()
            .text("Principales plantas") // Define el texto del título
            .padding(0.0, 0.0, 10.0, 0.0) // Configura el espaciado del título

        // Configurar la leyenda del gráfico
        pie.legend()
            .position("center-bottom") // Ubica la leyenda en la parte inferior central
            .itemsLayout(LegendLayout.HORIZONTAL) // Distribuye los elementos horizontalmente
            .align(Align.CENTER) // Centra la leyenda

        anyChartView.setChart(pie) // Asigna el gráfico a la vista para mostrarlo

    }
}