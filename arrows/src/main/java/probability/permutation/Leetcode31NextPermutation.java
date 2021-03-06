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

package probability.permutation;

/**
 * <pre>
 *  31. Next Permutation  QuestionEditorial Solution  My Submissions
 * Total Accepted: 71358
 * Total Submissions: 264129
 * Difficulty: Medium
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 *
 *  Tags Array
 *  Similar Problems :
 *
 *  (M) Permutations
 *  (M) Permutations II
 *  (M) Permutation Sequence
 *  (M) Palindrome Permutation II
 *
 *   1 input check: null,  {}, {one element}
 *
 *   2 left <--right . find the arr[i] <  arr[i+1]
 *
 *        if not found then reverse the whole arr;
 *        else
 *              i is the one 'need swap';
 *              find the j to 'swap with':  left <---( try binary search )----right . arr[j] > 'need swap'.
 *
 *             a>  swap arr[i] and arr[j]
 *             b>  reverse [i+1~ end]
 *
 *  case 1
 *     9 3 4 9 8 6 5 1
 *         |
 *         i       |
 *                 j
 *
 *     9 3 5 9 8 6 4 1
 *           |
 *           i+1
 *     9 3 5 1 4 6 8 9
 *
 *  case 2
 *     5 4 3 2
 *     2 3 4 5
 *
 *  case 3
 *     null
 *     {}
 *     {1}
 *
 *     Note:
 *          it required "If such arrangement is not possible,
 *          it must rearrange it as the lowest possible order (ie, sorted in ascending order)."
 *          This make the resolution is not common; If without this special condition. Its pros:
 *          1 can process all permutations for a given array
 *          2 array can have duplicated number.
 *          3 array do not need sort in this resolution.
 *          see {@link Leetcode47PermutationsII4}
 *
 * @see <a href="https://leetcode.com/problems/next-permutation/">leetcode</a>
 */
public class Leetcode31NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2; // pre peak
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                for (int j = nums.length - 1; j >= 0; j--) { // bigger
                    if (nums[j] > nums[i]) {
                        nums[i] ^= nums[j];
                        nums[j] ^= nums[i];
                        nums[i] ^= nums[j];
                        break;
                    }
                }
                break;
            }
        }
        i++;
        int j = nums.length - 1;
        while (i < j) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
            i++;
            j--;
        }
    }
}
