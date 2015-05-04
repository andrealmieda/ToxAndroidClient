package scaloid.example

import im.tox.tox4j.core.callbacks.ConnectionStatusCallback
import im.tox.tox4j.core.enums.ToxConnection
import org.scaloid.common._
import android.graphics.drawable.ColorDrawable
import android.view.Gravity._
import im.tox.tox4j.ToxCoreImpl
import im.tox.tox4j.core.{ToxCore, ToxConstants, ToxOptions}


class HelloScaloid extends SActivity {

  implicit val tag = LoggerTag("MyAppTag")

  private var text : STextView = null;
  private var connection : ToxCore = null

  onCreate {
    setTitle("First App in Scaloid")
    getActionBar.setLogo(new ColorDrawable(0x00FFFFFF))
    contentView = new SVerticalLayout {
      text = STextView("Checking Connection").gravity(CENTER)
    } padding 20.dip

    connection = new ToxCoreImpl(new ToxOptions(), null)

    connection.bootstrap("192.254.75.102", 33445, parsePublicKey("951C88B7E75C867418ACDB5D273821372BB5BD652740BCDF623A4FA293E75D2F"))

    connection.callbackConnectionStatus(new connectionSTA)

    runOnUiThread(new Runnable() {

      def run() {

        while(true){
          connection.iteration()
          connection.iterationInterval()
        }

      }

    })

  }

  private def parsePublicKey(id: String): Array[Byte] = {
    val publicKey = Array.ofDim[Byte](ToxConstants.PUBLIC_KEY_SIZE)

    for (i <- 0 until ToxConstants.PUBLIC_KEY_SIZE) {
      publicKey(i) = (
        (fromHexDigit(id.charAt(i * 2)) << 4) +
          fromHexDigit(id.charAt(i * 2 + 1))
        ).toByte
    }
    publicKey


  }

  private def fromHexDigit(c: Char): Byte = {
    if (c >= '0' && c <= '9') {
      (c - '0').toByte
    } else if (c >= 'a' && c <= 'f') {
      (c - 'A' + 10).toByte
    } else if (c >= 'A' && c <= 'F') {
      (c - 'A' + 10).toByte
    } else {
      throw new IllegalArgumentException("Non-hex digit character: " + c)
    }
  }

  private sealed class connectionSTA() extends ConnectionStatusCallback {

    def connectionStatus(connectionStatus: ToxConnection): Unit = {
      warn(s"connectionStatus($connectionStatus)")
      text.text(s"connectionStatus($connectionStatus)")
    }

  }



}

