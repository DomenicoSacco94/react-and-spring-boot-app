import React, { Component } from "react";
import Message from "../Message/Message";
import { getMessages } from "../../services/MessageService";

export default class MessageDisplayer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
    };
  }

  componentDidMount() {
    getMessages().then(
      (messageList) => {
        this.setState({
          isLoaded: true,
        });
        this.props.setMessageList(messageList);
      },
      (error) => {
        this.setState({
          isLoaded: true,
          error,
        });
      }
    );
  }

  render() {
    const { error, isLoaded } = this.state;
    console.log(this.state);
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading messages...</div>;
    } else {
      return (
        <div className="chat-message-list">
          {this.props.messageList.map((message) => (
            <Message
              key={message.id}
              senderName={message.senderName}
              text={message.text}
              sendDate={message.sendDate}
            />
          ))}
        </div>
      );
    }
  }
}
