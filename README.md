# ScreenshotComparisonTest

This is part of a QA testing suite. It checks for pixel-level differences and ensures the images have the same dimensions.

Project relies on the external library ru.yandex.qatools.ashot for performing image comparison. 
AShot is a popular Java library used for capturing screenshots and performing image comparisons in automated tests. 
It provides methods for pixel-by-pixel image comparison.

The @Test annotation is used to define the testImageComparison() method. 
This method tests the image comparison logic, ensuring that the specified criteria for image equality are met. 
