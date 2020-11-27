
import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.GpioPinDigitalOutput
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin


trait Blink {

  //setup
  val gpio: GpioController = GpioFactory.getInstance

  val myLed: GpioPinDigitalOutput =
    gpio.provisionDigitalOutputPin(
      RaspiPin.GPIO_00,
      "My LED",
      PinState.LOW
    )

  println(s"using pin: ${myLed.getPin}")

  def runMain: Unit = {
    while (true) {
      myLed.setState(PinState.HIGH)
      println("led turned on >>>")
      Thread.sleep(1000)
      myLed.setState(PinState.LOW)
      println("led turned off <<<")
      Thread.sleep(1000)
    }
  }

  def runShutdown: Unit = {
    println("gpio down")
    gpio.shutdown()
  }
}

object Raspignegna extends App with Blink {
  runMain

  val shutDownThread: Thread = new Thread {
    override def run(): Unit =
      runShutdown
  }

  java.lang.Runtime.getRuntime.addShutdownHook(shutDownThread)
}
