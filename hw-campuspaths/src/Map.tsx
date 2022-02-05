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

import React, {Component} from 'react';
import "./Map.css";

interface MapState {
    backgroundImage: HTMLImageElement | null;
}

interface MapProps {
    path:  {
        cost: number,
        start: {x: number, y: number};
        path: {
            cost: number,
            start: {x: number, y: number},
            end: {x: number, y: number}}[];
    };
}

class Map extends Component<MapProps, MapState> {

    // NOTE:
    // This component is a suggestion for you to use, if you would like to.
    // It has some skeleton code that helps set up some of the more difficult parts
    // of getting <canvas> elements to display nicely with large images.
    //
    // If you don't want to use this component, you're free to delete it.

    canvas: React.RefObject<HTMLCanvasElement>;

    constructor(props: MapProps) {
        super(props);
        this.state = {
            backgroundImage: null,
        };
        this.canvas = React.createRef();
    }

    componentDidMount() {
        this.fetchAndSaveImage();
        this.drawBackgroundImage();
    }

    componentDidUpdate() {
        this.drawBackgroundImage();
    }

    fetchAndSaveImage() {
        // Creates an Image object, and sets a callback function
        // for when the image is done loading (it might take a while).
        let background: HTMLImageElement = new Image();
        background.onload = () => {
            this.setState({
                backgroundImage: background
            });
        };
        // Once our callback is set up, we tell the image what file it should
        // load from. This also triggers the loading process.
        background.src = "./campus_map.jpg";
    }

    drawBackgroundImage() {
        let canvas = this.canvas.current;
        if (canvas === null) throw Error("Unable to draw, no canvas ref.");
        let ctx = canvas.getContext("2d");
        if (ctx === null) throw Error("Unable to draw, no valid graphics context.");
        //
        if (this.state.backgroundImage !== null) { // This means the image has been loaded.
            // Sets the internal "drawing space" of the canvas to have the correct size.
            // This helps the canvas not be blurry.
            canvas.width = this.state.backgroundImage.width;
            canvas.height = this.state.backgroundImage.height;
            ctx.drawImage(this.state.backgroundImage, 0, 0);
        }

        let lines = this.props.path["path"];
        // draws lines of path
        for(let line of lines) {
            this.drawPath(ctx, line);
        }
        let radius = 15;
        // draws dot for start
        if(this.props.path["cost"] !== null) {
            let start = this.props.path["start"];
            ctx.fillStyle = "red";
            ctx.beginPath();
            ctx.arc(start.x, start.y, radius, 0, 2 * Math.PI);
            ctx.fill();
        }
        // draws dot for dest
        if(lines.length > 0) {
            let end = lines[lines.length - 1].end;
            ctx.beginPath();
            ctx.arc(end.x, end.y, radius, 0, 2 * Math.PI);
            ctx.fill();
        }
    }

    drawPath = (ctx: CanvasRenderingContext2D, line: {
            cost: number,
            start: {x: number, y: number},
            end: {x: number, y: number}}) => {
        let start = line["start"];
        let end = line["end"];
        ctx.beginPath();
        ctx.moveTo(start.x, start.y);
        ctx.lineTo(end.x, end.y);
        ctx.strokeStyle = "blue";
        ctx.lineWidth = 15;
        ctx.stroke();
    };

    calcDistance = () => {
        return Math.round(this.props.path["cost"]);
    };

    render() {
        return (
            <div id="map">
                <canvas ref={this.canvas}/>
                <p>Total Distance: {this.calcDistance()} feet</p>
            </div>
        )
    }
}

export default Map;