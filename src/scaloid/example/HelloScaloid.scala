package scaloid.example

import org.scaloid.common._
import android.graphics.Color
import android.app.ActionBar
import android.graphics.drawable.ColorDrawable
import android.view.Gravity._
import im.tox.tox4j.ToxCoreImpl
import im.tox.tox4j.core.ToxOptions

class HelloScaloid extends SActivity {

  onCreate {
    setTitle("First App in Scaloid")
    getActionBar.setLogo(new ColorDrawable(0x00FFFFFF))
    contentView = new SVerticalLayout{
      STextView("Welcome to my First app Screen").gravity(CENTER)
      SButton("Other View").onClick(startActivity[Other])
    }padding 20.dip

    
 
  }

  new ToxCoreImpl(new ToxOptions(), null).close()
  
}


