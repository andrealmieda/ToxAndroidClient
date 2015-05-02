package scaloid.example

import org.scaloid.common._
import android.graphics.Color
import android.view.Gravity._

class Other extends SActivity{


	onCreate{
		contentView = new SVerticalLayout{
			STextView("ID").gravity(CENTER)
		}padding 20.dip
	}
}