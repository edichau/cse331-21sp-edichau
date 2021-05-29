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
import EdgeList from "./EdgeList";
import Grid from "./Grid";
import GridSizePicker from "./GridSizePicker";

// Allows us to write CSS styles inside App.css, any any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    gridSize: number;  // size of the grid to display
    edges: string;
    edgeList: string[];
}

class App extends Component<{}, AppState> { // <- {} means no props.

    constructor(props: any) {
        super(props);
        this.state = {
            gridSize: 4,
            edges: "",
            edgeList: []
        };
    }

    updateGridSize = (newSize: number) => {
        this.setState({
            gridSize: newSize,
        });
    };

    updateEdgeList = (edges: string) => {
        this.setState({
            edges: edges
        });
    };

    updateParts = (edgeList: string[]) => {
        this.setState({
        edgeList: edgeList
    });
    };

    // emptyEdges = () => {
    //     this.setState({
    //         edgeList: []
    //     });
    // };
    render() {
        const canvas_size = 500;
        return (
            <div>
                <p id="app-title">Connect the Dots!</p>
                <GridSizePicker value={this.state.gridSize.toString()} onChange={this.updateGridSize}/>

                <EdgeList edges = {this.state.edges.toString()} onChange={this.updateEdgeList}
                            edgeList={this.state.edgeList}
                          onEdgeChange={this.updateParts}
                />
                <Grid size={this.state.gridSize} width={canvas_size} height={canvas_size} edgeList={this.state.edgeList}/>
            </div>

        );
    }

}

export default App;
