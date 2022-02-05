import React, {Component} from 'react';
import Select from 'react-select';

interface SelectBoxProps {
    // campus buildings names
    buildings: [];
    // value in the box
    value: {value: string, label: string};
    // called when a selection is made
    onChange(val: {value: string, label: string}): void;
}

/*
 * SelectBox allows client to choose buildings to look for paths.
 */
class SelectBox extends Component<SelectBoxProps> {

    // change the state to the selected value
    onSelectChange = (t: any) => {
        this.props.onChange(t);
    }

    // show campus buildings to select
    generateOptions = () => {
        let selection: any[] = [];
        Object.entries(this.props.buildings).forEach(([key, val]) => {
            selection.push({value: key.toString(), label: val});
        });
        return selection;
    };

    render() {
        return (
            <div>
                <Select
                    options={this.generateOptions()}
                    value={this.props.value}
                    onChange={this.onSelectChange}
                />
            </div>
        );
    }
}

export default SelectBox;