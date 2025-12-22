import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

/* === 원본 백업 START === */
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );
/* === 원본 백업 END === */

/* === LibraryPage START === */
// import LibraryPage from './pages/test/LibraryPage'  // Script가 Script를 import할 땐 확장자(.jsx)를 표시하지 않아도 된다.
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <LibraryPage />
// );
/* === LibraryPage END === */

/* === CommentPage START */
// import CommentPage from './pages/test/CommentPage'
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <CommentPage />
// );
/* === CommentPage END */

/* === ButtonPage START */
import ButtonPage from './pages/test/ButtonPage'
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <ButtonPage />
);
/* === ButtonPage END */

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
