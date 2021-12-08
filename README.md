### Development
[Vagrant](https://www.vagrantup.com/) is used for a shared development environment

This project is written in Java, targeting JDK 17. It utilizes [LibGDX](https://libgdx.com/) for graphics, [SquidLib](https://github.com/yellowstonegames/SquidLib) for roguelike tools (pathfinding, LOS, etc.).


### Building
Requires Java 17 JDK

Use vagrant to make things easy!
```shell
vagrant up
vagrant ssh
cd /vagrant
./build.sh
```

### Running
Requires Java 17 JRE
```shell
java -jar target/lords-of-mysterium-jar-with-dependencies.jar
```