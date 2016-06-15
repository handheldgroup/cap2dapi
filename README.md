![Current version](https://img.shields.io/bintray/v/s-gruber-handheld/handheldgroup/cap2dapi.svg?maxAge=2592000&style=flat-square&label=Current%20version "Current version")
# Usage
## Add libary

Add the libary to your project by adding this to your gradle file
```gradle
dependencies {
    compile 'com.handheldgroup.cap2dapi:cap2dapi:1.1.0'
}
```

## Start scanning
To start the scanner, call the following code
```java
new ScannerIntent.Builder()
    .setReturnMethod(ScannerIntent.Builder.ResultType.USER_EVENT)
    .build()
    .start(this);
```

Once a code has been scanned, a broadcast will be send out. To receive this broadcast, register a listenser like this:
```java
registerReceiver(new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra(ScannerIntent.EXTRA_BARCODE_DATA);
        int type = intent.getIntExtra(ScannerIntent.EXTRA_BARCODE_TYPE, 0);
        ((EditText) findViewById(R.id.editTextData)).setText(data);
        ((EditText) findViewById(R.id.editTextType)).setText(SsiCodeTypes.getName(type) + " (" + type + ")");
    }
}, new IntentFilter(ScannerIntent.INTENT_SCAN_RESULT));
```

# Release Notes

### Version 1.1.0
 - Added all common CodeTypes

### Version 1.0.0
 - Initial release

