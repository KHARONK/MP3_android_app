package nmhu.edu.javakotlin

import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


class MoodActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        var path = ""
        val raining = findViewById<ImageView>(R.id.imageView1)
        val intent = intent
        val choice = intent.getStringExtra(EXTRA)

        val textView = findViewById<TextView>(R.id.audiofile)
        textView.setText("https://www.zapsplat.com/").toString()
        val textViewValue = textView.text
        val imageUrlTextView = findViewById<TextView>(R.id.imageUrl)



        path = if (choice == "Blue")
        {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.BLUE)
            imageUrlTextView.setText("image Url: https://www.wallpapers13.com/rain-drops/");
            raining.setImageResource(R.drawable.raining)
            "android.resource://" + this.packageName + "/raw/rain"
        }
        else if (choice == "Yellow") {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.YELLOW)
            imageUrlTextView.setText("image Url: https://www.smithsonianmag.com/science-nature/north-america-has-Universal-Tree-and-the-African-198271");
            raining.setImageResource(R.drawable.birdy)
            "android.resource://" + this.packageName + "/raw/birds"

        }
        else { //Green
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.GREEN)
            imageUrlTextView.setText("image Url: https://dlpng.com/png/4717597/");
            raining.setImageResource(R.drawable.windy)
            "android.resource://" + this.packageName + "/raw/wind"
        }
        val uri = Uri.parse(path)
        try {
            run {
                mediaPlayer!!.setDataSource(applicationContext, uri)
                mediaPlayer!!.prepare()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaPlayer!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = null
    }

    companion object {
        const val EXTRA = "package nmhu.edu.javaproject.EXTRA"
    }
}

