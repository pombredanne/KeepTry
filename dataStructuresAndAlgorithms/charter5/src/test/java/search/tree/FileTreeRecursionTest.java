package search.tree;//  Copyright 2016 The Sawdust Open Source Project
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

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileTreeRecursionTest {

    @Test(timeout = 100L, expected = Test.None.class)
    public void test() {
        try {
            List<File> r = new LinkedList<>();
            FileTreeRecursion.find(new File("../").getCanonicalFile() , "BUCK", r);
            Assert.assertEquals(r.get(0).getName(), "BUCK");
        } catch (IOException e) {
            //
        }
    }
}