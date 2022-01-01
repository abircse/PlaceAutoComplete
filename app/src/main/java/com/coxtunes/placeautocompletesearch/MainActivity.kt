package com.coxtunes.placeautocompletesearch

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode


class MainActivity : AppCompatActivity() {


    /**
     * Auto Complete Search using google places Api
     *
     * @param AutocompleteActivityMode = FULLSCREEN, OVERLAY
     * @param .setCountry("BD") AS we want search BD place
     * @param place = allkinds of google returns data
     */

    lateinit var searchbox: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        searchbox = findViewById(R.id.searchbox)
        searchbox.setOnClickListener {
            val fields: List<Place.Field> = listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.ADDRESS_COMPONENTS,
                Place.Field.LAT_LNG,
                Place.Field.PLUS_CODE
            )
            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN,
                fields
            ).setCountry("BD").build(this)
            resultLauncher.launch(intent)
        }
    }

    // On Activity Intent Result
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                when (result.resultCode) {
                    Activity.RESULT_OK -> {
                        val place = Autocomplete.getPlaceFromIntent(result.data!!)
                        // place object contain other required data which are needed here like lat.long etc
                        textView.text = place.address!!.toString()

                    }
                    AutocompleteActivity.RESULT_ERROR -> {
                        textView.text = "Error"
                    }
                    AutocompleteActivity.RESULT_CANCELED -> {
                        Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
}