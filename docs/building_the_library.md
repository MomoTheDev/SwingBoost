Building SwingBoost
---

Building SwingBoost may be a bit complicated for beginners but it's really just a process of executing some commands.

Make sure you have all the [requirements](#requirements) met.

Step 1: We have to first compile the dynamic link library (DLL)...
1. Launch a _x64 Developer Visual Studio_ command prompt terminal instance
3. Pull the repository using `git pull https://github.com/MomoTheDev/SwingBoost.git` (you probably need to do this in Git Bash)
4. Enter the native directory using `cd SwingBoost\native`
5. Compile using this command  `cl /clr /LD -I"path/to/jdk/include" -I"path/to/jdk/include/win32" Ole32.lib SwingBoost.cpp` (make sure to replace _path/to/jdk_ to the actual path of your jdk)
6. Done, move to the second step!
\
\
Step 2: Now, we have to move the generated dynamic link library (DLL)...
1. Select the generated `SwingBoost.dll` file and copy it using _CTRL+C_
2. Go back to the root directory _SwingBoost/_
3. Go to `src/me/mohammad/swingboost` and paste the file using _CTRL+V_
4. Done, move to the third step!
\
\
Step 3: Finally, we can compile the final jar file...
1. Go to the source directory (_.../SwingBoost/src_) and spawn a new terminal
2. Execute `javac me/mohammad/swingboost/*.java`
3. Execute `jar cf ../SwingBoost.jar me/mohammad/swingboost/*.class me/mohammad/swingboost/SwingBoost.dll`
4. Enjoy! You'll find the jar file in the parent directory.

Requirements
---
* The Microsoft Visual C/C++ Compiler (cl.exe)
* A JDK (latest version, the one used for the prebuilt binaries is _OpenJDK-21.0.2_)
