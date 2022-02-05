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

import React, {Component, ReactText} from 'react';
import EdgeList from "./EdgeList";
import Grid from "./Grid";
import GridSizePicker from "./GridSizePicker";

// Allows us to write CSS styles inside App.css, any any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    gridSize: number;  // size of the grid to display
    edge: string;
    edges: ReactText[][];
}

class App extends Component<{}, AppState> { // <- {} means no props.

    constructor(props: any) {
        super(props);
        this.state = {
            gridSize: 4,
            edge: "",
            edges: [],
        };
    }

    componentDidUpdate() {
        this.checkUpdates();
    }

    componentDidMount() {
        this.checkUpdates();
    }

    checkUpdates() {
        let max:number = 0;
        for(let edge of this.state.edges) {
            let x1 = edge[0] as number;
            let y1 = edge[1] as number;
            let x2 = edge[2] as number;
            let y2 = edge[3] as number;
            max = Math.max(x1, y1, x2, y2, max);
        }
        if(this.state.gridSize <= max) {
            max++;
            alert("Cannot draw edges, grid must be at least size " +
                max + ".");
            this.setState({
                edges: [],
            });
        }
    }

    updateGridSize = (newSize: number) => {
        this.setState({
            gridSize: newSize
        });
    };

    updateEdge = (e: string) => {
        this.setState( {
            edge: e,
        });
    };

    onEdgeDraw = (edge: string) => {
        let edges = edge.toString().split("\n");
        let res: ReactText[][] = [];
        let line: number = 0;
        for(let e of edges) {
            if(e.length === 0) {
                continue;
            }
            line++;
            let edge = e.split(" ");
            if(edge.length !== 3) {
                alert("There was an error with some of your line input.\n" +
                    "For reference, the correct form for each line is: x1,y1 x2,y2 color\n\n" +
                    "Line " + line + ": Missing a portion of the line, or missing a space.");
                break;
            }
            let start = edge[0].split(",");
            let dest = edge[1].split(",");
            let color = edge[2]; // color
            if(start.length !== 2) {
                res.length = 0;
                alert("There was an error with some of your line input.\n" +
                    "For reference, the correct form for each line is: x1,y1 x2,y2 color\n\n" +
                    "Line " + line + ": Wrong number of l4 to the first coordinate.");
                break;
            } else if(dest.length !== 2) {
                res.length = 0;
                alert("There was an error with some of your line input.\n" +
                    "For reference, the correct form for each line is: x1,y1 x2,y2 color\n\n" +
                    "Line " + line + ": Wrong number of l4 to the second coordinate.");
                break;
            }
            let x1 = parseInt(start[0]);
            let y1 = parseInt(start[1]);
            let x2 = parseInt(dest[0]);
            let y2 = parseInt(dest[1]);
            if (isNaN(x1) ||
                isNaN(y1) ||
                isNaN(x2) ||
                isNaN(y2)) {
                res.length = 0;
                alert("There was an error with some of your line input.\n" +
                    "For reference, the correct form for each line is: x1,y1 x2,y2 color\n\n" +
                    "Line " + line + ": Coordinate(s) contain non-integer value(s).");
                break;
            } else if (x1 < 0 ||
                y1 < 0 ||
                x2 < 0 ||
                y2 < 0) {
                res.length = 0;
                alert("There was an error with some of your line input.\n" +
                    "For reference, the correct form for each line is: x1,y1 x2,y2 color\n\n" +
                    "Line " + line + ": Coordinate(s) contain negative value(s).");
                break;
            } else if (x1 >= this.state.gridSize ||
                y1 >= this.state.gridSize ||
                x2 >= this.state.gridSize ||
                y2 >= this.state.gridSize) {
                res.length = 0;
                alert("Cannot draw edges, grid must be at least size " +
                    Math.max(x1+1, y1+1, x2+1, y2+1) + ".");
                break;
            }
            res.push([x1, y1, x2, y2, color]);
        }
        this.setState( {
            edges: res
        });
    };

    onEdgeClear = () => {
        this.setState( {
            edge: "",
            edges: [],
        });
    };

    render() {
        const canvas_size = 500;
        return (
            <div>
                <p id="app-title">Connect the Dots!</p>
                <GridSizePicker value={this.state.gridSize.toString()} onChange={this.updateGridSize} edges={this.state.edges}/>
                <Grid size={this.state.gridSize} width={canvas_size} height={canvas_size} edges={this.state.edges}/>
                <EdgeList value={this.state.edge} onChange={this.updateEdge} onBtnDraw={this.onEdgeDraw} onBtnClear={this.onEdgeClear}/>
            </div>

        );
    };

}

export default App;
