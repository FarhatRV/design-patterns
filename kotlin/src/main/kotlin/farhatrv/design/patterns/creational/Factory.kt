package farhatrv.design.patterns.creational

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * The factory design pattern is used when we have a superclass with multiple sub-classes and based on input,
 * we need to return one of the sub-class.
 * This pattern takes out the responsibility of the instantiation of a class
 * from the client program to the factory class.
 *
 *
 * Factory pattern provides abstraction between implementation and client classes through inheritance.
 */
abstract class Computer {
    abstract val rAM: String
    abstract val hDD: String
    abstract val cPU: String

    override fun toString(): String {
        return "Computer Type= " + this.javaClass + ", RAM= " + rAM + ", HDD=" + hDD + ", CPU=" + cPU
    }
}

class PC(override val rAM: String, override val hDD: String, override val cPU: String) : Computer()

class Server(override val rAM: String, override val hDD: String, override val cPU: String) : Computer()

object ComputerFactory {
    // child object creation method should either be static or should be served via a singleton object
    fun getComputer(type: String, ram: String, hdd: String, cpu: String): Computer? {
        return when (type) {
            "PC" -> PC(ram, hdd, cpu)
            "Server" -> Server(ram, hdd, cpu)
            else -> null
        }
    }
}

object Factory {
    @JvmStatic
    fun main(args: Array<String>) {
        val logger: Logger = LoggerFactory.getLogger(Factory::class.java)
        val pc = ComputerFactory.getComputer("Server", "2 GB", "50 GB", "2.4 GHz")
        val server = ComputerFactory.getComputer("PC", "16 GB", "1 TB", "2.9 GHz")
        logger.info(pc.toString())
        logger.info(server.toString())
    }
}
