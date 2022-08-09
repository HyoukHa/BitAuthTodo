import './App.css';
import AddTodo from './pre_integration/AddTodo';
import Todo from './pre_integration/Todo';
import { Container } from '@mui/system';
import { List, Paper } from '@mui/material';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { API_BASE_URL } from './server.config';


function App() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    console.log("side effects");
  }, [items])

  const getData = () => {
    axios.get(API_BASE_URL + "/todo")
      .then((res) => {
        setItems(res.data.resList);
        console.log(res.data.resList);
      })
      .catch((error) => { console.log(error) });
  }

  // Todo 조회
  useEffect(() => {
    getData();
  }, [])

  // Todo 생성
  const addItem = (item) => {
    axios.post(API_BASE_URL + "/todo", item)
      .then((res) => { setItems(res.data.resList); })
      .catch((error) => { console.log("err1") });
  }

  // Todo 수정
  const editItem = (item) => {
    axios.put(API_BASE_URL + "/todo", item)
      .then((res) => { setItems(res.data.resList); })
      .catch((error) => { console.log("err2") });
  }

  // Todo 삭제
  const deleteItem = (item) => {
    axios.delete(API_BASE_URL + "/todo", { data: item })
      .then((res) => { setItems(res.data.resList); })
      .catch((error) => { console.log(error) })
  }


  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo addItem={addItem} />
        <Paper style={{ margin: 16 }}>
          <List>
            {items.map((item, idx) => {
              return <Todo key={item.id} items={item} editItem={editItem} deleteItem={deleteItem} />
            })}
          </List>
        </Paper>
      </Container>
    </div>
  );
}

export default App;
