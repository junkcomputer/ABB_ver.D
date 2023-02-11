package app.okuyama.yuu.test_keiziban

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.UserDictionary.Words
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import app.okuyama.yuu.test_keiziban.databinding.ActivityAddBinding
import app.okuyama.yuu.test_keiziban.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.units.qual.A
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Firebase.firestore
        val taro = MainAdapter()

        binding.recyclerView2.adapter = taro
        val rThreadName = intent.getStringExtra("threadName").toString()
        binding.documenttextview.text = rThreadName

        val main = mutableListOf<Datas>()

        db.collection("Thread")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    //Log.d("xxxxxx" ,rThreadName.toString())
                    if (document.id == rThreadName) {
                            main.add(Datas(document.data.get("name").toString(),document.data.get("text").toString()))
                    }
                }
                Log.d("neko", main.toString())
                taro.updateThreads(main)
            }

        binding.kousinkun.setOnClickListener {
            val MainIntent: Intent = Intent(this,MainActivity::class.java)
            MainIntent.putExtra("threadName",rThreadName)
            startActivity(MainIntent)
        }
    }
}

/*
        //送信ボタン
        binding.sousin.setOnClickListener {

            val data = Datas(
                name = binding.onamae.text.toString(),
                text = binding.kakiko.text.toString()
            )

            //書き込み

            db.collection("Thread").document()
                .set(data)
                .addOnSuccessListener {
                }
                .addOnFailureListener {
                }

            val MainIntent: Intent = Intent(this,MainActivity::class.java)
            startActivity(MainIntent)
        }*/



//読み込み
/*db.collection("Thread").document(rThreadName)
    .get()
    .addOnCompleteListener { result ->
        val datas  = mutableListOf<Datas>()
            if (result.isSuccessful) {
                val document = result.result
                    while (document != null) {
                        datas.add(Datas(document.data?.get("name").toString(), document.data?.get("text").toString()))
                    }
            }
        adapter.updateThreads(datas)
    }
    .addOnFailureListener { e ->
        Log.w(TAG,"Error reading document",e)
    }
 */