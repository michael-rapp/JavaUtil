# JavaUtil - RELEASE NOTES

## Version 2.0.1 (Aug. 21th 2018)

A minor release, which introduces the following changes:

- Added `open` modifiers to the classes `SortedArrayList` and `SortedArraySet`. 

## Version 2.0.0 (Aug. 7th 2018)

A major release, which introduces the following changes:

- Migrated the project to use the Kotlin programming language instead of Java.
- Added `ensureHasText`-methods to the class `Condition`.
- Removed the classes `ArrayUtil` and `FileUtil`.

## Version 1.2.0 (Jan. 25th 2017)

A feature release, which introduces the following changes:

- Added the data structure `ListenerList`, which is meant to be used for managing listeners.

## Version 1.1.1 (Jul. 31th 2017)

A minor release, which introduces the following changes:

- Removed `final` modifiers from methods of the class `SortedArrayList` and `SortedArraySet`.

## Version 1.1.0 (Jul. 28th 2017)

A feature release, which introduces the following changes:

- The class `SortedArrayList` has been added.
- The class `SortedArraySet` has been added.

## Version 1.0.0 (Jun. 7th 2017)

The first stable release of the library, which provides the following utility classes:

- The class `Condition` provides methods, which allow to ensure that variables or objects fulfill certain conditions by throwing exceptions, if conditions are violated, e.g. when an object is null.
- The class `ClassUtil` provides methods, which allow to handle class names.
- The class `ArrayUtil` provides methods, which allow to handle arrays.
- The class `FileUtil` provides methods, which allow to handle files.
- The class `StreamUtil` provides methods, which allow to handle streams.
- The classes `Pair` and `Triple` implement generic data structures, which ease to pass around two or three objects.