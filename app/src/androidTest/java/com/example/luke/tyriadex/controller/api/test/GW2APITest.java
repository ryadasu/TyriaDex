/**
 * File generated by Magnet rest2mobile 1.1 - 23/11/2017 12:33:51 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.controller.api.test;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.test.suitebuilder.annotation.Suppress;

import com.magnet.android.mms.MagnetMobileClient;
import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.exception.SchemaException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.luke.tyriadex.controller.api.GW2API;
import com.example.luke.tyriadex.controller.api.GW2APIFactory;

/**
* This is generated stub to test {@link GW2API}
* <p>
* All test cases are suppressed by defaullt. To run the test, you need to fix all the FIXMEs first :
* <ul>
* <li>Set proper value for the parameters
* <li>Remove out the @Suppress annotation
* <li>(optional)Add more asserts for the result
* <ul><p>
*/

public class GW2APITest extends InstrumentationTestCase {
  private GW2API gW2API;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    // Instantiate a controller
    MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(this.getInstrumentation().getTargetContext());
    GW2APIFactory controllerFactory = new GW2APIFactory(magnetClient);
    gW2API = controllerFactory.obtainInstance();

    assertNotNull(gW2API);
  }

  /**
    * Generated unit test for {@link GW2API#getCharacters}
    */
  @Suppress //FIXME : set proper parameter value and un-suppress this test case
  @SmallTest
  public void testGetCharacters() throws SchemaException, ExecutionException, InterruptedException {
    // FIXME : set proper value for the parameters
    String access_token = null;

    Call<List<String>> callObject = gW2API.getCharacters(
      access_token, null);
    assertNotNull(callObject);

    List<String> result = callObject.get();
    
    //TODO : add more asserts
  }

}
