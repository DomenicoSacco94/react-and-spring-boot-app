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

    const intervalId = setInterval(this.timer.bind(this), 1000);
    this.setState({ intervalId: intervalId });
  }

  timer() {
    // setState method is used to update the state
    getMessages().then((messageList) => {
      this.props.setMessageList(messageList);
    });
  }

  componentDidUpdate(prev) {
    if (prev.messageList.length !== this.props.messageList.length) {
      this.scrollToBottom();
    }
  }

  componentWillUnmount() {
    // use intervalId from the state to clear the interval
    clearInterval(this.state.intervalId);
  }

  scrollToBottom() {
    const objDiv = document.getElementById("chatList");
    if (objDiv) objDiv.scrollTop = objDiv.scrollHeight;
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
        <div
          id="chatList"
          data-cy="chat-message-list"
          className="chat-message-list"
        >
          {this.props.messageList.map((message) => (
            <Message
              key={message.id}
              className={
                message.senderName === this.props.currentUser
                  ? "chat-message-user"
                  : "chat-message"
              }
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
