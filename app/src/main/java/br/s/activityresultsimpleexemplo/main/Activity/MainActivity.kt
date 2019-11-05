package br.s.activityresultsimpleexemplo.main.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.s.activityresultsimpleexemplo.R
import br.s.activityresultsimpleexemplo.core.constantes.ObjectGlobal.GameList
import br.s.activityresultsimpleexemplo.core.constantes.ObjectGlobal.Main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var game: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null){
            if(game != null){
                game = savedInstanceState.getString(GameList.RESULT_KEY)
                button.text = game
            }
        }

        button.setOnClickListener {
            if(it != null){
                var intent = Intent(this, GamerListActivity::class.java)
                intent.putExtra(GameList.GAMES_KEY,game)
                startActivityForResult(intent,Main.REQUEST_GAME)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) when(requestCode){
            Main.REQUEST_GAME -> {
                game = data?.getStringExtra(GameList.RESULT_KEY)
                button.text = game
            }
        }else {
            Toast.makeText(this,"Result in this cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(GameList.GAMES_KEY,game)
    }


}
