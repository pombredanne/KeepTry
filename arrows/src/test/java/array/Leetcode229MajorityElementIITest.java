//  Copyright 2016 The Sawdust Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package array;

import org.junit.Assert;
import org.junit.Test;

public class Leetcode229MajorityElementIITest {

    @Test(timeout = 3000l, expected = Test.None.class)
    public void test() {
        Assert.assertEquals(Leetcode229MajorityElementII.MajorityElementOf(
                new int[]{2, 2, 9, 3, 9, 3, 9, 3, 9, 3, 9, 3, 9, 3, 9, 3, 9}).toString(), "[9, 3]");
        Assert.assertEquals(Leetcode229MajorityElementII.MajorityElementOf(
                new int[]{1, 2, 3, 4, 4, 4, 4, 5, 6, 7}).toString(), "[4]");
        Assert.assertEquals(Leetcode229MajorityElementII.MajorityElementOf(
                new int[]{5, 6, 7, 1, 2, 3, 4, 4, 4, 4}).toString(), "[4]");
        Assert.assertEquals(Leetcode229MajorityElementII.MajorityElementOf(
                new int[]{4, 4, 4, 4, 5, 6, 7, 1, 2, 3}).toString(), "[4]");
    }
}
