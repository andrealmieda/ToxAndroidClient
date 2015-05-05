package scaloid.example

import android.content.{Intent, Context, BroadcastReceiver}
import org.scaloid.common._
import android.graphics.drawable.ColorDrawable
import android.view.Gravity.CENTER


class HelloScaloid extends SActivity {

  private var text : STextView = null;
  var receiver : BroadcastReceiver = _

  onCreate {
    setTitle("First App in Scaloid")
    getActionBar.setLogo(new ColorDrawable(0x00FFFFFF))
    contentView = new SVerticalLayout {
      text = STextView("Checking Connection").gravity(CENTER)
    } padding 20.dip

    receiver = new UIBroadcasterReceiver
    registerReceiver(receiver,Constants.CONNECTION_STATUS)
    startService(SIntent[ToxService])


  }

  onDestroy({
    stopService(SIntent[ToxService])
    unregisterReceiver(receiver)
  })

  def updateLabel(newStatus :String): Unit ={
    text.text(newStatus)
  }

}

class UIBroadcasterReceiver extends BroadcastReceiver{
  implicit val tag = LoggerTag("ToxUI")

  def onReceive(context: Context, intent: Intent): Unit = {
    val service = context.asInstanceOf[HelloScaloid]
    warn("Received")
    service.updateLabel("Connected via "+intent.getStringExtra("status"))

  }
}

object Constants{
  val CONNECTION_STATUS = "0"
}