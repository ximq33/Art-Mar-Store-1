import React from 'react';
import ReactDOM from 'react-dom';
import StorePage from './StorePage';

it('It should mount', () => {
  const div = document.createElement('div');
  ReactDOM.render(<StorePage />, div);
  ReactDOM.unmountComponentAtNode(div);
});