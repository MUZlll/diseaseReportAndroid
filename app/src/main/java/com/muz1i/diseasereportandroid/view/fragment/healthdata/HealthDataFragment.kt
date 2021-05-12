package com.muz1i.diseasereportandroid.view.fragment.healthdata

import android.graphics.Color
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.datepicker.MaterialDatePicker
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.MyArrayAdapter
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.bean.HealthData
import com.muz1i.diseasereportandroid.databinding.FragmentHealthDataBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.viewmodel.healthdata.HealthDataViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class HealthDataFragment : BaseFragment<HealthDataViewModel, FragmentHealthDataBinding>() {
    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("选择日期")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    }
    private val barWidth = 0.3f
    private lateinit var barDataSetHealth: BarDataSet
    private lateinit var barDataSetLight: BarDataSet
    private lateinit var barDataSetSerious: BarDataSet
    private lateinit var healthDataList: List<HealthData>

    override fun getVMClass(): Class<HealthDataViewModel> {
        return HealthDataViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_health_data
    }

    override fun initView() {
        initBarChart()
        (binding.healthDataInstitute.editText as AutoCompleteTextView).setAdapter(
            MyArrayAdapter(requireContext(), R.layout.list_item, Constants.INSTITUTE_ITEMS)
        )
    }

    private fun initBarChart() {
        binding.barChart.run {
            initX()
            initY()
            description.text = "SUM DATA"
            description.textColor = context.getColor(R.color.orange)
            legend.textSize = 10f
        }
    }

    private fun initX() {
        binding.barChart.run {
            xAxis.position = XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.valueFormatter = getDateValueFormatter()
            xAxis.textSize = 10f
            xAxis.granularity = 1f
            xAxis.axisMinimum = -0.5f
        }
    }

    private fun initY() {
        binding.barChart.run {
            axisLeft.valueFormatter = getIntValueFormatter()
            axisLeft.granularity = 1f
            axisLeft.axisMinimum = 0f
            axisLeft.textSize = 10f
            axisRight.isEnabled = false
        }
    }

    override fun loadData() {
        loadTodaySchoolData()
    }

    override fun reload() {
        loadTodaySchoolData()
    }
    private fun loadTodaySchoolData() {
        viewModel.getSchoolData(getDayFormat(System.currentTimeMillis()))
//        viewModel.getSchoolData("2021-05-09")
    }

    override fun initEvent() {
        binding.healthDataCreateTime.run {
            editText?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    datePicker.show(parentFragmentManager, "tag")
                }
            }
            editText?.setOnClickListener {
                datePicker.show(parentFragmentManager, "tag")
            }
            datePicker.addOnPositiveButtonClickListener {
                val dayFormat = getDayFormat(it)
                this.editText?.setText(dayFormat)
                viewModel.getDataByDay(dayFormat)
                binding.healthDataTitle.text = "日期查询"
            }
        }
        binding.healthDataInstitute.editText?.addTextChangedListener {
            viewModel.getDataByInstitute(it.toString())
            binding.healthDataTitle.text = "学院查询"
            binding.healthDataCreateTime.editText?.text?.clear()
        }
    }

    override fun observeData() {
        val that = this
        viewModel.run {
            schoolData.observe(that) {
                healthDataList = it
                setBarChartData(it)
            }
            dataByDay.observe(that) {
                binding.barChart.xAxis.valueFormatter = getInstituteValueFormatter()
                healthDataList = it
                setBarChartData(it)
            }
            dataByInstitute.observe(that) {
                binding.barChart.xAxis.valueFormatter = getDateValueFormatter()
                healthDataList = it
                setBarChartData(it)
                binding.barChart.moveViewToX(healthDataList.size - 1f)
            }
        }
    }

    private fun setBarChartData(it: List<HealthData>) {
        val valuesHealth = mutableListOf<BarEntry>()
        val valuesLight = mutableListOf<BarEntry>()
        val valuesSerious = mutableListOf<BarEntry>()
        for (i in it.indices) {
            if (!isNullData(it[i])) {
                valuesHealth.add(BarEntry(i.toFloat() - barWidth, it[i].healthNum.toFloat()))
                valuesLight.add(BarEntry(i.toFloat(), it[i].lightNum.toFloat()))
                valuesSerious.add(BarEntry(i.toFloat() + barWidth, it[i].seriousNum.toFloat()))
            }
        }
        binding.barChart.run {
            if (data != null && data.dataSetCount > 0) {
                barDataSetHealth = data.getDataSetByIndex(0) as BarDataSet
                barDataSetLight = data.getDataSetByIndex(1) as BarDataSet
                barDataSetSerious = data.getDataSetByIndex(2) as BarDataSet
                barDataSetHealth.values = valuesHealth
                barDataSetLight.values = valuesLight
                barDataSetSerious.values = valuesSerious
                data.notifyDataChanged()
                notifyDataSetChanged()
            } else {
                barDataSetHealth = BarDataSet(valuesHealth, "丙")
                barDataSetHealth.color = Color.rgb(33, 150, 243)
                barDataSetHealth.valueTextSize = 12f
                barDataSetHealth.valueTextColor = Color.rgb(33, 150, 243)
                barDataSetLight = BarDataSet(valuesLight, "乙")
                barDataSetLight.color = Color.rgb(255, 152, 0)
                barDataSetLight.valueTextSize = 12f
                barDataSetLight.valueTextColor = Color.rgb(255, 152, 0)
                barDataSetSerious = BarDataSet(valuesSerious, "甲")
                barDataSetSerious.color = Color.rgb(255, 0, 0)
                barDataSetSerious.valueTextSize = 12f
                barDataSetSerious.valueTextColor = Color.rgb(255, 0, 0)
                val barData = BarData(barDataSetHealth, barDataSetLight, barDataSetSerious)
                barData.setValueFormatter(getIntValueFormatter())
                barData.barWidth = barWidth
                this.data = barData
            }
            setVisibleXRangeMaximum(3.5f)
            invalidate()
        }
    }

    private fun isNullData(data: HealthData): Boolean {
        return (data.healthNum == 0 && data.lightNum == 0 && data.seriousNum == 0)
    }

    private fun getIntValueFormatter(): ValueFormatter {
        return object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val str = value.toString()
                return if (str.isEmpty()) {
                    str
                } else {
                    str.substring(0, str.indexOf("."))
                }
            }
        }
    }

    private fun getDateValueFormatter(): ValueFormatter {
        return object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val position = value.toInt()
                return healthDataList[position].day
            }
        }
    }

    private fun getInstituteValueFormatter(): ValueFormatter {
        return object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val position = value.toInt()
                return if (position >= healthDataList.size) {
                    ""
                } else {
                    healthDataList[position].institute.toString()
                }
            }
        }
    }

    private fun getDayFormat(time: Long): String {
        val date = Date(time)
        return SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date)
    }
}
