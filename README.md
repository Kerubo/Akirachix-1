Akirachix Project
===============================
This project contains basic Android tutorials to help you get started in Android.
The following project is what the Akirachix 2014 class learned.

Setup Instructions:
===================


1. Android Studio IDE
=======================

Doesn't require additional configuration. Gradle does it for you.
1. Open Android Studio.
2. Select Import project or Import module.
3. You are good to go.

2. IDEA IDE
=======================
If you don't use Gradle:

1. Open Module Settings
2. Import “appcompat” module from <android-sdk>/extras/android/support/v7
3. Add dependency to module “appcompat”
4. Add dependency to libraries pack (android-support-v4.jar, android-support-v7-appcompat.jar) they were imported with appcompat project

Note: When you import sample IDE automatically adds appcompact as module and as a dependency, but if not then do it yourself.

3. Eclipse IDE
=======================
1. Import “appcompat” lib project from <android-sdk>/extras/android/support/v7
2. Configure appcompat. Project->Properties->Android:
    - set Project Build Target 4.4.2 (You can select your build target version. It is recommended you build with the highest)
    - check “Is Library” should be set.
3. Configure sample. Project->Properties->Android:
    - add appcompat
    - set Project Build Target 4.4.2
4. Project->Properties->Java Build Path->Libraries, “Add External JARs…”
    - All all the Libraries in the Libs directory
    - Go to Order & Export tab and check
    - Clean and rebuild project.

