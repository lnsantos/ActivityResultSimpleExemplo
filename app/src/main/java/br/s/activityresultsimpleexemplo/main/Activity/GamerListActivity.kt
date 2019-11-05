package br.s.activityresultsimpleexemplo.main.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import br.s.activityresultsimpleexemplo.R
import br.s.activityresultsimpleexemplo.core.constantes.ObjectGlobal

class GamerListActivity : AppCompatActivity() {
        private lateinit var listView : ListView
        private lateinit var listAdapter : ArrayAdapter<String>
        private var position = -1

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        setContentView(listView)

        val games = resources.getStringArray(R.array.games)
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, games)
        listView.adapter = listAdapter
        listView.choiceMode = AbsListView.CHOICE_MODE_SINGLE

        val game = intent.getStringExtra(ObjectGlobal.GameList.RESULT_KEY)

        if (game != null){
            this.position = games.indexOf(game)
            listView.setItemChecked(position, true)

            for (_game in games){
                if(games[position] != _game){
                    val positionGame = games.indexOf(_game)
                    listView.setItemChecked(positionGame, false)
                }
            }
        }

         listView.setOnItemClickListener{list,view,index,_->
             if(view != null){
                 val gameSelect = games[index]
                 var intent = Intent()

                 intent.putExtra(ObjectGlobal.GameList.RESULT_KEY,gameSelect)
                 setResult(Activity.RESULT_OK, intent)
                 finish()
             }
         }

    }


}
