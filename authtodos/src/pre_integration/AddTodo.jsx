import { Paper, Grid, TextField, Button } from '@mui/material';
import React, { useState } from 'react';

const AddTodo = ({ addItem }) => {
  const [items, setItems] = useState({ title: '' });

  const handleChange = (e) => {
    setItems({ ...items, title: e.target.value });
  }

  const onButtonClick = (e) => {
    // props로 내려받은 add함수로 
    addItem(items);
    console.log(items);
    setItems({ ...items, title: '' })
  }

  const enterKeyEventHandler = (e) => {
    if (e.key === "Enter") {
      onButtonClick()
    }
  }

  return (
    <Paper style={{ margin: 16, padding: 30 }}>
      <Grid container>
        <Grid item xs={11} md={11} sm={11}>
          <TextField
            placeholder="hello"
            fullWidth
            value={items.title}
            onChange={handleChange}
            onKeyPress={enterKeyEventHandler}
          >

          </TextField>
        </Grid>

        <Grid item xs={1} md={1} sm={1}>
          <Button
            color="secondary"
            variant="text"
            style={{ height: "100%" }}
            onClick={onButtonClick}
          >
            <i className="fa-solid fa-box-archive" style={{ fontSize: "2rem" }}></i>
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default AddTodo;