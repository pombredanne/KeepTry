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

package array.KthMostFrequentNumber;

import java.util.Arrays;
import java.util.Map;

import static array.KthMostFrequentNumber.NTimes.getFrequent;

public class KthMostFrequentNumber4 {
    //O(n) time and O(n) space solution
    public static Integer usingQuickSelect(int[] arr, int kth) {
        Map<Integer, NTimes> map = getFrequent(arr);
        NTimes[] ntimes = new NTimes[map.size()];
        map.values().toArray(ntimes);
      //todo  QuickSelect.introSelectKth(ntimes, 0, arr.length - 1, kth);
        System.out.println(Arrays.toString(ntimes));
        return kth <= ntimes.length ? ntimes[ntimes.length - kth].v : null;
    }
    // Todo: http://www.capacode.com/string/k-most-frequent-items-with-linear-time-solution/
}
