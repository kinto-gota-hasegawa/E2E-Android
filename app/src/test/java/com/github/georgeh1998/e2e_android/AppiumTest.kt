package com.github.georgeh1998.e2e_android

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.junit.Test
import org.openqa.selenium.By
import java.io.File
import java.net.URI
import java.util.Base64

internal fun buildDriver(): AndroidDriver {
    val options =
        UiAutomator2Options().apply {
            setCapability("platformName", "Android")
            setCapability("appium:automationName", "uiautomator2")
            setCapability("appium:platformVersion", "14")
            setCapability("appium:noReset", true)
            setCapability("appium:disableIdLocatorAutocompletion", true)
        }

    return AndroidDriver(
        URI("http://localhost:4723").toURL(),
        options,
    )
}


class AppiumTest {

    @Test
    fun test() {
        val driver = buildDriver()
        driver.activateApp("com.github.georgeh1998.e2e_android")
        driver.startRecordingScreen()
        Thread.sleep(5000)
        driver.findElement(By.id("login_button")).click()
        val base64Video = driver.stopRecordingScreen()
        val videoBytes = Base64.getDecoder().decode(base64Video)
        File("test-result.mp4").writeBytes(videoBytes)
        driver.quit()
    }

}