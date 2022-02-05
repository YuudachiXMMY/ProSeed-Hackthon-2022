/*
 * Copyright (C) 2021 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import "./App.css";

import React, {Component} from 'react';

import Map from "./Map";
import Buttons from "./Buttons";
import SelectBox from "./SelectBox";

interface AppState {
    path: {
        cost: number,
        start: {x: number, y: number};
        path: {
            cost: number,
            start: {x: number, y: number},
            end: {x: number, y: number}}[];
    };
    buildings: [];
    start: {value: string, label: string};
    end: {value: string, label: string};
}

class App extends Component<{}, AppState> {

    constructor(props: any) {
        super(props);
        this.state = {
            path: {
                cost: 0,
                start: {x: 0, y: 0},
                path: [],
            },
            buildings: [],
            start: {value: "", label: "Select a Starting Building"},
            end: {value: "", label: "Select a Destination Building"}
        };
    }

    componentDidMount() {
        this.requestBuildings();
    }

    // request the backend to get campus buildings
    requestBuildings = async() => {
        await fetch("http://localhost:4567/buildings")
            .then(response => response.json())
            .then(data => {
                this.setState({
                    buildings: data
                });
            })
            .catch(e => {
                console.log(e);
                alert("An Error Occurred on the Server.");
            })
    };

    // request the backend to find a path between selected buildings
    requestFindPath = async() => {
        if(this.state.start["value"].length <= 0) {
            alert("Please select a Starting Building.")
        } else if(this.state.end["value"].length <= 0) {
            alert("Please select a Destination Building.")
        } else {
            let start = this.state.start["value"].toString();
            let end = this.state.end["value"].toString();
            let url = "http://localhost:4567/findPath?" +
                "start=" + start + "&" +
                "end=" + end;
            await fetch(url)
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        path: data
                    });
                })
                .catch(e => {
                    console.log(e);
                    alert("An Error Occurred on the Server.");
                })
        }
    };

    // updates starting building
    updateStart = (s: {value: string, label: string}) => {
        this.setState({start: s});
    };

    // updates destination building
    updateEnd = (e: {value: string, label: string}) => {
        this.setState({end: e});
    };

    // Clear the map and all selected status
    resetAll = () => {
        this.setState({
            path: {
                cost: 0,
                start: {x:0, y:0},
                path: []
            } ,
            buildings: [],
            start: {value: "", label: "Select a Starting Building"},
            end: {value: "", label: "Select a Destination Building"}
        });
    };

    render() {
        return (
            <div>
                <p id="app-title">Campus Paths!</p>
                <Map path={this.state.path}/>
                <p id="button-title">Starting Building:</p>
                <SelectBox buildings={this.state.buildings} value={this.state.start} onChange={this.updateStart}/>
                <br/>
                <p id="button-title">Destination Building:</p>
                <SelectBox buildings={this.state.buildings} value={this.state.end} onChange={this.updateEnd}/>
                <Buttons onFindPath={this.requestFindPath} onResetAll={this.resetAll}/>
            </div>
        );
    }

}

export default App;

