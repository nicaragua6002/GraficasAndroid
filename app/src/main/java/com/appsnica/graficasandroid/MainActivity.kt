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
import com.anychart.enums.Anchor
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.appsnica.graficasandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Vinculación de vista con ViewBinding
    private lateinit var binding: ActivityMainBinding // Declara la variable para ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater) // Infla el layout usando ViewBinding
        setContentView(binding.root) // Establece la raíz del layout como contenido de la actividad

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val anyChartView: AnyChartView = binding.anyChartView
        anyChartView.setProgressBar(findViewById(R.id.progress_bar))

        // Crear el gráfico
        val cartesian: Cartesian = AnyChart.column()

        // Configurar los datos simulados para "La Perfecta"
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Enero", 200))
        data.add(ValueDataEntry("Febrero", 180))
        data.add(ValueDataEntry("Marzo", 220))
        data.add(ValueDataEntry("Abril", 250))
        data.add(ValueDataEntry("Mayo", 270))
        data.add(ValueDataEntry("Junio", 230))
        data.add(ValueDataEntry("Julio", 240))
        data.add(ValueDataEntry("Agosto", 260))
        data.add(ValueDataEntry("Septiembre", 210))
        data.add(ValueDataEntry("Octubre", 190))
        data.add(ValueDataEntry("Noviembre", 220))
        data.add(ValueDataEntry("Diciembre", 280))

        // Asignar datos al gráfico
        val column = cartesian.column(data)

        // Configurar el tooltip
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("\${%Value} K")

        cartesian.animation(true)
        cartesian.title("Ventas de Productos Lácteos de La Perfecta")

        cartesian.yScale().minimum(0.0)

        // Configurar el formato de los ejes
        cartesian.yAxis(0).labels().format("\${%Value} K")
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(com.anychart.enums.HoverMode.BY_X)

        cartesian.xAxis(0).title("Meses")
        cartesian.yAxis(0).title("Ventas (en miles $)")

        anyChartView.setChart(cartesian)
    }
}