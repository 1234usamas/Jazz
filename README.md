# JazzCash Wallet Preview

A small native Android demo wallet built with Java. It uses sample data and local UI only; it does not connect to JazzCash services, process payments, or collect PINs/passwords.

## Open and run

Open this folder (the one containing `settings.gradle`) in Android Studio. Android Studio uses the included Gradle wrapper to sync and build native Android projects, so the Gradle build scripts and wrapper are required. Use the Android Studio bundled JDK and install Android SDK 35 in SDK Manager, then select the `app` run configuration and your connected phone.

To build a debug APK from PowerShell:

```powershell
.\gradlew.bat :app:assembleDebug
```

The APK is written to `app\build\outputs\apk\debug\app-debug.apk`.

The launcher uses an original wallet preview icon. The in-app JazzCash-inspired layout is for a clearly identified interview preview; it is not the official JazzCash app or a payment client.

## Demo features

- Sample wallet balance and masked sample number
- Send money, mobile load, and bill-payment preview dialogs
- A local-only recipient lookup demo (use `03001234567` to see the sample recipient)
- Recent activity and an activity screen
- QR and profile demo placeholders

All transaction previews are simulated and explicitly state that no money is moved.

## JazzCash recipient lookup research

The public merchant payment API reference does not document a recipient-name lookup. An unofficial copy of a JazzCash Generic Disbursement API draft (v3.3) describes a **Title Fetch API** for checking an MSISDN or IBAN and returning `accountTitle` and `accountStatus`. The copied document omits the actual endpoint URL (shown as `[Link]`) and says client credentials and encryption details are provided during partner onboarding. Treat this as a lead to confirm with JazzCash, not a production integration specification.

This app's lookup is a hard-coded sample fixture, clearly labeled as demo data. It does not call JazzCash. Request the current official Title Fetch API specification, sandbox URL, credentials, encryption requirements, and permitted use from JazzCash before implementing a live integration. Keep any issued secrets on a backend, not in the Android app.
