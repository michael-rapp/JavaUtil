# JavaUtil - README

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=X75YSLEJV3DWE)

"JavaUtil" is a library that provides various utility classes for use in Java development. Despite being meant to be used in Java projects, the library itself is implemented using the Kotlin programing language since version 2.0.0. The library currently provides the following features:

- The class `Condition` provides methods, which allow to ensure that variables or objects fulfill certain conditions by throwing exceptions, if conditions are violated, e.g. when an object is null.
- The class `ClassUtil` provides methods, which allow to handle class names.
- The class `StreamUtil` provides methods, which allow to handle streams.
- The class `TextUtil` provides methods, which allow to handle texts.
- The class `IteratorUtil` provides methods, which allow to handle `Iterator`s and `Iterable`s.
- The class `FlagUtil` provides methods, which allow to handle flags.
- The class `Pair` is a generic data structure, which eases to pass around a tuple of two objects.
- The class `Triple` is a generic data structure, which eases to pass around a triple of three objects.
- The class `SortedArrayList` is an extension of the class `java.util.ArrayList`, which keeps its items sorted whenever updated.
- The class `SortedArraySet` is a sorted set, similar to a `java.util.TreeSet`, which detects duplicate items based on their hash codes instead of using the `Comparable` interface or the result of the `compareTo`-method.
- The class `ListenerList` is a list, which is meant to be used for managing listeners.
- The class `FixedPriorityQueue` is a `PriorityQueue` with a fixed maximum size.

## License Agreement

This project is distributed under the Apache License version 2.0. For further information about this license agreement's content please refer to its full version, which is available at http://www.apache.org/licenses/LICENSE-2.0.txt.

## Download

The latest release of this library can be downloaded as a zip archive from the download section of the project's Github page, which is available [here](https://github.com/michael-rapp/JavaUtil/releases). Furthermore, the library's source code is available as a Git repository, which can be cloned using the URL https://github.com/michael-rapp/JavaUtil.git.

Alternatively, the library can be added to your project as a Gradle dependency by adding the following to the `build.gradle` file:

```groovy
dependencies {
    compile 'com.github.michael-rapp:java-util:2.5.0'
}
```

When using Maven, the following dependency can be added to the `pom.xml`:

```xml
<dependency>
    <groupId>com.github.michael-rapp</groupId>
    <artifactId>java-util</artifactId>
    <version>2.5.0</version>
</dependency>
```

## Contact information

For personal feedback or questions feel free to contact me via the mail address, which is mentioned on my [Github profile](https://github.com/michael-rapp). If you have found any bugs or want to post a feature request, please use the [bugtracker](https://github.com/michael-rapp/JavaUtil/issues) to report them.
