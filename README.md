# Primaree
[![Build Status](https://travis-ci.com/int02h/primaree.svg?branch=master)](https://travis-ci.com/github/int02h/primaree)
[![Latest release](https://img.shields.io/github/release/int02h/primaree.svg)](https://github.com/int02h/primaree/releases/latest)

Primaree - a simple library for safe initialization of multi-process
Android applications.

### But I have only one process

You think so. But if you take a look at your merged
`AndroidManifest.xml` you may notice that there are some services,
content providers or other Android components that specify
`android:process` - so they work in different processes.

### What's the problem?

The problem is that your `Application.onCreate()` method is being called
as many times as many processes your application has. It means that you
can accidentally initialize your code twice, thrice or even more times.
This may lead to deadlocks or concurrent access to entities that are not
supposed to have concurrent access. For example, SQLite.

### What Primaree actually does?

Just look at the following code:

```kotlin
class App : PrimareeApplication() {

    override fun onPrimaryCreate() {
        // ...
    }

    override fun onSecondaryCreate(name: String) {
        // ...
    }

    override fun onPrimaryConfigurationChanged() {
        // ...
    }

    override fun onSecondaryConfigurationChanged(name: String) {
        // ...
    }
}
```

Primaree provides abstract class `PrimareeApplication` you can inherit
from. Its main purpose is to separate every considerable call in
Application (such as `onCreate` or `onConfigurationChanged`) into two
calls like `onPrimaryCreate` and `onSecondaryCreate`. These methods are
called in the primary process of your application and every secondary
process respectively. All secondary calls also receive the name of the
secondary process as their argument in the same format it appears in
`AndroidManifest.xml`.

It worth to be mentioned that `onCreate` and other considerable calls
are marked `final` so you forced to split your app initialization for
primary and secondary processes. It makes you never forget that
the application is multi-process.

### What if my application class inherits another base class?

In this situation, Primaree is still able to help. It has few handy
extensions for the Application class. You may use them as the following:

```kotlin
class App : Application() {
    
    override fun onCreate() {
        super.onCreate()

        runIfPrimaryProcess {
            // do something in primary process
        }

        if (isPrimaryProcess) {
            // do something in primary process
        } else {
            // do something in secondary process
        }
    }
    
}
```

## Download

Add the following dependency to your `build.gradle` script:

```groovy
dependencies {
    implementation 'com.dpforge:primaree:1.2.2'
}
```

## License

Copyright (c) 2023 Daniil Popov

Licensed under the [MIT](LICENSE) License.