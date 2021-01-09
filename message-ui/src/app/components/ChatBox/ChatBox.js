import React, { useState } from "react";

import MessageDisplayer from "../MessageDisplayer/MessageDisplayer";
import MessageForm from "../MessageForm/MessageForm";

const ChatBox = () => {
  const [list, setList] = useState([]);

  return (
    <>
      <MessageDisplayer messageList={list} setMessageList={setList} />
      <MessageForm setMessageList={setList} />
    </>
  );
};

export default ChatBox;
