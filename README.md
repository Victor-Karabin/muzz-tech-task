# muzz-tech_task

# Getting Started

### Requirements

https://docs.google.com/document/d/1pMAbbc7rKCvHDHINomcYKC86mADCbqNgGQKrCufBiT4/export?format=pdf

# Build and Test
* To build the project, from the commandline, run ./gradlew clean build
* For running Unit Tests from the commandline, run ./gradlew test
* For running Android Tests from the commandline, run ./gradlew cAT
* For debugging TransactionTooLargeExceptions, run adb logcat -s TooLargeTool
* For debugging memory leaks, run adb logcat -s LeakCanary

# Implementation decisions
For development, standard practices, recommendations, and tech stack were used:
- simplified implementation of a clean architecture, divided into layers (data, domain, ui)
- MVVM
- Jetpack Compose + Material Design3
- Kotlin Coroutines
- Hilt
- Room
- Unit tests

# App limitations
- Working with the database is simplified and not optimized. Adding several thousand records will 
likely cause performance issues. For a real project, a different solution is needed
- The application doesn't support dynamic colors (Android 12+)
- The application support implementation of dark theme, but for a real project, a design is needed
- Accessibility Scanner shows few colors-problems. A real design is needed

# What I would do given more time (maybe some clauses are overhead for this simple project)
- modular approach for better isolation of components
- more tests
- more code analysis (detekt, ktlint)
- more accurate work with branches and Git
- more optimize communication with data base
- pagination for messages
- more error handling
- empty state handling
- load state handling
- add dark theme and dynamic color
- add design system
