import { DeleteOutlined } from '@mui/icons-material';
import { Checkbox, IconButton, InputBase, ListItem, ListItemSecondaryAction, ListItemText } from '@mui/material';
import React, { useState } from 'react';


const Todo = ({ items, editItem = () => { }, deleteItem = () => { } }) => {
  const [item, setItem] = useState({ ...items });
  const [readOnly, setReadOnly] = useState(true);

  const handleCheckbox = (e) => {
    item.done = e.target.checked;
    editItem(item);
  }

  const handleEdit = (e) => {
    setItem({ ...item, title: e.target.value });
    editItem(item);
  }

  const handleDelete = () => {
    deleteItem(item);
  }

  //쓰기 설정
  const turnOffReadOnly = () => {
    setReadOnly(false)
  }

  // 입력 종료
  const turnOnReadOnly = (e) => {
    if (e.key === "Enter") {
      setReadOnly(true);
    }
  }


  return (
    <div>
      <ListItem>
        <Checkbox
          checked={item.done}
          onChange={handleCheckbox}
        />
        <ListItemText>
          <InputBase
            inputProps={{ readOnly: readOnly }}
            onClick={turnOffReadOnly}
            onChange={handleEdit}
            onKeyDown={turnOnReadOnly}
            type='text'
            id={item.id}
            name={item.id}
            value={item.title}
            multiline
            fullWidth
            spellCheck={false}
          />
        </ListItemText>
        <ListItemSecondaryAction>
          <IconButton onClick={handleDelete}>
            <DeleteOutlined />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    </div>
  );
};

export default Todo;