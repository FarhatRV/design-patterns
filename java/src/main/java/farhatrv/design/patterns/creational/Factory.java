package farhatrv.design.patterns.creational;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The factory design pattern is used when we have a superclass with multiple sub-classes and based on input,
 * we need to return one of the sub-class.
 * This pattern takes out the responsibility of the instantiation of a class
 * from the client program to the factory class.
 * <p>
 * Factory pattern provides abstraction between implementation and client classes through inheritance.
 */
abstract class Computer {

    public abstract String getRAM();

    public abstract String getHDD();

    public abstract String getCPU();

    @Override
    public String toString() {
        return "Computer Type= " + this.getClass() + ", RAM= " + this.getRAM() + ", HDD=" + this.getHDD() + ", CPU=" + this.getCPU();
    }
}

class PC extends Computer {
    private final String ram;
    private final String hdd;
    private final String cpu;

    public PC(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }
}

class Server extends Computer {
    private final String ram;
    private final String hdd;
    private final String cpu;

    public Server(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }

}

class ComputerFactory {
    // child object creation method should either be static or should be served via a singleton object
    public static Computer getComputer(@NotNull String type, String ram, String hdd, String cpu) {
        switch (type) {
            case "PC":
                return new PC(ram, hdd, cpu);
            case "Server":
                return new Server(ram, hdd, cpu);
            default:
                return null;
        }
    }
}

public class Factory {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Factory.class);
        Computer pc = ComputerFactory.getComputer("PC", "2 GB", "500 GB", "2.4 GHz");
        Computer server = ComputerFactory.getComputer("Server", "16 GB", "1 TB", "2.9 GHz");
        logger.info(String.valueOf(pc));
        logger.info(String.valueOf(server));
    }
}
