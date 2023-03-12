package app.okuyama.yuu.test_keiziban

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.okuyama.yuu.test_keiziban.MyClass.Companion.nameList
import app.okuyama.yuu.test_keiziban.MyClass.Companion.textList
import app.okuyama.yuu.test_keiziban.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        nameList.clear()
        textList.clear()
        val db = Firebase.firestore
        val selectIntent:Intent = Intent(this,SelectActivity::class.java)
        val manager = LinearLayoutManager(this)
        //binding.recyclerView2.layoutManager = manager
        val taro = MainAdapter()
        //binding.recyclerView2.adapter = taro

        val rThreadName = intent.getStringExtra("threadName").toString()
        binding.documenttextview.text = rThreadName.toString()

        val main = mutableListOf<Datas>()

        binding.selectmodoru.setOnClickListener {
            startActivity(selectIntent)
        }

        db.collection("Thread")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    //Log.d("xxxxxx" ,rThreadName.toString())
                    if (document.id == rThreadName) {
                        /*//val length = Datas(document.data.get("name") as List<String>)
                        main.add(Datas(document.data.get("name") as MutableList<String>,document.data.get("text") as MutableList<String>))
                         */

                        nameList = document.data.get("name") as MutableList<String>
                        textList = document.data.get("text") as MutableList<String>

                    }
                }

                var i = 0

                while (i < nameList.size) {
                    var numberView = TextView(this)
                    val nameView = TextView(this)
                    val messageView = TextView(this)
                    val layout = LinearLayout(this)

                    val s = i + 1

                    numberView.text = s.toString()
                    nameView.text = nameList[i].toString()
                    messageView.text = textList[i].toString()

                    numberView.textSize = 20.0f
                    nameView.textSize = 20.0f
                    messageView.textSize = 40.0f
                    messageView.typeface = Typeface.DEFAULT_BOLD

                    layout.addView(numberView)
                    layout.addView(nameView)
                    layout.addView(messageView)

                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    lp.gravity = Gravity.LEFT
                    numberView.layoutParams = lp
                    nameView.layoutParams = lp
                    messageView.layoutParams = lp

                    binding.comment.addView(layout)

                    i = i + 1
                }
                //Log.d("neko", main.toString())
                //taro.updateThreads(main)
            }

        //送信ボタン
        binding.sousin.setOnClickListener {
            db.collection("Thread")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        //Log.d("xxxxxx" ,rThreadName.toString())
                        if (document.id == rThreadName) {
                            val list: List<String> = document.data.get("name") as List<String>
                            val size = list.size
                            var tNameList: MutableList<String> = document.data.get("name") as MutableList<String>
                            tNameList.add(size, binding.onamae.text.toString())
                            var tTextList: MutableList<String> = document.data.get("text") as MutableList<String>
                            tTextList.add(size, binding.kakiko.text.toString())

                            val data = Datas(
                                name = tNameList,
                                text = tTextList
                            )

                            //書き込み
                            db.collection("Thread").document(binding.documenttextview.text.toString())
                                .set(data)
                                .addOnSuccessListener {
                                }
                                .addOnFailureListener {
                                }

                            nameList = tNameList
                            textList = tTextList

                            val MainIntent: Intent = Intent(this,MainActivity::class.java)
                            MainIntent.putExtra("threadName",rThreadName)
                            startActivity(MainIntent)
                        }


                        }
                    }
                    //Log.d("neko", main.toString())
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