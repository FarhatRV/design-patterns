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

enum class CommonComputerTypes {
    SERVER,
    PC
}

enum class SuperComputerTypes {
    MASTER_BASED_CLUSTER,
    MULTI_NODE_CLUSTER
}

class PC(override val rAM: String, override val hDD: String, override val cPU: String) : Computer()

class Server(override val rAM: String, override val hDD: String, override val cPU: String) : Computer()

class MultiNodeSuperComputer(
    override val rAM: String, override val hDD: String, override val cPU: String, private val numberOfNodes: Int
) : Computer() {
    override fun toString(): String {
        return super.toString() + ", number of nodes=" + numberOfNodes
    }
}

object ComputerFactory {
    // child object creation method should either be static or should be served via a singleton object
    fun getComputer(type: String, ram: String, hdd: String, cpu: String): Computer {
        return when (enumValueOf<CommonComputerTypes>(type)) {
            CommonComputerTypes.PC -> PC(ram, hdd, cpu)
            CommonComputerTypes.SERVER -> Server(ram, hdd, cpu)
        }
    }
}

class ComputerFactoryProvider {
    inline fun <reified M : Enum<M>> createComputer(
        type: String,
        ram: String, hdd: String,
        cpu: String,
        numberOfNodes: Int = 2
    ): Computer? {
        return when (enumValueOf<M>(type)) {
            CommonComputerTypes.SERVER -> Server(ram, hdd, cpu)
            CommonComputerTypes.PC -> PC(ram, hdd, cpu)
            SuperComputerTypes.MULTI_NODE_CLUSTER -> MultiNodeSuperComputer(ram, hdd, cpu, numberOfNodes)
            else -> null
        }
    }
}

object Factory {
    @JvmStatic
    fun main(args: Array<String>) {
        val logger: Logger = LoggerFactory.getLogger(Factory::class.java)

        // factory 1 - type defined on 1 level
        val pc = ComputerFactory.getComputer("SERVER", "2 GB", "50 GB", "2.4 GHz")
        val server = ComputerFactory.getComputer("PC", "16 GB", "1 TB", "2.9 GHz")
        logger.info(pc.toString())
        logger.info(server.toString())

        // factory 2 - FactoryProvider - type defined on 2 levels
        val computerFactoryProvider = ComputerFactoryProvider()
        val pc1 = computerFactoryProvider.createComputer<SuperComputerTypes>(
            "MULTI_NODE_CLUSTER", "16 GB", "1 TB", "2.9 GHz", 16
        )
        logger.info(pc1.toString())
    }
}
