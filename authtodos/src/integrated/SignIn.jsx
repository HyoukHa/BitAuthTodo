import { Container } from '@mui/system';
import { Button, Grid, TextField, Typography } from "@mui/material";
import React from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../server.config';

const SignIn = () => {
  const signIn = (userDTO) => {
    axios.post("http://localhost:8080/api/user/signin", userDTO)
      .then((res) => {
        console.log("success")
        alert(res.data.token);
      })
      .catch((error) => {
        console.log("fail");
        console.log(error);
      });
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = new FormData(e.target);
    const username = data.get("username");
    const password = data.get("password");
    console.log("username: " + username);
    console.log("password: " + password);

    signIn({ username: username, password: password });
  }

  return (
    <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
      <Grid container spacing={2} >
        <Grid item xs={12}>
          <Typography component='h1' variant='h5'>
            <span>LogIn</span>
            <i className="fa-solid fa-key" style={{ marginLeft: '10px', color: '#ccc' }}></i>


          </Typography>

        </Grid>
      </Grid>

      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              variant="outlined"
              required
              fullWidth
              id="username"
              name="username"
              label="id"
              autoComplete='username'
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              variant="outlined"
              required
              fullWidth
              type="password"
              id="password"
              name="password"
              label="password"
              autoComplete='password'
            />
          </Grid>
          <Grid item xs={12}>
            <Button type="submit" fullWidth variant="contained" color="primary">
              SignIn
            </Button>
          </Grid>
        </Grid>
      </form>


    </Container >
  );
};

export default SignIn;