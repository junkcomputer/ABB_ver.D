package app.okuyama.yuu.test_keiziban

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import app.okuyama.yuu.test_keiziban.databinding.ActivityAddBinding
import app.okuyama.yuu.test_keiziban.databinding.ActivitySelectBinding
import app.okuyama.yuu.test_keiziban.databinding.ThreadNameBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.concurrent.thread

class SelectActivity : AppCompatActivity() {
    private lateinit var Selectbinding: ActivitySelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        Selectbinding = ActivitySelectBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Firebase.firestore

        val adapter = ThreadNameAdapter()

        db.collection("Thread")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val idView = Button(this)
                    val layout = LinearLayout(this)
                    idView.text = document.id.toString()
                    idView.textSize = 20.0f
                    idView.setOnClickListener {
                        val mainIntent: Intent = Intent(this,MainActivity::class.java)
                        mainIntent.putExtra("threadName",document.id.toString())
                        startActivity(mainIntent)
                    }

                    layout.addView(idView)

                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    lp.gravity = Gravity.CENTER_VERTICAL
                    idView.layoutParams = lp

                    Selectbinding.container.addView(layout)
                }
                /*val threadNames = mutableListOf<ThreadNames>()
                for (document in result) {
                    threadNames.add(ThreadNames(document.id))
                }
                adapter.updateThreads(threadNames)
                 */
            }

        //Selectbinding.recyclerView.adapter = adapter



        val AddIntent:Intent = Intent(this,AddActivity::class.java)
        Selectbinding.tuika.setOnClickListener {
            startActivity(AddIntent)
        }
    }
}