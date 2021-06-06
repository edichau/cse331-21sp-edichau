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
import GridSizePicker from "../../hw-dots/src/GridSizePicker";
import EdgeList from "../../hw-dots/src/EdgeList";
import Grid from "../../hw-dots/src/Grid";
import "./App.css";
import MapView from "./MapView";
import Route from "./Route";

interface AppState {
    buildingData: any;
    path: any;
}

class App extends Component<{}, AppState> {

    constructor(props: any) {
        super(props);
        this.state = {
            buildingData: new Map<String, [number, number]>(),
            // locationMap: new Map<String, [number, number]>(),
            path: new Route(),
        };
    }

    async loadBuildingData() {
        try{
            let input = await fetch("http://localhost:4567/campusBuiding");
            if (!input.ok){
                alert("Building data invalid");
                return;
            }
            let parsed = await input.json();
            this.setState({
                buildingData: parsed
            })
        } catch (e){
            alert("Building data invalid" + e);
        }
    };

    async getPath(start: string, end: string) {
        try{
            let input = await fetch("http://localhost:4567/findPath?startBuilding=" + start + "&endBuiding="+ end);
            if (!input.ok){
                alert("Path data invalid");
                return;
            }

            let parsed = await input.json();
            this.setState({
                path: parsed
            })
        } catch (e){
            alert("Building data invalid" + e);
        }
    };

    updatePath = (newPath: Route) => {
        this.setState({
            path: newPath,
        });
    };


    render() {
        return (
            <div>
                <p id="app-title">Campus Map</p>
                <MapView />
            </div>

        );
    }

}

export default App;
