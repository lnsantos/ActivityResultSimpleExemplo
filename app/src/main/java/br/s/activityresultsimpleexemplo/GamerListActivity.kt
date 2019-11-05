package br.s.activityresultsimpleexemplo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*

class GamerListActivity : AppCompatActivity() {
    companion object{
        const val RESULT_KEY = "result"
    }

        private lateinit var listView : ListView
        private lateinit var listAdapter : ArrayAdapter<String>
        private var position = -1
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        setContentView(listView)

        val games = resources.getStringArray(R.array.games)
        listAdapter = ArrayAdapter(this, android.R.layout.simple_selectable_list_item, games)
        listView.adapter = listAdapter
        listView.choiceMode = AbsListView.CHOICE_MODE_SINGLE
        val game = intent.getStringExtra(RESULT_KEY)

        if (game != null){
            for (_game in games){
                if(game != _game){
                    val positionGame = games.indexOf(_game)
                    listView.setItemChecked(positionGame, false)
                }
            }
            this.position = games.indexOf(game)
            listView.setItemChecked(position, true)
        }

         listView.setOnClickListener {view ->
             val resultFinal = listView.getItemAtPosition(position) as String
             var intent = Intent()

             intent.putExtra(RESULT_KEY,resultFinal)
             setResult(Activity.RESULT_OK, intent)
             finish()
         }

    }


}
