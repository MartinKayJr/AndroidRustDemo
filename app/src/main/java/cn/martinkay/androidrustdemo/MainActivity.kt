package cn.martinkay.androidrustdemo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.martinkay.androidrustdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var activityMainBinding: ActivityMainBinding? = null

    companion object {
        init {
            System.loadLibrary("rust")
        }
    }

    external fun test(input: String): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding?.root)
        activityMainBinding?.textView?.text = test("Rust")

    }
}