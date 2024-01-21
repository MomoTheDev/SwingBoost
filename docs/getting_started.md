Notice
---

SwingBoost is a Swing library for Windows that mainly enables the user to extract theme settings and modify the taskbar behavior for the application. 
It's important to know that this library is currently still unstable but still works on Windows 7, 8, 8.1, 10 and 11.

First things first...
---

First things first, you have to initialize the library by either calling `SwingBoost.extract()` and/or `SwingBoost.initialize()`, 
now... What exactly do these methods do and why are they separated?
\
This library (v1.0.0) is mainly used to: 
* (Theme) _retrieve the accent color of the current user_
* (Theme) _get the color state (light/dark) of the current user_
* (Taskbar) _update the taskbar progress state (error, normal, paused, none, indeterminate)_
* (Taskbar) _update the taskbar progress value_

Because of the way that this library works, initializing all of the features at once will leave a big overhead.
The `SwingBoost.extract()` method will enable the theme functions to be used and the `SwingBoost.initialize()` method will enable the taskbar functions.
We've basically let the user decide if they want to use the theme, the taskbar, or both APIs in order to minimize the overhead.
\
\
You may ask yourself why the overhead is so big and significant, well...
* the Theme API will spawn two *Command Prompt* processes to retrieve registry values by executing the `reg query` command.
* the Taskbar API, on the other hand, will request the JVM to load a dynamic link library (DLL) while the `SwingBoost.initialize()` method initializes the Windows COM-Library to use the native ITaskbarList3 interface.
Don't forget to call `SwingBoost.uninitialize()` when you're done using the Taskbar API.
\
And finally, you can now go further and experiment with the library by yourself... Maybe after looking at our [examples](#Examples).

Examples (Theme)
---

_*Getting the accent color of the current user*_
```java

// ... application start
SwingBoost.extract();

// ... somewhere in the application cycle
final Color accentColor = SwingBoost.getAccent();

```
\
_*Getting the color state (light/dark) of the current user*_
```java

// ... application start
SwingBoost.extract();

// ... somewhere in the application cycle
final boolean darkTheme = SwingBoost.isDark();

```

Examples (Taskbar)
---

_*Setting the taskbar progress state to indeterminate*_
```java

// ... application start
SwingBoost.initialize();

// ... somewhere in the application cycle
SwingBoost.setTaskbarProgressState(frame, ProgressState.INDETERMINATE);

// ... during the finalization process
SwingBoost.uninitialize();

```
_The `ProgressState` enum contains options for the following states: ERROR, NORMAL, PAUSED, NONE, and INDETERMINATE_
\
\
_*Setting the taskbar progress state and value*_
```java

// ... application start
SwingBoost.initialize();

// ... somewhere in the application cycle
SwingBoost.setTaskbarProgressState(frame, ProgressState.NORMAL);
SwingBoost.setTaskbarProgressValue(frame, 5, 10);

// ... during the finalization process
SwingBoost.uninitialize();

```
_The parameters for the method `setTaskbarProgressValue` are: **Frame** frame, **long** completed, **long** total. In the example above, the progress bar will be stop at the middle of center of the taskbar icon because 5 is half of 10 and because we haven't updated the value yet_
