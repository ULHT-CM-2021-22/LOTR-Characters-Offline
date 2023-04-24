package pt.ulusofona.cm.lotrcharactersoffline.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.ConnectivityUtil
import pt.ulusofona.cm.lotrcharactersoffline.databinding.ActivityMainBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var model: LOTR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = LOTRRepository.getInstance()
    }

    override fun onStart() {
        super.onStart()

        binding.getCharactersBtn.setOnClickListener {

            // show a circular progress indicator
            binding.getCharactersBtn.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                // call getCharacters on the "IO Thread"
                model.getCharacters { result ->
                    // process the result in the "Main Thread" since it will change the view
                    CoroutineScope(Dispatchers.Main).launch {

                        if (result.isSuccess) {

                            if (!ConnectivityUtil.isOnline(this@MainActivity)) {
                                Toast.makeText(this@MainActivity, "You are offline. Retrieved characters from the local database", Toast.LENGTH_SHORT).show()
                            }

                            val characters = result.getOrNull()!!
                            val intent = Intent(this@MainActivity, CharactersListActivity::class.java)
                            Log.i("APP", "Passing ${characters.size} characters to CharactersListActivity")

                            // have to convert to array to be able to pass it to another activity
                            intent.putExtra("characters", characters.toTypedArray())
                            startActivity(intent)

                        } else {

                            // show a dialog
                            AlertDialog.Builder(this@MainActivity)
                                .setTitle("Error")
                                .setMessage("There was an error connecting to the server")
                                .setPositiveButton("OK") { _,_ -> }
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .create()
                                .show()
                        }

                        // dismiss the circular progress indicator
                        binding.getCharactersBtn.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}