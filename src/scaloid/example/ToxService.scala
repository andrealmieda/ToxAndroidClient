package scaloid.example

import android.content.Intent
import android.os.IBinder
import im.tox.tox4j.core.callbacks.{FriendRequestCallback, ConnectionStatusCallback}
import im.tox.tox4j.core.enums.ToxConnection
import im.tox.tox4j.core.{ToxConstants, ToxOptions}
import org.scaloid.common._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import im.tox.tox4j.ToxCoreImpl


class ToxService extends SService{

  def onBind(intent: Intent): IBinder = null
  var toxService : ToxCoreImpl = null
  implicit val tag = LoggerTag("ToxService")


  onCreate({

    toxService = new ToxCoreImpl(new ToxOptions(), null)
    toxService.bootstrap("192.254.75.102", 33445, parsePublicKey("951C88B7E75C867418ACDB5D273821372BB5BD652740BCDF623A4FA293E75D2F"))
    toxService.callbackConnectionStatus(new connectionStatus)
    toxService.callbackFriendRequest(new friendRequest)


    Future{
      while(true) {
        toxService.iteration()
        Thread.sleep(toxService.iterationInterval())
      }
    }

  })

  onDestroy({
    toxService.close()
  })


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

  private def readablePublicKey(id: Array[Byte]): String = {
    val str: StringBuilder = new StringBuilder
    for (b <- id) {
      str.append(f"$b%02X")
    }
    str.toString()
    }

  private sealed class connectionStatus extends ConnectionStatusCallback{

    def connectionStatus(connectionStatus : ToxConnection): Unit ={
      warn("Status Update")
      sendBroadcast(new Intent(Constants.CONNECTION_STATUS).putExtra("status",s"$connectionStatus"))
      info(readablePublicKey(toxService.getPublicKey))
      info(toxService.getAddress.mkString)
    }

  }

  private sealed class friendRequest extends FriendRequestCallback{

    def friendRequest(publicKey: Array[Byte], timeDelta: Int, message: Array[Byte]): Unit = {
      info("New Friend Request "+readablePublicKey(publicKey)+" -> "+message)
    }

  }

}


