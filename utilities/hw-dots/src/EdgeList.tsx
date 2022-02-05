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

interface EdgeListProps {
    value: string;
    onChange(edge: string): void;  // called when a new edge list is ready
                                 // once you decide how you want to communicate the edges to the App, you should
                                 // change the type of edges so it isn't `any`
    onBtnDraw(edge: string): void;
    onBtnClear(): void;
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps> {

    onInputChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        const input: string = event.target.value;
        this.props.onChange(input); // Tell our parent component about the new size.
    };

    onBtnDrawClick = () => {
        this.props.onBtnDraw(this.props.value);
    };

    onBtnClearClick = () => {
        this.props.onBtnClear();
    };

    render() {
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={this.onInputChange}
                    value={this.props.value}
                /> <br/>
                <button onClick={this.onBtnDrawClick}>Draw</button>
                <button onClick={this.onBtnClearClick}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
