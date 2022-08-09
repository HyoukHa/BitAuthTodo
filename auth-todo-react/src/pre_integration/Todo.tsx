import { DeleteOutline, DeleteOutlined } from '@mui/icons-material';
import { Checkbox, IconButton, InputBase, ListItem, ListItemSecondaryAction, ListItemText } from '@mui/material';
import React, { useState } from 'react';

interface TodoProps {
  items: {
    id: string,
    title: string,
    done: boolean
  },
  editItem(e: string): void | undefined,
  deleteItem(e: string): void | undefined,
}

const Todo: React.FC<TodoProps> = ({ items, editItem, deleteItem }: TodoProps): React.ReactElement => {
  const [item, setItem] = useState(items);
  const [readOnly, setreadOnly] = useState(true);

  return (
    <div>
      <ListItem>
        <Checkbox />
        <ListItemText>
          <InputBase
            inputProps={{ readOnly: "aria-readonly" }}
            type='text'
            id={item.id}
            name={item.id}
            value={item.title}
            multiline
            fullWidth
          />
        </ListItemText>
        <ListItemSecondaryAction>
          <IconButton>
            <DeleteOutlined />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    </div>
  );
};

export default Todo;