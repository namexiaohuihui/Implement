package UiAutoTest;

/**
 * @Author: DingDong
 * @Date: 2019/3/8 14:31
 * @USER: Administrator
 * @PACKAGE_NAME: UiAutoTest
 * @PROJECT_NAME: Implement
 * @Description:
 **/

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
public class TestUi extends UiAutomatorTestCase {
    public void testHome(){
        UiDevice.getInstance().pressHome();
    }
}
