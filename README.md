# cordova example using shadow-cljs

Only tested using the `windows` platform. Other platforms should work too but maybe require some tweaks. Maybe they just work.

Project skeleton created by `npx create-cljs-project cordova-cljs`.

Then
```bash
$ cordova create MyApp
$ cd MyApp
$ cordova platform add windows
# add support for console.log (required since the devtools try to log)
$ cordova plugin add cordova-plugin-console
```

Start shadow-cljs, config outputs to `MyApp/www/js`.

```
$ shadow-cljs watch app
```

Adjust the `MyApp/www/index.html` to load the proper output and rewrite or remove the `Content-Security-Policy` since it will otherwise block the `shadow-cljs` websocket connection.

```html
<!DOCTYPE html>
<html>
    <head>
        <meta name="format-detection" content="telephone=no">
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="viewport" content="initial-scale=1, width=device-width, viewport-fit=cover">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <title>Hello World</title>
    </head>
    <body>
        <script type="text/javascript" src="cordova.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
    </body>
</html>
```

And finally run the app.

```bash
$ cordova run windows
```

## Android

In order for hot code reloading to work on Android you need to do two things:

### Enable clear text traffic.

By default Android will block clear text traffic. You can allow it by adding the following to your `config.xml` file.

```
<edit-config file="app/src/main/AndroidManifest.xml" mode="merge" target="/manifest/application" xmlns:android="http://schemas.android.com/apk/res/android">
  <application android:usesCleartextTraffic="true" />
</edit-config>
```

Note that you might want to disable this in production.


### Add devtools-url

Android emulators will not know how to access localhost, so instead you need to
point it to `10.0.2.2`. You can do this by adjusting the `:devtools`:

```
:devtools {:use-document-host false
           :devtools-url "http://10.0.2.2:9630"}
```
