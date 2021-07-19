package huji.bez.drop

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.util.*
import kotlin.concurrent.schedule
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class DroplingSprite(activity: AppCompatActivity) {
    private var imageViewDropBody: ImageView = activity.findViewById(R.id.imageViewDropBody)
    private var imageViewDropFeatures: ImageView = activity.findViewById(R.id.imageViewDropFeatures)
    var parentActivity : AppCompatActivity = activity
    private var state : String = "IDLE"



    init {
        imageViewDropBody.setBackgroundColor(Color.TRANSPARENT)
        imageViewDropFeatures.setBackgroundColor(Color.TRANSPARENT)
        imageViewDropBody.setOnClickListener(){
            showTouchState()
        }
        imageViewDropFeatures.setOnClickListener(){
            showTouchState()
        }
    }
    /**** General Parameters ****/
    fun setBodyColor(color: Int) {
        imageViewDropBody.setColorFilter(color)
    }

    /**** State Switches ****/
    fun showTouchState() {
        when(state) {
            "IDLE" -> showTouchHappyState()
            "SAD" -> showTouchSadState()
        }
        val handler = Handler()
        handler.postDelayed( {
            when(state) {
                "IDLE" -> showIdleState()
                "SAD" -> showSadState()
            }
        }, 1000)
    }

    fun showTouchHappyState() {
        Glide.with(parentActivity).load(R.drawable.happy_touch_body)
            .into(imageViewDropBody)

        Glide.with(parentActivity).load(R.drawable.happy_touch_parts)
            .into(imageViewDropFeatures)

//        Glide.with(parentActivity).load(R.drawable.happy_touch_parts)
//            .listener(object : RequestListener<Drawable> {
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: com.bumptech.glide.request.target.Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    showIdleState()
//                    return true
//                }
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {return false}
//            })
//            .into(imageViewDropFeatures)
    }

    fun showTouchSadState() {
        Glide.with(parentActivity).load(R.drawable.sad_touch_body).into(imageViewDropBody)
        Glide.with(parentActivity).load(R.drawable.sad_touch_parts)
            .into(imageViewDropFeatures)
    }

    fun showIdleState() {
        state = "IDLE"
        Glide.with(parentActivity).load(R.drawable.idle_body).into(imageViewDropBody)
        Glide.with(parentActivity).load(R.drawable.idle_parts).into(imageViewDropFeatures)
    }

    fun showSadState() {
        state = "SAD"
        Glide.with(parentActivity).load(R.drawable.sad_idle_body).into(imageViewDropBody)
        Glide.with(parentActivity).load(R.drawable.sad_idle_parts).into(imageViewDropFeatures)
    }

    fun showSleepState() {
        Glide.with(parentActivity).load(R.drawable.sleep_body).into(imageViewDropBody)
        Glide.with(parentActivity).load(R.drawable.sleep_parts).into(imageViewDropFeatures)
    }

    /**** Movement Controls ****/
    fun scrollY(value: Int) {
        imageViewDropBody.scrollY = value
        imageViewDropFeatures.scrollY = value
    }

    fun scrollX(value: Int) {
        imageViewDropBody.scrollX = value
        imageViewDropFeatures.scrollX = value
    }

    fun moveBy(x: Int, y: Int) {
        scrollX(x)
        scrollY(y)
    }

}