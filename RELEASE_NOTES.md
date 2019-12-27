# JavaUtil - RELEASE NOTES

## Versoin 2.5.0 (Dec. 28th 2019)

A feature release, which introduces the following changes:

- Added the class `FileUtil` which provides methods for handling files.
- Updated the Kotlin programming language to version 1.3.61.

## Version 2.4.1 (Aug. 6th 2019)

A bugfix release, which fixes the following issues:

- The `createFilteredIterable`-, `createFilteredIterator`-, `createNestedIterable`-, and `createNestedIterator`-methods of the class `IteratorUtil` now properly handle null values.
- Added the `createNotNullIterable`- and `createNotNullIterator`-methods to the class `IteratorUtil`.

## Version 2.4.0 (Jul. 16th 2019)

A feature release, which introduces the following features:

- Update Kotlin programming language to version 1.3.30.
- Added the class `FlagUtil`.

## Version 2.3.0 (Apr. 3rd 2019)

A feature release, which introduces the following features:

- Added the class `FixedPriorityQueue`.

## Version 2.2.1 (Mar. 9th 2019)

A minor release, which introduces the following changes:

- Added `createNestedIterable`- and `createNestedIterator`-method to class `IteratorUtil`.

## Version 2.2.0 (Mar. 8th 2019)

A feature release, which introduces the following features:

- Added the interfaces `Provider`, `Visitor` and `Identifiable`.
- Added the utility class `TextUtil` that provides methods for handling texts.
- Added the utility class `IteratorUtil` that provides methods for handling `Iterator`s and `Iterable`s.

## Version 2.1.1 (Feb. 23th 2019)

A minor release, which introduces the following changes:

- Update Kotlin programming language to version 1.3.21.

## Version 2.1.0 (Feb. 19th 2019)

A feature release, which introduces the following changes:

- The class `SortedArraySet` now implements the interface `NavigableSet`, but only the `pollFirst`- and `pollLast`-methods are implemented yet.
- The class `SortedArrayList` now uses a binary search to implement the `indexOf`-, `lastIndexOf`-, `contains`- and `remove`-methods more efficiently.
- The `Comparator` that is used by the class `SortedArrayList` can now be replaced by invoking the `sort`-method.
- The classes `Pair` and `Triple` now implement the interface `Serializable`.

## Version 2.0.2 (Jan. 20th 2019)

A minor release, which introduces the following changes:

- Updated Kotlin programming language to version 1.3.11.

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