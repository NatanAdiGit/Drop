package huji.bez.drop

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DroplingSprite
    (activity: AppCompatActivity) {
    private var imageViewDropBody: ImageView = activity.findViewById(R.id.imageViewDropBody)
    private var imageViewDropFeatures: ImageView = activity.findViewById(R.id.imageViewDropFeatures)
    var parentActivity : AppCompatActivity = activity

    init {
        imageViewDropBody.setBackgroundColor(Color.TRANSPARENT)
        imageViewDropFeatures.setBackgroundColor(Color.TRANSPARENT)
    }

    fun showHappyState() {
        Glide.with(parentActivity).load(R.drawable.droplet_test).into(imageViewDropBody)
    }

}