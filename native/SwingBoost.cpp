#include "SwingBoost.h"
#include <comdef.h>
#include <Shobjidl.h>
#include <Windows.h>

JNIEXPORT jlong JNICALL Java_me_mohammad_swingboost_NativeController_getHandle(JNIEnv *env, jobject object, jstring title) {
	HWND hwnd = NULL;
	const char *str = (env)->GetStringUTFChars(title, NULL);
	hwnd = FindWindow("SunAwtFrame", str);
	(env)->ReleaseStringUTFChars(title, str);
	return (jlong) hwnd;
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_initialize(JNIEnv *env, jobject object) {
	CoInitialize(NULL);
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_uninitialize(JNIEnv *env, jobject object) {
	CoUninitialize();
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_setTaskbarProgressValue(JNIEnv *env, jobject object, jlong jlongHwnd, jlong completed, jlong total) {
	HWND hwnd = (HWND) jlongHwnd;
	ITaskbarList3* pTaskbarList;
	HRESULT hr = CoCreateInstance(CLSID_TaskbarList, NULL, CLSCTX_INPROC_SERVER, IID_PPV_ARGS(&pTaskbarList));
	if (SUCCEEDED(hr)) {
		hr = pTaskbarList->HrInit();
	    if (SUCCEEDED(hr))
	    	pTaskbarList->SetProgressValue(hwnd, (ULONGLONG) completed, (ULONGLONG) total);
	    pTaskbarList->Release();
	}
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_setTaskbarProgressStateNormal(JNIEnv *env, jobject object, jlong jlongHwnd) {
	HWND hwnd = (HWND) jlongHwnd;
	ITaskbarList3* pTaskbarList;
	HRESULT hr = CoCreateInstance(CLSID_TaskbarList, NULL, CLSCTX_INPROC_SERVER, IID_PPV_ARGS(&pTaskbarList));
	if (SUCCEEDED(hr)) {
		hr = pTaskbarList->HrInit();
	    if (SUCCEEDED(hr))
	    	pTaskbarList->SetProgressState(hwnd, TBPF_NORMAL);
	    pTaskbarList->Release();
	}
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_setTaskbarProgressStateError(JNIEnv *env, jobject object, jlong jlongHwnd) {
	HWND hwnd = (HWND) jlongHwnd;
	ITaskbarList3* pTaskbarList;
	HRESULT hr = CoCreateInstance(CLSID_TaskbarList, NULL, CLSCTX_INPROC_SERVER, IID_PPV_ARGS(&pTaskbarList));
	if (SUCCEEDED(hr)) {
		hr = pTaskbarList->HrInit();
	    if (SUCCEEDED(hr))
	    	pTaskbarList->SetProgressState(hwnd, TBPF_ERROR);
	    pTaskbarList->Release();
	}
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_setTaskbarProgressStatePaused(JNIEnv *env, jobject object, jlong jlongHwnd) {
	HWND hwnd = (HWND) jlongHwnd;
	ITaskbarList3* pTaskbarList;
	HRESULT hr = CoCreateInstance(CLSID_TaskbarList, NULL, CLSCTX_INPROC_SERVER, IID_PPV_ARGS(&pTaskbarList));
	if (SUCCEEDED(hr)) {
		hr = pTaskbarList->HrInit();
	    if (SUCCEEDED(hr))
	    	pTaskbarList->SetProgressState(hwnd, TBPF_PAUSED);
	    pTaskbarList->Release();
	}
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_setTaskbarProgressStateNone(JNIEnv *env, jobject object, jlong jlongHwnd) {
	HWND hwnd = (HWND) jlongHwnd;
	ITaskbarList3* pTaskbarList;
	HRESULT hr = CoCreateInstance(CLSID_TaskbarList, NULL, CLSCTX_INPROC_SERVER, IID_PPV_ARGS(&pTaskbarList));
	if (SUCCEEDED(hr)) {
		hr = pTaskbarList->HrInit();
	    if (SUCCEEDED(hr))
	    	pTaskbarList->SetProgressState(hwnd, TBPF_NOPROGRESS);
	    pTaskbarList->Release();
	}
}

JNIEXPORT void JNICALL Java_me_mohammad_swingboost_NativeController_setTaskbarProgressStateIndeterminate(JNIEnv *env, jobject object, jlong jlongHwnd) {
	HWND hwnd = (HWND) jlongHwnd;
	ITaskbarList3* pTaskbarList;
	HRESULT hr = CoCreateInstance(CLSID_TaskbarList, NULL, CLSCTX_INPROC_SERVER, IID_PPV_ARGS(&pTaskbarList));
	if (SUCCEEDED(hr)) {
		hr = pTaskbarList->HrInit();
	    if (SUCCEEDED(hr))
	    	pTaskbarList->SetProgressState(hwnd, TBPF_INDETERMINATE);
	    pTaskbarList->Release();
	}
}
