package otus.homework.coroutines

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope

class CatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ICatsView {

    var presenter :CatsPresenter? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<Button>(R.id.button).setOnClickListener {
            presenter?.onInitComplete(GlobalScope)
        }
    }

    override fun populate(catDescription: CatDescription) {
        findViewById<TextView>(R.id.fact_textView).text = catDescription.fact
        val image = findViewById<ImageView>(R.id.cat_image)
        Picasso.get().load(catDescription.imageUrl).into(image)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(@StringRes messageId: Int, vararg formatArgs: Any?) {
        showError(context.getString(messageId, formatArgs))
    }
}

interface ICatsView {

    fun populate(catDescription: CatDescription)

    fun showError(message: String)
    fun showError(@StringRes messageId: Int, vararg formatArgs: Any?)
}