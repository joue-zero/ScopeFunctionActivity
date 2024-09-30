package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())
        Log.d("function output MyCode", getTestDataArray().toString())
        Log.d("function output ", getTestDataArray1().toString())
        val testArray = List(10){ Random.nextDouble() }
        Log.d("function output MyCode", averageLessThanMedian(testArray).toString())
        Log.d("function output", averageLessThanMedian1(testArray).toString())

        // A list of integers for demonstration
        val collection = listOf(1, 2, 3, 4, 5)

        // Get reference to the layout where views will be added
        val textViewLayout = findViewById<LinearLayout>(R.id.linearLayout)

        // Iterate through the collection and add views to the layout
        for (i in collection.indices) {
            // Pass null for recycledView as we are creating views from scratch in this example
            val textView = getView(i, null, collection, this)
            textViewLayout.addView(textView)
        }

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray1() : List<Int> {
        val testArray = MutableList(10){ Random.nextInt()}
        testArray.sort()
        return testArray
    }
//    private fun getTestDataArray() = MutableList(10){ Random.nextInt()}.sorted()
    private fun getTestDataArray() = MutableList(10){ Random.nextInt()}.apply { sort() }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian1(listOfNumbers: List<Double>): Boolean {
        val avg = listOfNumbers.average()
        val sortedList = listOfNumbers.sorted()
        val median = if (sortedList.size % 2 == 0)
            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
        else
            sortedList[sortedList.size / 2]

        return avg < median
    }
    private fun averageLessThanMedian(listOfNumbers: List<Double>) = listOfNumbers.average() < listOfNumbers.sorted().let {
        if (it.size % 2 == 0)
                (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
        else
            it[it.size / 2]
    }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
//    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
//        val textView: TextView
//
//        if (recycledView != null) {
//            textView = recycledView as TextView
//        } else {
//            textView = TextView(context)
//            textView.setPadding(5, 10, 10, 0)
//            textView.textSize = 22f
//        }
//
//        textView.text = collection[position].toString()
//
//        return textView
//    }
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) = recycledView?.let { it as TextView } ?: TextView(context).apply {
        setPadding(5, 10, 10, 0)
        textSize = 22f
    }.apply { text = collection[position].toString() }

}