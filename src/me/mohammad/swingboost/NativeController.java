package me.mohammad.swingboost;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NativeController {
	
	private static NativeController instance;
	
	protected NativeController() {
		if (instance != null)
			return;
		try {
			
			final File file = File.createTempFile("lib-win32-SwingBoost-", "-java.dll");
			file.deleteOnExit();
			
			final InputStream inputStream = NativeController.class.getResourceAsStream("SwingBoost.dll");
			final FileOutputStream fileOutputStream = new FileOutputStream(file);
			
			int read = 0;
			final byte[] buffer = new byte[4096];
			while ((read = inputStream.read(buffer, 0, buffer.length)) != -1)
				fileOutputStream.write(buffer, 0, read);
			
			fileOutputStream.flush();
			fileOutputStream.close();
			inputStream.close();
			
			System.load(file.getAbsolutePath());
			
		} catch (IOException e) {
			throw new RuntimeException("Unable to construct native controller: " + e.getMessage());
		}
		
		instance = this;
	}
	
	public native long getHandle(final String title);
	
	public native void initialize();
	
	public native void uninitialize();
	
	public native void setTaskbarProgressValue(final long hwnd, final long completed, final long total);
	
	public native void setTaskbarProgressStateNormal(final long hwnd);
	
	public native void setTaskbarProgressStateError(final long hwnd);
	
	public native void setTaskbarProgressStatePaused(final long hwnd);
	
	public native void setTaskbarProgressStateNone(final long hwnd);
	
	public native void setTaskbarProgressStateIndeterminate(final long hwnd);
	
}
