import { AppBar, Button, Grid, Toolbar, Typography } from '@mui/material'
import React from 'react';

const NavBar = () => {
  return (
    <AppBar position='static'>
      <Toolbar>
        <Grid container justifyContent={'space-between'}>
          <Grid item>
            <Typography variant='h6'>오늘의 할 일</Typography>
          </Grid>
          <Grid item>
            <Button color='inherit' raised="true">
              <i className="fa-solid fa-right-from-bracket" style={{ fontSize: '1rem' }}></i>
              &nbsp; 로그아웃
            </Button>
          </Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  );
};

export default NavBar;