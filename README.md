Add libary
=====
Add the libary to you project

First add the repository to your build fole

```gradle
repositories {
    maven {
        url  "http://dl.bintray.com/s-gruber-handheld/handheldgroup"
    }
}
```

After that, add the dependency
```gradle
dependencies {
    compile 'com.handheldgroup.cap2dapi:cap2dapi:1.0.0'
}
```

Usage
=====
To start the scanner, call the following code
```java
new ScannerIntent.Builder()
    .setReturnMethod(ScannerIntent.Builder.ResultType.USER_EVENT)
    .build()
    .start(this);
```

This will start the scanner. Once a code has been scanned, a broadcast will be send out. To receive this broadcast, register a listenser like this:
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
