package app.okuyama.yuu.test_keiziban

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.okuyama.yuu.test_keiziban.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Firebase.firestore
        val manager = LinearLayoutManager(this)
        binding.recyclerView2.layoutManager = manager
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
                //Log.d("neko", main.toString())
                taro.updateThreads(main)
            }

        //送信ボタン
        binding.sousin.setOnClickListener {

            val data = Datas(
                name = binding.onamae.text.toString(),
                text = binding.kakiko.text.toString()
            )

            //書き込み

            db.collection("Thread").document(binding.documenttextview.text.toString())
                .set(data)
                .addOnSuccessListener {
                }
                .addOnFailureListener {
                }

            val MainIntent: Intent = Intent(this,MainActivity::class.java)
            MainIntent.putExtra("threadName",rThreadName)
            startActivity(MainIntent)
        }

        binding.kousinkun.setOnClickListener {
            val MainIntent: Intent = Intent(this,MainActivity::class.java)
            MainIntent.putExtra("threadName",rThreadName)
            startActivity(MainIntent)
        }
    }
}

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