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

package queue;

public class ArrayCircularQueue<T> extends ArrayFIFOQueue2<T> implements CircularQueue<T> {
    @Override
    public void rotate() {
        if (size() != 0) {
            //offer(poll());
            augmentImplementationRotate();
        }
    }

    // R-6.15
    public void augmentImplementationRotate() {
        if (size() != 0) {
            T r = d[head];
            d[head] = null;
            d[(head + size()) % d.length] = r;
            head = (head + 1) % d.length;
        }
    }
}
