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

package bfs.shortestpath;

import java.util.Map;

public class Node {
    String name;
    // node and distance from curent node to this neighbor node
    Map<Node, Integer> nds;

    // keep calculate result: tentative distance from 'from' node to I
    int td;
    Node from;

    public Node(String name) {
        this.name = name;
        this.td = Integer.MAX_VALUE;
        this.from = null;
    }

    public void init(Map nds) {
        this.nds = nds;
    }

    public void setTd(int td) {
        this.td = td;
    }
}