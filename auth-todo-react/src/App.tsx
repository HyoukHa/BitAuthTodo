import './App.css';
import AddTodo from './pre_integration/AddTodo';
import Todo from './pre_integration/Todo';
import { Container } from '@mui/system';
import { List, Paper } from '@mui/material';
import React, { useState } from 'react';
import axios from 'axios';

interface DataType {
  id: string
  title: string
  done: boolean
}

const data = {
  items: [
    { id: '0', title: "리액트 컴포넌트 만들기 연습", done: true },
    { id: '1', title: "리액트 훅 useState 연습", done: false },
    { id: '2', title: "리액트 훅 useEffect 연습", done: true },
    { id: '3', title: "리액트 훅 useRef 연습", done: true },
    { id: '4', title: "리액트 훅 useMemo 연습", done: false },
    { id: '5', title: "리액트 훅 useReducer 연습", done: true },
  ]
};

function App() {
  const [items, setItems] = useState(data.items);

  // Todo 생성
  const addItem = (item: string) => {

  }

  // Todo 수정
  const editItem = (item: string) => {
    setItems
  }

  // Todo 삭제
  const deleteItem = (item: string) => {

  }

  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo />
        <Paper style={{ margin: 16 }}>
          <List>
            {items.map((item, idx: number) => {
              return <Todo editItem={editItem} deleteItem={deleteItem} key={idx} items={item} />
            })}
          </List>
        </Paper>
      </Container>
    </div>
  );
}

export default App;
