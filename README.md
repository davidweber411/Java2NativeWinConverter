# Description

With this application you can easily convert a JAR application into a native Windows application either as portable
application or with an
installer.

This application is a GUI for the JPackage tool of the JDK.

This application uses its own wrapped OpenJDK 17.

What you need to do:

1. Compile the fat/uber JAR of this application
2. Start this application
3. Convert an JAR application to an EXE or MSI
4. Profit.

# 1. Compile the fat/uber JAR of this application

1. Clone this repository
2. Navigate to the project
3. Execute the gradle task "createCustomFatJar".

###### Windows:

    .\gradlew createCustomFatJar

###### Linux:

    ./gradlew createCustomFatJar

# 2. Start this application

You just created a fat/uber JAR. Just double click on the JAR and start it.

# 3. Convert an JAR application to an EXE or MSI

Set the configurations in the GUI and that's it!

