package app.okuyama.yuu.test_keiziban

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.okuyama.yuu.test_keiziban.databinding.ActivityHomeBinding
import app.okuyama.yuu.test_keiziban.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val SelectIntent:Intent = Intent(this,SelectActivity::class.java)

        binding.button2.setOnClickListener {
            startActivity(SelectIntent)
        }
    }
}