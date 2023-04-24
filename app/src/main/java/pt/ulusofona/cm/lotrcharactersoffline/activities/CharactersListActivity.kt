package pt.ulusofona.cm.lotrcharactersoffline.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ulusofona.cm.lotrcharactersoffline.databinding.ActivityCharactersListBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter
import pt.ulusofona.cm.lotrcharactersoffline.ui.adapter.CharacterListAdapter

class CharactersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersListBinding

    private lateinit var characters: List<LOTRCharacter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characters = (intent.getSerializableExtra("characters") as Array<LOTRCharacter>).asList()

        binding.charactersListRv.layoutManager = LinearLayoutManager(this)
        binding.charactersListRv.adapter = CharacterListAdapter(characters)
        // add a divider between items of the list
        binding.charactersListRv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

    }
}