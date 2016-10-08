package common_lib;//  Copyright 2016 The Sawdust Open Source Project
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Common {
    /**
     * <pre>
     * <a href="http://stackoverflow.com/questions/3153337/get-current-working-directory-in-java">
     *     current working directory in Java </a>
     */
    public static File getLocalFile(String name, Class className) {
        String current = className.getName();
        current = current.substring(0, current.lastIndexOf(".", current.length() - 1)).replace(".", "/");
        String location = className.getProtectionDomain().getCodeSource()
                .getLocation().getFile();
        return new File(location + current + "/" + name);
    }

    // Assume the file exists and readable
    public static List<String> LinesOf(File f) {
        List<String> lines = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                lines.add(curLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Integer[] toIntegerArray(int[] ints) {
        if (ints == null) {
            return null;
        }
        Integer[] arr = new Integer[ints.length];
        int index = 0;
        for (int i : ints) {
            arr[index++] = i;
        }
        return arr;
    }

    public static <T extends Object> void reverse(T[] arr) {
        Collections.reverse(Arrays.asList(arr));
    }

    public static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //   a < b
    public static boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    //  a = b
    public static boolean same(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    //  a > b
    public static boolean greatThan(Comparable a, Comparable b) {
        return lessThan(b, a);
    }

    /**
     * Divide arr into 2 halves
     * Note this method assume arr.length() >= 2
     *
     * @param arr
     * @param <T>
     * @return 2 divided 2 sub arrays
     */
    public static <T extends Comparable<T>> Comparable[][] divide(T[] arr) {
        Comparable[][] re = new Comparable[2][];

        Comparable[] l = new Comparable[arr.length / 2];
        Comparable[] r = new Comparable[arr.length - arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            l[i] = arr[i];
        }
        for (int i = arr.length / 2, j = 0; i < arr.length; i++, j++) {
            r[j] = arr[i]; // note here
        }
        re[0] = l;
        re[1] = r;
        return re;
    }

    /**
     * Merge arrays l and r into array re
     * Note: Assume l.length() + r.length() == re.length()
     *
     * @param l  sorted left half
     * @param r  sorted right half
     * @param re result
     */
    public static void mergeInsort(Comparable[] l, Comparable[] r, Comparable[] re) {
        // System.out.format("\nmerge %s and %s\n", Arrays.toString(l), Arrays.toString(r));
        int i = 0, j = 0; // in loop the current index of l and r
        int k = 0;
        while (true) {
            if (i == l.length && j == r.length) {
                break;
            } else if (i < l.length && (
                    j == r.length || j < r.length && lessThan(l[i], r[j])
            )) {
                re[k++] = l[i++];

            } else { // Without else, k will trigger java.lang.ArrayIndexOutOfBoundsException
                re[k++] = r[j++];
            }
        }
        // System.out.format("\n     -->   %s\n", Arrays.toString(re));
    }

    /**
     * For a given array, sort its sub array [l, r]
     * Assume
     * 1> [l, mid] and [mid+1, r] are sorted already.
     * 2> not thread safe.
     * 3> l < r
     *
     * @param ar  array
     * @param l   left index, included.
     * @param mid index
     * @param r   right index, included.
     * @param tmp In one thread. this tmp array is provided for performance concern in recursion.
     *            without `new` more tmp arrays.
     */
    public static void mergeInsort(Comparable[] ar, int l, int mid, int r, Comparable[] tmp) {
        // System.out.format("\nmerge %s : index scope[%s, %s] and  index scope [%s, %s]", Arrays.toString(ar), l, mid, mid + 1, r);

        // Input check
        if (l >= r) {
            return;
        }

        // Improved
        if (lessThan(ar[mid], ar[mid + 1])) {
            return;
        }

        for (int i = l; i <= r; i++) {
            tmp[i] = ar[i];
        }
        // Start merge in sort. Rewrite arr from l
        int i = l, j = mid + 1;
        int k = l;
        while (true) {
            if (i == mid + 1 && j == r + 1) {
                /* Both have no member */
                break;
            } else if (i <= mid && (
                    j == r + 1 || j <= r && lessThan(tmp[i], tmp[j]))) {
                ar[k++] = tmp[i++];
            } else { // Without else, k will trigger java.lang.ArrayIndexOutOfBoundsException
                ar[k++] = tmp[j++];
            }
        }
        // System.out.format(" -->   %s \n", Arrays.toString(ar));
    }

    /**
     * Improvement of selecting the pivot.
     * <pre> Idea:
     * 1  Select the median value:  lv<= mv  <= rv.
     * 2  Allocate the pivot to l .
     *
     * @param a   array
     * @param l   left index, the place of pivot.
     * @param r   right index
     * @param m   middle index
     * @param <T>
     */
    public static <T extends Comparable<T>> void initPivotUsingMedianOf3(T[] a, int l, int r, int m) {
        if (r == l + 1) {
            return;
        }

        Comparable vl = a[l];
        Comparable vr = a[r];
        Comparable vm = a[m];

        int medianIndex = lessThan(vl, vm)
                ? lessThan(vm, vr) ? m : lessThan(vl, vr) ? r : l
                : lessThan(vl, vr) ? l : lessThan(vm, vr) ? r : m;

        if (medianIndex != l && a[medianIndex] != vl) {// Note: their values may be equal
            swap(a, l, medianIndex);
        }
    }
}