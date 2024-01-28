_SwingBoost ([Deprecated](#Deprecated))_
---
SwingBoost is a Java library that's intended to be used on Windows 7, 8, 8.1, 10, and 11. 
It's a library that can be used either with AWT, and/or Swing (and/or as a Standalone to access the Theme-API) to modify the behavior of the taskbar progress state/value (Taskbar-API) and to retrieve some personalization details of the current user (Theme-API).

The Taskbar-API is basically a wrapper for *_some_* of the functions of the [ITaskbarList3](https://learn.microsoft.com/en-us/windows/win32/api/shobjidl_core/nn-shobjidl_core-itaskbarlist3) interface.

Getting Started
---
You can get started by reading the documentation [here](https://github.com/MomoTheDev/SwingBoost/blob/master/docs/getting_started.md).

Examples
---
You can view some Theme-API examples [here](https://github.com/MomoTheDev/SwingBoost/blob/master/docs/getting_started.md#examples-theme) and some Taskbar-API examples [here](https://github.com/MomoTheDev/SwingBoost/blob/master/docs/getting_started.md#examples-taskbar), in the same documentation as the getting started page.

Building the Library
---
A guide on how you can build the library by yourself can be found [here](https://github.com/MomoTheDev/SwingBoost/blob/master/docs/building_the_library.md), you can also download the prebuilt Java Archive (jar) from the current [release](https://github.com/MomoTheDev/SwingBoost/releases/download/1.0.0/SwingBoost-1.0.0.jar) page.

License
---
This library is currently licenses under the [MIT License](https://github.com/MomoTheDev/SwingBoost/blob/master/LICENSE)

Deprecated
---
You should use the [Taskbar API](https://docs.oracle.com/javase/9/docs/api/java/awt/Taskbar.html) provided by AWT (Java 9+) which is more feature filled and stable. It integrates very well with both AWT & Swing.
