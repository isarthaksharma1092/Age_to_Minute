package com.sarthaksharma.dobcal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity()
{
    private var selectedScreen1 :TextView? = null
    private var selectedScreen2 :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//**************************************************************************************************

        val datePicker: Button = findViewById(R.id.btnDatePicker)  // datePicker has access to btnDatePicker item

        selectedScreen1 = findViewById(R.id.screen1)
        selectedScreen2 = findViewById(R.id.screen2)
        datePicker.setOnClickListener() {
            clickDatePicker()
        }
    }
    fun clickDatePicker() {
        val myCalender = Calendar.getInstance() //to get access to calender
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dPD = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"year was $selectedYear, month was ${selectedMonth+1}, day of month was $selectedDayOfMonth", Toast.LENGTH_LONG).show()
                // why month+1 beacuse of index starts with zero but it dosent happens in other cases like year or days, so chill

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                selectedScreen1?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateMinutes = theDate.time/60000
                val currDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currDateMinute = currDate.time/60000
                val diffInMinutes = currDateMinute - selectedDateMinutes
                selectedScreen2?.setText(diffInMinutes.toString())


            },
            year,
            month,
            day)

        dPD.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dPD.show()
    }
}

//******************* Notes *************************
//Sp = Scale independent pixels, DP = Density Independent pixels
// Wrap_Content => The view should be only big enough to enclose its content
