# EPAMHomeworks
___
### Task

>1) Look through the build.gradle files at the project and module levels.

a) sign the release build with a key (generate it first)

To the debug buildtype add ".debug" application Id suffix.

>2) Build and install-uninstall the debug and release builds to a real device or an emulator using:

a) Android Studio Terminal and gradlew,

b) Android Studio Gradle tab.

>3) Add 2 flavors: "prod" and "preprod" under a single dimension.

Define from the default config block a buildConfigField string parameter named "BASE_URL" with the value "https://myserver.com/" and override it to "https://preprod.myserver.com/" for the preprod flavor.

Add "-preprod" version suffix for the preprod.

>4) In you MainActivity create and compose an "appInfo" string which will contain:

- build type

- flavor

- version name

- BASE_URL

and log it from the activity onCreate method to Logcat using Log.d("MainActivity", appInfo) or output it into a TextView.

>5) Put ImageView to the MainActivity layout:

<ImageView

        android:layout_width="wrap_content"

        android:layout_height="wrap_content"

        app:layout_constraintStart_toStartOf="parent"

        app:layout_constraintTop_toTopOf="parent"

        app:srcCompat="@drawable/image" />

and put to res\drawable a picture "image.png". Then override it to any other "image.png" picture for the debug buids using sourcesets.

>6) How many APKs can you build now? How many APKs can you install and use in a single device at once?
___
### Report

![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/key0screenshot.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/signreleaseScreenshot.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/add%20suffix%20Screenshot.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/Task%202Screenshot.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/3.1Screenshot.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/3.2%20Screenshot%20.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/4Screenshot.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/5.1%20Screenshot%20.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/5.2%20Screenshot.png)
![](https://github.com/npogoncuk/EPAMHomeworks/blob/android-general-overview/AndroidGeneralOverview/task1screens/6.1%20Screenshot.png)
