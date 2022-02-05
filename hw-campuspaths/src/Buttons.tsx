import React, {Component} from 'react';

interface ButtonsProps {
    // called when a Find Path request is made
    onFindPath: () => Promise<void>;
    // called when a Reset Request is made
    onResetAll: () => void;
}

/*
 * Buttons allows client to find path or clear all selections
 */
class Buttons extends Component<ButtonsProps> {

    // draw found path to the map
    onFindPathClick = () => {
        this.props.onFindPath();
    };

    // reset all state to original
    onResetAllClick = () => {
        this.props.onResetAll();
    };

    render () {
        return (
            <div id="buttons">
                <button onClick={this.onFindPathClick}>Search</button>
                <button onClick={this.onResetAllClick}>Clear</button>
            </div>
        );
    }
}

export default Buttons;