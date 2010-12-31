# MiddleCraft

MiddleCraft (will be) advanced middleware for Minecraft, with a focus on servers.  The idea is to create a familiar API for developers to use, one that doesn't change as often and suddenly as the Minecraft code itself.

## Compiling

### Ant (the easy way)

First, ensure you have at least the J6SE (Java 6 Standard Edition) JDK installed; if you have installed Xcode on a Macintosh running Snow Leopard you already have this, otherwise you will need to [download it from Oracle's website][http://www.oracle.com/technetwork/java/javase/downloads/index.html]

The rest of the process is handled for you automatically by ant; it will take care of downloading the latest minecraft_server.jar and validating it to ensure it is compatible with this release.  Simply run this command to have everything set up to default:

$ ant full-setup

### IDEs

If you would rather use an IDE such as Eclipse (recommended) or NetBeans, you may either set up a Java project that uses the ant script or you may use the following command to ensure the scaffolding and obtaining of the Minecraft server is completed while allowing your IDE to handle the rest:

$ ant first-init

### Roll your own

If you would rather not use ant at all, you will need to place minecraft_server.jar in the lib/ directory (or whichever directory you have set up for it).  Be cautioned that taking care of compiling by yourself entirely may cause conflicts when merging from upstream sources, and the MiddleCraft team may not always be able to help you with these.

## Running

Dump your desired plugins into the plugins directory and run MiddleCraft.jar.
