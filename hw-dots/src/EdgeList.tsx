/*
 * Copyright (C) 2021 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, {Component} from 'react';
//import {getValue} from "@testing-library/user-event/dist/utils";

interface EdgeListProps {
    edges: string;
    onChange(edges: string): void;  // called when a new edge list is ready
                                 // once you decide how you want to communicate the edges to the App, you should
                                 // change the type of edges so it isn't `any`
    edgeList: string[];
    onEdgeChange(edgeList: string[]): void;

    gridSize: number;
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps> {

    onInputChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
            const edges: string = event.target.value;
            this.props.onChange(edges); // Tell our parent component about the new size.
    };

    onDrawButtonPress = () => {
        const edges: string = this.props.edges;
        let gridSize: number = this.props.gridSize;
        //const userKeyRegExp = /^[0-9]+,[0-9]+\s[0-9]+,[0-9]+\s[a-zA-Z]+\n*$/;
        //if (userKeyRegExp.test(edges)){
            const edgeList: string[] = edges.split("\n");

            let correct : boolean = true;
            let largestCoord : number = 0;
            edgeList.forEach(edge => {
                if(edge.length !== 0) {

                    let parts: string[] = edge.split(" ");
                    if (parts.length !== 3) {
                        correct = false;
                    }
                    for (let i = 0; i < parts.length; i++) {
                        if (i < 2) {
                            if (parts[i].split(",").length !== 2) {
                                correct = false;
                            }
                            if(parseInt(parts[i].split(",")[0]) > largestCoord){
                                largestCoord = parseInt(parts[i].split(",")[0])
                            }
                            if(parseInt(parts[i].split(",")[1]) > largestCoord){
                                largestCoord = parseInt(parts[i].split(",")[1])
                            }
                        }
                    }
                }

            });
            if(!correct) {
                alert("please enter in this format: 'x1,y1 x2,y2 COLOR' with a 1 line for each new line");
            }else if (largestCoord > gridSize){
                alert("Cannot draw edges, grid must be at least size of " + largestCoord+1);
            } else {
                this.props.onEdgeChange(edgeList);
            }

    };

    onClearPress = () => {
        let edgeList : string[] = []
        this.props.onEdgeChange(edgeList);
    }
    render() {
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={this.onInputChange}

                /> <br/>
                <button onClick={this.onDrawButtonPress}>Draw</button>
                <button onClick={this.onClearPress}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
