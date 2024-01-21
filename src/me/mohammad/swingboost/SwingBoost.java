package me.mohammad.swingboost;

import java.awt.Color;
import java.awt.Frame;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SwingBoost {
	
	private static boolean initialized;
	private static final Map<Frame, Long> cache;
	
	private static boolean extracted;
	private static Color accent;
	private static boolean dark;
	
	private static NativeController controller;
	
	static {
		initialized = false;
		cache = new HashMap<>();
	}
	
	private static void _accent() throws Exception {
		final Process process = new ProcessBuilder("cmd", "/c", "reg", "query", "HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\DWM", "/v", "AccentColor").start();
		final InputStream inputStream = process.getInputStream();
		final int code = process.waitFor();
		
		if (code != 0)
			throw new Exception();
		
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		final byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = inputStream.read(buffer)) != -1)
			byteArrayOutputStream.write(buffer, 0, read);
		
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();
		inputStream.close();
		
		final String output = new String(byteArrayOutputStream.toByteArray());
		
		final Pattern pattern = Pattern.compile("REG_DWORD\\s+(0x[0-9a-fA-F]+)");
		final Matcher matcher = pattern.matcher(output);
		
		String match = "null";
		
		if (matcher.find())
			match = matcher.group(1);
		else
			throw new Exception();
		
		if (match.equals("null"))
			throw new Exception();
		final int alpha = Integer.parseInt(match.substring(2, 4), 16);
		final int blue = Integer.parseInt(match.substring(4, 6), 16);
		final int green = Integer.parseInt(match.substring(6, 8), 16);
		final int red = Integer.parseInt(match.substring(8, 10), 16);
		accent = new Color(red, green, blue, alpha);
	}
	
	private static void _dark() throws Exception {
		final Process process = new ProcessBuilder("cmd", "/c", "reg", "query", "HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize", "/v", "AppsUseLightTheme").start();
		final InputStream inputStream = process.getInputStream();
		final int code = process.waitFor();
		
		if (code != 0)
			throw new Exception();
		
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		final byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = inputStream.read(buffer)) != -1)
			byteArrayOutputStream.write(buffer, 0, read);
		
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();
		inputStream.close();
		
		final String output = new String(byteArrayOutputStream.toByteArray());
		
		final Pattern pattern = Pattern.compile("REG_DWORD\\s+(0x[0-9a-fA-F]+)");
		final Matcher matcher = pattern.matcher(output);
		
		String match = "null";
		
		if (matcher.find())
			match = matcher.group(1);
		else
			throw new Exception();
		
		if (match.equals("null"))
			throw new Exception();
		
		dark = (Integer.parseInt(match.substring(2)) == 0);
	}
	
	public static void extract() {
		try {
			_accent();
			_dark();
			extracted = true;
		} catch (Exception e) {
			throw new RuntimeException("Unable to extract theme settings: " + e.getMessage());
		}
	}
	
	public static void initialize() {
		if (initialized)
			throw new RuntimeException("Controller already initialized");
		if (!(System.getProperty("os.name").contains("Windows")))
			throw new RuntimeException("SwingBoost is only usable on Windows");
		controller = new NativeController();
		controller.initialize();
		initialized = true;
	}
	
	public static void uninitialize() {
		if (!(initialized))
			throw new RuntimeException("Controller wasn't initialized");
		controller.uninitialize();
		initialized = false;
	}
	
	public static Color getAccent() {
		if (!(extracted))
			throw new RuntimeException("Theme related functions can only be called after extract() is called");
		return accent;
	}
	
	public static boolean isDark() {
		if (!(extracted))
			throw new RuntimeException("Theme related functions can only be called after extract() is called");
		return dark;
	}
	
	public static void setTaskbarProgressValue(final Frame frame, final long completed, final long total) {
		if (!(initialized))
			throw new RuntimeException("Controller should be initialized first");
		final long handle = getHandle(frame);
		if (handle == 0)
			throw new RuntimeException("Unable to get the handle of the frame");
		controller.setTaskbarProgressValue(handle, completed, total);
	}
	
	public static void setTaskbarProgressState(final Frame frame, final ProgressState state) {
		if (!(initialized))
			throw new RuntimeException("Controller should be initialized first");
		final long handle = getHandle(frame);
		if (handle == 0)
			throw new RuntimeException("Unable to get the handle of the frame");
		switch (state) {
		case NORMAL: 
			controller.setTaskbarProgressStateNormal(handle);
			break;
		case ERROR: 
			controller.setTaskbarProgressStateError(handle);
			break;
		case PAUSED: 
			controller.setTaskbarProgressStatePaused(handle);
			break;
		case NONE: 
			controller.setTaskbarProgressStateNone(handle);
			break;
		case INDETERMINATE: 
			controller.setTaskbarProgressStateIndeterminate(handle);
			break;
		default: 
			break;
		}
	}
	
	private static long getHandle(final Frame frame) {
		final long[] hwnd = new long[1];
		if (cache.containsKey(frame))
			hwnd[0] = cache.get(frame);
		else
			cache.put(frame, (hwnd[0] = controller.getHandle(frame.getTitle())));
		return hwnd[0];
	}
	
	public enum ProgressState {
		
		NORMAL, 
		ERROR, 
		PAUSED, 
		NONE, 
		INDETERMINATE
		
	}
	
}
