package app.okuyama.yuu.test_keiziban

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.okuyama.yuu.test_keiziban.databinding.ActivityAddBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.widget.Toast
import kotlin.concurrent.thread

class AddActivity : AppCompatActivity() {
    private lateinit var Addbinding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        Addbinding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var db = Firebase.firestore

        Addbinding.sousinTuikaKun.setOnClickListener {

            if (Addbinding.threadTuikaKun.length() == 0 || Addbinding.onamaeTuikaKun.length() == 0 || Addbinding.honbunTuikaKun.length() == 0) {
                Toast.makeText(applicationContext, "何か文字を入力してください", Toast.LENGTH_LONG).show()
            } else {

                val addnameList: List<String> = listOf(Addbinding.onamaeTuikaKun.text.toString())
                val addtextList: List<String> = listOf(Addbinding.honbunTuikaKun.text.toString())

                val data = Datas(
                    name = addnameList as MutableList<String>,
                    text = addtextList as MutableList<String>
                )

                val MainIntent: Intent = Intent(this, MainActivity::class.java)
                db.collection("Thread").document(Addbinding.threadTuikaKun.text.toString())
                    .set(data)
                    .addOnSuccessListener { documentReference ->
                        startActivity(MainIntent)
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }
                MainIntent.putExtra("threadName", Addbinding.threadTuikaKun.text.toString())
                startActivity(MainIntent)
            }
        }


    }
}