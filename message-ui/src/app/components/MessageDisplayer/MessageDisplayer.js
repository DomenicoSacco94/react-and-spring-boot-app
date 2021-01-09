import React, { Component } from "react";
import Message from "../Message/Message";

export default class MessageDisplayer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      messages: [],
    };
  }

  componentDidMount() {
    fetch("/api/v1/messages")
      .then((res) => res.json())
      .then(
        (messages) => {
          console.log(messages);
          this.setState({
            isLoaded: true,
            messages,
          });
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
    const { error, isLoaded, messages } = this.state;
    console.log(this.state);
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading messages...</div>;
    } else {
      return (
        <div className="chat-message-list">
          {messages.map((message) => (
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
