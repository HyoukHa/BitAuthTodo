import { Typography } from '@mui/material';
import React from 'react';

const Copyright = () => {
  return (
    <Typography variant='body2' color='textSecondary' align="center">
      Copyright ©&nbsp;
      <i className="fa-solid fa-gears" style={{ fontSize: "2rem" }}></i>&nbsp;
      BIT 221th&nbsp;
      {/* {new Date().getFullYear()} */}
    </Typography>
  );
};

export default Copyright;