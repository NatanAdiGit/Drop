package huji.bez.drop

import android.graphics.Color
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DroplingSprite(activity: AppCompatActivity) {
    private var imageViewDropBody: ImageView = activity.findViewById(R.id.imageViewDropBody)
    private var imageViewDropFeatures: ImageView = activity.findViewById(R.id.imageViewDropFeatures)
    var parentAct : AppCompatActivity = activity

    init {
        imageViewDropBody.setBackgroundColor(Color.TRANSPARENT)
        imageViewDropFeatures.setBackgroundColor(Color.TRANSPARENT)
    }
    /**** General Parameters ****/
    fun setBodyColor(color: Int) {
        imageViewDropBody.setColorFilter(color)
    }

    /**** State Switches ****/
    fun showIdleState() {
        Glide.with(parentAct).load(R.drawable.idle_body).into(imageViewDropBody)
        Glide.with(parentAct).load(R.drawable.idle_parts).into(imageViewDropFeatures)
    }

    fun showSadState() {
        Glide.with(parentAct).load(R.drawable.sad_idle_body).into(imageViewDropBody)
        Glide.with(parentAct).load(R.drawable.sad_idle_parts).into(imageViewDropFeatures)
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