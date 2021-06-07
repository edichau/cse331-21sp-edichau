import React, {Component} from 'react';

interface UserInProps {
    onChange(start: String, end: String): void;
    value: String[];
}

interface UserInState {
    start: string;
    end: string;
}

//This class handles all of the user input
class UserInput extends Component<UserInProps, UserInState> {
    constructor(props: UserInProps) {
        super(props);
        this.state = {
            start: "",
            end: ""
        }
    }

    onClickSubmit = () => {
        this.props.onChange(this.state.start, this.state.end)
    }

    onClickClear = () => {
        this.setState({
            start: "",
            end: ""
        })
        this.props.onChange("", "");

    }

    onInputStartChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        let start: string = event.target.value;
        this.setState({
            start: start
        })
    }

    onInputEndChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        let end: string = event.target.value;
        this.setState({
            end: end
        })
        console.log(this.state.end);
    }

    render() {
        return (
            <div id="input">
                <br/>
                Start:
                <select value={this.state.start} onChange={this.onInputStartChange}>
                    {this.props.value.map((x, y) => <option key={y}>{x}</option>)}
                </select>
                <br/>
                Destination:
                <select value={this.state.end} onChange={this.onInputEndChange}>
                    {this.props.value.map((x, y) => <option key={y}>{x}</option>)}
                </select>
                <br/>
                <br/>
                <button onClick={() => {
                    this.onClickSubmit()
                }}>
                    Submit
                </button>
                <button onClick={() => {
                    this.onClickClear()
                }}>
                    Clear
                </button>
            </div>
        )
    }
}

export default UserInput;