package app.motsu.hiromoto.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val memo:Memo? = read()

        if(memo != null){
            titleTextEdit.setText(memo.titile)
            contentTextEdit.setText(memo.content)
        }

        saveButton.setOnClickListener{
            val title:String = titleTextEdit.text.toString()
            val content:String = contentTextEdit.text.toString()
            save(title,content)
        }

    }

    fun save(title: String, content: String) {
        val memo:Memo? = read()

        realm.executeTransaction {
            if(memo != null){
                memo.titile = title
                memo.content = content
            }else{
                val newMemo: Memo = it.createObject(Memo::class.java)
                newMemo.titile = title
                newMemo.content = content
            }
            Snackbar.make(container,"保存しました！",Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun read():Memo?{
        return realm.where(Memo::class.java).findFirst()
    }
}